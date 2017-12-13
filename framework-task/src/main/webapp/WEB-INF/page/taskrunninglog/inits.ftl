<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href='${Request["basePath"] ! ""}' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>任务运行日志表管理</title>
	<link rel="stylesheet" type="text/css" href="css/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/inits.css?a=b">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'west',border:false,width:'220px'" id="west_h" style="background-color:#e2e5ed;">
	<div class="search-form" id="searchClear">
		<input type="text" id="sysNo"  name="sysNo" class="easyui-combobox search-input" placeholder="请选择所属业务系统" data-options="inputId:'sysNo',required: true,editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key=SYS_NO'" >
		<div>
			<div style="height:28px;padding-top:8px;padding-left:8px;font-size:14px;">所属业务系统任务列表</div>
			<div id="muneLists">
				
			</div>
		</div>
	</div>
</div>

<div data-options="region:'center',border:false" style="background-color:#ededed">
	<div class="center-con">
	<div style="width:99%;height:28px;line-height:28px;background:#ea3d48;font-size:16px;color:#fff;font-family:Microsoft Yahei;font-weight:400;"><span  id="title_task_name" style="margin-left:10px;">任务名称</span></div>
	<div style="width:99%;font-family:Microsoft Yahei;" id="change_data">
		<table class="hovertable" width="100%" style="background-color:rgb(226, 229, 237)">
			<tr>
				<td width='7%'  height="28">任务名称：</td>
				<td width='15%' id="taskNo">&nbsp;</td>
				<td width='7%'>实例任务名称：</td>
				<td width='15%' id="objTaskName">&nbsp;</td>
				<td width='7%'>实例唯一标示：</td>
				<td width='49%' id="objectTal">&nbsp;</td>
			</tr>
			<tr>
				<td height="28">任务运行服务器：</td>
				<td id="taskRunServer_taskRunPort">&nbsp;</td>
				<td>上次运行时间：</td>
				<td id="preRunTime">&nbsp;</td>
				<td>最新运行时间：</td>
				<td id="nextRunTime">&nbsp;</td>
			</tr>
			<tr id="part_value_show">
				<td height="28">分片值：</td>
				<td>
					<input type="text"  id="partValue" name="partValue" value="" class="easyui-textbox"  data-options="">
				</td>
				<td>取模值：</td>
				<td colspan="3">
                    <input type="text"  id="modeValue" name="modeValue" value="" class="easyui-textbox"  data-options="">
                </td>
			</tr>
			<tr>
				<td colspan="6" height="28">
					<span onclick="updateInfo()" id="edit_buttons" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">&nbsp;修&nbsp;改&nbsp;</span>
					<span onclick="javascript:$('#change_data').hide();" class="easyui-linkbutton" data-options="iconCls:'icon-no'">&nbsp;关&nbsp;闭&nbsp;</span>
					
					<span id="icon_redo" onclick="updateTaskStatus('redo')" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">&nbsp;停&nbsp;止&nbsp;任&nbsp;务&nbsp;</span>&nbsp;
					<span id="icon_undo" onclick="updateTaskStatus('undo')" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">&nbsp;启&nbsp;动&nbsp;任&nbsp;务&nbsp;</span>&nbsp;
					<span id="icon_tip" onclick="updateTaskStatus('tip')" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">&nbsp;强&nbsp;行&nbsp;执&nbsp;行&nbsp;</span>&nbsp;
					
					<span id="mesagess" style="color:red"></span>
				</td>
			</tr>
		</table>
	</div>
	<table  id="tt"  style="width:99%;" url="taskrunninglog/search.dhtml" data-options="striped:true,rownumbers:true">
		<thead>
    		<tr>
	        	<th field="taskNo_colmun" width="200" align="left">任务名称</th>
	        	<th field="objTaskName_colmun" width="200" align="left">实例任务名称</th>
	        	<th field="objectTal_colmun" width="200" align="left">实例唯一标示</th>
	        	<th field="taskRunServer_colmun" width="120" align="left">任务运行服务器</th>
	        	<th field="taskRunPort_colmun" width="120" align="left">运行服务器端口</th>
	        	<th field="partValue_colmun" width="110" align="left">分片值</th>
	        	<th field="modeValue_colmun" width="110" align="left">取模值</th>
	        	<th field="preRunTime_colmun" width="110" align="left">上次运行时间</th>
	        	<th field="nextRunTime_colmun" width="110" align="left">最新运行时间</th>
	        	<th field="ddlStatus_colmun" width="100" align="left">运行状态</th>
	        	<th field="tddls" width="100" align="center" formatter="add_function" >操作功能</th>
    		</tr>
	    </thead>
	</table>
	<div>&nbsp;</div>
	</div>
</div>
<input type="hidden" id="url" value="taskrunninglog/" />
<input type="text" id="id_key" value=""/>
<input type="text" id="id_name" value=""/>
<input type="text" id="runNo" value=""/>
<input type="text" id="ddlStatus" value=""/>
<input type="text" id="status" value=""/>
<input type="text" id="fullTaskCount" value=""/>
</body>
<script type="text/javascript">
$('#sysNo').combobox({
	onSelect: function(param){
		var sysNo = param.id;
		$.ajax({  
        type:"POST",  
        url:"taskconfiginfo/selectv.dhtml?sysNo=" + sysNo,
        timeout:6000,
        dataType:'json',
        success:function(data){
           //处理保存完毕后处理
           $("#muneLists div").remove();
        	$(data.rows).each(function (index){
        		if(data.rows[index].status == 1){
        			$("#muneLists").append("<div class='taskNameClass' style='color:blue' onclick='openTaskMenu(&quot;"+data.rows[index].taskNo+"&quot;,&quot;"+data.rows[index].status+"&quot;,&quot;"+data.rows[index].fullTaskCount+"&quot;,&quot;"+data.rows[index].taskName+"&quot;)'>"+(index+1)+"、"+data.rows[index].taskName+"<div>");
        		}else{
        			$("#muneLists").append("<div class='taskNameClass' style='color:red' onclick='openTaskMenu(&quot;"+data.rows[index].taskNo+"&quot;,&quot;"+data.rows[index].status+"&quot;,&quot;"+data.rows[index].fullTaskCount+"&quot;,&quot;"+data.rows[index].taskName+"&quot;)'>"+(index+1)+"、"+data.rows[index].taskName+"<div>");
        		}
        		
        	});
        },
        error: function(){  
           alert('系统异常，请稍后重试！');  
        },
        complete : function(XMLHttpRequest,status){
        	if(status == "timeout"){
        		ajaxTimeoutTest.abort();
        		alert("加载数据超时！");
        	}
        }
     });
	}
});
function openTaskMenu(taskNo,status,fullTaskCount,taskNo_colmun){
	$("#status").val(status);
	$('#change_data').hide();
	$("#fullTaskCount").val(fullTaskCount);
	if(fullTaskCount == "1"){
		$('#part_value_show').hide();
	}else{
		$('#part_value_show').show();
	}
	
	$("#title_task_name").text("任务名称：" + taskNo_colmun);
	var dg = $('#tt');
    var queryParams =dg.datagrid('options').queryParams; 
    queryParams.taskNo= taskNo;
    dg.datagrid('options').queryParams = queryParams;  
    dg.datagrid('reload'); 
}
function add_function(val,row){ 
	if(row.ddlStatus == '040'){
		return "【<a href='javascript:void(0)' onclick='ajax_data(\"taskrunninglog/del.dhtml?id_key="+row.runNo+"\")' >删除历史记录</a>】";
	}else{
		return "【<a href='javascript:void(0)' onclick='editWins(\""+row.runNo+"\",\""+row.taskNo+"\",\""+row.objTaskName+"\",\""+row.objectTal+"\",\""+row.taskRunServer+"\",\""+row.taskRunPort+"\",\""+row.preRunTime+"\",\""+row.nextRunTime+"\",\""+row.partValue+"\",\""+row.ddlStatus+"\",\""+row.modeValue+"\")' >修改</a>】" +
				"【<a href='javascript:void(0)' onclick='executeOnceNow(\""+row.runNo+"\",\""+row.taskNo+"\",\""+row.objTaskName+"\",\""+row.objectTal+"\",\""+row.taskRunServer+"\",\""+row.taskRunPort+"\")' >立即执行一次</a>】";
	}
}
function executeOnceNow(runNo,taskNo,objTaskName,objectTal,taskRunServer,taskRunPort) {
    var url = "taskrunninglog/executeOnceNow.dhtml?runNo=" + runNo + "&taskNo=" + taskNo + "&objTaskName=" + objTaskName +
					"&objectTal="+ objectTal +"&taskRunServer="+taskRunServer +"&taskRunPort=" + taskRunPort;
    $.ajax({
        type:"POST",
        url:url,
        dataType:'json',
        success:function(data){
            //处理保存完毕后处理
            alert(data.message);
            $("#tt").datagrid('reload');
        },
        error: function(){
            alert('系统异常，请稍后重试！');
        },
        complete : function(XMLHttpRequest,status){
            if(status == "timeout"){
                ajaxTimeoutTest.abort();
                alert("加载数据超时！");
            }
        }
    });
}
function editWins(runNo,taskNo,objTaskName,objectTal,taskRunServer,taskRunPort,preRunTime,nextRunTime,partValue,ddlStatus,modeValue){
	$('#change_data').show();
	$("#mesagess").text("");
	$("#runNo").val(runNo);
	$("#taskNo").text(taskNo);
	$("#objTaskName").text(objTaskName);
	$("#objectTal").text(objectTal);
	$("#taskRunServer_taskRunPort").text(taskRunServer+":"+taskRunPort);
	$("#preRunTime").text(preRunTime);
	$("#nextRunTime").text(nextRunTime);
	$("#partValue").textbox("setValue",partValue);
	$("#ddlStatus").val(ddlStatus);
	$("#modeValue").textbox("setValue",modeValue);
	if("010" == ddlStatus){
		$("#icon_redo").show();
		$("#icon_undo").hide();
		$("#icon_tip").hide();
	}else if("020" == ddlStatus){
		$("#icon_redo").hide();
		$("#icon_undo").show();
		if($("#fullTaskCount").val() == "1"){
			$("#icon_tip").show();
		}else{
			$("#icon_tip").hide();
		}
	}else{
		$("#icon_redo").hide();
		$("#icon_undo").hide();
		$("#icon_tip").hide();
	}
}
function updateInfo(){
	var runNo = $("#runNo").val();
	var partValue = $("#partValue").textbox("getValue");
	var modeValue = $("#modeValue").textbox("getValue");
	var url = "taskrunninglog/edit.dhtml?runNo=" + runNo + "&partValue=" + partValue + "&modeValue=" + modeValue;
	$.ajax({  
        type:"POST",  
        url:url,
        timeout:6000,
        dataType:'json',
        success:function(data){
           //处理保存完毕后处理
        	$("#mesagess").text(data.message);
        	$("#tt").datagrid('reload');
        },
        error: function(){  
           alert('系统异常，请稍后重试！');  
        },
        complete : function(XMLHttpRequest,status){
        	if(status == "timeout"){
        		ajaxTimeoutTest.abort();
        		alert("加载数据超时！");
        	}
        }
     });
}
function updateTaskStatus(type){
	var runNo = $("#runNo").val();
	var url = "taskrunninglog/closerun.dhtml?id_key=" + runNo;
	if("redo" == type){
		url = url + "&type=CLOSE_TASK";
	}else if("undo" == type){
		url = url + "&type=RUN_TASK";
	}else if("tip" == type){
		url = url + "&type=ONE_TASK_SERVER_RUN";
	}
	$.ajax({  
    type:"POST",  
    url:url,
    timeout:6000,
    dataType:'json',
    success:function(data){
       //处理保存完毕后处理
    	$("#mesagess").text(data.message);
    	$("#tt").datagrid('reload');
    	$("#icon_redo").hide();
		$("#icon_undo").hide();
		$("#icon_tip").hide();
    },
    error: function(){  
       alert('系统异常，请稍后重试！');  
    },
    complete : function(XMLHttpRequest,status){
    	if(status == "timeout"){
    		ajaxTimeoutTest.abort();
    		alert("加载数据超时！");
    	}
    }
 });
}
$('#change_data').hide();
$('#part_value_show').hide();
</script>
<script type="text/javascript" src="js/inits.js"></script>
</html>
