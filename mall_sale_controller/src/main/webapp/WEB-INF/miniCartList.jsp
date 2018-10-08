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
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    $(function () {
        var count = '${shp_count}';
        if( count != null &&  count != "")
        $(".card .num").text(count);
    })
    function goto_cart_list() {
        location.href = "/goto_cart_list.do"
    }
    function del_minicart(sku_id) {
        $.post("del_minicart.do",{sku_id:sku_id},function (data) {
            $("#cart_list").html(data);
        })
    }
</script>
<title>硅谷商城</title>
</head>
<body>
<div>
<h6 style="margin-left: 20px">最新加入的商品</h6>
</div>
<c:forEach items="${list_cart}" var="cart">
    <div class="one">
    <img src="upload/image/${cart.shp_tp}" width="60px"/>
    <span class="one_name" style="font-size: 12px">
        ${cart.sku_mch}
    </span>
    <span class="one_prece" style="font-size: 12px">
        <div>￥${cart.sku_jg} X ${cart.tjshl}</div>
        <a href="javascript:del_minicart(${cart.sku_id});" style="padding: 0px;margin-left: 35px;">删除</a>
    </span>
    </div>
</c:forEach>
<div class="gobottom">
    共<span class="count">${shp_count}</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
    共计￥<span class="sum">${sum}</span>
    <button class="goprice" onclick="goto_cart_list()" style="cursor: pointer;">去购物车</button>
</div>
</body>
</html>
