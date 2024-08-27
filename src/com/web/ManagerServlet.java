package com.web;

import com.entity.Manager;
import com.service.ManagerService;
import com.service.impl.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManagerServlet")
public class ManagerServlet extends BasicServlet {
    private ManagerService managerService=new ManagerServiceImpl();
    public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println("username="+name);
//        System.out.println("password="+password);
        Manager manager;
        if(name!=null&&password!=null&&(manager=managerService.loginManager(new Manager(null,name,password)))!=null){
            request.getSession().setAttribute("manager",manager);
            request.getRequestDispatcher("/views/manager/manage_menu.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","账号或密码错误");
            request.getRequestDispatcher("/views/manager/manage_login.jsp").forward(request,response);
        }
    }
}