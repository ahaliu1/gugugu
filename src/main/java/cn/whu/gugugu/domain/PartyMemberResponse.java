package cn.whu.gugugu.domain;

public class PartyMemberResponse {
    private String username;
    private String header;

    public PartyMemberResponse(String username, String header) {
        this.username = username;
        this.header = header;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
