package cn.whu.gugugu.controller;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.domain.BaseResponse;
import cn.whu.gugugu.domain.PartyIdResponse;
import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.service.PartyService;
import cn.whu.gugugu.service.impl.PartyImpl;
import cn.whu.gugugu.utils.FixedPointNumber;
import cn.whu.gugugu.utils.UID;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SponsorController extends AuthenticatedController {
    PartyImpl partyService = new PartyService();

    /**
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
                                    @RequestParam(value = "detail") String detail,
                                    @RequestParam(value = "fee") String fee,
                                    @RequestParam(value = "time") int time,
                                    @RequestParam(value = "latitude") double latitude,
                                    @RequestParam(value = "longtitude") double longtitude,
                                    @RequestParam(value = "party_id") String partyId) {
        //todo 各种判定，然后返回错误信息


        Party party = new Party();
        party.setPartySubject(name);
        party.setPartyDetail(detail);
        party.setDeposit(new FixedPointNumber(fee).getStorageValue());
        party.setParticipateTime(new Date(time));
        party.setLatitude((float) latitude);
        party.setLongtitude((float) longtitude);
        party.setOriginator(this.getRequestedUser().getOpenId());
        party.setPartyId(partyId);

        if (partyId == null || partyId.isEmpty()) {
            //新建
            party.setPartyId(UID.getUUID());
            partyService.createParty(party);
        } else {
            party.setDeposit(null);
            partyService.updateParty(party);
        }

        return new BaseResponse("ok", new PartyIdResponse(party.getPartyId()));
    }
}
