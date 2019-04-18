package cn.whu.gugugu.generated.model;

import java.util.Date;

public class Party {
    private String partyId;

    private String partySubject;

    private String partyDetail;

    private Date partyDate;

    private Integer deposit;

    private Float latitude;

    private Float longtitude;

    private String originator;

    private Integer totalSum;

    private Date participateTime;

    private Integer mode;

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId == null ? null : partyId.trim();
    }

    public String getPartySubject() {
        return partySubject;
    }

    public void setPartySubject(String partySubject) {
        this.partySubject = partySubject == null ? null : partySubject.trim();
    }

    public String getPartyDetail() {
        return partyDetail;
    }

    public void setPartyDetail(String partyDetail) {
        this.partyDetail = partyDetail == null ? null : partyDetail.trim();
    }

    public Date getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(Date partyDate) {
        this.partyDate = partyDate;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Float longtitude) {
        this.longtitude = longtitude;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator == null ? null : originator.trim();
    }

    public Integer getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Integer totalSum) {
        this.totalSum = totalSum;
    }

    public Date getParticipateTime() {
        return participateTime;
    }

    public void setParticipateTime(Date participateTime) {
        this.participateTime = participateTime;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }
}