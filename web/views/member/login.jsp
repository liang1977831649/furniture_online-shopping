<%--
  Created by IntelliJ IDEA.
  User: Administrator_user
  Date: 2024/3/13
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <base href="<%=request.getContextPath()%>/">
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var $codeImgLogin = $("#codeImgLogin");
            var $codeImgRegister = $("#codeImgRegister");
            //--------------------------------页面加载完毕后，开始加载验证码--------------------------------
            $codeImgRegister.prop("src","<%=request.getContextPath()%>/KaptchaServlet?d=" + new Date());
            $codeImgLogin.prop("src","<%=request.getContextPath()%>/KaptchaServlet?d=" + new Date());
            // -----------------------------验证是否需要跳到注册页面----------------------------
            if ("${requestScope.active}" == "register") {
                //如果是注册失败了，那就跳转到对应的注册页面，并内带了刷新验证码
                $("#registerDiv")[0].click();
            }
            <%--//因为注册的图片是最后一个访问KapcheServlet的，所以在登陆页面展示时候，需要刷新一下新的验证码--%>
            // -----------------------------点击图片引发的验证码切换-----------------------------------------
            //验证码刷新校验,写一个事件
            $codeImgRegister.click(function () {
                this.src = "<%=request.getContextPath()%>/KaptchaServlet?d=" + new Date();
            })
            $codeImgLogin.click(function () {
                this.src = "<%=request.getContextPath()%>/KaptchaServlet?d=" + new Date();
            })
            //----------------------------------点击Table引发的事件--------------------------------------
            //如果点击到了会员登陆或者注册会员这四个字，那就重新刷新一下验证码kapche，以获取最新的验证码，抛弃旧的验证码
            $("#loginDiv").click(function () {
                $("span[class=errorMsg]").text("");//设置提示span为空
                $codeImgLogin.prop("src", "<%=request.getContextPath()%>/KaptchaServlet?d=" + new Date())
            })
            $("#registerDiv").click(function () {
                $("span[class=errorMsg]").text("");
                $codeImgRegister.prop("src", "<%=request.getContextPath()%>/KaptchaServlet?d=" + new Date())
            })
            //---------------------------------验证信息是否已经填写---------------------------------------
            $("#sub-btn").click(function () {
                //验证账号是否通过
                var usernameVal = $("#username").val();
                var userNamePattern = /^\w{6,10}$/;
                if (!userNamePattern.test(usernameVal)) {
                    $("span.errorMsg").text("用户名格式错误");
                    return false;
                }
                //验证密码是否通过
                var passwordVal = $("#password").val();
                var passwordPattern = /^\w{6,10}$/;
                if (!passwordPattern.test(passwordVal)) {
                    $("span[class=errorMsg]").text("密码格式错误");
                    return false;
                }
                //验证前后密码是否一致
                var repwdVal = $("#repwd").val();
                if (repwdVal != passwordVal) {
                    $("span.errorMsg").text("前后密码不一致");
                    return false;
                }
                //验证邮箱是否正确
                var emailVal = $("#email").val();
                var emailPattern = /^[\w-]+@[a-zA-Z0-9\.]+[\w]+$/
                if (!emailPattern.test(emailVal)) {
                    $("span.errorMsg").text("邮箱格式错误");
                    return false;
                }
                //验证码是否为空
                if($("input[name='inputRegister']").val()==""){
                    $("span.errorMsg").text("请输入验证码");
                    return false;
                }
                if($("span.errorMsg").text()=="该账号已存在，无法注册"){
                    return false;
                }
                return true;
            })
            $("#btLogin").click(function () {
                //判断账号和密码是否为空
                var usernameVal = $("#usernameLg").val();
                if (usernameVal == "") {
                    $("#spanLg").text("请输入账号！");
                    return false
                }
                var passwordVal = $("#passwordLg").val();
                if (passwordVal == "") {
                    $("#spanLg").text("请输入密码！");
                    return false
                }
                //验证码是否为空
                if($("input[name='inputLogin']").val()==""){
                    return false;
                }
                return true;
            })
        })
        $(function (){
            //在注册时，使用ajax验证账号是否存在，在离开焦点时，会时触发ajax事件，来验证是否账号存在
            $("#username").blur(function (){
                var usernameTest = $(this).val();
                if(usernameTest!=""){
                    $.get(
                        "MemberServlet",
                        {
                            action:"validUsername",
                            username:usernameTest
                        },
                        function (data){
                            console.log("验证结果：",data.msg);
                            if(data.msg=="true"){
                                $(".errorMsg").text("该账号已存在，无法注册");
                            }else{
                                $(".errorMsg").text("");
                            }
                        },
                        "JSON"
                    )
                }
            })
        })
    </script>
</head>
<body>

<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top Message Start -->
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

            </div>
        </div>
    </div>
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Main Menu Start -->
    <div style="width: 100%;height: 50px;background-color: black"></div>
    <!-- Main Menu End -->
</div>
<!-- Header Area End  -->
<!-- login area start -->
<div class="login-register-area pt-70px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                <div class="login-register-wrapper">
                    <div class="login-register-tab-list nav">
                        <a id="loginDiv" class="active" data-bs-toggle="tab" href="#lg1">
                            <h4>会员登录</h4>
                        </a>
                        <a id="registerDiv" data-bs-toggle="tab" href="#lg2">
                            <h4>会员注册</h4>
                        </a>
                    </div>
                    <div class="tab-content">
                        <div id="lg1" class="tab-pane active">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <span class="errorMsg" id="spanLg"
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">${requestScope.errorMsg}</span>
                                    <%request.removeAttribute("msg");%>
                                    <form action="MemberServlet" method="post">
                                        <input type="hidden" name="action" value="login">
                                        <input type="text" name="username" id="usernameLg" placeholder="Username"
                                               value="${username}"/>
                                        <input type="password" name="password" id="passwordLg" placeholder="Password"/>
                                        <input type="text" name="inputLogin" style="width: 50%"
                                               placeholder="验证码"/>　　<img id="codeImgLogin" alt=""
                                                                            src="">
                                        <div class="button-box">
                                            <div class="login-toggle-btn">
                                                <input type="checkbox"/>
                                                <a class="flote-none" href="javascript:void(0)">Remember me</a>
                                                <a href="#">Forgot Password?</a>
                                            </div>
                                            <button type="submit" id="btLogin"><span>Login</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div id="lg2" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <span class="errorMsg"
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">${requestScope.errorMsg}</span>
                                    <%request.removeAttribute("msg");%>
                                    <form action="MemberServlet" method="post">
                                        <input type="hidden" name="action" value="register">
                                        <input type="text" id="username" name="username" placeholder="Username"/>
                                        <input type="password" id="password" name="password" placeholder="输入密码"/>
                                        <input type="password" id="repwd" name="repassword" placeholder="确认密码"/>
                                        <input name="email" id="email" placeholder="电子邮件" type="email"/>
                                        <input type="text" name="inputRegister" style="width: 50%"
                                               placeholder="验证码"/>　　<img id="codeImgRegister" alt=""
                                                                            src="">
                                        <div class="button-box">
                                            <button type="submit" id="sub-btn"><span>会员注册</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login area end -->

<!-- Footer Area Start -->
<div class="footer-area">
    <div class="footer-container">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px" data-aos="fade-up"
                         data-aos-delay="400">
                        <div class="single-wedge">
                            <h4 class="footer-herading">信息</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="about.html">关于我们</a></li>
                                        <li class="li"><a class="single-link" href="#">交货信息</a></li>
                                        <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="#">条款和条件</a></li>
                                        <li class="li"><a class="single-link" href="#">制造</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-lm-30px" data-aos="fade-up" data-aos-delay="600">
                        <div class="single-wedge">
                            <h4 class="footer-herading">我的账号</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="my-account.html">我的账号</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="cart.html">我的购物车</a></li>
                                        <li class="li"><a class="single-link" href="login.jsp">登录</a></li>
                                        <li class="li"><a class="single-link" href="wishlist.html">感兴趣的</a></li>
                                        <li class="li"><a class="single-link" href="checkout.html">结账</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="800">

                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="row flex-sm-row-reverse">
                    <div class="col-md-6 text-right">
                        <div class="payment-link">
                            <%--<img src="#" alt="">--%>
                        </div>
                    </div>
                    <div class="col-md-6 text-left">
                        <p class="copy-text">Copyright &copy; 2021 ~</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Area End -->
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>
