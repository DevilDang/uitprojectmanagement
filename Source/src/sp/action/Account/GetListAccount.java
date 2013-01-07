package sp.action.Account;

import java.io.PrintWriter;
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
import sp.util.JSONObjectList;

public class GetListAccount extends org.apache.struts.action.Action {
	 /*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * Action này dùng để lấy danh sách các tài khoản
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
    	// lấy ra mã nhóm
    	PrintWriter out = response.getWriter();
        long group = Long.parseLong(request.getParameter("group"));
        int page = Integer.parseInt(request.getParameter("PAGE"));
        
        List<User> list_user = UserDao.getListAccountSorted(group, page);
        
        JSONObjectList jsonlist = UserBlo.createJSONObjectList(list_user);
        
        out.write(jsonlist.toJSONtextString());
        out.close();
        
        return null;
    }
}
