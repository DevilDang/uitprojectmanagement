<%@page import="sp.dto.Group"%>
<%@page import="java.util.List"%>
<%@page import="sp.dao.GroupDao"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
<head>
<title>Quản Lý Nhân viên</title>
<link rel="stylesheet" type="text/css" href="default.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css"
	href="css/SpryMenuBarHorizontal.css" />
<script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
<!--  script src="javascripts/MyJavaScripts.js" type="text/javascript"></script -->
<script src="javascripts/json.js" type="text/javascript"></script>
<script src="javascripts/check.js" type="text/javascript"></script>
<script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>

</head>
<body>
	<div id="container">
		<jsp:include page="module/header.jsp" />
		<div id="nav_sub">
			<div id="nav_sub_left">
				<div class="logo">
					<img src="images/logo.png" />
				</div>
				<div class="logo">
					<h1>Quản Lý Nhân Viên</h1>
				</div>
			</div>
			<!--end nav_sub_left-->
		</div>
		<!--end nav_sub-->
		<div id="content">
			<div id="content_left">
				<h3 align="center">Quản lý danh sách</h3>
				<%
                    	String groupID = (String)request.getAttribute("groupID");
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
                   %>
				<br>
				<form name="listAccount" id="listAccount" method="post"
					action="/deleteaccount.do">
					  <%
                          		GroupDao groupDao = new GroupDao();
                                
                          		List<Group> list_Group = groupDao.getGroupList("IDgroup desc");
                                int length = list_Group.size();                        
                          %>
					  <div class="chose3" align="center">
                            Nhóm:
                         
                            <select name="groupID" id="box" onChange="getListAccount(1)">
                                <option value="" selected="selected"></option>
                                                             
                                <%
                
                                if("0".equals(groupID))
                                {
                                	%>
                                	<option value="0" selected="selected">Chưa phân nhóm</option>
                                	<%
                                }
                                else
                                {
                                	%>
                                	<option value="0">Chưa phân nhóm</option>
                                	<%
                                }
                                %>
                                
                                <%
                                	for(int i = 0;i< length;i++)
                                	{
                                		if(groupID != null && groupID.equals(String.valueOf(list_Group.get(i).getIDgroup())))
                                		{
                                			%>
                                				<option value="<%= list_Group.get(i).getIDgroup() %>" selected="selected" ><%= list_Group.get(i).getGroupname() %></option>	
                                			<%
                                		}
                                		else
                                		{
	                                		%>
	                                		  
	                                			<option value="<%= list_Group.get(i).getIDgroup() %>" ><%= list_Group.get(i).getGroupname() %></option>		
	                                		<%
                                		}
                                	}
                                %>
                              
                            </select>
                        </div>
                        <p>&nbsp;</p>
                        <p><br>
                        </p>
                        
					<div id="table">
						<table id="table_danhsach_account" cellspacing="0" cellpadding="0"
							border="1">
							<thead>
								<tr align="center">
									<td width="20"><input type="checkbox" name="checkall"
										id="checkall" onClick="checkUncheckAll(this);" /></td>
									<td width="70"><b>Tên Đăng Nhập</b></td>
									<td width="130"><b>Tên nhân viên</b></td>
									<td width="130"><b>Quyền Hạn</b></td>
								</tr>
							</thead>
							<tbody>
								<logic:present name="account_list">
									<logic:iterate id="element" name="account_list">
										<tr align="center">
											<td width="20"><input type="checkbox" name="check" value="<bean:write name="element"  property="email"/>"/></td>
											<td width="70"><a href="#"><b><bean:write name="element"  property="email"/></b></a></td>
											<td width="130"><b><bean:write name="element"  property="name"/></b></td>
											<td width="130"><b><bean:write name="element"  property="idPermision"/></b></td>
										</tr>
									</logic:iterate>
								</logic:present>
							</tbody>
						</table>
					</div>
					<div id="phantrang" class="chose3" align="center">
						<select name="PAGE" id="select_page"
							onchange="getListaccountByPage()">
							<option value="1">1</option>
						</select>

					</div>
					<div class="chose3" align="center">
						<input type="submit" id="submit" value="Xóa"
							style="height: 25px; width: 100px" />

					</div>
				</form>
				<div></div>
			</div>
			<!--end content left-->
			<html:form method="post" action="/editaccount.do">
				<div id="content_right">
					<h3 align="center">Chỉnh sửa</h3>
					<br>
					<table id="table_monhoc" class="table_right" cellspacing="5"
						cellpadding="0" border="0">
						<thead>

						</thead>
						<tr>
							<td width="100"><html:errors /></td>
						</tr>
						<tr>
							<td width="100">Email:</td>
							<td width="300" id="EditAccount"><html:text property="email" /></td>
						</tr>

						<tr>
							<td>Tên nhân viên:</td>
							<td><html:text property="fullname" /></td>
						</tr>
						<tr>
							<td>Mật Khẩu:</td>
							<td><html:text property="password" /></td>
						</tr>
						<tr>
							<td>Nhập lại Mật Khẩu:</td>
							<td><html:text property="retypepassword" /></td>
						</tr>
						<tr>
							<td>Quyền hạn:</td>
							<td><html:select property="permission">
									<html:option value="admin">ADMIN</html:option>
									<html:option value="project_manager">ProJectManager</html:option>
									<html:option value="leader">Leader</html:option>
									<html:option value="employer">Employer</html:option>
								</html:select></td>
						</tr>
						<tr>
							<td>Thuộc nhóm:</td>
							<td>
							<html:select property="groupCode">                             
                                <%
                
                                if("0".equals(groupID))
                                {
                                	%>
                                	<option value="0" selected="selected">Chưa phân nhóm</option>
                                	<%
                                }
                                else
                                {
                                	%>
                                	<option value="0">Chưa phân nhóm</option>
                                	<%
                                }
                                %>
                                
                                <%
                                	for(int i = 0;i< length;i++)
                                	{
                                		if(groupID != null && groupID.equals(String.valueOf(list_Group.get(i).getIDgroup())))
                                		{
                                			%>
                                				<option value="<%= list_Group.get(i).getIDgroup() %>" selected="selected" ><%= list_Group.get(i).getGroupname() %></option>	
                                			<%
                                		}
                                		else
                                		{
	                                		%>
	                                		  
	                                			<option value="<%= list_Group.get(i).getIDgroup() %>" ><%= list_Group.get(i).getGroupname() %></option>		
	                                		<%
                                		}
                                	}
                                %>
								</html:select></td>
						</tr>
					</table>
					<table>
						<tr>
                               <td width="170" align="right"><div id="bt_reset">
                                       <a href="/changemodeuser.do">Thêm Mới</a>
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
				</div>
				<!--end content right-->
			</html:form>

		</div>

		<jsp:include page="module/footer.jsp" />

	</div>
	<!-- end container-->
</body>
</html>
