<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

