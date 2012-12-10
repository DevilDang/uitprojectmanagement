package sp.blo;

import sp.dao.UserDao;
import sp.dto.User;

public class UserBlo {
	private static UserDao userDao = new UserDao();

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
	}
}
