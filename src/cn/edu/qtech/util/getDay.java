package cn.edu.qtech.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ��ȡ��ǰ��������Ϣ
 * 
 * @author ����
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
	 * ��ȡ����ʽ����������Ϣ
	 */
	public String getFormatDate() {
		return Str;
	}

	/**
	 * ��ȡ������ʽ����������Ϣ
	 */
	public String getUnFormatDate() {
		return Str2;
	}
}