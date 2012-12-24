package sp.action.Account;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dao.PMF;
import sp.dto.User;
import sp.util.Constant;

public class DeleteAccount extends org.apache.struts.action.Action{

	 /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * Class này dùng để xóa các tài khoản
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
        
    	String login_name_array[] = request.getParameterValues("check");
    	for(int i = 0;i<login_name_array.length;i++)
    		PMF.deleteObject(User.class, login_name_array[i]);
    	
    	List<User> list_user = (List<User>)PMF.getList(User.class);  	
    	request.setAttribute(Constant.ACCOUNT_LIST, list_user);
    	System.out.println(list_user.size() + "snaznajhzbnahjzb");
        return mapping.findForward(SUCCESS);
    }
    
}
