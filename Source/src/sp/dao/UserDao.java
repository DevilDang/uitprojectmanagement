package sp.dao;
import sp.dto.User;

import sp.dao.PMF;
public class UserDao {
	
	public boolean checkExistUser(String id){
		return PMF.isObject(User.class, id);
	}

	public User getUser(String id){
		return (User)PMF.getObject(User.class, id);
	}		
}
