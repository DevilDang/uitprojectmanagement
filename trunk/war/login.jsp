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
            <jsp:include page="module/header_login.jsp" />

            <div id="con_login" align="center">
                <form styleId="login" method="post" action="project.jsp">
                    <div class="login" align="left">
                	Người dùng:
                        <input type="text" name="username"><br>
                    </div>
                    <br>
                    <div class="login" align="left">
                        Mật Khẩu:
                        <input type="password" name="pwd">
                    </div>
                    <br>
                    <div class="login" align="left">
                    <input type="submit"  value="Đăng nhập" style="height: 25px; width: 100px">
                    </div>
                <form>
            </div>

        </div> <!-- end content-->

        <jsp:include page="module/footer.jsp" />
        <!-- end footer-->
    </body>
</html>
