<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

	<title>Login Page - Ace Admin</title>

	<meta name="description" content="User login page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="statics/css/bootstrap.min.css">
	<link rel="stylesheet" href="statics/css/font-awesome.min.css">

	<!-- text fonts -->
	<link rel="stylesheet" href="statics/css/fonts.googleapis.com.css">

	<!-- ace styles -->
	<link rel="stylesheet" href="statics/css/ace.min.css">

	<!--[if lte IE 9]>
	<link rel="stylesheet" href="assets/css/ace-part2.min.css" />
	<![endif]-->
	<link rel="stylesheet" href="statics/css/ace-rtl.min.css">

	<!--[if lte IE 9]>
	<link rel="stylesheet" href="assets/css/ace-ie.min.css" />
	<![endif]-->

	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src="assets/js/html5shiv.min.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body class="login-layout light-login">
<div class="main-container">
	<div class="main-content">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<div class="login-container">
					<div class="center">
						<h1>
							<i class="ace-icon fa fa-leaf green"></i>
							<span class="red">Ace</span>
							<span class="grey" id="id-text2">Application</span>
						</h1>
						<!--
                        <h4 class="blue" id="id-company-text">© Company Name</h4>
                        -->
					</div>

					<div class="space-6"></div>

					<div class="position-relative">
						<div id="login-box" class="login-box visible widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">

                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i>
                                        mine-cms
                                    </h4>

									<div class="space-6">1111</div>

									<form id="loginForm" method="post" action="<%=request.getContextPath()%>/login">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

										<%String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);%>
										<c:set var="exp_type" value="<%=error%>" scope="request"/>

										<c:if test="${fn:contains(exp_type,'UnknownAccountException')}">
											<div id="authen-tips" class="has-error help-block">
												验证码错误
											</div>
										</c:if>
										<%--&lt;%&ndash;<c:set var="tips" value=""></c:set>&ndash;%&gt;--%>
										<%--<c:if test="${fn:contains(error,'UnknownAccountException')}">--%>
											<%--<div class="help-block">--%>
												<%--验证码错误--%>
											<%--</div>--%>
											<%--&lt;%&ndash;<c:set var="tips" value="验证码错误"></c:set>&ndash;%&gt;--%>
										<%--</c:if>--%>

										<fieldset>
											<label class="block clearfix has-info">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" name="username" id="username" placeholder="帐号">
															<i class="ace-icon fa fa-user"></i>
														</span>
											</label>

											<label class="block clearfix has-info">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="password" id="password" placeholder="密码">
															<i class="ace-icon fa fa-lock"></i>
														</span>
											</label>


											<div class="space"></div>

											<div class="clearfix">
												<label class="inline">
													<input type="checkbox" class="ace" name="rememberMe">
													<span class="lbl"> 记住我</span>
												</label>

												<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
													<i class="ace-icon fa fa-key"></i>
													<span class="bigger-110">登录</span>
												</button>
											</div>

											<div class="space-4"></div>
										</fieldset>
									</form>

									<!--
                                    <div class="social-or-login center">
                                        <span class="bigger-110">Or Login Using</span>
                                    </div>
                                    -->

									<div class="space-6"></div>

									<!--
                                    <div class="social-login center">
                                        <a class="btn btn-primary">
                                            <i class="ace-icon fa fa-facebook"></i>
                                        </a>

                                        <a class="btn btn-info">
                                            <i class="ace-icon fa fa-twitter"></i>
                                        </a>

                                        <a class="btn btn-danger">
                                            <i class="ace-icon fa fa-google-plus"></i>
                                        </a>
                                    </div>
                                    -->
								</div><!-- /.widget-main -->

							</div><!-- /.widget-body -->
						</div><!-- /.login-box -->


					</div><!-- /.position-relative -->

					<div class="navbar-fixed-top align-right">
						<br>
						&nbsp;
						<a id="btn-login-dark" href="#">Dark</a>
						&nbsp;
						<span class="blue">/</span>
						&nbsp;
						<a id="btn-login-blur" href="#">Blur</a>
						&nbsp;
						<span class="blue">/</span>
						&nbsp;
						<a id="btn-login-light" href="#">Light</a>
						&nbsp; &nbsp; &nbsp;
					</div>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.main-content -->
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
</script><script type="text/javascript" async="" src="statics/js/watch.js"></script><script src="statics/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>

<script src="statics/js/validate/jquery.validate.js"></script>
<script src="statics/js/validate/additional-methods.js"></script>
<script src="statics/js/validate/localization/messages_zh.js"></script>
<script src="statics/js/jquery.form.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
	jQuery(function($) {
		$(document).on('click', '.toolbar a[data-target]', function(e) {
			e.preventDefault();
			var target = $(this).data('target');
			$('.widget-box.visible').removeClass('visible');//hide others
			$(target).addClass('visible');//show target
		});
	});

	<!-- crsf token 在提交 ajax 的时候 加入  -->
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
		beforeSend: function (xhr) {
			if(header && token ){
				xhr.setRequestHeader(header, token);
			}
		}
	});
	<!-- crsf end-->

	//you don't need this, just used for changing background
	jQuery(function($) {



		$('#loginForm').validate({
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: false,
			//ignore: "",
			rules: {
				username: {
					required: true,
					minlength: 3
				},
				password: {
					required: true,
					minlength: 3
				}

			},
			messages: {
				username: {
					required: "帐号不能为空."
				},
				password: {
					required: "密码不能为空."
				}
			},
//			submitHandler: function(form) {
//				debugger;
//				$(form).ajaxSubmit();
//
//				return false;
//			},

			highlight: function (e) {
				// block clearfix
				$(e).closest('.clearfix').removeClass('has-info').addClass('has-error');
			},

			success: function (e) {
				$(e).closest('.clearfix').removeClass('has-error');//.addClass('has-info');
				$(e).remove();
			},
			errorPlacement: function (error, element) {
				if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
					var controls = element.closest('div[class*="col-"]');
					if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
					else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
				}
				else if(element.is('.select2')) {
					error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
				}
				else if(element.is('.chosen-select')) {
					error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
				}
				else error.insertAfter(element.parent());
			}
//			,
//			submitHandler: function (form) {
//			},
//			invalidHandler: function (form) {
//			}
		});


		$('#btn-login-dark').on('click', function(e) {
			$('body').attr('class', 'login-layout');
			$('#id-text2').attr('class', 'white');
			$('#id-company-text').attr('class', 'blue');

			e.preventDefault();
		});
		$('#btn-login-light').on('click', function(e) {
			$('body').attr('class', 'login-layout light-login');
			$('#id-text2').attr('class', 'grey');
			$('#id-company-text').attr('class', 'blue');

			e.preventDefault();
		});
		$('#btn-login-blur').on('click', function(e) {
			$('body').attr('class', 'login-layout blur-login');
			$('#id-text2').attr('class', 'white');
			$('#id-company-text').attr('class', 'light-blue');

			e.preventDefault();
		});

	});
</script>

<script type="text/javascript">

</script>


<script>

</script>


</body></html>