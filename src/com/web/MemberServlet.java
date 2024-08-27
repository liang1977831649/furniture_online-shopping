package com.web;

import com.entity.Member;
import com.service.CartServer;
import com.service.impl.CartServerImpl;
import com.service.impl.MemberServiceImpl;
import com.utils.CodeCheckUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/MemberServlet")
public class MemberServlet extends BasicServlet {
    private MemberServiceImpl memberService = new MemberServiceImpl();
    private CartServer cartServer = new CartServerImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Member member = new Member(null, username, password, null);
        request.setAttribute("username", username);

        //验证码的校验
        //获取验证码信息
        String code = request.getParameter("inputLogin");//得到用户输入的验证码
        String kaptchaSessionKey = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");//验证码文本
        Object kaptchaSessionDate = request.getSession().getAttribute("KAPTCHA_SESSION_DATE");//时间
        //移除验证码和时间
        request.removeAttribute("KAPTCHA_SESSION_KEY");
        request.removeAttribute("KAPTCHA_SESSION_DATE");
        //验证的结果
        int result = CodeCheckUtils.checkCode(kaptchaSessionKey, code, kaptchaSessionDate);
        if (result == 0) {
            request.setAttribute("errorMsg", "验证码错误");
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            return;
        } else if (result == 1) {
            request.setAttribute("errorMsg", "验证码已过期");
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            return;
        }

        if (username != null && password != null) {
            member = memberService.LoginMember(member);
            if (member != null) {
                HttpSession session = request.getSession();
                //存入到session
                session.setAttribute("member", member);
                //加载它的购物车商品类数量，因为CartServlet会用到重定向，所以为了保存数据，需要存放到Session中
                session.setAttribute("furnCount", cartServer.getCartItemCount(member.getId()));
                //既然session保存了，数据没有存放在request中，那这里就可以直接用重定向了，
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            } else {
                request.setAttribute("errorMsg", "账号或密码错误！");
                request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMsg", "账号或密码错误！");
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        //获取一些基本的信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        //获取验证码信息
        String code = request.getParameter("inputRegister");//得到用户输入的验证码
        String kaptchaSessionKey = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");//验证码文本
        Object kaptchaSessionDate = request.getSession().getAttribute("KAPTCHA_SESSION_DATE");//时间
        //移除验证码和时间
        request.removeAttribute("KAPTCHA_SESSION_KEY");
        request.removeAttribute("KAPTCHA_SESSION_DATE");
        //验证的结果
        int result = CodeCheckUtils.checkCode(kaptchaSessionKey, code, kaptchaSessionDate);
        if (result == 0 || result == 1) {
            if (result == 0) {
                request.setAttribute("errorMsg", "验证码错误");
            } else {
                request.setAttribute("errorMsg", "验证码已过期");
            }
            request.setAttribute("active", "register");
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            return;
        }
        //判断账号和密码是否为空
        if (username != null && password != null) {
            System.out.println("用户名" + username + "不存在，可以使用");
            //注册操作
            if (memberService.registerMember(new Member(null, username, password, email)) == 1) {
                request.setAttribute("errorMsg", "注册成功，请登录");
                request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            }
            //注册失败
            else {
                request.setAttribute("errorMsg", "服务器异常");
                request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMsg", "请输入账号密码！");
            request.setAttribute("active", "register");
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        }
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    //验证用户名是否存在的方法
    public void validUsername(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        System.out.println("username="+username);
        String json="";
        if( memberService.isExistsMember(username)){
                //如果存在
            json="{\"msg\":\"true\"}";
        }else{
            json="{\"msg\":\"false\"}";
        }
        response.getWriter().write(json);
    }
}