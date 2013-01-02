package sp.action.organization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.OrganizationBlo;
import sp.form.OrganizationForm;
import sp.util.Constant;

public class DislayOrganization extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//get id from request
		String id = request.getParameter("id");
		
		//get obj from id 
		OrganizationForm orgForm = OrganizationBlo.getOrganizationForm(id);
		
		HttpSession se = request.getSession();
		
		//save orgForm into request
		se.setAttribute(Constant.ORGANIZATION, orgForm);
		
		//mode update
		se.setAttribute("flagOrg", "2");
		
		//forward page result
		return mapping.findForward(Constant.SUCCESS);

	}

}
