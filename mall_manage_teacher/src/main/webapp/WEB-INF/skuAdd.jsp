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
<title>硅谷商城</title>
</head>
<body>
    <form action="save_sku.do">
        <input type="hidden" value="${flbh1}" name="flbh1"/>
        <input type="hidden" value="${flbh2}" name="flbh2"/>
        品牌：<select id="sku_tm_select" name="pp_id" onchange="get_spu_list(this.value)"></select>
        商品：<select id="spu_list" name="id" onchange="get_spu_sale_attr(this.value)"></select>
        <hr>
        分类属性：<br>
        <c:forEach items="${list_attr}" var="attr" varStatus="status">
            <input type="checkbox" name="list_attr[${status.index}].shxm_id" onclick="show_val(${attr.id})" value="${attr.id}"/>${attr.shxm_mch}&nbsp;&nbsp;
        </c:forEach>
        <hr>
        <c:forEach items="${list_attr}" var="attr" varStatus="status">
            <div id="val_${attr.id}" style="display: none">
                <c:forEach items="${attr.list_value}" var="val">
                    <input type="radio" value="${val.id}" name="list_attr[${status.index}].shxzh_id"/>${val.shxzh}${val.shxzh_mch}&nbsp;
                </c:forEach>
            </div>
        </c:forEach>
        商品库存名称：<input type="text" name="sku_mch"/><br>
        商品库存数量：<input type="text" name="kc"/><br>
        商品库存价格：<input type="text" name="jg"/><br>
        商品库存地址：<input type="text" name="kcdz"/><br>
        <div id="saleAttr" style="display: none;">
            颜色：<div class="colorAttr"></div>
            版本：<div class="versionAttr"></div>
        </div>
        <input type="submit" value="提交"/>
    </form>
    <script type="text/javascript">
        $(function () {
            var flbh1 = "${flbh1}";//el表达式事先在服务端编译好了，js代码在浏览器客户端运行时已经取到值了
            $.get("js/json/tm_class_1_"+flbh1+".js",function (data) {
                $("#sku_tm_select").empty();
                $("#sku_tm_select").append("<option>请选择</option>");
                $(data).each(function (i,item) {
                    $("#sku_tm_select").append("<option value='"+item.id+"'>"+item.ppmch+"</option>");
                });
            },"json");
        });

        function get_spu_list(pp_id) {
            var flbh2 = "${flbh2}"
            $.post("get_spu_list.do",{pp_id:pp_id,flbh2:flbh2},function (data) {
                $("#spu_list").empty();
                $("#spu_list").append("<option>请选择</option>");
                $(data).each(function (i,item) {
                    $("#spu_list").append("<option value='"+item.id+"'>"+item.shp_mch+"</option>");
                })
            });
        }

        function get_spu_sale_attr(spu_id) {
            $.post("get_spu_sale_attr.do",{spu_id:spu_id},function (data) {
                $("#saleAttr").show();
                $(".colorAttr").empty();
                $(".versionAttr").empty();
                $(data.list_color).each(function (i,item) {
                    $(".colorAttr").append("<input type='radio' value='"+item.id+"' name='shp_ys'/>"+ item.shp_ys);
                })
                $(data.list_version).each(function (i,item) {
                    var val = '${item.shp_bb}';
                    $(".versionAttr").append("<input type='radio' value='"+item.id+"' name='shp_bb'/>"+item.shp_bb);
                })
            });
        }


        function show_val(attr_id) {
            $("#val_"+attr_id).toggle();
        }
    </script>
</body>
</html>
