package sp.dao;

import java.util.ArrayList;
import java.util.List;

import sp.dto.Task;
import sp.dto.User;

public class TaskDao {
	
	/**
	 * save a Task
	 * @param task
	 * @return boolean
	 */
	public boolean saveTask(Task task){
		return PMF.insertObject(task);
	}

	/**
	 * getTaskList Paging
	 * @param filter
	 * @param page
	 * @return List<Task> 
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getTaskListByFilter(String filter, int page){

		return (List<Task>)PMF.getObjectListPaging(Task.class, filter, page);
	}
	
	/**
	 * get number of record 
	 * @param filter
	 * @return int 
	 */
	public int getNumberByFilter(String filter){
		return PMF.countNumberAll(Task.class, filter);
	}
	
	/**
	 * get danh sach nhan vien thuoc group ma chua co task
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getUserFreeTask(String filter){
		
		List<User> userList = (List<User>) PMF.getObject(User.class, filter);
		
		if (userList == null || userList.size() == 0 ){
			return null;
		}
		List<String> idUserList = new ArrayList<String>();
		int size = userList.size();
		for (int i = 0; i<size; i++){
			idUserList.add(userList.get(i).getEmail());
		}
		
		return idUserList;
	}
}
