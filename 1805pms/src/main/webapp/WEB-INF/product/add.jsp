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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>新增</title>
<style type="">
.h2_ch a:hover, .h2_ch a.here {
    color: #FFF;
    font-weight: bold;
    background-position: 0px -32px;
}
.h2_ch a {
    float: left;
    height: 32px;
    margin-right: -1px;
    padding: 0px 16px;
    line-height: 32px;
    font-size: 14px;
    font-weight: normal;
    border: 1px solid #C5C5C5;
    background: url('../res/lecheng/img/admin/bg_ch.gif') repeat-x scroll 0% 0% transparent;
}
a {
    color: #06C;
    text-decoration: none;
}
</style>
<script type="text/javascript">




$(function(){
	var tObj;
	//获得根路径的值
	var path=$("#path").val();
	
	$("#tabs a").each(function(){
		if($(this).attr("class").indexOf("here") == 0){tObj = $(this)}
		$(this).click(function(){
			var c = $(this).attr("class");
			if(c.indexOf("here") == 0){return;}
			var ref = $(this).attr("ref");
			var ref_t = tObj.attr("ref");
			tObj.attr("class","nor");
			$(this).attr("class","here");
			$(ref_t).hide();
			$(ref).show();
			tObj = $(this);
			
			//选到第二个tab
			if(ref == '#tab_2'){
				var fck = new FCKeditor("productdesc");//productdesc是textarea的ID
				fck.BasePath = "../res/fckeditor/";//基本路径webapp../res/fckeditor/.
				fck.Height = 400 ;
			//	alert(path);
				//更改fck上传图片路径  拼绝对路径 
				fck.Config["ImageUploadURL"] = path+"/upload/fck.do";//上传图片
				fck.ReplaceTextarea();//转成Fck
			}
		});
	});
});

function uploadPic(){
	//定义参数
	var options = {
		url : "../upload/server.do",
		dataType : "text",
		type :  "post",
		success : function(data){
			//回调 二个路径  
			//url
			//path
			$("#allImgUrl").attr("src",data);
			//写隐藏域
			$("#picurl").val(data);
		}
	};
	//jquery.form使用方式
	$("#jvForm").ajaxSubmit(options);
}

</script>
</head>
<body>
<!--获得根路径-->
<input type="hidden" value="${pageContext.request.contextPath}"  id="path" />

<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='/admin/productList.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<h2 class="h2_ch"><span id="tabs">
<a href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>
<a href="javascript:void(0);" ref="#tab_2" title="商品描述" class="nor">商品描述</a>
<a href="javascript:void(3);" ref="#tab_3" title="商品参数" class="nor">包装清单</a>
</span></h2>
<div class="body-box" style="float:right">
	<form id="jvForm" action="add.do" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						商品类型:</td><td width="80%" class="pn-fcontent">
								<select name="ptype">
									<option value="">请选择</option>
									<c:forEach items="${TYPES}" var="type">
										<option value="${type.id}">${type.tname}</option>	
									</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						商品名称:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="pname" maxlength="100" size="100"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						商品产地:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="fromarea" maxlength="100" size="100"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						商品品牌:</td><td width="80%" class="pn-fcontent">
						<select name="brand">
							<option value="">请选择品牌</option>
								<c:forEach items="${BRANDS}" var="brand">
										<option value="${brand.id}">${brand.bname}</option>	
								</c:forEach>
											
						</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						商品毛重:</td><td width="80%" class="pn-fcontent">
						<input type="text" value="" class="required" name="weight" maxlength="10"/>KG
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						材质:</td><td width="80%" class="pn-fcontent">
						<c:forEach items="${FEATURES}" var="fea">
							<input type="checkbox" value="${fea.cid}" name="features">${fea.cname}</input>	
						</c:forEach>
							
							
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						颜色:</td><td width="80%" class="pn-fcontent">
							<c:forEach items="${COLORS}" var="cl">
								<input type="checkbox" value="${cl.cid}" name="colors">${cl.cname}</input>	
							</c:forEach>	
									
							
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						尺码:</td><td width="80%" class="pn-fcontent">
						<c:forEach items="${SIZES}" var="si">
							<input type="checkbox" name="sizes" value="${si.cid}">${si.cname}</input>
						</c:forEach>
							
						
						
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						状态:</td><td width="80%" class="pn-fcontent">
						<input type="checkbox" name="isnew" value="1"/>新品
						<input type="checkbox" name="isrecommend" value="1"/>推荐
						<input type="checkbox" name="ishot" value="1"/>热卖
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">
						注:该尺寸图片必须为90x150。
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
						<img width="100" height="100" id="allImgUrl"/>
						<input type="hidden" name="picurl" id="picurl"/>
						<input type="file" onchange="uploadPic()" name="file"/>
					</td>
				</tr>
			</tbody>
			<tbody id="tab_2" style="display: none">
				<tr>
					<td >
						<textarea rows="10" cols="10" id="productdesc" name="discribe"></textarea>
					</td>
				</tr>
			</tbody>
			<tbody id="tab_3" style="display: none">
				<tr>
					<td >
						<textarea rows="15" cols="136" id="productList" name="packlist"></textarea>
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>