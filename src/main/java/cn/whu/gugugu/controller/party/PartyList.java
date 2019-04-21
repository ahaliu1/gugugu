package cn.whu.gugugu.controller.party;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.commons.BaseResponse;
import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.PartyExample;
import cn.whu.gugugu.generated.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

class PartyData{

    public String getParty_id() {
        return party_id;
    }

    public void setParty_id(String party_id) {
        this.party_id = party_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    private String party_id;

    private String name;

    private String mode;

}

class PartyListData{

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public ArrayList<PartyListData> getParties() {
        return parties;
    }

    public void setParties(ArrayList<PartyListData> parties) {
        this.parties = parties;
    }

    private int total;

    private int next;

    private ArrayList<PartyListData> parties = new ArrayList<>();
}


class PartyListResponse extends BaseResponse{

    @Override
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Data data = null;
}

/*
查看聚会记录

按时间排序

返回值

正常情况
{
    "message": "ok",
    "data": {
        "total": 12311,  // 总数，用于分页查看
        "next": 123,  // 下一页起始=start + count，若为-1则没有下一页
        "parties": [
            {
                "party_id": "16AB81P",
                "name": "聚会名称",
                "mode": 3,  //聚会状态
            },
            ...
        ]
    }
}

start 越界（count越界不会报错，只会将剩下的不足count个对象返回）
{
    "message": "out of range"
}
 */
public class PartyList extends AuthenticatedController {

    @Autowired
    private PartyMapper mapper;

    @RequestMapping(value = "/party/list", method = RequestMethod.POST)
    public PartyListResponse list(int start, int count){
        User user = getRequestedUser();
        PartyExample example = new PartyExample();
        example.createCriteria();
        ArrayList<Party> parties = mapper.selectByExample();

    }

}
