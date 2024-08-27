package com.filter;

import com.utils.JDBCUtilsDruid;

import javax.servlet.*;
import java.io.IOException;

public class transitionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取JDBC的connection

        try {
            //处理事务开始
            JDBCUtilsDruid.getConnect();
            filterChain.doFilter(servletRequest,servletResponse);
            //事务处理完了之后，进行提交
            JDBCUtilsDruid.commit();
        } catch (Exception e) {
            //如果发生异常，那么久回滚
                JDBCUtilsDruid.rollback();
                throw  new RuntimeException();
        }
    }

    @Override
    public void destroy() {

    }
}
