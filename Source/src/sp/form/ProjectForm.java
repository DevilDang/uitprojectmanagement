package sp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import sp.dao.ProjectDao;
import sp.dto.Project;

public class ProjectForm  extends ValidatorForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long IDproject;
	private String projectname;
	private String projectmanager;
	private long process;
	private String startDate;
	private String endDate;
    private int status;
    
    
	public ProjectForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectForm(Long iDproject, String projectname,
			String projectmanager, long process, String startDate,
			String endDate, int status) {
		super();
		IDproject = iDproject;
		this.projectname = projectname;
		this.projectmanager = projectmanager;
		this.process = process;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	public long getIDproject() {
		return IDproject;
	}
	public void setIDproject(long iDproject) {
		IDproject = iDproject;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProjectmanager() {
		return projectmanager;
	}
	public void setProjectmanager(String projectmanager) {
		this.projectmanager = projectmanager;
	}
	public long getProcess() {
		return process;
	}
	public void setProcess(long process) {
		this.process = process;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
	 public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	        ActionErrors errors = new ActionErrors();
	        
	        String checkMode = request.getParameter("isEdit");
	        if("add".equals(checkMode))
	        {
	        	if(getIDproject() != 0)
	        	{
	        		ProjectDao projectdao = new ProjectDao();
	        		if(projectdao.checkExistproject(getIDproject()))
	        		{
	        			errors.add("idproject", new ActionMessage("error.idproject.isexist"));
	        		}
	        	}
	        }else if("edit".equals(checkMode))
	        {
	        	if(getIDproject() != 0)
	        	{
	        		ProjectDao projectdao = new ProjectDao();
	        		if(!projectdao.checkExistproject(getIDproject()))
	        		{
	        			errors.add("idproject", new ActionMessage("error.idproject.unexist"));
	        		}
	        	}
	        }
	        
	        if (getProjectname() == null || getProjectname().length() < 1) {
	            errors.add("projectname", new ActionMessage("error.projectname.required"));
	            // TODO: add 'error.name.required' key to your resources
	        }
	        
	        if(getEndDate() == null || getEndDate().length() < 1)
	        {
	        	errors.add("enddate", new ActionMessage("error.enddate.required"));
	        }
	        
	        if(getStartDate() == null || getStartDate().length() < 1)
	        {
	        	errors.add("startdate", new ActionMessage("error.startdate.required"));
	        }
	        return errors;
	    }
	 
	 
	 /*
		 * transfer Form -> DTO
		 */
		public Project getProject(){
			Project project = new Project();
			project.setIDproject(this.IDproject);
			project.setProcess(this.process);
			project.setProjectmanager(this.projectmanager);
			project.setProjectname(this.projectname);
			project.setEndDate(this.endDate);
			project.setStartDate(this.startDate);
			project.setStatus(this.status);
			return project;		
		}
		
		/*
		 * transfer DTO -> Form
		 */
		
		
		public void editForm(Project project){
			
		}
		public ProjectForm(Project project) {
			
			this.IDproject = project.getIDproject();
			this.endDate = project.getEndDate();
			this.process = project.getProcess();
			this.projectmanager = project.getProjectmanager();
			this.projectname = project.getProjectname();
			this.startDate = project.getStartDate();
			this.status = project.getStatus();
		}
    
}
