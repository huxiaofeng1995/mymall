<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    $(function () {
        $(".card").hover(function () {
            $(".cart_pro").show();
            $.get("miniCart.do",function (data) {
                $("#cart_list").html(data);
            })
        },function () {
            $(".cart_pro").hide();
        })
    })
</script>
<title>硅谷商城</title>
</head>
<body>
<div class="card">
    <a href="goto_cart_list.do" target="_blank" id="cart">购物车<div class="num">0</div></a>

    <!--购物车商品-->
    <div id="cart_list" class="cart_pro" style="display: none;">
        <jsp:include page="miniCartList.jsp"/>
    </div>

</div>
</body>
</html>
