<%@page import="sp.dto.User"%>
<%@page import="sp.dto.Project"%>
<%@page import="sp.util.Constant"%>
<%@page import="sp.dao.ProjectDao"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
        <title>Quản Lý Nhóm</title>
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
                    <div class="logo"><h1> Quản Lý Nhóm</h1></div>
                </div> <!--end nav_sub_left-->               
            </div><!--end nav_sub-->
            <div id="content">
                <div id="content_left">
                    <h3 align="center"> Quản lý danh sách </h3><br>
                    <%
                    	String idProject = (String)request.getAttribute("idProject");
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
                    <form name="listGroup" id ="listGroup" method="post" action="/deletegroup.do">
                          <%
                          		ProjectDao project = new ProjectDao();
                                
                          		List<Project> list_project = project.getProjectListFilter("status=='"+Constant.OPEN+"'", "IDproject desc");
                                int length = list_project.size();                        
                          %>
                    	  <div class="chose3" align="center">
                            Nhóm:
                         
                            <select name="idProject" id="box" onChange="getListGroup(1)">
                                <option value="-1"></option>
                                <%
                
                                if("0".equals(idProject))
                                {
                                	%>
                                	<option value="0" selected="selected">Chưa có dự án</option>
                                	<%
                                }
                                else
                                {
                                	%>
                                	<option value="0">Chưa có dự án</option>
                                	<%
                                }
                                %>
                                
                                <%
                                	for(int i = 0;i< length;i++)
                                	{
                                		if(idProject != null && idProject.equals(String.valueOf(list_project.get(i).getIDproject())))
                                		{
                                			%>
                                				<option value="<%= list_project.get(i).getIDproject() %>" selected="selected"><%= list_project.get(i).getProjectname() %></option>	
                                			<%
                                		}
                                		else
                                		{
	                                		%>
	                                		  
	                                			<option value="<%= list_project.get(i).getIDproject() %>" ><%= list_project.get(i).getProjectname() %></option>		
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
                            <table id="table_danhsach_group" cellspacing="0" cellpadding="0" border="1">                               
                                <thead>
                                    <tr align="center">
                                        <td width="20"><input type="checkbox" name="checkall" id="checkall" onClick="checkUncheckAll(this);"/></td>
                                        <td width="130"><b>Tên Nhóm</b></td>
                                        <td width="130"><b>Nhóm Trưởng</b></td>                              
                                    </tr>
                                </thead>
                                <tbody>
                                 <logic:present name="group_list">
									<logic:iterate id="element" name="group_list">
                                	 <tr align="center">
                                        <td width="20"><input type="checkbox" name="check" id="check" value="<bean:write name="element"  property="IDgroup"/>" /></td>
                                       <td width="130"><a href="/getgroup.do?IDgroup=<bean:write name="element"  property="IDgroup"/>&idProject=<%=idProject%>&PAGE=<%=page_pos%>"><bean:write name="element"  property="groupname"/></a></td>
                                         <td width="130"><b><bean:write name="element"  property="leader"/></b></td>                           
                                    </tr>
                                   </logic:iterate>
								</logic:present>                                     
                                </tbody>
                            </table>
                        </div>
                         <div id="phantrang" class="chose3" align="center">
                           <select name="PAGE" id="select_page"
							onchange="getListGroupByPage()">
							
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
                 <html:form method="post" action="/editgroup.do">
                    <div id="content_right">
                        <h3 align="center"> Chỉnh sửa </h3><br>
                        <table id="table_monhoc" class="table_right" cellspacing="5" cellpadding="0" border="0">
                            <thead>

                            </thead>
                             <tr>
							<td width="200"><html:errors /></td>
							</tr>
                            <tr>
                                <td width="100">Mã nhóm: </td>
                                <td width="300">                    	                       	
                                     <html:text property="IDgroup" readonly="true" ></html:text>                 
                                </td>
                            </tr>
                            <tr>
                                <td>Tên nhóm: </td>
                                <td><html:text property="groupname" ></html:text> 
                                </td>
                            </tr>
                            <tr>
                                <td>Trường nhóm: </td>
                                <td>
                                   <%
           							     	List<User> list_user = (List<User>)request.getAttribute(Constant.ACCOUNT_LIST);
           							        
                                   %>
                                   <html:select property="leader">
                                    	 <option value="" >Chưa có nhóm trưởng</option>
           							     
                                    	 
                                    	 <%
                                    	    if(list_user != null)
                                    	    {
                                    	    	int length_user = list_user.size();
	                                    	 	for(int i = 0;i<length_user;i++)
	                                    	 	{
	                                    	 		%>
	                                    	 		   <option value="<%=list_user.get(i).getEmail() %>" ><%=list_user.get(i).getName() %></option>
	                                    	 		<%
	                                    	 	}
                                    	    }
                                    	 %>                                	 
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td>Thành Viên: </td>
                                <td>
                                    <select name="makhoa" id="manhom" onchange="reset_page('form_monhoc')">
                                    	<%
                                    	    if(list_user != null)
                                    	    {
                                    	    	int length_user = list_user.size();
	                                    	 	for(int i = 0;i<length_user;i++)
	                                    	 	{
	                                    	 		%>
	                                    	 		   <option value="<%=list_user.get(i).getEmail() %>" ><%=list_user.get(i).getName() %></option>
	                                    	 		<%
	                                    	 	}
                                    	    }
                                    	 %>     
                                    </select>
                                </td>
                            </tr>   
                            <tr>
                                <td>Tham gia dự án: </td>
                                <td>   
                                <html:select property="idProject">
                                     <%
                
                                if("0".equals(idProject))
                                {
                                	%>
                                	<option value="0" selected="selected">Chưa có dự án</option>
                                	<%
                                }
                                else
                                {
                                	%>
                                	<option value="0">Chưa có dự án</option>
                                	<%
                                }
                                %>
                                
                                <%
                                	for(int i = 0;i< length;i++)
                                	{
                                		if(idProject != null && idProject.equals(String.valueOf(list_project.get(i).getIDproject())))
                                		{
                                			%>
                                				<option value="<%= list_project.get(i).getIDproject() %>" selected="selected"><%= list_project.get(i).getProjectname() %></option>	
                                			<%
                                		}
                                		else
                                		{
	                                		%>
	                                		  
	                                			<option value="<%= list_project.get(i).getIDproject() %>" ><%= list_project.get(i).getProjectname() %></option>		
	                                		<%
                                		}
                                	}
                                %>                            	 
                                    </html:select>
                                </td>
                            </tr>
                        </table>
                        <table>
                            <tr>
                               <td width="170" align="right"><div id="bt_reset">
                                       <a href="//changemodegroup.do">Thêm Mới</a>
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
