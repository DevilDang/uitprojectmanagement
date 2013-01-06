package sp.blo;

import java.util.ArrayList;
import java.util.List;

import sp.dao.OrganizationDao;
import sp.dto.Organization;
import sp.form.OrganizationForm;
import sp.util.JSONObject;
import sp.util.JSONObjectList;

public class OrganizationBlo {

	private static OrganizationDao orgDao = new OrganizationDao();

	/*
	 * save an obj
	 */
	public static boolean saveOrganization(Organization org) {
		return orgDao.saveOrganization(org);
	}

	/*
	 * get an obj
	 */
	public static OrganizationForm getOrganizationForm(String key) {
		Long id = Long.valueOf(key);
		Organization org = orgDao.getOrganization(id);
		OrganizationForm form = new OrganizationForm();
		form.editForm(org);
		return form;
	}
	
	/*
	 * delete an obj
	 */
	public static boolean deleteOrganization(String key) {
		Long id = Long.valueOf(key);
		return orgDao.deleteOrganization(id);
	}
	
	/*
	 * delete list obj
	 */
	public static boolean deleteOrganizationList(String keyList[]) {
		if (keyList.length >0){
			int size = keyList.length;
			for(int i = 0; i<size; i++){
				Long id = Long.valueOf(keyList[i]);
				orgDao.deleteOrganization(id);
			}
		}
		return true;
	}
	
	/*
	 * get list record cua 1 page
	 */
	public static List<OrganizationForm> getOrganizationList(int page) {
		List<OrganizationForm> formList = new ArrayList<OrganizationForm>();
		List<Organization> list = orgDao.getOrganizationList(page);
		if (list != null) {
			int length = list.size();
			for (int i = 0; i < length; i++) {
				OrganizationForm form = new OrganizationForm();
				// transfer dto->form
				form.editForm(list.get(i));
				// add form
				formList.add(form);
			}
		}
		return formList;
	}

//	/*
//	 * tinh so page dua tren so record
//	 */
//	public static List<String> countOrganizationAll(int count) {
//		
//		List<String> pageList = new ArrayList<String>();
//		int page = 0;
//		if (count > 0) {
//			page = count / Constant.RECORD;
//			if (count % Constant.RECORD > 0) {
//				page = page + 1;
//			}
//			page = page + 1;
//			for (int i = 1; i < page; i++) {
//				pageList.add(String.valueOf(i));
//			}
//		}
//		return pageList;
//
//	}
	
	/*
	 * tinh so page dua tren so record
	 */
	public static int countOrganizationAll() {
		
		return orgDao.countOrganizationAll();

	}
	
	/**
	 * get value of list checkbox
	 * @param input
	 * @return
	 */
	public static List<String> getListString(String input){
		return null;
	}
	
	/**
	 * create List Json Report
	 * @param reportList
	 * @return
	 */
	public static JSONObjectList createJSONObjectList(List<OrganizationForm> orgList)
	{
		JSONObjectList jsonlist = new JSONObjectList();
        int length = orgList.size();
        for (int i = 0; i < length; i++) {
            JSONObject uc =createJSONObject(orgList.get(i));

            jsonlist.getListobject().add(uc);

        }
        
        return jsonlist;
	}
	
	/**
	 * create JsonObject from an ReportForm
	 * @param report
	 * @return
	 */
	public static JSONObject createJSONObject(OrganizationForm org)
	{
		String keys[] = {"idOrg", "nameOrg","websiteOrg"};
        
		JSONObject uc = new JSONObject(keys);
        uc.getObject().put(keys[0], String.valueOf(org.getIdOrg()));
        uc.getObject().put(keys[1], org.getNameOrg());
        uc.getObject().put(keys[2], org.getWebsiteOrg());

       return uc;     
    }
	
}
