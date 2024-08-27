<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>-家居网购</title>
    <!-- 移动端适配 -->
    <base href="<%=request.getContextPath()+ "/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">
</head>
<style>
    #msgTip {
        border: none;
        color: black;
    }
</style>
<script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#subFormAdd").click(function () {
            var stockVal = $("input[name='stock']").val();
            var priceVal = $("input[name='price']").val();
            var salesVal = $("input[name='sales']").val();
            var nameVal = $("input[name='name']").val();
            var businessVal = $("input[name='business']").val();
            var regx = /^[0-9]+$/;
            if (nameVal == "" || businessVal == "") {
                $("#msgTip").text("输入内容不能为空，请正确输入");
                return false;
            }
            if (!regx.test(stockVal)) {
                $("#msgTip").text("库存量格式不正确，请正确输入");
                return false;
            }
            if (!regx.test(salesVal)) {
                $("#msgTip").text("销量格式不正确，请正确输入");
                return false;
            }
            regx = /^[0-9]+(\\.[0-9]+)?$/;
            if (!regx.test(priceVal)) {
                $("#msgTip").text("价格格式不正确，请正确输入");
                return false;
            }
            return true;
        })

    })

    function prev(event) {
        //获取展示图片的区域
        var img;
        //获取文件对象
        var file = event.files[0];
        //获取文件阅读器： Js的一个类，直接使用即可
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
            //给img的src设置图片url
            img.setAttribute("src", this.result);
        }
    }
</script>
<body>
<!-- Header Area start  -->
<div class="header section">
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

                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">

                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <a href="#">家居管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="#">订单管理</a>
                        </div>
                    </div>
                </div>
                <!-- Header Action End -->
            </div>
        </div>
    </div>
    <!-- Header Bottom  End -->
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

<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">家居后台管理-添加家居</h3>
        <span id="msgTip">${requestScope.msg}</span>
        <%request.removeAttribute("msg");%>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="FurnAddAndUpdate" method="post" enctype="multipart/form-data">
                    <input hidden="hidden" type="text" name="action" value="addFurn">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>家居名</th>
                                <th>商家</th>
                                <th>价格</th>
                                <th>销量</th>
                                <th>库存</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="product-thumbnail">
                                    <%--<a href="#">--%>
                                    <%--<img class="img-responsive ml-3"--%>
                                    <%--             src="assets/images/product-image/default.jpg"--%>
                                    <%--             alt=""/>--%>
                                    <input type="file" name="pic" id="" value="" onchange="prev(this)"><br>
                                    <%--</a>--%>
                                </td>
                                <td class="product-name"><input name="name" style="width: 60%" type="text"
                                                                value=""/></td>
                                <td class="product-name"><input name="business" style="width: 90%" type="text"
                                                                value="大象家居"/></td>
                                <td class="product-price-cart"><input name="price" style="width: 90%" type="text"
                                                                      value=""/></td>
                                <td class="product-quantity">
                                    <input name="sales" style="width: 90%" type="text" value="100"/>
                                </td>
                                <td class="product-quantity">
                                    <input name="stock" style="width: 90%" type="text" value="80"/>
                                </td>
                                <td>
                                    <%--<input type="button" id="subFormAdd"--%>
                                    <%--       style="width: 90%;background-color: silver;border: silver;border-radius: 20%;"--%>
                                    <%--       value="添加家居"/>--%>
                                    <button type="submit" id="subFormAdd"
                                            style="width: 90%;background-color: silver;border: silver;border-radius: 20%;">
                                        添加家居
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Cart Area End -->

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
                                        <li class="li"><a class="single-link" href="login.html">登录</a></li>
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