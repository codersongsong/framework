<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>表持久化信息表管理</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/inits.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',border:false,width:'220px'" id="west_h" style="background-color:#e2e5ed;">
	<div class="search-form" id="searchClear">
		<input type="text" id="childProjectCode"  name="childProjectCode" placeholder="请选择子项目持久层" class="easyui-combobox search-input" data-options="inputId:'childProjectCode',editable:false,valueField:'childProjectCode',textField:'childProjectName',url:'childprojectinfo/select.dhtml?type=010'">
		<input type="text"  id="tableChan" name="tableChan" placeholder="请输入表中文名" class="easyui-textbox search-input">
		<input type="text"  id="tableEng" name="tableEng" placeholder="请输入表英文名" class="easyui-textbox search-input">
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
				<table title="业务系统表数据列表" id="tt"  style="width:100%;height:615px;" url="tabledaoinfo/search.dhtml" data-options="striped:true,rownumbers:true,pagination:true">
					<thead>
		        		<tr>
			            	<th field="tableNumbers" checkbox="true" width="40" align="center">序号</th>
                            <th field="tddls" width="120" align="left" formatter="add_function">操作功能</th>
			            	<th field="childProjectCode_colmun" width="150" align="left">子项目编码</th>
			            	<th field="tableChan_colmun" width="150" align="left">表中文名</th>
			            	<th field="tableEng_colmun" width="150" align="left">表英文名</th>
			            	<th field="showColumns_colmun" width="150" align="left">显示字段</th>
			            	<th field="queryColumns_colmun" width="150" align="left">查询字段</th>
			            	<th field="addColumns_colmun" width="150" align="left">添加字段</th>
			            	<th field="pageForm_colmun" width="150" align="left">页面元素</th>
			            	<th field="editColumns_colmun" width="150" align="left">修改字段</th>
			            	<th field="viewColumns_colmun" width="150" align="left">浏览字段</th>
			            	<th field="columnType_colmun" width="150" align="left">字段类型</th>
			            	<th field="keyText_colmun" width="150" align="left">主键字段</th>
			            	<th field="columnsName_colmun" width="150" align="left">字段注释</th>
			            	<th field="createFiles_colmun" width="150" align="left">创建文件</th>
			            	<th field="createStatus_colmun" width="150" align="left">添加状态</th>
		        		</tr>
				    </thead>
				</table>
			</div>
			<div id="tjsj" style="height:600px;">
				<iframe scrolling="no" id="tjsj_add" frameborder="no" frameborder="0" marginwidth="0" marginheight="0"  src="tabledaoinfo/queryTable.dhtml" style="width:100%;height:600px;"></iframe>
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
    <div data-options="name:'create',iconCls:'icon-sum'" del_div_id="sqsj">创建覆盖原有</div>
</div>
<input type="hidden" id="url" value="tabledaoinfo/" />
<input type="hidden" id="pri_key" value="tableNumbers"/>
<input type="hidden" id="menu_name_v" value="tableChan"/>
<input type="text" id="id_key" value=""/>
<input type="text" id="id_name" value=""/>
</body>
<script type="text/javascript">
function params_str(queryParams){
//	queryParams.tableNumbers= $("#tableNumbers").combobox("getValue");
	queryParams.childProjectCode= $("#childProjectCode").combobox("getValue");
	queryParams.tableChan= $("#tableChan").textbox("getValue");
	queryParams.tableEng= $("#tableEng").textbox("getValue");
}
function ddl_other(item){//扩展
	var ck_v = $("#ssjg input:checked");
	if(ck_v.length == 0){
		$.messager.alert('警告','请选择您所要创建的持久层','warning');
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
/*对比刷新功能 whm 20171207 tableNumbers*/
function add_function(val, row) {
    return "【<a href='javascript:void(0)' onclick='refreshCache(\"010\",\"" + row.childProjectCode + "\")' >创建(保留新建)</a>】"
}

function refreshCache(flag, childProjectCode) {
    var params = {};
    params.flag = flag;
    params.id_key = childProjectCode;
    var url = 'tabledaoinfo/create.dhtml';
    $.ajax({
        type: "POST",
        url: url,
        timeout: 6000,
        dataType: 'json',
        data: params,
        success: function (data) {
            $.messager.alert("提示", data.message);
        },
        error: function () {
            $.messager.alert('警告', '系统异常，请稍后重试！', 'warning');
        },
        complete: function (XMLHttpRequest, status) {
            if (status == "timeout") {
                ajaxTimeoutTest.abort();
                $.messager.alert('警告', '加载数据超时！', 'warning');
            }
        }
    });
}

document.oncontextmenu=function(){return false;}
</script>
<script type="text/javascript" src="js/inits.js"></script>
</html>
