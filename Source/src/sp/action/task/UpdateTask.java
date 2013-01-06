/**
 * 
 */
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
import sp.dto.Task;
import sp.form.TaskForm;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class UpdateTask extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			//get form
			TaskForm formTask = (TaskForm)form;
			
			//get  session
			HttpSession se = request.getSession();
			TaskForm sortForm = (TaskForm) se.getAttribute(Constant.RECORD_SORT);
			String mode = (String) se.getAttribute(Constant.RECORD_FLAG);
			Task task ;
			

			//set sortForm into  formReport
			formTask.setIdProject(sortForm.getIdProject());
			
			//set idReq
			formTask.setIdReq(sortForm.getIdReq());
			
			//set idGroup
			formTask.setIdGroup(sortForm.getIdGroup());
			
			//mode insert
			if (Constant.MODE_INSERT.equals(mode)){
				
				//update status of emplooyee
				TaskBlo.updateStatusTask(formTask.getEmailEmployee(), Constant.USER_ASSIGN_TASK);
				
			}
			//update
			else
			{
				TaskBlo.updateStatusUser(formTask.getId(), formTask.getEmailEmployee());
			}
			
			task = formTask.getTask();
			
			//save dto
			TaskBlo.saveTask(task);
			
			//get task free
			List<String> idUserFree = TaskBlo.getUserFreeTask(task.getId());
			
			//save usertask free
			se.setAttribute(Constant.TASK_USER_FREE, idUserFree);
			
			//remove from session
			se.removeAttribute(Constant.TASK);
				
			
			return mapping.findForward(Constant.SUCCESS);
		}
}
