package sp.action.Group;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.GroupBlo;
import sp.blo.ProjectBlo;
import sp.dao.GroupDao;
import sp.dao.ProjectDao;
import sp.dto.Group;
import sp.dto.Project;
import sp.util.JSONObjectList;

public class GetListGroup extends org.apache.struts.action.Action{
	
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
        long idProject = Long.parseLong(request.getParameter("idProject"));
        int page = Integer.parseInt(request.getParameter("PAGE"));
        
        System.out.println(idProject + "   " + page);
        GroupDao groupdao = new GroupDao();
        List<Group> list_group = groupdao.getGroupListFilter(page, "idProject=="+idProject, "IDgroup desc");
        
        JSONObjectList jsonlist = GroupBlo.createJSONObjectList(list_group);
        
        out.write(jsonlist.toJSONtextString());
        out.close();
    	
        return mapping.findForward(SUCCESS);
    }

}
