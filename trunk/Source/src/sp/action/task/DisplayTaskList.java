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

import sp.blo.CommonBlo;
import sp.blo.ReportBlo;
import sp.blo.TaskBlo;
import sp.dao.PMF;
import sp.dto.User;
import sp.form.IdName;
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
		String userId = (String) se.getAttribute(Constant.User_Login);
		
		User user = (User) PMF.getObject(User.class, userId);
	
		// get permision
		String permission = user.getIdPermision();

		// initial sortForm
		int level = 0;
		IdName idProject = new IdName();
		IdName idReq = new IdName();
		IdName idGroup = new IdName();
		// String status = Constant.BLANK;
		String idUser = user.getEmail();

		// value into session
		List<IdName> idProjectList = new ArrayList<IdName>();
		List<IdName> idReqList = new ArrayList<IdName>();
		List<IdName> idGroupList = new ArrayList<IdName>();
		boolean sort = false;
		Long temp;
		// ADMIN
		if (User.ADMIN.equals(permission)) { // thao tac khi co project

			// get idProjectList
			idProjectList = ReportBlo.getIdProjectList();

			if (idProjectList != null) {

				// set SortForm
				idProject = idProjectList.get(0);

				level = Constant.ADMIN_INT;

				sort = true;
			}

		} else if (User.PROJECT_MANAGER.equals(permission)){ // chi thao tac khi da nhan
												// project, co requirement

			// get idProject
			idProject = ReportBlo.getIdProjectByPM(idUser);

			// pm had got project
			if (idProject != null) {

				// set sortForm
				// status = Constant.REQ_NEW;
				level = Constant.PM_INT;

				// set session
				idProjectList.add(idProject);

				sort = true;
			}

		} else if ((User.LEADER.equals(permission))||(User.EMPLOYER.equals(permission))) {

			temp = user.getGroupID();
			idProject = ReportBlo.getProjectOfGroup(temp);

			if (idProject != null) {
				idReq = ReportBlo.getIdReq(temp, idProject.getId());
				if (idReq != null) {

					level = Constant.LEADER_INT;

					//update idName
					idGroup.setId(temp);
					idGroup.setName(CommonBlo.getGroupName(temp));
					idProject.setName(CommonBlo.getProjectName(idProject.getId()));
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
			TaskForm sortForm = TaskBlo.getSortForm(idProject.getId(),
					idReq.getId(), idGroup.getId(),idUser, level);

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
			List<String> idTaskList = TaskBlo.getUserFreeTask(idGroup.getId());
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