package sp.action.report;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.ReportBlo;
import sp.form.ReportForm;
import sp.util.CommonUtil;
import sp.util.Constant;
import sp.util.JSONObject;

public class PageReportList extends org.apache.struts.action.Action{
	
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
    	//get flag
    	String flag = request.getParameter("flag");
    	
    	//get HttpSession
    	HttpSession se = request.getSession();
    	ReportForm sortForm = (ReportForm)se.getAttribute(Constant.RECORD_SORT);
    	
    	String filter;
    	//get page my report
    	if (flag != null){
    		
    		//get filter
    		filter = ReportBlo.getFilterMine(sortForm);
    	}
    	else
    	{
    		//get filter
    		filter = ReportBlo.getFilter(sortForm);
    	}
    	
		//get total number page
		int total = ReportBlo.countReportAllBySQL(filter);
		
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

