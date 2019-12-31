<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>课堂教学评价系统</title>
<!-- JS+CSS引入 -->
<jsp:include page="/WEB-INF/common/include/public.jsp"></jsp:include>
<style>
.tit {
	color: white;
	font-size: 20px;
}

.nav-header {
	padding: 11px 25px
}
#content-main{
		height: calc(100% - 93px);
	}
</style>
</head>

<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header"><span class="logo-mini"><img
							width="50px" height="42px"
							src="${cssPath}/css/patterns/logo.png"></img></span> <span class="tit">课堂教学评价系统</span>
					</li>

					<c:forEach var="menu" items="${menuList}" varStatus="menuStatus">
						<c:if test="${menu.children == null}">
							<li>
								<a class="J_menuItem" href="${ctx}/${menu.url}">
									<i class="${menu.icon}"></i>
									<span class="nav-label">${menu.name}</span>
								</a>
							</li>
						</c:if>
						<c:if test="${menu.children != null}">
							<li>
								<a href="#"><i class="${menu.icon}"></i>
									<span class="nav-label">${menu.name}</span>
									<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<c:forEach var="child" items="${menu.children}" varStatus="childStatus">
										<li>
											<a class="J_menuItem" href="${ctx}/${child.url}">${child.name}</a>
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>

					<%--<li>--%>
						<%--<a class="J_menuItem" href="${ctx}/admin/professor/list">--%>
							<%--<i class="fa fa-group"></i>--%>
							<%--<span class="nav-label">督导专家</span>--%>
						<%--</a>--%>
					<%--</li>--%>
					<%--<li>--%>
						<%--<a href="#"><i class="fa fa-flask"></i>--%>
							<%--<span class="nav-label">教学指标</span>--%>
							<%--<span class="fa arrow"></span></a>--%>
						<%--<ul class="nav nav-second-level">--%>
							<%--<li>--%>
								<%--<a class="J_menuItem" href="${ctx}/admin/questionGroup/list">指标分类</a>--%>
							<%--</li>--%>
							<%--<li>--%>
								<%--<a class="J_menuItem" href="${ctx}/admin/question/list">评价指标</a>--%>
							<%--</li>--%>
						<%--</ul>--%>
					<%--</li>--%>

					<%--<li><a class="J_menuItem" href="${ctx}/admin/paper/list"><i--%>
							<%--class="fa fa-table"></i> <span class="nav-label">调查问卷</span></a></li>--%>
					<%--<li><a class="J_menuItem" href="${ctx}/admin/standard/list"><i--%>
							<%--class="fa fa-columns"></i> <span class="nav-label">评价标准</span></a></li>--%>
					<%--<li><a class="J_menuItem" href="${ctx}/admin/exam/list"><i--%>
							<%--class="fa fa-file-excel-o"></i> <span class="nav-label">评价方案</span></a></li>--%>
					<%--<li><a class="J_menuItem" href="${ctx}/admin/examPlan/list"><i--%>
							<%--class="fa fa-calendar-check-o"></i> <span class="nav-label">评价安排</span></a></li>--%>
					<%--<li><a class="J_menuItem" href="${ctx}/admin/userexam/list"><i--%>
							<%--class="fa fa-columns"></i> <span class="nav-label">我的问卷</span></a></li>--%>
					<%--<li><a class="J_menuItem" href="${ctx}/admin/sso/user/list"><i--%>
							<%--class="fa fa-columns"></i> <span class="nav-label">用户</span></a></li>--%>
					<%--<li><a class="J_menuItem" href="${ctx}/admin/sso/role/list"><i--%>
							<%--class="fa fa-columns"></i> <span class="nav-label">角色</span></a></li>--%>
					<%--<li><a class="J_menuItem" href="${ctx}/admin/sso/permission/list"><i--%>
							<%--class="fa fa-columns"></i> <span class="nav-label">权限</span></a></li>--%>
				</ul>
			</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2" href="#"><i
							class="fa fa-bars" style="color: #fff;"></i> </a>
                        <button class="btn btn-default dim " style="margin-bottom: 0px !important;margin-top: 3px" onclick="notice();" type="button"> <i class="fa fa-user-secret"></i>
                        </button>
                        <!-- 
                         <marquee id="messageMarquee"
                          onMouseOut="this.start()" onMouseOver="this.stop()" 
                          behavior="scroll" direction="left" align="bottom">
                          </marquee>
                        -->
					</div>
				</nav>
			</div>
			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="#" class="active J_menuTab" data-id="index_content">首页</a>
					</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>

					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<a href="${ctx}/logout" class="roll-nav roll-right J_tabExit"><i
					class="fa fa fa-sign-out"></i> 退出</a>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="${ctx}/admin/first" frameborder="0" data-id="index_content" seamless></iframe>
			</div>
			<%--<div class="footer">--%>
				<%--<div class="pull-right hidden-xs">--%>
					<%--<b>Version</b> 1.0.0--%>
				<%--</div>--%>
				<%--<strong>技术支持单位:<a href="https://www.founderbd.com"--%>
					<%--target="_blank">安徽方正北斗电子科技有限公司 2013-2018</a></strong>--%>
			<%--</div>--%>
		</div>
		<!--右侧部分结束-->
	</div>
	<!-- 消息 -->
	<div class="modal inmodal fade" id="messageModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content col-md-11 modal-new-dialog">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true"></span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modal-title">详情</h4>
				</div>
				<div class="ibox float-e-margins modal-body">
					<div class="ibox-content" style="margin-bottom: 0;">
						<form class="form-horizontal m-t" id="addForm" method="post">
							<div class="tab-content">
								<div id="tab-1" class="tab-pane active modal-content-wrap">
									<input type="hidden" name="id" value="">
									<div class="form-group col-sm-12">
										<label class="col-sm-2 control-label">编号：</label>
										<div class="col-sm-4">
											<input name="code" maxlength="20" class="form-control"
												type="text" required="required" placeholder="自动生成"
												aria-required="true" aria-invalid="false"
												onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-a-z-A-Z]+/,'');}).call(this)"
												onblur="this.v();">
										</div>
										<label class="col-sm-2 control-label"><span
											class="required" style="color: #ff0000">*</span>&nbsp;名称：</label>
										<div class="col-sm-4">
											<input name="name" maxlength="100" class="form-control"
												type="text" required="required">
										</div>
									</div>
								</div>
							</div>
						</form>
						<div class="clear"></div>
					</div>
				</div>
				<div class="modal-footer modal-footer-bar">
					<button id="entityBtnAdd" class="bk-margin-5 btn btn-info">保存</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
    //五分钟
    var timeInterval = 5*60*1000;
	$(function () {
		init();
		myMessageInterval();
	});

	init = function () {
		var roleId = '${roleId}';
		if (roleId == null || roleId == '' || roleId.trim() == '') {
			notice();
		}
	}

	notice = function(){
		$.request("${ctx}/getRoleList", "post",{}, function (data) {
			if (data == null || data == '') {
				layer.msg("当前用户无角色权限！", {time: 1000});
			} else {
				var btn = [];
				var btnId = [];
				$.each(data, function (index, role) {
					btn.push(role.rolename);
					btnId.push(role.id);
				})
				layer.closeAll();
				layer.open({
					type: 1
					,title: "选择角色"
					,closeBtn: false
					,area: '300px;'
					,shade: 0.8
					,id: 'LAY_layuipro'
					,btn: btn
					,btnAlign: 'c'
					,moveType: 1 //拖拽模式，0或者1
					,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">&nbsp;欢迎进入评价系统！<br>请选择一个角色进入系统 ^_^</div>'
					,success: function(layero){
						var array = $(".layui-layer-btn a");
						for (var i = 0; i < array.length; i++) {
							array[i].style='background: #0071ce;width: 70px;height: 20px;color: #fff;border: 0px;margin: 2px;margin-left: 11px;margin-right: 23px;',
									(function(n){ //这个是function里n，即function的形参，也可以换成j，换成什么变量名都无所谓
										$(".layui-layer-btn .layui-layer-btn" + i).click(function(){setRole(btnId[n]);})
									})(i);
						}
					}
				});
			}
		});
	}

	setRole = function(roleId){
		$.request("${ctx}/setRole", "post", {roleId:roleId}, function (data) {
			if (data == '1') {
				layer.msg("选择成功", {time: 1000},function(){
					window.location.reload();
				});
			} else {
				layer.msg("选择失败", {time: 1000},function(){
					window.location.reload();
				});
			}
		});
	}
	
	myMessageInterval = function(){
	   setInterval("myUnreadMessage();", timeInterval);
	}
	
	myUnreadMessage = function(){
		$.request("${ctx}/admin/message/myUnreadMessage", "post", {}, function (data) {
			var html = "";
			for (var i = 0; i < data.length; i++) {
				var d = data[i];
				var time = dateFormatShow(d.createTime,'yyyy-MM-dd hh:mm');
				html += "<a href='#' onclick=\"readMessage('"+d.id+"');\">***在"+time+"对您进行了测评，请查看评价详情。</a>";
				html += "&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			//$("#messageMarquee").html(html);
		});
	}
	
	readMessage = function(id){
        $.request("${ctx}/admin/message/getById", "post", {id:id}, function (data) {
			$('#messageModal').modal('show');
			//updateMessageRead(id);
		});
	}
	
	/**
	*读取状态位
	*/
	updateMessageRead = function(id){
        $.request("${ctx}/admin/message/updateMessageRead", "post", {id:id,flag:'1'}, function (data) {
		});
	}

</script>

</html>
