package sp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import sp.dao.UserDao;
import sp.dto.User;
import sp.util.Constant;


public class ChangePassForm extends ValidatorForm implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oldpassword;
	 private String newpassword;
	 private String retype_newpassword;
	 
	 
	 
	 public ChangePassForm() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getOldpassword() {
		return oldpassword;
	}



	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}



	public String getNewpassword() {
		return newpassword;
	}



	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}



	public String getRetype_newpassword() {
		return retype_newpassword;
	}



	public void setRetype_newpassword(String retype_newpassword) {
		this.retype_newpassword = retype_newpassword;
	}



	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		   
		    
	        ActionErrors errors = new ActionErrors();
	      	        
	        if (getOldpassword() == null || getOldpassword().length() < 1) {
	            errors.add("Oldpassword", new ActionMessage("error.Oldpassword.required"));
	            // TODO: add 'error.name.required' key to your resources
	        }else
	        {
	        	 String userlogin = (String)request.getSession().getAttribute(Constant.User_Login);
	        	 UserDao userdao = new UserDao();
	        	 User user = userdao.getUser(userlogin);
	        	 if(user != null)
	        	 {
	        		 if(!getOldpassword().equals(user.getPassword()))
	        		 {
	        			 errors.add("wrongOldpassword", new ActionMessage("error.wrongOldpassword.required"));
	        		 }
	        	 }
	        }
	        
	        if (getNewpassword() == null || getNewpassword().length() < 1) {
	            errors.add("Newpassword", new ActionMessage("error.Newpassword.required"));
	            // TODO: add 'error.name.required' key to your resources
	        }
	        
	        if (getRetype_newpassword() == null || getRetype_newpassword().length() < 1) {
	            errors.add("Retype_newpassword", new ActionMessage("error.Retype_newpassword.required"));
	            // TODO: add 'error.name.required' key to your resources
	        }
	        
	        if(getNewpassword() != null && getNewpassword().length() >= 1 && getRetype_newpassword() != null && getRetype_newpassword().length() >= 1)
			{
				if(!getNewpassword().equals(getRetype_newpassword()))
				{
					errors.add("notmatch", new ActionMessage(
							"error.notmatch.required"));
				}
			}
	        	      
	        return errors;
	    }

}
