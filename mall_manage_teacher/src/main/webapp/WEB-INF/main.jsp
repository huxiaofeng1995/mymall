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

	<link rel="stylesheet" type="text/css" href="js/easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>硅谷商城</title>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:180px;padding:10px;">
		<div class="easyui-accordion" style="width:160px;">
			<div title="内容管理" data-options="iconCls:'icon-search'" style="width:110px;">
				<ul class="easyui-tree">
					<li>
						<span>商品管理</span>
						<ul>
							<li><a href="javascript:add_tab('goto_spu.do','商品信息管理')">商品信息管理</a></li>
							<li><a href="javascript:add_tab('goto_attr.do','商品属性管理')">商品属性管理</a></li>
							<li><a href="javascript:add_tab('goto_sku.do','商品库存单元管理')">商品库存单元管理</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<div title="检索管理" data-options="iconCls:'icon-search'" style="width:160px;">
				<ul class="easyui-tree">
					<li>
						<span>缓存管理</span>
						<ul>
							<li>商品缓存管理</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<div id="tab" class="easyui-tabs"></div>
	</div>
	<script type="text/javascript">
        function add_tab(url,title) {
            var existFlag = $("#tab").tabs("exists",title);//判断是否存在，不存在就添加，存在就选中
            if(!existFlag){
                $("#tab").tabs("add",{
                    title:title,
                    href:url,
                    closable:true,
                    tools:[{
                        iconCls:'icon-mini-refresh',
                        handler:function(){
                            alert('refresh');
                        }
                    }]
                });
            }else{
                $('#tab').tabs('select',title);
            }
		}
	</script>
</body>

</html>