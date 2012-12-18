/**
 * 
 */
package sp.action.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.ReportBlo;
import sp.dto.Report;
import sp.form.ReportForm;
import sp.util.CommonUtil;
import sp.util.Constant;

/**
 * @author Thuy
 * 
 */
public class DislayReportList extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession se = request.getSession();
		// get page
		String page = request.getParameter("page");
		int pageNumber = 1;

		if (page != null) {
			pageNumber = Integer.parseInt(page);
		}
		// get form to sort
		String idProject = request.getParameter("idProject");
		String idReq = request.getParameter("idReq");
		String idTask = request.getParameter("idTask");
		String status = request.getParameter("status");

		// call method to get Page Report
		List<ReportForm> reportList = ReportBlo.getListPage(idProject, idReq,
				idTask, status, pageNumber);

		//get total number page
		int total = ReportBlo.countOrganizationAll();
		
		//get List page number
		List<String> pageList = CommonUtil.countOrganizationAll(total);
		
		// save session
		se.setAttribute(Constant.REPORT_LIST, reportList);
		se.setAttribute(Constant.REPORT_PAGE_LIST, pageList);
		se.setAttribute(Constant.REPORT_PAGE_NUMBER, page);

		return mapping.findForward(Constant.SUCCESS);
	}
}
