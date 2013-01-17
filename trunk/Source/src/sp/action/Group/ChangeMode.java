package sp.action.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.form.GroupForm;
import sp.form.ProjectForm;

public class ChangeMode extends Action{
	
	/*
	 * forward name="success" path=""
	 */
	private static final String SUCCESS = "success";

	/**
	 * This is the action called from the Struts framework. Class này dùng chuyển sang giao diện thêm mới
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

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GroupForm projectform  = new GroupForm();
		
		form = projectform;
		request.getSession().setAttribute("GroupForm", form);
		request.setAttribute("isEdit", "add");
		//forward page result
		return mapping.findForward(SUCCESS);
	}

}
