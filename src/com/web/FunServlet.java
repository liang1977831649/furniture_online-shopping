package com.web;

import com.entity.Furn;
import com.entity.Page;
import com.service.impl.FurnServiceImpl;
import com.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FurnServlet")
public class FunServlet extends BasicServlet {
    private FurnServiceImpl furnService = new FurnServiceImpl();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Furn> furns = furnService.queryFurns();
        String parameter =request.getParameter("pageNum");
        int pageNum=1;
        if(parameter!=null){
            pageNum=Integer.parseInt(parameter);
        }
        Page<Furn> page = furnService.currentPageAndFurns(pageNum, 4);
        request.setAttribute("pages",page);
        request.getRequestDispatcher("/views/manager/furn_manage.jsp").forward(request, response);
    }

    public void addFurn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String name = request.getParameter("name");
//        String business = request.getParameter("business");
//        System.out.println("name="+name);
//        System.out.println("business="+business);
//        BigDecimal price = null;
//        Integer stock = null;
//        Integer sales = null;
//        try {
//            if(name==null||business==null){
//                throw new RuntimeException();
//            }
//            price = new BigDecimal( request.getParameter("price"));
//            stock = Integer.parseInt(request.getParameter("stock"));
//            sales = Integer.parseInt( request.getParameter("sales"));
//        } catch (Exception e) {
//            request.setAttribute("msg","输入格式错误，请重新输入");
//            System.out.println("跳转");
//            request.getRequestDispatcher ("/views/manager/furn_add.jsp").forward(request,response);
//            return;
//        }
//        Furn furn = new Furn(null, name, business, price, sales, stock, "assets/images/product-image/default.jpg");
        Furn furn = DataUtils.copyParamToBean(request.getParameterMap(), new Furn());
//        System.out.println("furn=" + furn);
        furnService.addFurn(furn);
        //因为添加的Furn肯定会被安排在最后一条，所以要到最后一页去取，默认每页显示的是4
        int FurnCount = furnService.queryCountFurns();
        int lastPage=(int)(Math.ceil(FurnCount*1.0/4));
        response.sendRedirect("/furniture/FurnServlet?action=list&pageNum="+lastPage);
    }

    public void deleteFurn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int pageNum =Integer.parseInt(request.getParameter("pageNum"));//当前页数
        if (furnService.deleteFurnById(id) == 0) {
            request.setAttribute("msg", "家居删除失败");
        }
        //重新拿到page,重新获取页数
        Page<Furn> page = furnService.currentPageAndFurns(pageNum, 4);
        //如果得到的总页数<这条记录所在的页数，那就返回前一页（也就是最后一页）
        if(page.getPageNum()<pageNum){
            //此时的page的list是空的
            response.sendRedirect("/furniture/FurnServlet?action=list&pageNum="+(page.getPageNum()));
        }
        //否则就返回这一条记录所在的页数
        else{
            response.sendRedirect("/furniture/FurnServlet?action=list&pageNum="+pageNum);
        }
    }

    public void queryFurnToUpdateJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pageNum = request.getParameter("pageNum");
        int i;
        try {
            i = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            //如果转化失败，那就跳转到管理页面
            response.encodeRedirectURL("/FurnServlet?action=list");
            return;
        }
        Furn furn = furnService.queryFurnById(i);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("furn", furn);
        //跳转到修改页面
        request.getRequestDispatcher("/views/manager/furn_update.jsp").forward(request, response);
    }

    public void updateFurn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Furn furn = DataUtils.copyParamToBean(request.getParameterMap(), new Furn());
//        System.out.println(furn);
        furnService.UpdateFurn(furn);
        response.sendRedirect(request.getContextPath() + "/FurnServlet?action=list&pageNum="+request.getParameter("pageNum"));
    }
}