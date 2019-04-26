package cn.whu.gugugu.controller.party;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.commons.MessageResponse;
import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.mapper.PartyRecordMapper;
import cn.whu.gugugu.generated.mapper.TransactionMapper;
import cn.whu.gugugu.generated.mapper.UserMapper;
import cn.whu.gugugu.generated.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Data {

    private String mode = "";

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

class ChmodResponse extends MessageResponse {

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Data data = null;

}

/*
更改聚会状态
本接口只有盟主可以使用。
聚会状态转换
存在以下状态：
00：未结算，不允许加入，未开启签到
10：开启加入，未开启签到
01：不允许加入，开启签到
11：已结算，不允许加入，不允许签到
返回值
正常情况
{
    "message": "ok"
    "data": {
        "mode": 1  // 更改后的聚会状态
    }
}
已结算后，不允许更改状态
{
    "message": "already settled"
}
非盟主调用或聚会不存在
{
    "message": "permission denied"
}
状态不存在(如6)
{
    "message": "no such mode"
}
 */
@RestController
public class ChmodController extends AuthenticatedController {

    @Autowired
    private PartyMapper mapper;

    @Autowired
    private PartyRecordMapper mapper1;

    @Autowired
    private UserMapper mapper2;

    @Autowired
    private TransactionMapper mapper3;

    @Autowired
    private UserMapper mapper4;

    @RequestMapping(value = "/party/chmod", method = RequestMethod.POST)
    public ChmodResponse chmod(String part_id, String mode){
        User user = getRequestedUser();
        Party party = mapper.selectByPrimaryKey(part_id);
        ChmodResponse resp = new ChmodResponse();
        // 检查当前 User 是否为 party_id 对应的 Party 的盟主，或者 party 是否存在
        if (party == null || !party.getOriginator().equals(user.getOpenId())){
            resp.setMessage("permission denied");
            return resp;
        }
        // 检查状态转换是否合法
        if (!mode.equals("0") && !mode.equals("1") && !mode.equals("2") && !mode.equals("3")){
            resp.setMessage("no such mode");
            return resp;
        }
        if (party.getMode() == 3){
            resp.setMessage("already settled");
            return resp;
        }
        // 要转换的状态为3，付款
        if (mode.equals("3")){
            PartyRecordExample example = new PartyRecordExample();
            example.createCriteria().andPartyIdEqualTo(part_id);
            Long total_joined = mapper1.countByExample(example);  // 总参与人数，若为0则全部给盟主

            PartyRecordExample example1 = new PartyRecordExample();
            // PartyRecord.status 0 代表已加入未签到, 1 代表已签到未结算，2代表已签到已结算
            example1.createCriteria().andPartyIdEqualTo(part_id).andStatusEqualTo(1);
            long total_signed = mapper1.countByExample(example1);

            long total_account = party.getTotalSum()*total_joined;  // 总金额 = Party.sum * 参与记录条数

            if(total_signed == 0){
                // 更新金额
                user.setAccount((int)(user.getAccount()+total_account));
                mapper2.updateByPrimaryKey(user);
                // 创建账单，只对签到的人创建账单
                Transaction transaction = new Transaction();
                transaction.setMoney((int)total_account);
                transaction.setPartyId(part_id);
                transaction.setPaymentTime(new Date());  // 据说此时间为当前时间
                transaction.setUserId(user.getOpenId());
                mapper3.insert(transaction);
                // 更新 party
                party.setMode(3);
                mapper.updateByPrimaryKey(party);
                // 更新 Party records
                PartyRecordExample example2 = new PartyRecordExample();
                example2.createCriteria().andPartyIdEqualTo(part_id);
                List<PartyRecord> records = mapper1.selectByExample(example2);
                for (PartyRecord record: records) {
                    if (record.getStatus() == 1){
                        // 只对已签到未结算的状态（1）进行状态变更为已签到已结算（2），已加入未签到的（0）保持不变？？
                        record.setStatus(2);
                        mapper1.updateByPrimaryKey(record);
                    }
                }
                // 返回
                resp.setMessage("ok");
                return resp;
            }
            // 至少有一人签到的状况
            // 更新金额
            PartyRecordExample example2 = new PartyRecordExample();
            example2.createCriteria().andPartyIdEqualTo(part_id);
            List<PartyRecord> records =  mapper1.selectByExample(example2);
            for (PartyRecord record: records){
                // 计算每人分到的金额
                int every = (int)(total_account / total_signed);  // 每人分到的金额 = 总金额 / 签到人数
                if (record.getStatus() == 1){
                    // 对已签到未结算的记录（1）更新金额
                    User user1 = mapper4.selectByPrimaryKey(record.getUserId());
                    user1.setAccount(user.getAccount()+every);
                    mapper4.updateByPrimaryKey(user1);
                    // 创建账单
                    Transaction transaction = new Transaction();
                    transaction.setMoney(every);
                    transaction.setPartyId(part_id);
                    transaction.setPaymentTime(new Date());
                    transaction.setUserId(user1.getOpenId());
                    mapper3.insert(transaction);
                    // 更新 PartyRecord
                    record.setStatus(2);
                    mapper1.updateByPrimaryKey(record);
                }
            }
            // 更新 Party
            party.setMode(3);
            mapper.updateByPrimaryKey(party);
            // 返回
            resp.setMessage("ok");
            return resp;
        }
        // end
        party.setMode(Integer.parseInt(mode));
        mapper.updateByPrimaryKey(party);
        resp.setMessage("ok");
        Data data = new Data();
        data.setMode(mode);
        resp.setData(data);
        return resp;
    }
}
