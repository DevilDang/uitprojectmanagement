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
import org.apache.struts.action.ActionMessages;

import sp.blo.RequirementBlo;
import sp.dto.Requirement;
import sp.form.IdName;
import sp.form.RequirementForm;
import sp.util.Constant;
import sp.util.Validation;

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
			
//			if (Constant.BLANK.equals(formReq.getMode())){
//				formReq.setMode(Constant.MODE_INSERT);
//			}
//			
			ActionMessages messages = Validation.checkDate(formReq.getMode(), formReq.getStartDate(), formReq.getEndDate());
			 
			if (messages.size() == 0){

			//get  session
			HttpSession se = request.getSession();
			RequirementForm sortForm = (RequirementForm) se.getAttribute(Constant.RECORD_SORT);
//			String mode = (String) se.getAttribute(Constant.RECORD_FLAG);
			Requirement req ;
			
			//set sortForm into  formReport
			formReq.setIdProject(sortForm.getIdProject());
			
			//mode insert
			if (formReq.getMode().equals(Constant.MODE_INSERT)){
				
				//set status = Open
				formReq.setStatus(Constant.OPEN);
				//update status of Group = assign
				RequirementBlo.updateStatusGroup(formReq.getIdGroup(), Constant.GROUP_ASSIGN_REQ);
				
			}
			//mode update
			else
			{
				//update group
				RequirementBlo.updateStatusGroup(formReq.getId(),formReq.getIdGroup());
				
				//update Group if status = close
				if (Constant.CLOSE.equals(formReq.getStatus())){
					RequirementBlo.updateStatusGroup(formReq.getIdGroup(), Constant.GROUP_FREE_REQ);
				}
				else
				{
					RequirementBlo.updateStatusGroup(formReq.getIdGroup(), Constant.GROUP_ASSIGN_REQ);
				}
				
			}
			
			req = formReq.getRequirement();
			//save dto
			RequirementBlo.saveRequirement(req);
			
			//get group free
			List<IdName> idGroupFreeList = RequirementBlo.getGroupListFree(req.getIdProject());
			
			se.setAttribute(Constant.REQ_GROUP_FREE, idGroupFreeList);
			
			//remove  REQ
			formReq.clear();
			}
			else
			{
				// storing messages 
		        saveMessages(request.getSession(), messages);
			}
			return mapping.findForward(Constant.SUCCESS);
		}
}
