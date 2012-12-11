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
    private Long idUser;
	/**
	 * nameUser
	 */
	@Persistent
	private String nameUser;
	
	/**
	 * password
	 */
	@Persistent
	private String password;
	
	/**
	 * idPermision
	 */
	private int idPermision;	
	/**
	 * statusLogin
	 */
	private boolean statusLogin;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
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
	
}
