package cn.whu.gugugu.domain;

public class PartyIdResponse implements DataImpl {
    private String party_id;

    public PartyIdResponse(String paty_id) {
        this.party_id = paty_id;
    }

    public String getPaty_id() {
        return party_id;
    }

    public void setPaty_id(String paty_id) {
        this.party_id = paty_id;
    }
}
