<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<html>
    <head>
        <title>Trang chủ</title>
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link rel="stylesheet" type="text/css" href="css/SpryMenuBarHorizontal.css"/>
        <script src="javascripts/SpryMenuBar.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="container">
            <jsp:include page="module/header.jsp" />
            <div id="nav_sub">
			<div id="nav_sub_left">
				<div class="logo">
					<img src="images/logo.png" />
				</div>
				<div class="logo">
					<h1>Đổi Mật Khẩu</h1>
				</div>
			</div>
			<!--end nav_sub_left-->
		</div>
            <div id="con_login" align="center">
                <form method="post" action="/LogIn.do">
                    <div class="login" align="center">
                	Mật khẩu cũ:
                       <input type="text" name="oldpassword" id="oldpassword"/>
                    </div>
                    <br>
                    <div class="login" align="center">
                    Mật khẩu mới:
                        <input type="text" name="newpassword" id="newpassword"/>
                    </div>
                    <br>
                    <div class="login" align="center">
                   Nhập lại mật khẩu:
                        <input type="text" name="retype_newpassword" id="retype_newpassword"/>
                    </div>
                    <br>
                    <div class="login" align="center">
                      <input type="button" value="Đồng ý" onclick="changePassWord();">    
                    </div>
                </form>
            </div>

        </div> <!-- end content-->

        <jsp:include page="module/footer.jsp" />
        <!-- end footer-->
    </body>
</html>
