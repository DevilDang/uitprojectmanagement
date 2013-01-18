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
import org.apache.struts.action.ActionMessages;

import sp.blo.TaskBlo;
import sp.dto.Task;
import sp.form.TaskForm;
import sp.util.Constant;
import sp.util.Validation;

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
			
			ActionMessages messages = Validation.checkDate(formTask.getMode(), formTask.getStartDate(), formTask.getEndDate());
			
			if (messages.size() == 0){
			
			//get  session
			HttpSession se = request.getSession();
			
			
			TaskForm sortForm = (TaskForm) se.getAttribute(Constant.RECORD_SORT);
//			String mode = (String) se.getAttribute(Constant.RECORD_FLAG);
			Task task ;

			//set sortForm into  formReport
			formTask.setIdProject(sortForm.getIdProject());
			
			//set idReq
			formTask.setIdReq(sortForm.getIdReq());
			
			//set idGroup
			formTask.setIdGroup(sortForm.getIdGroup());
			
			//mode insert
			if (Constant.MODE_INSERT.equals(formTask.getMode())){
				
				//set status = Open
				formTask.setStatus(Constant.OPEN);
				
				//update status of emplooyee
				TaskBlo.updateStatusTask(formTask.getEmailEmployee(), Constant.USER_ASSIGN_TASK);
			}
			//update
			else
			{
				TaskBlo.updateStatusUser(formTask.getId(), formTask.getEmailEmployee());
				
				// open -> close
				if (Constant.CLOSE.equals(formTask.getStatus())){
					TaskBlo.updateStatusTask(formTask.getEmailEmployee(), Constant.USER_FREE_TASK);
				}
				else
				{
					TaskBlo.updateStatusTask(formTask.getEmailEmployee(), Constant.USER_ASSIGN_TASK);
				}
			}
			
			task = formTask.getTask();
			
			//save dto
			TaskBlo.saveTask(task);
			
			//get task free
			List<String> idUserFree = TaskBlo.getUserFreeTask(task.getId());
			
			//save usertask free
			se.setAttribute(Constant.TASK_USER_FREE, idUserFree);
			
			//remove from session
			formTask.clear();
				
			}
			else
			{
				// storing messages 
		        saveMessages(request.getSession(), messages);
			}
			return mapping.findForward(Constant.SUCCESS);
		}
}
