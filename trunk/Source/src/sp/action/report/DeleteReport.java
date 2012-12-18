/**
 * 
 */
package sp.action.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.ReportBlo;
import sp.dto.Report;
import sp.form.ReportForm;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class DeleteReport extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			//get form
			ReportForm formReport = (ReportForm)form;
			
			//get dto 
			Report report = formReport.getReport();
			
			//save dto
			ReportBlo.saveReport(report);
			
			return mapping.findForward(Constant.SUCCESS);
		}
}
