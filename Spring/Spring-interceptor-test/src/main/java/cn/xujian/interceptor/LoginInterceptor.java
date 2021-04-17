package cn.xujian.interceptor;

import cn.xujian.domain.Manager;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        判断用户是否登陆   （判断session中有没有manager）
        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
//            没有登陆
        if (manager==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
        return true;
    }
}
