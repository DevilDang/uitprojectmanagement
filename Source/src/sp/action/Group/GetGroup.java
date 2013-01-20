package sp.action.Group;

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
import sp.form.GroupForm;
import sp.util.Constant;

public class GetGroup extends org.apache.struts.action.Action{
	
	/*
     * forward name="success" path=""
     */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * Action này dùng để lấy danh sách các dự án
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
    	
    	String IDgroup = request.getParameter("IDgroup");
    	int page = Integer.parseInt(request.getParameter("PAGE"));
    	 
    	GroupDao groupdao = new GroupDao();
    	Group group = groupdao.getGroup(Long.parseLong(IDgroup));
    	
    	GroupForm groupform = new GroupForm(group);
    	form = groupform;
    	request.getSession().setAttribute("GroupForm", form);
    	  	
        List<Group> list_Group =  groupdao.getGroupListFilter(page, "idProject==" + group.getIdProject(), "IDgroup desc");
        request.setAttribute(Constant.GROUP_LIST, list_Group);
        request.setAttribute("idProject", String.valueOf(group.getIdProject()));
        request.setAttribute("page_pos", page);
        request.setAttribute("isEdit", "edit");
        
        int count = PMF.countNumberAll(Class.forName("sp.dto.Group"), "idProject==" + group.getIdProject());
    	int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));
    
        request.setAttribute("PAGE",countpage );
        
        // lấy ra danh sách user thuộc nhóm
        UserDao userdao = new UserDao();
        
        List<User> list_user = userdao.getUserListFilter("groupID==" +group.getIDgroup() , "id desc");
        request.setAttribute(Constant.ACCOUNT_LIST, list_user);
        
        return mapping.findForward(SUCCESS);
    }

}
