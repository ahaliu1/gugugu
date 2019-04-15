package cn.whu.gugugu.commons;

import cn.whu.gugugu.generated.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestUserWrapper extends HttpServletRequestWrapper {
    private User user = null;

    public RequestUserWrapper(HttpServletRequest request) {
        super(request);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
