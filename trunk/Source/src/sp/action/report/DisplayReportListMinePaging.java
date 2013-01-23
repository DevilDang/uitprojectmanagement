package sp.action.report;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.ReportBlo;
import sp.form.ReportForm;
import sp.util.CommonUtil;
import sp.util.Constant;
import sp.util.JSONObjectList;

public class DisplayReportListMinePaging extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String ajax = request.getParameter("ajax");
		
		HttpSession se = request.getSession();
		//get sortForm
		ReportForm sortForm = (ReportForm)se.getAttribute(Constant.RECORD_SORT);
		
		//get value from request
		String idProject = request.getParameter("idProject");
		String idReq = request.getParameter("idReq");
		String  idGroup = request.getParameter("idGroup");
		String status = request.getParameter("status");
		String pageNumber = request.getParameter("page");
		int page = 0;
		if (idProject != null){
			sortForm.setIdProject(Long.valueOf(idProject));
		}
		if (idReq != null){
			sortForm.setIdReq(Long.valueOf(idReq));
		}
		if (idGroup != null){
			sortForm.setIdGroup(Long.valueOf(idGroup));
		}
		if (status != null){
			sortForm.setStatus(status);
		}
		if (pageNumber != null){
			page = Integer.parseInt(pageNumber);
			sortForm.setPage(page);
		}
		else
		{
			page = 1;
		}
		//get filter
		String filter = ReportBlo.getFilterMine(sortForm);
		
		//call method sort
		List<ReportForm> reportList = ReportBlo.getListPage(filter, page);
		
		if ("1".equals(ajax)){
			
			response.setCharacterEncoding("utf-8");
			//get PrintWriter
			PrintWriter out = response.getWriter();
			//return value (ajax)
			JSONObjectList jsonlist = ReportBlo.createJSONObjectList(reportList);
	        
	        out.write(jsonlist.toJSONtextString());
	        out.close();
	        return null;
		}
		else
		{
		//get total number page
		int total = ReportBlo.countReportAllBySQL(filter);
		
		//get List page number
		List<String> pageList = CommonUtil.createPageList(total);
		
		//save session
		se.setAttribute(Constant.RECORD_LIST, reportList);
		se.setAttribute(Constant.RECORD_PAGE_LIST, pageList);
		se.setAttribute(Constant.RECORD_PAGE_NUMBER, String.valueOf(page));
		return mapping.findForward(Constant.SUCCESS);
		}
	}
}
