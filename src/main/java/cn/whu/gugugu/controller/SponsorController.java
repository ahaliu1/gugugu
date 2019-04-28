package cn.whu.gugugu.controller;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.domain.*;
import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.PartyRecord;
import cn.whu.gugugu.generated.model.Transaction;
import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.service.PartyServiceImpl;
import cn.whu.gugugu.service.UserServiceImpl;
import cn.whu.gugugu.service.impl.PartyService;
import cn.whu.gugugu.service.impl.UserService;
import cn.whu.gugugu.utils.FixedPointNumber;
import cn.whu.gugugu.utils.UID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RestController
public class SponsorController extends AuthenticatedController {
    @Autowired
    PartyService partyService = new PartyServiceImpl();
    @Autowired
    UserService userService = new UserServiceImpl();

    /**
     * 创建聚会
     *
     * @param name       聚会名称
     * @param fee        每人的积分
     * @param time       集合时间
     * @param longtitude 经度
     * @param latitude   纬度
     * @param partyId    partyId
     * @return
     */
    @RequestMapping(value = "/party/launch", method = RequestMethod.POST)
    public BaseResponse launchParty(@RequestParam(value = "name") String name,
                                    @RequestParam(value = "fee") String fee,
                                    @RequestParam(value = "time") long time,
                                    @RequestParam(value = "latitude") double latitude,
                                    @RequestParam(value = "longtitude") double longtitude,
                                    @RequestParam(value = "party_id", required = false) String partyId) {
        User user = this.getRequestedUser();
        int deposit = FixedPointNumber.toInteger(fee);

        //余额不足
        if (user.getAccount() < deposit) {
            return new BaseResponse("score not enough");
        }

        //聚会名字过长
        if (name.length() > 45) {
            return new BaseResponse("name overlength");
        }

        //经度不合法。最大是180° 最小是0°
        if (0.0 > longtitude || 180.0 < longtitude) {
            return new BaseResponse("invalid longtitude");
        }

        //纬度不合法。最大是90° 最小是0°
        if (latitude > 90 || latitude < 0) {
            return new BaseResponse("invalid latitude");
        }

        //金额数字格式无效。负数，不是小数后两位
        if (deposit < 0 || !Pattern.matches("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){2})?$", fee)) {
            return new BaseResponse("invalid fee");
        }

        Party party = new Party();
        party.setPartySubject(name);
        party.setPartyDate(new Date(time));
        party.setLatitude((float) latitude);
        party.setLongtitude((float) longtitude);

        if (partyId == null || partyId.isEmpty()) {
            //新建
            String id = UID.getUUID();

            //party
            party.setPartyId(id);
            party.setTotalSum(1);
            party.setDeposit(deposit);
            party.setOriginator(user.getOpenId());
            party.setMode(0);
            partyService.createParty(party);

            //party record
            PartyRecord record = new PartyRecord();
            record.setPartyId(id);
            record.setRecordId(UID.getUUID());
            record.setStatus(0);
            record.setUserId(user.getOpenId());
            partyService.createRecord(record);

            //transaction
            Transaction transaction = new Transaction();
            transaction.setTransactionId(UID.getUUID());
            transaction.setPartyId(id);
            transaction.setUserId(user.getOpenId());
            transaction.setMoney(deposit);
            transaction.setPaymentTime(new Date());
            partyService.createTransaction(transaction);

            //扣钱
            partyService.pay(user, party);
        } else {
            //更新
            party.setPartyId(partyId);
            party.setDeposit(null);
            partyService.updateParty(party);
        }

        return new BaseResponse("ok", new PartyIdResponse(party.getPartyId()));
    }

    @RequestMapping(value = "/party/pay", method = RequestMethod.POST)
    public BaseResponse pay(@RequestParam(value = "party_id") String partyId) {
        User user = this.getRequestedUser();
        Party party = partyService.getInfo(partyId);

        //余额不足
        if (user.getAccount() < party.getDeposit()) {
            return new BaseResponse("score not enough");
        }

        //聚会加入入口已关闭（或聚会已结束）
        if (party.getMode() != 2) {
            return new BaseResponse("entry closed");
        }

        //加入。新建流水
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UID.getUUID());
        transaction.setPartyId(partyId);
        transaction.setUserId(user.getOpenId());
        transaction.setMoney(party.getDeposit());
        transaction.setPaymentTime(new Date());
        partyService.createTransaction(transaction);

        //party record
        PartyRecord record = new PartyRecord();
        record.setRecordId(UID.getUUID());
        record.setUserId(user.getOpenId());
        record.setPartyId(partyId);
        record.setStatus(0);
        partyService.createRecord(record);

        //扣钱
        partyService.pay(user, party);

        return new BaseResponse("ok");
    }

    @RequestMapping(value = "/party/participate", method = RequestMethod.GET)
    public BaseResponse partyBasicInfo(@RequestParam(value = "party_id") String partyId) {
        Party party = partyService.getInfo(partyId);

        //聚会不存在
        if (party == null) {
            return new BaseResponse("no such party");
        }

        //聚会已关闭加入（或已结束）
        if (party.getMode() != 2) {
            return new BaseResponse("entry closed");
        }

        return new BaseResponse("ok", new PartyBasicInfoResponse(party));
    }

    @RequestMapping(value = "/party/detail", method = RequestMethod.GET)
    public BaseResponse partyDetail(@RequestParam(value = "party_id") String partyId) {
        User user = this.getRequestedUser();
        PartyRecord record = partyService.getRecord(partyId, user.getOpenId());

        //无权查询或聚会不存在
        if (record == null) {
            return new BaseResponse("invalid party_id");
        }

        Party party = partyService.getInfo(partyId);

        if (party == null) {
            return new BaseResponse("invalid party_id");
        }

        PartyDetailResponse response = new PartyDetailResponse();
        response.setName(party.getPartySubject());
        response.setTime(party.getPartyDate().getTime());
        response.setLatitude(party.getLatitude());
        response.setLongtitude(party.getLongtitude());
        response.setFee(FixedPointNumber.toString(party.getDeposit()));
        response.setTotal(FixedPointNumber.toString(party.getDeposit() * party.getTotalSum()));

        String originator = party.getOriginator();
        User leader = userService.getUser(originator);
        response.setLeader(leader.getUserName());

        List<PartyRecord> list = partyService.getRecords(partyId);
        List<PartyMemberResponse> memberList = new ArrayList<>();

        for (PartyRecord r : list) {
            PartyMemberResponse member = new PartyMemberResponse(user.getUserName(), user.getHeader());
            memberList.add(member);
        }
        response.setMembers(memberList);

        return new BaseResponse("ok", response);
    }
}
