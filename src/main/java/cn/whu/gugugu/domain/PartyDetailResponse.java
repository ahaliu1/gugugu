package cn.whu.gugugu.domain;

import java.util.List;

public class PartyDetailResponse implements DataImpl{
    private String name;
    private long time;
    private float latitude;
    private float longtitude;
    private String fee;
    private String total;
    private String leader;

    private List<PartyMemberResponse> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public List<PartyMemberResponse> getMembers() {
        return members;
    }

    public void setMembers(List<PartyMemberResponse> members) {
        this.members = members;
    }
}
