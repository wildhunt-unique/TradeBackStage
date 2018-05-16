package cn.edu.qtech.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取当前年月日信息
 * 
 * @author 丁星
 */
public class getDay {
	private String Str;
	private String Str2;
	private Date date = new Date();
	private Calendar cal = Calendar.getInstance();

	public getDay() {
		// TODO Auto-generated constructor stub
		cal.setTime(date);
		Str = (new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
		Str2 = (new SimpleDateFormat("yyyyMMddmmss").format(cal.getTime()));
	}

	/**
	 * 获取带格式的年月日信息
	 */
	public String getFormatDate() {
		return Str;
	}

	/**
	 * 获取不带格式的年月日信息
	 */
	public String getUnFormatDate() {
		return Str2;
	}
}