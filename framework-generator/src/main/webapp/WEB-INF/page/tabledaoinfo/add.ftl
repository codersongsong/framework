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
	<div class="div_content_heah">修改数据--${TABLE_NAME!''}</div>
	<form id="ff" class="easyui-form" action='tabledaoinfo/save.dhtml' method="POST" data-options="novalidate:true">
	<input type="hidden" id="TABLE_ENG" name="TABLE_ENG" value="${TABLE_ENG}">
	<input type="hidden" id="TABLE_NAME" name="TABLE_NAME" value="${TABLE_NAME}">
	<input type="hidden" name="KEY" id="KEY" value="${KEY!''}" />
	<input type="hidden" name="COLUMNS_TYPE" id="COLUMNS_TYPE" value="${COLUMNS_TYPE!''}" />
	<input type="hidden" name="COLUMNS_CODE" id="COLUMNS_CODE" value="${COLUMNS_CODE!''}" />
	<input type="hidden" name="COLUMNS_NAME" id="COLUMNS_NAME" value="${COLUMNS_NAME!''}" />
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='100'>子项目编码：</td>
			<td colspan="5">
				<input type="text" id="childProjectCode"  value="${childProjectCode}"  name="childProjectCode" class="easyui-combobox" data-options="inputId:'childProjectCode',editable:false,valueField:'childProjectCode',textField:'childProjectName',url:'childprojectinfo/select.dhtml?type=010'" >
			</td>
		</tr>
		<tr>
			<td width='6%'>表中文名：</td>
			<td width='12%'>${TABLE_NAME!''}</td>
			<td width='6%'>表英文名：</td>
			<td width='12%'>${TABLE_ENG!''}</td>
			<td width='6%'>增加字典：</td>
			<td width='58%'>
				<input type="radio" id="addDic" checked value="010"  name="addDic" />普通
				<input type="radio" id="addDic"  value="020"  name="addDic" />增加
			</td>
		</tr>
		<tr>
			<td>生成文件：</td>
			<td colspan="5">
				<input type="checkbox" checked name="createFiles" value="1">持久层&nbsp;
				<input type="checkbox" name="createFiles" value="2">控制器层&nbsp;
				<input type="checkbox" name="createFiles" value="3">页面层&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="6" >
			<table  border="0" width="1600" >
					<tr>
						<th>字段名</th>
						<th>备注</th>
						<th>字段类型</th>
						<th>显示列表</th>
						<th>查询字段</th>
						<th>需要添加的字段</th>
						<th>需要修改的字段</th>
						<th>字段类型</th>
						<th>校验类型</th>
					</tr>
					<#list 0..count-1 as t>
					<tr>
						<td>${COLUMNS_CODE_LIST[t]}</td>
						<td>${COLUMNS_NAME_LIST[t]}</td>
						<td>${COLUMNS_TYPE_LIST[t]}</td>
						<td><input type="checkbox" checked name="showList" value="${t}"></td>
						<td><input type="checkbox" name="queryList" value="${t}"></td>
						<td><input type="checkbox" checked name="addList" value="${t}"></td>
						<td><input type="checkbox" checked name="upList" value="${t}"></td>
						<td>
							<select name="type_${t}" onChange="oop('col_name_${t}',this)">
								<option value="1">文本框</option>
								<option value="0">表主键</option>
								<option value="2">下拉列表</option>
								<option value="3">单选按钮</option>
								<option value="4">复选框</option>
								<option value="5">文本域</option>
								<option value="6">日期文本框</option>
								<option value="7">文件上传</option>
								<option value="8">系统时间</option>
								<option value="9">金额文本框</option>
								<option value="10">浮点数文本框</option>
								<option value="11">控制器层入参</option>
							</select>
						</td>
						<td>
							非空：<input type="checkbox" name="check_${t}" value="1">&nbsp;&nbsp;&nbsp;
							长度：<input type="checkbox" name="check_${t}" value="3"><input type="text" name="check_${t}_c3" style="width:40px" value="">&nbsp;&nbsp;&nbsp;
							大小：<input type="checkbox" name="check_${t}" value="4"><input type="text" name="check_${t}_c4_1" style="width:40px" value="">~<input type="text" name="check_${t}_c4_2" style="width:40px" value="">.<input type="text" name="check_${t}_c4_3" style="width:40px" value="">&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					</#list>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="6"><span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>
			<span onclick="javascript:colse_wins()" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
			&nbsp;&nbsp;<font color="red">${messages!""}</font>
			</td>
		</tr>
	</table>
</div>
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
function colse_wins(){	
	location.href="tabledaoinfo/queryTable.dhtml";
}
</script>
</html>
