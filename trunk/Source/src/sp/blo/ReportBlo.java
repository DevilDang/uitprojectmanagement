package sp.blo;

import java.util.ArrayList;
import java.util.List;

import sp.dao.PMF;
import sp.dao.ReportDao;
import sp.dto.Group;
import sp.dto.Project;
import sp.dto.Report;
import sp.dto.Requirement;
import sp.dto.Task;
import sp.dto.User;
import sp.form.ReportForm;
import sp.util.CommonUtil;
import sp.util.Constant;
import sp.util.JSONObject;
import sp.util.JSONObjectList;
public class ReportBlo {
	
	private static ReportDao reportDao = new ReportDao();

	public static boolean saveReport(Report report){
		return reportDao.saveReport(report);
	}
	
	public static boolean delete(Long id){
		return reportDao.deleteReport(id);
	}
	//get ReportList
	public static List<ReportForm> getListPage(String filter, int page){
		
		
		List<ReportForm> formList = new ArrayList<ReportForm>();
		List<Report> reportList = reportDao.getListReportPaging(filter.toString(), page);
		
		if (reportList != null){
			int size = reportList.size();
			for (int i = 0; i< size; i++){
				//transfer to ReportForm
				ReportForm form = new ReportForm();
				form.eidtForm(reportList.get(i));
				formList.add(form);
			}
			
		}
		
		return formList;
	}
	
	public static String getFilter(ReportForm sortForm){
		StringBuffer filter = new StringBuffer();
		
			filter.append("idProject=="+ sortForm.getIdProject());
			filter.append("&&");
			filter.append("status ==\'"+ sortForm.getStatus()+"\'");
			
		//Admin	
		if (Constant.ADMIN == sortForm.getLevel()){	
			filter.append("&&");
			filter.append("level=="+ Constant.PM );
		}
		//pm
		else if (Constant.PM == sortForm.getLevel()){
			filter.append("&&");
			filter.append("idReq=="+ sortForm.getIdReq());
			filter.append("&&");
			filter.append("level=="+ Constant.LEADER);
		}
		//leader
		else if(Constant.LEADER == sortForm.getLevel()){
			filter.append("&&");
			filter.append("idReq=="+ sortForm.getIdReq());
			filter.append(" && ");
			filter.append("idGroup=="+ sortForm.getIdGroup());
			filter.append(" && ");
			filter.append("level=="+ Constant.EMPLOYEE );
		}
		//employee
		else if(Constant.EMPLOYEE == sortForm.getLevel()){
			filter.append("&&");
			filter.append("idReq=="+ sortForm.getIdReq());
			filter.append(" && ");
			filter.append("idGroup=="+ sortForm.getIdGroup());
			filter.append(" && ");
			filter.append("idTask=="+ sortForm.getIdTask());
			filter.append(" && ");
			filter.append(" idUser== \'"+ sortForm.getIdUser() +"\'");
			filter.append(" && ");
			filter.append("level=="+ Constant.EMPLOYEE);
		}
		
		return filter.toString();
	}
	public static String getFilterMine(ReportForm sortForm){
		StringBuffer filter = new StringBuffer();
		
		filter.append("status ==\'"+ sortForm.getStatus()+"\'");
		filter.append("&&");
		filter.append("idUser ==\'"+ sortForm.getIdUser()+"\'");
		filter.append("&&");
		filter.append("level=="+ sortForm.getLevel());
		
	 if (Constant.PM == sortForm.getLevel()){
		filter.append("&&");
		filter.append("idProject=="+ sortForm.getIdProject());
	}
	//leader
	else if(Constant.LEADER == sortForm.getLevel()){
		filter.append("&&");
		filter.append("idProject=="+ sortForm.getIdProject());
		filter.append("&&");
		filter.append("idReq=="+ sortForm.getIdReq());
	}
		
		return filter.toString();
	}
	
	//get ReportListMine
	public static List<ReportForm> getListPageMine(String filter, int page){
		
		List<ReportForm> formList = new ArrayList<ReportForm>();
		List<Report> reportList = reportDao.getListReportPaging(filter.toString(), page);
		
		if (reportList != null){
			int size = reportList.size();
			for (int i = 0; i< size; i++){
				//transfer to ReportForm
				ReportForm form = new ReportForm();
				form.eidtForm(reportList.get(i));
				formList.add(form);
			}
			
		}
		
		return formList;
	}
	
	/*
	 * tinh so page dua tren so record
	 */
	public static int countReportAllBySQL(String filter) {
		
		return reportDao.countReportAllBySQL(filter);

	}
	
	/*
	 * get Form Report
	 */
	
	public static ReportForm getReportForm(Long id){
		
		//get Report
		Report report = reportDao.getReport(id);
		
		//get Form
		ReportForm form = new ReportForm();
		
		//transfer Report -> Form
		form.eidtForm(report);
		
		form.setFileName(CommonUtil.getReportName(form.getFileId()));
		
		return form;
	}
	/*
	 * get SortForm
	 */
	public static ReportForm getSortForm(Long idProject, Long idReq, Long idGroup, Long idTask, String user,String status,int level){
		
		ReportForm sortForm = new ReportForm();
			sortForm.setIdProject(idProject);
			sortForm.setIdReq(idReq);
			sortForm.setIdGroup(idGroup);
			sortForm.setIdTask(idTask);
			sortForm.setIdUser(user);
			sortForm.setStatus(status);
			sortForm.setLevel(level);
//		}
		return sortForm;
	}
	/*
	 * get danh sach idProject cua  Project
	 */
	public static List<Long> getIdProjectList(){
		
		List<Long> idProjectList = new ArrayList<Long>();
		List<Project> projectList = new ArrayList<Project>();
		
		//create filter
		String filter = Constant.DEFAULT_STATUS;
		
		//get list project
		projectList =  reportDao.getIdProjectList(filter);
		
		//check result
		if (projectList == null || projectList.size() == 0){
			return null;
		}
		
		//create list idProject
		int size = projectList.size();
		for( int i = 0; i<size; i++){
			idProjectList.add(projectList.get(0).getId());
		}
		
		return idProjectList;
	}
	
	/*
	 * get danh sach idReq cua  Project
	 */
	public static List<Long> getIdReqList(long idProject){
		
		List<Long> idReqList = new ArrayList<Long>();
		List<Requirement> reqList = new ArrayList<Requirement>();
		
		//create filter
		StringBuffer filter = new StringBuffer();
		filter.append(Constant.DEFAULT_STATUS);
		filter.append("&&");
		filter.append("idProject==" + idProject);
		
		//call method get Req List
		reqList =  reportDao.getIdRequirementList(filter.toString());
		
		//check result
		if (reqList == null || reqList.size() == 0){
			return null;
		}
		//create idReqList
		int size = reqList.size();
		for (int i = 0; i<size; i++){
			idReqList.add(reqList.get(i).getId());
		}
		
		//return value
		return idReqList;
	}
//	/*
//	 * get idReq theo idProject, idGroup
//	 */
//	public static long getIdReqList(long idProject, long idGroup){
//		
////		long idProject = getIdProject(idPM);
//		StringBuffer filter = new StringBuffer();
//		filter.append(Constant.DEFAULT_STATUS);
//		filter.append("&&");
//		filter.append("idProject==" + idProject);
//		filter.append("&&");
//		filter.append("idGroup==" + idGroup);
//		List<Long> idList = reportDao.getIdRequirementList(filter.toString());
//		if (idList != null){
//			return idList.get(0);
//		}
//		return -1;
//	}
	
	/*
	 * get danh sach report
	 */
	public static List<Report> getReportListUser(long idGroup ,String idUser , int level){
		
		StringBuffer filter = new StringBuffer();
		filter.append(Constant.DEFAULT_STATUS);
		filter.append("&&");
		filter.append("level==" + level);
		if(Constant.LEADER == level){
			filter.append("&&");
			filter.append("idGroup==" + idGroup);
		}
		else if (Constant.EMPLOYEE == level){
			filter.append("&&");
			filter.append("idUser==\'" + idUser +"\'");
		}
		
		return reportDao.getReportList(filter.toString());
		
	}
//	/*
//	 * get danh sach report
//	 */
//	public static List<Report> getReportListUser(String idUser, String status, int level){
//		
//		StringBuffer filter = new StringBuffer();
//		
//		
//	}
	
	/**
	 * get idProject from Project.class
	 * @param idPM 
	 * @return idProject
	 */
	public static Long getIdProjectByPM(String idPM){
		StringBuilder filter = new StringBuilder();
		filter.append("idPm==\'"+ idPM + "\'");
		filter.append("&&");
		filter.append(Constant.DEFAULT_STATUS);
		return reportDao.getIdProjectByPM(filter.toString());
	}
	
	public static boolean isIdProject(String idPM){
		return true;
	}
	
	/*
	 * check xem group nay da join vo du an chua
	 */
	public static Long getIdProjectByGroup(Long idGroup){
		return reportDao.getIdGroupAssign(idGroup);
	}
	/*
	 * check xem group nay da nhan requirement
	 */
	public static Long getIdReq(Long idGroup, Long idProject){
		StringBuilder filter = new StringBuilder();
		filter.append(Constant.DEFAULT_STATUS);
		filter.append(" && ");
		filter.append("idProject == " +idProject);
		
		filter.append(" && ");
		filter.append("idGroup == " + idGroup);
		
		return reportDao.isReqAssign(filter.toString());
	}
	/*
	 * get task cua employee
	 */
	public static Long getIdTask(Long idProject, Long idGroup,Long idReq, String idUser){
		StringBuilder sql = new StringBuilder();
		sql.append("idProject==" + idProject);
		sql.append("&& ");
		sql.append("idGroup==" + idGroup);
		sql.append("&& ");
		sql.append("idReq==" + idReq);
		sql.append("&& ");
		sql.append("emailEmployee==\'" + idUser +"\'");
		sql.append("&& ");
		sql.append(Constant.DEFAULT_STATUS);
		@SuppressWarnings("unchecked")
		List<Task> taskList = (List<Task>) PMF.getObjectList(Task.class, sql.toString());
		if (taskList != null){
			return taskList.get(0).getId();
		}
		return null;
	}
	
	
//	public static int checkIdProjectEmployee(String idUser, long idGroup){
//		
//		boolean isCheck = false;
//		
//		//check Group da nhan du an chua
//		isCheck = isGroupAssign(idGroup);
//		if(isCheck == false){
//			return 1; 
//		}
//		else{
//			//check User da co task chua
//			
//		}
//		
//	}
	
//	/*
//	 * xac dinh group da duoc assign vo du an chua
//	 */
//	
//	public static Long isGroupAssign(Long idGroup){
//		return reportDao.isGroupAssign(idGroup);
//	}
	
	/*
	 * xac dinh group da duoc assign requirement chua
	 */
	
	public static Long getReqAssign(Long idProject, Long idGroup){
		
		StringBuilder filter = new StringBuilder();
		filter.append(Constant.DEFAULT_STATUS);
		filter.append(" && ");
		filter.append("idProject == " +idProject);
		
		filter.append(" && ");
		filter.append("idGroup == " + idGroup);
		
		return reportDao.isReqAssign(filter.toString());
	}
	
//	/*
//	 * xac dinh user da co task chua?
//	 */
//	
//	public static boolean isUserAssignTask(String idUser, long idGroup, long idProjecr, long idReq){
//		return reportDao.isUserAssignTask(idUser, idGroup, idProjecr, idReq);
//	}
	
	/*
	 * create data
	 */
	public static void createData(){
		User user = new User();
		user.setEmail("a@gmail.com");
		user.setGroupID(1);
		user.setIdPermision(String.valueOf(Constant.EMPLOYEE));
		PMF.insertObject(user);
		
		//2
		user.setEmail("b@gmail.com");
		user.setGroupID(2);
		user.setIdPermision(String.valueOf(Constant.LEADER));
		PMF.insertObject(user);
		
		//
		user.setEmail("c@gmail.com");
		user.setGroupID(3);
		user.setIdPermision(String.valueOf(Constant.PM));
		PMF.insertObject(user);
		
		Project pro = new Project();
		pro.setIdPm("c@yahoo.com");
		
		PMF.insertObject(pro);
		
		Group group = new Group();
		group.setIdLeader("b@gmail.com");
		@SuppressWarnings("unchecked")
		List<Project> proList = (List<Project>) PMF.getObjectList(Project.class, "idPm == 'c@yahoo.com'" );
		group.setIdProject(proList.get(0).getId());
		
		PMF.insertObject(group);
		
		
		Requirement req = new Requirement();
		@SuppressWarnings("unchecked")
		List<Group> groupList = (List<Group>) PMF.getObjectList(Group.class, "idProject == " + proList.get(0).getId());
		req.setIdGroup(groupList.get(0).getId());
		req.setIdProject(proList.get(0).getId());
		PMF.insertObject(req);
		
		Task task = new Task();
		task.setIdProject(proList.get(0).getId());
		task.setIdGroup(groupList.get(0).getId());
		@SuppressWarnings("unchecked")
		List<Requirement> reqList = (List<Requirement>) PMF.getObjectList(Requirement.class, "idProject == " + proList.get(0).getId() + "&& idGroup==" + groupList.get(0).getId());
		task.setIdReq(reqList.get(0).getId());
		task.setEmailEmployee("a@gmail.com");
		PMF.insertObject(task);
//		
		@SuppressWarnings("unchecked")
		List<Group> groupList1 = (List<Group>) PMF.getObjectList(Group.class, Constant.DEFAULT_STATUS);
		groupList1.size();
		List<Requirement> reqList1 = (List<Requirement>) PMF.getObjectList(Requirement.class, Constant.DEFAULT_STATUS);
		List<Task> taskList = (List<Task>) PMF.getObjectList(Task.class, Constant.DEFAULT_STATUS);
		
		
	}
	
	/*
	 * delete list obj
	 */
	public static boolean deleteReportList(String keyList[]) {
		if (keyList.length >0){
			int size = keyList.length;
			for(int i = 0; i<size; i++){
				Long id = Long.valueOf(keyList[i]);
				reportDao.deleteReport(id);
			}
		}
		return true;
	}
	
	/**
	 * create List Json Report
	 * @param reportList
	 * @return
	 */
	public static JSONObjectList createJSONObjectList(List<ReportForm> reportList)
	{
		JSONObjectList jsonlist = new JSONObjectList();
        int length = reportList.size();
        for (int i = 0; i < length; i++) {
            JSONObject uc =createJSONObject(reportList.get(i));

            jsonlist.getListobject().add(uc);

        }
        
        return jsonlist;
	}
	
	/**
	 * create JsonObject from an ReportForm
	 * @param report
	 * @return
	 */
	public static JSONObject createJSONObject(ReportForm report)
	{
		String keys[] = {"id", "title","idUser","fileId"};
        
		JSONObject uc = new JSONObject(keys);
        uc.getObject().put(keys[0], String.valueOf(report.getId()));
        uc.getObject().put(keys[1], report.getTitle());
        uc.getObject().put(keys[2], report.getIdUser());
        uc.getObject().put(keys[3], report.getFileId());

       return uc;     
    }
}
