package sp.form;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import sp.blo.UserBlo;

public class UserForm extends ValidatorForm implements Serializable {

	/**
	 * Class này dùng để đăng nhập vào ứng dụng
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	 public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	        ActionErrors errors = new ActionErrors();
	        
	        boolean isHasUsername = false;
	        
	        if (getUsername() == null || getUsername().length() < 1) {
	            errors.add("name", new ActionMessage("error.name.required"));
	            // TODO: add 'error.name.required' key to your resources
	        }else
	        {
	        	isHasUsername = true;      	
	        }
	        
	        if (getPassword() == null || getPassword().length() < 1) {
	            errors.add("pass", new ActionMessage("error.pass.required"));
	            // TODO: add 'error.name.required' key to your resources
	        }
	        else
	        {
	        	if(isHasUsername)
	        	{
		        	int result = UserBlo.checkUser(getUsername(), getPassword());
		        	
		        	if(result != 2)
		        	{
			        	if(result == 1)
			        	{
			        		errors.add("name", new ActionMessage("error.name.wrongpassword"));
			        		
			        	}else if(result == 0)
			        	{
			        		errors.add("name", new ActionMessage("error.name.unexist"));
			        		
			        	}
		        	}
	        	}
	        }
	        return errors;
	    }
	

}
