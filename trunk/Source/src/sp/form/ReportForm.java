package sp.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import sp.dto.Report;

public class ReportForm extends ValidatorForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title ;
	private Long idProject;
	private Long idReq;
	private Long idTask;
	private Long idGroup;
	private String idUser;
//	private String nameUser;// hien thi nameUser
	private Date createDate;
	private String comment;
	private String status;
	private String content;
	private String fileId;
	private String fileName;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the idTask
	 */
	public Long getIdTask() {
		return idTask;
	}
	/**
	 * @param idTask the idTask to set
	 */
	public void setIdTask(Long idTask) {
		this.idTask = idTask;
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
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	
	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
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
	public Report getReport(){
		
		Report report = new Report();
		report.setId(this.id);
		report.setTitle(title);
		report.setIdProject(this.idProject);
		report.setIdReq(this.idReq);
		report.setIdTask(this.idTask);
		report.setIdGroup(this.idGroup);
		report.setIdUser(this.idUser);
		report.setComment(this.comment);
		report.setContent(this.content);
		report.setCreateDate(this.createDate);
		report.setStatus(this.status);
		report.setFileId(this.fileId);
		report.setLevel(this.level);
		return report;
	}
	
	/*
	 * transfer DTO-> form
	 */
	public void eidtForm(Report report){
		this.id = report.getId();
		this.title = report.getTitle();
		this.idProject = report.getIdProject();
		this.idReq = report.getIdReq();
		this.idTask = report.getIdTask();
		this.idGroup = report.getIdGroup();
		this.idUser = report.getIdUser();
		this.comment = report.getComment();
		this.content = report.getContent();
		this.createDate = report.getCreateDate();
		this.status = report.getStatus();
		this.fileId = report.getFileId();
		this.level = report.getLevel();
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
