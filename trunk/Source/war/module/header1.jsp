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

 <div class="Header">
          <div class="HeaderTitle">
            <h1><a href="#">Web site quản lý phần mềm</a></h1>
            <h2>UIT</h2>
          </div>
        </div><div class="Menu">
            <ul>
            <li><a href="#" class="ActiveMenuButton"><span>Dự án</span></a></li>|
            <li><a href="/displayReqList.do" class="MenuButton"><span>Yêu cầu</span></a></li>|
            <li><a href="/displayTaskList.do" class="MenuButton"><span>Công việc</span></a></li>|
            <li><a href="/displayReportList.do" class="MenuButton"><span>Báo cáo</span></a></li>|
            <li><a href="/displayOrgList.do" class="MenuButton"><span>Tổ chức</span></a></li></ul>
        </div> <!-- end header-->
<script type="text/javascript">
    <!--
    var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"images/arrow_down.gif", imgRight:"images/arrow_right.gif"});
    //-->
</script>
