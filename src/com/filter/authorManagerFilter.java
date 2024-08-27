package com.filter;

import com.entity.Manager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class authorManagerFilter implements Filter {
    String[] strArr;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String authorURL = filterConfig.getInitParameter("passURl");
        strArr = authorURL.split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取URL
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if (!containPassURL(requestURI)){
            //如果不包含该路径，就检查session是否保存过用户信息
            Manager manager =(Manager) request.getSession().getAttribute("manager");
            if(manager==null){
                request.setAttribute("errorMsg","请先登录");
                request.getRequestDispatcher("/views/manager/manage_login.jsp").forward(servletRequest,servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

    public boolean containPassURL(String requestURI) {
        for (String s : strArr) {
            if(requestURI.endsWith(s)){
                return true;
            }
        }
        return false;
    }
}
