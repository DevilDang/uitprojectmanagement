package sp.blo;

import sp.dao.UserDao;
import sp.dto.User;

public class UserBlo {
	private static UserDao userDao = new UserDao();

	/**
	 * checkUser
	 * @param id
	 * @return int
	 *//*
	public static int checkUser(String id) {
		User user = new User();
		if (userDao.checkExistUser(id)) {
			user = userDao.getUser(id);
			if (user != null) {
				if (user.getIdUser().equals(user.getPassword())) {
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
	}*/
	
	public static boolean isExistUser_byEmail(String id) {
		// user ton tai
		if (userDao.checkExistUser(id)) {
				return true;
		}
		// user ko ton tai
		return false;
	}
	
	public static boolean isExistUser_byLoginName(String id) {
		User user = new User();
		if (userDao.checkExistUser(id)) {
			user = userDao.getUser(id);
			if (user != null) {
				return true;
			}
		}
		// user ko ton tai
		return false;
	}
	public static int checkRole(String value){
		return 1;
	}
	
	
}
