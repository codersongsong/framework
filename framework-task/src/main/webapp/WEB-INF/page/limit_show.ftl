<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>没有权限</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
<div style="background:#E0ECFF; border: 0.03cm #95B8E7 solid;width:250px;height:100px;padding:20px;margin-left: auto;margin-right: auto;margin-top:100px;">
	<table>
		<tr><td  width="20" height="60"></td><td  width="60">
		<div style="width: 44px; height: 44px; background: url(images/result_src.png) no-repeat -138px 0px;">&nbsp;<div>
		</td><td style="font-size:14px;">对不起，您没有此功能的权限，如需操作请联系管理员！</td></tr>
		<tr><td></td><td></td><td>
		<span onclick="javascript:parent.go_lists()" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;关&nbsp;&nbsp;闭&nbsp</span>
		</td></tr>
	</table>
</div>
</body>
</html>