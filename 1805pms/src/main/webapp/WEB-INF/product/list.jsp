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
<meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<title>商品列表</title>
<script type="text/javascript">
function getTableForm() {
	return document.getElementById('tableForm');
}
function optDelete() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请至少选择一个!");
		return;
	}
	if(!confirm("确定删除吗?")) {
		return;
	}
	var f = getTableForm();
	f.action="/admin/productDelete.do";
	f.submit();
}
function optIsshow() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请至少选择一个!");
		return;
	}
	if(!confirm("确定上架吗?")) {
		return;
	}
	var f = getTableForm();
	f.action="/admin/productIsShow.do";
	f.submit();
}

function changePageNo(){
	$("input[name='pageNo']").val(1);
}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='toadd.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="/admin/productList.do" method="post" style="padding-top:5px;">
<input type="hidden" value="1" name="pageNo"/>
名称: <input type="text" onkeyup="changePageNo()" value="" name="name"/>
	<select onchange="changePageNo()" name="brandId">
		<option value="">请选择品牌</option>
		
			<option value="1" >耐克（Nike）</option>
		
			<option value="2" >阿迪达斯</option>
		
			<option value="3" >李宁</option>
		
			<option value="4" >361度</option>
		
			<option value="5" >沃特（VOIT）</option>
		
			<option value="6" >星期六</option>
		
			<option value="7" >百丽</option>
		
			<option value="8" >鸿星尔克</option>
		
	</select>
	<select onchange="changePageNo()" name="isShow">
		<option  value="1">上架</option>
		<option   value="0">下架</option>
	</select>
	<input type="submit" class="query" value="查询"/>
</form>
<form method="post" id="tableForm">

<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>图片</th>
			<th width="4%">新品</th>
			<th width="4%">热卖</th>
			<th width="4%">推荐</th>
			<th width="4%">上下架</th>
			<th width="12%">操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${LIST}" var="product">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${product.id}"/></td>
			<td>${product.id}</td>
			<td align="center">${product.pname}</td>
			<td align="center"><img width="50" height="50" src="${product.picurl}"/></td>
			<td align="center">${product.isnew}</td>
			<td align="center">${product.ishot}</td>
			<td align="center">${product.isrecommend}</td>
			<td align="center"><a href="up.do?id=${product.id}" class="pn-opt">上架</></td>
			<td align="center">
			<a href="${pageContext.request.contextPath}/webproduct/${product.id}.html" class="pn-opt">查看</a> | <a href="add.html" class="pn-opt">修改</a> | <a href="/admin/productDelete.do?ids=297&name=&brandId=&isShow=" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a> | <a href="sku.do?pid=${product.id}" class="pn-opt">库存</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div class="page pb15">
	<span class="r inb_a page_b">
		
			<font size=2>首页</font>
		
			<font size=2>上一页</font>
		
			<strong>1</strong>
		
			<a href="javascript:void(0);" onclick="javascript:window.location.href='/admin/productList.do?&pageNo=2'">2</a>
		
			<a href="javascript:void(0);" onclick="javascript:window.location.href='/admin/productList.do?&pageNo=3'">3</a>
		
			<a href="javascript:void(0);" onclick="javascript:window.location.href='/admin/productList.do?&pageNo=2'"><font size=2>下一页</font></a>
		
			<a href="javascript:void(0);" onclick="javascript:window.location.href='/admin/productList.do?&pageNo=3'"><font size=2>尾页</font></a>
		
			共<var>3</var>页 到第<input type='text' id='PAGENO'  size='3' />页 <input type='button' id='skip' class='hand btn60x20' value='确定' onclick="javascript:window.location.href = '/admin/productList.do?&pageNo=' + $('#PAGENO').val() "/>
			
		
	
	</span>
</div>
<div style="margin-top:15px;">
<input class="del-button" type="button" value="删除" onclick="optDelete();"/><input class="add" type="button" value="上架" onclick="optIsshow();"/><input class="del-button" type="button" value="下架" onclick="optDelete();"/></div>
</form>
</div>
</body>
</html>