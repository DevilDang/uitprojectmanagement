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
import sp.form.OrganizationForm;
import sp.util.CommonUtil;
import sp.util.Constant;

public class DislayOrganizationList extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession se = request.getSession();
		//get pagenumber
		//String pageNumber = request.getParameter("page");
		String pageNumber = (String)se.getAttribute(Constant.ORG_PAGE_NUMBER);
		int total = 0;
		int page = 1;
		if (pageNumber != null){
			page = Integer.parseInt(pageNumber);
		}
		
		//get data from DB depend on pageNumber
		List<OrganizationForm> formList = OrganizationBlo.getOrganizationList(page);
		
		//get total number record
		total = OrganizationBlo.countOrganizationAll();
		
		//total number of page
		List<String> pageList = CommonUtil.countOrganizationAll(total);
		
		//save into session
		se.setAttribute(Constant.ORGANIZATION_LIST, formList);
		se.setAttribute(Constant.ORG_PAGE_LIST, pageList);
		se.setAttribute(Constant.ORG_TOTAL_NUMBER, String.valueOf(total));
		
		//forward page result
		return mapping.findForward(Constant.SUCCESS);

	}

}