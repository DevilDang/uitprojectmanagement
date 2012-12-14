/**
 * 
 */
package sp.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Thuy
 *
 */
@PersistenceCapable
public class Organization  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long idOrg;
	
	@Persistent
	private String nameOrg;
	
	@Persistent
	private String addOrg;
	
	@Persistent
	private String websiteOrg;
	
	@Persistent
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
	

}
