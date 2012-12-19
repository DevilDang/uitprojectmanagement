package sp.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Thuy
 *
 */
@PersistenceCapable
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * idUser 
	 */
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String loginName;
   
	
	/**
	 * email
	 */
	@Persistent
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
	private int idPermision;	
	/**
	 * name
	 */
	@Persistent
	private String name;
	/**
	 * groupID
	 */
	@Persistent
	private int groupID;	
	/**
	 * taskID
	 */
	@Persistent
	private int taskID;	
	/**
	 * statusLogin
	 */
	@Persistent
	private boolean statusLogin;
	
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	public int getIdPermision() {
		return idPermision;
	}
	public void setIdPermision(int idPermision) {
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
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
	
	
}
