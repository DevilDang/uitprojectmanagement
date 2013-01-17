<%@page import="sp.dao.GroupDao"%>
<%@page import="sp.dao.UserDao"%>
<%@page import="sp.dto.Group"%>
<%@page import="sp.blo.UserBlo"%>
<%@page import="sp.dto.User"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
        <title>Quản Lý Dự Án</title>
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link rel="stylesheet" type="text/css" href="css/SpryMenuBarHorizontal.css"/>
        <script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
        <script src="javascripts/MyJavaScripts.js" type="text/javascript"></script>
        <script src="javascripts/json.js" type="text/javascript"></script>
        <script src="javascripts/check.js" type="text/javascript"></script>      
		<script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="container">           
            <jsp:include page="module/header.jsp" />
            <div id="nav_sub">
                <div id="nav_sub_left">
                    <div class="logo"><img src="images/logo.png" /></div>
                    <div class="logo"><h1> Quản Lý Dự Án</h1></div>
                </div> <!--end nav_sub_left-->               
            </div><!--end nav_sub-->
            <div id="content">
                <div id="content_left">
                    <h3 align="center"> Quản lý danh sách </h3><br>
                    <form name="listProject" id ="listProject" method="post" action="/deleteproject.do">
                        <div class="chose3" align="center">
                           Trạng thái:
                         
                            <select name="status" id="status" onchange="getListProject(1)">
                            			<%
                            			String status = (String)request.getAttribute("status");
                            			Integer Page= (Integer)request.getAttribute("PAGE"); // dành cho phân trang
                            			Integer page_pos = (Integer)request.getAttribute("page_pos"); // dành cho định vị trí project
                            			
                            			if(page_pos == null)
                            			{
                            				if(request.getParameter("page_pos") != null)
                            				{
                            					page_pos = Integer.parseInt(request.getParameter("page_pos"));
                            				}
                            			}
                            			
                            			String isedit = (String)request.getAttribute("isEdit");
                            			if(isedit == null)
                            			{
                            				
                            				isedit = request.getParameter("isEdit");
                            				
                            			}
                            			
                            			if(status == null)
                            			{
                            				%>
                            				<option value="-1" ></option>                            			
	                                    	<option value="1" >Mở</option>
	                                    	<option value="0" >Đóng</option>   
                            				<% 
                            			}
                            			else if("1".equals(status))
                            			{
                            				%>
                            				<option value="-1" ></option>                            			
	                                    	<option value="1" selected="true">Mở</option>
	                                    	<option value="0" >Đóng</option>   
                            				<%
                            			}
                            			else if("0".equals(status))
                            			{
                            				%>
                            				<option value="-1" ></option>                            			
	                                    	<option value="1" >Mở</option>
	                                    	<option value="0" selected="true">Đóng</option>   
                            				<%
                            			}
                            			%>
                            			                              	
                            </select>
                        </div>
                        <p>&nbsp;</p>
                        <p><br>
                        </p>
                        <div id="table">
                            <table id="table_danhsach_project" cellspacing="0" cellpadding="0" border="1">
                                <thead>
                                    <tr align="center">
                                        <td width="20"><input type="checkbox" name="checkall" id="checkall" onClick="checkUncheckAll(this);"/></td>
                                        <td width="130"><b>Tên Dự Án</b></td>
                                        <td width="130"><b>Người Quản lý</b></td>
                                        <td width="70"><b>Tiến Độ</b></td>                                     
                                    </tr>
                                </thead>
                                <tbody>
                                <logic:present name="project_list">
									<logic:iterate id="element" name="project_list">
                                	 <tr align="center">
                                        <td width="20"><input type="checkbox" name="check" value="<bean:write name="element"  property="IDproject"/>"/></td>
                                        <td width="130"><a href="/getproject.do?IDproject=<bean:write name="element"  property="IDproject"/>&status=<%=status%>&PAGE=<%=page_pos%>"><bean:write name="element"  property="projectname"/></a></td>
                                        
                                        <td width="130"><b><bean:write name="element"  property="projectmanager"/></b></td>
                                        <td width="70"><bean:write name="element"  property="process"/></td>                                     
                                    </tr>
                                    </logic:iterate>
								</logic:present>                               
                                </tbody>
                            </table>
                        </div>
                        <div id="phantrang" class="chose3" align="center">
                           <select name="PAGE" id="select_page"
							onchange="getListProjectByPage()">
							
							<%
							   if(Page == null || Page < 1 )
							   {
								 %>
								 <option value="1">1</option>
								 <%  
							   }
							   else
							   {
								   for(int i = 0;i<Page;i++)
								   {
									   if(page_pos == i + 1)
									   {
										   %>
											 <option value="<%=i+ 1%>" selected="selected"><%=i + 1%></option>
										   <%  
									   }else
									   {
										   %>
											 <option value="<%=i+ 1%>"><%=i + 1%></option>
										   <%  
									   }
									   
								   }
								  
							   }
							%>
							
							
						</select>

                        </div>
                        <div class="chose3" align="center">                          
						<input type="submit" id="submit" value="Xóa"
							style="height: 25px; width: 100px" />
                        </div>
                    </form>
                    <div>

                    </div>
                </div> <!--end content left-->
                  <html:form method="post" action="/editproject.do" >
                    <div id="content_right">
                        <h3 align="center"> Chỉnh sửa </h3><br>
                        <table id="table_monhoc" class="table_right" cellspacing="5" cellpadding="0" border="0">
                            <thead>
								
                            </thead>
                            <tr>
							<td width="200"><html:errors /></td>
							</tr>
                            <tr>
                                <td width="70">Mã dự án: </td>
                                <td width="100" id="EditAccount">
                                    <html:text property="IDproject" readonly="true" ></html:text>                 
                                </td>
                            </tr>
                            <tr>
                                <td>Tên dự án: </td>
                                <td><html:text property="projectname" ></html:text> 
                                </td>
                            </tr>
                            <tr>
                                <td>Người quản lý: </td>
                                <td>
                                   <html:select property="projectmanager">
                                   <%
                                   		List<User> list_user = UserDao.getListAccountSorted(0);
                                        if(list_user != null)
                                        {
                                        	for(int i = 0;i<list_user.size();i++)
                                        	{
                                        		
                                        		
                                   %>
                                        	<option value="<%=list_user.get(i).getEmail() %>">  <%=list_user.get(i).getName() %> </option>
                                   <%
                                            }
                                   		}
                                   %>
                                   
										
									 	
                                   </html:select>                                   
                                </td>
                            </tr>
                            <tr>
                                <td>Các nhóm tham gia: </td>
                                 <td  align="justify">
                                    <select name="makhoa" id="manhom" onchange="reset_page('form_monhoc')">
                                      <%
                                        GroupDao groupdao = new GroupDao();
                                        String param_idproject = request.getParameter("IDproject");
                                        if(param_idproject != null && param_idproject.length() > 1)
                                        {
	                                   		List<Group> list_group = groupdao.getGroupListFilter("idProject==" + param_idproject,"IDgroup desc");
	                                        if(list_group != null)
	                                        {
	                                        	for(int i = 0;i<list_group.size();i++)
	                                        	{
                                        		
	                                        		
	                                   %>
	                                        	<option value="<%=list_group.get(i).getIDgroup() %>">  <%=list_group.get(i).getGroupname() %> </option>
	                                   <%
	                                         	}
	                                   		}
                                        }
	                                   %>
                                    </select>                                    
                                </td>
                            </tr>
                            <tr>
                                <td>Tiến Độ: </td>
                                <td><html:text property="process" ></html:text> 
                                </td>
                            </tr>
                              <tr>
                                <td>Ngày Bắt Đầu: </td>
                                <td><html:text property="startDate"></html:text> 
                                </td>
                            </tr>  
                              <tr>
                                <td>Ngày Kết Thúc: </td>
                                <td><html:text property="endDate"></html:text> 
                                </td>
                            </tr>
                             <tr>
                             <td> Trạng thái</td>
                             <td>
                             	<html:select property="status">                           	 
                             		<html:option value="1" >Mở</html:option>                                  	
                             		<html:option value="0" >Đóng</html:option>                   		
                             	</html:select>
                             </td>                          
                            </tr>
                        </table>
                        <table>
                            <tr>
                                <td width="170" align="right"><div id="bt_reset">
                                       <a href="/changemodeproject.do">Thêm Mới</a>
                                    </div>
                                </td>
                                <td width="40"> </td>
                                <td width="150" align="left">
                                    <div id="bt_submit">
                                        <html:submit value="OK" style="height: 25px; width: 100px"></html:submit>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            <td>
							<td><input name="page_pos" type="hidden" value="<%=page_pos!=null?page_pos:"1" %>" />
							</td>
							<td>
							<td><input name="isEdit" type="hidden" value="<%="edit".equals(isedit)?"edit":"add" %>" />
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
