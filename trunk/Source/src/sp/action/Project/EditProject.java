package sp.action.Project;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import sp.dao.ProjectDao;
import sp.dao.UserDao;
import sp.dto.Project;
import sp.form.ProjectForm;
import sp.util.Constant;

public class EditProject extends org.apache.struts.action.Action {
	
	 /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * Action này dùng để lấy danh sách các dự án
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
           	
    	ProjectForm projectform = (ProjectForm)form;	
 		Project project = projectform.getProject();
 		String checkMode = request.getParameter("isEdit");
        if("add".equals(checkMode))
        {
        	project.setIDproject(System.currentTimeMillis());
        	ProjectDao.saveUser(project);
        }else if("edit".equals(checkMode))
        {
        	ProjectDao.saveUser(project);
        }
        
        ProjectDao projectdao = new ProjectDao();
        List<Project> list_project =  projectdao.getProjectListFilter(1, "status==" + project.getStatus(), "IDproject desc");
        request.setAttribute(Constant.PROJECT_LIST, list_project);
        request.setAttribute("status", String.valueOf(project.getStatus()));
        
        return mapping.findForward(SUCCESS);
    }

}

