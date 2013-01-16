package sp.dao;

import java.util.ArrayList;
import java.util.List;

import sp.dto.Project;

public class ProjectDao {
	
public boolean checkExistproject(Long id){
		
		return PMF.isObject(Project.class, id);
	}

	public Project getProject(long id){
		return (Project)PMF.getObject(Project.class, id);
	}
	
	// insert, update organization into DB
	public static boolean saveProject(Project project) {
		return PMF.insertObject(project);
	}

	@SuppressWarnings("unchecked")
	public List<Project> getProjectList(int page) {
		List<Project> resultList = new ArrayList<Project>();
		resultList = (List<Project>) PMF.getObjectList(Project.class,
				page);
		return resultList;

	}
	
	@SuppressWarnings("unchecked")
	public List<Project> getProjectListFilter(int page, String filter, String sort) {
		List<Project> resultList = new ArrayList<Project>();
		resultList = (List<Project>) PMF.getObjectList(Project.class, filter, sort, page);
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> getProjectListFilter(String filter, String sort) {
		List<Project> resultList = new ArrayList<Project>();
		resultList = (List<Project>) PMF.getObjectList(Project.class, filter, sort);
		return resultList;
	}
	
	public void deleteProjectlist(long project_name_array[])
	{
 
    	for(int i = 0;i<project_name_array.length;i++)
    		PMF.deleteObject(Project.class, project_name_array[i]);
	}
	
}
