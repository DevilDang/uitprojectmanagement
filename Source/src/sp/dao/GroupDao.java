package sp.dao;

import java.util.ArrayList;
import java.util.List;

import sp.dto.Group;

public class GroupDao {

	public boolean checkExistgroup(Long id) {

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
	
	@SuppressWarnings("unchecked")
	public List<Group> getGroupList(String sort) {
		List<Group> resultList = new ArrayList<Group>();
		resultList = (List<Group>) PMF.getObjectListSort(Group.class,
				sort);
		return resultList;
	}
	
	public void deleteGrouplist(long Group_name_array[]) {

		for (int i = 0; i < Group_name_array.length; i++)
			PMF.deleteObject(Group.class, Group_name_array[i]);
	}
	
	

}
