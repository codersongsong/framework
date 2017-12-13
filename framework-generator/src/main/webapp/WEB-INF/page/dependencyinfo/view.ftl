<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>依赖管理表 浏览</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="div_content_heah">浏览数据--${enitty.groupId!''}</div>
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='100'>主键ID：</td>
			<td>${enitty.id!''}</td>
		</tr>
		<tr>
			<td  width='100'>groupId：</td>
			<td>${enitty.groupId!''}</td>
		</tr>
		<tr>
			<td  width='100'>artifactId：</td>
			<td>${enitty.artifactId!''}</td>
		</tr>
		<tr>
			<td  width='100'>version：</td>
			<td>${enitty.version!''}</td>
		</tr>
		<tr>
			<td  width='100'>scope：</td>
			<td>${enitty.scope!''}</td>
		</tr>
		<tr>
			<td  width='100'>依赖名称：</td>
			<td>${enitty.dependencyName!''}</td>
		</tr>
        <tr>
            <td  width='100'>配置文件名称：</td>
            <td>${enitty.configFileName!''}</td>
        </tr>
        <tr>
            <td  width='100'>配置文件DEMO：</td>
            <td>
                <textarea class="textarea"  style="height:200px;width:800px;">${enitty.configFileContent!''}</textarea>
			</td>
        </tr>
		<tr>
			<td  width='100'>创建时间：</td>
			<td>${enitty.createTime!''}</td>
		</tr>
		<tr>
			<td  width='100'>更新时间：</td>
			<td>${enitty.updateTime!''}</td>
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
