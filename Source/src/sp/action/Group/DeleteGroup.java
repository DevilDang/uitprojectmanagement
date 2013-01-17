package sp.action.Group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dao.GroupDao;
import sp.dao.PMF;
import sp.dao.ProjectDao;
import sp.dto.Group;
import sp.dto.Project;
import sp.util.Constant;

public class DeleteGroup  extends org.apache.struts.action.Action{
	/*
	 * forward name="success" path=""
	 */
	private static final String SUCCESS = "success";

	/**
	 * This is the action called from the Struts framework. Class này dùng để
	 * xóa các tài khoản
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 * @throws java.lang.Exception
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String group_name_array[] = request.getParameterValues("check");

		for (int i = 0; i < group_name_array.length; i++)
			PMF.deleteObject(Group.class, Long.parseLong(group_name_array[i]));

		int idProject = Integer.parseInt(request.getParameter("idProject"));
		int page = Integer.parseInt(request.getParameter("PAGE"));

		GroupDao groupdao = new GroupDao();
		List<Group> list_group = groupdao.getGroupListFilter(page,
				"idProject==" + idProject, "IDgroup desc");
		
		System.out.println("dang tan loc " + list_group.size() );
		request.setAttribute(Constant.GROUP_LIST, list_group);
		
		request.setAttribute("idProject", String.valueOf(idProject));
		
		 request.setAttribute("page_pos", page);
         
        int count = PMF.countNumberAll(Class.forName("sp.dto.Group"), "idProject==" + idProject);
    	int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));
    
        request.setAttribute("PAGE",countpage );

		return mapping.findForward(SUCCESS);
	}
}
