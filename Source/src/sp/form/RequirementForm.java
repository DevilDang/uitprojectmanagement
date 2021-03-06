package sp.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

import sp.blo.CommonBlo;
import sp.dto.Requirement;
import sp.util.CommonUtil;
import sp.util.Constant;

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
	private String nameGroup;
	private String startDate;
	private String endDate;
	private int process;
	private String status;
	private int level;
	private String mode;
	private String statusReq; //good or late
	private int lateDate; 
	private String stage;
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
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
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
	
	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
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
		req.setStartDate(CommonUtil.convertStrToDate(this.startDate));
		req.setEndDate(CommonUtil.convertStrToDate(this.endDate));
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
		this.nameGroup = CommonBlo.getGroupName(this.idGroup);
		this.startDate = CommonUtil.convertDateToStr(req.getStartDate());
		this.endDate = CommonUtil.convertDateToStr(req.getEndDate());
		this.process = req.getProcess();
		this.status = req.getStatus();
		this.lateDate = CommonUtil.getDateLate(CommonUtil.getSystemDate(), req.getEndDate());
		if (this.lateDate > 0){
			this.statusReq = Constant.STATUS_LATE;
		}
		else
		{
			this.lateDate = 0;
			this.statusReq = Constant.STATUS_GOOD;
		}
			
		if (this.process >= 0 && this.process <= 20) {
			this.stage = Constant.ANALYST;
		} else if (this.process >= 20 && this.process <= 40) {
			this.stage = Constant.DESIGN;
		} else if (this.process >= 40 && this.process <= 70) {
			this.stage = Constant.CODE;
		} else if (this.process >= 70 && this.process <= 90) {
			this.stage = Constant.TEST;
		} else {
			this.stage = Constant.RELEASE;
		}
	}
	
	
	public void clear(){
		id = null;
		nameReq = "";
		content = "";
		idProject = null;
		idGroup = null;
		startDate = null;
		endDate = null;
		process = 0;
		status = "";
		level = 0;
		lateDate = 0;
		statusReq = "";
		//set mode insert
		mode = Constant.MODE_INSERT;
	}
	/**
	 * @return the statusReq
	 */
	public String getStatusReq() {
		return statusReq;
	}
	/**
	 * @param statusReq the statusReq to set
	 */
	public void setStatusReq(String statusReq) {
		this.statusReq = statusReq;
	}
	/**
	 * @return the lateDate
	 */
	public int getLateDate() {
		return lateDate;
	}
	/**
	 * @param lateDate the lateDate to set
	 */
	public void setLateDate(int lateDate) {
		this.lateDate = lateDate;
	}
	/**
	 * @return the nameGroup
	 */
	public String getNameGroup() {
		return nameGroup;
	}
	/**
	 * @param nameGroup the nameGroup to set
	 */
	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}
	/**
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}
	/**
	 * @param stage the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}
}
