<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
        <title>Quản Lý Yêu Cầu</title>
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link rel="stylesheet" type="text/css" href="css/SpryMenuBarHorizontal.css"/>
        <link rel="stylesheet"type="text/css" href="javascripts/ui/themes/base/ui.all.css"  />
        <script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
        <script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>
       <script src="javascripts/task.js" type="text/javascript"></script> 
<script type="text/javascript" src="javascripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="javascripts/ui/ui.datepicker_vn.js"></script>
<script type="text/javascript">
  $(function() {
      $("#datepicker").datepicker({dateFormat: 'dd/mm/yy'});
      $("#datepicker1").datepicker({dateFormat: 'dd/mm/yy'});
  });

</script>
    </head>
    <body>
        <div id="container">           
            <jsp:include page="module/header.jsp" />
            <div id="nav_sub">
                <div id="nav_sub_left">
                    <div class="logo"><img src="images/logo.png" /></div>
                    <div class="logo"><h1> Quản Lý Công việc</h1></div>
                </div> <!--end nav_sub_left-->               
            </div><!--end nav_sub-->
            <div id="content">
                <div id="content_left">
                    <h3 align="center"> Quản lý danh sách </h3><br>
                    <form name="sortForm" id ="sortForm" method="post" action="/deleteTask.do">
                    	  <div class="chose3" align="center">
                            Dự Án:
                         
                            <select name="project" id="box"
							onChange="getListTask(1,1)">
							<logic:present name="idProList">
							<logic:iterate id="element" name="idProList">
								<option value="/displayReportPaging.do?idProject=<bean:write name="element"/>" >
									<bean:write name="element" />
							</option>
							</logic:iterate>
							</logic:present>
						</select>
                        </div>
                        <logic:notEmpty name="idReqList">
						<div class="chose3" align="center">
							Yêu cầu: <select name="req" id="box"
								onChange="getListTask(2,1)">
							<logic:iterate id="element" name="idReqList">
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
								onChange="getListTask(3,1)">
								<logic:iterate id="element" name="idGroupList">
								<option value="">
										<bean:write name="element" />
									</option>
								</logic:iterate>
							</select>
						</div>
					</logic:notEmpty>
					<div class="chose3" align="center">
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
							</select>
						</div>
					<div class="chose3" align="center">
							Trạng thái: <select name="status" id="box"
								onChange="getListTask(5,1)">
								<logic:present name="record_sort">
							<logic:equal value="Open" name="record_sort"  property="status">
							<option value="Open">Open</option>
							<option value="Close">Close</option>
							</logic:equal>
							<logic:equal value="Close" name="record_sort"  property="status">
							<option value="Close">Close</option>
							<option value="Open">Open</option>
							</logic:equal>
							</logic:present>
							</select>
						</div>
						
                        <p>&nbsp;</p>
                        <p><br>
                        </p>
                        <div id="table">
                            <table id="table_task_list" cellspacing="0" cellpadding="0" border="1">                               
                                <thead>
                                    <tr align="center">
                                        <td width="20"><input type="checkbox" name="checkall" id="checkall" onClick="checkUncheckAll(this);"/></td>
                                        <td width="70"><b>Mã Yêu Cầu</b></td>
                                        <td width="130"><b>Tên Yêu Cầu</b></td>
                                        <td width="130"><b>Nhóm Thực Hiện</b></td>
                                        <td width="70"><b>Tiến Độ</b></td>                                     
                                    </tr>
                                </thead>
                                <tbody>
                                <logic:notEmpty name="record_list">
                                <logic:iterate id="item" name="record_list">
									<tr align="center">
										<td><input type="checkbox" name="check" id="checkall"  value="<bean:write name="item" property="id"/>"/> </td>
										<td width="70"><b><a href="/displayTask.do?id=<bean:write name="item" property="id"/>"><bean:write name="item" property="id"/></a> </b></td>
										<td width="130"><b><bean:write name="item" property="nameTask"/></b></td>
										<td width="130"><b><bean:write name="item" property="idGroup"/></b></td>
										<td width="70"><b><bean:write name="item" property="process"/>%</b></td>
									</tr>
								</logic:iterate>   
								</logic:notEmpty>
                                </tbody>
                            </table>
                        </div>
                        <div id="phantrang" class="chose3" align="center">
                        <select name="page" id="select_page" onchange="getListTaskByPage(this.options[this.selectedIndex].text)" >
							<logic:present name="record_page_list">
							<logic:iterate id="item" name="record_page_list">
							<option value="<bean:write name="item"/>" selected><bean:write name="item"/></option>
							</logic:iterate>
							</logic:present>
						</select>

                        </div>
                        <div class="chose3" align="center">
                            <input type="submit" id="submit" value="Xóa" style="height: 25px; width: 100px" onClick="ajax_delete_monhoc()">
                            <input type="hidden" name="KEY" value="XOA_MONHOC">

                        </div>
                    </form>
                    <div>

                    </div>
                </div> <!--end content left-->
                <html:form action="/updateTask.do" method="get">
                    <div id="content_right">
                      <h3 align="center">
					<logic:equal value="2" name="task" property="mode">
						Chỉnh sửa| 
					</logic:equal>
						<logic:present name="record_sort">
							<logic:equal value="2" name="record_sort" property="level">
						<a href="/changeModeTask.do">Thêm mới </a>
							</logic:equal>
						</logic:present>
					</h3><br>
                        <table id="table_monhoc" class="table_right" cellspacing="5" cellpadding="0" border="0">
                            <thead>

                            </thead>
                            <tr>
                            
							<td width="100"><html:errors /> <logic:messagesPresent
									message="true">
									<!-- display message return by action-->
									<html:messages id="message" message="true">
										<bean:write name="message" />
									</html:messages>
								</logic:messagesPresent> <html:hidden property="mode"></html:hidden></td>
                            </tr>
                            <logic:present name="task">
							<logic:equal name="task" value="2" property="mode">
								<tr >
									<td width="100">Mã công việc:</td>
									<td width="300"><bean:write name="task" property="id" />
									</td>
								</tr>
							</logic:equal>
						</logic:present>
                            <tr>
                                <td>Tên công việc: </td>
                                <td><html:text property="nameTask"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>Loại Công việc: </td>
                                <td>
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
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td>Người thực hiện: </td>
                                <td>
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
                                    </html:select>
                                  <%--   </logic:equal> --%>
                                    
                                    <logic:empty name="task_user_free">
									<logic:equal name="task" property="mode" value="1">
										<span style="color: red">Mọi người đều đã có task</span>
									</logic:equal>
								</logic:empty>
                                </td>
                            </tr>
                            <tr>
                                <td>Mô tả: </td>
                                <td>
                                  <html:textarea property="content"></html:textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>Ngày Bắt Đầu: </td>
                                <td><html:text property="startDate" styleId="datepicker" ></html:text>
                                </td>
                            </tr>  
                              <tr>
                                <td>Ngày Kết Thúc: </td>
                                <td><html:text property="endDate" styleId="datepicker1" ></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>Tiến Độ: </td>
                                <td><html:text property="process" ></html:text>%
                                </td>
                            </tr>
                            <tr>
                             <td> Trạng thái</td>
                             <td>
                             	 <html:select property="status">
                             	 <logic:present name="record_sort" >
                             	 <logic:equal value="Open" name="record_sort" property="status">
                             	 <html:option value="Open" >Open</html:option>
                             	 <html:option value="Close">Close</html:option>
                             	 </logic:equal>
                             	 <logic:equal value="Close" name="record_sort" property="status">
                             	 <html:option value="Close">Close</html:option>
                             	 <html:option value="Open" >Open</html:option>
                             	 </logic:equal>
                             	 </logic:present>
                             	 </html:select>
                             </td>
                              
                            </tr>  
                            <tr>
              </tr>
                        </table>
                        <table>
                        <logic:equal name="task" property="mode" value="1">
							<logic:notEmpty name="task_user_free">
								<logic:equal value="3" name="record_sort" property="level">
                            <tr>
										<td width="170" align="right"><div id="bt_reset">
												<input type="reset" name="reset" id="reset" value="Reset"
													style="height: 25px; width: 100px"
													>
											</div></td>
										<td width="40"></td>
										<td width="150" align="left">

											<div id="bt_submit">
												<input type="submit" id="submit" value="OK"
													style="height: 25px; width: 100px">
											</div></td>
									</tr>
									</logic:equal>
									</logic:notEmpty>
						</logic:equal>
									
									<logic:equal name="task" property="mode" value="2">
						<logic:equal value="3" name="record_sort" property="level">
									<tr>
										<td width="170" align="right"><div id="bt_reset">
												<input type="reset" name="reset" id="reset" value="Reset"
													style="height: 25px; width: 100px"
													onClick="reset_validate_form_monhoc()">
											</div></td>
										<td width="40"></td>
										<td width="150" align="left">

											<div id="bt_submit">
												<input type="submit" id="submit" value="OK"
													style="height: 25px; width: 100px">
											</div></td>
									</tr>
								</logic:equal>
						</logic:equal>
                        </table>           
                    </div><!--end content right-->
                </html:form>

            </div>

            <jsp:include page="module/footer.jsp" />

        </div> <!-- end container-->
    </body>
</html>
