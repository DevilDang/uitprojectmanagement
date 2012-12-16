<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
    <head>
        <title>Quản Lý Đối Tác</title>
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link rel="stylesheet" type="text/css" href="css/SpryMenuBarHorizontal.css"/>
        <script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
        <script src="javascripts/MyJavaScripts.js" type="text/javascript"></script>
        <script src="javascripts/json.js" type="text/javascript"></script>
        <script src="javascripts/check.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="container">           
            <jsp:include page="module/header.jsp" />
            <div id="nav_sub">
                <div id="nav_sub_left">
                    <div class="logo"><img src="images/logo.png" /></div>
                    <div class="logo"><h1> Quản Lý Đối Tác</h1></div>
                </div> <!--end nav_sub_left-->               
            </div><!--end nav_sub-->
            <div id="content">
                <div id="content_left">
                    <h3 align="center"> Quản lý danh sách </h3><br>
                    <form name="danhsachmonhoc" id ="danhsachmonhoc" method="post" action="">
                    	 
                        <p>Số lượng:
                        <logic:present name="org_total_number">
                        <bean:write name="org_total_number"/>
                        </logic:present>
                        </p>
                        <p><br>
                        </p>
                        <div id="table">
                            <table id="table_danhsach_monhoc" cellspacing="0" cellpadding="0" border="1">                               
                               <thead>
                                    <tr align="center">
                                        <td width="20"><input type="checkbox" name="checkall" id="checkall" onClick="checkUncheckAll(this);"/></td>
                                        <td width="70"><b>Mã Đối Tác</b></td>
                                        <td width="130"><b>Tên Đối Tác</b></td>
                                        <td width="130"><b>Website</b></td>                                 
                                    </tr>
                                </thead>
                                <tbody>
                                 <logic:present name="org_list">
      <logic:iterate id="element" name="org_list" > 
                                	 <tr align="center">
                                        <td width="20">
                                        <input type="checkbox" name="checkall" id="checkall"  onClick="checkUncheckAll(this);"/> </td>
                                        <td width="70"><b><a href="/displayOrg.do?id=<bean:write name="element"  property="idOrg"/>"><bean:write name="element"  property="idOrg"/></a></b></td>
                                        <td width="130"><b><bean:write name="element"  property="nameOrg"/></b></td>
                                        <td width="130"><b><bean:write name="element"  property="addOrg"/></b></td>                               
                                    </tr>  
                                    </logic:iterate>
      </logic:present>                             
                                </tbody>
                            </table>
                        </div>
                        <div id="phantrang" class="chose3" align="center">
                            <select name ="PAGE" id="select_page" onchange="ajax_load_page_danhsachmonhoc()">
                                <logic:present name="org_page_list">
                               <logic:iterate id="element" name="org_page_list" >
                                <option value="<bean:write name="element"/>"><bean:write name="element" /></option>
                                </logic:iterate>
                                </logic:present>
                            </select>

                        </div>
                        <div class="chose3" align="center">
                            <input type="button" id="submit" value="Xóa" style="height: 25px; width: 100px" onClick="ajax_delete_monhoc()">
                            <input type="hidden" name="KEY" value="XOA_MONHOC">

                        </div>
                    </form>
                    <div>

                    </div>
                </div> <!--end content left-->
                <html:form action="/updateOrg.do" method="get">
                    <div id="content_right">
                    
                        <h3 align="center"><!-- <a href="/changeMode.do?mode=2"> -->Chỉnh sửa </a> / <a href="/changeMode.do?mode=1">Thêm mới </a></h3><br>
                        <table id="table_monhoc" class="table_right" cellspacing="5" cellpadding="0" border="0">
                            <thead>

                            </thead>
                            <logic:present name="flagOrg">
                            <logic:equal name="flagOrg" value="2">
                            <tr>
                                <td width="100">Mã Đối tác: </td>
                                <td width="300"> 
                                  <bean:write name="organization" property="idOrg"/>                                                  
                                </td>
                            </tr>
                            </logic:equal>
                            </logic:present>
                            
                            <tr>
                                <td>Tên đối tác: </td>
                                <td>
                                <html:text property="nameOrg" />  
                                </td>
                            </tr>
  							 <tr>
                                <td width="100">Địa Chỉ: </td>
                                <td width="300">                    	                       	
                                     <html:text property="addOrg" />                       
                                </td>
                            </tr>
                            <tr>
                                <td>Website: </td>
                                <td>
                                <html:text property="websiteOrg" />  
                                </td>
                            </tr>
                            <tr>
                                <td>Số Điện thoại: </td>
                                <td>
                                <html:text property="numberOrg" />  
                                </td>
                            </tr>
                        </table>
                        <table>
                            <tr>
                                <td width="170" align="right"><div id="bt_reset">
                                        <input type="reset" name="reset" id="reset" value="Reset" style="height: 25px; width: 100px" onClick="reset_validate_form_monhoc()">
                                    </div>
                                </td>
                                <td width="40"> </td>
                                <td width="150" align="left">
                                    <div id="bt_submit">
                                        <input type ="submit"  value="OK" style="height: 25px; width: 100px" />
                                 <!--        <input type ="hidden" name ="KEY" value="THEM_MONHOC">
                                        <input type ="hidden" name="PAGE" value="0"> -->
                                    </div>
                                </td>
                            </tr>
                        </table>           
                    </div><!--end content right-->
</html:form>
            </div>

            <jsp:include page="module/footer.jsp" />

        </div> <!-- end container-->
    </body>
</html>
