package cn.whu.gugugu.commons;

import cn.whu.gugugu.generated.model.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthenticatedController {
    protected User getRequestedUser(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        RequestUserWrapper requestUserWrapper = (RequestUserWrapper) servletRequestAttributes.getRequest();
        return requestUserWrapper.getUser();
    }
}
