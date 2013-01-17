package sp.dao;
import java.util.ArrayList;
import java.util.List;

import sp.dto.Group;
import sp.dto.User;

import sp.dao.PMF;
public class UserDao {
	
	public boolean checkExistUser(String id) {

		return PMF.isObject(User.class, id);
	}

	public User getUser(String id) {
		return (User) PMF.getObject(User.class, id);
	}

	// insert, update organization into DB
	public static boolean saveUser(User user) {
		return PMF.insertObject(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserList(int page,String sort) {
		List<User> resultList = new ArrayList<User>();
		resultList = (List<User>) PMF.getObjectListSorted(User.class, sort, page);
		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<User> getUserListFilter(int page, String filter,
			String sort) {
		List<User> resultList = new ArrayList<User>();
		resultList = (List<User>) PMF.getObjectList(User.class, filter,
				sort, page);
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserListFilter(String filter,
			String sort) {
		List<User> resultList = new ArrayList<User>();
		resultList = (List<User>) PMF.getObjectList(User.class, filter,
				sort);
		return resultList;
	}
	
	public void deleteUserlist(long User_name_array[]) {

		for (int i = 0; i < User_name_array.length; i++)
			PMF.deleteObject(User.class, User_name_array[i]);
	}
	
}
