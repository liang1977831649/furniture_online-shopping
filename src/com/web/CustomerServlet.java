package com.web;

import com.dao.CartDao;
import com.dao.impl.CartDaoImpl;
import com.entity.Furn;
import com.entity.Member;
import com.entity.Page;
import com.service.FurnService;
import com.service.impl.FurnServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends BasicServlet {
    FurnService furnService = new FurnServiceImpl();
    CartDao cartDao = new CartDaoImpl();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//有可能会出现中文
        String parameter = request.getParameter("pageNum");
//        String furnName = request.getParameter("furnName");
        //如果存在furnname，那就说明，用户想到由搜索名确定的所有商品的某一页
//        if (furnName != null) {
//            searchFurnsByName(request, response);
//            return;
//        }
        int pageNo = 1;
        if (parameter != null) {
            pageNo = Integer.parseInt(parameter);
        }
        Page<Furn> page = furnService.currentPageAndFurns(pageNo, 4);
        System.out.println(page);
        request.setAttribute("pages", page);

        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);
    }

    public void searchFurnsByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String furnName = request.getParameter("furnName");
        String parameterPageNo = request.getParameter("pageNum");
        int pageNo = 1;
        if (parameterPageNo != null) {
            pageNo = Integer.parseInt(parameterPageNo);
        }
        //如果发现用户在搜索中什么都没输入就点击搜索，那么就回到主页面
        if (furnName == null) {
            furnName = "";
        }
        StringBuilder stringBuilder = new StringBuilder("CustomerServlet?action=searchFurnsByName");
        if (!"".equals(furnName)) {
            //存放在Attribute的目的是要在首页显示EL表达式
            request.setAttribute("furnName", furnName);
            stringBuilder.append("&furnName=").append(furnName);
        }

        Page<Furn> furnPage = furnService.pageSearchFurnByName(furnName, pageNo, 4);
        //添加路径
        furnPage.setUrl(stringBuilder.toString());
        request.setAttribute("pages", furnPage);
        //更新一下furnCount的值
        //拿到登陆后的用户
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            //拿到商品的种类数
            int totalCount = cartDao.getTotalFurnCount(member.getId());
            session.setAttribute("furnCount", totalCount);
        }
        //如果是furn_manager.jsp页面的话，通过识别type字段存不存在，从而确定要跳转到哪里
        if(request.getParameter("type")!=null){
            request.getRequestDispatcher("/views/manager/furn_manage.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);
    }

}
