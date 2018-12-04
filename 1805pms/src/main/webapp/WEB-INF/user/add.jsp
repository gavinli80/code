
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>

<script src="../res/common/js/jquery.js" type="text/javascript"></script>

<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="../res/common/js/lecheng.js" type="text/javascript"></script>
<script src="../res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="../res/css/style.css" />

<script type="text/javascript">
	
	//文件上传
	function upload(){
		//ajax请求 局部提交
		//设置参数
		var args={
			//url 绝对路径
			url:$("#path").val()+"/upload/common.do",
			//返回类型
			dataType:"text",
			//提交方式
			type:"post",
			success:function(result){
				//设置图片的属性
				$("#img").attr("src",$("#path").val()+"/upload/"+result);	
				//将路径设置到隐藏域中
				$("#picurl").val(result);
			}
		}
		//表单局部提交
		$("#jvForm").ajaxSubmit(args);
		
		
		
	}

	
	//文档就绪事件
	$(function(){
			//下拉框change事件
			$("#dep1").change(
					
					
					function(){
						//清空第二个下拉框
						$("#dep2").empty();

						//ajax的异步提交
						$.post("getdep.do", //url
								{
									pid : this.value
								}, //json类型数据 传值
								function(data) {
									if(data!=null){
										
										$(data).each(
											function(){
												//添加数据到第二个下拉框中
												
												$("#dep2").append("<option value="+this.id+">"+ this.name+ "</option>");
											}		
										);
									}
									
									
								}, //成功后执行
								"json" //返回类型
								)
						
						
					}
					
		);

	});
	
		
	
</script>
</head>
<body>
<!--获得应用的绝对路径-->
<input type="hidden" id="path" value="${pageContext.request.contextPath}">

<img src="${pageContext.request.contextPath}/images/logo4.png" /><br />  <!--绝对路径-->

<img src="../images/logo4.png" /><!--相对路径 ../ 表示上一级-->
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 添加 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="pn-frequired">${MSG}</span></div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form name="fm" id="jvForm" action="add.do" method="post"  enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						登录名:</td>
						<td width="80%" class="pn-fcontent">
						<input id="loginname" type="text" class="required" name="loginname" maxlength="100" />
						<span id="loginnamemsg" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						密码:</td>
						<td width="80%" class="pn-fcontent">
						<input id="password" type="password" class="required" name="password" maxlength="100"/>
						<span id="passwordmsg" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						确认密码:</td>
						<td width="80%" class="pn-fcontent">
						<input id="conpwd"  type="password" class="required" name="conpwd" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						
						真实姓名:</td>
						<td width="80%" class="pn-fcontent">
						<input id="realname" type="text" class="required" name="realname" maxlength="100"/>
						<span id="realnamemsg" />
					</td>
				</tr>
		
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						性别:</td><td width="80%" class="pn-fcontent">
						<input type="radio"  name="sex"  checked value="男"/>男
						<input type="radio"  name="sex"   value="女"/>女
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						出生日期:</td><td width="80%" class="pn-fcontent">
						<input type="text"  name="birthday" maxlength="80"   class="Wdate" onclick="WdatePicker()" readonly="readonly"/>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						email:</td><td width="80%" class="pn-fcontent">
						<input id="email" type="text"  name="email" maxlength="80"/>
						<span id="emailmsg" />
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						部门:</td><td width="80%" class="pn-fcontent">
						<select id="dep1" name="dep1">
							<c:forEach items="${DLIST}" var="dep1">
								<option value="${dep1.id}">${dep1.name}</option>
							</c:forEach>
						</select>
						<select id="dep2" name="dept.id">
							<c:forEach items="${DLIST2}" var="dep2">
								<option value="${dep2.id}">${dep2.name}</option>
							</c:forEach>
						</select>						
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						头像:</td><td width="80%" class="pn-fcontent">
						<input type="file" name="file"  onchange="upload()">
						<img id="img" width="150px" height="150px" />
						<!--提交图片路径-->
						<input type="hidden" name="picurl"  id="picurl">						
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