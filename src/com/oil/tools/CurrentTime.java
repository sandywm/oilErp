package com.oil.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CurrentTime {
	/**
	 * 获取当前日期（格式yyyy-MM-dd HH:mm:ss）
	 * @description
	 * @author wm
	 * @date 2015-10-17 上午10:49:02
	 * @return
	 */
	public static  String getCurrentTime(){
		return getFormat("yyyy-MM-dd HH:mm:ss");
	}
	//返回字符串作为文件名
	public static  String getStringTime(){
		return getFormat("yyyyMMddHHmmss");
	}
	public static String getStringTime1(){
		return getFormat("yyyyMMddHHmmssSSS");
	}
	/**
	 * 获取当前日期（格式yyyy-MM-dd）
	 * @description
	 * @author wm
	 * @date 2015-10-17 上午10:49:24
	 * @return
	 */
	public static String getStringDate(){
		return getFormat("yyyy-MM-dd");
	}
	public static String getYear(){
		return getFormat("yyyy");
	}
	public static String getMonth(){
		return getFormat("MM");
	}
	public static String getSimpleTime(){
		return getFormat("yyyyMMdd");
	}
	//数字左补0
	public static String getNumberFormat(double d){
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(12);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(12);
		return nf.format(d);
	}
	public static Timestamp getCurrentTime1(){
		java.util.Date date=new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		return time;
	}
	//timstamp转换成string
	public static String convertTimestampToString(Timestamp ts){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String tsStr = "";
		try{
	        tsStr = formatter.format(ts);   
	    } catch (Exception e) {   
	        e.printStackTrace();   
	    }  
	    return tsStr;
	}
	/**
	 * timstamp转换成string(yyyy-MM-dd)
	 * @description
	 * @author wm
	 * @date 2015-10-17 上午09:22:14
	 * @param ts
	 * @return
	 */
	public static String convertTimestampToString_1(Timestamp ts){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String tsStr = "";
		try{
	        tsStr = formatter.format(ts);   
	    } catch (Exception e) {   
	        e.printStackTrace();   
	    }  
	    return tsStr;
	}
	//string转换成timestamp
	public static Timestamp stringConvertToTimestamp(String value){
		Timestamp ts = null;
		try {  
            ts = Timestamp.valueOf(value);   
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return ts;  
	}
	/**
	 * string转换成date(java.sql.Date)
	 * @description
	 * @author wm
	 * @date 2018-6-4 上午08:25:52
	 * @param value
	 * @return
	 */
	public static java.sql.Date stringToDate(String value){
		java.util.Date d = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		try {  
            d = formatter.parse(value);
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
	}
	
	/**
	 * string转换成date（java.util.Date）
	 * @description
	 * @author wm
	 * @date 2018-7-25 上午09:12:58
	 * @param value
	 * @return
	 */
	public static Date stringToDate_1(String value){
		java.util.Date d = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		try {  
            d = formatter.parse(value);
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Date date = new Date(d.getTime());
        return date;
	}
	
	/**
	 * date转换成string
	 * @description
	 * @author wm
	 * @date 2018-6-13 下午03:40:37
	 * @param date
	 * @return
	 */
	public static String dateConvertToString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String tsStr = "";
		if(date != null){
			try{
		        tsStr = formatter.format(date);   
		    } catch (Exception e) {   
		        e.printStackTrace();   
		    }  
		}
	    return tsStr;
	}
	
	//系统日期+天数
	public static String getFinalDate(int days){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + days);
        Date date = calendar.getTime();
        return formatter.format(date);
	}
	//系统日期+天数
	public static String getFinalDateTime(int days){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + days);
        Date date = calendar.getTime();
        return formatter.format(date);
	}
	//工具方法
	private static String getFormat(String format)
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(currentTime);
	}
	
	/**
	 * 指定日期+/-天数
	 * @description
	 * @author wm
	 * @date 2018-9-12 上午11:30:01
	 * @param specifiedDate
	 * @param days
	 * @return
	 */
	public static String getFinalDate(String specifiedDate,int days){
		String finalDate = "";
        try {
        	Calendar calendar = Calendar.getInstance();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDate);
			calendar.setTime(date);
			int day = calendar.get(Calendar.DAY_OF_YEAR);
			calendar.set(Calendar.DAY_OF_YEAR, day + days);
			finalDate =  new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalDate;
	}
	
	/**
	 * 指定日期加月数
	 * @description
	 * @author Administrator
	 * @date 2018-10-12 上午10:08:22
	 * @param specifiedDate
	 * @param month
	 * @return
	 */
	public static String getFinalDate_1(String specifiedDate,int addMonthes){
		String finalDate = "";
		Calendar calendar = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(specifiedDate);
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, addMonthes);
			finalDate =  sdf.format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return finalDate;
	}
	
	/**
	 * 指定日期+年数
	 * @description
	 * @author Administrator
	 * @date 2018-10-25 上午09:13:28
	 * @param specifiedDate
	 * @param addMonthes
	 * @return
	 */
	public static String getFinalDate_2(String specifiedDate,int addYears){
		String finalDate = "";
		Calendar calendar = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(specifiedDate);
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, addYears);
			finalDate =  sdf.format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return finalDate;
	}
	
	/**
	 * 日期相减相差天数
	 * @description
	 * @author wm
	 * @date 2016-8-4 下午02:12:56
	 * @param oldDate
	 * @param newDate
	 * @return
	 */
	public static int compareDate(String oldDate,String newDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date benginDate;
		Date currentDate;
		int diffDays = 0;
		try {
			benginDate = formatter.parse(oldDate);
			currentDate = formatter.parse(newDate);
			long difference = currentDate.getTime() - benginDate.getTime();
	  		diffDays =  Math.round(difference / (1000 * 60 * 60 * 24));
	  		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diffDays;
	}
	//日期相减相差毫秒数
	public static long compareDateTime(String currentTime,String addTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date addDate;
		Date currentDate;
		long difference = 0;
		try {
			addDate = formatter.parse(addTime);
			currentDate = formatter.parse(currentTime);
			difference = currentDate.getTime() - addDate.getTime();
	  		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return difference;
	}
	//日期相减相差年/月数
	public static int comparaDate(String currentDate,String oldDate,String dateType){
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar bef = Calendar.getInstance();		
		Calendar aft = Calendar.getInstance();
		try {
			bef.setTime(sdf.parse(oldDate));
			aft.setTime(sdf.parse(currentDate));
			if(dateType.equals("year")){
				result = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);	
			}else{
				result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return result;
	}
    

    /**
     * 本月的第一天
     * @param sYear 年份
     * @param sMonth 月份
     * @return
     */
    public static  String getFirstDayofMonth(int sYear, int sMonth) {
    	 Calendar c = Calendar.getInstance();
        String tStartdate = "";
        c.set(c.YEAR, sYear);
        c.set(c.MONTH, sMonth);
        tStartdate = String.valueOf(sYear) + "-" + String.valueOf(sMonth) + "-" + c.getActualMinimum(c.DAY_OF_MONTH);
        return tStartdate;
    }

    /**
     * 本月的最后一天
     * @param sYear 年份
     * @param sMonth 月份
     * @return
     */
    public static String getEndDayofMonth(int sYear, int sMonth) {
    	Calendar c = Calendar.getInstance();
        String tEnddate = "";
        c.set(c.YEAR, sYear);
        c.set(c.MONTH, sMonth);
        tEnddate = String.valueOf(sYear) + "-" + String.valueOf(sMonth) + "-" + c.getActualMaximum(c.DAY_OF_MONTH);
        return tEnddate;
    }

    public static String getNewTime(int i){  

    	String newDateTime = null;
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar cal = new GregorianCalendar();  
        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();

        cal.setTime(date);  

        cal.add(12, i);  

        
        newDateTime = formatter.format(cal.getTime());

        return newDateTime;  

    }  
    /**
     * 
     * @description 获取上个月的第一天
     * @author zong
     * @date 2015-8-27 上午10:13:17
     * @return 返回上个月的第一天
     */
    public static String getMonthOrOne(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
	    return format.format(calendar.getTime());
    }
    /**
     * @description 获取上一个月的最后一天
     * @author zong
     * @date 2015-8-27 下午04:08:50
     * @return返回上个月的最后一天
     */
    public static String getMonthOrLast(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.add(Calendar.DATE, -1);
	    return format.format(calendar.getTime());
    }
    /**
     * @author zong
     * @return 本月的第一天
     */
    public static String getBeginDate(){
        Calendar begin = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        begin.set(Calendar.DAY_OF_MONTH, begin.getActualMinimum(Calendar.DAY_OF_MONTH));
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        
        return sdf.format(begin.getTime());
    }
    /**
     * @author zong
     * @return 本月的最后一天
     */
    public static String getEndDate(){
        Calendar end = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        
        return sdf.format(end.getTime());
    }
    
    /**
     * 获取本月的第一天
     * @description
     * @author wm
     * @date 2018-7-13 上午10:42:40
     * @return
     */
    public static String getBeginDate_1(){
        Calendar begin = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        begin.set(Calendar.DAY_OF_MONTH, begin.getActualMinimum(Calendar.DAY_OF_MONTH));
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        
        return sdf.format(begin.getTime());
    }
    
    /**
     * 获取本月的最后一天
     * @description
     * @author wm
     * @date 2018-7-13 上午10:43:06
     * @return
     */
    public static String getEndDate_1(){
        Calendar end = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        
        return sdf.format(end.getTime());
    }
    
    /**
     * 获取指定日期的第一天和最后一天
     * @param specDate（格式yyyy-mm）
     * @return
     */
    public static String getFirstEndDay(String specDate){
    	int year = Integer.parseInt(specDate.split("-")[0]);  //年
		int month = Integer.parseInt(specDate.split("-")[1]); //月
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastStr = sdf.format(cal.getTime());
		
		int firstDay = cal.getActualMinimum(Calendar.DATE);
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		String firstStr = sdf.format(cal.getTime());
		
    	return firstStr + ":" + lastStr;
    }
    
    /**
     * 获取指定月份天数
     * @param specDate
     * @return
     */
    public static Integer getMaxDays(String specDate){
    	int year = Integer.parseInt(specDate.split("-")[0]);  //年
		int month = Integer.parseInt(specDate.split("-")[1]); //月
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DATE);
    	return lastDay;
    }
    
    /**
     * 根据指定日期获取每隔3个月的时间
     * 2015-12-11至2016-03-10:2016-03-11至2016-06-10:2016-06-11至2016-09-10:2016-09-11至2016-12-10
     * @description
     * @author wm
     * @date 2015-12-11 下午04:59:15
     * @return
     * @throws Exception
     */
    public static String getStepSpecialDate(String spec) throws Exception{
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String dateArray = "";
		Date now = format1.parse(spec);
		calendar.setTime(now);
		for(int i = 1; i < 5 ; i++){
			if(i == 1){
				calendar.add(Calendar.MONTH, 3);
				calendar.add(Calendar.DAY_OF_YEAR,-1);
				String dateStr2 = format1.format(calendar.getTime());
				dateArray = spec + "~"+ dateStr2;
			}else{
				calendar.add(Calendar.DAY_OF_YEAR,+1);
				String dateStr3 = format1.format(calendar.getTime());
				calendar.add(Calendar.MONTH, 3);
				calendar.add(Calendar.DAY_OF_YEAR,-1);
				String dateStr4 = format1.format(calendar.getTime());
				dateArray += ":"+dateStr3 + "~"+ dateStr4;
			}
		}
		return dateArray;
    }
    
    /**
     * 指定时间加减月数
     * @author wm
     * @date 2019-5-26 上午09:17:23
     * @param specDate
     * @param diffMonth
     * @return
     * @throws Exception 
     */
    public static String getSpecNewDate(String specDate,Integer diffMonth) throws Exception{
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		Date now = format1.parse(specDate);
		calendar.setTime(now);
		calendar.add(Calendar.MONTH, diffMonth);
    	return format1.format(calendar.getTime());
    }
    
    /**
     * 指定日期加减月数
     * @param specDate
     * @param diffMonth
     * @return
     * @throws Exception
     */
    public static String getSpecNewDate_1(String specDate,Integer diffMonth) throws Exception{
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date now = format1.parse(specDate);
		calendar.setTime(now);
		calendar.add(Calendar.MONTH, diffMonth);
    	return format1.format(calendar.getTime());
    }
    
    /**
     * 获取随机数（分+秒+毫秒）
     * @description
     * @author Administrator
     * @date 2018-10-9 下午04:42:50
     * @return
     */
    public static String getRadomTime(){
    	Integer max = 100,min = 10;
    	String radomStr = String.valueOf(Math.round(Math.random()*(max-min)+min));//两位随机数
    	return getFormat("mm")+getFormat("ss")+getFormat("SSS")+radomStr;
    }
    
    /**
     * 将########日期转换成####-##-##
     * @description
     * @author Administrator
     * @date 2018-9-22 上午09:56:01
     * @param specStr
     * @return
     */
    public static String convertFormatDate(String specStr){
    	if(!specStr.equals("") && specStr.length() == 8){//########
    		return specStr.substring(0, 4) + "-" + specStr.substring(4, 6) + "-" + specStr.substring(6, 8);
    	}
    	return "";
    }
    
    /**
     * 判断是否是合法的yyyy-MM日期格式
     * @author wm
     * @date 2019-5-15 下午04:12:03
     * @param str
     * @return
     */
    public static boolean checkValidDate(String str){
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM"); //这里的时间格式根据自己需求更改（注意：格式区分大小写、格式区分大小写、格式区分大小写） 
		try{ 
			//设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
			formatter.setLenient(false);
			Date date = (Date)formatter.parse(str); 
			return str.equals(formatter.format(date)); 
		}catch(Exception e){ 
			return false; 
		} 
    }
    
    /**
     * 判断时候是合法的yyyy--MM-dd日期格式
     * @param str
     * @return
     */
    public static boolean checkValidDate_1(String str){
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //这里的时间格式根据自己需求更改（注意：格式区分大小写、格式区分大小写、格式区分大小写） 
		try{ 
			//设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
			formatter.setLenient(false);
			Date date = (Date)formatter.parse(str); 
			return str.equals(formatter.format(date)); 
		}catch(Exception e){ 
			return false; 
		} 
    }
    
	public static void main(String args[]) throws Exception{
//		System.out.println(CurrentTime.stringConvertToTimestamp("2019-03-01 00:00:00"));
		
		System.out.println(CurrentTime.dateConvertToString(CurrentTime.stringToDate_1("2019-1-1")));
	}
}
