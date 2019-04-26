package cn.whu.gugugu.commons;

import cn.whu.gugugu.generated.model.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthenticatedController {
    protected User getRequestedUser(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return ((RequestUserWrapper)(servletRequestAttributes.getRequest())).getUser();
    }
}
