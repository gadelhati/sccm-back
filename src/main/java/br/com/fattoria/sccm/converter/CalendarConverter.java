package br.com.fattoria.sccm.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarConverter {
	
	public static Calendar parseToCalendar(final String value, final String pattern) {
		if(value != null && !value.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			
			try {
				Date parse = sdf.parse(value);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(parse);
				return calendar;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
	
		} else {
			return null;
		}
	}
	
	public static String parseToString(final Calendar value, final String pattern) {
		if(value != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String parse = sdf.format(value.getTime());
			return parse;
		} else {
			return null;
		}
	}

}
