package sp.blo;

import java.util.ArrayList;
import java.util.List;

import sp.dao.RequirementDao;
import sp.dto.Group;
import sp.dto.Requirement;
import sp.form.IdName;
import sp.form.RequirementForm;
import sp.util.CommonUtil;
import sp.util.Constant;
import sp.util.JSONObject;
import sp.util.JSONObjectList;

public class RequirementBlo {
	
	private static RequirementDao reqDao = new RequirementDao();
	
	public static boolean saveRequirement(Requirement req){
		return reqDao.saveRequirement(req);
	}
	
	public static boolean updateStatusGroup(Long id, Long idGroup){
		
		//update status of old group = freeReq
		Requirement req = reqDao.getRequirement(id);
		Long idOldGroup = req.getIdGroup();
		boolean flag  = false;
		
		//change idGroup
		if (!idOldGroup.equals(idGroup)){
			flag = reqDao.updateStatusGroup(idOldGroup, Constant.GROUP_FREE_REQ);
			
			//success
			if (flag){
				
				//update status of new group = assignReq
				flag =	reqDao.updateStatusGroup(idGroup, Constant.GROUP_ASSIGN_REQ);
			}
		}
		
		return flag;
}
public static boolean updateStatusGroup(Long idGroup, String status){
		
			return reqDao.updateStatusGroup(idGroup,status);
}

public static boolean updateStatusGroupReq(Long id, String status){
	
	Requirement req = reqDao.getRequirement(id);
	return reqDao.updateStatusGroup(req.getIdGroup(), status);
}

	/**
	 * get file 
	 */
	public static String getFilterReq(RequirementForm sortForm){
		
		StringBuffer filter = new StringBuffer();
			filter.append("idProject=="+ sortForm.getIdProject());
			filter.append("&&");
			filter.append("status ==\'"+ sortForm.getStatus()+"\'");
			
		 if(Constant.LEADER_INT == sortForm.getLevel()){
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
	public static List<IdName> getGroupListFree(Long idProject){
		
		StringBuffer filter = new StringBuffer();
		filter.append("idProject=="+ idProject);
		filter.append("&&");
		filter.append("status==\'"+Constant.GROUP_FREE_REQ +"\'");
		List<Group> groupList = reqDao.getGroupOfProject(filter.toString());
		List<IdName> reList = new ArrayList<IdName>();
		if (groupList != null & groupList.size() >0){
			int size = groupList.size();
			for( int i = 0; i<size; i++){
				IdName idName = new IdName();
				idName.setId(groupList.get(i).getIDgroup());
				idName.setName(groupList.get(i).getGroupname());
				reList.add(idName);
			}
		}
		
		return reList;
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
public static Requirement getRequirement(Long id){
	
	//get Req
	return reqDao.getRequirement(id);
	
}
/*
 * tinh so page dua tren so record
 */
public static int countReportAllBySQL(String filter) {
	
	return reqDao.countReportAllBySQL(filter);

}
/*
 * delete list obj
 */
public static boolean deleteReqList(String keyList[]) {
	if (keyList.length >0){
		int size = keyList.length;
		for(int i = 0; i<size; i++){
			Long id = Long.valueOf(keyList[i]);
			reqDao.deleteReq(id);
//			if (updateStatusGroupReq(id, Constant.GROUP_FREE_REQ)){
//				reqDao.deleteReq(id);
//			}
		}
	}
	return true;
}

/**
 * create List Json Report
 * @param reportList
 * @return
 */
public static JSONObjectList createJSONObjectList(List<RequirementForm> reqList)
{
	JSONObjectList jsonlist = new JSONObjectList();
    int length = reqList.size();
    for (int i = 0; i < length; i++) {
        JSONObject uc =createJSONObject(reqList.get(i));

        jsonlist.getListobject().add(uc);

    }
    
    return jsonlist;
}

/**
 * create JsonObject from an ReportForm
 * @param report
 * @return
 */
public static JSONObject createJSONObject(RequirementForm req)
{
	String keys[] = {"id", "nameReq","nameGroup","process"};
    
	JSONObject uc = new JSONObject(keys);
    uc.getObject().put(keys[0], String.valueOf(req.getId()));
    uc.getObject().put(keys[1], CommonUtil.convertNVLFor(req.getNameReq()));
    uc.getObject().put(keys[2], req.getNameGroup());
    uc.getObject().put(keys[3], String.valueOf(req.getProcess()));

   return uc;     
}


}
