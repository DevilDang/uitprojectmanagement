package sp.blo;

import java.util.ArrayList;
import java.util.List;

import sp.dao.OrganizationDao;
import sp.dao.PMF;
import sp.dto.Organization;
import sp.form.OrganizationForm;

public class OrganizationBlo {
	
	private static OrganizationDao orgDao = new OrganizationDao();
	
	public static boolean saveOrganization(Organization org){
		return orgDao.saveOrganization(org);
	}

	public List<OrganizationForm> getOrganizationList(int page) {
		List<OrganizationForm> formList = new ArrayList<OrganizationForm>();
		List<Organization> list = new ArrayList<Organization>();
		list = orgDao.getOrganizationList(page);
		OrganizationForm form = new OrganizationForm();
		if (list != null) {
			for(Organization org:list){
				//transfer dto->form
				form.editForm(org);
				//add form
				formList.add(form);
			}
		}
		return formList;

	}
}
