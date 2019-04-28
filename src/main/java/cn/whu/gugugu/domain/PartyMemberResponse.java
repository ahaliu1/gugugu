package cn.whu.gugugu.domain;

public class PartyMemberResponse {
    private String username;
    private String header;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;

    public PartyMemberResponse(String username, String header, Integer status) {
        this.username = username;
        this.header = header;
        this.status = status;
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
