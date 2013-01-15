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

import sp.form.ReportForm;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class ChangeMode extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			HttpSession se = request.getSession();
			
			//remove Report Bean out of session
			se.removeAttribute(Constant.REPORT_FILE_ID);
			se.removeAttribute(Constant.REPORT_FILE_NAME);
			
			//get req from session
			ReportForm reportForm = (ReportForm) se.getAttribute(Constant.REPORT);
			
			//clear
			reportForm.clear();
			
			return mapping.findForward(Constant.SUCCESS);
		}
}
