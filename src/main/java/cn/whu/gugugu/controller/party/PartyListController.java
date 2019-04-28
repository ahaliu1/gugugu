package cn.whu.gugugu.controller.party;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.commons.MessageResponse;
import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.mapper.PartyRecordMapper;
import cn.whu.gugugu.generated.model.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


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

    public ArrayList<Party> getParties() {
        return parties;
    }

    public void setParties(ArrayList<Party> parties) {
        this.parties = parties;
    }

    private int total;

    private int next;

    private ArrayList<Party> parties = new ArrayList<>();

    public void addPartyRecord(Party data){
        this.parties.add(data);
    }
}

/*
{
    "message": String,
    "data": {
        "total": int,
        "next": int,
        "parties": [
            ...
        ]
    }
}
 */
class PartyListResponse extends MessageResponse {

    public PartyListData getData() {
        return data;
    }

    public void setData(PartyListData data) {
        this.data = data;
    }

    private PartyListData data = null;
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
@RestController
public class PartyListController extends AuthenticatedController {

    @Autowired
    private PartyRecordMapper mapper;

    @Autowired
    private PartyMapper mapper1;

    @RequestMapping(value = "/party/list", method = RequestMethod.GET)
    public PartyListResponse list(int start, int count){
        User user = getRequestedUser();
        PartyListResponse resp = new PartyListResponse();

        PartyRecordExample example = new PartyRecordExample();
        example.createCriteria().andUserIdEqualTo(user.getOpenId());

        int total = (int) mapper.countByExample(example);
        if (start > total){
            resp.setMessage("out of range");
            return resp;
        }
        PartyListData data = new PartyListData();
        data.setTotal(total);
        int next = 0;
        if (start + count > total){
            next = total;
        }else {
            next = start + next;
        }
        data.setNext(next);


        PageHelper.startPage(start, count);
        example.createCriteria().andUserIdEqualTo(user.getOpenId());
        List<PartyRecord> parties = mapper.selectByExample(example);

        for (PartyRecord record: parties) {
            Party party = mapper1.selectByPrimaryKey(record.getPartyId());
            data.addPartyRecord(party);
        }
        resp.setData(data);
        resp.setMessage("ok");
        return resp;
    }
}
