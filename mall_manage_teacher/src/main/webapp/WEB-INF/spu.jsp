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

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',split:true,border:false" style="height:50px">
			<form id="spfl" method="get" action="goto_spu_add.do" style="margin-top: 10px;margin-left: 10px;">
				一级：<select id="class_1_select" name="flbh1" onchange="get_class_2(this.value);"><option>请选择</option></select>
				二级：<select  id="class_2_select" name="flbh2" ><option>请选择</option></select>
				品牌：<select  id="tm_select" name="pp_id" ><option>请选择</option></select>
				<a href="javascript:goto_spu_add();">添加</a>
			</form>
		</div>
		<div data-options="region:'west',split:true,border:false" style="width:100px">
			查询<br>
			删除<br>
			编辑<br>
		</div>
		<div data-options="region:'center',border:false"></div>
	</div>

	<script type="text/javascript">
        $(function () {
            /*
                需求一：下拉列表显示一级分类的所有条目，当选择了一级分类后，自动加载二级分类和品牌
             */
            //与getJSON方法区别，这里要知道返回数据的类型，我指定成了json,两个方式结果一样。
            $.get("js/json/class_1.js",function (data) {
                $(data).each(function (i,item) {
                    $("#class_1_select").append("<option value='"+item.id+"'>"+item.flmch1+"</option>");
                })
            },"json");
        });

        function get_class_2(class_1_id) {
            $.get("js/json/class_2_"+class_1_id+".js",function (data) {
                //每次添加前要先清空原来的数据，否则二级分类会数据会一直叠加
                $("#class_2_select").empty();
                $("#class_2_select").append("<option>请选择</option>");
                $(data).each(function (i,item) {

                    $("#class_2_select").append("<option value='"+item.id+"'>"+item.flmch2+"</option>");
                });
            },"json");
            get_tm(class_1_id);
        }

        function get_tm(class_1_id) {
            $.get("js/json/tm_class_1_"+class_1_id+".js",function (data) {
                $("#tm_select").empty();
                $("#tm_select").append("<option>请选择</option>");
                $(data).each(function (i,item) {

                    $("#tm_select").append("<option value='"+item.id+"'>"+item.ppmch+"</option>");
                });
            },"json");
        }
        function goto_spu_add() {
            //使用这种提交form表单数据 post请求的方式，要注意jquery覆盖的问题
            add_tab_post("goto_spu_add.do","添加商品");
        }
	</script>

</body>
</html>