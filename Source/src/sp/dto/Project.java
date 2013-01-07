package sp.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class Project  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long IDproject;
	
	@Persistent
	private String projectname;
	@Persistent
	private String projectmanager;
	@Persistent
	private long process;
	@Persistent
	private String startDate;
	@Persistent
	private String endDate;
	@Persistent
	private int status;
    
    
    
	public Project() {
		super();
		status = 1;
		// TODO Auto-generated constructor stub
	}
	public Project(Long iDproject, String projectname, String projectmanager,
			long process, String startDate, String endDate, int status) {
		super();
		IDproject = iDproject;
		this.projectname = projectname;
		this.projectmanager = projectmanager;
		this.process = process;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	public Long getIDproject() {
		return IDproject;
	}
	public void setIDproject(Long iDproject) {
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
    
    
    
    
}
