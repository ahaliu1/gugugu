package cn.whu.gugugu.service.impl;

public interface LoginImpl {
    /**
     * 将code转换为session，需要通过微信接口
     * @param code
     * @return
     */
    public String getSession(String code);

    /**
     * 将session转换为token
     * @param session
     * @return
     */
    public String getToken(String session);
}
