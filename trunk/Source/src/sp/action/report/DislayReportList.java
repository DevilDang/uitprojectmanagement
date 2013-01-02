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
import sp.blo.UserBlo;
import sp.form.AccountForm;
import sp.form.ReportForm;
import sp.util.CommonUtil;
import sp.util.Constant;
import sp.util.JSONObjectList;

/**
 * @author T
 * 
 */
public class DislayReportList extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// get session
		HttpSession se = request.getSession();

		// get user from session
		AccountForm user = (AccountForm) se.getAttribute("user");

		// start temp
		user = new AccountForm();
		user.setEmail("a@gmail.com");
		user.setGroupCode(2);
		user.setPermission("4");
		// end temp

		// get permision
		int permission = Integer.parseInt(user.getPermission());

		// initial sortForm
		int level = 0;
		Long idProject = null;
		Long idReq = null;
		Long idGroup = user.getGroupCode();
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
		if (Constant.ADMIN == permission) { // thao tac khi co project

			// get idProjectList
			idProjectList = ReportBlo.getIdProjectList();

			if (idProjectList != null) {

				// set SortForm
				idProject = idProjectList.get(0);
				status = Constant.REPORT_NEW;
				level = Constant.ADMIN;

				sort = true;
			}

		} else if (Constant.PM == permission) { // chi thao tac khi da nhan
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
					level = Constant.PM;

					// set session
					idProjectList.add(idProject);

					sort = true;
				}
			}

		} else if (Constant.LEADER == permission) {

			idProject = ReportBlo.getIdProjectByGroup(idGroup);

			if (idProject != null) {
				idReq = ReportBlo.getIdReq(idGroup, idProject);
				if (idReq != null) {

					// set value into SortForm
					status = Constant.REPORT_NEW;
					level = Constant.LEADER;

					// set into session
					idProjectList.add(idProject);
					idReqList.add(idReq);
					idGroupList.add(idGroup);
					// tien hanh sort
					sort = true;
				}

			}

		} else if (Constant.EMPLOYEE == permission) {

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
							level = Constant.EMPLOYEE;

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

			// set mode = insert
			se.setAttribute(Constant.RECORD_FLAG, Constant.MODE_INSERT);

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
