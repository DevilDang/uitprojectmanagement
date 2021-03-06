package sp.dao;

import java.util.List;

import sp.dto.Group;
import sp.dto.Project;
import sp.dto.Report;
import sp.dto.Requirement;
import sp.dto.Task;
import sp.form.IdName;
import sp.util.Constant;

public class ReportDao {
	
	//insert or update
	public boolean saveReport(Report report){
		return PMF.insertObject(report);
	}

	//delete a report
	public boolean deleteReport(Long id){
		return PMF.deleteObject(Report.class, id);
	}
	
	//getReport
	public Report getReport(Long id){
		return (Report)PMF.getObject(Report.class, id);
	}
	
	//getList page
	@SuppressWarnings("unchecked")
	public List<Report> getListReportPaging(String filter, int page){
		return (List<Report>)PMF.getObjectListPaging(Report.class, filter, page);
	}
	
	/*
	 * count number all 
	 */
	public int countReportAllBySQL(String filter) {
		return PMF.countNumberAll(Report.class,filter);

	}
	
	/*
	 * get danh sach idProject trong entity Project
	 */
	
	@SuppressWarnings("unchecked")
	public List<Project> getIdProjectList(String filter){
		
		return (List<Project>) PMF.getObjectList(Project.class, filter);
	}
	
	/*
	 * get danh sach idReq trong Requirement
	 */
	
	@SuppressWarnings("unchecked")
	public List<Requirement> getIdRequirementList(String filter){
		
		return (List<Requirement>) PMF.getObjectList(Requirement.class, filter);
	}
	
	/*
	 * get danh sach Report
	 */
	@SuppressWarnings("unchecked")
	public List<Report> getReportList(String filter){
		
		return (List<Report>)PMF.getObjectList(Report.class, filter);
	}
	
	/*
	 * check PM da nhan du an chua?
	 * 
	 */
	public IdName getIdProjectByPM(String filter){
		
		IdName result = new IdName();
		@SuppressWarnings("unchecked")
		List<Project> projectList = (List<Project>) PMF.getObjectList(Project.class, filter.toString());
//		List<Project> projectList = (List<Project>) PMF.getObjectList(Project.class, 1);
		//check result
		if (projectList == null || projectList.size() == 0){
			return null;
		}
		result.setId(projectList.get(0).getIDproject());
		result.setName(projectList.get(0).getProjectname());
		
		return result;
	}
	
	/*
	 * check Group da duoc assign vo du an chua: update report.class -> Group.class
	 * tra ve la idProject
	 */
	public IdName getIdGroupAssign(Long idGroup){
		
		Group group = (Group)PMF.getObject(Group.class, idGroup);
		IdName re = new IdName();
		
		//check status
		if (group == null ){
			return null;
		}
		else{
			if(group.getIdProject() == null || ("close").equals(group.getStatus()) ){
				return null;
			}
		}
		//set value
		re.setId(group.getIdProject());
		//return
		return re;
	}
	
//	/**
//	 * get name of project
//	 * @param idProject
//	 * @return
//	 */
//	public String getProjectName(Long idProject){
//		
//		Project pro = (Project)PMF.getObject(Project.class, idProject);
//		
//		return pro.getProjectname();
//	}
//	
//	/**
//	 * get name of group
//	 * @param idProject
//	 * @return
//	 */
//	public String getGroupName(Long idGroup){
//		
//		Group group = (Group)PMF.getObject(Group.class, idGroup);
//		
//		return group.getGroupname();
//	}
	
	
	/*
	 * check Group da duoc assign Req : Report.class -> Requirement
	 * return : idRequirement
	 */
	public IdName isReqAssign(String filter){
		IdName re = new IdName();
		@SuppressWarnings("unchecked")
		List<Requirement> idList = (List<Requirement>) PMF.getObjectList(Requirement.class, filter);
		if (idList == null || idList.size() == 0){
			return null;
		}
		re.setId(idList.get(0).getId());
		re.setName(idList.get(0).getNameReq());
		return re;
	}
	
	
	
	/*
	 * check user da duoc assign vo task chua : Report.class -> Task.class
	 */
	public boolean isUserAssignTask(String idUser, long idGroup, long idProject, long idReq){
		
		StringBuilder sql = new StringBuilder();
		sql.append("idProject==" + idProject);
		sql.append("&& ");
		sql.append("idReq==" + idReq);
		sql.append("&& ");
		sql.append("idGroup==" + idGroup);
		sql.append("&& ");
		sql.append("emailEmployee==\'" + idUser +"\'");
		sql.append("&& ");
		sql.append(Constant.DEFAULT_STATUS);
		@SuppressWarnings("unchecked")
		List<Task> taskList = (List<Task>) PMF.getObjectList(Task.class, sql.toString());
		if (taskList == null){
			return false;
		}
		return true;
	}
	/*
	 * getTaskList for User
	 */
	public List<Long> getIdTaskList(String filter){
		return (List<Long>)PMF.getValueList(Task.class, "idTask", filter);
	}
	
}
