package sp.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import sp.dto.Requirement;
import sp.dto.Task;

public class RequirementForm extends ValidatorForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nameReq;
	private String content;
	private Long idProject;
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
	 * @return the nameReq
	 */
	public String getNameReq() {
		return nameReq;
	}
	/**
	 * @param nameReq the nameReq to set
	 */
	public void setNameReq(String nameReq) {
		this.nameReq = nameReq;
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
	public Requirement getRequirement(){
		Requirement req = new Requirement();
		req.setId(this.id);
		req.setNameReq(this.nameReq);
		req.setContent(this.content);
		req.setIdProject(this.idProject);
		req.setIdGroup(this.idGroup);
		req.setStartDate(this.startDate);
		req.setEndDate(this.endDate);
		req.setProcess(this.process);
		req.setStatus(this.status);
		return req;
	}
	
	/*
	 * transfer DTO -> Form
	 */
	public void editForm(Requirement req){
		this.id = req.getId();
		this.nameReq = req.getNameReq();
		this.content = req.getContent();
		this.idProject = req.getIdProject();
		this.idGroup = req.getIdGroup();
		this.startDate = req.getStartDate();
		this.endDate = req.getEndDate();
		this.process = req.getProcess();
		this.status = req.getStatus();
		
		
	}
}