package sp.blo;


import java.util.List;

import sp.dao.UserDao;
import sp.dto.User;
import sp.util.JSONObject;
import sp.util.JSONObjectList;

public class UserBlo {
	private static UserDao userDao = new UserDao();

	/**
	 * checkUser
	 * @param id
	 * @return int
	 */
	public static int checkUser(String id, String password) {
		User user = new User();
		if (userDao.checkExistUser(id)) {
			user = userDao.getUser(id);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					// success
					return 2;
				} else {
					// password bi sai
					return 1;
				}
			}
		}
		// user ko ton tai
		return 0;
	}
	
	public static boolean isExistUser_byEmail(String id) {
		// user ton tai
		if (userDao.checkExistUser(id)) {
				return true;
		}
		// user ko ton tai
		return false;
	}
	
	public static int checkRole(String value){
		return 1;
	}
	
	public static JSONObject createJSONObject(User user)
	{
		String keys[] = {"Email", "Name","IdPermision"};
		//String keys[] = {"key0", "key1","key2"};
        
		JSONObject uc = new JSONObject(keys);
        uc.getObject().put(keys[0], user.getEmail());
        uc.getObject().put(keys[1], user.getName());
        uc.getObject().put(keys[2], user.getIdPermision());

       return uc;     
    }
	
	public static JSONObjectList createJSONObjectList(List<User> list_user)
	{
		JSONObjectList jsonlist = new JSONObjectList();
        int length = list_user.size();
        for (int i = 0; i < length; i++) {
            JSONObject uc =createJSONObject(list_user.get(i));

            jsonlist.getListobject().add(uc);

        }
        
        return jsonlist;
	}
	

	
}
