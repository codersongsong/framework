<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>依赖管理表 添加</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="div_content_heah">添加数据</div>
	<form id="ff" class="easyui-form" action='dependencyinfo/save.dhtml' method="POST" data-options="novalidate:true">
	<table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
		<tr>
			<td width='100'>groupId：</td>
			<td>
				<input type="text"  id="groupId" name="groupId" value="" class="easyui-textbox"  data-options="required: true,validType:'length[0,64]',">
			</td>
		</tr>
		<tr>
			<td width='100'>artifactId：</td>
			<td>
				<input type="text"  id="artifactId" name="artifactId" value="" class="easyui-textbox"  data-options="required: true,validType:'length[0,32]',">
			</td>
		</tr>
		<tr>
			<td width='100'>version：</td>
			<td>
				<input type="text"  id="version" name="version" value="" class="easyui-textbox"  data-options="required: true,validType:'length[0,16]',">
			</td>
		</tr>
		<tr>
			<td width='100'>scope：</td>
			<td>
				<input type="text"  id="scope" name="scope" value="" class="easyui-textbox"  data-options="validType:'length[0,16]',">
			</td>
		</tr>
		<tr>
			<td width='100'>依赖名称：</td>
			<td>
				<input type="text"  id="dependencyName" name="dependencyName" value="" class="easyui-textbox"  data-options="required: true,validType:'length[0,32]',">
			</td>
		</tr>
        <tr>
            <td width='100'>配置文件名称：</td>
            <td>
                <input type="text"  id="configFileName" name="configFileName" value="" class="easyui-textbox"  data-options="validType:'length[0,32]',">
            </td>
        </tr>
        <tr>
            <td width='100'>配置文件DEMO：</td>
            <td>
                <textarea class="textarea" name="configFileContent" id="configFileContent" style="height:200px;width:800px;"></textarea>
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
