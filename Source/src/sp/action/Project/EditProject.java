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
 		
 		String page_pos = request.getParameter("page_pos");
        
 		if("add".equals(checkMode))
        {
        	project.setIDproject(System.currentTimeMillis());
        	ProjectDao.saveProject(project);
        }else if("edit".equals(checkMode))
        {
        	ProjectDao.saveProject(project);
        }
        
        ProjectDao projectdao = new ProjectDao();
        List<Project> list_project =  projectdao.getProjectListFilter( Integer.parseInt(page_pos), "status==" + project.getStatus(), "IDproject desc");
        request.setAttribute(Constant.PROJECT_LIST, list_project);
        request.setAttribute("status", String.valueOf(project.getStatus()));
        
        request.setAttribute("page_pos", Integer.parseInt(page_pos));
        
        
        int count = PMF.countNumberAll(Class.forName("sp.dto.Project"), "status==" + project.getStatus());
    	int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));
    
        request.setAttribute("PAGE",countpage );
        
        return mapping.findForward(SUCCESS);
    }

}

