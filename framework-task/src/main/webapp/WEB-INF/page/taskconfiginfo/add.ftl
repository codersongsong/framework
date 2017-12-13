<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>任务基本信息配置表编辑</title>
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
	<form id="ff" class="easyui-form" action='taskconfiginfo/save.dhtml' method="POST" data-options="novalidate:true">
	<table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
		<tr>
			<td width='140'>所属业务系统：</td>
			<td>
				<input type="text" id="sysNo"  name="sysNo" class="easyui-combobox" style="width:260px;" data-options="inputId:'sysNo',required: true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=SYS_NO'" >
			</td>
		</tr>
		<tr>
			<td width='140'>任务名称：</td>
			<td>
				<input type="text"  id="taskName" name="taskName" value="" class="easyui-textbox"  data-options="required: true," style="width:260px;">
			</td>
		</tr>
		<tr>
			<td width='140'>执行规则：</td>
			<td>
				<input type="text"  id="runRule" name="runRule" value="" class="easyui-textbox"  data-options="required: true," style="width:260px;">
			</td>
		</tr>
		<tr>
			<td width='140'>业务对象名称：</td>
			<td>
				<input type="text"  id="businessObjName" name="businessObjName" value="" class="easyui-textbox"  data-options="required: true," style="width:260px;">
			</td>
		</tr>
		<tr>
			<td width='140'>预计实例个数：</td>
			<td>
				<input type="text"  id="estimateObjCount" name="estimateObjCount" value="1" class="easyui-numberbox"  data-options="required: true,min:1,max:10" style="width:260px;">
			</td>
		</tr>
		<tr>
			<td width='140'>单实例最大任务数：</td>
			<td>
				<input type="text"  id="taskCount" name="taskCount" value="1" class="easyui-numberbox"  data-options="required: true,min:1,max:10" style="width:260px;">
				<span style="color:red">【注：同实例中同一个任务最多可分身多少个任务】</span>
			</td>
		</tr>
		<tr>
			<td width='150'>单实例任务并发上限：</td>
			<td>
				<input type="text"  id="onetaskLimitCount" name="onetaskLimitCount" value="1" class="easyui-numberbox"  data-options="required: true,min:1,max:10" style="width:260px;">
				<span style="color:red">【注：在一个实例中同一个任务最多可并发启动多少线程数】</span>
			</td>
		</tr>
		<tr>
			<td width='140'>全局任务数：</td>
			<td>
				<input type="text"  id="fullTaskCount" name="fullTaskCount" value="0" class="easyui-numberbox"  data-options="required: true,min:0,max:1" style="width:260px;">
				<span style="color:red">【注：0，表示不限制；1，表示只有一个实例在执行任务】</span>
			</td>
		</tr>
		<tr>
			<td width='140'>业务参数：</td>
			<td>
				<input type="text"  id="businessInfo" name="businessInfo" value="" class="easyui-textbox"  data-options="" style="width:260px;">
			</td>
		</tr>
		<tr>
			<td width='140'>分组标示：</td>
			<td>
				<input type="text"  id="groupTal" name="groupTal" value="" class="easyui-textbox"  data-options="required: true," style="width:260px;">
			</td>
		</tr>
		<tr>
			<td width='140'>任务执行模板：</td>
			<td>
				<input type="text" id="taskRunTemplate"  name="taskRunTemplate" class="easyui-combobox" style="width:260px;" data-options="inputId:'taskRunTemplate',editable:false,valueField:'id',required: true,textField:'value',url:'dictionary/selectv.dhtml?key=TASK_RUN_TEMPLATE'" >
				<span style="color:red">【注：当为串行时“单实例最大任务数”和“单实例单任务并发上限”必须为1.】</span>
			</td>
		</tr>
		<tr>
			<td width='140'>任务状态：</td>
			<td>
				<input type="radio" name="status" id="status" checked='checked'  value="1" class="easyui-radio">启用&nbsp;
				<input type="radio" name="status" id="status"  value="2" class="easyui-radio">禁用&nbsp;
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
<div class="div_content_heah">修改数据--${enitty.taskName!''}</div>
<form id="ff" class="easyui-form" action='taskconfiginfo/edit.dhtml' method="POST" data-options="novalidate:true">
	<input type="hidden" id="taskNo" name="taskNo" value="${enitty.taskNo!''}">
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='140'>所属业务系统：</td>
			<td>
				<input type="text" id="sysNo"  name="sysNo" value="${enitty.sysNo?c}" class="easyui-combobox" style="width:260px;" data-options="inputId:'sysNo',required: true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=SYS_NO'" >
			</td>
		</tr>
		<tr>
			<td  width='140'>任务名称：</td>
			<td>
				<input type="text"  id="taskName" name="taskName" value="${enitty.taskName!''}" class="easyui-textbox" style="width:260px;" data-options="required: true,">
			</td>
		</tr>
		<tr>
			<td  width='140'>执行规则：</td>
			<td>
				<input type="text"  id="runRule" name="runRule" value="${enitty.runRule!''}" class="easyui-textbox" style="width:260px;" data-options="required: true,">
			</td>
		</tr>
		<tr>
			<td  width='140'>业务对象名称：</td>
			<td>
				<input type="text"  id="businessObjName" name="businessObjName" value="${enitty.businessObjName!''}" class="easyui-textbox" style="width:260px;" data-options="required: true,">
			</td>
		</tr>
		<tr>
			<td width='140'>预计实例个数：</td>
			<td> 
				<input type="text"  id="estimateObjCount" name="estimateObjCount" value="${enitty.estimateObjCount?c}" value="1" class="easyui-numberbox"  data-options="required: true,min:1,max:10" style="width:260px;">
			</td>
		</tr>
		<tr>
			<td  width='140'>单实例最大任务数：</td>
			<td>
				<input type="text"  id="taskCount" name="taskCount" value="${enitty.taskCount?c}" class="easyui-numberbox" style="width:260px;" data-options="required: true,min:1,max:10">
				<span style="color:red">【注：同实例中同一个任务最多可分身多少个任务】</span>
			</td>
		</tr>
		<tr>
			<td  width='140'>单实例任务并发上限：</td>
			<td>
				<input type="text"  id="onetaskLimitCount" name="onetaskLimitCount" value="${enitty.onetaskLimitCount?c}" class="easyui-numberbox" style="width:260px;" data-options="required: true,min:1,max:10">
				<span style="color:red">【注：在一个实例中同一个任务最多可并发启动多少线程数】</span>
			</td>
		</tr>
		<tr>
			<td  width='140'>全局任务数：</td>
			<td>
				<input type="text"  id="fullTaskCount" name="fullTaskCount" value="${enitty.fullTaskCount?c}" class="easyui-numberbox" style="width:260px;" data-options="required: true,min:0,max:1">
				<span style="color:red">【注：0，表示不限制；1，表示只有一个实例在执行任务】</span>
			</td>
		</tr>
		<tr>
			<td  width='140'>业务参数：</td>
			<td>
				<input type="text"  id="businessInfo" name="businessInfo" value="${enitty.businessInfo!''}" class="easyui-textbox" style="width:260px;" data-options="">
			</td>
		</tr>
		<tr>
			<td  width='140'>分组标示：</td>
			<td>
				<input type="text"  id="groupTal" name="groupTal" value="${enitty.groupTal!''}" class="easyui-textbox" style="width:260px;" data-options="required: true,">
			</td>
		</tr>
		<tr>
			<td  width='140'>任务执行模板：</td>
			<td>
				<input type="text" id="taskRunTemplate"  value="${enitty.taskRunTemplate!''}"  name="taskRunTemplate" style="width:260px;" class="easyui-combobox" data-options="inputId:'taskRunTemplate',required: true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=TASK_RUN_TEMPLATE'" >
				<span style="color:red">【注：当为串行时“单实例最大任务数”和“单实例单任务并发上限”必须为1.】</span>
			</td>
		</tr>
		<tr>
			<td  width='140'>任务状态：</td>
			<td>
				<input type="radio" name="status" id="status"  value="1" class="easyui-radio">启用&nbsp;
				<input type="radio" name="status" id="status"  value="2" class="easyui-radio">禁用&nbsp;
			</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>
		<span onclick="colse_win('xgsj_${enitty.taskNo!''}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
		&nbsp;&nbsp;<font color="red">${messages!""}</font>
		</td>
	</tr>
</table>
</#if>
<#if resultType = 'view'>
<div class="div_content_heah">浏览数据--${enitty.taskName!''}</div>
	<table class="hovertable"  width="100%">
		<tr>
			<td  width='140'>任务编号：</td>
			<td>${enitty.taskNo!''}</td>
		</tr>
		<tr>
			<td  width='140'>所属业务系统：</td>
			<td>${enitty.sysNo_colmun}</td>
		</tr>
		<tr>
			<td  width='140'>任务名称：</td>
			<td>${enitty.taskName!''}</td>
		</tr>
		<tr>
			<td  width='140'>执行规则：</td>
			<td>${enitty.runRule!''}</td>
		</tr>
		<tr>
			<td  width='140'>业务对象名称：</td>
			<td>${enitty.businessObjName!''}</td>
		</tr>
		<tr>
			<td width='140'>预计实例个数：</td>
			<td>${enitty.estimateObjCount?c}</td>
		</tr>
		<tr>
			<td  width='140'>单实例最大任务数：</td>
			<td>${enitty.taskCount!''}</td>
		</tr>
		<tr>
			<td  width='140'>单实例任务并发上限：</td>
			<td>${enitty.onetaskLimitCount!''}</td>
		</tr>
		<tr>
			<td  width='140'>全局任务数：</td>
			<td>${enitty.fullTaskCount!''}</td>
		</tr>
		<tr>
			<td  width='140'>业务参数：</td>
			<td>${enitty.businessInfo!''}</td>
		</tr>
		<tr>
			<td  width='140'>分组标示：</td>
			<td>${enitty.groupTal!''}</td>
		</tr>
		<tr>
			<td  width='140'>任务执行模板：</td>
			<td>${enitty.taskRunTemplate_colmun!''}</td>
		</tr>
		<tr>
			<td  width='140'>任务状态：</td>
			<td>${enitty.status_colmun}</td>
		</tr>
		<tr>
			<td  width='140'>配置时间：</td>
			<td>${enitty.configTime!''}</td>
		</tr>
		<tr>
			<td  width='140'>修改人：</td>
			<td>${enitty.upPerson!''}</td>
		</tr>
		<tr>
			<td  width='140'>修改时间：</td>
			<td>${enitty.upTime!''}</td>
		</tr>
	<tr>
		<td colspan="2"><span onclick="colse_win('llsj_${enitty.taskNo!''}')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span></td>
	</tr>
</table>
</#if>
<#if resultType = 'edit'>
<script type="text/javascript">
	$("input[name='status'][value='${enitty.status}']").click();
</script>
</#if>
</body>
<script type="text/javascript">
$(function(){$('.validatebox-text').bind('blur', function(){$(this).validatebox('enableValidation').validatebox('validate');});})
function colse_win(id){	
	parent.colse_win(id);
}
$('#taskRunTemplate').combobox({
	onSelect: function(param){
		var id = param.id;
		if(id.indexOf("ChildJobTimerIStempImpl") > -1){
			$("#fullTaskCount").textbox("setValue","0");
		}else if(id.indexOf("ChildJobTimerAIStempImpl") > -1 ){
			$("#fullTaskCount").textbox("setValue","0");
		}else if(id.indexOf("ChildStateJobTimerIStempImpl") > -1 ){
			$("#taskCount").textbox("setValue","1");
			$("#onetaskLimitCount").textbox("setValue","1");
			$("#fullTaskCount").textbox("setValue","1");
		}else if(id.indexOf("ChildStateJobTimerAIStempImpl") > -1 ){
			$("#taskCount").textbox("setValue","1");
			$("#onetaskLimitCount").textbox("setValue","1");
			$("#fullTaskCount").textbox("setValue","0");
		}
	}
});
</script>
</html>
