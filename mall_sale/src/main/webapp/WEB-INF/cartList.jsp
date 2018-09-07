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
    <link rel="stylesheet" type="text/css" href="css/css.css">
    <link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
</script>
<title>硅谷商城</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="top_img">
    <img src="./images/top_img.jpg" alt="">
</div>
<div class="search">
    <div class="logo"><img src="./images/logo.jpg" alt=""></div>
    <div class="search_on">
        <div class="se">
            <input type="text" name="search" class="lf">
            <input type="submit" class="clik" value="搜索" style="height: 32px;">
        </div>
        <div class="se">
            <a href="">取暖神奇</a>
            <a href="">1元秒杀</a>
            <a href="">吹风机</a>
            <a href="">玉兰油</a>
        </div>
    </div>
</div>

<div class="Cbox">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>商品图片</th>
            <th>商品名称</th>
            <th>商品属性</th>
            <th>商品价格</th>
            <th>商品数量</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list_cart}" var="cart" varStatus="status">
            <tr>
                <td><input type="checkbox" ${cart.shfxz=="1"?"checked":""}><img src="upload/image/${cart.shp_tp}" alt="" width="100px"></td>
                <td>${cart.sku_mch}</td>
                <td>
                    <c:if test="${not empty obj_attr[status.index]}">
                        颜色：<span style='color:#ccc'>${obj_attr[status.index].color.shp_ys}</span><br>
                        尺码：<span style='color:#ccc'>${obj_attr[status.index].version.shp_bb}</span>
                    </c:if>
                </td>
                <td>${cart.hj}</td>
                <td><input type="text" name="min" value="${cart.tjshl}" style="width:50px;text-align:center"></td>
                <td><a href="del_cart.do?cartId=${cart.id}" >删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="Cprice">
    <div class="price">总价：${sum}</div>
    <div class="jiesuan">结算</div>
</div>
<div class="footer">
    <div class="top"></div>
    <div class="bottom"><img src="images/foot.jpg" alt=""></div>
</div>

</body>
</html>
