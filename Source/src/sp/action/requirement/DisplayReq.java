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
import sp.form.IdName;
import sp.form.RequirementForm;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class DisplayReq extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			HttpSession se = request.getSession();
			
			//get id
			String id = request.getParameter("id");
			
			//get Req
			if (id != null){
				RequirementForm reqForm = RequirementBlo.getRequirementForm(Long.parseLong(id));
				
				//set mode 
				reqForm.setMode(Constant.MODE_UPDATE);
				
//				//get group free
				List<IdName> idGroupFreeList = RequirementBlo.getGroupListFree(reqForm.getIdProject());
				
				se.setAttribute(Constant.REQ_GROUP_FREE, idGroupFreeList);
				
				//save into session, name = name of bean config into file config struts
				se.setAttribute(Constant.REQ, reqForm);
				
			}
			return mapping.findForward(Constant.SUCCESS);
		}
}
