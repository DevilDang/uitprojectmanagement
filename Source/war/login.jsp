<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
UserService userService = UserServiceFactory.getUserService();

 String login = "";
 if (request.getUserPrincipal() == null){
 	
     login = userService.createLoginURL("/index.jsp");
                             
}
%>
<html>
    <head>
        <title>Trang chủ</title>
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link rel="stylesheet" type="text/css" href="css/SpryMenuBarHorizontal.css"/>
        <script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
    </head>
    <body>
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
