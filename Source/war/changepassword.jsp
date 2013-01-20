<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
	
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

<body>
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
				<jsp:include page="module/header1.jsp" />
				<br>
				<div id="con_login" align="center">
				<html:form method="post" action="/changepass.do">
					<table>
					
					<tr>
					<td>
					<html:errors/>
					</td>
					</tr>
					
					<tr>
					<td>Mật khẩu cũ:</td>
					<td><html:text property="oldpassword"></html:text> </td>					
					</tr>
					
					<tr>
					<td>Mật khẩu mới:</td>
					<td><html:text property="newpassword"></html:text> </td>					
					</tr>
					<tr>
					
					<td>Nhập lại mật khẩu:</td>
					<td><html:text property="retype_newpassword"></html:text> </td>					
					</tr>
					
					</table>
					
					<tr>
					<td><html:submit value="Đồng ý"></html:submit> </td>
					<td><html:reset value="Nhập lại"></html:reset>  </td>					
					</tr>
					<tr>
					
				</html:form>
					
					
				</div>

				<div class="Footer">UIT - Quản lý phần mềm</div>
			</div>
		</div>
	</div>
</body>
</html>
