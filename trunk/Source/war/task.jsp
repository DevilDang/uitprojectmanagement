<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
<head>
<title>Web site quản lý phần mềm</title>
    <link rel="stylesheet" href="css/style1.css" />
<link rel="stylesheet" type="text/css"
	href="javascripts/ui/themes/base/ui.all.css" />
<script src="javascripts/check.js" type="text/javascript"></script>
<script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
<script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>
<script src="javascripts/task.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="javascripts/ui/ui.datepicker_vn.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#datepicker1").datepicker({
			dateFormat : 'dd/mm/yy'
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
					action="/deleteTask.do">
					
						Dự án: <select name="project" id="box" onChange="getListTask(1,1)">
							<logic:present name="idProList">
								<logic:iterate id="element" name="idProList">
									<option
										value="">
										<bean:write name="element" />
									</option>
								</logic:iterate>
							</logic:present>
						</select>
					<br>
					<logic:notEmpty name="idReqList">
						
							Yêu cầu: <select name="req" id="box" onChange="getListTask(2,1)">
								<logic:iterate id="element" name="idReqList">
									<option value="" selected>
										<bean:write name="element" />
									</option>
								</logic:iterate>
							</select>
						<br>
					</logic:notEmpty>
					<logic:notEmpty name="idGroupList">
						
							Nhóm thực hiện: <select name="group" id="box"
								onChange="getListTask(3,1)">
								<logic:iterate id="element" name="idGroupList">
									<option value="">
										<bean:write name="element" />
									</option>
								</logic:iterate>
							</select>
						<br>
					</logic:notEmpty>
					Loại công việc: <select name="kind" id="box"
								onChange="getListTask(4,1)">
								<!-- Test -->
								<logic:equal value="Test" name="record_sort" property="kind">
								<option value="Test" selected>Test</option>
								<option value="TestCase" >TestCase</option>
								<option value="UseCase" >UseCase</option>
								<option value="BanGiao" >BanGiao</option>
								<option value="Code" >Code</option>
								</logic:equal>
								<!-- TestCase -->
								<logic:equal value="TestCase" name="record_sort" property="kind">
								<option value="TestCase" selected>TestCase</option>
								<option value="Test" >Test</option>
								<option value="UseCase" >UseCase</option>
								<option value="BanGiao" >BanGiao</option>
								<option value="Code" >Code</option>
								</logic:equal>
								<!-- UseCase -->
								<logic:equal value="UseCase" name="record_sort" property="kind">
								<option value="UseCase" selected>UseCase</option>
								<option value="TestCase" >TestCase</option>
								<option value="Test" >Test</option>
								<option value="BanGiao" >BanGiao</option>
								<option value="Code" >Code</option>
								</logic:equal>
								<!-- BanGiao -->
								<logic:equal value="BanGiao" name="record_sort" property="kind">
								<option value="BanGiao" selected>BanGiao</option>
								<option value="UseCase" >UseCase</option>
								<option value="TestCase" >TestCase</option>
								<option value="Test" >Test</option>
								<option value="Code" >Code</option>
								</logic:equal>
								<!-- Code -->
								<logic:equal value="Code" name="record_sort" property="kind">
								<option value="Code" selected>Code</option>
								<option value="UseCase" >UseCase</option>
								<option value="TestCase" >TestCase</option>
								<option value="Test" >Test</option>
								<option value="BanGiao" >BanGiao</option>
								</logic:equal>
							</select><br>
						Trạng thái: <select name="status" id="box"
							onChange="getListTask(4,1)">
							<logic:present name="record_sort">
								<logic:equal value="Open" name="record_sort" property="status">
									<option value="Open">Open</option>
									<option value="Close">Close</option>
								</logic:equal>
								<logic:equal value="Close" name="record_sort" property="status">
									<option value="Close">Close</option>
									<option value="Open">Open</option>
								</logic:equal>
							</logic:present>
						</select><br>
					<br>
						Trang:<select name="page" id="select_page"
							onchange="getListTaskByPage(this.options[this.selectedIndex].text)">
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

            <span class="BlockHeader"><span>Người dùng</span></span>
            <div class="BlockContentBorder">

               <logic:present name="UserLogIN" >
                <ul>
                    <li><bean:write name="UserLogIN" />
                    </li>
                </ul>
</logic:present>

            </div> 

        </div></div>

        </div><div class="Column2">

        <div class="BlockBorder"><div class="BlockBL"><div></div></div><div class="BlockBR"><div></div></div><div class="BlockTL"></div><div class="BlockTR"><div></div></div><div class="BlockT"></div><div class="BlockR"><div></div></div><div class="BlockB"><div></div></div><div class="BlockL"></div><div class="BlockC"></div><div class="Block">

            <span class="BlockHeader"><span>
           <logic:equal value="3" name="record_sort" property="level">
					<logic:equal name="task" property="mode" value="1">
						Thêm mới
					</logic:equal>
					<logic:equal name="task" property="mode" value="2">
						Chỉnh sửa
					</logic:equal>
			</logic:equal>
			<logic:notEqual value="3" name="record_sort" property="level">
						Thông tin chi tiết
			</logic:notEqual>
					</span></span>
            <div class="BlockContentBorder">
<html:form action="/updateTask.do" method="get">
<html:errors /> <logic:messagesPresent
									message="true">
									<!-- display message return by action-->
									<html:messages id="message" message="true">
										<bean:write name="message" />
									</html:messages>
								</logic:messagesPresent> <html:hidden property="mode"></html:hidden>
						<logic:present name="task">
							<logic:equal name="task" value="2" property="mode">
								Mã công việc:<br>
								<span style='color:red'><bean:write name="task" property="id" /></span><br>
							</logic:equal>
						</logic:present>
Tên công việc:<br>
<html:text property="nameTask" size="22"></html:text><br>
Loại công việc:<br>
<html:select property="kind" >
                                   <logic:equal value="3" name="record_sort" property="level">
                                      <logic:equal value="Test" name="record_sort" property="kind">
                                       <html:option value="Test" >Test</html:option>
                                       <html:option value="TestCase" >TestCase</html:option>
                                       <html:option value="UseCase" >UseCase</html:option>
                                       <html:option value="BanGiao" >BanGiao</html:option>
                                       <html:option value="Code" >Code</html:option>
                                   	  </logic:equal>
                                   	  <logic:equal value="TestCase" name="record_sort" property="kind">
                                       <html:option value="Test" >Test</html:option>
                                       <html:option value="TestCase" >TestCase</html:option>
                                       <html:option value="UseCase" >UseCase</html:option>
                                       <html:option value="BanGiao" >BanGiao</html:option>
                                       <html:option value="Code" >Code</html:option>
                                   	  </logic:equal>
                                   	  <logic:equal value="UseCase" name="record_sort" property="kind">
                                   	  <html:option value="UseCase" >UseCase</html:option>
                                       <html:option value="Test" >Test</html:option>
                                       <html:option value="TestCase" >TestCase</html:option>
                                       <html:option value="BanGiao" >BanGiao</html:option>
                                       <html:option value="Code" >Code</html:option>
                                   	  </logic:equal>
                                   	   <logic:equal value="BanGiao" name="record_sort" property="kind">
                                   	   <html:option value="BanGiao" >BanGiao</html:option>
                                       <html:option value="Test" >Test</html:option>
                                       <html:option value="TestCase" >TestCase</html:option>
                                       <html:option value="UseCase" >UseCase</html:option>
                                       <html:option value="Code" >Code</html:option>
                                   	  </logic:equal>
                                   	   <logic:equal value="Code" name="record_sort" property="kind">
                                   	    <html:option value="Code" >Code</html:option>
                                       <html:option value="Test" >Test</html:option>
                                       <html:option value="UseCase" >UseCase</html:option>
                                       <html:option value="BanGiao" >BanGiao</html:option>
                                   	  </logic:equal>
                                    </logic:equal>
                                    </html:select><br>
Người thực hiện:<br>
<%--   <logic:equal name="task" property="mode" value="2"> --%>
                                     <html:select property="emailEmployee" >
                                    <option value="<bean:write name="task" property="emailEmployee"/>"><bean:write name="task" property="emailEmployee"/></option>
                                   <logic:equal value="Open" name ="record_sort" property="status">
                                   <logic:equal value="3" name="record_sort" property="level">
                                    
                                    <logic:notEmpty name="task_user_free">
                                    
                                     <logic:iterate id="item" name="task_user_free">
                                     <option value="<bean:write name="item" />"><bean:write name="item" /></option>
                                    </logic:iterate>
                                    </logic:notEmpty>
                                    </logic:equal>
                                    </logic:equal>
                                    </html:select><br>
                                  <%--   </logic:equal> --%>
                                   <logic:empty name="task_user_free">
									<logic:equal name="task" property="mode" value="1">
										<span style="color: red">Mọi người đều đã có task</span>
									</logic:equal>
								</logic:empty>
								                                   

									
Mô tả: <br>
<html:textarea property="content"></html:textarea><br>
Ngày bắt đầu: <br>
<html:text property="startDate" styleId="datepicker" ></html:text><br>
Ngày kết thúc: <br>
<html:text property="endDate" styleId="datepicker1" ></html:text><br>
<logic:equal name="task" property="mode" value="2">
Tiến độ: <br>
<html:text property="process"  ></html:text><br>
Trình trạng: <br>
<span style='color:red'><bean:write name="task" property="statusTask" /></span>
<br>
<logic:equal name="task" property="statusTask" value="Đang trễ">
Số ngày trễ: <br>
<span style='color:red'><bean:write name="task" property="lateDate" /></span><br>
</logic:equal>
Trạng thái:<br>
<html:select property="status">
<logic:present name="record_sort">
												<logic:equal value="Open" name="record_sort"
													property="status">
													<html:option value="Open">Open</html:option>
													<html:option value="Close">Close</html:option>
												</logic:equal>
												<logic:equal value="Close" name="record_sort"
													property="status">
													<html:option value="Close">Close</html:option>
													<html:option value="Open">Open</html:option>
												</logic:equal>
											</logic:present>
</html:select><br>
</logic:equal>
<logic:equal name="task" property="mode" value="1">
							<logic:notEmpty name="task_user_free">
								<logic:equal value="3" name="record_sort" property="level">

												 <span class="ButtonInput"><span><input type="submit" value="Thực hiện" /></span></span> 
								</logic:equal>
							</logic:notEmpty>
						</logic:equal>
<logic:equal name="task" property="mode" value="2">
						<logic:equal value="3" name="record_sort" property="level">
							

												 <span class="ButtonInput"><span><input type="submit" value="Thực hiện" /></span></span> 
								</logic:equal>
</logic:equal>
</html:form>
										

                <!-- <span class="ButtonInput"><span><input type="button" value="Thực hiện" /></span></span> --> 
               <div class="BlockContentBorder">

               

            </div>

            </div>

        </div></div></div><div class="MainColumn">
        <div class="ArticleBorder"><div class="ArticleBL"><div></div></div><div class="ArticleBR"><div></div></div><div class="ArticleTL"></div><div class="ArticleTR"><div></div></div><div class="ArticleT"></div><div class="ArticleR"><div></div></div><div class="ArticleB"><div></div></div><div class="ArticleL"></div><div class="ArticleC"></div><div class="Article">

        <h2>Danh sách công việc</h2> <br>
        <form action="/deleteTask.do">
        <logic:present name="record_sort">
						<input type="submit" id="submit" value="Xóa"/>	<logic:equal value="3" name="record_sort" property="level">
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
									<td width="70"><b>Mã công việc</b>
									</td>
									<td width="130"><b>Tên công việc</b>
									</td>
									<td width="130"><b>Nhóm thực hiện</b>
									</td>
									<td width="70"><b>Tiến độ</b>
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
													href="/displayTask.do?id=<bean:write name="item" property="id"/>"><bean:write
															name="item" property="id" />
												</a> </b>
											</td>
											<td width="130"><b><bean:write name="item"
														property="nameTask" />
											</b>
											</td>
											<td width="130"><b><bean:write name="item"
														property="idGroup" />
											</b>
											</td>
											<td width="70"><b><bean:write name="item"
														property="process" />%</b>
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
