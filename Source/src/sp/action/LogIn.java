package sp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dto.User;

import sp.dao.PMF;
import sp.dao.UserDao;
import sp.form.UserForm;
import sp.util.Constant;

/**
 *
 * @author ITDevil
 */
public class LogIn extends org.apache.struts.action.Action {

    /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

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
    	
    	String typelogin = request.getParameter("typeLogin");
    	if("GoogleAccount".equals(typelogin))
    	{   
    		System.out.println(typelogin + 1);
    		if(request.getUserPrincipal() != null)
    		{
    			System.out.println(typelogin + 1);
    			String username = request.getUserPrincipal().getName();
    			UserDao usedao = new UserDao();
    			if(usedao.checkExistUser(username))
    			{
    				request.getSession().setAttribute(Constant.Type_Login, Constant.GOOGLE_AUTHENTICATION);
    				request.getSession().setAttribute(Constant.User_Login, username);
    				return mapping.findForward(SUCCESS);	
    			}
    			else
    			{
    				
    				response.sendRedirect("/login.jsp?statuslogin=false");
    				return null;
    			}
    		}else
    		{
    			return mapping.findForward(FAIL);
    		}
    	}
    	else
    	{
    	
	    	UserForm userlogin = (UserForm)form;
	    	request.getSession().setAttribute(Constant.User_Login, userlogin);
	    	request.getSession().setAttribute(Constant.Type_Login, Constant.MY_AUTHENTICATION);
	    	User  user = (User)PMF.getObject(User.class, userlogin.getUsername());
	    	user.setStatusLogin(true);
	        return mapping.findForward(SUCCESS);
    	}
    	
    }
}
