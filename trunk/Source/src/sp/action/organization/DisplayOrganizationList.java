package sp.action.organization;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.OrganizationBlo;
import sp.dao.PMF;
import sp.dto.User;
import sp.form.OrganizationForm;
import sp.util.CommonUtil;
import sp.util.Constant;
import sp.util.JSONObjectList;

public class DisplayOrganizationList extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// get session
		HttpSession se = request.getSession();

		// get user from session
		String userId = (String) se.getAttribute(Constant.User_Login);
		
		User user = (User) PMF.getObject(User.class, userId);
	
		// get permision
		String permission = user.getIdPermision();
		
		//user co quyen admin
		if (User.ADMIN.equals(permission))
		{
		//get pagenumber
		String pageNumber = request.getParameter("page");
		
//		String pageNumber = (String)se.getAttribute(Constant.ORG_PAGE_NUMBER);
		int total = 0;
		int page = 1;
		
		//ajax
		if (pageNumber != null){
			page = Integer.parseInt(pageNumber);
			
			//get data from DB depend on pageNumber
			List<OrganizationForm> formList = OrganizationBlo.getOrganizationList(page);
			
			//get PrintWriter
			PrintWriter out = response.getWriter();
			
			//return value (ajax)
			JSONObjectList jsonlist = OrganizationBlo.createJSONObjectList(formList);
	        
	        out.write(jsonlist.toJSONtextString());
	        out.close();
	        return null;
		}
		else//action
		{
			//get data from DB depend on pageNumber
			List<OrganizationForm> formList = OrganizationBlo.getOrganizationList(page);
			
			//get total number record
			total = OrganizationBlo.countOrganizationAll();
			
			//total number of page
			List<String> pageList = CommonUtil.createPageList(total);
			
			//save into session
			se.setAttribute(Constant.ORGANIZATION_LIST, formList);
			se.setAttribute(Constant.ORG_PAGE_LIST, pageList);
			se.setAttribute(Constant.ORG_TOTAL_NUMBER, String.valueOf(total));
			
		}
		}
		
		//forward page result
		return mapping.findForward(Constant.FAILURE);

	}

}
