<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>pom文件profile信息管理 添加</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="div_content_heah">添加数据</div>
	<form id="ff" class="easyui-form" action='pomprofileinfo/save.dhtml' method="POST" data-options="novalidate:true">
	<table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
		<tr>
			<td width='100'>配置描述：</td>
			<td>
				<input type="text"  id="configDescription" name="configDescription" value="" class="easyui-textbox" style="width:260px;" data-options="required: true,validType:'length[0,64]',">
			</td>
		</tr>
		<tr>
			<td width='100'>配置标签：</td>
			<td>
				<input type="text"  id="configName" name="configName" value="" class="easyui-textbox" style="width:260px;" data-options="required: true,validType:'length[0,32]',">
			</td>
		</tr>
		<tr>
			<td width='100'>配置类型：</td>
			<td>
				<input type="text" id="type"  name="type" class="easyui-combobox" data-options="inputId:'type',required: true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=PROFILE_TYPE'" >
			</td>
		</tr>
        <tr>
            <td width='120'>关联主项目名称：</td>
            <td>
                <input type="text" id="andProjectId" name="andProjectId" value="" placeholder="请选择关联项目"
                       class="easyui-combobox search-input" style="width:260px;"
                       data-options="inputId:'projectCode',editable:false,valueField:'projectCode',textField:'projectName',url:'projectbasicinfo/select.dhtml'">
            </td>
        </tr>
		<tr>
			<td width='100'>关联依赖：</td>
			<td>
                <input type="text"  id="andDependencyId" name="andDependencyId"  class="easyui-combobox"  style="width:500px;" data-options="inputId:'type',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=DEPENDENCY'">
			</td>
		</tr>
		<tr>
			<td width='100'>开发环境配置：</td>
			<td>
				<input type="text"  id="envDev" name="envDev" value="" class="easyui-textbox" style="width:500px;" data-options="required: true,validType:'length[0,128]',">
			</td>
		</tr>
		<tr>
			<td width='100'>UAT环境配置：</td>
			<td>
				<input type="text"  id="envUat" name="envUat" value="" class="easyui-textbox" style="width:500px;" data-options="required: true,validType:'length[0,128]',">
			</td>
		</tr>
		<tr>
			<td width='100'>PRE环境配置：</td>
			<td>
				<input type="text"  id="envPre" name="envPre" value="" class="easyui-textbox" style="width:500px;" data-options="required: true,validType:'length[0,128]',">
			</td>
		</tr>
		<tr>
			<td width='100'>生产环境配置：</td>
			<td>
				<input type="text"  id="envLive" name="envLive" value="" class="easyui-textbox" style="width:600px;" data-options="required: true,validType:'length[0,128]',">
			</td>
		</tr>
		<tr>
			<td width='100'>分组：</td>
			<td>
				<input type="text"  id="groupInfo" name="groupInfo" value="" class="easyui-textbox" style="width:260px;" data-options="required: true,validType:'length[0,8]',">
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
</body>
<script type="text/javascript">
$(function(){$('.validatebox-text').bind('blur', function(){$(this).validatebox('enableValidation').validatebox('validate');});})
function colse_win(id){	
	parent.colse_win(id);
}
</script>
</html>
