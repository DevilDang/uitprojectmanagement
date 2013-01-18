package sp.action.Project;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.ProjectBlo;
import sp.dao.ProjectDao;
import sp.dto.Project;
import sp.util.JSONObjectList;

public class GetListProject extends org.apache.struts.action.Action {
	
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
    	response.setCharacterEncoding("utf-8");
    	PrintWriter out = response.getWriter();
        String status = request.getParameter("status");
        int page = Integer.parseInt(request.getParameter("PAGE"));
        
        System.out.println(status + "   " + page);
        ProjectDao projectdao = new ProjectDao();
        List<Project> list_project = projectdao.getProjectListFilter(page, "status=='"+status+"'", "IDproject desc");
        
        System.out.println(status + "   " + list_project.size());
        JSONObjectList jsonlist = ProjectBlo.createJSONObjectList(list_project);
        
        out.write(jsonlist.toJSONtextString());
        out.close();
    	
        return mapping.findForward(SUCCESS);
    }

}
