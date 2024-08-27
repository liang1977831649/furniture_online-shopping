package com.web;

import com.entity.CartItem;
import com.entity.Furn;
import com.entity.Member;
import com.service.CartServer;
import com.service.FurnService;
import com.service.impl.CartServerImpl;
import com.service.impl.FurnServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/CartServlet")
public class CartServlet extends BasicServlet {
    private FurnService furnService=new FurnServiceImpl();
    private CartServer cartServer=new CartServerImpl();
    public void addCartItem(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        int memberId =Integer.parseInt( request.getParameter("memberId"));
        int furnId =Integer.parseInt( request.getParameter("furnId"));
//        System.out.println("memberID="+memberId);
//        System.out.println("furn="+furnId);
        int count=1;
        Furn furn = furnService.queryFurnById(furnId);
        //创建CartItem
        CartItem cartItem = new CartItem(furn.getId(), furn.getName(), count, furn.getPrice(), furn.getPrice(), memberId,furn.getImgPath());
        //添加操作
        cartServer.addCartItem(cartItem);
        //得到购物车商品类总数
//        int furnCount = cartServer.cartItemCount(memberId);
//        request.setAttribute("furnCount",furnCount);
        //重新刷新一下页面
//        response.sendRedirect(request.getContextPath()+"/index.jsp");
//        request.getRequestDispatcher("/index.jsp").forward(request,response);
//        Member member = new Member(10, "ok", "world", "123@qq.com");
//        String json = new Gson().toJson(member);
//        System.out.println(json);
        int furnCount = cartServer.getCartItemCount(memberId);
        request.getSession().setAttribute("furnCount",furnCount);
        String json="{\"msg\":\"ok\",\"furnCount\":\""+furnCount+"\"}";
        response.getWriter().write(json);
    }
    public void cartItemManagement(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int memberId = ((Member) request.getSession().getAttribute("member")).getId();
        List<CartItem> cartItems = cartServer.listCartItem(memberId);

        //加入到request
        request.setAttribute("total",cartServer.getTotalCart(memberId));//商品总价
        request.setAttribute("countCartItem",cartServer.getCartItemCountNumber(memberId));//商品总数量
        request.setAttribute("cartItems",cartItems);//商品列表
        request.getRequestDispatcher("/views/cart/cart.jsp").forward(request,response);
    }
    public void updateCartItemQuantity(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int memberId =Integer.parseInt( request.getParameter("memberId"));
        int furnId =Integer.parseInt( request.getParameter("furnId"));
        int count =Integer.parseInt( request.getParameter("count"));
        int i = cartServer.updateCartItemCount(memberId, furnId, count);
        String json;
        if(i==1){//更新成功
            //获取这个商品
            CartItem cartItem = cartServer.getCartItemSingleByMemberIdAndFurnId(memberId, furnId);
            int total = cartItem.getTotal().intValue();//获取这个商品的总额
            int allTotal = cartServer.getTotalCart(memberId).intValue();//获取购物车总价格
            int allCount = cartServer.getCartItemCountNumber(memberId);//获取购物车所有商品数量和
            json="{\"msg\":\"success\",\"total\":\""+total+"\",\"allTotal\":\""+allTotal+"\",\"allCount\":\""+allCount+"\"}";
        }else{
            json="{\"msg\":\"fail\"}";
        }
        response.getWriter().write(json);
    }

    public void deleteCartItem(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("deleteCartItem被调用");
        int furnId =Integer.parseInt( request.getParameter("furnId"));
        int memberId = Integer.parseInt( request.getParameter("memberId"));
//        System.out.println("furnId="+furnId);
//        System.out.println("memberId="+memberId);
        //删除业务，并更新总数量和总价格
        cartServer.deleteCartItemByMemberIdAndFurnId(memberId,furnId);
        //得到最新的总价和商品总数量
        BigDecimal allTotal = cartServer.getTotalCart(memberId);
        int allCount = cartServer.getCartItemCountNumber(memberId);

        String json="{\"msg\":\"ok\",\"allTotal\":\""+allTotal+"\",\"allCount\":\""+allCount+"\"}";
        response.getWriter().write(json);
    }
    public void clearCart(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int memberId=Integer.parseInt(request.getParameter("memberId"));
        System.out.println(memberId);
        cartServer.clearCart(memberId);
        int allTotal=0;
        int allCount=0;
        String json="{\"msg\":\"ok\",\"allTotal\":\""+allTotal+"\",\"allCount\":\""+allCount+"\"}";
        response.getWriter().write(json);
    }

}