package sp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import sp.dto.User;

public class AccountForm extends ValidatorForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String email;
	private String fullname;
	private String password;
	private String retypepassword;
	
	private int permission;
	private int groupCode;
	private int taskCode;
	
	private String iTypeAction;
	
	
	public AccountForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getiTypeAction() {
		return iTypeAction;
	}



	public void setiTypeAction(String iTypeAction) {
		this.iTypeAction = iTypeAction;
	}



	public String getRetypepassword() {
		return retypepassword;
	}



	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public int getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}
	public int getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(int taskCode) {
		this.taskCode = taskCode;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	 {	   
       ActionErrors errors = new ActionErrors();
       if (getUsername() == null || getUsername().length() < 1) {
           errors.add("username", new ActionMessage("error.username.required"));
           // TODO: add 'error.name.required' key to your resources
       }
       
       if (getEmail() == null || getEmail().length() < 1) {
           errors.add("email", new ActionMessage("error.email.required"));
           // TODO: add 'error.name.required' key to your resources
       }
       
       if (getFullname() == null || getFullname().length() < 1) {
           errors.add("Fullname", new ActionMessage("error.fullname.required"));
           // TODO: add 'error.name.required' key to your resources
       }
       
       if (getPassword() == null || getPassword().length() < 1) {
           errors.add("Password", new ActionMessage("error.password.required"));
           // TODO: add 'error.name.required' key to your resources
       }
       
       if (getRetypepassword() == null || getRetypepassword().length() < 1) {
           errors.add("Retypepassword", new ActionMessage("error.retypepassword.required"));
           // TODO: add 'error.name.required' key to your resources
       }
       
       if(getiTypeAction() == null)
       {
    	   errors.add("Retypepassword", new ActionMessage("error.retypepassword.required"));
       }
       
       return errors;
	  }
	
	/*
	 * transfer Form -> DTO
	 */
	public User getUser(){
		User user = new User();
		user.setEmail(this.email);
		user.setLoginName(this.username);
		user.setIdPermision(this.permission);
		user.setPassword(this.password);
		user.setStatusLogin(false);
		user.setName(this.fullname);
		user.setGroupID(this.groupCode);
		user.setTaskID(this.taskCode);
		return user;		
	}
	
	/*
	 * transfer DTO -> Form
	 */
	public void editForm(User user){
		this.email = user.getEmail();
		this.username = user.getLoginName();
		this.permission = user.getIdPermision();
		this.password = user.getPassword();
		this.fullname  = user.getName();
		this.groupCode = user.getGroupID();
		this.taskCode = user.getTaskID();
	}
	
}
