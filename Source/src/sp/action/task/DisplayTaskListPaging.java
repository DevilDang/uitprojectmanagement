package sp.action.task;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.TaskBlo;
import sp.form.TaskForm;
import sp.util.CommonUtil;
import sp.util.Constant;
import sp.util.JSONObjectList;

public class DisplayTaskListPaging extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ajax = request.getParameter("ajax");
		
		HttpSession se = request.getSession();
		
		//get sortForm
		TaskForm sortForm = (TaskForm)se.getAttribute(Constant.RECORD_SORT);
		
		//get value from request
		String idProject = request.getParameter("project");
		String idReq = request.getParameter("req");
		String  idGroup = request.getParameter("group");
		String  kind = request.getParameter("kind");
		String status = request.getParameter("status");
		String pageNumber = request.getParameter("page");
		int page = 1;
		if (idProject != null){
			sortForm.setIdProject(Long.valueOf(idProject));
		}
		if (idReq != null){
			sortForm.setId(Long.valueOf(idReq));
		}
		if (idGroup != null){
			sortForm.setIdGroup(Long.valueOf(idGroup));
		}
		if (status != null){
			sortForm.setStatus(status);
		}
		if (kind != null){
			sortForm.setKind(kind);
		}
		if (pageNumber != null){
			page = Integer.parseInt(pageNumber);
			sortForm.setPage(page);
		}
		
		//save sortForm into session
		se.setAttribute(Constant.RECORD_SORT, sortForm);
		
		//get filter
		String filter = TaskBlo.getFilterSQL(sortForm);
		
		//call method sort
		List<TaskForm> taskList = TaskBlo.getTaskListByFilter(filter, page);
		
		//ajax
		if ("1".equals(ajax)){
			response.setCharacterEncoding("utf-8");
			//get PrintWriter
			PrintWriter out = response.getWriter();
			
			//return value (ajax)
			JSONObjectList jsonlist = TaskBlo.createJSONObjectList(taskList);
	        
	        out.write(jsonlist.toJSONtextString());
	        out.close();
	        return null;
		}
		else
		{
			//get total number page
			int total = TaskBlo.getNumberByFilter(filter);
			
			//get List page number
			List<String> pageList = CommonUtil.createPageList(total);
			
			// get group free
			List<String> idTaskList = TaskBlo.getUserFreeTask(sortForm.getIdGroup());
			
			//save session
			se.setAttribute(Constant.TASK_USER_FREE, idTaskList);
			se.setAttribute(Constant.RECORD_LIST, taskList);
			se.setAttribute(Constant.RECORD_PAGE_LIST, pageList);
			se.setAttribute(Constant.RECORD_PAGE_NUMBER, String.valueOf(page));
			//forward
			return mapping.findForward(Constant.SUCCESS);
		}
		
	}
}
