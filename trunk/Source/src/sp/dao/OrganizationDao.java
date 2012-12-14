package sp.dao;

import java.util.ArrayList;
import java.util.List;

import sp.dto.Organization;

public class OrganizationDao {

	// insert, update organization into DB
	public boolean saveOrganization(Organization org) {
		return PMF.insertObject(org);
	}

	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationList(int page) {
		List<Organization> resultList = new ArrayList<Organization>();
		resultList = (List<Organization>) PMF.getObjectList(Organization.class,
				page);
		return resultList;

	}
}
