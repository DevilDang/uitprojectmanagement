package sp.dao;

import java.util.ArrayList;
import java.util.List;

import sp.dto.Organization;

public class OrganizationDao {

	// insert, update organization into DB
	public boolean saveOrganization(Organization org) {
		return PMF.insertObject(org);
	}
	
	/*
	 * get an obj
	 */
	
	public  Organization getOrganization(Long id){
		return (Organization) PMF.getObject(Organization.class, id);
	}
	
	/*
	 * delete an obj
	 */
	
	public  boolean deleteOrganization(Long id){
		return  PMF.deleteObject(Organization.class, id);
	}
	
	/*
	 * get page record
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationList(int page) {
		List<Organization> resultList = new ArrayList<Organization>();
		resultList = (List<Organization>) PMF.getObjectList(Organization.class,
				page);
		return resultList;

	}
	
	/*
	 * count number all 
	 */
	public int countOrganizationAll() {
		return PMF.countNumberAll(Organization.class);

	}
}
