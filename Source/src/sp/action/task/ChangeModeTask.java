package sp.action.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.form.TaskForm;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class ChangeModeTask extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			HttpSession se = request.getSession();

			//get task from session
			TaskForm task = (TaskForm) se.getAttribute(Constant.TASK);
			
			//clear Task
			task.clear();
			
			return mapping.findForward(Constant.SUCCESS);
		}
}

