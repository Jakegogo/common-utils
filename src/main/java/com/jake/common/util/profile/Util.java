package com.jake.common.util.profile;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

public class Util {

	public static Date dateAdd(Date theDate, int addHours, int addMinutes, int addSecond) {
		if (theDate == null) {
			return null;
		}
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(theDate);
	
		cal.add(HOUR_OF_DAY, addHours);
		cal.add(MINUTE, addMinutes);
		cal.add(SECOND, addSecond);
	
		return cal.getTime();
	}

}
