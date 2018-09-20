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
    <link rel="stylesheet" type="text/css" href="css/css.css"/>
    <link rel="stylesheet" type="text/css" href="css/finishCart.css"/>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
</script>
<title>硅谷商城</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="searchArea.jsp"></jsp:include>
<div class="menu">
    <div class="nav">
        <div class="navs">
            <div class="left_nav">
                全部商品分类
            </div>
            <ul>
                <li><a href="">服装城</a></li>
                <li><a href="">美妆馆</a></li>
                <li><a href="">超市</a></li>
                <li><a href="">全球购</a></li>
                <li><a href="">闪购</a></li>
                <li><a href="">团购</a></li>
                <li><a href="">拍卖</a></li>
                <li><a href="">金融</a></li>
                <li><a href="">智能</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="cartPro">
    <span class="dec">该商品已加入购物车</span>
    <div class="lec">
        <a href="goto_sku_detail.do?sku_id=${cart.sku_id}&spu_id=${cart.shp_id}"><img src="upload/image/${cart.shp_tp}" width="62px" height="62px"/></a>
    </div>
    <span class="lec_name">
				${cart.sku_mch}
			</span>
    <span class="lec_des">
				数量 ${cart.tjshl}
			</span>
    <div class="shop_des">
        <a href="goto_sku_detail.do?sku_id=${cart.sku_id}&spu_id=${cart.shp_id}"><img src="images/shop_des.png"/></a>
    </div>
    <div class="shop_cart">
        <a href="goto_cart_list.do"><img src="images/shop_cart.png"/></a>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
