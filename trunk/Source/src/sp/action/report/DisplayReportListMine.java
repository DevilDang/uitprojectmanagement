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
//		
		//get session
		HttpSession se = request.getSession();

		/*// get user from session
		AccountForm user = (AccountForm) se.getAttribute("user");
		
		//start temp
		ReportBlo.createData();
		user = new AccountForm();
		user.setEmail("a@gmail.com");
		user.setGroupCode(2);
		user.setPermission("3");
		//end temp
		
		// get permision
		int permission = Integer.parseInt(user.getPermission());
		
		//initial sortForm
		Long idProject = null;
		Long idReq = null;
		Long idGroup = user.getGroupCode();
		String status = Constant.BLANK;
		String idUser = user.getEmail();
		int level = permission;
		
		// value into session
		List<Long> idProjectList = new ArrayList<Long>();
		List<Long> idReqList = new ArrayList<Long>();
		List<Long> idGroupList = new ArrayList<Long>();
		
		boolean sort = false;
		
		// ADMIN
		if (Constant.ADMIN == permission) { // get report cua admin  

				status = Constant.REPORT_NEW;
				sort = true;

		} else if (Constant.PM == permission) { // get report cua project hien tai
												
			// get idProject
			idProject = ReportBlo.getIdProjectByPM(idUser);
			
			//pm had got project
			if (idProject != null){
				
					status = Constant.REPORT_NEW;
					//set session
					idProjectList.add(idProject);
					
					sort = true;
			}

		} else if (Constant.LEADER == permission) {

			idProject = ReportBlo.getIdProjectByGroup(idGroup);

			if (idProject != null) {
				idReq = ReportBlo.getIdReq(idGroup, idProject);
				if (idReq != null) {

					// set value into SortForm
					status = Constant.REPORT_NEW;

					// set into session
					idProjectList.add(idProject);
					idReqList.add(idReq);
					idGroupList.add(idGroup);
					// tien hanh sort
					sort = true;
				}

			}

		}*/
		//get sortForm from session
		ReportForm sortForm = (ReportForm) se.getAttribute(Constant.RECORD_SORT);
		if (sortForm != null) {
			sortForm.setStatus(Constant.REPORT_NEW);
			String filter = ReportBlo.getFilterMine(sortForm);
			
			// call method to get Page Report
			List<ReportForm> reportList = ReportBlo.getListPageMine(filter,
					1);
			//
			// get total number page
			int total = ReportBlo.countReportAllBySQL(filter);

			// get List page number
			List<String> pageList = CommonUtil.createPageList(total);

			// save session
			se.setAttribute(Constant.RECORD_LIST, reportList);
			se.setAttribute(Constant.RECORD_PAGE_LIST, pageList);
			se.setAttribute(Constant.RECORD_PAGE_NUMBER,"1");
			se.setAttribute(Constant.RECORD_SORT, sortForm); // su dung khi // update/insert
//			
			//set mode = insert												
			se.setAttribute(Constant.RECORD_FLAG, Constant.MODE_INSERT);
//			
//			// sort
//			se.setAttribute(Constant.IDPROJECTLIST, idProjectList);
//			se.setAttribute(Constant.IDREQLIST, idReqList);
//			se.setAttribute(Constant.IDGROUPLIST, idGroupList);
//			se.setAttribute(Constant.PERMISSION, permission);

			return mapping.findForward(Constant.SUCCESS);
		} else {
			return mapping.findForward(Constant.FAILURE);
		}
	}
}
