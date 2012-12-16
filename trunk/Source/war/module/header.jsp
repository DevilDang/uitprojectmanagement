<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
	UserService userService = UserServiceFactory.getUserService();
String logout = "";
if (request.getUserPrincipal() != null) {	
    		 logout =  userService.createLogoutURL("/login.jsp") ;                          
}
%>
<div id="header">
    <div id="banner">
        <div align="center"><img src="images/banner.jpg" alt="tkb banner" />
        </div>
    </div> <!-- end banner-->
    <div id="menu" align="center">
        <ul id="MenuBar1" class="MenuBarHorizontal">
            <li><a href="project.jsp">Dự án</a></li>
            <li><a href="requirement.jsp">Yêu Cầu</a></li>
            <li><a href="task.jsp">Công Việc</a></li>
            <li><a href="/displayOrgList.do">Đối Tác</a></li>
            <li><a href="group.jsp">Nhóm</a></li>
            <li><a href="account.jsp">Tài Khoản</a></li>
            <li><a href="report.jsp">Báo Cáo</a></li>            
            <li><a href="<%=logout %>">Đăng Xuất</a></li>
        </ul>
    </div> <!--end menu-->

</div> <!-- end header-->
<script type="text/javascript">
    <!--
    var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"images/arrow_down.gif", imgRight:"images/arrow_right.gif"});
    //-->
</script>
