package com.garrison.campusstore.interceptor.shopadmin;

import com.garrison.campusstore.entity.PersonInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ShopLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session中取出用户信息来
        Object userObj = request.getSession().getAttribute("user");
        if(userObj != null) {
            //若用户信息不为空则将session里的用户信息转换成personInfo实体类对象
            PersonInfo user = (PersonInfo) userObj;
            //确保userId不为空，且该账号的可用状态为1，并且用户类型为店家
            if (user != null && user.getUserId() != null && user.getUserId() > 0 && user.getEnableStatus() == 1 && user.getUserType() == 2) {
                //若通过验证则返回true，拦截器返回true之后，则交给下一步（或下一个interceptor或controller）
                return true;
            }
        }
            //若不满足登陆验证，则直接跳转到账户登录页面
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.open('"+request.getContextPath()+"/local/login','_self')");
            out.println("</script>");
            out.println("/html");
            return false;
    }
}

