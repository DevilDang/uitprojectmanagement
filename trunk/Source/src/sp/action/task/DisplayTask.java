package sp.action.task;

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
import sp.util.Constant;

public class DisplayTask extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession se = request.getSession();
		
		//get id
		String id = request.getParameter("id");
		
		//get Report
		if (id != null){
			TaskForm formTask = TaskBlo.getTaskForm(Long.parseLong(id));
			
			formTask.setMode(Constant.MODE_UPDATE);
			
			//get task free
			List<String> idUserFree = TaskBlo.getUserFreeTask(formTask.getIdGroup());
			
			//save usertask free
			se.setAttribute(Constant.TASK_USER_FREE, idUserFree);
			
			//save into session, name = name of bean config into file config struts
			se.setAttribute(Constant.TASK, formTask);
			
		}
		return mapping.findForward(Constant.SUCCESS);
	}
}

