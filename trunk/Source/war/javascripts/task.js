function getListTask(flag,page)
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
		select = form.elements["kind"];
	}
	else if (flag == 5){
		select = form.elements["status"];
	}
	
	var sort_value =select[select.selectedIndex].value;
	
	ajax_getListTask(page,select.name,sort_value);
	
	ajax_getPageTask(page,1);
}


//tinh so page
function ajax_getPageTask(page, flag)
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

  var url = "countTaskListPage.do";
  //my report
  if (flag == 1){
  	 url = addURLParam(url, "flag", "1");
  }
  xhr.open("get", url, true);
  xhr.send(null);
}


//get danh sach req
function ajax_getListTask(page, sort_name, sort_value)
{
	
  var xhr = createXHR();
  xhr.onreadystatechange = function(){
      if (xhr.readyState == 4){
          if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){
              var list_report = JSON.parse(xhr.responseText);
              //draw_phantrang(list_Account[0].SOLUONG,page);
              draw_table_danhsach_task(list_report, list_report.length,0);

              //undraw_loading();
          } else {
              alert("Request was unsuccessful: " + xhr.status);
          }
      }
  };

  
  var url = "displayTaskListPaging.do";
  url = addURLParam(url, sort_name, sort_value);
  url = addURLParam(url, "page", page);
  url = addURLParam(url, "ajax", "1");
//  url = addURLParam(url, select_group.name, select_group[select_group.selectedIndex].value);
  
  
  xhr.open("get", url, true);
  xhr.send(null);
}

//get danh sach report by page
function getListTaskByPage(page)
{
	
  var xhr = createXHR();
  xhr.onreadystatechange = function(){
      if (xhr.readyState == 4){
          if ((xhr.status  >= 200  &&  xhr.status  <  300) || xhr.status == 304){

              var list_report = JSON.parse(xhr.responseText);
              //draw_phantrang(list_Account[0].SOLUONG,page);

              draw_table_danhsach_task(list_report, list_report.length,0);

              //undraw_loading();
          } else {
              alert("Request was unsuccessful: " + xhr.status);
          }
      }
  };

  
  var url = "displayTaskListPaging.do";
  url = addURLParam(url, "page", page);
  url = addURLParam(url, "ajax", "1");
//  url = addURLParam(url, select_group.name, select_group[select_group.selectedIndex].value);
  
  
  xhr.open("get", url, true);
  xhr.send(null);
}

function draw_table_danhsach_task(list_report,length,index)
{
	
  var tr, td, i;
  var table = document.getElementById("table_task_list");

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
      input.setAttribute("value",list_report[i].id);
      td.appendChild(input);
     
      

      td = tr.insertCell(tr.cells.length);
      var a =  document.createElement("a");
      a.setAttribute("href","/displayTask.do?id="+list_report[i].id);
      a.innerHTML = list_report[i].id;
      td.appendChild(a);
             
      
      td = tr.insertCell(tr.cells.length);
      td.innerHTML = list_report[i].nameTask;
      
      td = tr.insertCell(tr.cells.length);
      td.innerHTML = list_report[i].nameGroup;
      
      td = tr.insertCell(tr.cells.length);
      td.innerHTML = list_report[i].process;
      
      
  }
  
}

//function changeGroup(this){
//	alert('js');
//	this.value = this.text;
//}
