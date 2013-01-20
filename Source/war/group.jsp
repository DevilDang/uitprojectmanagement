<%@page import="sp.dto.User"%>
<%@page import="sp.dto.Project"%>
<%@page import="sp.util.Constant"%>
<%@page import="sp.dao.ProjectDao"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
<head>
<title>Quản Lý Nhóm</title>
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
									<form name="SearchGroup" id="SearchGroup">
										<%
											String idProject = (String) request.getAttribute("idProject");
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

											ProjectDao project = new ProjectDao();

											List<Project> list_project = project.getProjectListFilter(
													"status=='" + Constant.OPEN + "'", "IDproject desc");
											int length = list_project.size();
										%>
										<div>
											Dự án: <select name="idProject" id="box"
												onChange="getListGroup(1)">
												<option value="-1"></option>
												<%
													if ("0".equals(idProject)) {
												%>
												<option value="0" selected="selected">Chưa có</option>
												<%
													} else {
												%>
												<option value="0">Chưa có</option>
												<%
													}
												%>

												<%
													for (int i = 0; i < length; i++) {
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
											Trang <select name="PAGE" id="select_page"
												onchange="getListGroupByPage()">

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
									<html:form method="post" action="/editgroup.do">
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
													<td width="150">Mã nhóm:</td>
													<td width="180"><html:text property="IDgroup"
															readonly="true"></html:text></td>
												</tr>
												<tr>
													<td  width="100">Tên nhóm:</td>
													<td><html:text property="groupname"></html:text></td>
												</tr>
												<tr>
													<td width="100">Trường nhóm:</td>
													<td>
														<%
															List<User> list_user = (List<User>) request
																		.getAttribute(Constant.ACCOUNT_LIST);
														%> <html:select property="leader">
															<option value="">Chưa có</option>


															<%
																if (list_user != null) {
																			int length_user = list_user.size();
																			for (int i = 0; i < length_user; i++) {
															%>
															<option value="<%=list_user.get(i).getEmail()%>"><%=list_user.get(i).getName()%></option>
															<%
																}
																		}
															%>
														</html:select>
													</td>
												</tr>
												<tr>
													<td width="100">Thành Viên:</td>
													<td><select name="makhoa" id="manhom"
														onchange="reset_page('form_monhoc')">
															<%
																if (list_user != null) {
																		int length_user = list_user.size();
																		for (int i = 0; i < length_user; i++) {
															%>
															<option value="<%=list_user.get(i).getEmail()%>"><%=list_user.get(i).getName()%></option>
															<%
																}
																	}
															%>
													</select></td>
												</tr>
												<tr>
													<td width="100">Dự án:</td>
													<td><html:select property="idProject">
															<%
																if ("0".equals(idProject)) {
															%>
															<option value="0" selected="selected">Chưa có</option>
															<%
																} else {
															%>
															<option value="0">Chưa có</option>
															<%
																}
															%>

															<%
																for (int i = 0; i < length; i++) {
																			if (idProject != null
																					&& idProject.equals(String.valueOf(list_project
																							.get(i).getIDproject()))) {
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
														</html:select></td>
												</tr>
											</table>
											<table>
												<tr>
													<td width="170" align="right"><div id="bt_reset">
															<a href="/changemodegroup.do">Thêm Mới</a>
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
								<h2>Danh sách dự án</h2>
								<br>
								<form name="listGroup" id="listGroup" method="post"
									action="/deletegroup.do">

									<input type="hidden" name="PAGE" /> 
									<input type="hidden" name="idProject" />
									<div>
										<input type="submit" id="submit" value="Xóa" />
									</div>
									<br />
									<table id="table_danhsach_group" cellspacing="0"
										cellpadding="0" border="1">
										<thead>
											<tr align="center">
												<td width="20"><input type="checkbox" name="checkall"
													id="checkall" onClick="checkUncheckAll(this);" /></td>
												<td width="130"><b>Tên Nhóm</b></td>
												<td width="130"><b>Nhóm Trưởng</b></td>
											</tr>
										</thead>
										<tbody>
											<logic:present name="group_list">
												<logic:iterate id="element" name="group_list">
													<tr align="center">
														<td width="20"><input type="checkbox" name="check"
															id="check"
															value="<bean:write name="element"  property="IDgroup"/>" /></td>
														<td width="130"><a
															href="/getgroup.do?IDgroup=<bean:write name="element"  property="IDgroup"/>&idProject=<%=idProject%>&PAGE=<%=page_pos%>"><bean:write
																	name="element" property="groupname" /></a></td>
														<td width="130"><b><bean:write name="element"
																	property="leader" /></b></td>
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
