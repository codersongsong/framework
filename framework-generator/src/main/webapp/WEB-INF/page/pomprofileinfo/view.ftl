<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>pom文件profile信息管理 浏览</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="div_content_heah">浏览数据--${enitty.configName!''}</div>
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='100'>主键ID：</td>
			<td>${enitty.id!''}</td>
		</tr>
		<tr>
			<td  width='100'>配置描述：</td>
			<td>${enitty.configDescription!''}</td>
		</tr>
		<tr>
			<td  width='100'>配置标签：</td>
			<td>${enitty.configName!''}</td>
		</tr>
		<tr>
			<td  width='100'>配置类型：</td>
			<td>${enitty.type_colmun!''}</td>
		</tr>
        <tr>
            <td width='120'>关联主项目名称：</td>
            <td>
                <input type="text" id="andProjectId" name="andProjectId" value="${enitty.andProjectId!''}"
                       placeholder="关联项目"
                       class="easyui-combobox search-input" style="width:260px;"
                       data-options="disabled:true,inputId:'projectCode',editable:false,valueField:'projectCode',textField:'projectName',url:'projectbasicinfo/select.dhtml'">
            </td>
        </tr>
		<tr>
			<td  width='100'>关联依赖：</td>
			<td>
                <input type="text"  id="andDependencyId" name="andDependencyId" value="${enitty.andDependencyId!''}" style="width:500px;" class="easyui-combobox"  data-options="inputId:'type',disabled:true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=DEPENDENCY'">
			</td>
		</tr>
		<tr>
			<td  width='100'>开发环境配置：</td>
			<td>${enitty.envDev!''}</td>
		</tr>
		<tr>
			<td  width='100'>UAT环境配置：</td>
			<td>${enitty.envUat!''}</td>
		</tr>
		<tr>
			<td  width='100'>PRE环境配置：</td>
			<td>${enitty.envPre!''}</td>
		</tr>
		<tr>
			<td  width='100'>生产环境配置：</td>
			<td>${enitty.envLive!''}</td>
		</tr>
		<tr>
			<td  width='100'>分组：</td>
			<td>${enitty.groupInfo!''}</td>
		</tr>
		<tr>
			<td  width='100'>创建时间：</td>
			<td>${enitty.createTime_colmun!''}</td>
		</tr>
		<tr>
			<td  width='100'>更新时间：</td>
			<td>${enitty.updateTime_colmun!''}</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="colse_win('llsj_${enitty.id!''}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span></td>
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
