package sp.util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
	
	/*
	 * tinh so page dua tren so record
	 */
	public static List<String> countOrganizationAll(int count) {
		
		List<String> pageList = new ArrayList<String>();
		int page = 0;
		if (count > 0) {
			page = count / Constant.RECORD;
			if (count % Constant.RECORD > 0) {
				page = page + 1;
			}
			page = page + 1;
			for (int i = 1; i < page; i++) {
				pageList.add(String.valueOf(i));
			}
		}
		return pageList;

	}

}
