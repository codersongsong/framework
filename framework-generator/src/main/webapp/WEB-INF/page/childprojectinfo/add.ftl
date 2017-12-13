<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>子项目信息配置表添加</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="div_content_heah">添加数据</div>
	<form id="ff" class="easyui-form" action='childprojectinfo/save.dhtml' method="POST" data-options="novalidate:true">
	<table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
				<tr>
			<td  width='120'>主项目名称：</td>
			<td>
                <input type="text" id="projectCode" name="projectCode" value="" placeholder="请选择主项目"
                       class="easyui-combobox search-input" style="width:260px;"
                       data-options="onSelect:onSelect,required: true,inputId:'projectCode',editable:false,valueField:'projectCode',textField:'projectName',url:'projectbasicinfo/select.dhtml'">
			</td>
		</tr>
		<tr>
			<td width='100'>子项目名称：</td>
			<td>
				<input type="text"  id="childProjectName" name="childProjectName" value="" class="easyui-textbox"  data-options="required: true," style="width:260px;">
			</td>
		</tr>
		<tr>
			<td width='100'>子项目英文名：</td>
			<td>
				<input type="text"  id="childProjectEng" name="childProjectEng" value="" class="easyui-textbox"  data-options="required: true," style="width:260px;">
			</td>
		</tr>
		<tr>
			<td width='100'>子项目类型：</td>
			<td>
				<input type="text" id="projectType"  name="projectType" placeholder="请选择子项目类型" class="easyui-combobox" style="width:260px;" data-options="required: true,inputId:'projectType',editable:false,valueField: 'value',textField: 'label',data:[{label: '持久层',value: '010'},{label: '前端WEB',value: '020'},{label: 'API接口',value: '030'},{label: 'DUBBO服务',value: '040'},{label: '定时任务',value: '050'},{label: '后台管理',value: '060'},{label: '其他',value: '999'}]" >
			</td>
		</tr>
		<tr>
			<td width='100'>打包类型：</td>
			<td>
				<input type="text" id="packageType"  name="packageType" placeholder="请选择打包类型" class="easyui-combobox" style="width:260px;" data-options="required: true,inputId:'packageType',editable:false,valueField: 'value',textField: 'label',data:[{label: 'war',value: 'war'},{label: 'jar',value: 'jar'},{label: 'pom',value: 'pom'}]" >
			</td>
		</tr>
		<tr>
			<td width='100'>版本配置文件名：</td>
			<td>
				<input type="text"  id="configFileName" name="configFileName" value="" class="easyui-textbox"  data-options="" style="width:260px;">&nbsp;&nbsp;（例：login-admin.properties）
			</td>
		</tr>
        <tr>
            <td width='100'>基础依赖包：</td>
            <td>
                <input id="basicJar" name="basicJar" value="" placeholder="请选择基础依赖包" class="easyui-combobox"
                       style="width:500px;"
                       data-options="inputId:'type',multiple:true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=DEPENDENCY'">
            </td>
        </tr>
		<tr>
			<td width='100'>环境版本配置：</td>
			<td>
                <input id="versionConfig" name="versionConfig" value="" placeholder="请选择环境配置" class="easyui-combobox"
                       style="width:500px;"
                       data-options="inputId:'type',multiple:true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=PROFILE'">
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

function onSelect(param) {
    $('#versionConfig').combobox({
        url: 'childprojectinfo/queryPomProfiles.dhtml?projectCode=' + param.projectCode,
        disabled: false,
        valueField: 'id',
        textField: 'configDescription',
        panelHeight: 'auto',
        prompt: '请选择配置信息'
    });
}
</script>
</html>
