package sp.action.Group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dao.GroupDao;
import sp.dao.PMF;
import sp.dto.Group;
import sp.form.GroupForm;
import sp.util.Constant;

public class EditGroup extends org.apache.struts.action.Action{
	/*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
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
    	
    	GroupForm groupform = (GroupForm)form;	
 		Group group = groupform.getGroup();
 		String checkMode = request.getParameter("isEdit");
 		String page_pos = request.getParameter("page_pos");
 		
        if("add".equals(checkMode))
        {
        	if(group.getIdProject() == 0)
        	{
        		group.setStatus(Group.STATUS_FREE);
        	}else
        	{
        		group.setStatus(Group.STATUS_OWN_A_GROUP);
        	}
        	
        	group.setIDgroup(System.currentTimeMillis());
        	GroupDao.saveGroup(group);
        }else if("edit".equals(checkMode))
        {
        	if(group.getIdProject() == 0)
        	{
        		group.setStatus(Group.STATUS_FREE);
        	}else
        	{
        		group.setStatus(Group.STATUS_OWN_A_GROUP);
        	}
        	
        	GroupDao.saveGroup(group);
        }
        
        
        GroupDao grouptdao = new GroupDao();
        List<Group> list_group =  grouptdao.getGroupListFilter(Integer.parseInt(page_pos), "idProject==" + group.getIdProject(), "IDgroup desc");
        request.setAttribute(Constant.GROUP_LIST, list_group);
        request.setAttribute("idProject", String.valueOf(group.getIdProject()));
        
        request.setAttribute("page_pos", Integer.parseInt(page_pos));
               
        int count = PMF.countNumberAll(Class.forName("sp.dto.Group"), "idProject==" + group.getIdProject());
    	int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));
    
        request.setAttribute("PAGE",countpage );
        
        return mapping.findForward(SUCCESS);
    	
    }
    
   
}
