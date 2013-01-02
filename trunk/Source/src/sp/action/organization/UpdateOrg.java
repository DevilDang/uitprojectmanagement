package sp.action.organization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.OrganizationBlo;
import sp.dto.Organization;
import sp.form.OrganizationForm;
import sp.util.Constant;

public class UpdateOrg extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get form after check validate
		OrganizationForm orgForm = (OrganizationForm) form;

		//get form
		Organization org = orgForm.getOrganization();
		
		// update or insert org into DB
		OrganizationBlo.saveOrganization(org);
		
		//remove org from session
		HttpSession se = request.getSession();
		se.removeAttribute(Constant.ORGANIZATION);
		
		//forward page result
		return mapping.findForward(Constant.SUCCESS);

	}

}
