package sp.dao;

import java.util.List;

import sp.dto.Organization;
import sp.dto.Report;

public class ReportDao {
	
	//insert or update
	public boolean saveReport(Report report){
		return PMF.insertObject(report);
	}

	//delete a report
	public boolean deleteReport(Long id){
		return PMF.deleteObject(Report.class, id);
	}
	
	//getReport
	public Report getReport(Long id){
		return (Report)PMF.getObject(Report.class, id);
	}
	
	//getList page
	@SuppressWarnings("unchecked")
	public List<Report> getListReport(String filter, int page){
		return (List<Report>)PMF.getObjectList(Report.class, filter, page);
	}
	
	/*
	 * count number all 
	 */
	public int countOrganizationAll() {
		return PMF.countNumberAll(Organization.class);

	}
	
}
