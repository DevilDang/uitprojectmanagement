package sp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dao.UserDao;
import sp.dto.User;
import sp.form.ChangePassForm;
import sp.util.Constant;

public class changepassword extends org.apache.struts.action.Action{
	/*
     * forward name="success" path=""
     */
	 private static final String SUCCESS = "success";
    /**
     * This is the action called from the Struts framework.
     *Class này dùng để đổi mật khẩu
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
        
    	 String userlogin = (String)request.getSession().getAttribute(Constant.User_Login);
    	 UserDao userdao = new UserDao();
    	 User user = userdao.getUser(userlogin);
    	 
    	 ChangePassForm changepassform = (ChangePassForm)form;
    	 user.setPassword(changepassform.getNewpassword());
    	 
    	 UserDao.saveUser(user);
    	 	
    	return mapping.findForward(SUCCESS);
    }

}
