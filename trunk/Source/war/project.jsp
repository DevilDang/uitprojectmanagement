<%@page import="sp.form.ProjectForm"%>
<%@page import="sp.util.Constant"%>
<%@page import="sp.dao.GroupDao"%>
<%@page import="sp.dao.UserDao"%>
<%@page import="sp.dto.Group"%>
<%@page import="sp.blo.UserBlo"%>
<%@page import="sp.dto.User"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
<head>
<title>Quản Lý Dự Án</title>
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
									<form name="SearchProject" id="SearchProject">
										<div>
											Trạng thái: <select name="status" id="status"
												onchange="getListProject(1)">
												<%
													String status = (String) request.getAttribute("status");
													Integer Page = (Integer) request.getAttribute("PAGE"); // dành cho phân trang
													Integer page_pos = (Integer) request.getAttribute("page_pos"); // dành cho định vị trí project

													if (page_pos == null) {
														if (request.getParameter("page_pos") != null) {
															page_pos = Integer.parseInt(request
																	.getParameter("page_pos"));
														}
													}

													String isedit = (String) request.getAttribute("isEdit");
													if (isedit == null) {

														isedit = request.getParameter("isEdit");

													}

													if (status == null) {
												%>
												<option value=""></option>
												<option value="Open">Mở</option>
												<option value="Close">Đóng</option>
												<%
													} else if (Constant.OPEN.equals(status)) {
												%>
												<option value=""></option>
												<option value="Open" selected="selected">Mở</option>
												<option value="Close">Đóng</option>
												<%
													} else if (Constant.CLOSE.equals(status)) {
												%>
												<option value=""></option>
												<option value="Open">Mở</option>
												<option value="Close" selected="selected">Đóng</option>
												<%
													}
												%>

											</select>
										</div>
										<br />
										<div>
											Trang <select name="PAGE" id="select_page"
												onchange="getListProjectByPage()">

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
											<li><bean:write name="UserLogIN" /></li>
										</ul>
									</logic:present>
								</div>

							</div>
						</div>
					</div>
					<div class="Column2">
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

								<div class="BlockContentBorder">
									<html:form method="post" action="/editproject.do">
										<div id="content_right">
											<h3 align="center">Chỉnh sửa</h3>
											<br>
											<table id="table_monhoc" class="table_right" cellspacing="5"
												cellpadding="0" border="0">
												<thead>

												</thead>
												<tr>
													<td width="200"><html:errors /></td>
												</tr>
												<tr>
													<td width="70">Mã dự án:</td>
													<td width="70" id="EditAccount"><html:text
															property="IDproject" readonly="true"></html:text></td>
												</tr>
												<tr>
													<td>Tên dự án:</td>
													<td><html:text property="projectname"></html:text></td>
												</tr>
												<tr>
													<td>Người quản lý:</td>
													<td><html:select property="projectmanager">
															<option value="">Chưa có</option>
															<%
																ProjectForm form = (ProjectForm) request.getSession()
																				.getAttribute("ProjectForm");
																		String projectmanager = form.getProjectmanager();
																		UserDao userdao = new UserDao();
																		List<User> list_user = userdao.getUserListFilter(
																				"idPermision=='" + User.PROJECT_MANAGER + "'",
																				"id desc");
																		if (list_user != null) {
																			for (int i = 0; i < list_user.size(); i++) {
																				if (list_user.get(i).getEmail()
																						.equals(projectmanager)) {
															%>
															<option value="<%=list_user.get(i).getEmail()%>"
																selected="selected">
																<%=list_user.get(i).getName()%>
															</option>
															<%
																} else {
															%>
															<option value="<%=list_user.get(i).getEmail()%>">
																<%=list_user.get(i).getName()%>
															</option>
															<%
																}

																			}
																		}
															%>



														</html:select></td>
												</tr>
												<tr>
													<td>Các nhóm tham gia:</td>
													<td align="justify"><select name="makhoa" id="manhom"
														onchange="reset_page('form_monhoc')">
															<%
																GroupDao groupdao = new GroupDao();
																	String param_idproject = request.getParameter("IDproject");
																	if (param_idproject != null && param_idproject.length() > 1) {
																		List<Group> list_group = groupdao.getGroupListFilter(
																				"idProject==" + Long.parseLong(param_idproject), "IDgroup desc");
																		if (list_group != null) {
																			for (int i = 0; i < list_group.size(); i++) {
															%>
															<option value="<%=list_group.get(i).getIDgroup()%>">
																<%=list_group.get(i).getGroupname()%>
															</option>
															<%
																}
																		}
																	}
															%>
													</select></td>
												</tr>
												<tr>
													<td>Tiến Độ:</td>
													<td><html:text property="process"></html:text></td>
												</tr>
												<tr>
													<td>Ngày Bắt Đầu:</td>
													<td><html:text property="startDate"></html:text></td>
												</tr>
												<tr>
													<td>Ngày Kết Thúc:</td>
													<td><html:text property="endDate"></html:text></td>
												</tr>
												<tr>
													<td>Trạng thái</td>
													<td><html:select property="status">
															<html:option value="Open">Mở</html:option>
															<html:option value="Close">Đóng</html:option>
														</html:select></td>
												</tr>
											</table>
											<table>
												<tr>
													<td width="170" align="right"><div id="bt_reset">
															<a href="/changemodeproject.do">Thêm Mới</a>
														</div></td>
													<td width="40"></td>
													<td width="150" align="left">
														<div id="bt_submit">
															<html:submit value="OK"
																style="height: 25px; width: 100px"></html:submit>
														</div>
													</td>
												</tr>
												<tr>
													<td>
													<td><input name="page_pos" type="hidden"
														value="<%=page_pos != null ? page_pos : "1"%>" /></td>
													<td>
													<td><input name="isEdit" type="hidden"
														value="<%="edit".equals(isedit) ? "edit" : "add"%>" /></td>
												</tr>
											</table>
										</div>
										<!--end content right-->

									</html:form>

									<div class="BlockContentBorder"></div>

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
								<h2>Danh sách nhóm</h2>
								<br>
								<form name="listProject" id="listProject" method="post"
									action="/deleteproject.do">

									<input type="hidden" name="PAGE" value="<%=status%>" /> <input
										type="hidden" name="status" value="<%=page_pos%>" />
									<div>
										<input type="submit" id="submit" value="Xóa" />
									</div>
									<br />
									<table id="table_danhsach_project" cellspacing="0"
										cellpadding="0" border="1">
										<thead>
											<tr align="center">
												<td width="20"><input type="checkbox" name="checkall"
													id="checkall" onClick="checkUncheckAll(this);" /></td>
												<td width="120"><b>Tên Dự Án</b></td>
												<td width="250"><b>Người Quản lý</b></td>
												<td width="70"><b>Tiến Độ</b></td>
											</tr>
										</thead>
										<tbody>
											<logic:present name="project_list">
												<logic:iterate id="element" name="project_list">
													<tr align="center">
														<td><input type="checkbox" name="check"
															value="<bean:write name="element"  property="IDproject"/>" /></td>
														<td><a
															href="/getproject.do?IDproject=<bean:write name="element"  property="IDproject"/>&status=<%=status%>&PAGE=<%=page_pos%>"><bean:write
																	name="element" property="projectname" /></a></td>

														<td><b><bean:write name="element"
																	property="projectmanager" /></b></td>
														<td><bean:write name="element" property="process" /></td>
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
