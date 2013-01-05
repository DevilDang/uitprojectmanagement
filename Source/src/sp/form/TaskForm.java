package sp.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import sp.dto.Task;

public class TaskForm extends ValidatorForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; 
	private String kind;
	private String  nameTask;
	private String  emailEmployee;
	private Long idProject;
	private Long idReq;
	private Long idGroup;
	private Date startDate;
	private Date endDate;
	private int process;
	private String status;
	private int level; 
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
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/*
	 * transfer Form -> DTO
	 */
	public Task getTask(){
		Task task = new Task();
		task.setId(this.id);
		task.setKind(this.kind);
		task.setEmailEmployee(this.emailEmployee);
		task.setNameTask(this.nameTask);
		task.setIdProject(this.idProject);
		task.setIdReq(this.idReq);
		task.setIdGroup(this.idProject);
		task.setStartDate(this.startDate);
		task.setEndDate(this.endDate);
		task.setProcess(this.process);
		task.setStatus(this.status);
		return task;
	}
	
	/*
	 * transfer DTO -> Form
	 */
	public void editForm(Task task){
		this.id = task.getId();
		this.kind = task.getKind();
		this.emailEmployee = task.getEmailEmployee();
		this.nameTask = task.getNameTask();
		this.idReq = task.getIdReq();
		this.idGroup = task.getIdGroup();
		this.startDate = task.getStartDate();
		this.endDate = task.getEndDate();
		this.status = task.getStatus();
		this.process = task.getProcess();
	}
	
}
