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
        <script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
        <script src="javascripts/MyJavaScripts.js" type="text/javascript"></script>
        <script src="javascripts/json.js" type="text/javascript"></script>
        <script src="javascripts/check.js" type="text/javascript"></script>
        <script src="javascripts/quanlyyeucauscripts.js" type="text/javascript"></script>
       <script src="javascripts/requirement.js" type="text/javascript"></script> 

    </head>
    <body>
        <div id="container">           
            <jsp:include page="module/header.jsp" />
            <div id="nav_sub">
                <div id="nav_sub_left">
                    <div class="logo"><img src="images/logo.png" /></div>
                    <div class="logo"><h1> Quản Lý Yêu Cầu</h1></div>
                </div> <!--end nav_sub_left-->               
            </div><!--end nav_sub-->
            <div id="content">
                <div id="content_left">
                    <h3 align="center"> Quản lý danh sách </h3><br>
                    <form name="sortForm" id ="sortForm" method="post" action="/deleteReq.do">
                    	  <div class="chose3" align="center">
                            Dự Án:
                         
                            <select name="project" id="box"
							onChange="getListReq(1,1)">
							<logic:present name="idProList">
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
							</logic:present>
						</select>
                        </div>
                        <logic:notEmpty name="idReqList">
						<div class="chose3" align="center">
							Yêu cầu: <select name="req" id="box"
								onChange="getListReq(2,1)">
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
								onChange="getListReq(3,1)">
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
					<div class="chose3" align="center">
							Trạng thái: <select name="status" id="box"
								onChange="getListReq(4,1)">
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
                            <table id="table_req_list" cellspacing="0" cellpadding="0" border="1">                               
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
                                <logic:present name="record_list">
                                <logic:iterate id="item" name="record_list">
									<tr align="center">
										<td><input type="checkbox" name="check" id="checkall"  value="<bean:write name="item" property="id"/>"/> </td>
										<td width="70"><b><a href="/displayReq.do?id=<bean:write name="item" property="id"/>"><bean:write name="item" property="id"/></a> </b></td>
										<td width="130"><b><bean:write name="item" property="nameReq"/></b></td>
										<td width="130"><b><bean:write name="item" property="idGroup"/></b></td>
										<td width="70"><b><bean:write name="item" property="process"/>%</b></td>
									</tr>
								</logic:iterate>   
								</logic:present>
                                </tbody>
                            </table>
                        </div>
                        <div id="phantrang" class="chose3" align="center">
                            <select name="page" id="select_page" onchange="getListReqByPage(this.options[this.selectedIndex].text)" >
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
                <html:form action="/updateReq.do" method="get">
                <div id="content_right">
                        <h3 align="center"> Chỉnh sửa
                        <logic:present name="record_sort" >
						<logic:equal value="2" name="record_sort"  property="level">
						| <a href="/changeModeReq.do?mode=1">Thêm mới </a>
						</logic:equal>
						</logic:present>
                         </h3><br>
                        <table id="table_monhoc" class="table_right" cellspacing="5" cellpadding="0" border="0">
                            <thead>

                            </thead>
                        <logic:present name="record_flag">
							<logic:equal name="record_flag" value="2">
								<tr >
									<td width="100">Mã tập tin:</td>
									<td width="300"><bean:write name="req" property="id" />
									</td>
									<%-- <html:hidden property="id" value="<bean:write name="report" property="id" />"/> --%>
								</tr>
							</logic:equal>
						</logic:present>
                            <tr>
                                <td>Tên yêu cầu: </td>
                                <td><html:text property="nameReq"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>Nhóm thực hiện: </td>
                                <td>
                                    <html:select property="idGroup" onchange="this.options[this.selectedIndex].value = this.options[this.selectedIndex].text">
                                   <option value="<bean:write name="req" property="idGroup"/>"><bean:write name="req" property="idGroup"/></option>
                                   <logic:equal value="2" name="record_sort" property="level">
                                    <logic:iterate id="item" name="req_group_free">
                                    <option value="<bean:write name="item" />"><bean:write name="item" /></option>
                                    </logic:iterate>
                                    </logic:equal>
                                      <logic:notEqual value="2" name="record_sort" property="level">
                                     </logic:notEqual>
                                    </html:select>
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
                                <td><html:text property="startDate"></html:text>
                                </td>
                            </tr>  
                              <tr>
                                <td>Ngày Kết Thúc: </td>
                                <td><html:text property="endDate"></html:text>
                                </td>
                            </tr>
                            <tr>
                                <td>Tiến Độ: </td>
                                <td><html:text property="process"></html:text>
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
                        </table>
                        <table>
                            <tr>
                                <td width="170" align="right"><div id="bt_reset">
                                        <input type="reset" name="reset" id="reset" value="Reset" style="height: 25px; width: 100px" onClick="reset_validate_form_monhoc()">
                                    </div>
                                </td>
                                <td width="40"> </td>
                                <td width="150" align="left">
                                <logic:equal value="2" name="record_sort" property="level">
                                    <div id="bt_submit">
                                        <input type ="submit" id="submit" value="OK" style="height: 25px; width: 100px" >
                                        <input type ="hidden" name ="KEY" value="THEM_MONHOC">
                                        <input type ="hidden" name="PAGE" value="0">
                                    </div>
                               </logic:equal>
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
