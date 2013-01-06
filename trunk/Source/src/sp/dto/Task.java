package sp.dto;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Task implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id; 
	
	@Persistent
	private String  kind;
	
	@Persistent
	private String  nameTask;
	
	@Persistent
	private String  emailEmployee;
	
	@Persistent
	private Long idProject;
	
	@Persistent
	private Long idReq;
	
	@Persistent
	private Long idGroup;
	
	@Persistent
	private Date startDate;
	
	@Persistent
	private Date endDate;
	
	@Persistent
	private int  process;
	
	@Persistent
	private String status;
	
	@Persistent
	private String content;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * @return the nameTask
	 */
	public String getNameTask() {
		return nameTask;
	}

	/**
	 * @param nameTask the nameTask to set
	 */
	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
	}

	/**
	 * @return the emailEmployee
	 */
	public String getEmailEmployee() {
		return emailEmployee;
	}

	/**
	 * @param emailEmployee the emailEmployee to set
	 */
	public void setEmailEmployee(String emailEmployee) {
		this.emailEmployee = emailEmployee;
	}

	/**
	 * @return the idProject
	 */
	public Long getIdProject() {
		return idProject;
	}

	/**
	 * @param idProject the idProject to set
	 */
	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}

	/**
	 * @return the idReq
	 */
	public Long getIdReq() {
		return idReq;
	}

	/**
	 * @param idReq the idReq to set
	 */
	public void setIdReq(Long idReq) {
		this.idReq = idReq;
	}

	/**
	 * @return the idGroup
	 */
	public Long getIdGroup() {
		return idGroup;
	}

	/**
	 * @param idGroup the idGroup to set
	 */
	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the process
	 */
	public int getProcess() {
		return process;
	}

	/**
	 * @param process the process to set
	 */
	public void setProcess(int process) {
		this.process = process;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	

}
