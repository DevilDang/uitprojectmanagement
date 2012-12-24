package sp.dao;
import java.util.ArrayList;
import java.util.List;

import sp.dto.User;

import sp.dao.PMF;
public class UserDao {
	
	public boolean checkExistUser(String id){
		
		return PMF.isObject(User.class, id);
	}

	public User getUser(String id){
		return (User)PMF.getObject(User.class, id);
	}
	
	// insert, update organization into DB
	public static boolean saveUser(User user) {
		return PMF.insertObject(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getOrganizationList(int page) {
		List<User> resultList = new ArrayList<User>();
		resultList = (List<User>) PMF.getObjectList(User.class,
				page);
		return resultList;

	}
}
