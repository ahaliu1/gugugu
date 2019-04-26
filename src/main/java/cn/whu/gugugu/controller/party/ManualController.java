package cn.whu.gugugu.controller.party;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.commons.MessageResponse;
import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.mapper.PartyRecordMapper;
import cn.whu.gugugu.generated.mapper.UserMapper;
import cn.whu.gugugu.generated.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

class ManualData {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    private String username;

    private boolean signed = true;

}

class ManualResponse extends MessageResponse {

    private ManualData data;

    public ManualData getData() {
        return data;
    }

    public void setData(ManualData data) {
        this.data = data;
    }
}

/*
手动更改签到状态
本接口不需要传入更改到的状态，默认为在“已签到”和“未签到”两种状态之间转换
返回值
正常情况
{
    "message": "ok",
    "data": {
        "username": "已变更用户状态的用户名",
        "signed": true,  // 是否已签到
    }
}
已关闭签到通道
{
    "message": "entry closed"
}
无权限更改或聚会不存在
{
    "message": "no such party"
}
用户不存在
{
    "message": "no such user"
}
 */
@RestController
public class ManualController extends AuthenticatedController {

    @Autowired
    private PartyMapper mapper;

    @Autowired
    private PartyRecordMapper mapper1;

    @Autowired
    private UserMapper mapper2;

    @RequestMapping(value = "/party/manual", method = RequestMethod.POST)
    public ManualResponse manual(String party_id, String username){
        User user =  getRequestedUser();
        ManualResponse resp = new ManualResponse();
        Party party = mapper.selectByPrimaryKey(party_id);
        if (party == null || !party.getOriginator().equals(user.getOpenId())){
            resp.setMessage("no such party");
            return resp;
        }
        if (party.getMode() == 2 || party.getMode() == 3){
            resp.setMessage("entry closed");
            return resp;
        }
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username);
        List<User> user1 = mapper2.selectByExample(example);
        if (user1.size() == 0){
            resp.setMessage("no such user");
            return resp;
        }
        User user2 = user1.get(0);
        PartyRecordExample example1 = new PartyRecordExample();
        example1.createCriteria().andUserIdEqualTo(user2.getUserName()).andPartyIdEqualTo(party_id);
        List<PartyRecord> record = mapper1.selectByExample(example1);
        if (record.size() == 0){
            resp.setMessage("no such user");
            return resp;
        }
        PartyRecord record1 = record.get(0);
        record1.setStatus(1);  // 1 = 已签到未结算
        mapper1.updateByPrimaryKey(record1);
        resp.setMessage("ok");
        ManualData data = new ManualData();
        data.setUsername(username);
        data.setSigned(true);
        resp.setData(data);
        return resp;
    }
}
