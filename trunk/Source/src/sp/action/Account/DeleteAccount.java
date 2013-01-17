package sp.action.Account;


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
        
    	String user_name_array[] = request.getParameterValues("check");

		for (int i = 0; i < user_name_array.length; i++)
			PMF.deleteObject(User.class, user_name_array[i]);

		int IDgroup = Integer.parseInt(request.getParameter("IDgroup"));
		int page = Integer.parseInt(request.getParameter("PAGE"));

		UserDao userdao = new UserDao();
		List<User> list_user = userdao.getUserListFilter(page,
				"IDgroup==" + IDgroup, "id desc");
		
		request.setAttribute(Constant.ACCOUNT_LIST, list_user);
		
		request.setAttribute("IDgroup", String.valueOf(IDgroup));
		
		 request.setAttribute("page_pos", page);
         
        int count = PMF.countNumberAll(Class.forName("sp.dto.User"), "IDgroup==" + IDgroup);
    	int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));
    
        request.setAttribute("PAGE",countpage );

		return mapping.findForward(SUCCESS);
    }
    
}
