/**
 * 
 */
package sp.action.requirement;

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
import sp.blo.RequirementBlo;
import sp.blo.UserBlo;
import sp.dao.PMF;
import sp.dto.Group;
import sp.dto.Project;
import sp.dto.Requirement;
import sp.dto.User;
import sp.form.AccountForm;
import sp.form.RequirementForm;
import sp.form.TaskForm;
import sp.util.CommonUtil;
import sp.util.Constant;

/**
 * @author T
 * 
 */
public class DisplayReqList extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// get session
		HttpSession se = request.getSession();

		// get user from session
		AccountForm user = (AccountForm) se.getAttribute("user");
		user = new AccountForm();
		User u = (User)PMF.getObject(User.class, "b@yahoo.com");
	
		user.editForm(u);

		// get permision
		int permission = Integer.parseInt(user.getPermission());

		// initial sortForm
		int level = 0;
		Long idProject = null;
		Long idReq = null;
		Long idGroup = 0L;
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
//					status = Constant.REQ_NEW;
					level = Constant.PM;

					// set session
					idProjectList.add(idProject);

					sort = true;
				}

		} else if (Constant.LEADER == permission) {
			
			idGroup = user.getGroupCode();
			idProject = ReportBlo.getIdProjectByGroup(idGroup);

			if (idProject != null) {
				idReq = ReportBlo.getIdReq(idGroup, idProject);
				if (idReq != null) {

					// set value into SortForm
//					status = Constant.REQ_NEW;
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
			RequirementForm sortForm = RequirementBlo.getSortForm(idProject, idReq,
					idGroup, level);
			
			sortForm.setStatus(Constant.OPEN);
			
			// get filter
			String filter = RequirementBlo.getFilterReq(sortForm);

			// call method to get Page Report
			List<RequirementForm> reportList = RequirementBlo.getListPage(filter, 1);
//
			// get total number page
			int total = RequirementBlo.getNumberByFilter(filter);

			// get List page number
			List<String> pageList = CommonUtil.createPageList(total);

			//get group free
			List<Long> idGroupFreeList = RequirementBlo.getGroupListFree(idProject);
			
			// save session
			se.setAttribute(Constant.REQ_GROUP_FREE, idGroupFreeList);
			se.setAttribute(Constant.RECORD_LIST, reportList);
			se.setAttribute(Constant.RECORD_PAGE_LIST, pageList);
			se.setAttribute(Constant.RECORD_PAGE_NUMBER, "1");
			se.setAttribute(Constant.RECORD_SORT, sortForm); // su dung khi //
																// update/insert

			RequirementForm req = new RequirementForm();
			req.setMode(Constant.MODE_INSERT);
			
			// set mode = insert
			se.setAttribute(Constant.REQ, req);

			// sort
			se.setAttribute(Constant.IDPROJECTLIST, idProjectList);
			se.setAttribute(Constant.IDREQLIST, idReqList);
			se.setAttribute(Constant.IDGROUPLIST, idGroupList);

			return mapping.findForward(Constant.SUCCESS);
		}
		
		return mapping.findForward(Constant.FAILURE);
}
}