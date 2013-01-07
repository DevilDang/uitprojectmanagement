package sp.action.organization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.OrganizationBlo;
import sp.util.Constant;
/*
 * using ajax
 */
public class DeleteOrganization extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String org_id_array[] = request.getParameterValues("check");
	   	 
		//delete
		OrganizationBlo.deleteOrganizationList(org_id_array);
		
		//forward page result
		return mapping.findForward(Constant.SUCCESS);

	}

}
