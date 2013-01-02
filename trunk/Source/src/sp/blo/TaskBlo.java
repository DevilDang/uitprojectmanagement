package sp.blo;

import java.util.ArrayList;
import java.util.List;

import sp.dao.PMF;
import sp.dao.TaskDao;
import sp.dto.Task;
import sp.dto.User;
import sp.form.TaskForm;
import sp.util.Constant;


public class TaskBlo {

	public static final TaskDao taskDao = new TaskDao();

	/**
	 * 
	 * @param task
	 * @return
	 */
	public boolean saveTask(Task task) {
		return taskDao.saveTask(task);
	}

	/**
	 * getTaskList paging
	 * 
	 * @param filter
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskForm> getTaskListByFilter(String filter, int page) {

		List<TaskForm> resultList = new ArrayList<TaskForm>();
		List<Task> taskList = (List<Task>) PMF.getObjectListPaging(Task.class,
				filter, page);

		if (taskList == null || taskList.size() == 0) {
			return null;
		}
		int size = taskList.size();
		for (int i = 0; i < size; i++) {
			TaskForm form = new TaskForm();
			form.editForm(taskList.get(i));
			resultList.add(form);
		}

		return resultList;
	}

	/**
	 * get number of record
	 * 
	 * @param filter
	 * @return
	 */
	public int getNumberByFilter(String filter) {
		return taskDao.getNumberByFilter(filter);
	}

	/**
	 * get idUser who had assigned task
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<String> getUserAssignedTask(Long idProject, Long idGroup) {
		List<String> idUserList = new ArrayList<String>();
		StringBuilder filter = new StringBuilder();
		filter.append("idProject==" + idProject);
		filter.append("&&");
		filter.append("idGroup==" + idGroup);
		filter.append("&&");
		filter.append(Constant.DEFAULT_STATUS);
		
		//get List
		List<Task> taskList = (List<Task>) PMF.getObjectList(Task.class,
				filter.toString());
		
		//check result
		if (taskList == null || taskList.size() == 0) {
			return null;
		}
		int size = taskList.size();

		//add mail into idUserList
		for (int i = 0; i < size; i++) {
			idUserList.add(taskList.get(i).getEmailEmployee());
		}
		
		return idUserList;
	}
	
	/**
	 * get all user of group
	 * @param idGroup
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<User> getUserByGroup(Long idGroup){
		
		//get user by idGroup
		List<User> userList = (List<User>) PMF.getObjectList(User.class, "groupID=="+idGroup);
		if (userList == null || userList.size() == 0){
			return null;
		}
		
		return userList;
		
	}
	
}
