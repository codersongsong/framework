<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>定时任务代码生成表编辑</title>
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
	<form id="ff" class="easyui-form" action='taskcodegenerator/save.dhtml' method="POST" data-options="novalidate:true">
	<table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
		<tr>
			<td width='100'>子项目名称：</td>
			<td>
			    <input type="text" id="childProjectCode"  name="childProjectCode" value="" placeholder="请选择子项目" class="easyui-combobox search-input" style="width:260px;" data-options="required: true,inputId:'childProjectCode',editable:false,valueField:'childProjectCode',textField:'childProjectName',url:'childprojectinfo/selectByType.dhtml?childProjectEng=task'">
			</td>
		</tr>
		<tr>
			<td width='100'>表中文名：</td>
			<td>
				<input type="text"  id="tableChan" name="tableChan" value="" class="easyui-textbox"  data-options="required: true,">
			</td>
		</tr>
		<tr>
			<td width='100'>表英文名：</td>
			<td>
				<input type="text"  id="tableEng" name="tableEng" value="" class="easyui-textbox"  data-options="required: true,">
			</td>
		</tr>
		<tr>
			<td width='100'>锁定位：</td>
			<td>
				<input type="text"  id="lockFlg" name="lockFlg" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>锁定时间：</td>
			<td>
				<input type="text"  id="lockTm" name="lockTm" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>执行次数：</td>
			<td>
				<input type="text"  id="runCounts" name="runCounts" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>表主键：</td>
			<td>
				<input type="text"  id="tableKey" name="tableKey" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>任务说明：</td>
			<td>
				<input type="text"  id="taskAsk" name="taskAsk" value="" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td width='100'>任务类名：</td>
			<td>
				<input type="text"  id="taskClassName" name="taskClassName" value="" class="easyui-textbox"  data-options="">
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
<div class="div_content_heah">修改数据--${enitty.tableNumbers!''}</div>
<form id="ff" class="easyui-form" action='taskcodegenerator/edit.dhtml' method="POST" data-options="novalidate:true">
	<input type="hidden" id="tableNumbers" name="tableNumbers" value="${enitty.tableNumbers?c}">
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='100'>子项目名称：</td>
			<td>
				<input type="text" id="childProjectCode"  name="childProjectCode" value="${enitty.childProjectCode?c}" placeholder="请选择子项目" class="easyui-combobox search-input" style="width:260px;" data-options="required: true,inputId:'childProjectCode',editable:false,valueField:'childProjectCode',textField:'childProjectName',url:'childprojectinfo/selectByType.dhtml?childProjectEng=task'">
			</td>
		</tr>
		<tr>
			<td  width='100'>表中文名：</td>
			<td>
				<input type="text"  id="tableChan" name="tableChan" value="${enitty.tableChan!''}" class="easyui-textbox"  data-options="required: true,">
			</td>
		</tr>
		<tr>
			<td  width='100'>表英文名：</td>
			<td>
				<input type="text"  id="tableEng" name="tableEng" value="${enitty.tableEng!''}" class="easyui-textbox"  data-options="required: true,">
			</td>
		</tr>
		<tr>
			<td  width='100'>锁定位：</td>
			<td>
				<input type="text"  id="lockFlg" name="lockFlg" value="${enitty.lockFlg!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>锁定时间：</td>
			<td>
				<input type="text"  id="lockTm" name="lockTm" value="${enitty.lockTm!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>执行次数：</td>
			<td>
				<input type="text"  id="runCounts" name="runCounts" value="${enitty.runCounts!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>表主键：</td>
			<td>
				<input type="text"  id="tableKey" name="tableKey" value="${enitty.tableKey!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>任务说明：</td>
			<td>
				<input type="text"  id="taskAsk" name="taskAsk" value="${enitty.taskAsk!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
		<tr>
			<td  width='100'>任务类名：</td>
			<td>
				<input type="text"  id="taskClassName" name="taskClassName" value="${enitty.taskClassName!''}" class="easyui-textbox"  data-options="">
			</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>
		<span onclick="colse_win('xgsj_${enitty.tableNumbers?c}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
		&nbsp;&nbsp;<font color="red">${messages!""}</font>
		</td>
	</tr>
</table>
</#if>
<#if resultType = 'view'>
<div class="div_content_heah">浏览数据--${enitty.tableNumbers!''}</div>
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='100'>表DAO序号：</td>
			<td>${enitty.tableNumbers!''}</td>
		</tr>
		<tr>
			<td  width='100'>子项目编码：</td>
			<td>${enitty.childProjectCode!''}</td>
		</tr>
		<tr>
			<td  width='100'>表中文名：</td>
			<td>${enitty.tableChan!''}</td>
		</tr>
		<tr>
			<td  width='100'>表英文名：</td>
			<td>${enitty.tableEng!''}</td>
		</tr>
		<tr>
			<td  width='100'>锁定位：</td>
			<td>${enitty.lockFlg!''}</td>
		</tr>
		<tr>
			<td  width='100'>锁定时间：</td>
			<td>${enitty.lockTm!''}</td>
		</tr>
		<tr>
			<td  width='100'>执行次数：</td>
			<td>${enitty.runCounts!''}</td>
		</tr>
		<tr>
			<td  width='100'>表主键：</td>
			<td>${enitty.tableKey!''}</td>
		</tr>
		<tr>
			<td  width='100'>任务说明：</td>
			<td>${enitty.taskAsk!''}</td>
		</tr>
		<tr>
			<td  width='100'>任务类名：</td>
			<td>${enitty.taskClassName!''}</td>
		</tr>
		<tr>
			<td  width='100'>添加时间：</td>
			<td>${enitty.createTime!''}</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="colse_win('llsj_${enitty.tableNumbers?c}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span></td>
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
