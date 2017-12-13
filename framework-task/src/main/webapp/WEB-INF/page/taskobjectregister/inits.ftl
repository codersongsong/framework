<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>定时任务实例启动日志表管理</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/inits.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',border:false,width:'220px'" id="west_h" style="background-color:#e2e5ed;">
	<div class="search-form" id="searchClear">
		<input type="text"  id="objectTal" name="objectTal" placeholder="请输入实例唯一标示" class="easyui-textbox search-input">
		<input type="text"  id="taskRunServer" name="taskRunServer" placeholder="请输入服务器IP" class="easyui-textbox search-input">
		<input type="text"  id="taskRunPort" name="taskRunPort" placeholder="请输入服务器端口" class="easyui-textbox search-input">
		<input type="text"  id="registerTime" name="registerTime" placeholder="请输入注册时间" class="easyui-textbox search-input">
		<input type="text"  id="objectStatus" name="objectStatus" placeholder="请输入实例状态" class="easyui-textbox search-input">
		<input type="text"  id="upTime" name="upTime" placeholder="请输入修改时间" class="easyui-textbox search-input">
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
	    </ul>
		<div id="alls_divs">
			<div id="ssjg">
				<table title="业务系统表数据列表" id="tt"  style="width:100%;height:615px;" url="taskobjectregister/search.dhtml"
			         data-options="striped:true,rownumbers:true,pagination:true">
					<thead>
		        		<tr>
	            	<th field="registerNo" checkbox="true" width="40" align="center">序号</th>
	            	<th field="objectTal_colmun" width="150" align="left">实例唯一标示</th>
	            	<th field="taskRunServer_colmun" width="150" align="left">服务器IP</th>
	            	<th field="taskRunPort_colmun" width="150" align="left">服务器端口</th>
	            	<th field="registerTime_colmun" width="150" align="left">注册时间</th>
	            	<th field="objectStatus_colmun" width="150" align="left">实例状态</th>
	            	<th field="upTime_colmun" width="150" align="left">修改时间</th>
		        		</tr>
				    </thead>
				</table>
			</div>
			<div id="tjsj" style="height:600px;">
				<iframe scrolling="no" id="tjsj_add" frameborder="no" frameborder="0" marginwidth="0" marginheight="0"  src="taskobjectregister/add.dhtml" style="width:100%;height:600px;"></iframe>
			</div>
			<div id="xgsj" style="height:600px;"></div>
			<div id="llsj" style="height:600px;"></div>
		</div>
	</div>
</div>
<input type="hidden" id="url" value="taskobjectregister/" />
<input type="hidden" id="pri_key" value="registerNo"/>
<input type="hidden" id="menu_name_v" value="registerNo"/>
<input type="text" id="id_key" value=""/>
<input type="text" id="id_name" value=""/>
</body>
<script type="text/javascript">
function params_str(queryParams){
	queryParams.registerNo= $("#registerNo").textbox("getValue");
	queryParams.objectTal= $("#objectTal").textbox("getValue");
	queryParams.taskRunServer= $("#taskRunServer").textbox("getValue");
	queryParams.taskRunPort= $("#taskRunPort").textbox("getValue");
	queryParams.registerTime= $("#registerTime").textbox("getValue");
	queryParams.objectStatus= $("#objectStatus").textbox("getValue");
	queryParams.upTime= $("#upTime").textbox("getValue");
}
function ddl_other(item){//扩展
	//TODO
}
document.oncontextmenu=function(){return false;}
</script>
<script type="text/javascript" src="js/inits.js"></script>
</html>
