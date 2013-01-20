<%@page import="sp.form.UserForm"%>
<%@page import="sp.dao.UserDao"%>
<%@page import="sp.blo.UserBlo"%>
<%@page import="sp.dto.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
	UserService userService = UserServiceFactory.getUserService();

	String login = "";
	if (request.getUserPrincipal() == null) {

		login = userService
				.createLoginURL("/LogIn.do?typeLogin=GoogleAccount");

	} else {
		// nếu đã qua vòng chứng thực của google thì sẽ k phải đăng nhập mà sẽ được redirect vao hệ thống luôn, không phải
		// đăng nhập lại
		String username = request.getUserPrincipal().getName();
		UserDao userdao = new UserDao();
		if (userdao.checkExistUser(username)) {
			response.sendRedirect("/index.jsp");
		} else {
			login = userService
					.createLoginURL("/LogIn.do?typeLogin=GoogleAccount");
		}
	}
%>
<html>
<head>
<title>Trang chủ</title>
<link rel="stylesheet" href="css/style1.css" />
<link rel="stylesheet" type="text/css"
	href="javascripts/ui/themes/base/ui.all.css" />
<script src="javascripts/check.js" type="text/javascript"></script>
<script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
<script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>
<script src="javascripts/requirement.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
</head>
<body onload="checkStatusLoginByGoogleAccount()">
	<div class="BodyContent">
		<div class="BorderBorder">
			<div class="BorderBL">
				<div></div>
			</div>
			<div class="BorderBR">
				<div></div>
			</div>
			<div class="BorderTL"></div>
			<div class="BorderTR">
				<div></div>
			</div>
			<div class="BorderT"></div>
			<div class="BorderR">
				<div></div>
			</div>
			<div class="BorderB">
				<div></div>
			</div>
			<div class="BorderL"></div>
			<div class="BorderC"></div>
			<div class="Border">
				<jsp:include page="module/header_login.jsp" />
				<br>
				<div align="center">
					<html:errors />
					<html:form styleId="login" method="post" action="/LogIn.do">
						<div class="login" align="center">
							Người dùng:
							<html:text property="username"></html:text>
						</div>
						<br>
						<div class="login" align="center">
							Mật khẩu:
							<html:password property="password"></html:password>
						</div>
						<br>
						<div class="login" align="center">
							<html:submit property="submit" value="Đăng nhập"
								style="height: 25px; width: 100px"></html:submit>
						</div>
					</html:form>

					<a href="<%=login%>">Đăng nhập bằng tài khoản google</a>
				</div>
				<div class="Footer">UIT - Quản lý phần mềm</div>
			</div>
		</div>
	</div>
	<!-- end footer-->
</body>
</html>
