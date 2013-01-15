package sp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class Validation {

	/**
	 * compare dateValue with SysDate
	 * @param dateValue
	 * @return
	 * @throws ParseException
	 */
	public static boolean checkSysDate(String dateValue) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse(dateValue);
		dateFormat.format(date);
		Date sysDate = CommonUtil.getSystemDate();
		if (date.compareTo(sysDate)<0){
			return false;
		}
		return true;
	}
	
	/**
	 * @param dateValue1
	 * @param dateValue2
	 * @return
	 * @throws ParseException
	 */
	public static boolean checkValueDate(String dateValue1, String dateValue2) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse(dateValue1);
		Date date2 = dateFormat.parse(dateValue2);
		if (date1.compareTo(date2)>0){
			return false;
		}
		return true;
	}
	
	/**
	 * checkDate
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static ActionMessages checkDate(String mode,String startDate, String endDate) throws ParseException{
		
		ActionMessages errors = new ActionMessages();
		//mode insert
		if (Constant.MODE_INSERT.equals(mode)){
			
			if (!checkSysDate(startDate) || (!checkSysDate(endDate))){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.req.invalidDate"));
			}
			else
			{
				if (!checkValueDate(startDate, endDate)){
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.req.compareDate"));
				}
			}
		}
		else
		{
			if (!checkValueDate(startDate, endDate)){
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.req.compareDate"));
			}
		}
		
		
		return errors;
	}
	
}
