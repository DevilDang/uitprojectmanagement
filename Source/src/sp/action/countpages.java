package sp.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.dao.PMF;
import sp.util.Constant;
import sp.util.JSONObject;

public class countpages extends org.apache.struts.action.Action{
	
	 /*
     * forward name="success" path=""
     */
    
    /**
     * This is the action called from the Struts framework.
     *Class này dùng để tính số lượng page khi view danh sách
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
        
    	PrintWriter out = response.getWriter();
    	String classname = request.getParameter("classname");
    	String filter = request.getParameter("filter");
    	
    	System.out.println(classname + "   " + filter);
    	int count = PMF.countNumberAll(Class.forName(classname), filter);
    	int countpage = (count < Constant.RECORD ? 1 : (count % Constant.RECORD == 0 ? count/Constant.RECORD : count/Constant.RECORD + 1));
    	
    	String keys[] = {"Count", "CountPage"};
    	JSONObject jsonobject = new JSONObject(keys);
    	jsonobject.getObject().put(keys[0], String.valueOf(count));
    	jsonobject.getObject().put(keys[1], String.valueOf(countpage));
    	
    	out.write(jsonobject.toJSONtextString());
        out.close();
           	
        return null;
    }

}
