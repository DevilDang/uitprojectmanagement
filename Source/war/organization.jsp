<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE html>
<html>
<head>
<title>Web site quản lý phần mềm</title>
<link rel="stylesheet" href="css/style1.css" />
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script src="javascripts/check.js" type="text/javascript"></script>
<script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
<script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>
<script src="javascripts/requirement.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="javascripts/SpryTabbedPanels.js" type="text/javascript"></script>

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

								<%-- <span class="BlockHeader"><span>Tìm kiếm</span></span>
								<div class="BlockContentBorder">

									<form name="sortForm" id="sortForm" method="post"
										action="/deleteReq.do">

										Dự án: <select name="project" id="box"
											onChange="getListReq(1,1)">
											<logic:present name="idProList">
												<logic:iterate id="element" name="idProList">
													<option value="<bean:write name="element" property="id"/>">
														<bean:write name="element" property="name"/>
													</option>
												</logic:iterate>
											</logic:present>
										</select> <br>
										<logic:notEmpty name="idReqList">
						
							Yêu cầu: <select name="req" id="box" onChange="getListReq(2,1)">
												<logic:iterate id="element" name="idReqList">
													<option value="<bean:write name="element" property="id"/>">
														<bean:write name="element" property="name"/>
													</option>
												</logic:iterate>
											</select>
											<br>
										</logic:notEmpty>
										<logic:notEmpty name="idGroupList">
						
							Nhóm thực hiện: <select name="group" id="box"
												onChange="getListReq(3,1)">
												<logic:iterate id="element" name="idGroupList">
													<option value="<bean:write name="element" property="id"/>">
														<bean:write name="element" property="name"/>
													</option>
												</logic:iterate>
											</select>
											<br>
										</logic:notEmpty>

										Trạng thái: <select name="status" id="box"
											onChange="getListReq(4,1)">
											<logic:present name="record_sort">
												<logic:equal value="Open" name="record_sort"
													property="status">
													<option value="Open">Open</option>
													<option value="Close">Close</option>
												</logic:equal>
												<logic:equal value="Close" name="record_sort"
													property="status">
													<option value="Close">Close</option>
													<option value="Open">Open</option>
												</logic:equal>
											</logic:present>
										</select> <br> Trang:<select name="page" id="select_page"
											onchange="getListReqByPage(this.options[this.selectedIndex].text)">
											<logic:present name="record_page_list">
												<logic:iterate id="item" name="record_page_list">
													<option value="<bean:write name="item"/>">
														<bean:write name="item" />
													</option>

												</logic:iterate>
											</logic:present>
										</select>

									</form>


								</div> --%>

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

								<span class="BlockHeader"><span> 
								<logic:present name="flagOrg">
											 <logic:equal name="flagOrg" value="1">
						Thêm mới
					</logic:equal>
											
                            <logic:equal name="flagOrg" value="2">
						Chỉnh sửa
					</logic:equal>
					</logic:present>
								</span></span>
								<div class="BlockContentBorder">
									<html:form action="/updateOrg.do" method="get">
										<html:errors />
										<logic:messagesPresent message="true">
											<!-- display message return by action-->
											<html:messages id="message" message="true">
												<bean:write name="message" />
											</html:messages>
										</logic:messagesPresent>
										
										<logic:present name="flagOrg">
											<logic:equal name="flagOrg" value="2">
								Mã Đối tác:<br>
												<span style='color: red'><bean:write name="organization" property="idOrg"/>     </span>
												<br>
											</logic:equal>
										</logic:present>
Tên đối tác:<br>
										 <html:text property="nameOrg"  />  
										<br>

									
Địa Chỉ: <br>
										<html:text property="addOrg" /> 
										<br>
Website:<br>
										<html:text property="websiteOrg" />  
										<br>
Số Điện thoại: <br>
										<html:text property="numberOrg" />  
										<br>



													<span class="ButtonInput"><span><input
															type="submit" value="Thực hiện" /></span></span>
									</html:form>


									<!-- <span class="ButtonInput"><span><input type="button" value="Thực hiện" /></span></span> -->
									<div class="BlockContentBorder"></div>

								</div>

							</div>
						</div>
					</div>
					<div class="MainColumn">
        <div class="ArticleBorder"><div class="ArticleBL"><div></div></div><div class="ArticleBR"><div></div></div><div class="ArticleTL"></div><div class="ArticleTR"><div></div></div><div class="ArticleT"></div><div class="ArticleR"><div></div></div><div class="ArticleB"><div></div></div><div class="ArticleL"></div><div class="ArticleC"></div><div class="Article">

        <h2>Danh sách đối tác</h2> <br>
        <p>Số lượng:
                        <logic:present name="org_total_number">
                        <bean:write name="org_total_number"/>
                        </logic:present>
                        </p>
        <form action="/deleteOrganization.do">
        <input type="submit" id="submit" value="Xóa"/>	
        
        <logic:present name="record_sort">
        <logic:equal value="3" name="record_sort" property="level">
						<input type="submit" id="submit" value="Xóa"/>	
						<a href="/changeModeTask.do">|Thêm mới </a>
							</logic:equal>
						</logic:present>

        <table id="table_task_list" cellspacing="0" cellpadding="0"
							border="1">
							<thead>
								<tr align="center">
									<td width="20"><input type="checkbox" name="checkall"
										id="checkall" onClick="checkUncheckAll(this);" />
									</td>
									<td width="70"><b>Mã Đối Tác</b>
									</td>
									<td width="130"><b>Tên Đối Tác</b>
									</td>
									<td width="130"><b>Tên Đối Tác</b>
								</tr>
							</thead>
							<tbody>
								<logic:present name="org_list">
									<logic:iterate id="item" name="org_list">
										<tr align="center">
											<td><input type="checkbox" name="check" id="checkall"
												value="<bean:write name="item" property="idOrg"/>" /></td>
											<td width="70"><b><a
													href="/displayTask.do?id=<bean:write name="item" property="idOrg"/>"><bean:write
															name="item" property="idOrg" />
												</a> </b>
											</td>
											<td width="130"><b><bean:write name="item"
														property="nameOrg" />
											</b>
											</td>
											<td width="130"><b><bean:write name="item"
														property="addOrg" />
											</b>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
							</tbody>
						</table>
</form>
        </div></div>


</div>
				</div>
				<div class="Footer">UIT - Quản lý phần mềm</div>

			</div>
		</div>
	</div>
	<!-- <span class="BackLink"><a href="http://cooltemplate.com">Web Template</a> created using Cool Template</span> -->
</body>

</html>
