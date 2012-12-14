package sp.action.organization;

import java.util.List;

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

public class DislayOrganizationList extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//get pagenumber
		String pageNumber = request.getParameter("page");
		HttpSession se = request.getSession();
		int page = 0;
		if (pageNumber != null){
			page = Integer.parseInt(pageNumber);
		}
		
		//get data from DB depend on pageNumber
		List<OrganizationForm> formList = OrganizationBlo.getOrganizationList(page);
		
		//save into session
		se.setAttribute(Constant.ORGANIZATION_LIST, formList);
		
		//forward page result
		return mapping.findForward(Constant.SUCCESS);

	}

}
