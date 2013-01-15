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
import sp.form.ReportForm;
import sp.util.CommonUtil;
import sp.util.Constant;

/**
 * @author T
 * 
 */
public class DisplayReportListMine extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// get session
		HttpSession se = request.getSession();

		// get sortForm from session
		ReportForm sortForm = (ReportForm) se
				.getAttribute(Constant.RECORD_SORT);
		if (sortForm != null) {
			sortForm.setStatus(Constant.REPORT_NEW);
			String filter = ReportBlo.getFilterMine(sortForm);

			// call method to get Page Report
			List<ReportForm> reportList = ReportBlo.getListPageMine(filter, 1);
			//
			// get total number page
			int total = ReportBlo.countReportAllBySQL(filter);

			// get List page number
			List<String> pageList = CommonUtil.createPageList(total);

			// save session
			se.setAttribute(Constant.RECORD_LIST, reportList);
			se.setAttribute(Constant.RECORD_PAGE_LIST, pageList);
			se.setAttribute(Constant.RECORD_PAGE_NUMBER, "1");
			se.setAttribute(Constant.RECORD_SORT, sortForm); // su dung khi //
																// update/insert
			//
			ReportForm reportForm = new ReportForm();
			reportForm.setMode(Constant.MODE_INSERT);

			// save in session
			se.setAttribute(Constant.REPORT, reportForm);
			//
			return mapping.findForward(Constant.SUCCESS);
		} else {
			return mapping.findForward(Constant.FAILURE);
		}
	}
}
