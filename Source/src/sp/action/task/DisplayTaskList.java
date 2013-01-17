/**
 * 
 */
package sp.action.task;

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
import sp.blo.TaskBlo;
import sp.dao.PMF;
import sp.dto.User;
import sp.form.AccountForm;
import sp.form.TaskForm;
import sp.util.CommonUtil;
import sp.util.Constant;

/**
 * @author T
 * 
 */
public class DisplayTaskList extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// get session
		HttpSession se = request.getSession();

		// get user from session
		AccountForm user = (AccountForm) se.getAttribute("user");
		
		User u = (User)PMF.getObject(User.class, "a@yahoo.com");
		user = new AccountForm(u);
		
//		// get permision
//		
//		User user1 = new User();
//		user1.setEmail("t@yahoo.com");
//		user1.setIdPermision("3");
//		user1.setGroupID(1);
//		user1.setStatusTask(Constant.USER_FREE_TASK);
//		PMF.insertObject(user1);
		
		int permission = Integer.parseInt(user.getPermission());

		// initial sortForm
		int level = 0;
		Long idProject = null;
		Long idReq = null;
		Long idGroup = 0L;
		// String status = Constant.BLANK;
		String idUser = user.getEmail();

		// value into session
		List<Long> idProjectList = new ArrayList<Long>();
		List<Long> idReqList = new ArrayList<Long>();
		List<Long> idGroupList = new ArrayList<Long>();
		boolean sort = false;

		// ADMIN
		if (Constant.ADMIN == permission) { // thao tac khi co project

			// get idProjectList
			idProjectList = ReportBlo.getIdProjectList();

			if (idProjectList != null) {

				// set SortForm
				idProject = idProjectList.get(0);

				level = Constant.ADMIN;

				sort = true;
			}

		} else if (Constant.PM == permission) { // chi thao tac khi da nhan
												// project, co requirement

			// get idProject
			idProject = ReportBlo.getIdProjectByPM(idUser);

			// pm had got project
			if (idProject != null) {

				// set sortForm
				// status = Constant.REQ_NEW;
				level = Constant.PM;

				// set session
				idProjectList.add(idProject);

				sort = true;
			}

		} else if ((Constant.LEADER == permission)||(Constant.EMPLOYEE== permission)) {

			idGroup = user.getGroupCode();
			idProject = ReportBlo.getIdProjectByGroup(idGroup);

			if (idProject != null) {
				idReq = ReportBlo.getIdReq(idGroup, idProject);
				if (idReq != null) {

					level = Constant.LEADER;

					// set into session
					idProjectList.add(idProject);
					idReqList.add(idReq);
					idGroupList.add(idGroup);

					// tien hanh sort
					sort = true;
				}

			}
		}
		if (sort == true) {
			// create sortForm
			TaskForm sortForm = TaskBlo.getSortForm(idProject,
					idReq, idGroup,idUser, level);

			sortForm.setStatus(Constant.OPEN);
			sortForm.setKind(Constant.TASK_TEST);
			// get filter
			String filter = TaskBlo.getFilterSQL(sortForm);
			
			// call method to get Page Report
			List<TaskForm> reportList = TaskBlo.getTaskListByFilter(filter, 1);
			//
			// get total number page
			int total = TaskBlo.getNumberByFilter(filter);

			// get List page number
			List<String> pageList = CommonUtil.createPageList(total);

			// get group free
			List<String> idTaskList = TaskBlo.getUserFreeTask(idGroup);
			// save session
			se.setAttribute(Constant.TASK_USER_FREE, idTaskList);
			se.setAttribute(Constant.RECORD_LIST, reportList);
			se.setAttribute(Constant.RECORD_PAGE_LIST, pageList);
			se.setAttribute(Constant.RECORD_PAGE_NUMBER, "1");
			se.setAttribute(Constant.RECORD_SORT, sortForm); // su dung khi //
																// update/insert
			TaskForm task = new TaskForm();
			task.setMode(Constant.MODE_INSERT);
			// set mode = insert
			se.setAttribute(Constant.TASK, task);

			// sort
			se.setAttribute(Constant.IDPROJECTLIST, idProjectList);
			se.setAttribute(Constant.IDREQLIST, idReqList);
			se.setAttribute(Constant.IDGROUPLIST, idGroupList);

			return mapping.findForward(Constant.SUCCESS);
		}

		return mapping.findForward(Constant.FAILURE);
	}
}