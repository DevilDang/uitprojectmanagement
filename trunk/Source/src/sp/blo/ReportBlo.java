package sp.blo;

import java.util.ArrayList;
import java.util.List;

import sp.dao.ReportDao;
import sp.dto.Report;
import sp.form.ReportForm;
public class ReportBlo {
	
	private static ReportDao reportDao = new ReportDao();

	public static boolean saveReport(Report report){
		return reportDao.saveReport(report);
	}
	
	public static boolean delete(Long id){
		return reportDao.deleteReport(id);
	}
	//get ReportList
	public static List<ReportForm> getListPage(Long idProject, Long idReq, Long idTask,int status, int page){
		
		List<ReportForm> formList = new ArrayList<ReportForm>();
		StringBuffer filter = new StringBuffer();
		filter.append("idProject=="+ idProject);
		filter.append("idReq=="+ idReq);
		filter.append("idTask=="+ idTask);
		filter.append("status=="+ status );
		List<Report> reportList = reportDao.getListReport(filter.toString(), page);
		
		if (reportList != null){
			int size = reportList.size();
			for (int i = 0; i< size; i++){
				//transfer to ReportForm
				ReportForm form = new ReportForm();
				form.eidtForm(reportList.get(i));
				formList.add(form);
			}
			
		}
		
		return formList;
	}
	
	/*
	 * tinh so page dua tren so record
	 */
	public static int countOrganizationAll() {
		
		return reportDao.countOrganizationAll();

	}
	
	/*
	 * get Form Report
	 */
	
	public static ReportForm getReportForm(Long id){
		
		//get Report
		Report report = reportDao.getReport(id);
		
		//get Form
		ReportForm form = new ReportForm();
		
		//transfer Report -> Form
		form.eidtForm(report);
		
		return form;
	}
}
