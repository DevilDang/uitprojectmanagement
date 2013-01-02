package sp.dao;

import java.util.List;

import sp.dto.Task;

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
	 * get user who had assigned task
	 * @return
	 */
	public List<String> getUserAssignedTask(String filter){
		
		return null;
	}
}
