package sp.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

import sp.dto.Organization;

public class OrganizationForm extends ValidatorForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private  Long idOrg;
	private String nameOrg;
	private String addOrg;
	private String websiteOrg;
	private String numberOrg;
	
	
	public Long getIdOrg() {
		return idOrg;
	}
	public void setIdOrg(Long idOrg) {
		this.idOrg = idOrg;
	}
	public String getNameOrg() {
		return nameOrg;
	}
	public void setNameOrg(String nameOrg) {
		this.nameOrg = nameOrg;
	}
	public String getAddOrg() {
		return addOrg;
	}
	public void setAddOrg(String addOrg) {
		this.addOrg = addOrg;
	}
	public String getWebsiteOrg() {
		return websiteOrg;
	}
	public void setWebsiteOrg(String websiteOrg) {
		this.websiteOrg = websiteOrg;
	}
	public String getNumberOrg() {
		return numberOrg;
	}
	public void setNumberOrg(String numberOrg) {
		this.numberOrg = numberOrg;
	}
	
	/*
	 * transfer Form -> DTO
	 */
	public Organization getOrganization(){
		Organization org = new Organization();
		org.setIdOrg(this.idOrg);
		org.setNameOrg(this.nameOrg);
		org.setAddOrg(this.addOrg);
		org.setNumberOrg(this.numberOrg);
		org.setWebsiteOrg(this.websiteOrg);
		return org;		
	}
	
	/*
	 * transfer DTO -> Form
	 */
	public void editForm(Organization org){
		this.idOrg = org.getIdOrg();
		this.nameOrg= org.getNameOrg();
		this.addOrg = org.getAddOrg();
		this.websiteOrg = org.getWebsiteOrg();
		this.numberOrg = org.getNumberOrg();
	}
}
