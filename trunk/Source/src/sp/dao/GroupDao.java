package sp.dao;

import java.util.ArrayList;
import java.util.List;

import sp.dto.Group;
import sp.dto.Project;
import sp.dto.User;

public class GroupDao {

	public boolean checkExistproject(Long id) {

		return PMF.isObject(Group.class, id);
	}

	public Group getGroup(long id) {
		return (Group) PMF.getObject(Group.class, id);
	}

	// insert, update organization into DB
	public static boolean saveGroup(Group group) {
		return PMF.insertObject(group);
	}

	@SuppressWarnings("unchecked")
	public List<Group> getGroupList(int page) {
		List<Group> resultList = new ArrayList<Group>();
		resultList = (List<Group>) PMF.getObjectList(Group.class, page);
		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<Group> getGroupListFilter(int page, String filter,
			String sort) {
		List<Group> resultList = new ArrayList<Group>();
		resultList = (List<Group>) PMF.getObjectList(Group.class, filter,
				sort, page);
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Group> getGroupListFilter(String filter,
			String sort) {
		List<Group> resultList = new ArrayList<Group>();
		resultList = (List<Group>) PMF.getObjectList(Group.class, filter,
				sort);
		return resultList;
	}
	
	public void deleteGrouplist(long project_name_array[]) {

		for (int i = 0; i < project_name_array.length; i++)
			PMF.deleteObject(Group.class, project_name_array[i]);
	}
	
	

}
