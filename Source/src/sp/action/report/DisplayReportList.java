/**
 * 
 */
package sp.action.report;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sp.blo.ReportBlo;
import sp.dao.PMF;
import sp.dto.User;
import sp.form.ReportForm;
import sp.util.CommonUtil;
import sp.util.Constant;

/**
 * @author T
 * 
 */
public class DisplayReportList extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get session
		HttpSession se = request.getSession();

		// get user from session
		String userId = (String) se.getAttribute(Constant.User_Login);
		User user = (User) PMF.getObject(User.class, userId);
	
		// get permision
		String permission = user.getIdPermision();

		// initial sortForm
		int level = 0;
		Long idProject = null;
		Long idReq = null;
		Long idGroup  = 0L;
		Long idTask = null;
		String status = Constant.BLANK;
		String idUser = user.getEmail();

		// value into session
		List<Long> idProjectList = new ArrayList<Long>();
		List<Long> idReqList = new ArrayList<Long>();
		List<Long> idGroupList = new ArrayList<Long>();
		List<Long> idTaskList = new ArrayList<Long>();
		boolean sort = false;

		
		// ADMIN
		if (User.ADMIN.equals(permission)) { // thao tac khi co project

			// get idProjectList
			idProjectList = ReportBlo.getIdProjectList();

			if (idProjectList != null) {

				// set SortForm
				idProject = idProjectList.get(0);
				status = Constant.REPORT_NEW;
				level = Constant.ADMIN_INT;

				sort = true;
			}

		} else if (User.PROJECT_MANAGER.equals(permission)) { // chi thao tac khi da nhan
												// project, co requirement

			// get idProject
			idProject = ReportBlo.getIdProjectByPM(idUser);

			// pm had got project
			if (idProject != null) {

				// get idReqList
				idReqList = ReportBlo.getIdReqList(idProject);

				// check project had requirements

				if (idReqList != null && idReqList.size() != 0) {

					// set sortForm
					idReq = idReqList.get(0);
					status = Constant.REPORT_NEW;
					level = Constant.PM_INT;

					// set session
					idProjectList.add(idProject);

					sort = true;
				}
			}

		} else if (User.LEADER.equals(permission)) {
			
			idGroup = user.getGroupID();

			idProject = ReportBlo.getIdProjectByGroup(idGroup);

			if (idProject != null) {
				idReq = ReportBlo.getIdReq(idGroup, idProject);
				if (idReq != null) {

					// set value into SortForm
					status = Constant.REPORT_NEW;
					level = Constant.LEADER_INT;

					// set into session
					idProjectList.add(idProject);
					idReqList.add(idReq);
					idGroupList.add(idGroup);
					// tien hanh sort
					sort = true;
				}

			}

		} else if (User.EMPLOYER.equals(permission)) {

			//get idGroup
			
			idGroup = user.getGroupID();
			
			// check member da duoc gan vo nhom chua
			
			if (idGroup != 0) {

				// get idProject
				idProject = ReportBlo.getIdProjectByGroup(idGroup);
				if (idProject != null) {

					// get idReq
					idReq = ReportBlo.getIdReq(idGroup, idProject);

					if (idReq != null) {

						// get idTask
						idTask = ReportBlo.getIdTask(idProject, idGroup, idReq,
								idUser);

						if (idTask != null) {

							// set into SortFrom
							status = Constant.REPORT_NEW;
							level = Constant.EMPLOYEE_INT;

							// set into session
							idProjectList.add(idProject);
							idReqList.add(idReq);
							idGroupList.add(idGroup);
							idTaskList.add(idTask);

							// tien hanh sort
							sort = true;
						}

					}
				}
			}
		}
		if (sort == true) {
			// create sortForm
			ReportForm sortForm = ReportBlo.getSortForm(idProject, idReq,
					idGroup, idTask, idUser, status, level);

			// get filter
			String filter = ReportBlo.getFilter(sortForm);

			// call method to get Page Report
			List<ReportForm> reportList = ReportBlo.getListPage(filter, 1);

			// get total number page
			int total = ReportBlo.countReportAllBySQL(filter);

			// get List page number
			List<String> pageList = CommonUtil.createPageList(total);

			// save session
			se.setAttribute(Constant.RECORD_LIST, reportList);
			se.setAttribute(Constant.RECORD_PAGE_LIST, pageList);
			se.setAttribute(Constant.RECORD_PAGE_NUMBER, "1");
			se.setAttribute(Constant.RECORD_SORT, sortForm); // su dung khi //
																// update/insert

			ReportForm reportForm = new ReportForm();
			reportForm.setMode(Constant.MODE_INSERT);
			
			//save in session
			se.setAttribute(Constant.REPORT, reportForm);
			
			// sort
			se.setAttribute(Constant.IDPROJECTLIST, idProjectList);
			se.setAttribute(Constant.IDREQLIST, idReqList);
			se.setAttribute(Constant.IDGROUPLIST, idGroupList);
			se.setAttribute(Constant.IDTASKLIST, idTaskList);

			return mapping.findForward(Constant.SUCCESS);
		} else {
			return mapping.findForward(Constant.FAILURE);
		}
	}
}
