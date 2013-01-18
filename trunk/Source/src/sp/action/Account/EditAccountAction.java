package sp.action.Account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.UserBlo;
import sp.dao.GroupDao;
import sp.dao.PMF;
import sp.dao.UserDao;
import sp.dto.Group;
import sp.dto.User;
import sp.form.AccountForm;
import sp.form.GroupForm;
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
    @SuppressWarnings("unchecked")
	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
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
