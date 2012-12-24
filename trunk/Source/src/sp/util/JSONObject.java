package sp.util;

import java.util.HashMap;

/*
 * Class này dùng để tạo ra 1 đối tượng JSON*/
public class JSONObject {
	HashMap<String, String>  object;
	String keys[];
	public JSONObject(String keys[])
	{
		 object = new HashMap<String, String>();
		 this.keys = keys;
	}

	/**
	 * @return the object
	 */
	public HashMap<String, String> getObject() {
		
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(HashMap<String, String> object) {
		this.object = object;
	}
	
	public String toJSONtextString()
	{
		String result = "";
		int lenght = keys.length;
		for(int i = 0;i<lenght;i++)
		{
			result = result + "\"" + keys[i] + "\"" +":" + "\"" + object.get(keys[i]) + "\"";
			if(i == lenght - 1)
				break;
			result = result + ",";
		}
		
		result = "{" + result + "}";
		
		return result;
	}

}
