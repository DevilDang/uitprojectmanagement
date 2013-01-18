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
import sp.form.AccountForm;
import sp.form.GroupForm;
import sp.util.Constant;

public class GetAccount extends org.apache.struts.action.Action {
	
	 /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * Action này dùng để lấy thông tin chi tiết của một tài khoản
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
        
    	String email = request.getParameter("email");
    	int page = Integer.parseInt(request.getParameter("PAGE"));
    	 
    	UserDao userdao = new UserDao();
    	User user = userdao.getUser(email);
    	
    	AccountForm accountform = new AccountForm(user);
    	accountform.setRetypepassword(user.getPassword());
    	form = accountform;
    	request.getSession().setAttribute("AccountForm", form);
    	  	
        List<User> list_user =  userdao.getUserListFilter(page, "groupID==" + user.getGroupID(), "id desc");
        request.setAttribute(Constant.ACCOUNT_LIST, list_user);
        request.setAttribute("groupID", String.valueOf(user.getGroupID()));
        request.setAttribute("page_pos", page);
        request.setAttribute("isEdit", "edit");
        
        int count = PMF.countNumberAll(Class.forName("sp.dto.User"), "groupID==" + user.getGroupID());
    	int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));
    
        request.setAttribute("PAGE",countpage );
        
        
        return mapping.findForward(SUCCESS);
    }

}
