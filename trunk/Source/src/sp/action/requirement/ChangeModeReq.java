package sp.action.requirement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.form.RequirementForm;
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
			
			//get req from session
			RequirementForm req = (RequirementForm) se.getAttribute(Constant.REQ);
			
			//clear
			req.clear();
			
			return mapping.findForward(Constant.SUCCESS);
		}
}

