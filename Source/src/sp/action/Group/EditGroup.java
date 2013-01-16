package sp.action.Group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.tools.ant.taskdefs.Sleep;

import sp.dao.GroupDao;
import sp.dao.ProjectDao;
import sp.dto.Group;
import sp.dto.Project;
import sp.form.GroupForm;
import sp.form.ProjectForm;
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
        List<Group> list_group =  grouptdao.getGroupListFilter(1, "idProject==" + group.getIdProject(), "IDgroup desc");
        request.setAttribute(Constant.GROUP_LIST, list_group);
        request.setAttribute("idProject", String.valueOf(group.getIdProject()));
        
        return mapping.findForward(SUCCESS);
    	
    }
    
   
}
