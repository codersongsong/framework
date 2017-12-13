<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<body>
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
			<td  width='100'>显示字段：</td>
			<td>${enitty.showColumns!''}</td>
		</tr>
		<tr>
			<td  width='100'>查询字段：</td>
			<td>${enitty.queryColumns!''}</td>
		</tr>
		<tr>
			<td  width='100'>添加字段：</td>
			<td>${enitty.addColumns!''}</td>
		</tr>
		<tr>
			<td  width='100'>页面元素：</td>
			<td>${enitty.pageForm!''}</td>
		</tr>
		<tr>
			<td  width='100'>修改字段：</td>
			<td>${enitty.editColumns!''}</td>
		</tr>
		<tr>
			<td  width='100'>浏览字段：</td>
			<td>${enitty.viewColumns!''}</td>
		</tr>
		<tr>
			<td  width='100'>字段类型：</td>
			<td>${enitty.columnType!''}</td>
		</tr>
		<tr>
			<td  width='100'>主键字段：</td>
			<td>${enitty.keyText!''}</td>
		</tr>
		<tr>
			<td  width='100'>字段注释：</td>
			<td>${enitty.columnsName!''}</td>
		</tr>
		<tr>
			<td  width='100'>添加时间：</td>
			<td>${enitty.createTime!''}</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="colse_win('llsj_${enitty.tableNumbers?c}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span></td>
	</tr>
</table>
</body>
<script type="text/javascript">
$(function(){$('.validatebox-text').bind('blur', function(){$(this).validatebox('enableValidation').validatebox('validate');});})
function colse_win(id){	
	parent.colse_win(id);
}
function colse_wins(){	
	location.href="tabledaoinfo/queryTable.dhtml";
}
</script>
</html>
