<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href='${Request["basePath"] ! ""}'/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>DUBBO服务类生产配置编辑</title>
    <link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/basic.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/jquery.form.min.js"></script>
</head>
<body>
<#if resultType = 'add'>
<div class="div_content_heah">添加数据</div>
<form id="ff" class="easyui-form" action='dubboclasscreateconfig/save.dhtml' method="POST"
      data-options="novalidate:true">
    <table class="hovertable" style="background-color:rgb(226, 229, 237)" width="100%">
        <tr>
            <td width='100'>子项目编码：</td>
            <td>
                <input type="text" id="childProjectCode" name="childProjectCode" class="easyui-combobox"
                       data-options="inputId:'childProjectCode',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=CHILD_PROJECT_CODE'">
            </td>
        </tr>
        <tr>
            <td width='100'>生成类型：</td>
            <td>
                <input type="text" id="createType" name="createType" class="easyui-combobox"
                       data-options="inputId:'createType',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=CREATE_TYPE'">
            </td>
        </tr>
        <tr>
            <td width='100'>DUBBO服务接口类：</td>
            <td>
                <input type="text" id="dubboClass" name="dubboClass" value="" class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务接口类功能说明：</td>
            <td>
                <input type="text" id="dubboClassAsk" name="dubboClassAsk" value="" class="easyui-textbox"
                       data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>DUBBO服务接口方法：</td>
            <td>
                <input type="text" id="dubboClassMethod" name="dubboClassMethod" value="" class="easyui-textbox"
                       data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务接口方法说明：</td>
            <td>
                <input type="text" id="dubboClassMethodAsk" name="dubboClassMethodAsk" value="" class="easyui-textbox"
                       data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务层接口类：</td>
            <td>
                <input type="text" id="serviceCalss" name="serviceCalss" value="" class="easyui-textbox"
                       data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务层接口类功能说明：</td>
            <td>
                <input type="text" id="serviceCalssAsk" name="serviceCalssAsk" value="" class="easyui-textbox"
                       data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务层接口方法：</td>
            <td>
                <input type="text" id="serviceCalssMethod" name="serviceCalssMethod" value="" class="easyui-textbox"
                       data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务层接口方法功能说明：</td>
            <td>
                <input type="text" id="serviceCalssMethodAsk" name="serviceCalssMethodAsk" value=""
                       class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>业务层类名称：</td>
            <td>
                <input type="text" id="logicCalss" name="logicCalss" value="" class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>业务层类功能说明：</td>
            <td>
                <input type="text" id="logicCalssAsk" name="logicCalssAsk" value="" class="easyui-textbox"
                       data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>保存地址：</td>
            <td>
                <input type="text" id="saveAddress" name="saveAddress" value="" class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}" id="add_buttons"
                      class="easyui-linkbutton" data-options="iconCls:'icon-add'">&nbsp;需&nbsp;要&nbsp;保&nbsp;存&nbsp;吗&nbsp;？&nbsp; </span>&nbsp;
                <span onclick="javascript:parent.go_lists()" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;去&nbsp;列&nbsp;表&nbsp;页&nbsp;？&nbsp;</span>
            </td>
        </tr>
    </table>
</form>
</#if>

<#if resultType = 'edit'>
<div class="div_content_heah">修改数据--${enitty.classNo!''}</div>
<form id="ff" class="easyui-form" action='dubboclasscreateconfig/edit.dhtml' method="POST"
      data-options="novalidate:true">
    <input type="hidden" id="classNo" name="classNo" value="${enitty.classNo?c}">
    <table class="hovertable" width="100%">
        <tr>
            <td width='100'>子项目编码：</td>
            <td>
                <input type="text" id="childProjectCode" value="${enitty.childProjectCode!''}" name="childProjectCode"
                       class="easyui-combobox"
                       data-options="inputId:'childProjectCode',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=CHILD_PROJECT_CODE'"
                       disabled>
            </td>
        </tr>
        <tr>
            <td width='100'>生成类型：</td>
            <td>
                <input type="text" id="createType" value="${enitty.createType!''}" name="createType"
                       class="easyui-combobox"
                       data-options="inputId:'createType',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=CREATE_TYPE'"
                       disabled>
            </td>
        </tr>
        <tr>
            <td width='100'>DUBBO服务接口类：</td>
            <td>
                <input type="text" id="dubboClass" name="dubboClass" value="${enitty.dubboClass!''}"
                       class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务接口类功能说明：</td>
            <td>
                <input type="text" id="dubboClassAsk" name="dubboClassAsk" value="${enitty.dubboClassAsk!''}"
                       class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>DUBBO服务接口方法：</td>
            <td>
                <input type="text" id="dubboClassMethod" name="dubboClassMethod" value="${enitty.dubboClassMethod!''}"
                       class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务接口方法说明：</td>
            <td>
                <input type="text" id="dubboClassMethodAsk" name="dubboClassMethodAsk"
                       value="${enitty.dubboClassMethodAsk!''}" class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务层接口类：</td>
            <td>
                <input type="text" id="serviceCalss" name="serviceCalss" value="${enitty.serviceCalss!''}"
                       class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务层接口类功能说明：</td>
            <td>
                <input type="text" id="serviceCalssAsk" name="serviceCalssAsk" value="${enitty.serviceCalssAsk!''}"
                       class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务层接口方法：</td>
            <td>
                <input type="text" id="serviceCalssMethod" name="serviceCalssMethod"
                       value="${enitty.serviceCalssMethod!''}" class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>服务层接口方法功能说明：</td>
            <td>
                <input type="text" id="serviceCalssMethodAsk" name="serviceCalssMethodAsk"
                       value="${enitty.serviceCalssMethodAsk!''}" class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>业务层类名称：</td>
            <td>
                <input type="text" id="logicCalss" name="logicCalss" value="${enitty.logicCalss!''}"
                       class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>业务层类功能说明：</td>
            <td>
                <input type="text" id="logicCalssAsk" name="logicCalssAsk" value="${enitty.logicCalssAsk!''}"
                       class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td width='100'>保存地址：</td>
            <td>
                <input type="text" id="saveAddress" name="saveAddress" value="${enitty.saveAddress!''}"
                       class="easyui-textbox" data-options="">
            </td>
        </tr>
        <tr>
            <td colspan="2"><span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}"
                                  id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>
                <span onclick="colse_win('xgsj_${enitty.classNo?c}')" class="easyui-linkbutton"
                      data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
                &nbsp;&nbsp;<font color="red">${messages!""}</font>
            </td>
        </tr>
    </table>
</#if>
<#if resultType = 'view'>
    <div class="div_content_heah">浏览数据--${enitty.classNo!''}</div>
    <table class="hovertable" width="100%">
        <tr>
            <td width='100'>类序号：</td>
            <td>${enitty.classNo!''}</td>
        </tr>
        <tr>
            <td width='100'>子项目编码：</td>
            <td>${enitty.childProjectCode!''}</td>
        </tr>
        <tr>
            <td width='100'>生成类型：</td>
            <td>${enitty.createType!''}</td>
        </tr>
        <tr>
            <td width='100'>DUBBO服务接口类：</td>
            <td>${enitty.dubboClass!''}</td>
        </tr>
        <tr>
            <td width='100'>服务接口类功能说明：</td>
            <td>${enitty.dubboClassAsk!''}</td>
        </tr>
        <tr>
            <td width='100'>DUBBO服务接口方法：</td>
            <td>${enitty.dubboClassMethod!''}</td>
        </tr>
        <tr>
            <td width='100'>服务接口方法说明：</td>
            <td>${enitty.dubboClassMethodAsk!''}</td>
        </tr>
        <tr>
            <td width='100'>服务层接口类：</td>
            <td>${enitty.serviceCalss!''}</td>
        </tr>
        <tr>
            <td width='100'>服务层接口类功能说明：</td>
            <td>${enitty.serviceCalssAsk!''}</td>
        </tr>
        <tr>
            <td width='100'>服务层接口方法：</td>
            <td>${enitty.serviceCalssMethod!''}</td>
        </tr>
        <tr>
            <td width='100'>服务层接口方法功能说明：</td>
            <td>${enitty.serviceCalssMethodAsk!''}</td>
        </tr>
        <tr>
            <td width='100'>业务层类名称：</td>
            <td>${enitty.logicCalss!''}</td>
        </tr>
        <tr>
            <td width='100'>业务层类功能说明：</td>
            <td>${enitty.logicCalssAsk!''}</td>
        </tr>
        <tr>
            <td width='100'>保存地址：</td>
            <td>${enitty.saveAddress!''}</td>
        </tr>
        <tr>
            <td width='100'>添加时间：</td>
            <td>${enitty.createTime!''}</td>
        </tr>
        <tr>
            <td width='100'>修改时间：</td>
            <td>${enitty.upTime!''}</td>
        </tr>
        <tr>
            <td colspan="2"><span onclick="colse_win('llsj_${enitty.classNo?c}')" class="easyui-linkbutton"
                                  data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
            </td>
        </tr>
    </table>
</#if>
<#if resultType = 'edit'>
    <script type="text/javascript">
    </script>
</#if>
<#if resultType = 'import'>
    <div class="div_content_heah">导入Excel</div>
    <form method="post" action="dubboclasscreateconfig/saveExcel.dhtml" id="form1" enctype="multipart/form-data">
        <table>
            <tr>
                <td width='100'>子项目编码：</td>
                <td>
                    <input type="text" id="childProjectCode" name="childProjectCode" class="easyui-combobox"
                           data-options="inputId:'childProjectCode',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=CHILD_PROJECT_CODE'">
                </td>
            </tr>
            <tr>
                <td class="td_1">导入Excel:</td><br>
                <td><input type="text" class="easyui-filebox" id="load_file_name" name="load_file_name"
                           style="width:300px;"></td><br>
                <td><span class="easyui-linkbutton btn" id="ajaxSubmit" onclick="to();">&nbsp;保&nbsp;存&nbsp;文&nbsp;件&nbsp;</span></td>
                <td class="td_1"></td><br>
                <td class="td_1"></td><br>
                <td class="td_1">Excel模板:</td><br>
                <td class="td_1" ><a href="file/InterfaceExample.xlsx" >InterfaceExample.xlsx</a></td><br>
                <#--<td class="td_1" ><a href="javascript:void(0);" onclick="interfaceExample()">InterfaceExample.xlsx</a></td><br>-->
            </tr>
        </table>
    </form>
</#if>
</body>
<script type="text/javascript">
    $(function () {
        $('.validatebox-text').bind('blur', function () {
            $(this).validatebox('enableValidation').validatebox('validate');
        });

    })
    function interfaceExample() {
        $.post("/dubboclasscreate/getInterfaceExample.dhtml");
    }
    function colse_win(id) {
        parent.colse_win(id);
    }
    function to() {
        debugger
        var load_file_name = $("#load_file_name").textbox("getValue");
        if (load_file_name == "") {
            alert("请选择上传文件");
        } else {
            var fileName = $('#load_file_name').textbox("getValue");
            var pos = fileName.lastIndexOf(".");
            var lastname = fileName.substring(pos, fileName.length);
            if (lastname.toLowerCase() != ".xls" && lastname.toLowerCase() != ".xlsx") {
                alert("必须上传excel格式的文件！");
            } else {
                $("#ajaxSubmit").removeAttr("onclick");//删除onclick属性
                $("#ajaxSubmit").removeClass("btn").addClass("btn-grey");
                $("#form1").ajaxSubmit({
                    type: 'post',
                    url: 'dubboclasscreateconfig/saveExcel.dhtml',
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == "success") {
                            alert("上传成功");
                            $("#ajaxSubmit").attr("onclick", "to();");
                            $("#ajaxSubmit").removeClass("btn-grey").addClass("btn");
                            //parent.location.reload();
                        } else {
                            alert(data.message);
                            $("#ajaxSubmit").attr("onclick", "to();");
                            $("#ajaxSubmit").removeClass("btn-grey").addClass("btn");
                            //parent.location.reload();
                        }
                    },
                    error: function (XmlHttpRequest, textStatus, errorThrown) {
                        alert("系统异常");
                        $("#ajaxSubmit").attr("onclick", "to();");
                        $("#ajaxSubmit").removeClass("btn-grey").addClass("btn");
                    }
                });
            }
        }
    }
</script>
</html>
