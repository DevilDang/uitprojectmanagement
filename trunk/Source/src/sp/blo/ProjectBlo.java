package sp.blo;

import java.util.List;

import sp.dto.Project;
import sp.util.JSONObject;
import sp.util.JSONObjectList;

public class ProjectBlo {
	
	public static JSONObject createJSONObject(Project project)
	{
		String keys[] = {"IDproject", "projectname","projectmanager","process"};
        
		JSONObject uc = new JSONObject(keys);
        uc.getObject().put(keys[0], String.valueOf(project.getIDproject()));
        uc.getObject().put(keys[1], project.getProjectname());
        uc.getObject().put(keys[2], project.getProjectmanager());
        uc.getObject().put(keys[3], String.valueOf(project.getProcess()));
        
       return uc;     
    }
	
	public static JSONObjectList createJSONObjectList(List<Project> list_project)
	{
		JSONObjectList jsonlist = new JSONObjectList();
        int length = list_project.size();
        for (int i = 0; i < length; i++) {
            JSONObject uc =createJSONObject(list_project.get(i));
            jsonlist.getListobject().add(uc);

        }
        
        return jsonlist;
	}
	

}
