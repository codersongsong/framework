<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>任务分片配置表编辑</title>
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
	<form id="ff" class="easyui-form" action='taskpartconfig/save.dhtml' method="POST" data-options="novalidate:true">
	<table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
		<tr>
			<td width='100'>任务编号：</td>
			<td>
				<input type="text"  id="taskNo" name="taskNo" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>实例任务主键：</td>
			<td>
				<input type="text"  id="objTaskKey" name="objTaskKey" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>任务运行服务器：</td>
			<td>
				<input type="text"  id="taskRunServer" name="taskRunServer" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>任务运行服务器端口：</td>
			<td>
				<input type="text"  id="taskRunPort" name="taskRunPort" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>分片值：</td>
			<td>
				<input type="text"  id="partValue" name="partValue" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>取模值：</td>
			<td>
				<input type="text"  id="modeValue" name="modeValue" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>实例唯一标示：</td>
			<td>
				<input type="text"  id="objectTal" name="objectTal" value="" class="easyui-textbox"  data-options="">
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
<div class="div_content_heah">修改数据--${enitty.partNo!''}</div>
<form id="ff" class="easyui-form" action='taskpartconfig/edit.dhtml' method="POST" data-options="novalidate:true">
	<input type="hidden" id="partNo" name="partNo" value="${enitty.partNo!''}">
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='100'>任务编号：</td>
			<td>
				<input type="text"  id="taskNo" name="taskNo" value="${enitty.taskNo!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>实例任务主键：</td>
			<td>
				<input type="text"  id="objTaskKey" name="objTaskKey" value="${enitty.objTaskKey!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>任务运行服务器：</td>
			<td>
				<input type="text"  id="taskRunServer" name="taskRunServer" value="${enitty.taskRunServer!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>任务运行服务器端口：</td>
			<td>
				<input type="text"  id="taskRunPort" name="taskRunPort" value="${enitty.taskRunPort!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>分片值：</td>
			<td>
				<input type="text"  id="partValue" name="partValue" value="${enitty.partValue!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>取模值：</td>
			<td>
				<input type="text"  id="modeValue" name="modeValue" value="${enitty.modeValue!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>实例唯一标示：</td>
			<td>
				<input type="text"  id="objectTal" name="objectTal" value="${enitty.objectTal!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>
		<span onclick="colse_win('xgsj_${enitty.partNo!''}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
		&nbsp;&nbsp;<font color="red">${messages!""}</font>
		</td>
	</tr>
</table>
</#if>
<#if resultType = 'view'>
<div class="div_content_heah">浏览数据--${enitty.partNo!''}</div>
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='100'>分片编号：</td>
			<td>${enitty.partNo!''}</td>
		</tr>
		<tr>
			<td  width='100'>任务编号：</td>
			<td>${enitty.taskNo!''}</td>
		</tr>
		<tr>
			<td  width='100'>实例任务主键：</td>
			<td>${enitty.objTaskKey!''}</td>
		</tr>
		<tr>
			<td  width='100'>任务运行服务器：</td>
			<td>${enitty.taskRunServer!''}</td>
		</tr>
		<tr>
			<td  width='100'>任务运行服务器端口：</td>
			<td>${enitty.taskRunPort!''}</td>
		</tr>
		<tr>
			<td  width='100'>分片值：</td>
			<td>${enitty.partValue!''}</td>
		</tr>
		<tr>
			<td  width='100'>取模值：</td>
			<td>${enitty.modeValue!''}</td>
		</tr>
		<tr>
			<td  width='100'>实例唯一标示：</td>
			<td>${enitty.objectTal!''}</td>
		</tr>
		<tr>
			<td  width='100'>修改时间：</td>
			<td>${enitty.upTime!''}</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="colse_win('llsj_${enitty.partNo!''}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span></td>
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
