package com.filter;

import com.entity.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class authorMemberFilter implements Filter {
    String[] strArr;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取到放行的url
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
            Member member =(Member) request.getSession().getAttribute("member");
            if(member==null){
                request.setAttribute("errorMsg","请先登录");
                request.getRequestDispatcher("/views/member/login.jsp").forward(servletRequest,servletResponse);
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
