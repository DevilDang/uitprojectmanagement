package sp.action.organization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("123");
		// get form after check validate
		OrganizationForm orgForm = (OrganizationForm) form;

		// update or insert org into DB
		Organization org = orgForm.getOrganization();
		OrganizationBlo.saveOrganization(org);
		
		//forward page result
		return mapping.findForward(Constant.SUCCESS);

	}

}
