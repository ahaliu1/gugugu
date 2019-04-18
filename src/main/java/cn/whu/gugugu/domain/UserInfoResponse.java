package cn.whu.gugugu.domain;

import cn.whu.gugugu.utils.FixedPointNumber;

public class UserInfoResponse implements DataImpl {
    private String username;
    private String header;
    private String score;

    public UserInfoResponse(String username, String header, int score) {
        this.username = username;
        this.header = header;
        this.score = new FixedPointNumber(score).toString();
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
