<%@page import="sp.util.Constant"%>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>

<%
 String typelogin = (String)request.getSession().getAttribute(Constant.Type_Login);
 Object userlogin = (Object)request.getSession().getAttribute(Constant.User_Login);
 if(typelogin == null || userlogin == null )
 {
	 response.sendRedirect("/login.jsp");
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
            <li><a href="/displayReqList.do">Yêu Cầu</a></li>
            <li><a href="/displayTaskList.do">Công Việc</a></li>
            <li><a href="/displayOrgList.do">Đối Tác</a></li>
            <li><a href="group.jsp">Nhóm</a></li>
            <li><a href="account.jsp">Tài Khoản</a></li>
            <li><a href="changepassword.jsp">Đổi Mật Khẩu</a></li>
            <li><a href="/displayReportList.do">Báo Cáo</a></li>            
            <li><a href="logout.do">Đăng Xuất</a></li>
        </ul>
    </div> <!--end menu-->

</div> <!-- end header-->
<script type="text/javascript">
    <!--
    var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"images/arrow_down.gif", imgRight:"images/arrow_right.gif"});
    //-->
</script>
