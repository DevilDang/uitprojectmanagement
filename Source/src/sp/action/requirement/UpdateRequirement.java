/**
 * 
 */
package sp.action.requirement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.ReportBlo;
import sp.blo.RequirementBlo;
import sp.dto.Report;
import sp.dto.Requirement;
import sp.form.ReportForm;
import sp.form.RequirementForm;
import sp.util.CommonUtil;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class UpdateRequirement extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			//get form
			RequirementForm formReq = (RequirementForm)form;
			
			//get  session
			HttpSession se = request.getSession();
			RequirementForm sortForm = (RequirementForm) se.getAttribute(Constant.RECORD_SORT);
			
			String mode =(String) se.getAttribute(Constant.RECORD_FLAG);
			
				//set sortForm into  formReport
			formReq.setIdProject(sortForm.getIdProject());
			formReq.setIdGroup(sortForm.getIdGroup());
				
			
			//get dto 
			Requirement req = formReq.getRequirement();
			
			//save dto
			RequirementBlo.saveRequirement(req);
			
			//remove  from session
			se.removeAttribute(Constant.REQ);
				
			
			return mapping.findForward(Constant.SUCCESS);
		}
}
