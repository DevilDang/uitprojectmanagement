package sp.dao;

import java.util.ArrayList;
import java.util.List;

import sp.dto.Group;
import sp.dto.User;

public class GroupDao {
	
public boolean checkExistGroup(String id){
		
		return PMF.isObject(Group.class, id);
	}

	public Group getGroup(String id){
		return (Group)PMF.getObject(Group.class, id);
	}
	
	// insert, update organization into DB
	public static boolean saveGroup(Group group) {
		return PMF.insertObject(group);
	}

	@SuppressWarnings("unchecked")
	public List<Group> getGroupList(int page) {
		List<Group> resultList = new ArrayList<Group>();
		resultList = (List<Group>) PMF.getObjectList(Group.class,
				page);
		return resultList;

	}
	
	// lấy danh sách nhóm tham gia cùng 1 dự án
	@SuppressWarnings("unchecked")
	public List<Group> getGroupList(String filter) {
		List<Group> resultList = new ArrayList<Group>();
		resultList = (List<Group>) PMF.getObjectList(Group.class,filter);
		return resultList;

	}
	
}
