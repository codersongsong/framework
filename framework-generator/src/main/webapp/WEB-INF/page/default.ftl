<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href='${Request["basePath"] ! ""}' />
	<title>项目自动化管理后台</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" id="norths" style="overflow:visible;">
		<div class="login-Bg">
			<div class="logo"></div>
			<div class="user-status" style="color:#ffffff"><br/>
			当前用户：管理员&nbsp;&nbsp;&nbsp;&nbsp;
			【<a href="login/refundLogin.dhtml"  style="color:#ffffff;text-decoration:none;">退出登录</a>】</div>
		</div>
		<div class="main-nav">
			<div class="main-nav-logo"></div>
			<div class="main-nav-box">
				<ul class="nav-box">
					<li class="nav-item click-cur" id="nav-item-dlsy">
						<a class="nav-item-primary" onClick="add_menu('登录首页','defaultshow/show.dhtml','dlsy',this)">登录首页</a>
					</li>
					<li class="nav-item" id="nav-item-1001">
						<a class="nav-item-primary">代码生成</a>
							<div class="dropbox" id="dropbox1001">
								<dl>
									<dt>项目管理<i class="sub-line"></i></dt>
									<dd>
										<a id="nav-bxsy" onclick="add_menu('主项目管理','projectbasicinfo/inits.dhtml','1002','dropbox1001')">主项目管理</a>
										<a id="nav-bxsy" onclick="add_menu('子项目管理','childprojectinfo/inits.dhtml','1003','dropbox1001')">子项目管理</a>
									</dd>
								</dl>
								<dl>
									<dt>持久层管理<i class="sub-line"></i></dt>
									<dd>
										<a id="nav-bxsy" onclick="add_menu('DAO持久层管理','tabledaoinfo/inits.dhtml','1004','dropbox1001')">DAO持久层管理</a>
										<a id="nav-bxsy" onclick="add_menu('任务代码生产','taskcodegenerator/inits.dhtml','1005','dropbox1001')">任务代码生产</a>
									</dd>
								</dl>
								<dl>
									<dt>服务层管理<i class="sub-line"></i></dt>
									<dd>
										<a id="nav-bxsy" onclick="add_menu('Dubbo服务层管理','dubboclasscreateconfig/inits.dhtml','1006','dropbox1001')">Dubbo服务管理</a>
										<a id="nav-bxsy" onclick="add_menu('API服务层管理','dubboclasscreateconfig/inits.dhtml','1007','dropbox1001')">API服务层管理</a>
									</dd>
								</dl>
                                <dl>
                                    <dt>依赖环境管理<i class="sub-line"></i></dt>
                                    <dd>
                                        <a id="nav-bxsy" onclick="add_menu('依赖管理','dependencyinfo/inits.dhtml','1008','dropbox1001')">依赖管理</a>
                                        <a id="nav-bxsy" onclick="add_menu('pom配置信息管理','pomprofileinfo/inits.dhtml','1009','dropbox1001')">pom配置信息管理</a>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>帮助<i class="sub-line"></i></dt>
                                    <dd>
                                        <a id="nav-bxsy" onclick="add_menu('BUG反馈','bugUpload/inits.dhtml','1010','dropbox1001')">BUG反馈</a>
                                    </dd>
                                </dl>
							</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center',border:false" id="center_div">
		<iframe scrolling="no" id="centerIframe-dlsy" frameborder="no" frameborder="0" marginwidth="0" marginheight="0"  src="defaultshow/show.dhtml" style="width:100%;"></iframe>
	</div>
	<div data-options="region:'south',border:false" class="default_menu" id="south_div">
		<div class="cur-item dlsy" id="dlsy_menu" parentMenuID="nav-item-dlsy">登录首页</div>
	</div>
</body>
<input type="text" id="height_default" value=""/>
<script type="text/javascript" src="js/default.js?a=bbabb"></script>
</html>