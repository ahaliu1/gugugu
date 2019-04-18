package cn.whu.gugugu.domain;

public class PartyIdResponse implements DataImpl {
    private String paty_id;

    public PartyIdResponse(String paty_id) {
        this.paty_id = paty_id;
    }

    public String getPaty_id() {
        return paty_id;
    }

    public void setPaty_id(String paty_id) {
        this.paty_id = paty_id;
    }
}
