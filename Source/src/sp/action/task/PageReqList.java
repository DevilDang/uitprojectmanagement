package sp.action.task;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.TaskBlo;
import sp.form.TaskForm;
import sp.util.CommonUtil;
import sp.util.Constant;
import sp.util.JSONObject;

public class PageReqList extends org.apache.struts.action.Action{

	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	PrintWriter out = response.getWriter();
    	
    	//get HttpSession
    	HttpSession se = request.getSession();
    	TaskForm sortForm = (TaskForm)se.getAttribute(Constant.RECORD_SORT);
    	
    	//get filter
    	String filter = TaskBlo.getFilterSQL(sortForm);
    	
		//get total number page
		int total = TaskBlo.getNumberByFilter(filter);
		
		//get max page
		int page = CommonUtil.countPageList(total);
		
		String keys[] = {"Count", "CountPage"};
    	JSONObject jsonobject = new JSONObject(keys);
    	jsonobject.getObject().put(keys[0], String.valueOf(total));
    	jsonobject.getObject().put(keys[1], String.valueOf(page));
    	
    	out.write(jsonobject.toJSONtextString());
        out.close();
           	
        return null;
    }
}
