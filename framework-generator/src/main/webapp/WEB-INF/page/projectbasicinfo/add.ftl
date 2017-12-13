<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>项目基本信息表编辑</title>
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
<form id="ff" class="easyui-form" action='projectbasicinfo/save.dhtml' method="POST" data-options="novalidate:true">
<table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
	<tr>
		<td width='120'>项目名称：</td>
		<td>
			<input type="text"  id="projectName" name="projectName" value="" class="easyui-textbox"  data-options="required: true,">
		</td>
	</tr>
	<tr>
		<td width='100'>项目英文名：</td>
		<td>
			<input type="text"  id="projectEng" name="projectEng" value="" class="easyui-textbox"  data-options="required: true,">
		</td>
	</tr>
	<tr>
		<td width='100'>数据库类型：</td>
		<td>
			<input type="text" id="databaseType"  name="databaseType" class="easyui-combobox" data-options="required: true,inputId:'databaseType',editable:false,valueField: 'value',textField: 'label',data:[{label: 'mysql',value: '010'},{label: 'oracle',value: '020'}]" >
		</td>
	</tr>
	<tr>
		<td width='100'>数据库驱动类：</td>
		<td>
			<input type="text"  id="databaseDriver" name="databaseDriver" value="" class="easyui-textbox"  data-options="required: true,"  style="width:400px">&nbsp;（注：oracle.jdbc.driver.OracleDriver，com.mysql.jdbc.Driver）
		</td>
	</tr>
	<tr>
		<td width='100'>数据库连接URL：</td>
		<td>
			<input type="text"  id="databaseUrl" name="databaseUrl" value="" class="easyui-textbox"  data-options="required: true,"  style="width:400px">&nbsp;（注：jdbc:oracle:thin:@10.126.53.184:31521:PRE_PRO，jdbc:mysql://10.126.53.197:3306/monitoruat）
		</td>
	</tr>
	<tr>
		<td width='100'>数据库账号：</td>
		<td>
			<input type="text"  id="databaseAccount" name="databaseAccount" value="" class="easyui-textbox"  data-options="required: true,">
		</td>
	</tr>
	<tr>
		<td width='100'>数据库密码：</td>
		<td>
			<input type="text"  id="databasePassword" name="databasePassword" value="" class="easyui-textbox"  data-options="required: true">
		</td>
	</tr>
	<tr>
		<td width='100'>项目存放路径：</td>
		<td>
			<input type="text"  id="projectPath" name="projectPath" value="" class="easyui-textbox"  data-options="required: true" style="width:400px">
		</td>
	</tr>
	<tr>
		<td>主包名：</td>
		<td>
			<input type="text"  id="packages" name="packages" value="" class="easyui-textbox"  data-options="required: true" style="width:400px">
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
<div class="div_content_heah">修改数据--${enitty.projectName!''}</div>
<form id="ff" class="easyui-form" action='projectbasicinfo/edit.dhtml' method="POST" data-options="novalidate:true">
<input type="hidden" id="projectCode" name="projectCode" value="${enitty.projectCode?c}">
<table class="hovertable"  width="100%">
	<tr>
		<td  width='120'>项目名称：</td>
		<td>
			<input type="text"  id="projectName" name="projectName" value="${enitty.projectName!''}" class="easyui-textbox"  data-options="required: true,">
		</td>
	</tr>
	<tr>
		<td  width='100'>项目英文名：</td>
		<td>
			<input type="text"  id="projectEng" name="projectEng" value="${enitty.projectEng!''}" class="easyui-textbox"  data-options="required: true,">
		</td>
	</tr>
	<tr>
		<td  width='100'>数据库类型：</td>
		<td>
			<input type="text" id="databaseType"  value="${enitty.databaseType!''}"  name="databaseType" class="easyui-combobox" data-options="required: true,inputId:'databaseType',editable:false,valueField: 'value',textField: 'label',data:[{label: 'mysql',value: '010'},{label: 'oracle',value: '020'}]" >
		</td>
	</tr>
	<tr>
		<td  width='100'>数据库驱动类：</td>
		<td>
			<input type="text"  id="databaseDriver" name="databaseDriver" value="${enitty.databaseDriver!''}" class="easyui-textbox"  data-options="required: true,"  style="width:400px">
		</td>
	</tr>
	<tr>
		<td  width='100'>数据库连接URL：</td>
		<td>
			<input type="text"  id="databaseUrl" name="databaseUrl" value="${enitty.databaseUrl!''}" class="easyui-textbox"  data-options="required: true,"  style="width:400px">
		</td>
	</tr>
	<tr>
		<td  width='100'>数据库账号：</td>
		<td>
			<input type="text"  id="databaseAccount" name="databaseAccount" value="${enitty.databaseAccount!''}" class="easyui-textbox"  data-options="required: true,">
		</td>
	</tr>
	<tr>
		<td  width='100'>数据库密码：</td>
		<td>
			<input type="text"  id="databasePassword" name="databasePassword" value="${enitty.databasePassword!''}" class="easyui-textbox"  data-options="required: true,">
		</td>
	</tr>
	<tr>
		<td  width='100'>项目存放路径：</td>
		<td>
			<input type="text"  id="projectPath" name="projectPath" value="${enitty.projectPath!''}" class="easyui-textbox"  data-options="required: true"  style="width:400px">
		</td>
	</tr>
	<tr>
		<td>主包名：</td>
		<td>
			<input type="text"  id="packages" name="packages" value="${enitty.packages!''}" class="easyui-textbox"  data-options="required: true" style="width:400px">
		</td>
	</tr>
	<tr>
		<td colspan="2"><span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>
		<span onclick="colse_win('xgsj_${enitty.projectCode?c}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
		&nbsp;&nbsp;<font color="red">${messages!""}</font>
		</td>
	</tr>
</table>
</#if>
<#if resultType = 'view'>
<div class="div_content_heah">浏览数据--${enitty.projectName!''}</div>
<table class="hovertable"  width="100%">
	<tr>
		<td  width='120'>项目编码：</td>
		<td>${enitty.projectCode?c}</td>
	</tr>
	<tr>
		<td  width='100'>项目名称：</td>
		<td>${enitty.projectName!''}</td>
	</tr>
	<tr>
		<td  width='100'>项目英文名：</td>
		<td>${enitty.projectEng!''}</td>
	</tr>
	<tr>
		<td  width='100'>数据库类型：</td>
		<td>${enitty.databaseType_colmun!''}</td>
	</tr>
	<tr>
		<td  width='100'>数据库驱动类：</td>
		<td>${enitty.databaseDriver!''}</td>
	</tr>
	<tr>
		<td  width='100'>数据库连接URL：</td>
		<td>${enitty.databaseUrl!''}</td>
	</tr>
	<tr>
		<td  width='100'>数据库账号：</td>
		<td>${enitty.databaseAccount!''}</td>
	</tr>
	<tr>
		<td  width='100'>数据库密码：</td>
		<td>${enitty.databasePassword!''}</td>
	</tr>
	<tr>
		<td  width='100'>项目存放路径：</td>
		<td>${enitty.projectPath!''}</td>
	</tr>
	<tr>
		<td>主包名：</td>
		<td>${enitty.packages!''}</td>
	</tr>
	<tr>
		<td  width='100'>项目创建时间：</td>
		<td>${enitty.createTime!''}</td>
	</tr>
	<tr>
		<td colspan="2"><span onclick="colse_win('llsj_${enitty.projectCode?c}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span></td>
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
