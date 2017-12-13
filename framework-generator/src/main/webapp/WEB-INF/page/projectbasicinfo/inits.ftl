<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>项目基本信息表管理</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/inits.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',border:false,width:'220px'" id="west_h" style="background-color:#e2e5ed;">
	<div class="search-form" id="searchClear">
		<input type="text"  id="projectName" name="projectName" placeholder="请输入项目名称" class="easyui-textbox search-input">
		<input type="text"  id="projectEng" name="projectEng" placeholder="请输入项目英文名" class="easyui-textbox search-input">
		<input type="text" id="databaseType"  name="databaseType" placeholder="请选择数据库类型" class="easyui-combobox search-input" data-options="inputId:'databaseType',editable:false,valueField: 'value',textField: 'label',data:[{label: 'mysql',value: '010'},{label: 'oracle',value: '020'}]">
		<div class="search-opera">
			<span id="search_button" class="easyui-linkbutton" iconCls="icon-search">想要什么？</span>
			<span id="search_clear_button" class="easyui-linkbutton" iconCls="icon-clear">清理一下？</span>
		</div>
	</div>
</div>

<div data-options="region:'center',border:false" style="background-color:#ededed">
	<div class="center-con">
		<ul class="oper-item " id="menus_divs">
	    	<li class="oper-btn" id="ssjg_div"><i class="oper-search-btn"></i><span>搜索结果</span></li>
	    	<li class="oper-btn" id="tjsj_div"><i class="oper-add-btn"></i><span>添加数据</span></li>
	    </ul>
		<div id="alls_divs">
			<div id="ssjg">
				<table title="业务系统表数据列表" id="tt"  style="width:100%;height:615px;" url="projectbasicinfo/search.dhtml"
			         data-options="striped:true,rownumbers:true,pagination:true">
					<thead>
		        		<tr>
			            	<th field="projectCode" checkbox="true" width="40" align="center">序号</th>
			            	<th field="projectName_colmun" width="120" align="left">项目名称</th>
			            	<th field="projectEng_colmun" width="100" align="left">项目英文名</th>
			            	<th field="databaseType_colmun" width="100" align="left">数据库类型</th>
			            	<th field="databaseDriver_colmun" width="150" align="left">数据库驱动类</th>
			            	<th field="databaseUrl_colmun" width="200" align="left">数据库连接URL</th>
			            	<th field="databaseAccount_colmun" width="100" align="left">数据库账号</th>
			            	<th field="databasePassword_colmun" width="150" align="left">数据库密码</th>
			            	<th field="projectPath_colmun" width="300" align="left">项目存放路径</th>
			            	<th field="createTime_colmun" width="150" align="left">项目创建时间</th>
		        		</tr>
				    </thead>
				</table>
			</div>
			<div id="tjsj" style="height:600px;">
				<iframe scrolling="no" id="tjsj_add" frameborder="no" frameborder="0" marginwidth="0" marginheight="0"  src="projectbasicinfo/add.dhtml" style="width:100%;height:600px;"></iframe>
			</div>
			<div id="xgsj" style="height:600px;"></div>
			<div id="llsj" style="height:600px;"></div>
		</div>
	</div>
</div>
<div id="mm" class="easyui-menu" data-options="onClick:menuHandler" style="width:120px;">
    <div data-options="name:'add',iconCls:'icon-save'" add_div_id="tjsj">添加数据</div>
    <div data-options="name:'query',iconCls:'icon-edit'" query_div_id="xgsj">修改数据</div>
    <div data-options="name:'del',iconCls:'icon-no'" del_div_id="sqsj">删除数据</div>
    <div data-options="name:'create',iconCls:'icon-sum'" del_div_id="sqsj">创建主项目</div>
</div>
<input type="hidden" id="url" value="projectbasicinfo/" />
<input type="hidden" id="pri_key" value="projectCode"/>
<input type="hidden" id="menu_name_v" value="projectName"/>
<input type="text" id="id_key" value=""/>
<input type="text" id="id_name" value=""/>
</body>
<script type="text/javascript">
function params_str(queryParams){
	queryParams.projectName= $("#projectName").textbox("getValue");
	queryParams.projectEng= $("#projectEng").textbox("getValue");
	queryParams.databaseType= $("#databaseType").combobox("getValue");
}
function ddl_other(item){//扩展
	var ck_v = $("#ssjg input:checked");
	if(ck_v.length == 0){
		$.messager.alert('警告','请选择您所要创建的主项目','warning');
		return ;
	}
	if(ck_v.length == 1){if(ck_v[0].value =="on"){return ;}}
	$.messager.confirm('创建', '确认要创建吗？', function(r){
		if (r){
			var del_str = "";
			$(ck_v).each(function (index){
				if(ck_v[index].value!="on"){
					del_str = del_str + ck_v[index].value+",";
				}
			});
			del_str = del_str.substring(0, del_str.length-1);
			ajax_data($("#url").val()+"create.dhtml?id_key="+del_str);
		}
	});
}
document.oncontextmenu=function(){return false;}
</script>
<script type="text/javascript" src="js/inits.js"></script>
</html>
