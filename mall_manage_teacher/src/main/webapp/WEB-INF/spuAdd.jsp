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
<!-- <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>  阻止jquery覆盖的问题-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		<div id = "dimg">
		<input id="file_0" type="file" name="files" style="display: none"/>
		<img id="img_0" style="cursor: pointer;margin-left: 10px" src="image/upload_hover.png" width="100px" height='100px' onclick="cimg(0)"/></div><!-- 这里的div结束标签不能换行，换行会导致append新元素时有空格间距-->
		颜色：<div id="colorDiv" style="border: 1px dotted grey;">
				<input type="text" name="list_color[0].shp_ys"/>
				<button type="button" onclick="addColor()">添加颜色</button>
			</div>
		版本：<div id="versionDiv" style="border: 1px dotted grey;">
		<input type="text" name="list_version[0].shp_bb"/>
		<button type="button" onclick="addVersion()">添加版本</button>
		</div>
		<input type="submit" value="提交"/>
	</form>
	<script type="text/javascript">
		function addColor() {
            var count = $("#colorDiv input").length;
            $("#colorDiv").append("<input type='text' name='list_color["+count+"].shp_ys'/>");
        }
        function addVersion() {
            var count = $("#versionDiv input").length;
            $("#colorDiv").append("<input type='text' name='list_version["+count+"].shp_bb'/>");
        }
        function cimg(index){
            $("#file_"+index).click();//触发文件input的点击事件
            if(window.URL.createObjectURL) {
                $("#file_" + index).change(function () {
                    //获得图片对象
                    var blob_img = $("#file_" + index)[0].files[0];//Jquery对象转dom对象 $x.[0]
                    var url = window.URL.createObjectURL(blob_img);
                    //替换img
                    $("#img_" + index).attr("src", url);
                    var length = $(":file").length;
                    if((index+1) == length && index < 4){//说明用户点击的是最后一张,并限制图片个数
                        add_image(index);
                    }
                });
            }else {
                $("#file_" + index).change(function () {
                    //获得图片对象
                    var blob_img = $("#file_" + index)[0].files[0];
                    var fr = new FileReader();
                    fr.onload = function () {
                        $("#img_" + index).attr("src", this.result);
                    }
                    fr.readAsDataURL(blob_img);
                    var length = $(":file").length;
                    if((index+1) == length){//说明用户点击的是最后一张
                        add_image(index);
                    }
                });
            }
        }

        function add_image(index) {
            index++;
            var a="<input id='file_" + index +"' type='file' name='files' style='display: none'/>"
            var b="<img id='img_" + index +"' style='cursor: pointer;margin-left: 10px' src='image/upload_hover.png' width='100px' height='100px' onclick='cimg("+index+")'/>"
            $("#dimg").append(a+b);
        }
	</script>

</body>
</html>