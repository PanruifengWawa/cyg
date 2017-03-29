package com.cyg.utils;

public class UseTimeUtil {
	public static String parse(String useTimeId) {
		String result = "";
		String[] times = {"08:00-10:00","10:00-12:00","12:00-13:30","13:30-15:00","15:00-17:00","17:00-18:30","18:30-20:00","20:00-22:00"};
		try {
			String[] ids = useTimeId.split(",");
			for(String id: ids) {
				Integer temp = Integer.parseInt(id);
				result += times[temp-1] + ",";
			}
			result = result.substring(0, result.length() - 1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return result;
		
	}
	
	public static boolean checkConfict(String[] useTimeId,String checkedUseTimeId) {
		boolean flag = false;
		if (useTimeId != null && checkedUseTimeId != null) {
			for(String id: useTimeId) {
				if (checkedUseTimeId.contains(id)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

}
