<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="height:100%">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>表持久化信息表编辑</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body style="height:100%">
<div style="height:100%;overflow:auto !important">
	<div class="div_content_heah">查询数据</div>
	<form id="ff" class="easyui-form" action='tabledaoinfo/add.dhtml' method="POST" data-options="novalidate:true">
	<table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
		<tr>
			<td width='100'>子项目编码：</td>
			<td>
                <input type="text" id="childProjectCode"  name="childProjectCode" placeholder="请选择子项目持久层" class="easyui-combobox search-input" style="width:280px;"
                       data-options="onSelect:onSelect,inputId:'childProjectCode',editable:false,valueField:'childProjectCode',textField:'childProjectName',url:'childprojectinfo/select.dhtml?type=010'">
				<#--<input type="text" id="childProjectCode"  name="childProjectCode" placeholder="请选择子项目持久层" class="easyui-combobox search-input" style="width:280px;" data-options="required: true,inputId:'childProjectCode',editable:false,valueField:'childProjectCode',textField:'childProjectName',url:'childprojectinfo/select.dhtml?type=010'">-->
			</td>
		</tr>
		<tr>
			<td width='100'>表英文名：</td>
			<td>
                <input type="text" id="tableEng"  name="tableEng" placeholder="请选择要生成的表" class="easyui-combobox" style="width:280px;"
                       data-options="disabled: true,inputId:'tableEng',editable:false,valueField:'name',textField:'name'">
				<#--<input type="text"  id="tableEng" name="tableEng" value="" class="easyui-textbox"  data-options="required: true" style="width:280px;">-->
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="add_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-add'">&nbsp;查&nbsp;询&nbsp; </span>&nbsp;
			</td>
		</tr>
	</table>
	</form>
	</div>
</body>
<script type="text/javascript">
$(function(){$('.validatebox-text').bind('blur', function(){$(this).validatebox('enableValidation').validatebox('validate');});})
function colse_win(id){	
	parent.colse_win(id);
}
function onSelect(param) {
    $('#tableEng').combobox({
        url: 'projectbasicinfo/queryTblName.dhtml?childProjectCode='+param.childProjectCode,
        panelHeight:300,
		disabled:false,
        valueField:'name',
        textField:'name',
        prompt : '请选择要生成的表'
    });
}
</script>
</html>
