package sp.blo;

import java.util.ArrayList;
import java.util.List;

import sp.dao.PMF;
import sp.dao.ReportDao;
import sp.dao.RequirementDao;
import sp.dto.Group;
import sp.dto.Report;
import sp.dto.Requirement;
import sp.form.ReportForm;
import sp.form.RequirementForm;
import sp.util.CommonUtil;
import sp.util.Constant;

public class RequirementBlo {
	
	private static RequirementDao reqDao = new RequirementDao();
	
	public static boolean saveRequirement(Requirement req){
		return reqDao.saveRequirement(req);
	}
	
//	/**
//	 * get ReqList of project
//	 */
//	@SuppressWarnings("unchecked")
//	public List<RequirementForm> getReqListByFilter(Long idProject){
//
//		return (List<Requirement>)PMF.getObjectListPaging(Requirement.class, filter, page);
//	}
	
//	/**
//	 * get number of record 
//	 */
//	public int getNumberByFilter(String filter){
//		return PMF.countNumberAll(Requirement.class, filter);
//	}
	
	/**
	 * get file 
	 */
	public static String getFilterReq(RequirementForm sortForm){
		
		StringBuffer filter = new StringBuffer();
			filter.append("idProject=="+ sortForm.getIdProject());
//			filter.append("&&");
//			filter.append("status ==\'"+ sortForm.getStatus()+"\'");
			
		 if(Constant.LEADER == sortForm.getLevel()){
			filter.append("&&");
			filter.append("id=="+ sortForm.getId());
			filter.append(" && ");
			filter.append("idGroup=="+ sortForm.getIdGroup());
		}
		
		return filter.toString();
	}
	
	/*
	 * tinh so luong record
	 */
	public static int getNumberByFilter(String filter) {
		
		return reqDao.getNumberByFilter(filter);

	}
	
	/*
	 * get danh sach req cua project ( goi ham ReportBlo.getIdReqList())
	 */
	
	/*
	 * get danh sach nhom da duoc assign vo du an nhung chua duoc set req
	 */
	
	/*
	 * get tat ca danh sach nhom cua du an
	 */
	public static List<Group> getGroupListAll(Long idProject){

		StringBuffer filter = new StringBuffer();
		filter.append("idProject=="+ idProject);
		filter.append("&&");
		filter.append(Constant.DEFAULT_STATUS);
		
		return reqDao.getGroupOfProject(filter.toString());
	}
	
	/*
	 * get danh sach nhom cua du an da duoc set Req
	 */
	public static List<Requirement> getGroupListOfReq(Long idProject){

		StringBuffer filter = new StringBuffer();
		filter.append("idProject=="+ idProject);
		filter.append("&&");
		filter.append(Constant.DEFAULT_STATUS);
		
		return reqDao.getGroupOfReq(filter.toString());
	}
	/*
	 * get danh sach group ma chua co Req
	 */
	public static List<Long> getGroupListFree(Long idProject){
		
		List<Long> idGroupList = new ArrayList<Long>();
		
		List<Group> groupList = getGroupListAll(idProject);
		if (groupList == null || groupList.size()==0){
			return null;
		}
		
		List<Requirement> reqList = getGroupListOfReq(idProject);
		if (reqList != null && reqList.size() >0){
			int groupLen = groupList.size();
			int reqLen = reqList.size();
			int i, j;
			for ( i = 0; i< groupLen; i++){
				for( j = 0; j< reqLen; j++){
					if( groupList.get(i).getId() == reqList.get(j).getIdGroup()){
						break;
					}
				}
				if( j == reqLen){
					idGroupList.add(groupList.get(i).getId());
				}
				
			}
		}
		return idGroupList;
	}
	
public static RequirementForm getSortForm(Long idProject, Long idReq, Long idGroup,int level){
		
			RequirementForm sortForm = new RequirementForm();
			sortForm.setIdProject(idProject);
			sortForm.setId(idReq);
			sortForm.setIdGroup(idGroup);
//			sortForm.setStatus(status);
			sortForm.setLevel(level);
//		}
		return sortForm;
	}

//get getListPage
public static List<RequirementForm> getListPage(String filter, int page){
	
	
	List<RequirementForm> formList = new ArrayList<RequirementForm>();
	List<Requirement> reqList = reqDao.getReqListByFilter(filter, page);
	
	if (reqList != null && reqList.size() >0){
		int size = reqList.size();
		for (int i = 0; i< size; i++){
			//transfer to ReportForm
			RequirementForm form = new RequirementForm();
			form.editForm(reqList.get(i));
			formList.add(form);
		}
		
	}
	return formList;
}
public static RequirementForm getRequirementForm(Long id){
	
	//get Req
	Requirement req = reqDao.getRequirement(id);
	
	//get Form
	RequirementForm form = new RequirementForm();
	
	//transfer Report -> Form
	form.editForm(req);
	
	return form;
}

}
