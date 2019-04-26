package cn.whu.gugugu.controller.party;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.commons.MessageResponse;
import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.mapper.PartyRecordMapper;
import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.PartyRecord;
import cn.whu.gugugu.generated.model.PartyRecordExample;
import cn.whu.gugugu.generated.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

class ArriveResponse extends MessageResponse {

}

/*
签到
返回值
正常情况
{
    "message": "ok"
}
不在范围内
{
    "message": "wrong region"
}
未开启签到
{
    "message": "sign closed"
}
聚会id不存在或未加入
{
    "message": "no such party"
}
 */
@RestController
public class ArriveController extends AuthenticatedController {

    @Autowired
    private PartyMapper mapper;

    @Autowired
    private PartyRecordMapper mapper1;

    private static final  double EARTH_RADIUS = 6378137;  // 赤道半径
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }
    public static double GetDistance(float lon1, float lat1, float lon2, float lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        return s;  //单位米
    }

    @RequestMapping(value = "/party/arrive", method = RequestMethod.POST)
    public ArriveResponse arrive(String longtitude, String latitude, String party_id){
        User user = getRequestedUser();
        ArriveResponse resp = new ArriveResponse();
        Party party = mapper.selectByPrimaryKey(party_id);
        if (party == null){
            resp.setMessage("no such party");
        }
        if (party.getMode() == 3 || party.getMode() == 2){
            resp.setMessage("sign closed");
            return resp;
        }
        Float lo = party.getLongtitude();
        Float la = party.getLatitude();
        float _lo = Float.parseFloat(longtitude);
        float _la = Float.parseFloat(latitude);
        if (GetDistance(lo, la, _lo, _la) > 100){
            resp.setMessage("wrong region");
            return resp;
        }
        // 在范围内，更新记录
        PartyRecordExample example = new PartyRecordExample();
        example.createCriteria().andUserIdEqualTo(user.getOpenId()).andPartyIdEqualTo(party_id);
        List<PartyRecord> records = mapper1.selectByExample(example);
        if (records.size() == 0){  // 不属于这个party
            resp.setMessage("no such party");
            return resp;
        }
        PartyRecord record = records.get(0);  // 一定有且仅有一个记录
        record.setStatus(1);  // 1 = 已签到未结算
        mapper1.updateByPrimaryKey(record);
        resp.setMessage("ok");
        return resp;
    }
}
