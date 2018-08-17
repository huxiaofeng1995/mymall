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
		/*
			需求一：下拉列表显示一级分类的所有条目，当选择了一级分类后，自动加载二级分类和品牌
		 */
        //与getJSON方法区别，这里要知道返回数据的类型，我指定成了json,两个方式结果一样。
		$.get("js/json/class_1.js",function (data) {
			$(data).each(function (i,item) {
				$("#sku_class_1_select").append("<option value='"+item.id+"'>"+item.flmch1+"</option>");
            })
        },"json");
    });

	function get_class_2(class_1_id) {
        $.get("js/json/class_2_"+class_1_id+".js",function (data) {
            //每次添加前要先清空原来的数据，否则二级分类会数据会一直叠加
            $("#sku_class_2_select").empty();
            $("#sku_class_2_select").append("<option>请选择</option>");
            $(data).each(function (i,item) {

                $("#sku_class_2_select").append("<option value='"+item.id+"'>"+item.flmch2+"</option>");
            });
        },"json");
    }
	function goto_sku_add() {
	    var flbh1 = $("#sku_class_1_select").val();
        var flbh2 = $("#sku_class_2_select").val();
        window.location.href="goto_sku_add.do?flbh1="+flbh1+"&flbh2="+flbh2;
    }
</script>
<title>硅谷商城</title>
</head>
<body>
	spu商品信息管理
	<hr>
	一级：<select id="sku_class_1_select" name="flbh1" onchange="get_class_2(this.value);"><option>请选择</option></select>
	二级：<select  id="sku_class_2_select" name="flbh2" ><option>请选择</option></select>
	查询<br>
	<a href="javascript:goto_sku_add();">添加</a><br>
	删除<br>
	编辑<br>
</body>
</html>