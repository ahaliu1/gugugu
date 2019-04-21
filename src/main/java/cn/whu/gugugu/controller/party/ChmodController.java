package cn.whu.gugugu.controller.party;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.commons.BaseResponse;
import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

class Data {

    private String mode = "";

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

class ChmodResponse extends BaseResponse {

    private Data data = null;

    @Override
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
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
        party.setMode(Integer.parseInt(mode));
        mapper.updateByPrimaryKey(party);
        resp.setMessage("ok");
        Data data = new Data();
        data.setMode(mode);
        resp.setData(data);
        return resp;
    }
}
