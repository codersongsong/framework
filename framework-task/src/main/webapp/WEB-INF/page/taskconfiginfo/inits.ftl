<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>任务基本信息配置表管理</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/inits.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',border:false,width:'220px'" id="west_h" style="background-color:#e2e5ed;">
	<div class="search-form" id="searchClear">
		<input type="text" id="sysNo"  name="sysNo" class="easyui-combobox search-input" placeholder="请选择所属业务系统" data-options="inputId:'sysNo',required: true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=SYS_NO'" >
		<input type="text"  id="taskName" name="taskName" placeholder="请输入任务名称" class="easyui-textbox search-input">
		<input type="text"  id="businessObjName" name="businessObjName" placeholder="请输入业务对象名称" class="easyui-textbox search-input">
		<input type="text"  id="groupTal" name="groupTal" placeholder="请输入分组标示" class="easyui-textbox search-input">
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
				<table title="业务系统表数据列表" id="tt"  style="width:100%;height:615px;" url="taskconfiginfo/search.dhtml"
			         data-options="striped:true,rownumbers:true,pagination:true">
					<thead>
		        		<tr>
		            	<th field="taskNo" checkbox="true" width="40" align="center">序号</th>
		            	<th field="sysNo_colmun" width="150" align="left">所属业务系统</th>
		            	<th field="taskName_colmun" width="200" align="left">任务名称</th>
		            	<th field="runRule_colmun" width="150" align="left">执行规则</th>
		            	<th field="businessObjName_colmun" width="220" align="left">业务对象名称</th>
		            	<th field="estimateObjCount" width="100" align="left">预计实例个数</th>
		            	<th field="taskCount_colmun" width="120" align="left">单实例最大任务数</th>
		            	<th field="onetaskLimitCount_colmun" width="155" align="left">单实例任务并发上限数</th>
		            	<th field="fullTaskCount_colmun" width="100" align="left">全局任务数</th>
		            	<th field="groupTal_colmun" width="100" align="left">分组标示</th>
		            	<th field="status_colmun" width="100" align="left">任务状态</th>
		            	<th field="taskRunTemplate_colmun" width="120" align="left">任务执行模板</th>
		        		</tr>
				    </thead>
				</table>
			</div>
			<div id="tjsj" style="height:600px;">
				<iframe scrolling="no" id="tjsj_add" frameborder="no" frameborder="0" marginwidth="0" marginheight="0"  src="taskconfiginfo/add.dhtml" style="width:100%;height:600px;"></iframe>
			</div>
			<div id="xgsj" style="height:600px;"></div>
			<div id="llsj" style="height:600px;"></div>
		</div>
	</div>
</div>
<div id="mm" class="easyui-menu" data-options="onClick:menuHandler" style="width:120px;">
    <div data-options="name:'add',iconCls:'icon-save'" add_div_id="tjsj">添加数据</div>
    <div data-options="name:'query',iconCls:'icon-edit'" query_div_id="xgsj">修改数据</div>
    <!-- <div data-options="name:'del',iconCls:'icon-no'" del_div_id="sqsj">删除数据</div> -->
</div>
<input type="hidden" id="url" value="taskconfiginfo/" />
<input type="hidden" id="pri_key" value="taskNo"/>
<input type="hidden" id="menu_name_v" value="taskName"/>
<input type="text" id="id_key" value=""/>
<input type="text" id="id_name" value=""/>
</body>
<script type="text/javascript">
function params_str(queryParams){
	queryParams.sysNo= $("#sysNo").textbox("getValue");
	queryParams.taskName= $("#taskName").textbox("getValue");
	queryParams.businessObjName= $("#businessObjName").textbox("getValue");
	queryParams.groupTal= $("#groupTal").textbox("getValue");
}
function ddl_other(item){//扩展
	//TODO
}
document.oncontextmenu=function(){return false;}
</script>
<script type="text/javascript" src="js/inits.js"></script>
</html>
