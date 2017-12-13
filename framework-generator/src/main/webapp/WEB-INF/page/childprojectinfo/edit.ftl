<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href='${Request["basePath"] ! ""}'/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>子项目信息配置表编辑</title>
    <link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/basic.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="div_content_heah">修改数据--${enitty.childProjectName!''}</div>
<form id="ff" class="easyui-form" action='childprojectinfo/edit.dhtml' method="POST" data-options="novalidate:true">
    <input type="hidden" id="childProjectCode" name="childProjectCode" value="${enitty.childProjectCode?c}">
    <table class="hovertable" width="100%">
        <tr>
            <td width='120'>主项目名称：</td>
            <td>
                <input type="text" id="projectCode" name="projectCode" value="${enitty.projectCode?c}"
                       placeholder="请选择主项目" class="easyui-combobox search-input" style="width:260px;"
                       data-options="required: true,inputId:'projectCode',editable:false,disabled:true,valueField:'projectCode',textField:'projectName',url:'projectbasicinfo/select.dhtml'">
            </td>
        </tr>
        <tr>
            <td width='100'>子项目名称：</td>
            <td>
                <input type="text" id="childProjectName" name="childProjectName" value="${enitty.childProjectName!''}"
                       class="easyui-textbox" data-options="required: true," style="width:260px;">
            </td>
        </tr>
        <tr>
            <td width='100'>子项目英文名：</td>
            <td>
                <input type="text" id="childProjectEng" name="childProjectEng" value="${enitty.childProjectEng!''}"
                       class="easyui-textbox" data-options="required: true," style="width:260px;">
            </td>
        </tr>
        <tr>
            <td width='100'>子项目类型：</td>
            <td>
                <input type="text" id="projectType" value="${enitty.projectType!''}" name="projectType"
                       placeholder="请选择子项目类型" class="easyui-combobox" style="width:260px;"
                       data-options="required: true,inputId:'projectType',editable:false,valueField: 'value',textField: 'label',data:[{label: '持久层',value: '010'},{label: '前端WEB',value: '020'},{label: 'API接口',value: '030'},{label: 'DUBBO服务',value: '040'},{label: '定时任务',value: '050'},{label: '后台管理',value: '060'},{label: '其他',value: '999'}]">
            </td>
        </tr>
        <tr>
            <td width='100'>打包类型：</td>
            <td>
                <input type="text" id="packageType" value="${enitty.packageType!''}" name="packageType"
                       placeholder="请选择打包类型" class="easyui-combobox" style="width:260px;"
                       data-options="required: true,inputId:'packageType',editable:false,valueField: 'value',textField: 'label',data:[{label: 'war',value: 'war'},{label: 'jar',value: 'jar'},{label: 'pom',value: 'pom'}]">
            </td>
        </tr>
        <tr>
            <td width='100'>项目存放路径：</td>
            <td>
                <input type="text" id="projectPath" name="projectPath" value="${enitty.projectPath!''}"
                       class="easyui-textbox" data-options="" style="width:260px;">
            </td>
        </tr>
        <tr>
            <td width='100'>版本配置文件名：</td>
            <td>
                <input type="text" id="configFileName" name="configFileName" value="${enitty.configFileName!''}"
                       class="easyui-textbox" data-options="" style="width:260px;">&nbsp;&nbsp;（例：login-admin.properties）
            </td>
        </tr>
        <tr>
            <td width='100'>基础依赖包：</td>
            <td>
                <input id="basicJar" name="basicJar" value="${enitty.basicJar!''}" class="easyui-combobox"
                       style="width:500px;"
                       data-options="inputId:'type',multiple:true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=DEPENDENCY'">
            </td>
        </tr>
        <tr>
            <td width='100'>环境版本配置：</td>
            <td>
                <input id="versionConfig" name="versionConfig" value="${enitty.versionConfig!''}"
                       class="easyui-combobox" style="width:500px;"
                       data-options="inputId:'type',multiple:true,editable:false,disabled: false,valueField: 'id',textField: 'configDescription',panelHeight: 'auto',prompt: '请选择配置信息',url: 'childprojectinfo/queryPomProfiles.dhtml?projectCode=${enitty.projectCode?c}'">
            </td>
        </tr>
        <tr>
            <td colspan="2"><span onclick="if($('#ff').form('enableValidation').form('validate')){ff.submit()}"
                                  id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>
                <span onclick="colse_win('xgsj_${enitty.childProjectCode?c}')" class="easyui-linkbutton"
                      data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
                &nbsp;&nbsp;<font color="red">${messages!""}</font>
            </td>
        </tr>
    </table>
    <script type="text/javascript">
    </script>
</body>
<script type="text/javascript">
    $(function () {
        $('.validatebox-text').bind('blur', function () {
            $(this).validatebox('enableValidation').validatebox('validate');
        });
    })

    function colse_win(id) {
        parent.colse_win(id);
    }

    function onSelect() {
        $('#versionConfig').combobox({
            url: 'childprojectinfo/queryPomProfiles.dhtml?projectCode=' + param.projectCode,
            disabled: false,
            valueField: 'id',
            textField: 'configDescription',
            panelHeight: 'auto',
            prompt: '请选择配置信息'
        });
    }
</script>
</html>
