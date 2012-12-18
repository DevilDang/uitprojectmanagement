package sp.action.organization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.util.Constant;
/*
 * using ajax
 */
public class DeleteOrganization extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//get mode
		String idList = request.getParameter("idList");
		
//		HttpSession se = request.getSession();
		
		//specify mode
//		se.setAttribute("flagOrg", mode);
		
		//forward page result
		return mapping.findForward(Constant.SUCCESS);

	}

}
