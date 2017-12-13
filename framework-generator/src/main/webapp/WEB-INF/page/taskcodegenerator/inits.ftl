<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href='${Request["basePath"] ! ""}'/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>定时任务代码生成表管理</title>
    <link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/inits.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',border:false,width:'220px'" id="west_h" style="background-color:#e2e5ed;">
    <div class="search-form" id="searchClear">
        <input type="text" id="childProjectCode" name="childProjectCode" placeholder="请输入子项目编码"
               class="easyui-textbox search-input">
        <input type="text" id="tableChan" name="tableChan" placeholder="请输入表中文名" class="easyui-textbox search-input">
        <input type="text" id="tableEng" name="tableEng" placeholder="请输入表英文名" class="easyui-textbox search-input">
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
                <table title="业务系统表数据列表" id="tt" style="width:100%;height:615px;" url="taskcodegenerator/search.dhtml"
                       data-options="striped:true,rownumbers:true,pagination:true">
                    <thead>
                    <tr>
                        <th field="tableNumbers" checkbox="true" width="40" align="center">序号</th>
                        <th field="childProjectCode_colmun" width="150" align="left">子项目编码</th>
                        <th field="tableChan_colmun" width="150" align="left">表中文名</th>
                        <th field="tableEng_colmun" width="150" align="left">表英文名</th>
                        <th field="lockFlg_colmun" width="150" align="left">锁定位</th>
                        <th field="lockTm_colmun" width="150" align="left">锁定时间</th>
                        <th field="runCounts_colmun" width="150" align="left">执行次数</th>
                        <th field="tableKey_colmun" width="150" align="left">表主键</th>
                        <th field="taskAsk_colmun" width="150" align="left">任务说明</th>
                        <th field="taskClassName_colmun" width="150" align="left">任务类名</th>
                        <th field="createTime_colmun" width="150" align="left">添加时间</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div id="tjsj" style="height:600px;">
                <iframe scrolling="no" id="tjsj_add" frameborder="no" frameborder="0" marginwidth="0" marginheight="0"
                        src="taskcodegenerator/add.dhtml" style="width:100%;height:600px;"></iframe>
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
    <div data-options="name:'createProject',iconCls:'icon-edit'">创建任务代码</div>
</div>
<input type="hidden" id="url" value="taskcodegenerator/"/>
<input type="hidden" id="pri_key" value="tableNumbers"/>
<input type="hidden" id="menu_name_v" value="tableNumbers"/>
<input type="text" id="id_key" value=""/>
<input type="text" id="id_name" value=""/>
</body>
<script type="text/javascript">
    function params_str(queryParams) {
        queryParams.childProjectCode = $("#childProjectCode").textbox("getValue");
        queryParams.tableChan = $("#tableChan").textbox("getValue");
        queryParams.tableEng = $("#tableEng").textbox("getValue");
    }
    function ddl_other(item) {//扩展
        if ("createProject" == item.name) {
            var ck_v = $("#ssjg input:checked");
            if (ck_v.length == 0) {
                $.messager.alert('警告', '请选择您所要生成代码的记录', 'warning');
                return;
            }
            var selectLength = 1;
            $(ck_v).each(function (index) {
                if (ck_v[index].value == "on") {
                    selectLength++;
                }
            });
            if (ck_v.length > selectLength) {
                $.messager.confirm('生成数据', '请选择一条记录！', function (r) {
                });
                return;
            }
            var id = $("#id_key").val();
            ajax_data($("#url").val() + "createProject.dhtml?id_key=" + $("#id_key").val());
        }
    }

    document.oncontextmenu = function () {
        return false;
    }
</script>
<script type="text/javascript" src="js/inits.js"></script>
</html>
