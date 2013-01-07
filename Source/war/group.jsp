<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
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
                    <form name="danhsachmonhoc" id ="danhsachmonhoc" method="post" action="">

                        <div id="table">
                            <table id="table_danhsach_monhoc" cellspacing="0" cellpadding="0" border="1">                               
                                <thead>
                                    <tr align="center">
                                        <td width="20"><input type="checkbox" name="checkall" id="checkall" onClick="checkUncheckAll(this);"/></td>
                                        <td width="70"><b>Mã Nhóm</b></td>
                                        <td width="130"><b>Tên Nhóm</b></td>
                                        <td width="130"><b>Nhóm Trưởng</b></td>                              
                                    </tr>
                                </thead>
                                <tbody>
                                	 <tr align="center">
                                        <td width="20"><input type="checkbox" name="checkall" id="checkall" onClick="checkUncheckAll(this);"/></td>
                                        <td width="70"><b><a href="#">MN1</a></b></td>
                                        <td width="130"><b>nhóm  1</b></td>
                                        <td width="130"><b>Đặng Tấn Lộc</b></td>                           
                                    </tr>                               
                                </tbody>
                            </table>
                        </div>
                        <div id="phantrang" class="chose3" align="center">
                            <select name ="PAGE" id="select_page" onchange="ajax_load_page_danhsachmonhoc()">
                                <option value="0" >1</option>
                            </select>

                        </div>
                        <div class="chose3" align="center">
                            <input type="button" id="submit" value="Xóa" style="height: 25px; width: 100px" onClick="ajax_delete_monhoc()">
                            <input type="hidden" name="KEY" value="XOA_MONHOC">

                        </div>
                    </form>
                    <div>

                    </div>
                </div> <!--end content left-->
                <form name="form1" method="post" action="" id="form_monhoc">
                    <div id="content_right">
                        <h3 align="center"> Chỉnh sửa </h3><br>
                        <table id="table_monhoc" class="table_right" cellspacing="5" cellpadding="0" border="0">
                            <thead>

                            </thead>
                            <tr>
                                <td width="100">Mã nhóm: </td>
                                <td width="300">                    	                       	
                                    <input name="mamon" type="text" id="mamon">                       
                                </td>
                            </tr>
                            <tr>
                                <td>Tên nhóm: </td>
                                <td><input type="text" name="tenmon" id="tenmon" >
                                </td>
                            </tr>
                            <tr>
                                <td>Trường nhóm: </td>
                                <td>
                                    <select name="makhoa" id="manhom" onchange="reset_page('form_monhoc')">
                                    	 <option value="user1" >Đặng tấn Lộc</option>
                                    	 <option value="user1" >Nguyễn Thị Thúy</option>                                  	 
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Thành Viên: </td>
                                <td>
                                    <select name="makhoa" id="manhom" onchange="reset_page('form_monhoc')" size="3">
                                    	<option value="user1" >Nhân viên 1</option>
                                    	<option value="user1" >Nhân viên 2</option>
                                    	<option value="user1" >Nhân viên 3</option>
                                    	<option value="user1" >Nhân viên 4</option>
                                    </select>
                                </td>
                            </tr>   
                            <tr>
                                <td>Tham gia dự án: </td>
                                <td> <select name="makhoa" id="manhom" onchange="reset_page('form_monhoc')">
                                    	 <option value="user1" >Dự án 1</option>
                                    	 <option value="user1" >Dự án 2</option>                                  	 
                                    </select>
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
                                    <div id="bt_submit">
                                        <input type ="button" id="submit" value="OK" style="height: 25px; width: 100px" onClick="ajax_capnhatmonhoc()">
                                        <input type ="hidden" name ="KEY" value="THEM_MONHOC">
                                        <input type ="hidden" name="PAGE" value="0">
                                    </div>
                                </td>
                            </tr>
                        </table>           
                    </div><!--end content right-->
                </form>

            </div>

            <jsp:include page="module/footer.jsp" />

        </div> <!-- end container-->
    </body>
</html>
