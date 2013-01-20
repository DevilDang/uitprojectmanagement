package sp.action.Account;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.UserBlo;
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
    	response.setCharacterEncoding("utf-8");
    	PrintWriter out = response.getWriter();	
        long groupID = Long.parseLong(request.getParameter("groupID"));
        int page = Integer.parseInt(request.getParameter("PAGE"));
        
        UserDao userdao = new UserDao();
        List<User> list_user;
        if(groupID == -1)
        {
        	list_user = userdao.getUserList(page,"id desc");
        }
        else
        {
        	list_user = userdao.getUserListFilter(page, "groupID=="+groupID, "id desc");
        }
        
        JSONObjectList jsonlist = UserBlo.createJSONObjectList(list_user);
        
        out.write(jsonlist.toJSONtextString());
        out.close();
    	
        return mapping.findForward(SUCCESS);
    }
}
