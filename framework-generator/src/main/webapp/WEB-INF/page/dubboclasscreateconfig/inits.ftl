<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href='${Request["basePath"] ! ""}'/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>DUBBO服务类生产配置管理</title>
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
               class="easyui-combobox search-input"
               data-options="inputId:'childProjectCode',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=CHILD_PROJECT_CODE'">
        <input type="text" id="createType" name="createType" placeholder="请输入生成类型" class="easyui-combobox search-input"
               data-options="inputId:'createType',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=CREATE_TYPE'">
        <input type="text" id="dubboClass" name="dubboClass" placeholder="请输入DUBBO服务接口类"
               class="easyui-textbox search-input">
        <input type="text" id="dubboClassAsk" name="dubboClassAsk" placeholder="请输入服务接口类功能说明"
               class="easyui-textbox search-input">
        <input type="text" id="dubboClassMethod" name="dubboClassMethod" placeholder="请输入DUBBO服务接口方法"
               class="easyui-textbox search-input">
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
            <li class="oper-btn" id="drwd_div"><i class="oper-add-btn"></i><span>导入Excel</span></li>
        </ul>
        <div id="alls_divs">
            <div id="ssjg">
                <table title="业务系统表数据列表" id="tt" style="width:100%;height:615px;"
                       url="dubboclasscreateconfig/search.dhtml"
                       data-options="striped:true,rownumbers:true,pagination:true">
                    <thead>
                    <tr>
                        <th field="classNo" checkbox="true" width="40" align="center">序号</th>
                        <th field="childProjectCode_colmun" width="150" align="left">子项目名称</th>
                        <th field="createType_colmun" width="150" align="left">生成类型</th>
                        <th field="dubboClass_colmun" width="150" align="left">DUBBO服务接口类</th>
                        <th field="dubboClassAsk_colmun" width="150" align="left">服务接口类功能说明</th>
                        <th field="dubboClassMethod_colmun" width="150" align="left">DUBBO服务接口方法</th>
                        <th field="dubboClassMethodAsk_colmun" width="150" align="left">服务接口方法说明</th>
                        <th field="serviceCalss_colmun" width="150" align="left">服务层接口类</th>
                        <th field="serviceCalssAsk_colmun" width="150" align="left">服务层接口类功能说明</th>
                        <th field="serviceCalssMethod_colmun" width="150" align="left">服务层接口方法</th>
                        <th field="serviceCalssMethodAsk_colmun" width="150" align="left">服务层接口方法功能说明</th>
                        <th field="logicCalss_colmun" width="150" align="left">业务层类名称</th>
                        <th field="logicCalssAsk_colmun" width="150" align="left">业务层类功能说明</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div id="tjsj" style="height:600px;">
                <iframe scrolling="no" id="tjsj_add" frameborder="no" frameborder="0" marginwidth="0" marginheight="0"
                        src="dubboclasscreateconfig/add.dhtml" style="width:100%;height:600px;"></iframe>
            </div>
            <div id="drwd" style="height:600px;">
                <iframe scrolling="no" id="drwd_add" frameborder="no" frameborder="0" marginwidth="0" marginheight="0"
                        src="dubboclasscreateconfig/import.dhtml" style="width:100%;height:600px;"></iframe>
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
    <div data-options="name:'create',iconCls:'icon-sum'" del_div_id="sqsj">创建dubbo服务</div>
</div>
<input type="hidden" id="url" value="dubboclasscreateconfig/"/>
<input type="hidden" id="create_url" value="dubboclasscreate/"/>
<input type="hidden" id="pri_key" value="classNo"/>
<input type="hidden" id="menu_name_v" value="classNo"/>
<input type="text" id="id_key" value=""/>
<input type="text" id="id_name" value=""/>
</body>
<script type="text/javascript">
    function params_str(queryParams) {
        queryParams.childProjectCode = $("#childProjectCode").combobox("getValue");
        queryParams.createType = $("#createType").combobox("getValue");
        queryParams.dubboClass = $("#dubboClass").textbox("getValue");
        queryParams.dubboClassAsk = $("#dubboClassAsk").textbox("getValue");
        queryParams.dubboClassMethod = $("#dubboClassMethod").textbox("getValue");
    }
    function ddl_other(item) {//扩展
        if ("import" == item.name) {
            content_opacity("drwd");
            menu_opacity("drwd_div");
        } else if ("create" == item.name) {
            var ck_v = $("#ssjg input:checked");
            if (ck_v.length == 0) {
                $.messager.alert('警告', '请选择您所要创建的dubbo服务', 'warning');
                return;
            }
            if (ck_v.length == 1) {
                if (ck_v[0].value == "on") {
                    return;
                }
            }
            $.messager.confirm('创建', '确认要创建吗？', function (r) {
                if (r) {
                    var del_str = "";
                    $(ck_v).each(function (index) {
                        if (ck_v[index].value != "on") {
                            del_str = del_str + ck_v[index].value + ",";
                        }
                    });
                    del_str = del_str.substring(0, del_str.length - 1);
                    ajax_data($("#create_url").val() + "createclass.dhtml?id_key=" + del_str);
                }
            });
        }
    }
    document.oncontextmenu = function () {
        return false;
    }
</script>
<script type="text/javascript" src="js/inits.js"></script>
</html>
