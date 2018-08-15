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
	function b(){}
</script>
<title>硅谷商城</title>
</head>
<body>
	spu信息添加${spu.flbh1}|${spu.flbh2}|${spu.pp_id}
	<hr>
	<!--注意要添加 enctype,才能提交文件-->
	<form action="spu_add.do" enctype="multipart/form-data" method="post">
		<input name="flbh1" type="hidden" value="${spu.flbh1}"/>
		<input name="flbh2" type="hidden" value="${spu.flbh2}"/>
		<input name="pp_id" type="hidden" value="${spu.pp_id}"/>
		商品名称：<input name="shp_mch" type="text" /><br>
		商品描述：<textarea name="shp_msh" rows="10" cols="50"></textarea><br>
		商品图片：<br>
		<input type="file" name="files"/><br>
		<input type="file" name="files"/><br>
		<input type="file" name="files"/><br>

		<input type="submit" value="提交"/>
	</form>
</body>
</html>