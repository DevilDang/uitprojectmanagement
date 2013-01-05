/**
 * 
 */
package sp.action.requirement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.RequirementBlo;
import sp.dto.Requirement;
import sp.form.RequirementForm;
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
			String mode = (String) se.getAttribute(Constant.RECORD_FLAG);
			Requirement req ;
			
			//mode insert
			if (Constant.MODE_INSERT.equals(mode)){
				
				//set sortForm into  formReport
				formReq.setIdProject(sortForm.getIdProject());
				
				//update status of Group = assign
				RequirementBlo.updateStatusGroup(formReq.getIdGroup(), Constant.GROUP_ASSIGN_REQ);
				req = formReq.getRequirement();
			}
			else
			{
				//if close Req
				if (Constant.CLOSE.equals(formReq.getStatus())){
					req = RequirementBlo.getRequirement(formReq.getId());
					req.setStatus(Constant.CLOSE);
				}
				else
				{
					RequirementBlo.updateStatusGroup(formReq.getId(),formReq.getIdGroup());
					req = formReq.getRequirement();
				}
			}
			
			//save dto
			RequirementBlo.saveRequirement(req);
			
			//get group free
			List<Long> idGroupFreeList = RequirementBlo.getGroupListFree(req.getIdProject());
			
			se.setAttribute(Constant.REQ_GROUP_FREE, idGroupFreeList);
			
			//remove  from session
			se.removeAttribute(Constant.REQ);
				
			
			return mapping.findForward(Constant.SUCCESS);
		}
}
