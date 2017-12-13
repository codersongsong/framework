<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href='${Request["basePath"] ! ""}'/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>子项目信息配置表浏览</title>
    <link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/basic.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="div_content_heah">浏览数据--${enitty.childProjectName!''}</div>
<table class="hovertable" width="100%">
    <tr>
        <td width='100'>子项目编码：</td>
        <td>${enitty.childProjectCode!''}</td>
    </tr>
    <tr>
        <td width='100'>项目编码：</td>
        <td>${enitty.projectCode_colmun!''}</td>
    </tr>
    <tr>
        <td width='100'>项目名称：</td>
        <td>${enitty.childProjectName!''}</td>
    </tr>
    <tr>
        <td width='100'>项目英文名：</td>
        <td>${enitty.childProjectEng!''}</td>
    </tr>
    <tr>
        <td width='100'>项目类型：</td>
        <td>${enitty.projectType_colmun!''}</td>
    </tr>
    <tr>
        <td width='100'>打包类型：</td>
        <td>
        ${enitty.packageType_colmun!''}
        </td>
    </tr>
    <tr>
        <td width='100'>项目存放路径：</td>
        <td>${enitty.projectPath!''}</td>
    </tr>
    <tr>
        <td width='100'>版本配置文件名：</td>
        <td>${enitty.configFileName!''}</td>
    </tr>
    <tr>
        <td width='100'>环境版本配置：</td>
        <td>${enitty.versionConfig!''}</td>

    </tr>
    <tr>
        <td width='100'>基础依赖包：</td>
        <td><input id="basicJar" name="basicJar" value="${enitty.basicJar!''}" class="easyui-combobox"
                   style="width:500px;"
                   data-options="inputId:'type',multiple:true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=DEPENDENCY'">
        </td>
    </tr>
    <tr>
        <td width='100'>项目创建时间：</td>
        <td>${enitty.createTime!''}</td>
    </tr>
    <tr>
        <td colspan="2"><span onclick="colse_win('llsj_${enitty.childProjectCode?c}')" class="easyui-linkbutton"
                              data-options="iconCls:'icon-no'">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>
        </td>
    </tr>
</table>
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
</script>
</html>
