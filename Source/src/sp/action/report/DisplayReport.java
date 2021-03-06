/**
 * 
 */
package sp.action.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.ReportBlo;
import sp.form.ReportForm;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class DisplayReport extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			HttpSession se = request.getSession();
			
			//get id
			String id = request.getParameter("id");
			
			//get Report
			if (id != null){
				ReportForm reportDisplay = ReportBlo.getReportForm(Long.parseLong(id));
				
				reportDisplay.setMode(Constant.MODE_UPDATE);
				
				//save into session, name = name of bean config into file config struts
				se.setAttribute(Constant.REPORT, reportDisplay);
				
//				//remove REPORT_FILE
				se.removeAttribute(Constant.REPORT_FILE_ID);
				se.removeAttribute(Constant.REPORT_FILE_NAME);
			}
			return mapping.findForward(Constant.SUCCESS);
		}
}
