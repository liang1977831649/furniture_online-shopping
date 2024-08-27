<%@ page import="com.entity.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title-家居网购</title>
    <!-- 移动端适配 -->
    <base href="<%=request.getContextPath()%>/">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">
</head>
<%
    Page pages = (Page) request.getAttribute("pages");
    int currentPage = 0;
    int pageNum = 0;
    int pageBegin=0;
    int pageEnd=0;
    if (pages != null) {
        currentPage = pages.getPageCurrent();
        pageNum = pages.getPageNum();
        pageBegin = currentPage % 4 == 0 ? ((currentPage - 1) / 4) * 4 + 1 : (currentPage / 4) * 4 + 1;
        pageEnd = pageNum - pageBegin >= 4 ? pageBegin + 3 : pageNum;
    }

%>
<script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    function deleteFurn(val) {
        var b = window.confirm("确定要删除【" + val + "】吗？");
        if (b) {
            return true;
        }
        return false;
    }

    $(function () {
        $(".delete").click(function () {
            var val = $(this).parent().parent().find("td:eq(1)").text();
            return deleteFurn(val);
        })
    })
</script>
<script>
    $(function () {
        //设置当前页颜色格式
        <%--var currentPage =--%>
        <%--<%=currentPage -1%>--%>
        <%--var $PageLi = $("a[class='pageLi']:eq(currentPage)");--%>
        <%--$PageLi.addClass("active");--%>

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
                                <form  class="action-form" action="CustomerServlet">
                                    <input type="text" hidden="hidden" name="action" value="searchFurnsByName">
                                    <input type="text" hidden="hidden" name="type" value="management">
                                    <input name="furnName" class="form-control" value="${furnName}" placeholder="请输入商品名称" type="text">
                                    <button class="submit" type="submit"><i class="icon-magnifier"></i></button>
                                </form>
                            </div>
                        </div>
                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <a href="javascript:void(0)">后台管理</a>|<a href="views/manager/furn_add.jsp?">添加家具</a>
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
<!-- Cart Area Start -->
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">家居后台管理</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <span style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">${requestScope.msg}</span>
                        <%request.removeAttribute("msg");%>
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
                            <c:forEach items="${requestScope.pages.list}" var="item">
                                <tr>
                                    <td class="product-thumbnail">
                                        <a href="#"><img class="img-responsive ml-3" src="${item.imgPath}" alt=""/></a>
                                    </td>
                                    <td class="product-name"><a href="#">${item.name}</a></td>
                                    <td class="product-name"><a href="#">${item.business}</a></td>
                                    <td class="product-price-cart"><span class="amount">${item.price}</span></td>
                                    <td class="product-quantity">${item.sales}</td>
                                    <td class="product-quantity">${item.stock}</td>
                                    <td class="product-remove">
                                        <a href="FurnServlet?action=queryFurnToUpdateJSP&id=${item.id}&pageNum=${requestScope.pages.pageCurrent}"
                                           class="editor"><i class="icon-pencil"></i></a>
                                        <a href="FurnServlet?action=deleteFurn&id=${item.id}&pageNum=${requestScope.pages.pageCurrent}"
                                           class="delete"><i
                                                class="icon-close"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
        <%--分页的div--%>
        <div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
            <ul>
                <li><a href="FurnServlet?action=list">首页</a></li>
                <li><a class="back" href="FurnServlet?action=list&pageNum=${pages.pageCurrent - 1}">上一页</a></li>

                <c:forEach var="i" begin="<%=pageBegin%>" end="<%=pageEnd%>" step="1">
                    <c:if test="${i==requestScope.pages.pageCurrent}">
                        <li><a class="pageLi active" href="FurnServlet?action=list&pageNum=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${i!=requestScope.pages.pageCurrent}">
                        <li><a class="pageLi" href="FurnServlet?action=list&pageNum=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
                <li><a class="next" href="FurnServlet?action=list&pageNum=${pages.pageCurrent + 1}">下一页</a></li>
                <li><a href="FurnServlet?action=list&pageNum=${pages.pageNum}">末页</a></li>
                <li><a>共${pages.pageNum}页</a></li>
                <li><a>共${pages.furnNum}记录</a></li>
            </ul>
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
                        <p class="copy-text">Copyright &copy; 2021 </p>
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