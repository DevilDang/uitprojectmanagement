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

//------------------các hàm xử lý trong trang project.jsp
function getListProject(page)
{
	var form = document.getElementById("listProject");
	var select_status = form.elements["status"];	    
    ajax_getCountAndCountPage("sp.dto.Project","status==" + select_status[select_status.selectedIndex].value,page);
    
    ajax_getListProject(page)
}

function getListProjectByPage()
{
	var form = document.getElementById("listProject");
	
	var select_page = form.elements["select_page"];
	ajax_getListProject(select_page[select_page.selectedIndex].value)
}

function ajax_getListProject(page)
{
    var form = document.getElementById("listProject");
    var select_status = form.elements["status"];
    
    var xhr = createXHR();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

                var list_project = JSON.parse(xhr.responseText);
                draw_table_danhsachProject(list_project,list_project.length,0)
            } else {
                alert("Request was unsuccessful: " + xhr.status);
            }
        }
    };

    
    var url = "getlistproject.do";
    url = addURLParam(url, "PAGE", page);
    url = addURLParam(url, select_status.name, select_status[select_status.selectedIndex].value);
    
    xhr.open("get", url, true);
    xhr.send(null);
}

function draw_table_danhsachProject(list_project,length,index)
{
    var form = document.getElementById("listProject");	
	var select_page = form.elements["select_page"];
	var select_status = form.elements["status"];
	
	// name của input checkbox
    var tr, td, i;
    var table = document.getElementById("table_danhsach_project");

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
        input.setAttribute("value",list_project[i].IDproject);
        td.appendChild(input);
       
        

        td = tr.insertCell(tr.cells.length);
        var a =  document.createElement("a");
        a.setAttribute("href","/getproject.do?IDproject=" + list_project[i].IDproject+"&status="+select_status[select_status.selectedIndex].value+"&PAGE=" + select_page[select_page.selectedIndex].value);
        a.innerHTML = list_project[i].projectname;
        td.appendChild(a);
               
        
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_project[i].projectmanager;
        
        td = tr.insertCell(tr.cells.length);
        td.innerHTML = list_project[i].process;
        
    }
}
//------------------các hàm xử lý trong trang project.jsp


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
		
	}else if(getvars['init']=="true")
	{
		alert("Chúc mừng bạn đã khởi tạo thành công tài khoản Admin. Hãy đăng nhập vào hệ thống với : <br> Tên truy nhập: admin <br> Mật khẩu : admin");
	}else if(getvars['init']=="false")
	{
	
	}
	
}

// ------------- Các hàm xử lý trong trang login.jsp


function getListReport(flag,page)
{
	var form = document.getElementById("sortForm");
	
	var select;
	
	if (flag == 1){
		
		select = form.elements["project"];
	}
	else if (flag == 2){
		select = form.elements["req"];
	}
	else if (flag == 3){
		select = form.elements["group"];
	}
	else if (flag == 4){
		select = form.elements["task"];
	}
	else if (flag == 5){
		select = form.elements["status"];
	}
	
	var sort_value =select[select.selectedIndex].value;
	
	
	ajax_getListReport(page,select.name,sort_value);
	
	
	ajax_getCountAndCountPageReport(page,0);
}

function getListReportByPage(page)
{
//	var form = document.getElementById("sortForm");
//	
//	var select_page = form.elements["select_page"];
	ajax_getListReportByPage(page)
}

//tinh so page
function ajax_getCountAndCountPageReport(page, flag)
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

  var url = "countPageList.do";
  
  //my report
  if (flag == 1){
  	 url = addURLParam(url, "flag", "1");
  }
  xhr.open("get", url, true);
  xhr.send(null);
}


//get danh sach report
function ajax_getListReport(page, sort_name, sort_value)
{
	
  var xhr = createXHR();
  xhr.onreadystatechange = function(){
      if (xhr.readyState == 4){
          if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

              var list_report = JSON.parse(xhr.responseText);
              //draw_phantrang(list_Account[0].SOLUONG,page);

              draw_table_danhsach_report(list_report, list_report.length,0);

              //undraw_loading();
          } else {
              alert("Request was unsuccessful: " + xhr.status);
          }
      }
  };

  
  var url = "dislayReportListPaging.do";
  url = addURLParam(url, "KEY", "LIST_REPORT");
  url = addURLParam(url, sort_name, sort_value);
  url = addURLParam(url, "page", page);
  url = addURLParam(url, "ajax", "1");
//  url = addURLParam(url, select_group.name, select_group[select_group.selectedIndex].value);
  
  
  xhr.open("get", url, true);
  xhr.send(null);
}

//get danh sach report by page
function ajax_getListReportByPage(page)
{
	
//  var form = document.getElementById("sortForm");
//  var select_group = form.elements["group"];
  
  var xhr = createXHR();
  xhr.onreadystatechange = function(){
      if (xhr.readyState == 4){
          if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

              var list_report = JSON.parse(xhr.responseText);
              //draw_phantrang(list_Account[0].SOLUONG,page);

              draw_table_danhsach_report(list_report, list_report.length,0);

              //undraw_loading();
          } else {
              alert("Request was unsuccessful: " + xhr.status);
          }
      }
  };

  
  var url = "dislayReportListPaging.do";
  url = addURLParam(url, "KEY", "LIST_REPORT");
  url = addURLParam(url, "page", page);
  url = addURLParam(url, "ajax", "1");
//  url = addURLParam(url, select_group.name, select_group[select_group.selectedIndex].value);
  
  
  xhr.open("get", url, true);
  xhr.send(null);
}

//--- ve
function draw_table_danhsach_report(list_report,length,index)
{
	// name cá»§a input checkbox
  var tr, td, i;
  var table = document.getElementById("table_report_list");

  // tháº» body cÅ©
  var body = table. tBodies[0];

  // váº½ má»™t thá»§ body má»›i chá»©a dá»¯ liá»‡u
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
      input.setAttribute("value",list_report[i].id);
      td.appendChild(input);
     
      

      td = tr.insertCell(tr.cells.length);
      var a =  document.createElement("a");
      a.setAttribute("href","/displayReport.do?id="+list_report[i].id);
      a.innerHTML = list_report[i].id;
      td.appendChild(a);
             
      
      td = tr.insertCell(tr.cells.length);
      td.innerHTML = list_report[i].title;
      
      td = tr.insertCell(tr.cells.length);
      td.innerHTML = list_report[i].idUser;
      
      td = tr.insertCell(tr.cells.length);
      var a =  document.createElement("a");
      a.setAttribute("href","/downloadFile.do?id="+list_report[i].fileId);
//      a.setAttribute("onclick","ajax_getAccount('" +list_report[i].id+"');");
      a.innerHTML = "Download";
      td.appendChild(a);
      
  }
  
}
//-- Change Mode --

function changeMode()
{
  
  var xhr = createXHR();
  xhr.onreadystatechange = function(){
      if (xhr.readyState == 4){
          if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){
          	
          } else {
              alert("Request was unsuccessful: " + xhr.status);
          }
      }
  };

  
  var url = "getlistaccount.do";
  url = addURLParam(url, sort_id, sort_value);
  url = addURLParam(url, "page", page);
//  url = addURLParam(url, select_group.name, select_group[select_group.selectedIndex].value);
  
  
  xhr.open("get", url, true);
  xhr.send(null);
}
function checkSubmit()
{
	var file = document.getElementById("fileId").value;
	if (file.length > 0)
		{
		return true;
		}
	else
		{
		alert('Vui lòng chọn file');
		return false;
		}
	
}


//-------------organization.jsp
//get danh sach req by page
function getListReqByPage(page)
{

//  var form = document.getElementById("sortForm");
//  var select_group = form.elements["group"];
  
  var xhr = createXHR();
  xhr.onreadystatechange = function(){
      if (xhr.readyState == 4){
          if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

              var list_req = JSON.parse(xhr.responseText);
              //draw_phantrang(list_Account[0].SOLUONG,page);

              draw_table_danhsach_req(list_req, list_req.length,0);

              //undraw_loading();
          } else {
              alert("Request was unsuccessful: " + xhr.status);
          }
      }
  };

  
  var url = "displayOrgList.do";
  url = addURLParam(url, "page", page);
//  url = addURLParam(url, select_group.name, select_group[select_group.selectedIndex].value);
  
  
  xhr.open("get", url, true);
  xhr.send(null);
}

//--- ve
function draw_table_danhsach_req(list_req,length,index)
{
	// name cá»§a input checkbox
  var tr, td, i;
  var table = document.getElementById("table_req_list");

  // tháº» body cÅ©
  var body = table. tBodies[0];

  // váº½ má»™t thá»§ body má»›i chá»©a dá»¯ liá»‡u
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
      input.setAttribute("value",list_req[i].idOrg);
      td.appendChild(input);
     
      

      td = tr.insertCell(tr.cells.length);
      var a =  document.createElement("a");
      a.setAttribute("href","/displayOrg.do?id="+list_req[i].idOrg);
      a.innerHTML = list_req[i].idOrg;
      td.appendChild(a);
             
      
      td = tr.insertCell(tr.cells.length);
      td.innerHTML = list_req[i].nameOrg;
      
      td = tr.insertCell(tr.cells.length);
      td.innerHTML = list_req[i].websiteOrg;
      
  }
}


