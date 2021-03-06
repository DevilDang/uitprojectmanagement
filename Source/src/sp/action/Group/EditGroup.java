package sp.action.Group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dao.GroupDao;
import sp.dao.PMF;
import sp.dao.UserDao;
import sp.dto.Group;
import sp.dto.User;
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
        	
        	group.setStatus( Constant.GROUP_FREE_REQ);
        	
        	
        	if(group.getIdProject() == 0)
        	{
        		group.setStatusGroup(Group.STATUS_FREE);
        	}else
        	{
        		group.setStatusGroup(Group.STATUS_OWN_A_GROUP);
        	}
        	
        	group.setIDgroup(System.currentTimeMillis());
        	GroupDao.saveGroup(group);
        	       	
        }else if("edit".equals(checkMode))
        {
        	if(group.getIdProject() == 0)
        	{
        		group.setStatusGroup(Group.STATUS_FREE);
        	}else
        	{
        		group.setStatusGroup(Group.STATUS_OWN_A_GROUP);
        	}
        	
        	GroupDao.saveGroup(group);
        	
        	String leader = group.getLeader();
        	if(!"".equals(leader))
        	{
        		UserDao userdao = new UserDao();
        		// lấy danh sách user của nhóm hiện tại
        		 List<User> list_user = userdao.getUserListFilter("groupID==" +group.getIDgroup() , "id desc");
        	        request.setAttribute(Constant.ACCOUNT_LIST, list_user);
        	     if(list_user != null)
        	     {
        	    	 for(int i = 0;i<list_user.size();i++)
        	    	 {
        	    		 if(User.LEADER.equals(list_user.get(i).getIdPermision()))
	    				 {
        	    			 list_user.get(i).setIdPermision(User.EMPLOYER);
        	    			 UserDao.saveUser( list_user.get(i));
        	    			 break;
	    				 }
        	    	 }
        	     }
        		
        		User user =  userdao.getUser(leader);
        		user.setIdPermision(User.LEADER);
        		UserDao.saveUser(user);
        	}
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
