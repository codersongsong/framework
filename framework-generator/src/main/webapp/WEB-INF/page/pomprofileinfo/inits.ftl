<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>pom文件profile信息管理管理</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/inits.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',border:false,width:'220px'" id="west_h" style="background-color:#e2e5ed;">
	<div class="search-form" id="searchClear">
		<input type="text"  id="configDescription" name="configDescription" placeholder="请输入配置描述" class="easyui-textbox search-input">
		<input type="text"  id="configName" name="configName" placeholder="请输入配置标签" class="easyui-textbox search-input">
		<input type="text" id="type"  name="type" placeholder="请输入配置类型" class="easyui-combobox search-input" data-options="inputId:'type',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=PROFILE_TYPE'">
        <input type="text" id="andProjectId" name="andProjectId" placeholder="请输入关联项目"
               class="easyui-combobox search-input"
               data-options="inputId:'projectCode',editable:false,valueField:'projectCode',textField:'projectName',url:'projectbasicinfo/select.dhtml'">
        <input type="text" id="andDependencyId" name="andDependencyId" placeholder="请输入关联依赖"
               class="easyui-combobox search-input"
               data-options="inputId:'andDependencyId',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=DEPENDENCY'">
		<input type="text"  id="groupInfo" name="groupInfo" placeholder="请输入分组" class="easyui-textbox search-input">
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
				<table title="pom文件profile信息表数据列表" id="tt"  style="width:100%;height:615px;" url="pomprofileinfo/search.dhtml"
			         data-options="striped:true,rownumbers:true,pagination:true">
					<thead>
		        		<tr>
                            <th field="tableNumbers" checkbox="true" width="40" align="center">序号</th>
							<th field="configName" width="150" align="left">配置名称</th>
							<th field="configDescription_colmun" width="150" align="left">配置描述</th>
							<th field="type_colmun" width="150" align="left">配置类型</th>
                            <th field="andProjectId_colmun" width="150" align="left">关联项目</th>
							<th field="andDependencyId_colmun" width="150" align="left">关联依赖</th>
							<th field="envDev_colmun" width="150" align="left">开发环境配置</th>
							<th field="envUat_colmun" width="150" align="left">UAT环境配置</th>
							<th field="envPre_colmun" width="150" align="left">PRE环境配置</th>
							<th field="envLive_colmun" width="150" align="left">生产环境配置</th>
							<th field="groupInfo_colmun" width="150" align="left">分组</th>
							<th field="createTime_colmun" width="150" align="left">创建时间</th>
							<th field="updateTime_colmun" width="150" align="left">更新时间</th>
		        		</tr>
				    </thead>
				</table>
			</div>
			<div id="tjsj" style="height:600px;">
				<iframe scrolling="no" id="tjsj_add" frameborder="no" frameborder="0" marginwidth="0" marginheight="0"  src="pomprofileinfo/add.dhtml" style="width:100%;height:600px;"></iframe>
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
</div>
<input type="hidden" id="url" value="pomprofileinfo/" />
<input type="hidden" id="pri_key" value="id"/>
<input type="hidden" id="menu_name_v" value="id"/>
<input type="text" id="id_key" value=""/>
<input type="text" id="id_name" value=""/>
</body>
<script type="text/javascript">
function params_str(queryParams){
	queryParams.configDescription= $("#configDescription").textbox("getValue");
	queryParams.configName= $("#configName").textbox("getValue");
	queryParams.type= $("#type").combobox("getValue");
	queryParams.andDependencyId= $("#andDependencyId").combobox("getValue");
	queryParams.groupInfo= $("#groupInfo").textbox("getValue");
    queryParams.andProjectId = $("#andProjectId").combobox("getValue");
}
function ddl_other(item){//扩展
	//TODO
}
document.oncontextmenu=function(){return false;}
</script>
<script type="text/javascript" src="js/inits.js"></script>
</html>
