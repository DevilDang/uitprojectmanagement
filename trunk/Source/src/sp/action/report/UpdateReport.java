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
import sp.dto.Report;
import sp.form.ReportForm;
import sp.util.CommonUtil;
import sp.util.Constant;

/**
 * @author Thuy
 *
 */
public class UpdateReport extends Action{
	
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			//get form
			ReportForm formReport = (ReportForm)form;
			
			//get  session
			HttpSession se = request.getSession();
			String fileId = (String)se.getAttribute(Constant.REPORT_FILE_ID);
			ReportForm sortForm = (ReportForm) se.getAttribute(Constant.RECORD_SORT);
//			String mode =(String) se.getAttribute(Constant.RECORD_FLAG);
			
			// insert, thay doi file khi update
			if (fileId != null){
				formReport.setFileId(fileId);
			}
			//mode insert
			if (Constant.MODE_INSERT.equals(formReport.getMode())){
				
				//set sortForm into  formReport
				formReport.setIdProject(sortForm.getIdProject());
				formReport.setIdGroup(sortForm.getIdGroup());
				formReport.setIdReq(sortForm.getIdReq());
				formReport.setIdTask(sortForm.getIdTask());
				formReport.setIdUser(sortForm.getIdUser());
				formReport.setLevel(sortForm.getLevel());
				formReport.setCreateDate(CommonUtil.getSystemDate());
				formReport.setStatus(Constant.REPORT_NEW);
			}
			
			//get dto 
			Report report = formReport.getReport();
			
			//save dto
			ReportBlo.saveReport(report);
			
			//remove  from session
			se.removeAttribute(Constant.REPORT);
			se.removeAttribute(Constant.REPORT_FILE_ID);
			se.removeAttribute(Constant.REPORT_FILE_NAME);
//			if (Constant.MODE_INSERT.equals(mode)){
//				
//			}
//			
			return mapping.findForward(Constant.SUCCESS);
		}
}
