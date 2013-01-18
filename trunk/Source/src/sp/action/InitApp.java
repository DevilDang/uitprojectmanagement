package sp.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dto.User;

import sp.dao.PMF;
import sp.util.Constant;

public class InitApp extends org.apache.struts.action.Action {
	
	 /*
     * forward name="success" path=""
     */
 

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
    	
    	User user = (User)PMF.getObject(User.class, "admin");
    	
    	if(user == null)
    	{
    		user = new User();
    		user.setEmail("admin");
    		user.setGroupID(0);
    		user.setId(System.currentTimeMillis());
    		user.setIdPermision(User.ADMIN);
    		user.setName("admin");
    		user.setPassword("admin");

        	user.setStatusTask(Constant.USER_FREE_TASK);
        	
    		PMF.insertObject(user);
    		
    		response.sendRedirect("/login.jsp?init=true");
    	}
    	else
    	{
    		response.sendRedirect("/login.jsp?init=false");
    	}
    	
    	return null;
    }

}
