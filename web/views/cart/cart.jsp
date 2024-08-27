<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>家居网购</title>
    <base href="<%=request.getContextPath()+ "/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
</head>

<script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(function () {
        var CartPlusMinus = $(".cart-plus-minus");
        CartPlusMinus.prepend('<div class="dec qtybutton">-</div>');
        CartPlusMinus.append('<div class="inc qtybutton">+</div>');
        $(".qtybutton").on("click", function () {
            var $button = $(this);
            var oldValue = $button.parent().find("input").val();
            if ($button.text() === "+") {
                //拿到对应的具体数量
                var newVal = parseFloat(oldValue) + 1;
            } else {
                // Don't allow decrementing below zero
                if (oldValue > 1) {
                    var newVal = parseFloat(oldValue) - 1;
                } else {
                    newVal = 1;
                }
            }
            $button.parent().find("input").val(newVal);
            //拿到商品id
            var furnId = $button.parent().find("input").attr("furnId");
            //拿到用户id
            var memberId =${member.id};
            //-----------------------下面是动态更新-------------------
            //拿到购物车商品的总数量
            var $countCartItem = $(".countCartItem");
            //拿到所有商品的总价
            var $total = $(".total");
            //拿到这个商品的总额
            var $cartItemTotal = $button.parent().parent().next();

            $.get(
                "CartServlet",
                {
                    "action": "updateCartItemQuantity",
                    "memberId": memberId,
                    "furnId": furnId,
                    "count": newVal
                },
                function (data) {
                    if (data.msg == "success") {
                        //改变
                        $total.text(data.allTotal);
                        $countCartItem.text(data.allCount);
                        $cartItemTotal.text(data.total);
                        console.log("修改成功");
                    } else {
                        console.log("修改失败");
                    }
                },
                "json"
            )
        });
    })
    //删除点击事件
    $(function () {
        $(".deleteCartItem").click(function () {
            var furnId = $(this).parent().prev().prev().find("input").attr("furnId");//拿到对应的商品的ID编号
            var memberId =${member.id};//拿到会员的Id
            var $tr = $(this).parent().parent();

            var b = window.confirm("确认删除该商品?");
            if (b == true) {
                $.get(
                    "CartServlet",
                    {
                        "action": "deleteCartItem",
                        "furnId": furnId,
                        "memberId": memberId
                    },
                    function (data) {
                        $tr.remove()//从列表中移除\
                        $(".countCartItem").text(data.allCount);
                        $(".total").text(data.allTotal);
                        console.log("删除成功", data.msg);
                    },
                    "JSON"
                )
            }

        })
    })

    $(function () {
        var $clear = $(".cart-clear").children("a");
        $clear.click(function () {
            //清空购物车
            $("tbody").empty();
            var memberId =${member.id};
            var b = window.confirm("确定要清空购物车吗？");
            if (b) {
                $.get(
                    "CartServlet",
                    {
                        "action": "clearCart",
                        "memberId": memberId
                    },
                    function (data) {
                        console.log("data.allCount=" + data.allCount);
                        console.log("data.allTotal=" + data.allTotal);
                        $(".countCartItem").text(data.allCount);
                        $(".total").text(data.allTotal);
                        $("tbody").empty();
                        console.log("成功", data.msg);
                    },
                    "JSON"
                )
            }else{
                return false;
            }
        })
    })
    $(function (){
        //购物车结账
        $(".cart-shiping-update").click(function (){
            var b = window.confirm("确定是否结账！");
            if(b){
                window.location.href="OrderServlet?action=addOrderAndOrderItem&memberId="+${member.id}
            }else{
               return false;
            }
        })
    })

</script>
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
                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header-bottom-set dropdown">
                            <a>欢迎: ${member.username}</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="OrderServlet?action=orderManager&memberId=${member.id}">订单管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="MemberServlet?action=exit">安全退出</a>
                        </div>
                    </div>
                </div>
                <!-- Header Action End -->
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
                        <a href="index.jsp"><img width="280px" src="assets/images/logo/logo.png"
                                                  alt="Site Logo"/></a>
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

<!-- OffCanvas Cart Start -->

<!-- OffCanvas Cart End -->

<!-- OffCanvas Menu Start -->

<!-- OffCanvas Menu End -->


<!-- breadcrumb-area start -->


<!-- breadcrumb-area end -->

<!-- Cart Area Start -->
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">Your cart items</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">

                    <div class="table-content table-responsive cart-table-content">
                        <span class="errorMsg" id="spanMsg"
                              style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">${requestScope.errorMsg}</span>
                        <%request.removeAttribute("msg");%>
                        <table>
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>家居名</th>
                                <th>单价</th>
                                <th>数量</th>
                                <th>金额</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${not empty requestScope.cartItems}">
                            <c:forEach items="${requestScope.cartItems}" var="cartItem">
                                <tr>
                                    <td class="product-thumbnail">
                                        <a href="#"><img class="img-responsive ml-3" src="${cartItem.img}"
                                                         alt=""/></a>
                                    </td>
                                    <td class="product-name"><a href="#">${cartItem.name}</a></td>
                                    <td class="product-price-cart"><span class="amount">${cartItem.price}</span></td>
                                    <td class="product-quantity">
                                        <div class="cart-plus-minus">
                                                <%--<div class="dec qtybutton ">-</div>--%>
                                            <input furnId="${cartItem.id}" class="cart-plus-minus-box" type="text"
                                                   name="qtybutton"
                                                   value="${cartItem.count}"/>
                                                <%--<div class="inc qtybutton ">+</div>--%>
                                        </div>
                                    </td>
                                    <td class="product-subtotal">${cartItem.total}</td>
                                    <td class="product-remove">
                                        <a class="deleteCartItem" href="javaScript:void(0)"><i
                                                class="icon-close"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="cart-shiping-update-wrapper">
                                <h4>共
                                    <span class="countCartItem">${countCartItem}</span>
                                    件商品 总价
                                    <span class="total">${total}</span>
                                    元</h4>
                                <div class="cart-shiping-update">
                                    <a href="javascript:void(0)">购 物 车 结 账</a>
                                </div>
                                <div class="cart-clear">
                                    <button>继 续 购 物</button>
                                    <a>清 空 购 物 车</a>
                                </div>
                            </div>
                        </div>
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
                        <p class="copy-text">Copyright &copy; 2021~</p>
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