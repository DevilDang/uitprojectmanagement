package sp.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class Group implements Serializable{
	
	static  public final String STATUS_FREE = "free";
	static  public final String STATUS_OWN_A_GROUP = "unfree";
	static  public final Boolean ISBUSY = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long IDgroup;
	
	@Persistent
	private Long idProject; //1
	@Persistent
	private String groupname;
	@Persistent
	private String leader; //1
	@Persistent
	private String status;// check staus cua Group : dang free yeu cau , hoac dang thuc hien yeu cau, 
	@Persistent
	private Boolean isbusy;//chua co req, da co req
	@Persistent
	private String statusGroup;// check staus cua Group : dang free, da duoc set vo du an, 
	
	
	
	public String getStatusGroup() {
		return statusGroup;
	}
	public void setStatusGroup(String statusGroup) {
		this.statusGroup = statusGroup;
	}
	public Long getIDgroup() {
		return IDgroup;
	}
	public void setIDgroup(Long iDgroup) {
		IDgroup = iDgroup;
	}
	public Long getIdProject() {
		return idProject;
	}
	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	

}
