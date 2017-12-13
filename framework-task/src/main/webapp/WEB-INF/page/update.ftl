<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改密码</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/basic.css?a=bb">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body style="padding:20px;">
<div class="div_content_heah" >修改密码--${user.userAccount!''}</div>
<form id="ff" class="easyui-form" action='login/updatepass.dhtml' method="POST" data-options="novalidate:true">
	<input type="hidden" id="userId" name="roleNo" value="${user.userId!''}">
	<table class="hovertable"  width="100%">
	<tr>
		<td width='100'>原密码：</td>
		<td>
			<input type="text" id="oldPass"  name="oldPass" value="" class="easyui-textbox" data-options="required: true,validType:['length[0,20]']
		" >
		</td>
	</tr>
	<tr>
		<td width='100'>新密码：</td>
		<td>
			<input type="text" id="newPass"  name="newPass" value="" class="easyui-textbox" data-options="required: true,validType:['length[0,20]']" >
		</td>
	</tr>
	<tr>
		<td colspan="2"><span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>
		&nbsp;&nbsp;<font color="red">${message!""}</font>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
$(function(){$('.validatebox-text').bind('blur', function(){$(this).validatebox('enableValidation').validatebox('validate');});})
function colse_win(id){	
	parent.colse_win(id);
}
</script>
</html>
