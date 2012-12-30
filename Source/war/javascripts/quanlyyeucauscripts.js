// các hàm dùng chung
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

//--- Po -- xử lý chọn chế độ thêm mới hay chỉnh sửa
function selectMode(isEdit)
{
	 var td = document.getElementById("EditAccount");
     
	 var input = td.firstChild;
	 if(isEdit)
		 input.setAttribute("readonly","readonly");
	 else
	 {
		 input.removeAttribute("readonly");
	 }
}
//--- Po -- xử lý chọn chế độ thêm mới hay chỉnh sửa

// ---- Po vẽ số lượng trang
function draw_phantrang(pageinfo,page)
{
    
    var select = document.getElementById("select_page");

    while(select.firstChild)
    {
        select.removeChild(select.firstChild);
    }
    
    for(var i = 0;i<pageinfo.CountPage;i++)
    {
        var option = document.createElement("option");
        option.setAttribute("value", i + 1);
        if(i == (page - 1))
        {
               
            option.setAttribute("selected", true);

        }
        option.innerHTML =(i+1);
        select.appendChild(option);
    }
}
//---- Po vẽ số lượng trang

// ---- Po tính số lượng trang
function ajax_getCountAndCountPage(classname,filter,page)
{
	
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var pageInfo = JSON.parse(xhr.responseText);              
                draw_phantrang(pageInfo,page);

            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };

    
    var url = "countpages.do";
    url = addURLParam(url, "classname", classname);
    url = addURLParam(url, "filter", filter);
    
    xhr.open("get", url, true);
    xhr.send(null);
}

//----- Po tính số lượng trang

// ------------------các hàm xử lý trong trang account.jsp

function getListAccount(page)
{
	var form = document.getElementById("listAccount");
	var select_group = form.elements["group"];	    
    ajax_getCountAndCountPage("sp.dto.User","groupID==" + select_group[select_group.selectedIndex].value,page);
    
	ajax_getListAccount(page)
}

function getListaccountByPage()
{
	var form = document.getElementById("listAccount");
	
	var select_page = form.elements["select_page"];
	ajax_getListAccount(select_page[select_page.selectedIndex].value)
}

function ajax_getListAccount(page)
{
	
    //draw_loading();
    var form = document.getElementById("listAccount");
    var select_group = form.elements["group"];
    
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_Account = JSON.parse(xhr.responseText);
                
                //draw_phantrang(list_Account[0].SOLUONG,page);

                draw_table_danhsachaccount(list_Account, list_Account.length,0);

                //undraw_loading();
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };

    
    var url = "getlistaccount.do";
    url = addURLParam(url, "KEY", "LIST_MONHOC");
    url = addURLParam(url, "PAGE", page);
    url = addURLParam(url, select_group.name, select_group[select_group.selectedIndex].value);
    
    
    xhr.open("get", url, true);
    xhr.send(null);
}

//--- Po draw table
function draw_table_danhsachaccount(list_account,length,index)
{
	// name của input checkbox
    var tr, td, i;
    var table = document.getElementById("table_danhsach_account");

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
        input.setAttribute("name","check");
        input.setAttribute("value",list_account[i].Email);
        td.appendChild(input);
       
        

        td = tr.insertCell(tr.cells.length);
        var a =  document.createElement("a");
        a.setAttribute("href","javascript: void(0)");
        a.setAttribute("onclick","ajax_getAccount('" +list_account[i].Email+"');");
        a.innerHTML = list_account[i].Email;
        td.appendChild(a);
               
        
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_account[i].Name;
        
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_account[i].IdPermision;
        
    }

    

    
}
// --- Po draw table
//------------------các hàm xử lý trong trang account.jsp

// ------------- Các hàm xử lý trong trang login.jsp

function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

function checkStatusLoginByGoogleAccount()
{
	var getvars = getUrlVars();
	if(getvars['typeLogin'] == "GoogleAccount")
	{
		alert("Hệ thống chứng thực bằng Google Account hiện bị lỗi");
	}else if(getvars['statuslogin']=="false")
	{
		alert("Tài khoản này không tồn tại trong hệ thống, vui lòng thử lại");
		
	}
	
}

// ------------- Các hàm xử lý trong trang login.jsp