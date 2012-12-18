/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var isprocess = false;

function serialize(form){
    var parts = new Array();
    var field = null;

    for (var i=0, len=form.elements.length; i  <  len; i++){
        field = form.elements[i];

        switch(field.type){
            case "select-one":
            case "select-multiple":
                for (var j=0, optLen = field.options.length; j  <  optLen; j++){
                    var option = field.options[j];
                    if (option.selected){
                        var optValue = "";
                        if (option.hasAttribute){
                            optValue = (option.hasAttribute("value") ?
                                option.value : option.text);
                        }
                        else {
                            optValue = (option.attributes["value"].specified ?
                                option.value : option.text);
                        }
                        parts.push(encodeURIComponent(field.name) + "=" +
                            encodeURIComponent(optValue));
                    }
                }
                break;

            case undefined:     //fieldset
            case "file":        //file input
            case "submit":      //submit button
            case "reset":       //reset button
            case "button":      //custom button
                break;

            case "radio":       //radio button
            case "checkbox":    //checkbox
                if (!field.checked){
                    break;
                }
            /* falls through */

            default:
                parts.push(encodeURIComponent(field.name) + "=" +
                    encodeURIComponent(field.value));
        }
    }
    return parts.join("&");
}

function addURLParam(url,name,value)
{
    url += (url.indexOf("?") == -1 ? "?" : "&");
    url += encodeURIComponent(name) + "=" + encodeURIComponent(value);
    return url;
}

function createXHR()
{

    if (typeof XMLHttpRequest != "undefined")
    {
        return new XMLHttpRequest();
    }
    else if (typeof ActiveXObject != "undefined")
    {
        if (typeof arguments.callee.activeXString != "string")
        {
            var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"];

            for (var i=0,len=versions.length; i  <  len; i++)
            {
                try
                {
                    var xhr = new ActiveXObject(versions[i]);
                    arguments.callee.activeXString = versions[i];
                    return xhr;
                }
                catch (ex){
                //skip
                }
            }
        }

        return new ActiveXObject(arguments.callee.activeXString);
    }
    else
    {
        throw new Error("No XHR object available.");
    }
}

// đoạn này sent dữ liệu lên server đễ add vào cơ sở dữ liệu
//-------
// các scripts ở trang taikhoan.jsp
//------
function ajax_getdanhsachuseraccount()
{
    // hàm này có chức năng lấy danh sách user account chuyển về từ server
    if(validate_form_username())
    {
        draw_loading();
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    // alert(xhr.responseText);
                   
                    var list_useracount = JSON.parse(xhr.responseText);
                    ///var listgv = eval('(' +xhr.responseText + ')');
                    drawTable_useraccount(list_useracount,list_useracount.length);
                    undraw_loading();
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };

   
        var url = "useraccountAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var form = document.getElementById("useraccount");
        xhr.send(serialize(form));
    //alert(form);
    }

}

function fill_date_useraccount(user)
{
    
    reset_validate_form_username();

    var form = document.getElementById("useraccount");
    var username = form.elements["username"];
    var hoten = form.elements["hoten"];
    var password = form.elements["password"];
    var quyenhan = form.elements["quyenhan"];
    var ghichu = form.elements["ghichu"];

    username.value = user.USERNAME;
    //username.readonly = true;
    username.disabled = true;
    hoten.value = user.HOTEN;
    password.value = "";
    quyenhan.selectedIndex = user.QUYENHAN;
    ghichu.value = user.GHICHU;

}

var is_edit_form_userccount = false;

function ajax_getuser(username)
{
    draw_loading();
    is_edit_form_userccount = true;

    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var useracount = JSON.parse(xhr.responseText);
                ///var listgv = eval('(' +xhr.responseText + ')');
                fill_date_useraccount(useracount);
                undraw_loading();

            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "useraccountAction.do";
    url = addURLParam(url, "KEY", "LAY_USER");
    url = addURLParam(url, "username", username);

    xhr.open("get", url, true);
    xhr.send(null);
}
function drawTable_useraccount(list_useracount,length)
{
    var tr, td, i;
    var table = document.getElementById("table_left");

    // thẻ body cũ
    var body = table. tBodies[0];

    // vẽ một thủ body mới chứa dữ liệu
    var tbody = document.createElement("tbody");
    table.replaceChild(tbody,body);
    for (i = 0; i<length; i++)
    {
        tr = tbody.insertRow(tbody.rows.length);
        tr.setAttribute("align", "center");
        
        td = tr.insertCell(tr.cells.length);
        var input = document.createElement("input");
        input.setAttribute("type","checkbox");
        input.setAttribute("name","useraccount");
        input.setAttribute("value",list_useracount[i].USERNAME);
        td.appendChild(input);


        td = tr.insertCell(tr.cells.length);
        var a =  document.createElement("a");
        a.setAttribute("href","javascript: void(0)");
        a.setAttribute("onclick","ajax_getuser('" +list_useracount[i].USERNAME+"');");
        a.innerHTML = list_useracount[i].USERNAME;
        td.appendChild(a);

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_useracount[i].HOTEN;

    }
}

// đoạn này validate form nhập useraccount
function reset_validate_form_username()
{
    var table = document.getElementById("table_right");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    var form = document.getElementById("useraccount");
    var username = form.elements["username"];
    username.disabled = false;
}
function validate_form_username()
{
    var flag = true;

    var form = document.getElementById("useraccount");
    var username = form.elements["username"];
    var hoten = form.elements["hoten"];
    var password = form.elements["password"];



    var tr, td;
    var table = document.getElementById("table_right");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    if(username.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul = document.createElement("ul");
        var li = document.createElement("li")
        li.innerHTML = "B\u1ea1n chưa nhập username"
        ul.appendChild(li);
        td.appendChild(ul);

        flag = false;
    }
    if(hoten.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul_1 = document.createElement("ul");
        var li_1 = document.createElement("li")
        li_1.innerHTML = "B\u1ea1n chưa nhập họ tên"
        ul_1.appendChild(li_1);
        td.appendChild(ul_1);

        flag = false;
    }
    if(password.value.trim() == "" && username.disabled == false)
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul_2 = document.createElement("ul");
        var li_2 = document.createElement("li")
        li_2.innerHTML = "B\u1ea1n chưa nhập mật khẩu"
        ul_2.appendChild(li_2);
        td.appendChild(ul_2);

        flag = false;
    }

    return flag;

}

function ajax_xoa_account()
{
    if(valiadate_delete_taikhoan())
    {
        draw_loading();
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    // alert(xhr.responseText);

                    var list_useracount = JSON.parse(xhr.responseText);
                    ///var listgv = eval('(' +xhr.responseText + ')');
                    drawTable_useraccount(list_useracount,list_useracount.length);
                    undraw_loading();
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };


        var url = "useraccountAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var form = document.getElementById("form_danhsachtaikhoan");
        xhr.send(serialize(form));
    }
   
}

function valiadate_delete_taikhoan()
{
    var form = document.getElementById("form_danhsachtaikhoan");
    var length = form.elements.length;
    var count = 0;


    for(var z=0; z<length;z++){
        if(form.elements[z].type=='checkbox' && form.elements[z].name != "checkall" && !form.elements[z].checked){
            count++;
        }
    }

    // message_box(count + "  " + length);
    if(count == (length - 3))
    {
        alert("b\u1ea1n chưa chọn môn học nào")
        return false;
    }
    else
    {
        return true;
    }
}
//------
// các scripts ở trang taikhoan.jsp
//-----


//------
// các scripts ở trang thietlap.jsp
//------

function message_box(message)
{
    alert(message);
}

function confirmation_thietlap() {
    //var answer = confirm("l\u1ecbch H\u1ecdc (nếu có )ở niên học và học kỳ bạn ch\u1ecdn sẽ được khởi tạo lại giá trị ban đầu")
    //if (answer){
        ajax_thietlsp();
  //  }

}

function ajax_thietlsp()
{
    if(validate_form_thietlap())
    {
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    // alert(xhr.responseText);
                   
                    message_box(xhr.responseText);

                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };

   
        var url = "thietlapAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var form = document.getElementById("thietlapform");
        xhr.send(serialize(form));
        
    }

}

function validate_form_thietlap()
{
   
    var flag = true;

    var form = document.getElementById("thietlapform");
    var socahoc = form.elements["socahoc"];
    var checkboxs = form.elements["checkbox"];
    var namhoc = form.elements["namhoc"];


    var div_giogiangday = document.getElementById("giogiangday");

    var div_message_error = document.createElement("div");
    div_message_error.setAttribute("id", "message_error");

    var div_message_error_old = document.getElementById("message_error");

    div_giogiangday.replaceChild(div_message_error, div_message_error_old);

    if(socahoc[socahoc.selectedIndex].value == 0)
    {
        
        var ul = document.createElement("ul");
        var li = document.createElement("li")
        li.innerHTML = "B\u1ea1n chưa nhập username"
        ul.appendChild(li);
        div_message_error.appendChild(ul);

        flag = false;
    }

    var length = checkboxs.length;
    for(var i = 0;i<length;i++)
    {
        if(checkboxs[i].checked)
        {
            flag = true;
            break;
        }
        else
        {
            flag = false;
            if(i == length - 1)
            {
                var ul_1 = document.createElement("ul");
                var li_1 = document.createElement("li")
                li_1.innerHTML = "B\u1ea1n chưa chọn bất kỳ thời điểm giảng dạy nào"
                ul_1.appendChild(li_1);
                div_message_error.appendChild(ul_1);
            }
        }

    }

    if(namhoc == null || namhoc.value == "")
    {
        var ul_2 = document.createElement("ul");
        var li_2 = document.createElement("li")
        li_2.innerHTML = "B\u1ea1n chưa nhập năm học"
        ul_2.appendChild(li_2);
        div_message_error.appendChild(ul_2);

        flag = false;
    }
    else
    {
        var str_1=  (namhoc.value.split("-"))[0].trim();
        var str_2=  (namhoc.value.split("-"))[1].trim();
        var re5digit=/^\d{4}$/ //regular expression defining a 5 digit number
        if (str_2.search(re5digit)==-1 || str_1.search(re5digit)==-1) //if match failed
        {
            var ul_3 = document.createElement("ul");
            var li_3 = document.createElement("li")
            li_3.innerHTML = "B\u1ea1n nhập năm học không hợp lệ"
            ul_3.appendChild(li_3);
            div_message_error.appendChild(ul_3);

            flag = false;
        }
    }

    return flag;
}



function check_uncheck_table_time_slot(ischeckall)
{
    var form = document.getElementById("thietlapform");
    var checkboxs = form.elements["checkbox"];
    var length = checkboxs.length;
    for(var i = 0;i<length;i++)
    {
        if(ischeckall)
        {
            checkboxs[i].checked = true;
        }
        else
        {
            checkboxs[i].checked = false;
        }
            
    }
}

function select_socahoc()
{
    
    var form = document.getElementById("thietlapform");
    var socahoc = form.elements["socahoc"];// lấy ra input select socahoc

    // lấy ra giá trị được select
    var cahoc = socahoc[socahoc.selectedIndex].value;
    draw_table_time_slot(cahoc);
}

function draw_table_time_slot(socahoc)
{
    var tr, td, i;
    var table = document.getElementById("timeslots");

    // thẻ body cũ
    var body = table. tBodies[0];

    // vẽ một thủ body mới chứa dữ liệu
    var tbody = document.createElement("tbody");
    table.replaceChild(tbody,body);

    for (i = 0; i<socahoc; i++)
    {
        tr = tbody.insertRow(tbody.rows.length);
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = "Ca " + (i + 1);
        for(var j = 0;j<7;j++)
        {
            td = tr.insertCell(tr.cells.length);
            var input = document.createElement("input");
            input.setAttribute("type","checkbox");
            input.setAttribute("name","checkbox");
            input.setAttribute("value",(j+2)+"_"+(i+1));
            td.appendChild(input);
        }

    }
}


//------
// các scripts ở trang thietlap.jsp
//------


//------
//cac scripts ở trang xeplichhoc.jsp
//-----

var list_lichhoc_global;

function ajax_filter_lp_mnsv(filterString)
{
    draw_loading();

    var form = document.getElementById("loaiphongform");
    var select_maloaiphong =form.elements["maloaiphong"];
    var select_manhomsinhvien = form.elements["manhomsinhvien"];

    var form_sualichoc = document.getElementById("form_SuaLichHoc");
    var maloaiphong = form_sualichoc.elements["maloaiphong"];
    maloaiphong.value =  select_maloaiphong[select_maloaiphong.selectedIndex].value;

    var manhomsinhvien = form_sualichoc.elements["manhomsinhvien"];
    manhomsinhvien.value =  select_manhomsinhvien[select_manhomsinhvien.selectedIndex].value;

    ajax_getphongtrong();

   
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_lichoc = JSON.parse(xhr.responseText);
                list_lichhoc_global = list_lichoc;
                draw_table_tkb(list_lichoc,list_lichoc.length);
                undraw_loading();

            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    

    var url = "xeplichhocAction.do";
    url = addURLParam(url, "KEY", "FILTER_LP_MNSV");
    url = addURLParam(url, "TYPE", filterString);
    url = addURLParam(url, select_maloaiphong.name, select_maloaiphong[select_maloaiphong.selectedIndex].value);
    url = addURLParam(url, select_manhomsinhvien.name, select_manhomsinhvien[select_manhomsinhvien.selectedIndex].value);

    xhr.open("get", url, true);
    xhr.send(null);

    
}

function isvalidate_formSuaLichHoc()
{
    var form_sualichoc = document.getElementById("form_SuaLichHoc");
    var malopmon = form_sualichoc.elements["malopmon"];

    if(malopmon == "")
    {
        alert("B\u1ea1n chưa chọn Lớp Môn")
        return false;
    }
    return true;
}

function fill_form_SuaLichHoc(LichHoc)
{
    var form_SuaLichHoc = document.getElementById("form_SuaLichHoc");
    var thu = form_SuaLichHoc.elements["thu"];

    var length_thu = thu.options.length;
    for(var i = 0;i<length_thu;i++)
    {
        if(thu[i].value == LichHoc.Thu)
        {
            thu.selectedIndex = i;
            break;
        }
    }

    var ca = form_SuaLichHoc.elements["ca"];
    ca.selectedIndex = LichHoc.Ca - 1;

    var phong = form_SuaLichHoc.elements["phong"];
    var length_phong = phong.options.length;
    for(var j = 0;j<length_phong;j++)
    {
        if(phong[j].value == LichHoc.Phong)
        {
            phong.selectedIndex = j;
            break;
        }
    }

    var malopmon = form_SuaLichHoc.elements["malopmon"];
    malopmon.value=LichHoc.MaLopMon;

    var trangthai = form_SuaLichHoc.elements["trangthai"];
   
    trangthai.selectedIndex = LichHoc.TrangThai;
}

function ajax_getlichoc(thu,ca,phong)
{

    var form_sualichoc = document.getElementById("form_SuaLichHoc");
    var thu_old = form_sualichoc.elements["thu_old"];
    thu_old.value = thu;
    var ca_old = form_sualichoc.elements["ca_old"];
    ca_old.value = ca;
    var phong_old = form_sualichoc.elements["phong_old"];
    phong_old.value = phong;

    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var LichHoc = JSON.parse(xhr.responseText);

                fill_form_SuaLichHoc(LichHoc);


                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };



    var url = "xeplichhocAction.do";
    url = addURLParam(url, "KEY", "LayLichHoc");
    url = addURLParam(url,"thu", thu);
    url = addURLParam(url, "ca", ca);
    url = addURLParam(url, "phong", phong);

    xhr.open("get", url, true);
    xhr.send(null);
}

function ajax_getphongtrong_sualichoc()
{

    var form = document.getElementById("loaiphongform");
    var select_maloaiphong =form.elements["maloaiphong"];

    var form_phongtrong = document.getElementById("form_SuaLichHoc");
    var thu = form_phongtrong.elements["thu"];
    var ca = form_phongtrong.elements["ca"];

    var form_sualichoc = document.getElementById("form_SuaLichHoc");
    var phong = form_sualichoc.elements["phong"];


    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_phonghoc = JSON.parse(xhr.responseText);
                draw_select_phonghoc_formSuaLich(list_phonghoc);

            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };
    var url = "phonghocAction.do";
    url = addURLParam(url, "KEY", "List_PhongTrong");
    url = addURLParam(url, "phong", phong.value);
    url = addURLParam(url, select_maloaiphong.name, select_maloaiphong[select_maloaiphong.selectedIndex].value);
    url = addURLParam(url, thu.name, thu[thu.selectedIndex].value);
    url = addURLParam(url, ca.name, ca[ca.selectedIndex].value);
    url = addURLParam(url, "type", "type_2");
    xhr.open("get", url, true);
    xhr.send(null);
}


function ajax_getphongtrong_sualichoc_getlichhoc(thu,ca,phong)
{
    draw_loading();
    var form = document.getElementById("loaiphongform");
    var select_maloaiphong =form.elements["maloaiphong"];

    //    var form_phongtrong = document.getElementById("form_SuaLichHoc");
    //    var thu = form_phongtrong.elements["thu"];
    //    var ca = form_phongtrong.elements["ca"];

    //var form_sualichoc = document.getElementById("form_SuaLichHoc");
    //var phong_old = form_sualichoc.elements["phong_old"];


    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_phonghoc = JSON.parse(xhr.responseText);
                draw_select_phonghoc_formSuaLich(list_phonghoc);

                ajax_getlichoc(thu,ca,phong);

            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };
    var url = "phonghocAction.do";
    url = addURLParam(url, "KEY", "List_PhongTrong");
    //url = addURLParam(url, "PhongCu", phong_old.value);
    url = addURLParam(url, select_maloaiphong.name, select_maloaiphong[select_maloaiphong.selectedIndex].value);
    url = addURLParam(url, "thu", thu);
    url = addURLParam(url, "ca", ca);
    url = addURLParam(url, "phong", phong);
    url = addURLParam(url, "type", "type_1");

    xhr.open("get", url, true);
    xhr.send(null);
}


function draw_loading()
{
    var div = document.getElementById("loading");
    div.setAttribute("style", "display:block;")
}

function undraw_loading()
{
    var div = document.getElementById("loading");
    div.setAttribute("style", "display:none;")
}

// hàm này dùng bên panel phòng trống
function ajax_getphongtrong()
{
    draw_loading();
    var form = document.getElementById("loaiphongform");
    var select_maloaiphong =form.elements["maloaiphong"];

    var form_phongtrong = document.getElementById("form_phongtrong");
    var thu = form_phongtrong.elements["thu"];
    var ca = form_phongtrong.elements["ca"];



    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_phonghoc = JSON.parse(xhr.responseText);
                draw_table_phongtrong(list_phonghoc,list_phonghoc.length)

                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };
    var url = "phonghocAction.do";
    url = addURLParam(url, "KEY", "List_PhongTrong");
    url = addURLParam(url, select_maloaiphong.name, select_maloaiphong[select_maloaiphong.selectedIndex].value);
    url = addURLParam(url, thu.name, thu[thu.selectedIndex].value);
    url = addURLParam(url, ca.name, ca[ca.selectedIndex].value);

    xhr.open("get", url, true);
    xhr.send(null);
}

// hàm này dùng cho form sửa lịch học

function draw_select_phonghoc_formSuaLich(list_phongtrong)
{
    
    var select = document.getElementById("select_phong_formSuaLichoc");

    while(select.firstChild)
    {
        select.removeChild(select.firstChild);
    }
    
    var length  = list_phongtrong.length;
    var option_0 = document.createElement("option");
    option_0.setAttribute("value", list_phongtrong[0].MaPhong);
    option_0.setAttribute("selected", true);
    option_0.innerHTML =list_phongtrong[0].TenPhong;
    select.appendChild(option_0);
    
    for(var i = 1;i<length;i++)
    {
        var option = document.createElement("option");
        option.setAttribute("value", list_phongtrong[i].MaPhong);
        option.innerHTML =list_phongtrong[i].TenPhong;
        select.appendChild(option);
    }
} 


function draw_table_phongtrong(list_phonghoc,length)
{
    var tr, td, i;
    var table = document.getElementById("table_phongtrong");

    // thẻ body cũ
    var body = table. tBodies[0];

    // vẽ một thủ body mới chứa dữ liệu
    var tbody = document.createElement("tbody");
    table.replaceChild(tbody,body);

    for (i = 0; i<length; i++)
    {
        tr = tbody.insertRow(tbody.rows.length);
        tr.setAttribute("align", "center");

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_phonghoc[i].TenPhong;

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_phonghoc[i].SucChua;


    }
}

function ajax_xeplich()
{
    draw_loading();
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                // alert(xhr.responseText);
                var list_giaovien = JSON.parse(xhr.responseText);
                list_lichhoc_global = list_giaovien;
                draw_table_tkb(list_giaovien,list_giaovien.length);
                undraw_loading();
            //message_box(xhr.responseText);

            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };

    
    var url = "xeplichhocAction.do";
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var form = document.getElementById("loaiphongform");
    xhr.send(serialize(form));
}

function draw_table_tkb(list_lichhoc,length)
{
    var tr, td, i;
    var table = document.getElementById("thoikhoabieu");

    // thẻ body cũ
    var body = table. tBodies[0];

    // vẽ một thủ body mới chứa dữ liệu
    var tbody = document.createElement("tbody");
    table.replaceChild(tbody,body);

    for (i = 0; i<length; i++)
    {
        tr = tbody.insertRow(tbody.rows.length);
        tr.setAttribute("align", "center");
        
        td = tr.insertCell(tr.cells.length);
        var a =  document.createElement("a");
        a.setAttribute("href","javascript: void(0)");
        //ajax_getphongtrong_sualichoc
        //a.setAttribute("onclick","ajax_getlichhoc("+list_lichhoc[i].THU+","+list_lichhoc[i].CA+",'" +list_lichhoc[i].PHONG+"');");
        a.setAttribute("onclick","ajax_getphongtrong_sualichoc_getlichhoc("+list_lichhoc[i].THU+","+list_lichhoc[i].CA+",'" +list_lichhoc[i].PHONG+"');");

        a.innerHTML = list_lichhoc[i].MALOPMON;
        td.appendChild(a);

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_lichhoc[i].SISO;

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_lichhoc[i].TENMON;

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_lichhoc[i].TENGIAOVIEN;

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_lichhoc[i].CA;

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_lichhoc[i].THU;

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_lichhoc[i].PHONG;


    }
}

function validate_ngaygio()
{
    var form = document.getElementById("form_SuaLichHoc");
    var ca = form.elements["ca"];
    var thu = form.elements["thu"];

    //var phong = form.elements("phong");
    var malopmon = form.elements["malopmon"];

    var length = list_lichhoc_global.length;
   // alert(length);
    for(var i = 0;i<length;i++)
    {
        if(list_lichhoc_global[i].CA == ca.value && list_lichhoc_global[i].THU == thu.value && list_lichhoc_global[i].MALOPMON != malopmon.value )
        {
            alert("L\u01b0u ý : Giờ học bạn chọn trùng với giờ học của lớp_môn (" + list_lichhoc_global[i].MALOPMON +") cùng nhóm sinh viên")

        }
    }

}

function ajax_process_sualichhoc()
{
    
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_lichoc = JSON.parse(xhr.responseText);
                list_lichhoc_global = list_lichoc;
                draw_table_tkb(list_lichoc,list_lichoc.length);

                // cap nhat lai thu_old,ca_old,phong_old
                var form_sualichoc = document.getElementById("form_SuaLichHoc");
                var thu = form_sualichoc.elements["thu"];
                var thu_old = form_sualichoc.elements["thu_old"];
                thu_old.value = thu.value;

                var ca = form_sualichoc.elements["ca"];
                var ca_old = form_sualichoc.elements["ca_old"];
                ca_old.value = ca.value;

                var phong = form_sualichoc.elements["phong"];
                var phong_old = form_sualichoc.elements["phong_old"];
                phong_old.value = phong[phong.selectedIndex].value;

                ajax_getphongtrong();
                ajax_getphongtrong_sualichoc();

                undraw_loading();

            }else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "xeplichhocAction.do";
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var form = document.getElementById("form_SuaLichHoc");

    xhr.send(serialize(form));
}
function ajax_sualichhoc()
{
    
    draw_loading();
    var form = document.getElementById("form_SuaLichHoc");
    var malopmon = form.elements["malopmon"];
    var ca = form.elements["ca"];
    var thu = form.elements["thu"];

    if(malopmon.value.trim() != "")
    {
        validate_ngaygio();


        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var list_malopmon = JSON.parse(xhr.responseText);
                                       
                    if(list_malopmon[0].ishasmalopmon == "true")
                    {

                        var noidung = "";
                        var length = list_malopmon.length;
                        for(var i = 1; i <length;i++)
                        {
                            noidung = noidung + list_malopmon[i].MaNhomSinhVien + " : " + list_malopmon[i].MaLopMon + '\n';
                        }
                        
                        thongbao("L\u01b0u ý : Giờ học bạn chọn trùng với giờ học của các lớp_môn có cùng gi\u1ea3ng viên : " + noidung);
                    }

                    ajax_process_sualichhoc();

                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };

    
        var url = "xeplichhocAction.do";
        url = addURLParam(url, "KEY", "KiemTraTrungGioHoc");
        url = addURLParam(url, "malopmon", malopmon.value);
        url = addURLParam(url, "ca", ca.value);
        url = addURLParam(url, "thu", thu.value);

        xhr.open("get", url, true);
        xhr.send(null);

       
    }
}
//-----
// các scripts ở trang xeplichoc.jsp
//----


//-----
// các scripts ở trang monhoc.jsp
// -----

function ajax_getdanhsachmonhoc(page)
{
    draw_loading();
    var form_1 = document.getElementById("form_monhoc");
    var hidden_page = form_1.elements["PAGE"];
    hidden_page.value=page;

    var form_danhsachmonhoc = document.getElementById("danhsachmonhoc");
    var select_khoa = form_danhsachmonhoc.elements["khoa"];

    
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_monhoc = JSON.parse(xhr.responseText);

                draw_phantrang(list_monhoc[0].SOLUONG,page);

                draw_table_danhsachmonhoc(list_monhoc, list_monhoc.length,1);

                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };

    
    var url = "monhocAction.do";
    url = addURLParam(url, "KEY", "LIST_MONHOC");
    url = addURLParam(url, "PAGE", page);
    url = addURLParam(url, select_khoa.name, select_khoa[select_khoa.selectedIndex].value);
   
    xhr.open("get", url, true);
    xhr.send(null);
}

function capnhat_chonkhoa()
{
   
    var form_monhoc = document.getElementById("form_monhoc");
    var makhoa_form_monhoc = (form_monhoc.elements["makhoa"]);
    var makhoa_form_monhoc_value = makhoa_form_monhoc[makhoa_form_monhoc.selectedIndex].value;
      
    var form_danhsachmonhoc = document.getElementById("danhsachmonhoc");
    var makhoa_danhsachmonhoc = (form_danhsachmonhoc.elements["khoa"]);
    var length = makhoa_danhsachmonhoc.options.length;

   
    for(var i = 0;i<length;i++)
    {
        if(makhoa_danhsachmonhoc[i].value == makhoa_form_monhoc_value )
        {
            makhoa_danhsachmonhoc.selectedIndex = i;
            break;
        }
    }
}

function reset_page(form)
{
    var form_1 = document.getElementById(form);
    var hidden_page = form_1.elements["PAGE"];
    hidden_page.value= 0;
}

function ajax_capnhatmonhoc()
{

    if(validate_form_monhoc())
    {
        draw_loading();
        var form = document.getElementById("form_monhoc");
        var key = form.elements["KEY"];
        var mamon = form.elements["mamon"];
        var page = form.elements["PAGE"];

        if(mamon.disabled)
        {
            key.value="CAP_NHAT_MONHOC";

        }
        else
        {
            key.value="THEM_MONHOC";

        }

        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var list_monhoc = JSON.parse(xhr.responseText);
      
                    if(key.value == 'CAP_NHAT_MONHOC' )
                    {
                        //message_box(page.value);
                        draw_phantrang(list_monhoc[0].SOLUONG, page.value);
                        draw_table_danhsachmonhoc(list_monhoc, list_monhoc.length,1);
                        capnhat_chonkhoa();
                        undraw_loading();
                    }
                    else
                    {
                        
                        var isalready = list_monhoc[0].ISALREADY;
                        
                        if(isalready == 'FALSE')
                        {
                            draw_phantrang(list_monhoc[1].SOLUONG, list_monhoc[1].SOLUONG - 1);
                            draw_table_danhsachmonhoc(list_monhoc, list_monhoc.length,2);
                            capnhat_chonkhoa();
                        }
                        else
                        {

                            validate_isexist_monhoc();

                        }

                        undraw_loading();
                    }


                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };


        var url = "monhocAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(serialize(form));
    }
}

function valiadate_delete_monhoc()
{
    
    var form = document.getElementById("danhsachmonhoc");
    var length = form.elements.length;
    var count = 0;

    
    for(var z=0; z<length;z++){
        if(form.elements[z].type=='checkbox' && form.elements[z].name != "checkall" && !form.elements[z].checked){
            count++;
        }
    }

    // message_box(count + "  " + length);
    if(count == (length - 5))
    {
        alert("b\u1ea1n chưa chọn môn học nào")
        return false;
    }
    else
    {
        return true;
    }
}

function ajax_delete_monhoc()
{
    
    if(valiadate_delete_monhoc())
    {
        draw_loading();
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var list_monhoc = JSON.parse(xhr.responseText);
                    draw_table_danhsachmonhoc(list_monhoc, list_monhoc.length,0);
                    undraw_loading();
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };


        var url = "monhocAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var form = document.getElementById("danhsachmonhoc");

        xhr.send(serialize(form));
    }
}

function reset_validate_form_monhoc()
{
    var table = document.getElementById("table_monhoc");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    var form = document.getElementById("form_monhoc");
    var mamon = form.elements["mamon"];
    mamon.disabled = false;
}

function fill_data_monhoc(monhoc)
{
    //reset_validate_form_monhoc();

     
    var form = document.getElementById("form_monhoc");
    var mamon = form.elements["mamon"];
    var tenmon = form.elements["tenmon"];
    var makhoa = form.elements["makhoa"];
    var ghichu = form.elements["ghichu"];
   
    mamon.value = monhoc.MAMON;
    //username.readonly = true;
    mamon.disabled = true;
    tenmon.value = monhoc.TENMON;

    var length = makhoa.options.length;
    for(var i = 0;i<length;i++)
    {
        if(makhoa[i].value == monhoc.MAKHOA)
        {
            makhoa.selectedIndex = i;
            break;
        }
    }
    ghichu.value = monhoc.GHICHU;
}

function ajax_getmonhoc(mamon)
{
     draw_loading();
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var monhoc = JSON.parse(xhr.responseText);
                ///var listgv = eval('(' +xhr.responseText + ')');
                fill_data_monhoc(monhoc);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "monhocAction.do";
    url = addURLParam(url, "KEY", "LAY_MONHOC");
    url = addURLParam(url, "mamon", mamon);

    xhr.open("get", url, true);
    xhr.send(null);
}

function ajax_load_page_danhsachmonhoc()
{
    draw_loading();
    var form = document.getElementById("danhsachmonhoc");
    var select_page = form.elements["PAGE"];
   
    var page = select_page[select_page.selectedIndex].value;

    var form_1 = document.getElementById("form_monhoc");
    var hidden_page = form_1.elements["PAGE"];
    hidden_page.value= page;


    var form_danhsachmonhoc = document.getElementById("danhsachmonhoc");
    var select_khoa = form_danhsachmonhoc.elements["khoa"];


    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_monhoc = JSON.parse(xhr.responseText);

                draw_table_danhsachmonhoc(list_monhoc, list_monhoc.length,1);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "monhocAction.do";
    url = addURLParam(url, "KEY", "LIST_MONHOC");
    url = addURLParam(url, "PAGE", page);
    url = addURLParam(url, select_khoa.name, select_khoa[select_khoa.selectedIndex].value);

    xhr.open("get", url, true);
    xhr.send(null);
}

function draw_phantrang(soluong,page)
{
    
    var select = document.getElementById("select_page");

    while(select.firstChild)
    {
        select.removeChild(select.firstChild);
    }
    
    for(var i = 0;i<soluong;i++)
    {
        var option = document.createElement("option");
        option.setAttribute("value", i);
        if(i == page)
        {
               
            option.setAttribute("selected", true);

        }
        option.innerHTML =(i+1);
        select.appendChild(option);
    }
}

function draw_table_danhsachmonhoc(list_monhoc,length,index)
{
    var tr, td, i;
    var table = document.getElementById("table_danhsach_monhoc");

    // thẻ body cũ
    var body = table. tBodies[0];

    // vẽ một thủ body mới chứa dữ liệu
    var tbody = document.createElement("tbody");
    table.replaceChild(tbody,body);
    for (i = index; i<length; i++)
    {
        tr = tbody.insertRow(tbody.rows.length);
        tr.setAttribute("align", "center");

        td = tr.insertCell(tr.cells.length);
        var input = document.createElement("input");
        input.setAttribute("type","checkbox");
        input.setAttribute("name","mamon");
        input.setAttribute("value",list_monhoc[i].MAMON);
        td.appendChild(input);


        td = tr.insertCell(tr.cells.length);
        var a =  document.createElement("a");
        a.setAttribute("href","javascript: void(0)");
        a.setAttribute("onclick","ajax_getmonhoc('" +list_monhoc[i].MAMON+"');");
        a.innerHTML = list_monhoc[i].MAMON;
        td.appendChild(a);

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_monhoc[i].TENMON;

    }

    

    
}

function validate_form_monhoc()
{
    var flag = true;

    var form = document.getElementById("form_monhoc");
    var mamon = form.elements["mamon"];
    var tenmon = form.elements["tenmon"];


    var tr, td;
    var table = document.getElementById("table_monhoc");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    if(mamon.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul = document.createElement("ul");
        var li = document.createElement("li")
        li.innerHTML = "B\u1ea1n chưa nhập mã môn"
        ul.appendChild(li);
        td.appendChild(ul);

        flag = false;
    }
    if(tenmon.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul_1 = document.createElement("ul");
        var li_1 = document.createElement("li")
        li_1.innerHTML = "B\u1ea1n chưa nhập tên môn"
        ul_1.appendChild(li_1);
        td.appendChild(ul_1);

        flag = false;
    }
    
    return flag;
}

function validate_isexist_monhoc()
{
    
    var tr, td;
    var table = document.getElementById("table_monhoc");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    tr = thead.insertRow(thead.rows.length);

    td = tr.insertCell(tr.cells.length);
    var ul = document.createElement("ul");
    var li = document.createElement("li")
    li.innerHTML = "Mã môn này \u0111ã tồn tại"
    ul.appendChild(li);
    td.appendChild(ul);
}
//-----
// các scripts ở trang monhoc.jsp
// -----

//-----
// các scripts ở trang dsphonghoc.jsp
// -----
function ajax_getdanhsachphonghoc(page)
{
    draw_loading();
    var form_1 = document.getElementById("form_PhongHoc");
    var hidden_page = form_1.elements["PAGE"];
    hidden_page.value=page;

    var form_danhsachphonghoc = document.getElementById("form_danhsachphonghoc");
    var select_loaiphong = form_danhsachphonghoc.elements["loaiphong"];


    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_phonghoc = JSON.parse(xhr.responseText);

                draw_phantrang(list_phonghoc[0].SOLUONG,page);

                draw_table_danhsachphonghoc(list_phonghoc, list_phonghoc.length,1);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "phonghocAction.do";
    url = addURLParam(url, "KEY", "List_PhongHoc");
    url = addURLParam(url, "PAGE", page);
    url = addURLParam(url, select_loaiphong.name, select_loaiphong[select_loaiphong.selectedIndex].value);

    xhr.open("get", url, true);
    xhr.send(null);
}

function draw_table_danhsachphonghoc(list_phonghoc,length,index)
{
    
    var tr, td, i;
    var table = document.getElementById("table_DanhSachPhongHoc");

    // thẻ body cũ
    var body = table. tBodies[0];

    // vẽ một thủ body mới chứa dữ liệu
    var tbody = document.createElement("tbody");
    table.replaceChild(tbody,body);
    for (i = index; i<length; i++)
    {
        tr = tbody.insertRow(tbody.rows.length);
        tr.setAttribute("align", "center");

        td = tr.insertCell(tr.cells.length);
        var input = document.createElement("input");
        input.setAttribute("type","checkbox");
        input.setAttribute("name","maphong");
        input.setAttribute("value",list_phonghoc[i].MaPhong);
        td.appendChild(input);


        td = tr.insertCell(tr.cells.length);
        var a =  document.createElement("a");
        a.setAttribute("href","javascript: void(0)");
        a.setAttribute("onclick","ajax_getphonghoc('" +list_phonghoc[i].MaPhong+"');");
        a.innerHTML = list_phonghoc[i].MaPhong;
        td.appendChild(a);

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_phonghoc[i].TenPhong;

    }
}

function ajax_load_page_danhsachphonghoc()
{
    draw_loading();
    var form = document.getElementById("form_danhsachphonghoc");
    var select_page = form.elements["PAGE"];

    var page = select_page[select_page.selectedIndex].value;

    var form_1 = document.getElementById("form_PhongHoc");
    var hidden_page = form_1.elements["PAGE"];
    hidden_page.value= page;


    var form_danhsachmonhoc = document.getElementById("form_danhsachphonghoc");
    var select_loaiphong = form_danhsachmonhoc.elements["loaiphong"];


    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_phonghoc = JSON.parse(xhr.responseText);

                draw_table_danhsachphonghoc(list_phonghoc, list_phonghoc.length,1);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "phonghocAction.do";
    url = addURLParam(url, "KEY", "List_PhongHoc");
    url = addURLParam(url, "PAGE", page);
    url = addURLParam(url, select_loaiphong.name, select_loaiphong[select_loaiphong.selectedIndex].value);

    xhr.open("get", url, true);
    xhr.send(null);
}

function ajax_delete_phonghoc()
{
    if(valiadate_delete_phonghoc())
    {
        draw_loading();
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var list_phonghoc = JSON.parse(xhr.responseText);                   
                    draw_table_danhsachphonghoc(list_phonghoc, list_phonghoc.length,0);
                    undraw_loading();
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };


        var url = "phonghocAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var form = document.getElementById("form_danhsachphonghoc");

        xhr.send(serialize(form));
    }

}

function valiadate_delete_phonghoc()
{
    var form = document.getElementById("form_danhsachphonghoc");
    var length = form.elements.length;
    var count = 0;


    for(var z=0; z<length;z++){
        if(form.elements[z].type=='checkbox' && form.elements[z].name != "checkall" && !form.elements[z].checked){
            count++;
        }
    }

    // message_box(count + "  " + length);
    if(count == (length - 5))
    {
        alert("b\u1ea1n chưa chọn phòng học nào")
        return false;
    }
    else
    {
        return true;
    }
}

function ajax_getphonghoc(maphong)
{
    draw_loading();
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var phonghoc = JSON.parse(xhr.responseText);
                ///var listgv = eval('(' +xhr.responseText + ')');
                fill_data_phonghoc(phonghoc);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "phonghocAction.do";
    url = addURLParam(url, "KEY", "Lay_PhongHoc");
    url = addURLParam(url, "maphong", maphong);

    xhr.open("get", url, true);
    xhr.send(null);
}

function fill_data_phonghoc(phonghoc)
{
    var form = document.getElementById("form_PhongHoc");
    var maphong = form.elements["maphong"];
    var tenphong = form.elements["tenphong"];
    var maloaiphong_select = form.elements["maloaiphong"];
    var succhua = form.elements["succhua"];
    var trangthai_select = form.elements["trangthai"];
    var ghichu = form.elements["ghichu"];

    maphong.value = phonghoc.MaPhong;
    //username.readonly = true;
    maphong.disabled = true;
    tenphong.value = phonghoc.TenPhong;

    var length_loaiphong = maloaiphong_select.options.length;
    for(var i = 0;i<length_loaiphong;i++)
    {
        if(maloaiphong_select[i].value == phonghoc.MaLoaiPhong)
        {
            maloaiphong_select.selectedIndex = i;
            break;
        }
    }

    succhua.value= phonghoc.SucChua;

    var length_trangthai = trangthai_select.options.length;
    for(var j = 0;j<length_trangthai;j++)
    {
        if(trangthai_select[j].value == phonghoc.TrangThai)
        {
            trangthai_select.selectedIndex = j;
            break;
        }
    }

    ghichu.value = phonghoc.GhiChu;
}

function ajax_capnhatphonghoc()
{
    if(validate_formPhongHoc())
    {
        draw_loading();
        var form = document.getElementById("form_PhongHoc");
        var key = form.elements["KEY"];
        var maphong = form.elements["maphong"];
        var page = form.elements["PAGE"];

        if(maphong.disabled)
        {
            key.value="CapNhatPhongHoc";

        }
        else
        {
            key.value="Them_PhongHoc";

        }

        //alert(key.value);
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var list_phonghoc = JSON.parse(xhr.responseText);

                    if(key.value == 'CapNhatPhongHoc' )
                    {
                        //message_box(page.value);
                        draw_phantrang(list_phonghoc[0].SOLUONG, page.value);
                        draw_table_danhsachphonghoc(list_phonghoc, list_phonghoc.length,1);
                        capnhat_chonloaiphong();
                    }
                    else
                    {

                        var isalready = list_phonghoc[0].ISALREADY;

                        if(isalready == 'FALSE')
                        {
                            draw_phantrang(list_phonghoc[1].SOLUONG, list_phonghoc[1].SOLUONG - 1);
                            draw_table_danhsachphonghoc(list_phonghoc, list_phonghoc.length,2);
                            capnhat_chonloaiphong();
                        }
                        else
                        {

                            validate_isexist_phonghoc();

                        }
                    }

                    undraw_loading();
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };


        var url = "phonghocAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(serialize(form));
    }
}

function validate_formPhongHoc()
{
    var flag = true;

    var form = document.getElementById("form_PhongHoc");
    var maphong = form.elements["maphong"];
    var tenphong = form.elements["tenphong"];
    var suchua = form.elements["succhua"];

    var tr, td;
    var table = document.getElementById("table_PhongHoc");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    if(maphong.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul = document.createElement("ul");
        var li = document.createElement("li")
        li.innerHTML = "B\u1ea1n chưa nhập mã phòng"
        ul.appendChild(li);
        td.appendChild(ul);

        flag = false;
    }
    if(tenphong.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul_1 = document.createElement("ul");
        var li_1 = document.createElement("li")
        li_1.innerHTML = "B\u1ea1n chưa nhập tên phòng"
        ul_1.appendChild(li_1);
        td.appendChild(ul_1);

        flag = false;
    }
    if(suchua.value.trim() != "")
    {
        
        var re5digit=/^\d{1,}$/;
        if(suchua.value.trim().search(re5digit) == -1)
        {
            tr = thead.insertRow(thead.rows.length);

            td = tr.insertCell(tr.cells.length);
            var ul_2 = document.createElement("ul");
            var li_2 = document.createElement("li")
            li_2.innerHTML = "B\u1ea1n nhập sức chứa không hợp lệ"
            ul_2.appendChild(li_2);
            td.appendChild(ul_2);

            flag = false;
        }

    }

    return flag;
    
}

function validate_isexist_phonghoc()
{
    var tr, td;
    var table = document.getElementById("table_PhongHoc");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    tr = thead.insertRow(thead.rows.length);

    td = tr.insertCell(tr.cells.length);
    var ul = document.createElement("ul");
    var li = document.createElement("li")
    li.innerHTML = "Mã phòng này \u0111ã tồn tại"
    ul.appendChild(li);
    td.appendChild(ul);
}

function capnhat_chonloaiphong()
{
    var form_monhoc = document.getElementById("form_PhongHoc");
    var maloaiphong_form_phonghoc = (form_monhoc.elements["maloaiphong"]);
    var maloaiphong_form_phonghoc_value = maloaiphong_form_phonghoc[maloaiphong_form_phonghoc.selectedIndex].value;

    var form_danhsachphonghoc = document.getElementById("form_danhsachphonghoc");
    var maloaiphong_danhsachphonghoc = (form_danhsachphonghoc.elements["loaiphong"]);
    var length = maloaiphong_danhsachphonghoc.options.length;


    for(var i = 0;i<length;i++)
    {
        if(maloaiphong_danhsachphonghoc[i].value == maloaiphong_form_phonghoc_value )
        {
            maloaiphong_danhsachphonghoc.selectedIndex = i;
            break;
        }
    }
}

function reset_validate_form_phonghoc()
{
    var table = document.getElementById("table_PhongHoc");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    var form = document.getElementById("form_PhongHoc");
    var maphong = form.elements["maphong"];
    maphong.disabled = false;
}
//-----
// các scripts ở trang dsphonghoc.jsp
// -----

//------
//các scripts loaiphong.jsp
//------

function ajax_getloaiphonghoc(maloaiphong)
{
    draw_loading();
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var loaiphong = JSON.parse(xhr.responseText);
                ///var listgv = eval('(' +xhr.responseText + ')');
                fill_dataLoaiPhong(loaiphong);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "loaiphongAction.do";
    url = addURLParam(url, "KEY", "Lay_LoaiPhong");
    url = addURLParam(url, "maloaiphong", maloaiphong);

    xhr.open("get", url, true);
    xhr.send(null);
}

function fill_dataLoaiPhong(loaphong)
{
    var form = document.getElementById("form_loaiphong");
    var maloaiphong = form.elements["maloaiphong"];
    var tenloaiphong = form.elements["tenloaiphong"];
    var ghichu = form.elements["ghichu"];

    maloaiphong.value = loaphong.MaLoaiPhong;
    maloaiphong.disabled = true;
    tenloaiphong.value = loaphong.TenLoaiPhong;
    ghichu.value = loaphong.GhiChu;
   
}

function ajax_capnhatloaiphong()
{
    if(valiate_loaiphong())
    {
        draw_loading();
        var form = document.getElementById("form_loaiphong");
        var maphong = form.elements["maloaiphong"];
        var key = form.elements["KEY"];
        if(maphong.disabled)
        {
            key.value="CapNhatLoaiPhongHoc";

        }
        else
        {
            
            key.value="ThemLoaiPhongHoc";

        }

        //alert(key.value);
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var list_loaiphonghoc = JSON.parse(xhr.responseText);

                    if(key.value == 'CapNhatLoaiPhongHoc' )
                    {
                        //message_box(page.value);
                        draw_table_danhsachloaiphonghoc(list_loaiphonghoc, list_loaiphonghoc.length,0);
                    }
                    else
                    {

                        var isalready = list_loaiphonghoc[0].ISALREADY;

                        if(isalready == 'FALSE')
                        {                           
                            draw_table_danhsachloaiphonghoc(list_loaiphonghoc, list_loaiphonghoc.length,1);
                        }
                        else
                        {

                            validate_isexist_loaiphonghoc();

                        }
                    }

                     undraw_loading();
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };


        var url = "loaiphongAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(serialize(form));
    }
}

function draw_table_danhsachloaiphonghoc (list_loaiphonghoc, length,index)
{
    var tr, td, i;
    var table = document.getElementById("table_danhsachloaiphong");

    // thẻ body cũ
    var body = table. tBodies[0];

    // vẽ một thủ body mới chứa dữ liệu
    var tbody = document.createElement("tbody");
    table.replaceChild(tbody,body);
    for (i = index; i<length; i++)
    {
        tr = tbody.insertRow(tbody.rows.length);
        tr.setAttribute("align", "center");

        td = tr.insertCell(tr.cells.length);
        var input = document.createElement("input");
        input.setAttribute("type","checkbox");
        input.setAttribute("name","maloaiphong");
        input.setAttribute("value",list_loaiphonghoc[i].MaLoaiPhong);
        td.appendChild(input);


        td = tr.insertCell(tr.cells.length);
        var a =  document.createElement("a");
        a.setAttribute("href","javascript: void(0)");
        a.setAttribute("onclick","ajax_getloaiphonghoc('" +list_loaiphonghoc[i].MaLoaiPhong+"');");
        a.innerHTML = list_loaiphonghoc[i].MaLoaiPhong;
        td.appendChild(a);

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_loaiphonghoc[i].TenLoaiPhong;

    }
}

function  validate_isexist_loaiphonghoc()
{
    
    var tr, td;
    var table = document.getElementById("table_loaiphong");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);


    tr = thead.insertRow(thead.rows.length);

    td = tr.insertCell(tr.cells.length);
    var ul = document.createElement("ul");
    var li = document.createElement("li")
    li.innerHTML = "Mã lo\u1ea1i phòng này đã tồn tại";
    ul.appendChild(li);
    td.appendChild(ul);


}
function valiate_loaiphong()
{
    var flag = true;

    var form = document.getElementById("form_loaiphong");
    var maloaiphong = form.elements["maloaiphong"];
    var tenloaiphong = form.elements["tenloaiphong"];

    var tr, td;
    var table = document.getElementById("table_loaiphong");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    if(maloaiphong.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul = document.createElement("ul");
        var li = document.createElement("li")
        li.innerHTML = "B\u1ea1n chưa nhập mã loại phòng"
        ul.appendChild(li);
        td.appendChild(ul);

        flag = false;
    }
    if(tenloaiphong.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul_1 = document.createElement("ul");
        var li_1 = document.createElement("li")
        li_1.innerHTML = "B\u1ea1n chưa nhập tên loại phòng"
        ul_1.appendChild(li_1);
        td.appendChild(ul_1);

        flag = false;
    }
    
    return flag;
}

function reset_validate_form_loaiphong()
{
    var table = document.getElementById("table_loaiphong");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

    var form = document.getElementById("form_loaiphong");
    var mamon = form.elements["maloaiphong"];
    mamon.disabled = false;
}

function ajax_xoa_loaiphong()
{
    if(valiadate_delete_loaiphong())
    {
        draw_loading();
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var list_loaiphonghoc = JSON.parse(xhr.responseText);
                    draw_table_danhsachloaiphonghoc(list_loaiphonghoc, list_loaiphonghoc.length, 0)
                    undraw_loading();
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };


        var url = "loaiphongAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var form = document.getElementById("form_danhsachloaiphonghoc");

        xhr.send(serialize(form));
    }
}

function valiadate_delete_loaiphong()
{
    
    var form = document.getElementById("form_danhsachloaiphonghoc");
    var length = form.elements.length;
    var count = 0;

    
    for(var z=0; z<length;z++){
        if(form.elements[z].type=='checkbox' && form.elements[z].name != "checkall" && !form.elements[z].checked){
            count++;
        }
    }
    
    // message_box(count + "  " + length);
    if(count == (length - 3))
    {
        alert("b\u1ea1n chưa chọn loại phòng học nào")
        return false;
    }
    else
    {
        return true;
    }
}
//------
//các scripts loaiphong.jsp
//------



//------
//các scripts dsgvtggd.jsp
//------
function ajax_getdanhsachgiaovien()
{
    draw_loading();
    var form_danhsachgiaoviendangky = document.getElementById("form_danhsachgiaoviendangky");
    var select_khoa = form_danhsachgiaoviendangky.elements["khoa"];

    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_giaovien = JSON.parse(xhr.responseText);

                draw_select_giaovien(list_giaovien);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "giaovienDKgiodayAction.do";
    url = addURLParam(url, "KEY", "List_GiaoVien");
    url = addURLParam(url, select_khoa.name, select_khoa[select_khoa.selectedIndex].value);

    xhr.open("get", url, true);
    xhr.send(null);


}
function ajax_getdanhsachgiaoviendangky(page)
{
    draw_loading();
    ajax_getdanhsachgiaovien();

    

    var form_danhsachgiaoviendangky = document.getElementById("form_danhsachgiaoviendangky");
    var select_khoa = form_danhsachgiaoviendangky.elements["khoa"];
    var select_namhoc = form_danhsachgiaoviendangky.elements["namhoc"];
    var select_hocky = form_danhsachgiaoviendangky.elements["hocky"];

    var form_1 = document.getElementById("form_giaoviendangky");
    var hidden_page = form_1.elements["PAGE"];
    hidden_page.value=page;
    var khoa = form_1.elements["khoa"];
    khoa.value = select_khoa.value;
    
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_giaovien = JSON.parse(xhr.responseText);

                draw_phantrang(list_giaovien[0].SOLUONG,page);

                draw_table_danhsachgiaoviendangky(list_giaovien, list_giaovien.length,1);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "giaovienDKgiodayAction.do";
    url = addURLParam(url, "KEY", "LIST_GiaoVienDangKy");
    url = addURLParam(url, "PAGE", page);
    url = addURLParam(url, select_khoa.name, select_khoa[select_khoa.selectedIndex].value);
    url = addURLParam(url, select_namhoc.name, select_namhoc[select_namhoc.selectedIndex].value);
    url = addURLParam(url, select_hocky.name, select_hocky[select_hocky.selectedIndex].value);

    xhr.open("get", url, true);
    xhr.send(null);
}
function ajax_getgiaoviendangky(magiaovien)
{
    draw_loading();
    var form_danhsachgiaoviendangky = document.getElementById("form_danhsachgiaoviendangky");
    var select_namhoc = form_danhsachgiaoviendangky.elements["namhoc"];
    var select_hocky = form_danhsachgiaoviendangky.elements["hocky"];

    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var giaovien = JSON.parse(xhr.responseText);

                fill_giaoviengiangday(giaovien);
                undraw_loading();

            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "giaovienDKgiodayAction.do";
    url = addURLParam(url, "KEY", "Lay_GioGiangDayGiaoVienDangKy");
    url = addURLParam(url, "magiaovien", magiaovien);
    url = addURLParam(url, select_namhoc.name, select_namhoc[select_namhoc.selectedIndex].value);
    url = addURLParam(url, select_hocky.name, select_hocky[select_hocky.selectedIndex].value);

    xhr.open("get", url, true);
    xhr.send(null);
}

function ajax_delete_giaoviendangky()
{
    draw_loading();
    var form = document.getElementById("form_danhsachgiaoviendangky");
    var page = form.elements["PAGE"];
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_giaovien = JSON.parse(xhr.responseText);

                draw_phantrang(list_giaovien[0].SOLUONG,page);
                draw_table_danhsachgiaoviendangky(list_giaovien, list_giaovien.length,1);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };

    var url = "giaovienDKgiodayAction.do";
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(serialize(form));


}

function  fill_giaoviengiangday(giaovien)
{
    var form = document.getElementById("form_giaoviendangky");

    var select_magiaovien = form.elements["magiaovien"];
    var magiaovien_length = select_magiaovien.options.length;

    for(var i = 0;i<magiaovien_length;i++)
    {
        if(select_magiaovien[i].value == giaovien.MaGiaoVien)
        {
            select_magiaovien.selectedIndex = i;
        }
    }

    var select_ut1 = form.elements["ut1"];
    var ut1_lenght = select_ut1.options.length;

    for(var j = 0;j<ut1_lenght;j++)
    {
        if(select_ut1[j].value == giaovien.UuTien1)
        {
            select_ut1.selectedIndex = j;
        }
    }

    var select_ut2 = form.elements["ut2"];
    var ut2_length = select_ut2.options.length;

    for(var t = 0;t<ut2_length;t++)
    {
        if(select_ut2[t].value == giaovien.UuTien2)
        {
            select_ut2.selectedIndex = t;
        }
    }

    var select_ut3 = form.elements["ut3"];
    var ut3_length = select_ut3.options.length;
    
    for(var h = 0;h<ut3_length;h++)
    {
        if(select_ut3[h].value == giaovien.UuTien3)
        {
            select_ut3.selectedIndex = h;
        }
    }

    var ghichu = form.elements["ghichu"];
    ghichu.value = giaovien.GhiChu;

}

function  draw_table_danhsachgiaoviendangky(list_giaoviendangky, length,index)
{
    
    var tr, td, i;
    var table = document.getElementById("table_danhsachgiaoviendangky");

    // thẻ body cũ
    var body = table. tBodies[0];

    // vẽ một thủ body mới chứa dữ liệu
    var tbody = document.createElement("tbody");
    table.replaceChild(tbody,body);
    for (i = index; i<length; i++)
    {
        tr = tbody.insertRow(tbody.rows.length);
        tr.setAttribute("align", "center");

        td = tr.insertCell(tr.cells.length);
        var input = document.createElement("input");
        input.setAttribute("type","checkbox");
        input.setAttribute("name","giaoviendangky");
        input.setAttribute("value",list_giaoviendangky[i].MaGiaoVien);
        td.appendChild(input);


        td = tr.insertCell(tr.cells.length);
        var a =  document.createElement("a");
        a.setAttribute("href","javascript: void(0)");
        a.setAttribute("onclick","ajax_getgiaoviendangky('" +list_giaoviendangky[i].MaGiaoVien+"');");
        a.innerHTML = list_giaoviendangky[i].MaGiaoVien;
        td.appendChild(a);

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_giaoviendangky[i].TenGiaoVien;

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_giaoviendangky[i].UuTienMot;

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_giaoviendangky[i].UuTienHai;

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_giaoviendangky[i].UuTienBa;

    }


}

function thongbao(message)
{
    alert(message);
}
function ajax_capnhatlichgiangday()
{
    var form = document.getElementById("form_giaoviendangky");
    var page = form.elements["PAGE"];

    if(validate_formLichGiangDay())
    {
        draw_loading();
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var list_giaovien = JSON.parse(xhr.responseText);

                    draw_phantrang(list_giaovien[0].SOLUONG,page);
                    draw_table_danhsachgiaoviendangky(list_giaovien, list_giaovien.length,1);
                    thongbao("cập nhật thành công");
                    undraw_loading();
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };

        var url = "giaovienDKgiodayAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(serialize(form));
    }
  
}

function validate_formLichGiangDay()
{
    var form_1 = document.getElementById("form_giaoviendangky");
    var select_giaovien = form_1.elements["magiaovien"];

    if(select_giaovien[select_giaovien.selectedIndex].value == 0)
    {
        alert("Ch\u01b0a chọn giáo viên");
        return false;
    }

    return true;
}

function chonnamhoc()
{
    var form_danhsachgiaoviendangky = document.getElementById("form_danhsachgiaoviendangky");
    var select_namhoc = form_danhsachgiaoviendangky.elements["namhoc"];

    var form_giaoviendangky = document.getElementById("form_giaoviendangky");
    form_giaoviendangky.elements["namhoc"].value = select_namhoc[select_namhoc.selectedIndex].value;

    ajax_getdanhsachgiaoviendangky(0);
}

function chonhocky()
{
    var form_danhsachgiaoviendangky = document.getElementById("form_danhsachgiaoviendangky");
    var select_hocky = form_danhsachgiaoviendangky.elements["hocky"];

    var form_giaoviendangky = document.getElementById("form_giaoviendangky");
    form_giaoviendangky.elements["hocky"].value = select_hocky[select_hocky.selectedIndex].value;

    ajax_getdanhsachgiaoviendangky(0);
}

function draw_select_giaovien(list_giaovien)
{
    
    var select = document.getElementById("select_magiaovien");

    while(select.firstChild)
    {
        select.removeChild(select.firstChild);
    }

    var option_khong = document.createElement("option");
    option_khong.setAttribute("value", "0");
    option_khong.innerHTML ="Ch\u01b0a Chọn";
    select.appendChild(option_khong);

    var soluong = list_giaovien.length;
    for(var i = 0;i<soluong;i++)
    {
        var option = document.createElement("option");
        option.setAttribute("value", list_giaovien[i].MaGiaoVien);
        option.innerHTML =list_giaovien[i].TenGiaoVien;
        select.appendChild(option);
    }
}
//------
//các scripts dsgvtggd.jsp
//------

//------
//các scripts xemtkb.jsp
//------
function ajax_xemlichhoc()
{
    draw_loading();
    var form = document.getElementById("form_xemthoikhoabieu");
    // draw_loading();
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_lichoc = JSON.parse(xhr.responseText);
                draw_table_tkb(list_lichoc,list_lichoc.length);
            undraw_loading();

            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "xeplichhocAction.do";
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(serialize(form));
}
//------
//các scripts xemtkb.jsp
//------

//------
//các scripts phongtrong.jsp
//------
function ajax_getphongtrong_1()
{
    draw_loading();
    var form = document.getElementById("form_phongtrong");
    // draw_loading();
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_phonghoc = JSON.parse(xhr.responseText);
                draw_table_phongtrong(list_phonghoc,list_phonghoc.length)
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "phongtrongAction.do";
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(serialize(form));
}
//------
//các scripts phongtrong.jsp
//------


//------
//các scripts nhatki.jsp
//------
function ajax_getdanhsachNhatKy()
{
    draw_loading();
    var form_1 = document.getElementById("form_danhsachnhatky");
    var ngaylog = form_1.elements["date"];

    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_nhatky = JSON.parse(xhr.responseText);

                draw_phantrang(list_nhatky[0].SOLUONG,0);

                draw_table_danhsachnhatky(list_nhatky, list_nhatky.length,1);

                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };
    var url = "auditlogAction.do";
    url = addURLParam(url, "KEY", "List_DanhSachNhatKy");
    url = addURLParam(url, "ngaylog", ngaylog.value);

    xhr.open("get", url, true);
    xhr.send(null);
}
function ajax_load_page_danhsachnhatky()
{
    draw_loading();
    var form = document.getElementById("form_danhsachnhatky");
    var select_page = form.elements["PAGE"];
    var ngaylog = form.elements["date"];

    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_nhatky = JSON.parse(xhr.responseText);

                draw_table_danhsachnhatky(list_nhatky, list_nhatky.length,0);

                undraw_loading();

            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "auditlogAction.do";
    url = addURLParam(url, "KEY", "Load_PaGe_NhatKy");
    url = addURLParam(url, "PAGE", select_page.value);
    url = addURLParam(url, "ngaylog", ngaylog.value);

    xhr.open("get", url, true);
    xhr.send(null);
}
function ajax_xoanhatky()
{
    if(valiadate_delete_nhatky())
    {
        draw_loading();
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var list_nhatky = JSON.parse(xhr.responseText);
                    draw_table_danhsachnhatky(list_nhatky, list_nhatky.length,0);

                    undraw_loading();

                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        };


        var url = "auditlogAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var form = document.getElementById("form_danhsachnhatky");

        xhr.send(serialize(form));
    }
}

function ajax_getnhatky(idlog)
{
    draw_loading();
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var nhatky = JSON.parse(xhr.responseText);

                fill_form_nhatky(nhatky);
                undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };
    var url = "auditlogAction.do";
    url = addURLParam(url, "KEY", "Lay_NhatKy");
    url = addURLParam(url, "idlog", idlog);

    xhr.open("get", url, true);
    xhr.send(null);
}

function fill_form_nhatky(nhatky)
{
    var form = document.getElementById("form_nhatky");

    var idlog = form.elements["idlog"];
    idlog.value = nhatky.IDLog;
    var username = form.elements["username"];
    username.value = nhatky.UserName;
    var ngaylog = form.elements["ngaylog"];
    ngaylog.value = nhatky.NgayLog;
    var noidung = form.elements["noidung"]
    noidung.value = nhatky.NoiDung;

}

function valiadate_delete_nhatky()
{

    var form = document.getElementById("form_danhsachnhatky");
    var length = form.elements.length;
    var count = 0;


    for(var z=0; z<length;z++){
        if(form.elements[z].type=='checkbox' && form.elements[z].name != "checkall" && !form.elements[z].checked){
            count++;
        }
    }

    // message_box(count + "  " + length);
    if(count == (length - 5))
    {
        alert("b\u1ea1n chưa chọn IDLog nào")
        return false;
    }
    else
    {
        return true;
    }
}
function draw_table_danhsachnhatky(list_nhatky, length,index)
{
    //alert("tanloc " + length);
    var tr, td, i;
    var table = document.getElementById("table_danhsachnhatky");

    // thẻ body cũ
    var body = table. tBodies[0];

    // vẽ một thủ body mới chứa dữ liệu
    var tbody = document.createElement("tbody");
    table.replaceChild(tbody,body);
    for (i = index; i<length; i++)
    {
        tr = tbody.insertRow(tbody.rows.length);
        tr.setAttribute("align", "center");

        td = tr.insertCell(tr.cells.length);
        var input = document.createElement("input");
        input.setAttribute("type","checkbox");
        input.setAttribute("name","IDLog");
        input.setAttribute("value",list_nhatky[i].IDLog);
        td.appendChild(input);


        td = tr.insertCell(tr.cells.length);
        var a =  document.createElement("a");
        a.setAttribute("href","javascript: void(0)");
        a.setAttribute("onclick","ajax_getnhatky('" +list_nhatky[i].IDLog+"');");
        a.innerHTML = list_nhatky[i].IDLog;
        td.appendChild(a);

        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_nhatky[i].UserName;

    }
}
//------
//các scripts nhatki.jsp
//------

//------
//----- các scripts thongbao.jsp
//------

function ajax_guimail()
{
    draw_loading();
    var form = document.getElementById("form_guiemail");
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                undraw_loading();
                var is_sent = JSON.parse(xhr.responseText);
                if(is_sent == true)
                {
                    thongbao("G\u1eedi Email Thành Công");
                }
                else
                    thongbao("G\u1eedi Email Thất Bại");
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };


    var url = "guiEmailAction.do";
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(serialize(form));
}

//------
//----- các scripts thongbao.jsp
//------


//------
//----- các scripts doimatkhau.jsp
//------

function ajax_doimatkhau()
{
    if(validate_form_doimatkhau())
    {
        draw_loading();
        var form = document.getElementById("form_doimatkhau");
        var xhr = createXHR();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                    var is_sent = JSON.parse(xhr.responseText);
                    //thongbao(is_sent);
                    if(is_sent == true)
                    {
                        thongbao("\u0110ổi mật khẩu thành công");
                     
                    }
                    else 
                        thongbao("\u0110ổi mật khẩu thất bại");

                    undraw_loading();
                } else {
                    alert("Request was unsuccessful: " + xhr.status);
                }
            }
        }
        var url = "useraccountAction.do";
        xhr.open("post", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(serialize(form));
    }
   
}

function validate_form_doimatkhau()
{
    var flag = true;
    var form = document.getElementById("form_doimatkhau");
    var matkhaucu = form.elements["matkhaucu"];
    var matkhaumoi = form.elements["matkhaumoi"];
    var matkhaumoi_retype = form.elements["matkhaumoi_retype"];

    var tr, td;
    var table = document.getElementById("table_doimatkhau");

    // thẻ head cũ
    var head = table.tHead;

    // vẽ một thẻ head mới chứa dữ liệu
    var thead = document.createElement("thead");
    table.replaceChild(thead,head);

   
    if(matkhaucu.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul = document.createElement("ul");
        var li = document.createElement("li")
        li.innerHTML = "B\u1ea1n chưa nhập mật khẩu cũ"
        ul.appendChild(li);
        td.appendChild(ul);
        flag = false;

    }

    if(matkhaumoi.value.trim() == "")
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul_1 = document.createElement("ul");
        var li_1 = document.createElement("li")
        li_1.innerHTML = "B\u1ea1n chưa nhập mật khẩu mới"
        ul_1.appendChild(li_1);
        td.appendChild(ul_1);
        flag = false;
    }

    if(matkhaumoi.value != matkhaumoi_retype.value)
    {
        tr = thead.insertRow(thead.rows.length);

        td = tr.insertCell(tr.cells.length);
        var ul_3 = document.createElement("ul");
        var li_3 = document.createElement("li")
        li_3.innerHTML = "M\u1eadt khẩu mới nhập lại chưa đúng"
        ul_3.appendChild(li_3);
        td.appendChild(ul_3);
        flag = false;
    }
    return flag;
}

//--- Po -- xóa các thông báo lỗi khi nhấn button reset
function xoa_errors()
{
	var ul = document.getElementById("errors");
	while(ul.firstChild)
    {
		 ul.removeChild(ul.firstChild);
    }
}
//--- Po -- xóa các thông báo lỗi khi nhấn button reset

//------
//----- các scripts doimatkhau.jsp
//------