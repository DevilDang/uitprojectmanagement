package sp.blo;

import java.util.ArrayList;
import java.util.List;

import sp.dao.PMF;
import sp.dao.TaskDao;
import sp.dto.Task;
import sp.dto.User;
import sp.form.TaskForm;
import sp.util.Constant;
import sp.util.JSONObject;
import sp.util.JSONObjectList;

public class TaskBlo {

	public static final TaskDao taskDao = new TaskDao();

	/**
	 * 
	 * @param task
	 * @return
	 */
	public static boolean saveTask(Task task) {
		return taskDao.saveTask(task);
	}

	public static Task getTask(Long id) {
		return (Task) PMF.getObject(Task.class, id);
	}

	public static TaskForm getTaskForm(Long id) {
		Task task = (Task) PMF.getObject(Task.class, id);
		TaskForm form = new TaskForm();
		form.editForm(task);
		return form;
	}

	/**
	 * getTaskList paging
	 * 
	 * @param filter
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<TaskForm> getTaskListByFilter(String filter, int page) {

		List<TaskForm> resultList = new ArrayList<TaskForm>();
		List<Task> taskList = (List<Task>) PMF.getObjectListPaging(Task.class,
				filter, page);

		if (taskList != null && taskList.size() > 0) {
			int size = taskList.size();
			for (int i = 0; i < size; i++) {
				TaskForm form = new TaskForm();
				form.editForm(taskList.get(i));
				resultList.add(form);
			}
		}

		return resultList;
	}

	/**
	 * get filter sql from sortForm
	 * 
	 * @param sortForm
	 * @return
	 */
	public static String getFilterSQL(TaskForm sortForm) {

		StringBuilder filter = new StringBuilder();
		// admin, pm: read
		if ((sortForm.getLevel() == Constant.ADMIN_INT)
				|| (sortForm.getLevel() == Constant.PM_INT)) {
			filter.append("idProject==" + sortForm.getIdProject());
		}
		// leader
		else if ((sortForm.getLevel() == Constant.LEADER_INT)) {
			filter.append("idProject==" + sortForm.getIdProject());
			filter.append("&&");
			filter.append("idReq==" + sortForm.getIdReq());
			filter.append("&&");
			filter.append("idGroup==" + sortForm.getIdGroup());
			filter.append("&&");
			filter.append("kind==\'" + sortForm.getKind() + "\'");
			filter.append("&&");
			filter.append("status==\'" + sortForm.getStatus() + "\'");
		} else if ((sortForm.getLevel() == Constant.EMPLOYEE_INT)) {
			filter.append("idProject==" + sortForm.getIdProject());
			filter.append("&&");
			filter.append("idReq==" + sortForm.getIdReq());
			filter.append("&&");
			filter.append("idGroup==" + sortForm.getIdGroup());
			filter.append("&&");
			filter.append("kind==\'" + sortForm.getKind() + "\'");
			filter.append("&&");
			filter.append("status==\'" + sortForm.getStatus() + "\'");
			filter.append("&&");
			filter.append("emailEmployee==" + sortForm.getEmailEmployee());
		}
		return filter.toString();
	}

	/**
	 * get number of record
	 * 
	 * @param filter
	 * @return
	 */
	public static int getNumberByFilter(String filter) {
		return taskDao.getNumberByFilter(filter);
	}

	// /**
	// * get idUser who had assigned task
	// *
	// * @return
	// */
	//
	// @SuppressWarnings("unchecked")
	// public List<String> getUserAssignedTask(Long idProject, Long idGroup) {
	// List<String> idUserList = new ArrayList<String>();
	// StringBuilder filter = new StringBuilder();
	// filter.append("idProject==" + idProject);
	// filter.append("&&");
	// filter.append("idGroup==" + idGroup);
	// filter.append("&&");
	// filter.append(Constant.DEFAULT_STATUS);
	//
	// //get List
	// List<Task> taskList = (List<Task>) PMF.getObjectList(Task.class,
	// filter.toString());
	//
	// //check result
	// if (taskList == null || taskList.size() == 0) {
	// return null;
	// }
	// int size = taskList.size();
	//
	// //add mail into idUserList
	// for (int i = 0; i < size; i++) {
	// idUserList.add(taskList.get(i).getEmailEmployee());
	// }
	//
	// return idUserList;
	// }
	//
	/**
	 * get all user of group
	 * 
	 * @param idGroup
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<User> getUserByGroup(Long idGroup) {

		// get user by idGroup
		List<User> userList = (List<User>) PMF.getObjectList(User.class,
				"groupID==" + idGroup);
		if (userList == null || userList.size() == 0) {
			return null;
		}

		return userList;

	}

	/**
	 * get danh sach nhan vien thuoc group chua co task
	 * 
	 * @return
	 */
	public static List<String> getUserFreeTask(Long idGroup) {
		StringBuilder filter = new StringBuilder();
		filter.append("groupID==" + idGroup);
		filter.append("&&");
		filter.append("statusTask==\'" + Constant.USER_FREE_TASK + "\'");

		return taskDao.getUserFreeTask(filter.toString());

	}

	/**
	 * update status of User in User class
	 * 
	 * @param email
	 *            User
	 * @param status
	 *            update
	 * @return
	 */
	public static boolean updateStatusTask(String email, String status) {

		// get user
		User user = (User) PMF.getObject(User.class, email);

		// set status
		user.setStatusTask(status);

		// save into DB
		return PMF.insertObject(user);

	}

	/**
	 * update old employee = free, new employee = assign
	 * 
	 * @param email
	 *            User
	 * @param status
	 *            update
	 * @return
	 */
	public static boolean updateStatusUser(Long idTask, String email) {

		boolean flag = false;
		// get Task
		Task task = (Task) PMF.getObject(Task.class, idTask);

		// get email
		String oldEmail = task.getEmailEmployee();

		if (!oldEmail.equals(email)) {

			// set status of user = free
			flag = updateStatusTask(oldEmail, Constant.USER_FREE_TASK);

			if (flag) {
				// set new email = assign
				updateStatusTask(email, Constant.USER_ASSIGN_TASK);
			}
		}
		return flag;
	}

	public static TaskForm getSortForm(Long idProject, Long idReq,
			Long idGroup, String email, int level) {

		TaskForm sortForm = new TaskForm();
		sortForm.setIdProject(idProject);
		sortForm.setIdReq(idReq);
		sortForm.setIdGroup(idGroup);
		sortForm.setEmailEmployee(email);
		sortForm.setLevel(level);
		return sortForm;
	}

	public static boolean deleteReqList(String keyList[]) {
		if (keyList.length > 0) {
			int size = keyList.length;
			for (int i = 0; i < size; i++) {
				Long id = Long.valueOf(keyList[i]);
				if (updateStatusUserTask(id, Constant.USER_ASSIGN_TASK)) {
					taskDao.deleteTask(id);
				}
			}
		}
		return true;
	}

	public static boolean updateStatusUserTask(Long idTask, String status) {
		Task task = getTask(idTask);
		return updateStatusTask(task.getEmailEmployee(), status);
	}

	public static JSONObjectList createJSONObjectList(List<TaskForm> taskList) {
		JSONObjectList jsonlist = new JSONObjectList();
		int length = taskList.size();
		for (int i = 0; i < length; i++) {
			JSONObject uc = createJSONObject(taskList.get(i));

			jsonlist.getListobject().add(uc);

		}

		return jsonlist;
	}

	/**
	 * create JsonObject from an ReportForm
	 * 
	 * @param report
	 * @return
	 */
	public static JSONObject createJSONObject(TaskForm task) {
		String keys[] = { "id", "nameTask", "idGroup", "process" };

		JSONObject uc = new JSONObject(keys);
		uc.getObject().put(keys[0], String.valueOf(task.getId()));
		uc.getObject().put(keys[1], task.getNameTask());
		uc.getObject().put(keys[2], String.valueOf(task.getIdGroup()));
		uc.getObject().put(keys[3], String.valueOf(task.getProcess()));

		return uc;
	}
}
