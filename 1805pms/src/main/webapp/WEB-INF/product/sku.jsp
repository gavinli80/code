<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>


<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="../res/common/js/jquery.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="../res/common/js/lecheng.js" type="text/javascript"></script>
<script src="../res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="../res/css/style.css" />






<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>库存管理</title>
<script type="text/javascript">
//修改按钮
function updateSku(skuId){
	//让市场价亮起来
	$("#p" + skuId).attr("disabled",false);  //可用
	//售价
	$("#s" + skuId).attr("disabled",false);
	//库存
	$("#r" + skuId).attr("disabled",false);
	//安全库存
	$("#a" + skuId).attr("disabled",false);
	//限购
	$("#l" + skuId).attr("disabled",false);
	//运费
	$("#t" + skuId).attr("disabled",false);
	
}
//保存按钮
function saveSku(skuId){
	
	//让市场价暗  //不可用
	var p = $("#p" + skuId).attr("disabled",true).val();
	//售价
	var s = $("#s" + skuId).attr("disabled",true).val();
	//库存
	var r= $("#r" + skuId).attr("disabled",true).val();
	//安全库存
	var a = $("#a" + skuId).attr("disabled",true).val();
	//限购
	var l = $("#l" + skuId).attr("disabled",true).val();
	//运费
	var t = $("#t" + skuId).attr("disabled",true).val();

	
	//保存
	var url = "supdate.do";
	//json数据
	var params = {"price" : p,"trancost" : t,"sellcost" : s,"recont" : r,"limi" : l,"safcont" : a,"id" : skuId };
	//发送ajax请求
	$.post(url,params,function(data){
		if(data='suc'){
			alert('修改成功');
		}
		
	},"text");
}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 库存管理 - 列表</div>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" id="tableForm">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品颜色</th>
			<th>商品尺码</th>
			<th>商品材质</th>
			<th>市场价格</th>
			<th>销售价格</th>
			<th>库       存</th>
			<th>安全库存</th>
			<th>购买限制</th>
			<th>运       费</th>
			<th>操       作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${LIST}" var="sku">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<td><input type="checkbox" name="ids" value="${sku.id}"/></td>
				<td>${sku.pid}--${sku.id}</td>
				<td align="center">${sku.colorTxt}</td>
				<td align="center">${sku.sizTxt}</td>
				<td align="center">${sku.featureTxt}</td>
				<td align="center"><input type="text" id="p${sku.id}" value="${sku.price}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="s${sku.id}" value="${sku.sellcost}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="r${sku.id}" value="${sku.recont}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="a${sku.id}" value="${sku.safcont}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="l${sku.id}" value="${sku.limi}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="t${sku.id}" value="${sku.trancost}" disabled="disabled" size="10"/></td>
				<td align="center"><a href="javascript:updateSku(${sku.id})" class="pn-opt">修改</a> | <a href="javascript:saveSku(${sku.id})" class="pn-opt">保存</a></td>
			</tr>
		</c:forEach>
				
	</tbody>
</table>
</form>
</div>
</body>
</html>