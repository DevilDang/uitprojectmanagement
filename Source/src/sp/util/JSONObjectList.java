package sp.util;

import java.util.Vector;
/*
 * Class này tạo ra một chuỗi nhiều đối tượng JSON*/
public class JSONObjectList
{
	Vector<JSONObject> listobject;

	/**
	 * 
	 */
	public JSONObjectList() {
		listobject = new Vector<JSONObject>();
	}

	/**
	 * @return the listobject
	 */
	public Vector<JSONObject> getListobject() {
		return listobject;
	}

	/**
	 * @param listobject the listobject to set
	 */
	public void setListobject(Vector<JSONObject> listobject) {
		this.listobject = listobject;
	}
	
	public String toJSONtextString()
	{
		String result = "";
		int lenght = listobject.size();
		
		for(int i = 0;i<lenght;i++)
		{
			result = result + listobject.elementAt(i).toJSONtextString();
			if(i == lenght - 1)
				break;
			result = result + ",";
		}
		
		result = "[" + result + "]";
		
		return result;
	}

}
