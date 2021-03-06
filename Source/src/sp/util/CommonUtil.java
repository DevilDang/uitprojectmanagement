package sp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;

public class CommonUtil {
	
	/*
	 * tinh so page dua tren so record
	 */
	public static List<String> createPageList(int count) {
		
		List<String> pageList = new ArrayList<String>();
		int page = 1;
		if (count > 0) {
			page = count / Constant.RECORD;
			if (count % Constant.RECORD > 0) {
				page = page + 1;
			}
		}
		for (int i = 1; i <= page; i++) {
			pageList.add(String.valueOf(i));
		}
		return pageList;

	}
	
	/*
	 * get maxpage
	 */
	public static int countPageList(int count) {
		
		int page = 1;
		if (count > 0) {
			page = count / Constant.RECORD;
			if (count % Constant.RECORD > 0) {
				page = page + 1;
			}
//			page = page + 1;
			}
		return page;
	}

	
	
	
	public static long convertToLong(String value){
		long result = 0;
		if (value != null && !Constant.BLANK.equals(value)){
			result = Long.valueOf(value);
		}
		return result;
	}
	
	public static int convertToInt(String value){
		int result = 0;
		if (value != null && !Constant.BLANK.equals(value)){
			result = Integer.valueOf(value);
		}
		return result;
	}

	/*
	 * get current Date
	 */
	public static Date getSystemDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		dateFormat.format(date);
		return date;
	}
	
	/*
	 * get reportName
	 */
	
	public static String getReportName(String id){
		BlobKey blobKey = new BlobKey(id);
        BlobInfoFactory bi = new BlobInfoFactory();
        String fname = bi.loadBlobInfo(blobKey).getFilename();
        return fname;
	}
	
	
	public static Date convertStrToDate(String date){
		if (date != null && !"".equals(date)){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date today = df.parse(date);
            return today;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
	}
	return null;
	}
	
	public static String convertDateToStr(Date date){
		if (date != null){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String s = formatter.format(date);
		return s;
		}
		return null;
	}
	
	public static String convertNVLFor(String value){
		if (value == null){
			return Constant.BLANK;
		}
		else
		{
			return value;
		}
	}
	
	/**
	 * get date late : endDate - startDate
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDateLate(Date sysDate, Date endDate){
		
		int result = 0;
		long dateDiff = sysDate.getTime() - endDate.getTime();
		result = (int) (dateDiff/(1000 * 60 * 60 * 24));
		return result;
	}
	
	
}
