package sp.blo;

import java.util.List;

import sp.dto.Group;
import sp.util.JSONObject;
import sp.util.JSONObjectList;

public class GroupBlo {
	
	public static JSONObject createJSONObject(Group group)
	{
		String keys[] = {"IDgroup", "groupname","leader"};
        
		JSONObject uc = new JSONObject(keys);
        uc.getObject().put(keys[0], String.valueOf(group.getIDgroup()));
        uc.getObject().put(keys[1], group.getGroupname());
        uc.getObject().put(keys[2], group.getLeader());
        
       return uc;     
    }
	
	public static JSONObjectList createJSONObjectList(List<Group> list_Group)
	{
		JSONObjectList jsonlist = new JSONObjectList();
        int length = list_Group.size();
        for (int i = 0; i < length; i++) {
            JSONObject uc =createJSONObject(list_Group.get(i));
            jsonlist.getListobject().add(uc);

        }
        
        return jsonlist;
	}
	
	

}
