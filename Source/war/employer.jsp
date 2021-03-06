<%@page import="sp.util.Constant"%>
<%@page import="sp.dto.Project"%>
<%@page import="sp.dao.ProjectDao"%>
<%@page import="sp.dto.User"%>
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
				<div class="Columns">
					<div class="Column1">
						<div class="BlockBorder">
							<div class="BlockBL">
								<div></div>
							</div>
							<div class="BlockBR">
								<div></div>
							</div>
							<div class="BlockTL"></div>
							<div class="BlockTR">
								<div></div>
							</div>
							<div class="BlockT"></div>
							<div class="BlockR">
								<div></div>
							</div>
							<div class="BlockB">
								<div></div>
							</div>
							<div class="BlockL"></div>
							<div class="BlockC"></div>
							<div class="Block">
								<span class="BlockHeader"><span>Tìm kiếm</span></span>
								<div class="BlockContentBorder">
									<form name="SearchAccount" id="SearchAccount">
										<%
										    String idProject = (String) request.getAttribute("idProject");									
											String groupID = (String) request.getAttribute("groupID");
											Integer Page = (Integer) request.getAttribute("PAGE"); // dành cho phân trang
											Integer page_pos = (Integer) request.getAttribute("page_pos"); // dành cho định vị trí project

											if (page_pos == null) {
												if (request.getParameter("page_pos") != null) {
													page_pos = Integer.parseInt(request
															.getParameter("page_pos"));
												}
											}
											ProjectDao project = new ProjectDao();

											List<Project> list_project = project.getProjectListFilter(
													"status=='" + Constant.OPEN + "'", "IDproject desc");
											int length_project = list_project.size();
											
											GroupDao groupDao = new GroupDao();
											List<Group> list_Group = groupDao.getGroupList("IDgroup desc");
											int length = list_Group.size();
										%>

										<div>
											Dự án: <select name="idProject" id="box"
												onChange="getGroups()">
                                                <option value=""></option>
												<%
													for (int i = 0; i < length_project; i++) {
														if (idProject != null
																&& idProject.equals(String.valueOf(list_project.get(i)
																		.getIDproject()))) {
												%>
												<option value="<%=list_project.get(i).getIDproject()%>"
													selected="selected"><%=list_project.get(i).getProjectname()%></option>
												<%
													} else {
												%>

												<option value="<%=list_project.get(i).getIDproject()%>"><%=list_project.get(i).getProjectname()%></option>
												<%
													}
													}
												%>

											</select>
										</div>
                                        	<br />
										<div>
											Nhóm: <select name="groupID" id="groupID"
												onChange="getListAccount(1)">
												<option value=""></option>
											</select>
										</div>
										<br />
										<div>
											Trang <select name="PAGE" id="select_page"
												onchange="getListaccountByPage()">

												<%
													if (Page == null || Page < 1) {
												%>
												<option value="1">1</option>
												<%
													} else {
														for (int i = 0; i < Page; i++) {
															if (page_pos == i + 1) {
												%>
												<option value="<%=i + 1%>" selected="selected"><%=i + 1%></option>
												<%
													} else {
												%>
												<option value="<%=i + 1%>"><%=i + 1%></option>
												<%
													}

														}

													}
												%>


											</select>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="BlockBorder">
							<div class="BlockBL">
								<div></div>
							</div>
							<div class="BlockBR">
								<div></div>
							</div>
							<div class="BlockTL"></div>
							<div class="BlockTR">
								<div></div>
							</div>
							<div class="BlockT"></div>
							<div class="BlockR">
								<div></div>
							</div>
							<div class="BlockB">
								<div></div>
							</div>
							<div class="BlockL"></div>
							<div class="BlockC"></div>
							<div class="Block">

								<span class="BlockHeader"><span>User</span></span>
								<div class="BlockContentBorder">
									<logic:present name="UserLogIN">
										<ul>
											<li><bean:write name="UserLogIN"/><a href="logout.do"><span>(Đăng Xuất)</span></a></li>
										</ul>
									</logic:present>
								</div>

							</div>
						</div>
					</div>

					<div class="MainColumn">
						<div class="ArticleBorder">
							<div class="ArticleBL">
								<div></div>
							</div>
							<div class="ArticleBR">
								<div></div>
							</div>
							<div class="ArticleTL"></div>
							<div class="ArticleTR">
								<div></div>
							</div>
							<div class="ArticleT"></div>
							<div class="ArticleR">
								<div></div>
							</div>
							<div class="ArticleB">
								<div></div>
							</div>
							<div class="ArticleL"></div>
							<div class="ArticleC"></div>
							<div class="Article">
								<h2>Danh sách nhân viên</h2>
								<br>
								<form name="listAccount" id="listAccount" method="post"
									action="/deleteaccount.do">

									<input type="hidden" name="PAGE" value="<%=page_pos%>" /> <input
										type="hidden" name="groupID" value="<%=groupID%>" />
									<br />
									<table id="table_danhsach_account" cellspacing="0"
										cellpadding="0" border="1" width="470">
										<thead>
											<tr align="center">
												<td width="10%"><input type="checkbox" name="checkall"
													id="checkall" onClick="checkUncheckAll(this);" /></td>
												<td width="40%"><b>Email</b></td>
												<td width="30%"><b>Họ Tên</b></td>
												<td width="20%"><b>Quyền Hạn</b></td>
											</tr>
										</thead>
										<tbody>
											<logic:present name="account_list">
												<logic:iterate id="element" name="account_list">
													<tr align="center">
														<td width="20"><input type="checkbox" name="check"
															value="<bean:write name="element"  property="email"/>" /></td>
														<td width="130"><a
															href="/getaccount.do?email=<bean:write name="element"  property="email"/>&groupID=<%=groupID%>&PAGE=<%=page_pos%>"><bean:write
																	name="element" property="email" /></a></td>
														<td width="130"><b><bean:write name="element"
																	property="name" /></b></td>
														<td width="130"><b><bean:write name="element"
																	property="idPermision" /></b></td>
													</tr>
												</logic:iterate>
											</logic:present>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>

				</div>
				<div class="Footer">UIT - Quản lý phần mềm</div>
			</div>

			<!--end nav_sub-->

		</div>
	</div>
	<!-- end container-->
</body>


</html>
