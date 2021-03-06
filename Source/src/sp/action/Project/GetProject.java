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

public class GetProject  extends org.apache.struts.action.Action {
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
    	
    	String IDproject = request.getParameter("IDproject");
    	int page = Integer.parseInt(request.getParameter("PAGE"));
    	 
    	ProjectDao projectdao = new ProjectDao();
    	Project project = projectdao.getProject(Long.parseLong(IDproject));
    	
    	ProjectForm projectform = new ProjectForm(project);
    	form = projectform;
    	request.getSession().setAttribute("ProjectForm", form);
    	
    	
    	
        List<Project> list_project =  projectdao.getProjectListFilter(page, "status==" + project.getStatus(), "IDproject desc");
        request.setAttribute(Constant.PROJECT_LIST, list_project);
        request.setAttribute("status", project.getStatus());
        request.setAttribute("page_pos", page);
        request.setAttribute("isEdit", "edit");
        
        int count = PMF.countNumberAll(Class.forName("sp.dto.Project"), "status=='" + project.getStatus()+"'");
    	int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));
    
        request.setAttribute("PAGE",countpage );
        
        
        return mapping.findForward(SUCCESS);
    }

}
