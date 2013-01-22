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
<title>Web site quản lý phần mềm</title>
    <link rel="stylesheet" href="css/style1.css" />
<script src="javascripts/check.js" type="text/javascript"></script>
<script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
<script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>
<script src="javascripts/reportMine.js" type="text/javascript"></script>
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

</head>
<body>
    <div class="BodyContent">

    <div class="BorderBorder"><div class="BorderBL"><div></div></div><div class="BorderBR"><div></div></div><div class="BorderTL"></div><div class="BorderTR"><div></div></div><div class="BorderT"></div><div class="BorderR"><div></div></div><div class="BorderB"><div></div></div><div class="BorderL"></div><div class="BorderC"></div><div class="Border">

       <jsp:include page="module/header1.jsp" />
        <div class="Columns"><div class="Column1">
        <div class="BlockBorder"><div class="BlockBL"><div></div></div><div class="BlockBR"><div></div></div><div class="BlockTL"></div><div class="BlockTR"><div></div></div><div class="BlockT"></div><div class="BlockR"><div></div></div><div class="BlockB"><div></div></div><div class="BlockL"></div><div class="BlockC"></div><div class="Block">

            <span class="BlockHeader"><span>Tìm kiếm</span></span>
            <div class="BlockContentBorder">

              <form name="sortForm" id="sortForm" method="post"
					action="/deleteReportMineMine.do">
					
						Dự án: <select name="project" id="box" onChange="getListReportMine(1,1)">
							<logic:present name="idProList">
								<logic:iterate id="element" name="idProList">
									<option value="<bean:write name="element" property="id"/>">
														<bean:write name="element" property="name"/>
									</option>
								</logic:iterate>
							</logic:present>
						</select>
					<br>
					<logic:notEmpty name="idReqList">
						
							Yêu cầu: <select name="req" id="box" onChange="getListReportMine(2,1)">
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
								onChange="getListReportMine(3,1)">
								<logic:iterate id="element" name="idGroupList">
									<option value="<bean:write name="element" property="id"/>">
														<bean:write name="element" property="name"/>
									</option>
								</logic:iterate>
							</select>
						<br>
					</logic:notEmpty>
						Trạng thái: 
						<select name="status" id="box" onChange="getListReportMine(5,1)">
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
					<br>
						Trang:<select name="page" id="select_page"
							onchange="getListReportMineByPage(this.options[this.selectedIndex].text)">
							<logic:present name="record_page_list">
								<logic:iterate id="item" name="record_page_list">
									<option value="<bean:write name="item"/>">
										<bean:write name="item" />
									</option>

								</logic:iterate>
							</logic:present>
						</select>

				</form>
              

            </div>

        </div></div>


        <div class="BlockBorder"><div class="BlockBL"><div></div></div><div class="BlockBR"><div></div></div><div class="BlockTL"></div><div class="BlockTR"><div></div></div><div class="BlockT"></div><div class="BlockR"><div></div></div><div class="BlockB"><div></div></div><div class="BlockL"></div><div class="BlockC"></div><div class="Block">

            <span class="BlockHeader"><span>Trạng thái</span></span>
            <div class="BlockContentBorder">

                <ul>
                <logic:notEqual name="record_sort" property="level" value="4">
                <li>
				<a href="/displayReportList.do"> Danh sach review</a></li>
				<li>
				<a href="/displayReportListMine.do"> Báo cáo của tôi</a></li>
				</logic:notEqual>
				<logic:equal name="record_sort" property="level" value="4">
				<li> Bao cao của tôi</li>
				</logic:equal>
                </ul>

            </div> 

        </div></div>

        </div><div class="Column2">

        <div class="BlockBorder"><div class="BlockBL"><div></div></div><div class="BlockBR"><div></div></div><div class="BlockTL"></div><div class="BlockTR"><div></div></div><div class="BlockT"></div><div class="BlockR"><div></div></div><div class="BlockB"><div></div></div><div class="BlockL"></div><div class="BlockC"></div><div class="Block">

            <span class="BlockHeader"><span>
					<logic:equal name="report" property="mode" value="1">
						Thêm mới
					</logic:equal>
					<logic:equal name="report" property="mode" value="2">
						Chỉnh sửa
					</logic:equal>
					</span></span>
            <div class="BlockContentBorder">
            <form name="uploadFile"
				action="<%=blobstoreService.createUploadUrl("/uploadFileMine.do")%>"
				method="post" enctype="multipart/form-data" id="uploadForm">
					<input type="file" name="myFile" class="buttonBG" id="myFile" size="10">
					<input id="uploadAction" type="submit" value="Upload File"
						class="buttonBG" width="20">

			</form>
<html:form action="/updateReportMine.do" method="get">
<html:errors /> <logic:messagesPresent
									message="true">
									<!-- display message return by action-->
									<html:messages id="message" message="true">
										<bean:write name="message" />
									</html:messages>
								</logic:messagesPresent> <html:hidden property="mode"></html:hidden>
					Tập tin :<br>
					<logic:present name="report_file_name">
									<bean:write name="report_file_name" />
									<input type="hidden" id="fileId" value="<bean:write name="report_file_id" />"/>
					</logic:present>	
					<logic:notPresent name="report_file_name"> 
							<logic:equal name="report" property="mode" value="2">
							<bean:write name="report" property="fileName"/>
							<html:hidden property="fileId" styleId="fileId"></html:hidden>
						
							<%-- <input type="hidden" id="fileName" value="<bean:write name="report" property="fileId" />"/> --%>
							</logic:equal>
							<logic:equal name="report" property="mode" value="1">
								<input type="hidden" id="fileId" value=""/>
							</logic:equal>
							</logic:notPresent>
						
						<logic:present name="report">
							<logic:equal name="report" property="mode" value="2">
									Mã tập tin:<br>
									<bean:write name="report" property="id" />
									<%-- <html:hidden property="id" value="<bean:write name="report" property="id" />"/> --%>
							</logic:equal>
						</logic:present>
							Tiêu đề:<br>
							<html:text property="title" /><br>	
							Nội dung:<br>
							<html:textarea property="content"></html:textarea><br>	
							
						<logic:present name="sortForm">
						<logic:notEqual name="sortForm" property="level" value="4">
							Comment:<br>
							<td><html:textarea property="comment"></html:textarea><br>
						<logic:equal name="sortForm" property="level" value="4">
							Comment:<br>
							<bean:write name="report" property="comment"/><br>
						</logic:equal>
						</logic:notEqual>
						</logic:present>
							
						<logic:present name="report">
						<logic:equal name="report" property="mode" value="2">
							Trạng thái:<br>
							<html:select property="status">
										<logic:present name="record_sort" >
										<!-- New -->
										<logic:equal value="New" name="record_sort" property="status">
										
										<html:option value="New">New</html:option>
										
										</logic:equal>
										
										<!-- Review -->
										<logic:equal value="Review" name="record_sort" property="status">
										<html:option value="Review">Review</html:option>
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
										</logic:equal>
										</logic:present>
										</html:select>
								
						</logic:equal>
						</logic:present>
						
						<logic:present name="record_sort" >
								
									<logic:equal value="New" name="record_sort" property="status">
									<logic:equal name="report" property="mode" value="1" >
									 <span class="ButtonInput"><span><input type="submit" value="Thực hiện" /></span></span> 
									</logic:equal>
									<logic:equal name="report" property="mode" value="2" >
									 <span class="ButtonInput"><span><input type="submit" value="Thực hiện" /></span></span> 
									</logic:equal>
									</logic:equal>
									<logic:equal value="Request_update" name="record_sort" property="status">
										 <span class="ButtonInput"><span><input type="submit" value="Thực hiện" /></span></span> 
									
								</logic:equal>
									</logic:present>
									
															

</html:form>
										

                <!-- <span class="ButtonInput"><span><input type="button" value="Thực hiện" /></span></span> --> 
               <div class="BlockContentBorder">

               

            </div>

            </div>

        </div></div></div><div class="MainColumn">
        <div class="ArticleBorder"><div class="ArticleBL"><div></div></div><div class="ArticleBR"><div></div></div><div class="ArticleTL"></div><div class="ArticleTR"><div></div></div><div class="ArticleT"></div><div class="ArticleR"><div></div></div><div class="ArticleB"><div></div></div><div class="ArticleL"></div><div class="ArticleC"></div><div class="Article">

        <h2>Báo cáo của tôi</h2> <br>
        <form action="/deleteReq.do">
        <logic:present name="record_sort">
						<input type="submit" id="submit" value="Xóa"/>	
						<a href="/changeModeReportMine.do">|Thêm mới </a>
						
						</logic:present>

        <table id="table_report_list" cellspacing="0" cellpadding="0"
							border="1">
							<thead>
								<tr align="center">
									<td width="20"><input type="checkbox" name="checkall"
										id="checkall" onClick="checkUncheckAll(this);" />
									</td>
									<td width="70"><b>Mã báo cáo</b>
									</td>
									<td width="130"><b>Tiêu đề</b>
									</td>
									<td width="130"><b>Người gởi</b>
									</td>
									<td width="70"><b>Tập tin</b>
									</td>
								</tr>
							</thead>
							<tbody>
								<logic:present name="record_list">
									<logic:iterate id="item" name="record_list">
										<tr align="center">
											<td><input type="checkbox" name="check" id="checkall"
												value="<bean:write name="item" property="id"/>" /></td>
											<td width="70"><b><a
													href="/displayReportMine.do?id=<bean:write name="item" property="id"/>"><bean:write
															name="item" property="id" />
												</a> </b>
											</td>
											<td width="130"><b><bean:write name="item"
														property="title" />
											</b>
											</td>
											<td width="130"><b><bean:write name="item"
														property="idUser" />
											</b>
											</td>
											<td width="70"><b><a href="/downloadFile.do?id=<bean:write name="item" property="fileId"/>">DownLoad</a> </b>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
							</tbody>
						</table>
</form>
        </div></div>


</div></div>
        <div class="Footer">
           UIT - Quản lý phần mềm
        </div>                

    </div></div>
    </div>
    <!-- <span class="BackLink"><a href="http://cooltemplate.com">Web Template</a> created using Cool Template</span> -->
</body>
</html>
