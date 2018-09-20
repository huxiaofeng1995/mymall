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
    function change_cart(checked, sku_id) {
        var shfxz = "0";
        if(checked){
            shfxz = "1";
        }
        $.post("change_cart.do",{sku_id:sku_id,shfxz:shfxz},function(data){
            $("#cartListInner").html(data);
        });
    }
    function del_cart(sku_id,mon,index) {
        $.post("del_cart.do",{sku_id:sku_id},function () {
            $("#cartListInner").html(data);
        })
    }
    function goto_check() {
        $("#goto_check").submit();
    }
</script>
<title>硅谷商城</title>
</head>
<body>
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
            <tr id="cart_${status.index}">
                <td><input type="checkbox" ${cart.shfxz=="1"?"checked":""} onclick="change_cart(this.checked,${cart.sku_id})"><img src="upload/image/${cart.shp_tp}" alt="" width="100px"></td>
                <td>${cart.sku_mch}</td>
                <td>
                    <c:if test="${not empty obj_attr[status.index]}">
                        颜色：<span style='color:#ccc'>${obj_attr[status.index].color.shp_ys}</span><br>
                        尺码：<span style='color:#ccc'>${obj_attr[status.index].version.shp_bb}</span>
                    </c:if>
                </td>
                <td>${cart.hj}</td>
                <td><input type="text" name="min" value="${cart.tjshl}" style="width:50px;text-align:center"></td>
                <td><a href="javascript:del_cart('${cart.sku_id}','${cart.hj}','${status.index}');">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="Cprice">
    <form id="goto_check" action="goto_checkOrder.do">
        <div class="price">总价：<span id="totalprice">${sum}</span></div>
        <div class="jiesuan" onclick="goto_check()">结算</div>
    </form>
</div>
</body>
</html>
