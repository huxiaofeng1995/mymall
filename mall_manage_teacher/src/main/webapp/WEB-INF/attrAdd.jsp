<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    $(function(){
        //添加属性值按钮事件
        $("form").on("click","table tr td .valBtn",function(){

            //获取table的tindex和属性值的vindex
            var $table = $(this).parent().parent().parent().parent();
            var tclass = $table.attr("class");

            var tindex = tclass.split("_")[1];
            var trCount = $("."+tclass+" tr").length;//获取这个table里tr的数量
            var vindex = trCount - 1;
            $table.append("<tr><td>属性值：<input type='text' name='list_attr["+tindex+"].list_value["+vindex+"].shxzh'/></td><td>单位：<input type='text' name='list_attr["+tindex+"].list_value["+vindex+"].shxzh_mch'/></td><td><button class='valdleBtn' type='button'>删除</button></td></tr>");
        })

        //删除属性值按钮绑定事件
        $("form").on("click","table tr td .valdleBtn",function(){
            //不管点击那个删除按钮，总是删除最后一个tr
            var $table = $(this).parent().parent().parent().parent();
            var tclass = $table.attr("class");
            var $tr = $("."+tclass+" tr:last-child");
            $tr.remove();
        });

    });

    function addAttr(){
        var count = $("form table").length;
        $("#submitBtn").before(
            "<table class='attrTable_"+count +"' border='1' width='600px'>"+
            "<tr><td>属性名：<input type='text' name='list_attr["+count+"].shxm_mch'/></td><td></td><td><button class='valBtn' type='button'>添加值</button></td></tr>"+
            "<tr><td>属性值：<input type='text' name='list_attr["+count+"].list_value[0].shxzh'/></td><td>单位：<input type='text' name='list_attr["+count+"].list_value[0].shxzh_mch'/></td><td><button class='valdleBtn' type='button'>删除</button></td></tr></table><br>");
    }

</script>
<title>硅谷商城</title>
</head>
<body>
添加商品属性
<hr>
<form action="attr_add.do" method="post">
    <input type="hidden" name="flbh2" value="${flbh2}"/>
    <button type="button" onclick="addAttr()">添加属性</button>
    <table class="attrTable_0" border="1" width="600px">
        <tr><td>属性名：<input type="text" name="list_attr[0].shxm_mch"/></td><td></td><td><button class="valBtn" type="button">添加值</button></td></tr>
        <tr><td>属性值：<input type="text" name="list_attr[0].list_value[0].shxzh"/></td><td>单位：<input type="text" name="list_attr[0].list_value[0].shxzh_mch"/></td><td><button class="valdleBtn" type="button">删除</button></td></tr>
    </table>
    <br>
    <input id="submitBtn" type="submit" value="提交"/>
</form>
</body>
</html>
