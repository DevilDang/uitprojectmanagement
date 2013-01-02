package sp.dao;

import java.util.List;

import sp.dto.Group;
import sp.dto.Requirement;
import sp.dto.Task;

public class RequirementDao {

	/**
	 * save a Requirement
	 * @param req
	 * @return boolean
	 */
	public boolean saveRequirement(Requirement req){
		return PMF.insertObject(req);
	}
	
	public Requirement getRequirement(Long id){
		return (Requirement) PMF.getObject(Requirement.class, id);
	}
	

	/**
	 * get ReqList
	 * @param filter
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Requirement> getReqListByFilter(String filter, int page){

		return (List<Requirement>)PMF.getObjectListPaging(Requirement.class, filter, page);
	}
	
	/**
	 * get number of record 
	 * @param filter
	 * @return int 
	 */
	public int getNumberByFilter(String filter){
		return PMF.countNumberAll(Requirement.class, filter);
	}
	
//	/**
//	 * get danh sach req cua du an
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public List<Requirement> getReqOfProject(String filter){
//		
//		return (List<Requirement>)PMF.getObjectList(Requirement.class, filter);
//	}
	
	
	/**
	 * get group cua du an
	 */
	@SuppressWarnings("unchecked")
	public List<Group> getGroupOfProject(String filter){
		
		return (List<Group>)PMF.getObjectList(Group.class, filter);
	}
	
	/**
	 * get group da duoc set Req
	 */
	@SuppressWarnings("unchecked")
	public List<Requirement> getGroupOfReq(String filter){
		
		return (List<Requirement>)PMF.getObjectList(Requirement.class, filter);
	}
}
