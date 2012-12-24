package sp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import sp.blo.UserBlo;
import sp.dto.User;

public class AccountForm extends ValidatorForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String fullname;
	private String password;
	private String retypepassword;
	
	private String permission;
	private long groupCode;
	
	//private String iTypeAction;
	
	
	public AccountForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	public String getiTypeAction() {
//		return iTypeAction;
//	}
//
//
//
//	public void setiTypeAction(String iTypeAction) {
//		this.iTypeAction = iTypeAction;
//	}



	public String getRetypepassword() {
		return retypepassword;
	}



	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
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
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public long getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(long groupCode) {
		this.groupCode = groupCode;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	 {
	   String checkMode = request.getParameter("isEdit");
	   
       ActionErrors errors = new ActionErrors();
       if (getEmail() == null || getEmail().length() < 1) {
           errors.add("username", new ActionMessage("error.username.required"));
           // TODO: add 'error.name.required' key to your resources
       }else
       {
    	   if("add".equals(checkMode))
    	   {
    		   // kiem tra login name co ton tai trong he thong hay khong
    		   if(UserBlo.isExistUser_byEmail(getEmail().trim()))
    	       {	    		
    			   errors.add("usernameExist", new ActionMessage("error.usernameExist.isExist"));
    	       }
    	   }   	   
    	   else if("edit".equals(checkMode))
    	   {
    		   if(!UserBlo.isExistUser_byEmail(getEmail().trim()))
    	       {
    	    		
    			   errors.add("usernameUnExist", new ActionMessage("error.usernameUnExist.isUnExist"));
    	       }
    	   }
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
       
//       if(getiTypeAction() == null)
//       {
//    	   errors.add("Retypepassword", new ActionMessage("error.retypepassword.required"));
//       }
       
       return errors;
	  }
	
	/*
	 * transfer Form -> DTO
	 */
	public User getUser(){
		User user = new User();
		user.setEmail(this.email);
		user.setIdPermision(this.permission);
		user.setPassword(this.password);
		user.setStatusLogin(false);
		user.setName(this.fullname);
		user.setGroupID(this.groupCode);
		return user;		
	}
	
	/*
	 * transfer DTO -> Form
	 */
	public void editForm(User user){
		this.email = user.getEmail();
		this.permission = user.getIdPermision();
		this.password = user.getPassword();
		this.fullname  = user.getName();
		this.groupCode = user.getGroupID();
	}
	
}
