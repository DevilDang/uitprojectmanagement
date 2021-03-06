package sp.action.Project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dao.PMF;
import sp.dao.ProjectDao;
import sp.dto.Project;
import sp.util.Constant;

public class DeleteProject extends org.apache.struts.action.Action {

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

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String project_name_array[] = request.getParameterValues("check");

		for (int i = 0; i < project_name_array.length; i++)
			PMF.deleteObject(Project.class, Long.parseLong(project_name_array[i]));

		String status = request.getParameter("status");
		int page = Integer.parseInt(request.getParameter("PAGE"));

		ProjectDao projectdao = new ProjectDao();
		List<Project> list_project = projectdao.getProjectListFilter(page,
				"status==" + status, "IDproject desc");
		request.setAttribute(Constant.PROJECT_LIST, list_project);
		
		request.setAttribute("status", status);
		request.setAttribute("page_pos", page);
		
		 int count = PMF.countNumberAll(Class.forName("sp.dto.Project"), "status=='"+status+"'");
	     int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));    
	     request.setAttribute("PAGE",countpage );
		return mapping.findForward(SUCCESS);
	}

}
