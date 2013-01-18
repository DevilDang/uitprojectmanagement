package sp.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Thuy
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class User implements Serializable {
	
	public static final String ADMIN = "admin";
	public static final String PROJECT_MANAGER = "project_manager";
	public static final String LEADER = "leader";
	public static final String EMPLOYER = "employer";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * idUser 
	 */
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String email;
   	
	/**
	 * password
	 */
	@Persistent
	private String password;
	
	/**
	 * idPermision
	 */
	@Persistent
	private String idPermision;	
	/**
	 * name
	 */
	@Persistent
	private String name;
	/**
	 * groupID
	 */
	@Persistent
	private long groupID;	
	
	/**
	 * statusLogin
	 */
	@Persistent
	private boolean statusLogin;
	
	/**
	 * LongID
	 */
	@Persistent
	private long id;
	
	/**
	 * statusTask
	 */
	@Persistent
	private String statusTask;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdPermision() {
		return idPermision;
	}
	public void setIdPermision(String idPermision) {
		this.idPermision = idPermision;
	}
	public boolean isStatusLogin() {
		return statusLogin;
	}
	public void setStatusLogin(boolean statusLogin) {
		this.statusLogin = statusLogin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getGroupID() {
		return groupID;
	}
	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}
	/**
	 * @return the statusTask
	 */
	public String getStatusTask() {
		return statusTask;
	}
	/**
	 * @param statusTask the statusTask to set
	 */
	public void setStatusTask(String statusTask) {
		this.statusTask = statusTask;
	}
	
}
