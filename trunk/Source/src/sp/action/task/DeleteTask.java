package sp.action.task;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.TaskBlo;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class DeleteTask extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			

			String[] task_id_array = request.getParameterValues("check");
			
			//delete
			TaskBlo.deleteReqList(task_id_array);
			
			//forward page result
			return mapping.findForward(Constant.SUCCESS);
			
		}
}

