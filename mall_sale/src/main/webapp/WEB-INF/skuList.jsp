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
            $("#sku_count").text("共" + ${count} + "件商品");
        })
    </script>
    <title>硅谷商城</title>
</head>
<body>
<c:forEach items="${list_sku}" var="item">
    <div class="list">
        <div class="img"><a href="goto_sku_detail.do?sku_id=${item.id}&spu_id=${item.spu.id}" target="_blank"><img src="upload/image/${item.spu.shp_tp}" width="220px" height="220px"></a></div>
        <div class="price">${item.jg}</div>
        <div class="title"><a href="goto_sku_detail.do?sku_id=${item.id}&spu_id=${item.spu.id}" target="_blank">${item.sku_mch}</a></div>
    </div>
</c:forEach>
</body>
</html>
