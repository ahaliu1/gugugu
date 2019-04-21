package cn.whu.gugugu.domain;

public class UserDetailResponse implements DataImpl {
    private String username;
    private String header;
    private String score;

    public UserDetailResponse(String username, String header, String score) {
        this.username = username;
        this.header = header;
        this.score = score;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
