<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	//校验手机号的正则
	$.extend($.fn.validatebox.defaults.rules, {
		phoneNumber : {
			validator : function(value, param) {
				var phone = /^1[3|4|5|8][0-9]\d{4,8}/;
				return phone.test(value);
			},
			message : '手机号输入有误	!'
		}
	});

	function doAdd() {
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}

	function doView() {
		alert("查看...");
	}

	function doDelete() {
		alert("删除...");
	}

	function doRestore() {
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	} ];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'staffName',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'staffPhone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "1") {
				return "有";
			} else {
				return "无";
			}
		}
	}, {
		field : 'status',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "0") {
				return "正常使用"
			} else {
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所属单位',
		width : 200,
		align : 'center'
	} ] ];
	//设置分页栏的信息
	

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 取派员信息表格
		$('#grid').datagrid({
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList : [ 5, 7, 10 ],
			pagination : true,
			toolbar : toolbar,
			url : "",
			loadMsg: "数据加载中...",
			idField : 'id',
			columns : columns,
			queryParams : { pageFlag : 'true'},
			onDblClickRow : doDblClickRow
		});
		$('#grid').datagrid('getPager').pagination({  
			  
	        displayMsg:'当前显示第 {from}-{to} 条记录 ， 共 {total} 条记录'  
	  
	    });  

		// 添加取派员窗口
		$('#addStaffWindow').window({
			title : '添加取派员',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});
		//增加取派员
		$("#save").click(function() {
			if ($("#staffHaspda").attr("checked")) {
				//被选中，则赋值为1
				$("#staffHaspda").attr("value", "1");
			} else {
				$("#staffHaspda").attr("value", "0");
			}
			alert($("#staffHaspda").val());
			var v = $("#addForm").form("validate");
			if (v) {
				$("#addForm").submit();
			}
		})
		//查询取派员
		queryStaffAll();
		
		
		
		
		
		
		var pg = $("#grid").datagrid("getPager");    
		if(pg)    
		{    
		   $(pg).pagination({    
		       onBeforeRefresh:function(){    
		           alert('before refresh');    
		    },    
		       onRefresh:function(page,rows){    
		           alert(page);    
		           alert(rows);    
		        },    
		       onChangePageSize:function(){    
		           alert('pagesize changed');    
		        },    
		       onSelectPage:function(page,rows){    
		           alert(page);    
		           alert(rows);    
		        }    
		   });    
		} 
	});
	
	
	//查询所有的取派员
	function queryStaffAll() {
		var options = $("#grid").datagrid('getPager').data("pagination").options;
		options.pageSize = 5;//设定初始每页展示3条
		query(options);
	}
	//查询数据
	function query(options) {
		var page = options.pageNumber;//当前页数  
		var pageSize = options.pageSize;//每页的记录数（行数） 
		$.ajax({
			 url : "${pageContext.request.contextPath }/staff/queryAll.action",//路径 
			 type : "POST", //提交方式
			 data:{
				"page":page, 
				"pageSize":pageSize, 
				"pageFlag":"true"
			 },
			 success : function(result){
				 //将数据塞到数据表格中
				 var data=JSON.parse(result);
				 if (data.returnCode=="0") {
					 $("#grid").datagrid("loadData",data.bean);
				}
			 }
		});
	}
	function doDblClickRow(rowIndex, rowData) {
		alert("双击表格数据...");
	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;"
			split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
			</div>
		</div>

		<div region="center" style="overflow: auto; padding: 5px;"
			border="false">
			<form id="addForm" action="${pageContext.request.contextPath }/staff/add.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>取派员编号</td>
						<td><input type="text" name="staffId"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="staffName" class="easyui-validatebox"
							required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" validType="phoneNumber" name="staffPhone"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td colspan="2"><input id="staffHaspda" type="checkbox"
							name="haspda"  /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard"
							class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
