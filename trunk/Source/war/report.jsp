<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
%>
<html>
<head>
<title>Quản Lý Báo Cáo</title>
<link rel="stylesheet" type="text/css" href="default.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css"
	href="css/SpryMenuBarHorizontal.css" />
<script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
<script src="javascripts/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="javascripts/json.js" type="text/javascript"></script>
<script src="javascripts/check.js" type="text/javascript"></script>
<script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
	 $(document).ready(function() {
		$("#idSubmit").click(function() {
	        var flag =  checkSubmit();
	        if(!flag){
	            return false;
	        }
	    });
	}); 
</script>

	<div id="container">
		<jsp:include page="module/header.jsp" />
		<div id="nav_sub">
			<div id="nav_sub_left">
				<div class="logo">
					<img src="images/logo.png" />
				</div>
				<div class="logo">
					<h1>Quản Lý Báo Cáo</h1>
				</div>
			</div>
			<!--end nav_sub_left-->
		</div>
		<!--end nav_sub-->
		<div id="content">
			<div id="content_left">
				<h3 align="center">
				<%-- <logic:present name="record_sort"> --%>
				<logic:notEqual name="record_sort" property="level" value="4">
				<a href="/displayReportListMine.do"> Danh sach review</a>
				|<a href="/displayReportListMine.do"> Bao cao cua toi</a>
				</logic:notEqual>
				<logic:equal name="record_sort" property="level" value="4">
				 Bao cao cua toi
				</logic:equal>
				<%-- </logic:present> --%>
				</h3>
				<br>

				<form name="sortForm" id="sortForm" method="post"
					action="/deleteReport.do">
					<div class="chose3" align="center">
						Dự Án: <select name="project" id="box"
							onChange="getListReport(1,1)">
							<logic:iterate id="element" name="idProList">
							<%-- <c:choose>
							<c:when test="${element == record_sort.idProject }">
							<option value="/displayReportPaging.do?idProject=<bean:write name="element"/>" selected>
									<bean:write name="element" />
							</option>
							</c:when>
							<c:otherwise>
							<option value="/displayReportPaging.do?idProject=<bean:write name="element"/>" >
									<bean:write name="element" />
							</option>
							</c:otherwise>
							</c:choose> --%>
								<option value="/displayReportPaging.do?idProject=<bean:write name="element"/>" >
									<bean:write name="element" />
							</option>
							</logic:iterate>
						</select>
					</div>
					<logic:notEmpty name="idReqList">
						<div class="chose3" align="center">
							Yêu cầu: <select name="req" id="box"
								onChange="getListReport(2,1)">
							<logic:iterate id="element" name="idReqList">
							<%-- <c:choose>
							<c:when test="${element == record_sort.idReq }">
							<option value="" selected>
										<bean:write name="element" />
									</option>
							</c:when>
							<c:otherwise>
							<option value="" >
										<bean:write name="element" />
							</option>
							</c:otherwise>
							</c:choose> --%>
								<option value="" selected>
										<bean:write name="element" />
									</option>
							</logic:iterate>
							</select>
						</div>
					</logic:notEmpty>
					<logic:notEmpty name="idGroupList">
						<div class="chose3" align="center">
							Nhóm thực hiện: <select name="group" id="box"
								onChange="getListReport(3,1)">
								<logic:iterate id="element" name="idGroupList">
								<%-- <c:choose>
								<c:when test="${element == record_sort.idGroup }">
								<option value="" selected>
										<bean:write name="element" />
								</option>
								</c:when>
								<c:otherwise>
								</c:otherwise>
								<option value="">
										<bean:write name="element" />
									</option>
								</c:choose> --%>
								<option value="">
										<bean:write name="element" />
									</option>
								</logic:iterate>
							</select>
						</div>
					</logic:notEmpty>
					<logic:notEmpty name="idTaskList">
						<div class="chose3" align="center">
							Công việc được giao: <select name="task" id="box"
								onChange="getListReport4,1)">
								<logic:iterate id="element" name="idTaskList">
								<%-- <c:choose>
								<c:when test="${element == record_sort.idTask }">
								<option value="" selected>
										<bean:write name="element" />
									</option>
								</c:when>
								<c:otherwise>
								<option value="">
										<bean:write name="element" />
									</option>
								</c:otherwise>
								</c:choose> --%>
								<option value="">
										<bean:write name="element" />
									</option>
									
								</logic:iterate>
							</select>
						</div>
					</logic:notEmpty>
					<div class="chose3" align="center">
						Trạng thái 
						<!-- onChange="getListReportByStatus(1) ; ONCHANGE="location = this.options[this.selectedIndex].value;"" -->
						<select name="status" id="box" onChange="getListReport(5,1)">
							<logic:equal name="record_sort" property="status" value="New"><option value="New" selected="selected">New</option></logic:equal>
							<logic:notEqual name="record_sort" property="status" value="New"><option value="New" >New</option></logic:notEqual>
							
							<logic:equal name="record_sort" property="status" value="Review"><option value="Review" selected="selected">Review</option></logic:equal>
							<logic:notEqual name="record_sort" property="status" value="Review"><option value="Review" >Review</option></logic:notEqual>
							
							<logic:equal name="record_sort" property="status" value="Request_update"><option value="Request_update" selected="selected">Request_update</option></logic:equal>
							<logic:notEqual name="record_sort" property="status" value="Request_update"><option value="Request_update" >Request_update</option></logic:notEqual>
							
							<logic:equal name="record_sort" property="status" value="Updated"><option value="Updated" selected="selected">Updated</option></logic:equal>
							<logic:notEqual name="record_sort" property="status" value="Updated"><option value="Updated" >Updated</option></logic:notEqual>
							
							<logic:equal name="record_sort" property="status" value="Finish"><option value="Finish" selected="selected">Finish</option></logic:equal>
							<logic:notEqual name="record_sort" property="status" value="Finish"><option value="Finish" >Finish</option></logic:notEqual>
						</select>
					</div>
					<p>&nbsp;</p>
					<p>
						<br>
					</p>
					<div id="table">
						<table id="table_report_list" cellspacing="0" cellpadding="0"
							border="1">
							<thead>

								<tr align="center">
								
									<td width="20"><input type="checkbox" name="checkall"
										id="checkall" onClick="checkUncheckAll(this);" /></td>
									<td width="50"><b>Số Báo Cáo</b></td>
									<td width="130"><b>Tiêu đề</b></td>
									<td width="130"><b>Người gửi</b></td>
									<td width="70"><b>Tập tin</b></td>
								</tr>

							</thead>
							<tbody>
								<logic:iterate id="item" name="record_list">
									<tr align="center">
									<td><input type="checkbox" name="check" id="checkall"  value="<bean:write name="item" property="id"/>"/> </td>
										<!-- <td width="20"><input type="checkbox" name="checkall"
											id="checkall" onClick="checkUncheckAll(this);" /></td> -->
										<td width="70"><b><a href="/displayReport.do?id=<bean:write name="item" property="id"/>"><bean:write name="item" property="id"/></a> </b></td>
										<td width="130"><b><bean:write name="item" property="title"/></b></td>
										<td width="130"><b><bean:write name="item" property="idUser"/></b></td>
										<td width="70"><b><a href="/downloadFile.do?id=<bean:write name="item" property="fileId"/>">DownLoad</a> </b></td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<div id="phantrang" class="chose3" align="center">
						<select name="page" id="select_page" onchange="getListReportByPage(this.options[this.selectedIndex].text)" >
							<logic:iterate id="item" name="record_page_list">
							<option value="<bean:write name="item"/>" selected><bean:write name="item"/></option>
							<%-- <c:choose>
							<c:when test="${item == record_page_number}">
							<option value="" selected><bean:write name="item"/></option>
							</c:when>
							<c:otherwise>
							<option value="/dislayReportListPaging.do?page=<bean:write name="item"/>" ><bean:write name="item"/></option>
							</c:otherwise>
							</c:choose> --%>
							
							<%-- <logic:equal value="<bean:write name="item"/>" name="report_page_number">
							<option value="/dislayReportListPaging.do?page=<bean:write name="item"/>" selected><bean:write name="item"/></option>
							</logic:equal>
							 --%>
							<%-- <logic:notEqual value="<bean:write name="item"/>" name="report_page_number">
							<option value="/dislayReportListPaging.do?page=<bean:write name="item"/>" ><bean:write name="item"/></option>
							</logic:notEqual> --%>
							</logic:iterate>
						</select>

					</div>
					<div class="chose3" align="center">
						<input type="submit" id="submit" value="Xóa"
							style="height: 25px; width: 100px" >
						<input type="hidden" name="KEY" value="XOA_MONHOC">

					</div>
				</form>
				<div></div>
			</div>
			<!--end content left-->

			<form name="uploadFile"
				action="<%=blobstoreService.createUploadUrl("/uploadFile.do")%>"
				method="post" enctype="multipart/form-data" id="uploadForm">
				<div id="content_right">
					<input type="file" name="myFile" class="buttonBG" id="myFile">
					<input id="uploadAction" type="submit" value="Upload File"
						class="buttonBG">
				</div>

			</form>
			<html:form action="/updateReport.do" method="get">
				<div id="content_right">
					<h3 align="center">
						Chỉnh sửa 
						<logic:present name="record_sort" >
						<logic:equal value="4" name="record_sort"  property="level">
						| <a href="/changeModeReport.do?mode=1">Thêm mới </a>
						</logic:equal>
						</logic:present>
						
					</h3>
					<br>

					<table id="table_monhoc" class="table_right" cellspacing="5"
						cellpadding="0" border="0">
						<thead>
						</thead>
						<tr>
							<td>Tập tin :</td>
							<td><logic:present name="report_file_name">
									<bean:write name="report_file_name" />
									<input type="hidden" id="fileId" value="<bean:write name="report_file_id" />"/>
								</logic:present>
								
								<!-- Update -->
								<logic:notPresent name="report_file_name"> 
							<logic:equal name="record_flag" value="2">
							<bean:write name="report" property="fileName"/>
							<html:hidden property="fileId" styleId="fileId"></html:hidden>
						
							<%-- <input type="hidden" id="fileName" value="<bean:write name="report" property="fileId" />"/> --%>
							</logic:equal>
							<logic:equal name="record_flag" value="1">
								<input type="hidden" id="fileId" value=""/>
							</logic:equal>
							</logic:notPresent>
							<html:hidden property="level"/>
							</td>
						
						</tr>
						<logic:present name="record_flag">
							<logic:equal name="record_flag" value="2">
								<tr >
									<td width="100">Mã tập tin:</td>
									<td width="300"><bean:write name="report" property="id" />
									</td>
									<%-- <html:hidden property="id" value="<bean:write name="report" property="id" />"/> --%>
								</tr>
							</logic:equal>
						</logic:present>
						<tr>
							<td width="100">Tiêu đề:</td>
							<td width="300"><html:text property="title" />
							</td>						</tr>

						<tr>
						<tr>
							<td>Nội dung:</td>
							<td><html:textarea property="content"></html:textarea>
							</td>
						</tr>
						<logic:present name="sortForm">
						<logic:notEqual name="sortForm" property="level" value="4">
						<tr>
							<td>Comment:</td>
							<td><html:textarea property="comment"></html:textarea>
							</td>
						</tr>
						<logic:equal name="sortForm" property="level" value="4">
						<tr>
							<td>Comment:</td>
							<td><bean:write name="report" property="comment"/>
							</td>
						</tr>
						</logic:equal>
						</logic:notEqual>
						</logic:present>
						<logic:present name="record_flag">
						<logic:equal name="record_flag" value="2">
						<tr>
							<td width="100">Trạng thái:</td>
							<td width="300"><html:select property="status">
										<logic:present name="record_sort" >
										<!-- New -->
										<logic:equal value="New" name="record_sort" property="status">
										
										<html:option value="New">New</html:option>
										
										<logic:notEqual value="4" name="record_sort" property="level">
										<html:option value="Review">Review</html:option>
										</logic:notEqual>
										</logic:equal>
										
										<!-- Review -->
										<logic:equal value="Review" name="record_sort" property="status">
										<html:option value="Review">Review</html:option>
										<logic:notEqual value="4" name="record_sort" property="level">
										<html:option value="Request_update">Request_update</html:option>
										</logic:notEqual>
										</logic:equal>
										
										<!-- Request_update -->
										<logic:equal value="Request_update" name="record_sort" property="status">
										<html:option value="Request_update">Request_update</html:option>
										<logic:equal value="4" name="record_sort" property="level">
										<html:option value="Updated">Updated</html:option>
										</logic:equal>
										</logic:equal>
										<!-- Updated -->
										<logic:equal value="Updated" name="record_sort" property="status">
										<html:option value="Updated">Updated</html:option>
										<logic:notEqual value="4" name="record_sort" property="level">
										<html:option value="Request_update">Request_update</html:option>
										<html:option value="Finish">Finish</html:option>
										</logic:notEqual>
										</logic:equal>
										<!-- Finish -->
										<logic:equal value="Finish" name="record_sort" property="status">
										<html:option value="Finish">Finish</html:option>
										</logic:equal>
										</logic:present>
										</html:select>
										<%-- <logic:iterate id="item" name="statusList">
											<html:option value=""><bean:write name="item" /></html:option>
											</logic:iterate> --%>
										<%-- </logic:equal>
									</logic:present> --%>
								
							</td>
						</tr>
						</logic:equal>
						</logic:present>
					</table>
					<table>
						<tr>
							<td width="170" align="right"><div id="bt_reset">
									<input type="reset" name="reset" id="reset" value="Reset"
										style="height: 25px; width: 100px"
										onClick="reset_validate_form_monhoc()">
								</div>
							</td>
							<td width="40"></td>
							<td width="150" align="left">
								<div id="bt_submit">
								<logic:present name="record_sort" >
								<!-- Leader, PM,Admin -->
								<logic:notEqual value="4" name="record_sort" property="level">
									<logic:equal value="New" name="record_sort" property="status">
										<input type="submit"  value="OK" 
										style="height: 25px; width: 100px"  >
									</logic:equal>
									<logic:equal value="Review" name="record_sort" property="status">
										<input type="submit" id="submit" value="OK"
										style="height: 25px; width: 100px"  >
									</logic:equal>
									
									<logic:equal value="Updated" name="record_sort" property="status">
										<input type="submit" id="submit" value="OK" 
										style="height: 25px; width: 100px" >
									</logic:equal>
								</logic:notEqual> 
								
								<logic:equal value="4" name="record_sort" property="level">
									<logic:equal value="New" name="record_sort" property="status">
									<logic:equal value="1" name="record_flag">
									<input type="submit" id="idSubmit" value="OK"
									style="height: 25px; width: 100px" >
									</logic:equal>
									<logic:equal value="2" name="record_flag">
									<input type="submit" id="submit" value="OK"
									style="height: 25px; width: 100px" >
									</logic:equal>
									</logic:equal>
									<logic:equal value="Request_update" name="record_sort" property="status">
										<input type="submit" id="submit" value="OK"
										style="height: 25px; width: 100px" >
									</logic:equal>
									
								</logic:equal>
									</logic:present>
									
								</div>
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
