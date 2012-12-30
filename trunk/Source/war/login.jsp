<%@page import="sp.form.UserForm"%>
<%@page import="sp.dao.UserDao"%>
<%@page import="sp.blo.UserBlo"%>
<%@page import="sp.dto.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
UserService userService = UserServiceFactory.getUserService();

 String login = "";
 if (request.getUserPrincipal() == null){
 	
     login = userService.createLoginURL("/LogIn.do?typeLogin=GoogleAccount");
                                
}else
{
	// nếu đã qua vòng chứng thực của google thì sẽ k phải đăng nhập mà sẽ được redirect vao hệ thống luôn, không phải
	// đăng nhập lại
	String username = request.getUserPrincipal().getName();
	UserDao userdao = new UserDao();
	if(userdao.checkExistUser(username))
	{
		response.sendRedirect("/index.jsp");
	}else
	{
		login = userService.createLoginURL("/LogIn.do?typeLogin=GoogleAccount");
	}
}
 
%>
<html>
    <head>
        <title>Trang chủ</title>
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link rel="stylesheet" type="text/css" href="css/SpryMenuBarHorizontal.css"/>
        <script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
        <script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>
    </head>
    <body onload="checkStatusLoginByGoogleAccount()">
        <div id="container">
            <jsp:include page="module/header_login.jsp" />

            <div id="con_login" align="center">
                <html:errors/>
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
                        <html:submit property="submit" value="Đăng nhập" style="height: 25px; width: 100px" ></html:submit>
                    </div>
                </html:form>
                
               <a href="<%=login%>">Đăng nhập bằng tài khoản google</a>
            </div>

        </div> <!-- end content-->

        <jsp:include page="module/footer.jsp" />
        <!-- end footer-->
    </body>
</html>
