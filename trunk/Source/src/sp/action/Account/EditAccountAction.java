package sp.action.Account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dao.GroupDao;
import sp.dao.PMF;
import sp.dao.ProjectDao;
import sp.dao.UserDao;
import sp.dto.Group;
import sp.dto.Project;
import sp.dto.User;
import sp.form.AccountForm;
import sp.util.Constant;

public class EditAccountAction extends org.apache.struts.action.Action{
    /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * Class này dùng để thêm mới, chỉnh sửa một tài khoản
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
    	UserDao userdao = new UserDao();
    	AccountForm accountForm = (AccountForm)form;
    	User user = accountForm.getUser();
 		String checkMode = request.getParameter("isEdit");
 		String page_pos = request.getParameter("page_pos");
 		
        if("add".equals(checkMode))
        {
        	user.setId(System.currentTimeMillis());
        	user.setStatusTask(Constant.USER_FREE_TASK);
        	UserDao.saveUser(user);
        	
        	
        }else if("edit".equals(checkMode))
        {
        	System.out.println(" Dang atn kic iu dào");
        	
        	// kiểm tra quyền hiện tại
        	User user_current = userdao.getUser(user.getEmail());
        	if(User.LEADER.equals(user_current.getIdPermision()))
        	{
        		if(!User.LEADER.equals(user.getIdPermision()))
        		{
        			GroupDao groupdao = new GroupDao();
            		Group group = groupdao.getGroup(user.getGroupID());
            		if(group != null)
            		{
            			group.setLeader("");
            			GroupDao.saveGroup(group);
            		}
            		
            		if(User.PROJECT_MANAGER.equals(user.getIdPermision()) || User.ADMIN.equals(user.getIdPermision()))
            		{
            			user.setGroupID(0);
            		}
        		}
        		
        		
        	}
        	else if(User.PROJECT_MANAGER.equals(user_current.getIdPermision()))
        	{
        		if(!User.PROJECT_MANAGER.equals(user.getIdPermision()))
        		{
        			ProjectDao projectDao = new ProjectDao();
        			List<Project> list_poject = projectDao.getProjectListFilter("projectmanager=='"+ user.getEmail()+"'", "IDproject desc");
        			for(int i = 0;i<list_poject.size();i++)
        			{
        				list_poject.get(i).setProjectmanager("");
        				ProjectDao.saveProject(list_poject.get(i));
        			}
        			
        			if(User.ADMIN.equals(user.getIdPermision()))
            		{
            			user.setGroupID(0);
            		}
            		
        		}
        	}else if(User.EMPLOYER.equals(user_current.getIdPermision()))
        	{
        		if(User.PROJECT_MANAGER.equals(user.getIdPermision()) || User.ADMIN.equals(user.getIdPermision()))
        		{
        			user.setGroupID(0);
        		}
        	}
        	
        	if(User.LEADER.equals(user.getIdPermision()))
        	{
        		GroupDao groupdao = new GroupDao();
        		Group group = groupdao.getGroup(user.getGroupID());
        		if(group != null)
        		{
        			group.setLeader(user.getEmail());
        			GroupDao.saveGroup(group);
        		}
        		
        	}
        	
        	UserDao.saveUser(user);
        }
        
        
        UserDao usertdao = new UserDao();
        List<User> list_user =  usertdao.getUserListFilter(Integer.parseInt(page_pos), "groupID==" + user.getGroupID(), "id desc");
        request.setAttribute(Constant.ACCOUNT_LIST, list_user);
        request.setAttribute("groupID", String.valueOf(user.getGroupID()));        
        request.setAttribute("page_pos", Integer.parseInt(page_pos));
               
        int count = PMF.countNumberAll(Class.forName("sp.dto.User"), "groupID==" + user.getGroupID());
    	int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));
    
        request.setAttribute("PAGE",countpage );
        
        return mapping.findForward(SUCCESS);
    }

}
