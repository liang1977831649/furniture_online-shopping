<%@ page import="com.entity.Page" %>
<%@ page import="com.entity.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>-家居网购~</title>
    <!-- 移动端适配 -->
    <base href="<%=request.getContextPath() +"/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">
</head>
<%
    Page pages = ((Page) request.getAttribute("pages"));
    int currentPage =1;
    int pageNum =1;
    int pageBegin =1;
    int pageEnd =1;
    if(pages!=null){
    currentPage = pages.getPageCurrent();
    pageNum = pages.getPageNum();
    pageBegin = currentPage % 4 == 0 ? ((currentPage - 1) / 4) * 4 + 1 : (currentPage / 4) * 4 + 1;
    pageEnd = pageNum - pageBegin >= 4 ? pageBegin + 3 : pageNum;}
    //拿到是否有用户名
//    String userName = CookieUtils.getUserName(request.getCookies());
    Member member =
            (Member) request.getSession().getAttribute("member");
    //flag用于判断是否登录
    int flag = 0;
    if (member != null) {
        flag = 1;
    }
%>
<script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
<script>
    $(function () {
        currentPage =<%=currentPage%>;//当前页数
        var pageNum =<%=pageNum%>;//总页数
        $(".back").click(function () {
            if (currentPage - 1 >= 1) {
                return true;
            }
            return false;
        })
        $(".next").click(function () {
            if (currentPage + 1 <= pageNum) {
                return true;
            }
            return false;
        })
    })
    //加入购物车的方法
    $(function () {
        var furnCount =${sessionScope.furnCount};//拿到购物车数量；
        $("button[class='add-to-cart']").click(function () {
            //拿到基本的信息
            var furn = $(this).parent().parent().parent().children(0).val();//拿到商品Id
            // var $sale = $(this).parent().next().children().children(".sale");//拿到对应的销量
            // var $stock = $(this).parent().next().children().children(".stock");//拿到对应的库存量
            var $spanNum = $("#CartCount");//拿到购物车小数字控件
            var flag =
            <%=flag%>//登陆的状态
            var member = ${member.id};//购买者的id;
            //拿到sale和stock对应的值分别是多少
            // var sale = $sale.text();
            // var stock = $stock.text();
            if (flag == 0) {
                alert("您没有登陆，请登录后再操作");
                return false;
            }
            // if(stock==0){
            //     alert("该商品库存量不足，无法购买！");
            //     return false;
            // }
            //使用ajax，异步请求，【貌似这里的session不是动态更新的，所以furnCount不能从session中拿到到最新的】
            $.get(
                "CartServlet",
                {
                    action: "addCartItem",
                    memberId: member,
                    furnId: furn
                },
                function (data) {
                    //更新下面的信息
                    // $sale.text((sale-1)+"");
                    // $stock.text((stock-1)+"");
                    var furnCount = data.furnCount;
                    $spanNum.text(furnCount);
                    alert("添加购物车成功");
                },
                "JSON"
            )
            //window.location.href="CartServlet?action=addCartItem&memberId="+memberId+"&furnId="+furnId;
        })
        //设置购物车的点击事件
        $(".icon-handbag").click(function () {
            window.location.href = "CartServlet?action=cartItemManagement";
        })
    })


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
                        <div class="header_account_list">
                            <a href="javascript:void(0)" class="header-action-btn search-btn"><i
                                    class="icon-magnifier"></i></a>
                            <div class="dropdown_search">
                                <form class="action-form" action="CustomerServlet" method="post">
                                    <input type="text" hidden="hidden" name="action" value="searchFurnsByName">
                                    <input name="furnName" class="form-control" value="${furnName}" placeholder="请输入商品名称" type="text">
                                    <button class="submit" type="submit"><i class="icon-magnifier"></i></button>
                                </form>
                            </div>
                        </div>
                        <!-- Single Wedge Start -->
                        <%--如果没有登录过，那就显示要登陆--%>
                        <c:if test="<%=member==null%>">
                            <div class="header-bottom-set dropdown">
                                <a href="views/member/login.jsp">登录|注册</a>
                            </div>
                        </c:if>
                        <%--如果登录过了，就显示姓名--%>
                        <c:if test="${not empty sessionScope.member}">
                            <div class="header-bottom-set dropdown">
                                <span>欢迎，${sessionScope.member.username}</span>
                            </div>
                            <div class="header-bottom-set dropdown">
                                <a href="OrderServlet?action=orderManager&memberId=${member.id}">订单管理</a>
                            </div>
                            <div class="header-bottom-set dropdown">
                                <a href="MemberServlet?action=exit">安全退出</a>
                            </div>
                            <!-- Single Wedge End -->
                            <a href="#offcanvas-cart"
                               class="header-action-btn header-action-btn-cart offcanvas-toggle pr-0">
                                <i class="icon-handbag">购物车</i>
                                <span id="CartCount" class="header-action-num"
                                      style="color: white">${sessionScope.furnCount}</span>
                            </a>
                        </c:if>
                        <a href="#offcanvas-mobile-menu"
                           class="header-action-btn header-action-btn-menu offcanvas-toggle d-lg-none">
                            <i class="icon-menu"></i>
                        </a>
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
<br/>
<!-- Header Area End  -->

<!-- OffCanvas Cart Start 弹出cart -->
<!-- OffCanvas Cart End -->

<!-- OffCanvas Menu Start 处理手机端 -->
<!-- OffCanvas Menu End -->

<!-- Product tab Area Start -->
<div class="section product-tab-area">
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="tab-content">
                    <!-- 1st tab start -->
                    <div class="tab-pane fade show active" id="tab-product-new-arrivals">
                        <div class="row">
                            <c:forEach items="${requestScope.pages.list}" var="item">
                                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6 mb-6" data-aos="fade-up"
                                     data-aos-delay="200">
                                    <!-- Single Prodect -->
                                    <input name="furnId" value="${item.id}" hidden="hidden">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="shop-left-sidebar.html" class="image">
                                                <img src="${item.imgPath}" alt="Product"/>
                                                <img class="hover-image" src="${item.imgPath}"
                                                     alt="Product"/>
                                            </a>
                                            <span class="badges">
                                                <span class="new">New</span>
                                            </span>
                                            <div class="actions">
                                                <a href="#" class="action wishlist" data-link-action="quickview"
                                                   title="Quick view" data-bs-toggle="modal"
                                                   data-bs-target="#exampleModal"><i
                                                        class="icon-size-fullscreen"></i></a>
                                            </div>
                                            <button title="Add To Cart" class="add-to-cart">Add
                                                To Cart
                                            </button>
                                        </div>
                                        <div class="content">
                                            <h5 class="title">
                                                <a href="shop-left-sidebar.html">${item.name}</a></h5>
                                            <span class="price">
                                                <span class="new">家居:　${item.name}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">厂商:　${item.business}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">价格:　￥${item.price}</span>
                                            </span>
                                            <span class="price">销量:　
                                                <span class="new sale">${item.sales}</span>
                                            </span>
                                            <span class="price ">库存:　
                                                <span class="new stock">${item.stock}</span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- 1st tab end -->
                </div>
            </div>
        </div>
    </div>
</div>
<!--  Pagination Area Start -->
<%--分页的div--%>
<div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
    <ul>
        <li><a href="${requestScope.pages.url}&furnName=${furnName}">首页</a></li>
        <li><a class="back"
               href="${requestScope.pages.url}&furnName=${furnName}&pageNum=${requestScope.pages.pageCurrent - 1}">上一页</a>
        </li>

        <%--        <c:set var="begin" value="${pages.pageCurrent%4==0?(((pages.pageCurrent-1)/4)*4):((pages.pageCurrent/4)*4)}"/>--%>
        <%--        <c:set var="end" value="${pages.pageNum-begin>=4?begin+3:pages.pageNum}"/>--%>
<%--        <c:forEach var="i" begin="1" end="${requestScope.pages.pageNum}" step="1">--%>
        <c:forEach var="i" begin="<%=pageBegin%>" end="<%=pageEnd%>" step="1">
            <c:if test="${i==requestScope.pages.pageCurrent}">
                <li><a class="pageLi active" href="${requestScope.pages.url}&furnName=${furnName}&pageNum=${i}">${i}</a>
                </li>
            </c:if>
            <c:if test="${i!=requestScope.pages.pageCurrent}">
                <li><a class="pageLi" href="${requestScope.pages.url}&furnName=${furnName}&pageNum=${i}">${i}</a></li>
            </c:if>
        </c:forEach>
        <li><a class="next"
               href="${requestScope.pages.url}&furnName=${furnName}&pageNum=${requestScope.pages.pageCurrent + 1}">下一页</a>
        </li>
        <li><a href="${requestScope.pages.url}&furnName=${furnName}&pageNum=${requestScope.pages.pageNum}">末页</a></li>
        <li><a>共${requestScope.pages.pageNum}页</a></li>
        <li><a>共${requestScope.pages.furnNum}记录</a></li>
    </ul>
</div>
<!--  Pagination Area End -->
<!-- Product tab Area End -->

<!-- Banner Section Start -->
<div class="section pb-100px pt-100px">
    <div class="container">
        <!-- Banners Start -->
        <div class="row">
            <!-- Banner Start -->
            <div class="col-lg-6 col-12 mb-md-30px mb-lm-30px" data-aos="fade-up" data-aos-delay="200">
                <a href="shop-left-sidebar.html" class="banner">
                    <img src="assets/images/banner/1.jpg" alt=""/>
                </a>
            </div>
            <!-- Banner End -->

            <!-- Banner Start -->
            <div class="col-lg-6 col-12" data-aos="fade-up" data-aos-delay="400">
                <a href="shop-left-sidebar.html" class="banner">
                    <img src="assets/images/banner/2.jpg" alt=""/>
                </a>
            </div>
            <!-- Banner End -->
        </div>
        <!-- Banners End -->
    </div>
</div>
<!-- Banner Section End -->
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

<!-- Modal 放大查看家居 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">x</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-5 col-sm-12 col-xs-12 mb-lm-30px mb-sm-30px">
                        <!-- Swiper -->
                        <div class="swiper-container gallery-top mb-4">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/1.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/2.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/3.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/4.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/5.jpg" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="swiper-container gallery-thumbs slider-nav-style-1 small-nav">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/1.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/2.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/3.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/4.jpg" alt="">
                                </div>
                                <div class="swiper-slide">
                                    <img class="img-responsive m-auto" src="assets/images/product-image/5.jpg" alt="">
                                </div>
                            </div>
                            <!-- Add Arrows -->
                            <div class="swiper-buttons">
                                <div class="swiper-button-next"></div>
                                <div class="swiper-button-prev"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-12 col-xs-12">
                        <div class="product-details-content quickview-content">
                            <h2>Originals Kaval Windbr</h2>
                            <p class="reference">Reference:<span> demo_17</span></p>
                            <div class="pro-details-rating-wrap">
                                <div class="rating-product">
                                    <i class="ion-android-star"></i>
                                    <i class="ion-android-star"></i>
                                    <i class="ion-android-star"></i>
                                    <i class="ion-android-star"></i>
                                    <i class="ion-android-star"></i>
                                </div>
                                <span class="read-review"><a class="reviews" href="#">Read reviews (1)</a></span>
                            </div>
                            <div class="pricing-meta">
                                <ul>
                                    <li class="old-price not-cut">$18.90</li>
                                </ul>
                            </div>
                            <p class="quickview-para">Lorem ipsum dolor sit amet, consectetur adipisic elit eiusm tempor
                                incidid ut labore et dolore magna aliqua. Ut enim ad minim venialo quis nostrud
                                exercitation ullamco</p>
                            <div class="pro-details-size-color">
                                <div class="pro-details-color-wrap">
                                    <span>Color</span>
                                    <div class="pro-details-color-content">
                                        <ul>
                                            <li class="blue"></li>
                                            <li class="maroon active"></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="pro-details-quality">
                                <div class="cart-plus-minus">
                                    <input class="cart-plus-minus-box" type="text" name="qtybutton" value="1"/>
                                </div>
                                <div class="pro-details-cart btn-hover">
                                    <button class="add-cart btn btn-primary btn-hover-primary ml-4"> Add To
                                        Cart
                                    </button>
                                </div>
                            </div>
                            <div class="pro-details-wish-com">
                                <div class="pro-details-wishlist">
                                    <a href="wishlist.html"><i class="ion-android-favorite-outline"></i>Add to
                                        wishlist</a>
                                </div>
                                <div class="pro-details-compare">
                                    <a href="compare.html"><i class="ion-ios-shuffle-strong"></i>Add to compare</a>
                                </div>
                            </div>
                            <div class="pro-details-social-info">
                                <span>Share</span>
                                <div class="social-info">
                                    <ul>
                                        <li>
                                            <a href="#"><i class="ion-social-facebook"></i></a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="ion-social-twitter"></i></a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="ion-social-google"></i></a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="ion-social-instagram"></i></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal end -->

<!-- Use the minified version files listed below for better performance and remove the files listed above -->
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>