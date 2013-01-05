package sp.action.requirement;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.OrganizationBlo;
import sp.blo.ReportBlo;
import sp.blo.RequirementBlo;
import sp.dto.Report;
import sp.form.ReportForm;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class DeleteRequirement extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			

			String[] req_id_array = request.getParameterValues("check");
			
			//delete
			RequirementBlo.deleteReqList(req_id_array);
			
			//forward page result
			return mapping.findForward(Constant.SUCCESS);
			
		}
}

