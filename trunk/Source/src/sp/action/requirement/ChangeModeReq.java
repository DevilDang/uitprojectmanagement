package sp.action.requirement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class ChangeModeReq extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			HttpSession se = request.getSession();
			//get mode 
			String mode = request.getParameter("mode");
			
			//remove 
			se.removeAttribute(Constant.REQ);
			
			//mode Insert
			se.setAttribute(Constant.RECORD_FLAG, mode);
			
			return mapping.findForward(Constant.SUCCESS);
		}
}

