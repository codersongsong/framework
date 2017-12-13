<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>定时任务实例启动日志表编辑</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body>
<#if resultType = 'add'>
<div class="div_content_heah">添加数据</div>
	<form id="ff" class="easyui-form" action='taskobjectregister/save.dhtml' method="POST" data-options="novalidate:true">
	<table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
		<tr>
			<td width='100'>注册编号：</td>
			<td>
				<input type="text"  id="registerNo" name="registerNo" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>实例唯一标示：</td>
			<td>
				<input type="text"  id="objectTal" name="objectTal" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>服务器IP：</td>
			<td>
				<input type="text"  id="taskRunServer" name="taskRunServer" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>服务器端口：</td>
			<td>
				<input type="text"  id="taskRunPort" name="taskRunPort" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>注册时间：</td>
			<td>
				<input type="text"  id="registerTime" name="registerTime" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>实例状态：</td>
			<td>
				<input type="text"  id="objectStatus" name="objectStatus" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>修改时间：</td>
			<td>
				<input type="text"  id="upTime" name="upTime" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
	<tr>
		<td colspan="2">
			<span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="add_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-add'">&nbsp;需&nbsp;要&nbsp;保&nbsp;存&nbsp;吗&nbsp;？&nbsp; </span>&nbsp;
			<span onclick="javascript:parent.go_lists()" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;去&nbsp;列&nbsp;表&nbsp;页&nbsp;？&nbsp;</span>
		</td>
	</tr>
</table>
</form>
</#if>

<#if resultType = 'edit'>
<div class="div_content_heah">修改数据--${enitty.registerNo!''}</div>
<form id="ff" class="easyui-form" action='taskobjectregister/edit.dhtml' method="POST" data-options="novalidate:true">
	<input type="hidden" id="registerNo" name="registerNo" value="${enitty.registerNo!''}">
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='100'>注册编号：</td>
			<td>
				<input type="text"  id="registerNo" name="registerNo" value="${enitty.registerNo!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>实例唯一标示：</td>
			<td>
				<input type="text"  id="objectTal" name="objectTal" value="${enitty.objectTal!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>服务器IP：</td>
			<td>
				<input type="text"  id="taskRunServer" name="taskRunServer" value="${enitty.taskRunServer!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>服务器端口：</td>
			<td>
				<input type="text"  id="taskRunPort" name="taskRunPort" value="${enitty.taskRunPort!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>注册时间：</td>
			<td>
				<input type="text"  id="registerTime" name="registerTime" value="${enitty.registerTime!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>实例状态：</td>
			<td>
				<input type="text"  id="objectStatus" name="objectStatus" value="${enitty.objectStatus!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>修改时间：</td>
			<td>
				<input type="text"  id="upTime" name="upTime" value="${enitty.upTime!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>
		<span onclick="colse_win('xgsj_${enitty.registerNo!''}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
		&nbsp;&nbsp;<font color="red">${messages!""}</font>
		</td>
	</tr>
</table>
</#if>
<#if resultType = 'view'>
<div class="div_content_heah">浏览数据--${enitty.registerNo!''}</div>
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='100'>注册编号：</td>
			<td>${enitty.registerNo!''}</td>
		</tr>
		<tr>
			<td  width='100'>实例唯一标示：</td>
			<td>${enitty.objectTal!''}</td>
		</tr>
		<tr>
			<td  width='100'>服务器IP：</td>
			<td>${enitty.taskRunServer!''}</td>
		</tr>
		<tr>
			<td  width='100'>服务器端口：</td>
			<td>${enitty.taskRunPort!''}</td>
		</tr>
		<tr>
			<td  width='100'>注册时间：</td>
			<td>${enitty.registerTime!''}</td>
		</tr>
		<tr>
			<td  width='100'>实例状态：</td>
			<td>${enitty.objectStatus!''}</td>
		</tr>
		<tr>
			<td  width='100'>修改时间：</td>
			<td>${enitty.upTime!''}</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="colse_win('llsj_${enitty.registerNo!''}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span></td>
	</tr>
</table>
</#if>
<#if resultType = 'edit'>
<script type="text/javascript">
</script>
</#if>
</body>
<script type="text/javascript">
$(function(){$('.validatebox-text').bind('blur', function(){$(this).validatebox('enableValidation').validatebox('validate');});})
function colse_win(id){	
	parent.colse_win(id);
}
</script>
</html>
