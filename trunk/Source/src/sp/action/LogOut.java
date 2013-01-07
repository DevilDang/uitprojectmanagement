package sp.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import sp.util.Constant;

public class LogOut extends org.apache.struts.action.Action  {
	
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
        
    	String typelogin = (String)request.getSession().getAttribute(Constant.Type_Login);    	
    	if(Constant.GOOGLE_AUTHENTICATION.equals(typelogin))
    	{
    		request.getSession().removeAttribute(Constant.Type_Login);
        	request.getSession().removeAttribute(Constant.User_Login);
    		UserService userService = UserServiceFactory.getUserService();
    		String logout = "";
    		if (request.getUserPrincipal() != null) {
    			 
    			 
    			 logout =  userService.createLogoutURL("/login.jsp") ;   
    			 response.sendRedirect(logout);
    			 return null;                       
    		}
    	}
    	else if(Constant.MY_AUTHENTICATION.equals(typelogin))
    	{
    		request.getSession().removeAttribute(Constant.Type_Login);
        	request.getSession().removeAttribute(Constant.User_Login);
    		return mapping.findForward(SUCCESS);
    	}
    	
    	return null;
    }

}
