package sp.blo;

import sp.dao.PMF;
import sp.dto.Group;
import sp.dto.Project;
import sp.dto.User;
import sp.util.Constant;

public class CommonBlo {
	
	public static void createData(){
		
		//create Leader 
		User user = new User();
		user.setEmail("a@yahoo.com");
		user.setGroupID(2);
		user.setIdPermision(String.valueOf(Constant.LEADER));
		user.setStatusTask(Constant.USER_FREE_TASK);
		//save
		PMF.insertObject(user);
		
		//createPM
		User user1 = new User();
		user1.setEmail("b@yahoo.com");
		user1.setIdPermision(String.valueOf(Constant.PM));
		user1.setStatusTask(Constant.USER_FREE_TASK);
		//save
		PMF.insertObject(user1);
		
		//create Em
		User user2 = new User();
		user2.setEmail("c@yahoo.com");
		user2.setGroupID(2);
		user2.setIdPermision(String.valueOf(Constant.EMPLOYEE));
		user2.setStatusTask(Constant.USER_FREE_TASK);
		//save
		PMF.insertObject(user2);
		
		//create Project
		Project pro = new Project();
		pro.setProjectmanager("b@yahoo.com");
		//save
		PMF.insertObject(pro);
		
		//create Group 1
		Group group = new Group();
		group.setLeader("a@yahoo.com");
		group.setIdProject(1L);
		group.setStatus(Constant.GROUP_FREE_REQ);
		PMF.insertObject(group);
		
//		//create data 2
//		Group group1 = new Group();
//		group1.setLeader("a1@yahoo.com");
//		group1.setIdProject(1L);
//		group1.setStatus(Constant.GROUP_FREE_REQ);
//		PMF.insertObject(group);
//		
//		
//		
//		//create leader 
//		User user4 = new User();
//		user4.setEmail("a1@yahoo.com");
//		user4.setGroupID(group1.getIDgroup());
//		user4.setIdPermision(String.valueOf(Constant.LEADER));
//		user4.setStatusTask(Constant.USER_FREE_TASK);
//		//save
//		PMF.insertObject(user4);
//		
//		//create list user
//		for  ( int i = 1; i <3; i++){
//			//create Em
//			User user5 = new User();
//			user5.setEmail("a"+String.valueOf(i)+"@yahoo.com");
//			user5.setGroupID(group1.getIDgroup());
//			user5.setIdPermision(String.valueOf(Constant.EMPLOYEE));
//			user5.setStatusTask(Constant.USER_FREE_TASK);
//			//save
//			PMF.insertObject(user5);
//		}
//		
		
		
	}
}
