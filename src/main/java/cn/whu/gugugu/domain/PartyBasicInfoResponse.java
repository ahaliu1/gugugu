package cn.whu.gugugu.domain;

import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.utils.FixedPointNumber;

public class PartyBasicInfoResponse implements DataImpl {
    public String name;
    public String detail;
    public float latitude;
    public float longtitude;
    public String fee;

    public PartyBasicInfoResponse(Party party) {
        name = party.getPartySubject();
        detail = party.getPartyDetail();
        latitude = party.getLatitude();
        longtitude = party.getLongtitude();
        fee = FixedPointNumber.toString(party.getDeposit());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
