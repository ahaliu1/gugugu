package cn.whu.gugugu.domain;

import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.utils.FixedPointNumber;

public class PartyBasicInfoResponse implements DataImpl {
    private String name;
    private String detail;
    private String place;
    private float latitude;
    private float longtitude;
    private String fee;

    public PartyBasicInfoResponse(Party party) {
        name = party.getPartySubject();
        detail = party.getPartyDetail();
        place = party.getPlace();
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
