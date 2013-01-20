package sp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import sp.dao.GroupDao;
import sp.dto.Group;

public class GroupForm extends ValidatorForm implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long IDgroup;

	private Long idProject; 
	private String groupname;
	private String leader; 
	private String status;
	
	public GroupForm() {
		super();
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
	
	 public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		   
		    
	        ActionErrors errors = new ActionErrors();
	        
	        String checkMode = request.getParameter("isEdit");
	        if("add".equals(checkMode))
	        {
	        	if(getIDgroup() != 0)
	        	{
	        		GroupDao groupdao = new GroupDao();
	        		if(groupdao.checkExistgroup(getIDgroup()))
	        		{
	        			errors.add("idgroup", new ActionMessage("error.idgroup.isexist"));
	        		}
	        	}
	        }else if("edit".equals(checkMode))
	        {
	        	if(getIDgroup() != 0)
	        	{
	        		GroupDao groupdao = new GroupDao();
	        		if(!groupdao.checkExistgroup(getIDgroup()))
	        		{
	        			errors.add("idgroup", new ActionMessage("error.idgroup.unexist"));
	        		}
	        	}
	        }
	        
	        
	        if (getGroupname() == null || getGroupname().length() < 1) {
	            errors.add("groupname", new ActionMessage("error.groupname.required"));
	            // TODO: add 'error.name.required' key to your resources
	        }
	        	      
	        return errors;
	    }
	 
	 
	 /*
		 * transfer Form -> DTO
		 */
		public Group getGroup(){
		  Group group = new Group();
		  group.setIDgroup(this.IDgroup);
		  group.setIdProject(this.idProject);
		  group.setGroupname(this.getGroupname());
		  group.setLeader(this.leader);
		  group.setStatus(this.getStatus());
		  
		  return group;
		}
		
		/*
		 * transfer DTO -> Form
		 */
		
		
		public void editForm(Group Group){
			
		}
		public GroupForm(Group group) {
			
			this.IDgroup = group.getIDgroup();
			this.idProject = group.getIdProject();
			this.groupname = group.getGroupname();
			this.leader = group.getLeader();
			this.status = group.getStatus();
		}

}
