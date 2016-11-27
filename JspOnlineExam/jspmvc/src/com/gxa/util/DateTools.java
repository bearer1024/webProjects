package com.gxa.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateTools {
	
	 /**
       * 日期格式化为yyyy-MM-dd  也可设置为 yyyy-MM-dd HH:mm:ss
       * @return
       */
	  public String getCurrentDate()
	    {
		    Date  da = new Date(System.currentTimeMillis());
	        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	        return sf.format(da);
	    }
	  public String getCurretYearMonth(){
		  Date date = new Date(System.currentTimeMillis());
		  SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		  return sf.format(date);
	  }
	 
	  public String getCurrentTime()
	    {
		    Date  da = new Date(System.currentTimeMillis());
	        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        return sf.format(da);
	    }
	  /**
	   * 格式化的日期格式为yyyy-MM-dd HH:mm
	   * @param date
	   * @return
	   */
	  public String formatDatetostring(Date date){
		  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 return sf.format(date);
	  }
	  /**
	   * 格式化的日期格式为yyyy-MM-dd HH:mm
	   * @param date
	   * @return
	   */
	  public String formatDatetostringDay(Date date){
		  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		 return sf.format(date);
	  }
	  
	  /**
	   * 字符串类型的日期转化为util包的Date类型的日期
	   * @param date
	   * @return
	   */
	  public Date formatStringtoDate(String date){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  Date datetime=new Date();
		  try {
		  datetime=sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return datetime;
	  }
	  
	  public Date formatStringtoDate2(String date){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Date datetime=new Date();
		  try {
		  datetime=sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return datetime;
	  }	  
	  
	  /**
	   * 将字串转化为日期格式
	   * modified by 廖先毅
	   */
	  public static Date convertStringtoDate(String date){
		  SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  SimpleDateFormat sdfCalendar = new SimpleDateFormat("yyyy-MM-dd");
		  Date datetime=new Date();
		  try {
				if(date.length()>10){
					datetime = sdfDate.parse(date);
					
				}else{
					datetime = sdfCalendar.parse(date);
				}
				
		 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return datetime;
	  }
	  
	  /**
	   * 将字串转化为日期格式
	   * modified by 廖先毅
	   */
	  public static Date convertStringtoDate(String date,String mask){
		  SimpleDateFormat sdf = new SimpleDateFormat(mask);
		  Date datetime=new Date();
		  try {
		  datetime=sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return datetime;
	  }
	  
	  /**
	   * 将日期转化为字串格式
	   * modified by 廖先毅
	   */
	  public static String convertDateToString(Date date,String mask){
		  SimpleDateFormat sdf = new SimpleDateFormat(mask);

		  return sdf.format(date);
				
	  }	  
	  
	
	  /**
	   * @param days 获得指定天数后的日期，负数表示多少天前的日期
	   * @param isStart true表示是起始日期，需要将时分秒置为0,false不改变时分秒
	   * @return 当前毫秒数
	   */
	  public Date getDateAfter(int days,boolean isStart){
		  Calendar calendar = new GregorianCalendar(); 
		  calendar.add(Calendar.DAY_OF_YEAR, days);
		  if (isStart){
			  int year = calendar.get(Calendar.YEAR);
		      int month = calendar.get(Calendar.MONTH);
		      int day = calendar.get(Calendar.DAY_OF_MONTH);
		      calendar.set(year, month, day, 0, 0, 0);
		  }
		  return calendar.getTime();
	  }
	  
}
