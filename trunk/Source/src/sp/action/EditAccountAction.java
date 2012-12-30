package sp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.UserBlo;
import sp.dao.PMF;
import sp.dao.UserDao;
import sp.dto.User;
import sp.form.AccountForm;

public class EditAccountAction extends org.apache.struts.action.Action{
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
    @SuppressWarnings("unchecked")
	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	AccountForm accountForm = (AccountForm)form;
    	User user = accountForm.getUser();
    	UserDao userdao = new UserDao();
    	if(userdao.checkExistUser(user.getEmail()))
    	{
    		UserDao.saveUser(user);
    	}
    	
    	List<User> list_user = (List<User>)PMF.getList(User.class);
    	System.out.println(user);
    	request.setAttribute("account_list", list_user);
        return mapping.findForward(SUCCESS);
    }

}
