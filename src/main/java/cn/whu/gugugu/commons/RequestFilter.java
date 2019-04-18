package cn.whu.gugugu.commons;

import cn.whu.gugugu.generated.mapper.UserMapper;
import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.generated.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.List;

@Order(2)
@WebFilter(filterName = "authFilter", urlPatterns = "/*")
public class RequestFilter implements Filter {

    @Autowired
    UserMapper mapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(request.getHeader("token"));
        List<User> userList = mapper.selectByExample(example);
        RequestUserWrapper requestUserWrapper = new RequestUserWrapper(request);
        if (userList.size() != 0) {
            User user = userList.get(0);
            Calendar calendar = Calendar.getInstance();
            long time = calendar.getTimeInMillis();
            if (time > user.getLoginTime() + 86400000) {
                writeError(servletResponse.getOutputStream(), "token expired");
                return;
            } else {
                requestUserWrapper.setUser(userList.get(0));
            }
        } else {
            writeError(servletResponse.getOutputStream(), "invalid token");
            return;
        }
        filterChain.doFilter(requestUserWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private void writeError(OutputStream outputStream, String message) {
        PrintWriter writer = null;
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(outputStream , StandardCharsets.UTF_8);
            writer = new PrintWriter(osw, true);
            String jsonStr = "{\"message\" : \"" + message + "\"}";
            writer.write(jsonStr);
            writer.flush();
            writer.close();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
            if(null != osw){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
