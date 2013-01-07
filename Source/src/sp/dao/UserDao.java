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
	public List<User> getUserList(int page) {
		List<User> resultList = new ArrayList<User>();
		resultList = (List<User>) PMF.getObjectList(User.class,
				page);
		return resultList;

	}
	
	@SuppressWarnings("unchecked")
	public static List<User> getListAccountSorted(long group, int page)
	{
		
        List<User> list_user = null; 
        if(group == -1)
        {
        	// lấy tất cả 
        	list_user = ( List<User>)PMF.getObjectListSorted(User.class,"id desc", page);
        }else
        {
        	list_user = (List<User>)PMF.getObjectList(User.class, "groupID==" +group, "id desc", page);
        }
        
        return list_user;
	}
	
	@SuppressWarnings("unchecked")
	public static List<User> getListAccountSorted(long group)
	{
		
        List<User> list_user = null; 
        if(group == -1)
        {
        	// lấy tất cả 
        	list_user = ( List<User>)PMF.getObjectList(User.class,"id desc");
        }else
        {
        	list_user = (List<User>)PMF.getObjectList(User.class, "groupID==" +group, "id desc");
        }
        
        return list_user;
	}
}
