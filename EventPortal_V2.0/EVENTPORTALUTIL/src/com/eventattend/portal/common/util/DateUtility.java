package com.eventattend.portal.common.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtility {
	
	    public static void main(String[] args) {  
	  
	    	String strtDate = "Wed Jun 22 12:00:00 IST 2011";
	    	String endDate = "2011-06-22 13:00:00";
	    	
	    	String inputFormat = "EEE MMM dd hh:mm:ss z yyyy";
			String outputFormat = "yyyy-MM-dd hh:mm:ss";
			
	    	DateUtility dateUtility = new DateUtility();
	    	
	    	System.out.println("Days==>"+dateUtility.convertStringToDateFormat(endDate,outputFormat,inputFormat));
	    	
		 }

	    
	 
	    public int noOfDayss(String strDate, String enDate) {
	    	
	    	Date startDate = null;
	    	Date endDate = null;
	    	
	    	startDate = convertStringToUtilDate(strDate, "YYYY-MM-DD");
	    	endDate = convertStringToUtilDate(enDate, "YYYY-MM-DD");
	    	
			
	  	  Calendar cal1 = new GregorianCalendar();
	  	  Calendar cal2 = new GregorianCalendar();
	  	  cal1.set(startDate.getYear(), startDate.getMonth(), startDate.getDay());
	  	  cal2.set(endDate.getYear(), endDate.getMonth(), endDate.getDay());
	  	  int actualDays = daysBetween(cal1.getTime(), cal2.getTime());
	  	  return actualDays;
	  	 }
	    
	    /**
		 * @method - To find the days between two dates
		 * @param d1
		 * @param d2
		 * @return int
		 */
		public int daysBetween(Date d1, Date d2) {
			return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
		}
	    
	    
	    public String convertDateFormat(String inputDate, String inputFormat, String outputFormat){
	    	
	    	
	    	String tempSqlDate = null;
			
			java.sql.Date retSqlDate = null;
			java.util.Date utildate = null;
			
			SimpleDateFormat sdfInput = new SimpleDateFormat(inputFormat);
			SimpleDateFormat sdfOutput = new SimpleDateFormat(outputFormat);
			
			//SimpleDateFormat sdfInput = new SimpleDateFormat("dd/M/yy hh:mm a");
			//SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			try {
				utildate = sdfInput.parse(inputDate);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tempSqlDate = sdfOutput.format(utildate);
			//retSqlDate = java.sql.Date.valueOf(tempSqlDate);
			
			//System.out.println("O/P Date is ===>"+tempSqlDate);
	    	
	    	
	    	return tempSqlDate.toString();
	    }
	    
	    public Date convertStringToDateFormat(String inputDate, String inputFormat, String outputFormat){
	    	
	    	Date retUtilDate = null;
	    	String tempSqlDate = null;
			
			java.sql.Date retSqlDate = null;
			java.util.Date utildate = null;
			
			SimpleDateFormat sdfInput = new SimpleDateFormat(inputFormat);
			SimpleDateFormat sdfOutput = new SimpleDateFormat(outputFormat);
			
			//SimpleDateFormat sdfInput = new SimpleDateFormat("dd/M/yy hh:mm a");
			//SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			try {
				utildate = sdfInput.parse(inputDate);
				tempSqlDate = sdfOutput.format(utildate);
				retUtilDate = sdfOutput.parse(tempSqlDate);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			
			//retSqlDate = java.sql.Date.valueOf(tempSqlDate);
			
			//System.out.println("O/P Date is ===>"+tempSqlDate);
	    	
	    	
	    	return retUtilDate;
	    }
	    
	    /**
		    * 
		    * @param inputDate
		    * @param dateFormat
		    * @return
		    */
		    		
		    public  String currentServerTimeTZ(TimeZone givenTimeZone){
		    	String currentServerTimeTZ = null;
		    	SimpleDateFormat today = new SimpleDateFormat("EEEE, MMMM,dd,yyyy - hh:mm aaa - z");
		    	today.setTimeZone(givenTimeZone);
		    	currentServerTimeTZ =today.format(new java.util.Date());
		    	//System.out.println(">> "+currentServerTimeTZ);
		    	return currentServerTimeTZ;
		    }
	   /**
	    * 
	    * @param inputDate
	    * @param dateFormat
	    * @return
	    */
	    		
	    public String currentServerTimeTZ(){
	    	String currentServerTimeTZ = null;
	    	SimpleDateFormat today = new SimpleDateFormat("EEEE, MMMM,dd,yyyy - hh:mm aaa - z");	
	    	currentServerTimeTZ =today.format(new java.util.Date());
	    //	System.out.println(">> "+currentServerTimeTZ);
	    	return currentServerTimeTZ;
	    }
	    /**
		    * 
		    * @param inputDate
		    * @param dateFormat
		    * @return
		    */
		    		
		    public String currentServerTZ(){
		    	String currentServerTZ = null;
		    	SimpleDateFormat today = new SimpleDateFormat("z");	
		    	currentServerTZ =today.format(new java.util.Date());
		    	//System.out.println(">> "+currentServerTZ);
		    	return currentServerTZ;
		    }
	   public Date convertStringToUtilDate(String inputDate, String dateFormat){
		   
		   Date utilDate = null;
		   
		   DateFormat df = new SimpleDateFormat(dateFormat);
		   
		   try {
			   utilDate = df.parse(inputDate);            
	       //     System.out.println("Today = " + df.format(utilDate));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		   
		   
		   return utilDate;
		   
	   }
	   
	   /**
	    * Compare the input date with current date
	    * @param input date in java.util.Date format
	    * @return GREATER, LESSER or EQUAL based on the compared result
	    */
	   
	   public String compareWithToday(Date inputDate){
		
		   String currentStrDate = null; 
		   String retcompareType = null;
		   Date currentDate = null;
		   int compareState = 0;
		   
		   currentStrDate = retCurrentDate();
		   currentDate = convertStringToUtilDate(currentStrDate, "MM/dd/yyyy");
		   
		   compareState = inputDate.compareTo(currentDate);
		   
		   retcompareType = dateCompare(compareState);
		   
		   return retcompareType;
		   
	   }
	   
	   /**
	    * Compare two dates 
	    * @param firstDate in java.util.Date format
	    * @param secondDate in java.util.Date format
	    * @return GREATER, LESSER or EQUAL based on the compared result
	    */
	   
	   public String compareTwoDates(Date firstDate, Date secondDate){
		   
		   String retcompareType = null;
		   Date currentDate = null;
		   int compareState = 0;
		   
		   compareState = firstDate.compareTo(secondDate);
		   
		   retcompareType = dateCompare(compareState);
		   
		   return retcompareType;
		   
	   }
	   
	   
	   
	   public String dateCompare(int compareState){
		
		  String retcompareType = null;
		   
		   if(compareState > 0){
			   retcompareType = "GREATER";
		   }else if(compareState < 0){
			   retcompareType = "LESSER";
		   }else{
			   retcompareType = "EQUAL";
		   }
		   
		   return retcompareType;
		   
	   }
	   
	   long startMilli;
		long endMilli;
		public String timeTaken = null;
		Calendar c = Calendar.getInstance();
		
		public float timeTaken1 = 0;
		public float timeTaken2 = 0;
		private float count =0;
		NumberFormat nf = NumberFormat.getInstance();
	
		public java.sql.Date convertStringToSqlDate(String strDate) {
			
			String tempSqlDate = null;
			
			java.sql.Date retSqlDate = null;
			java.util.Date utildate = null;
			
			SimpleDateFormat sdfInput = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				utildate = sdfInput.parse(strDate);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tempSqlDate = sdfOutput.format(utildate);
			retSqlDate = java.sql.Date.valueOf(tempSqlDate);
			return retSqlDate;
		}
		
		
		
		public java.sql.Date convertStringToSqlDate1(String strDate) {
			
			String tempSqlDate = null;
			
			java.sql.Date retSqlDate = null;
			java.util.Date utildate = null;
			
			SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOutput = new SimpleDateFormat("MM/dd/yyyy");
			
			try {
				utildate = sdfInput.parse(strDate);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tempSqlDate = sdfOutput.format(utildate);
			retSqlDate = java.sql.Date.valueOf(tempSqlDate);
			return retSqlDate;
		}
		
		public String retCurrentTime(){
			
			String time = null;
			String hour = null;
			String minute = null;
			String second = null;
			
			
			Calendar c = Calendar.getInstance();
			
			int hr = c.get(Calendar.HOUR);
			int mn = c.get(Calendar.MINUTE);
			int sc = c.get(Calendar.SECOND);
			int am_pm = c.get(Calendar.AM_PM);
			
			if(hr < 10){			
				if(hr == 0){
					hour = "12";
				}else{
					hour = "0"+hr;
				}			
			}else{
				hour = String.valueOf(hr);
			}
			if(mn < 10){
				minute = "0"+mn;
			}else{
				minute = String.valueOf(mn);
			}
			
			time = hour+" : "+minute+" : "+sc;
			
			
			if(am_pm == 0){
				time = hour+":"+minute+"am";
			}else{
				time = hour+":"+minute+"pm";
			}
			
			
			return time;
			
		}
		
		public String retCurrentDate() {
			int currentYear = 0;
			int currentMonth = 0;
			String crntMon = null;
			String crntDay = null;
			int currentDay = 0;
			String currentDate = null;
			TimeZone timeZone = TimeZone.getDefault();
			Calendar currentCalValue = Calendar.getInstance(timeZone);
			currentYear = currentCalValue.get(Calendar.YEAR);
			currentMonth = currentCalValue.get(Calendar.MONTH) + 1;
			currentDay = currentCalValue.get(Calendar.DATE);
			crntMon = new Integer(currentMonth).toString();
			crntDay = new Integer(currentDay).toString();
			if (currentMonth < 10) {
				crntMon = "0" + currentMonth;
			}
			if (currentDay < 10) {
				crntDay = "0" + currentDay;
			}
			
			currentDate = crntMon + "/" + crntDay + "/" + currentYear;
			
			return currentDate;
		}
		
		public String retCurrentDateWithTime() {
			int currentYear = 0;
			int currentMonth = 0;
			String crntMon = null;
			String crntDay = null;
			int currentDay = 0;
			String currentDate = null;
			
			int hour = 0;
			int min = 0;
			int sec = 0;
			
			TimeZone timeZone = TimeZone.getDefault();
			Calendar currentCalValue = Calendar.getInstance(timeZone);
			currentYear = currentCalValue.get(Calendar.YEAR);
			currentMonth = currentCalValue.get(Calendar.MONTH) + 1;
			currentDay = currentCalValue.get(Calendar.DATE);
			
			hour = currentCalValue.get(Calendar.HOUR);
			min = currentCalValue.get(Calendar.MINUTE);
			sec = currentCalValue.get(Calendar.SECOND);
			
			crntMon = new Integer(currentMonth).toString();
			crntDay = new Integer(currentDay).toString();
			if (currentMonth < 10) {
				crntMon = "0" + currentMonth;
			}
			if (currentDay < 10) {
				crntDay = "0" + currentDay;
			}
			
			currentDate = currentMonth + "-" + currentDay + "-" +currentYear + "_" + hour + "-" + min + "-" + sec;
			
			return currentDate;
		}
		
		
		public String convertSqlDateToString(String inputDate)
		{
			String strDate = "";		
			String dateArray[] = inputDate.split("-");  
			
			if(!dateArray.equals(""))
			{
				strDate = dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];
			}
			return strDate;
		}
		
		
		public String convertSqlDateToString(Date retSqlDate) {
			
			String tempSqlDate = null;
			
			
			java.util.Date utildate = null;
			
			SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOutput = new SimpleDateFormat("MM/dd/yyyy");
			
			try {
				utildate = sdfInput.parse(retSqlDate.toString());
				tempSqlDate = sdfOutput.format(utildate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			
			return tempSqlDate;
		}
		
		public Timestamp convertStringToTimeStamp(String fieldData) {
			long value = 0;
			try {
				fieldData = fieldData.toUpperCase();
				int index = fieldData.indexOf("DAY");
				if (index == -1) {
					index = fieldData.indexOf("YEAR");
					if (index == -1) {
						index = fieldData.indexOf("MONTH");
						if (index == -1) {
							index = fieldData.indexOf("WEEK");
							value = Integer.parseInt(fieldData.substring(0, index));
							value = (value * 7 * 86400000);
						} else {
							value = Integer.parseInt(fieldData.substring(0, index));
							value = (value * 30 * 86400000);
						}
					} else {
						value = Integer.parseInt(fieldData.substring(0, index));
						value = (value * 365 * 86400000);
					}
				} else {
					value = Integer.parseInt(fieldData.substring(0, index));
					value = (value * 86400000);
				}
			} catch (Exception ex) {
			}
			Timestamp ts = new Timestamp(value);
			return ts;
		}
		
		public void startTime(){
			
			startMilli = System.currentTimeMillis();
			
			
		}
		
		public Timestamp retTimestampForDate(String inputDate){
			
			
			int dayDifference = calculateDaysDifference(convertStringToSqlDate(retCurrentDate()), convertStringToSqlDate(inputDate));
			
			long inpValue = dayDifference * 86400000;
			
			System.out.print("Time Value==>"+inpValue);
			
			
			Timestamp timestamp = new Timestamp(inpValue);
			
			return timestamp;
		}
		
		public void endTime(){
			
			endMilli = System.currentTimeMillis();
			
			long finalMilli = endMilli - startMilli;
			long finalSecond = finalMilli / 1000;
			finalMilli = finalMilli % 1000;
			long finalMinute = finalSecond / 60;
			finalSecond = finalSecond % 60;
			//timeTaken  =  "Minutes :"+finalMinute+" | Seconds :"+finalSecond+" |MilliSeconds: "+finalMilli;
			timeTaken  =  finalMinute+" Mnts :"+finalSecond+" Sec :"+finalMilli+" Msec";
			
			//System.out.print("\n\t\t\t\t\t\t<< Total time taken for this process >> Minutes :"+finalMinute+" | Seconds :"+finalSecond+" |MilliSeconds: "+finalMilli);
			
		}
		public int  calculateDaysDifference(Date currentDate, Date futureDate)
		{
			int tempDifference = 0;
			int difference = 0;
			Calendar todayDate = Calendar.getInstance();
			Calendar later = Calendar.getInstance();
			
			if (currentDate.compareTo(futureDate) < 0)
			{
				todayDate.setTime(currentDate);
				later.setTime(futureDate);
			}
			else
			{
				todayDate.setTime(futureDate);
				later.setTime(currentDate);
			}
			
			while (todayDate.get(Calendar.YEAR) != later.get(Calendar.YEAR))
			{
				
				tempDifference = 365 * (later.get(Calendar.YEAR) - todayDate.get(Calendar.YEAR));
				difference += tempDifference;
				
				todayDate.add(Calendar.DAY_OF_YEAR, tempDifference);
			}
			
			if (todayDate.get(Calendar.DAY_OF_MONTH) != later.get(Calendar.DAY_OF_MONTH))
			{
				tempDifference = later.get(Calendar.DAY_OF_MONTH) - todayDate.get(Calendar.DAY_OF_MONTH);
				difference += tempDifference;
				
				todayDate.add(Calendar.DAY_OF_MONTH, tempDifference);
			}
			if (todayDate.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR))
			{
				tempDifference = later.get(Calendar.DAY_OF_YEAR) - todayDate.get(Calendar.DAY_OF_YEAR);
				difference += tempDifference;
				
				todayDate.add(Calendar.DAY_OF_YEAR, tempDifference);
			}
			if (todayDate.get(Calendar.WEEK_OF_YEAR) != later.get(Calendar.WEEK_OF_YEAR))
			{
				tempDifference = later.get(Calendar.WEEK_OF_YEAR) - todayDate.get(Calendar.WEEK_OF_YEAR);
				difference += tempDifference;
				
				todayDate.add(Calendar.WEEK_OF_YEAR, tempDifference);
			}
			
			//System.out.println("Validity Days Left"+difference);
			
			return difference;
		}
		//To check date is valid By Suresh on sep 25 2008
		public boolean validateDate(String theDate) {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date d;
			
			try {
				df.setLenient(false);
				d = df.parse(theDate);
			} catch (ParseException e) {
				return false;
			}
			return true;
		}
		//To calculate time By Suresh on sep 25 2008
		/*public String endTimeNew(long finalMilli)
		{ 	
			float second=(float)finalMilli/60000;
			int milliseconds = (int)(finalMilli % 1000);
			int seconds = (int)((finalMilli/1000) % 60);
			int minutes = (int)((finalMilli/60000) % 60);
			int hours = (int)((finalMilli/3600000) % 24);
			String millisecondsStr = (milliseconds<10 ? "00" : (milliseconds<100 ? "0" : ""))+milliseconds;
			String secondsStr = (seconds<10 ? "0" : "")+seconds;
			String minutesStr = (minutes<10 ? "0" : "")+minutes;
			String hoursStr = (hours<10 ? "0" : "")+hours;
			nf.setMaximumFractionDigits(2);
			timeTaken  =  minutesStr+" Mts :"+secondsStr+" Sec :"+millisecondsStr+" Msec";
			System.out.println(timeTaken);
			return timeTaken;
		}*/
	    //To calculate time By Suresh on sep 25 2008
		public String endTimeLoad(long finalMilli)
		{ 	
			float second=(float)finalMilli/60000;
			int milliseconds = (int)(finalMilli % 1000);
			int seconds = (int)((finalMilli/1000) % 60);
			int minutes = (int)((finalMilli/60000) % 60);
			int hours = (int)((finalMilli/3600000) % 24);
			String millisecondsStr = (milliseconds<10 ? "00" : (milliseconds<100 ? "0" : ""))+milliseconds;
			String secondsStr = (seconds<10 ? "0" : "")+seconds;
			String minutesStr = (minutes<10 ? "0" : "")+minutes;
			String hoursStr = (hours<10 ? "0" : "")+hours;
			nf.setMaximumFractionDigits(2);
			timeTaken  =  minutesStr+" Mts :"+secondsStr+" Sec :"+millisecondsStr+" Msec";
			return timeTaken;
		}
		/** to convert the date format  
		 * @method convertDateFormat
		 * @param strDate
		 * @return String
		 */
		public String convertDateFormat(String strDate) {
			String day = null;
			String month = null;
			String year = null;
			String convertedDate = null;
			StringTokenizer st = new StringTokenizer(strDate, "-");
			return convertedDate = strDate;
		}
		/** to seperate the date
		 * @method convertDateSeperator
		 * @param strDate
		 * @return String
		 */
		
		public String convertDateSeperator(String strDate) {
			String str = "";
			StringTokenizer st = new StringTokenizer(strDate, " ");
			str=st.nextToken();
			return str;
		}
		

		public static String  sessionCommentTimeCheck(String commentDate,String userTimeZone){		
		   // String commentTime = "2011-03-29 09:30:000";		
		   // String systemTime = "2011-03-30 09:35:000";
			DateUtility dateUtility = new DateUtility();
			String timeString = null;
			String commentTime = commentDate;
			TimeZone userTimeZ = TimeZone.getTimeZone(userTimeZone);		
			SimpleDateFormat dateFormatWithTimeZOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
			dateFormatWithTimeZOne.setTimeZone(userTimeZ);			
		    
			String systemTime = dateFormatWithTimeZOne.format(new java.util.Date());
			commentTime = dateFormatWithTimeZOne.format(dateUtility.convertStringToUtilDate(commentDate, "yyyy-MM-dd HH:mm:ss"));// comment time in user time zone
			
		   // System.out.println("systemTime converted with respect to user Time Zone: >>"+systemTime); 
		    int commentTimeYear = Integer.parseInt(commentTime.substring(0, 4));
		    int commentTimeMonth = Integer.parseInt(commentTime.substring(5, 7));
		    int commentTimeDate = Integer.parseInt(commentTime.substring(8, 10));
			int commentTimeHour = Integer.parseInt(commentTime.substring(11, 13));
			int commentTimeMinute = Integer.parseInt(commentTime.substring(14, 16));
			int systemTimeYear = Integer.parseInt(systemTime.substring(0, 4));
		    int systemTimeMonth = Integer.parseInt(systemTime.substring(5, 7));
		    int systemTimeDate = Integer.parseInt(systemTime.substring(8, 10));
			int systemTimeHour = Integer.parseInt(systemTime.substring(11, 13));
			int systemTimeMinute = Integer.parseInt(systemTime.substring(14, 16));	
					
		    Calendar calendar1 = Calendar.getInstance();
		    Calendar calendar2 = Calendar.getInstance();
		    calendar1.set(commentTimeYear, commentTimeMonth, commentTimeDate);
		    calendar2.set(systemTimeYear, systemTimeMonth, systemTimeDate);
		    long milliseconds1 = calendar1.getTimeInMillis();
		    long milliseconds2 = calendar2.getTimeInMillis();
		    long diff = milliseconds2 - milliseconds1;	    
		    long diffDays = diff / (24 * 60 * 60 * 1000);
		   
		    long diffHour = systemTimeHour-commentTimeHour;
		    long diffMinute = systemTimeMinute-commentTimeMinute;	    
		    long signCheck = isPositive(diffMinute);
		    
		    if(signCheck!=0){
		    	diffHour = diffHour-1;
		    	diffMinute = 60-(-diffMinute);
		    }
		    
//		    if(diffHour < 0){
//		    	diffHour = diffHour * -1;
//		    }
		    
		  /*  System.out.println("Time in days: " + diffDays 	+ " days.");
		    System.out.println("Time in hours: " + diffHour 	+ " hours.");
		    System.out.println("Time in minutes: " + diffMinute 	+ " minutes.");*/
		    
		    if(diffDays!=0){	    		    
		    	if(diffHour==0){
		    		timeString = diffDays+" days "+diffMinute+" mins ago";
		    	}else{
		    		timeString = diffDays+" days "+ diffHour+" hrs "+diffMinute+" mins ago";
		    	}
		    }else{
		    	if(diffHour==0){
		    		timeString = diffMinute+" mins ago";
		    	}else{
		    		timeString = diffHour+" hr "+diffMinute+" mins ago";
		    	}	    	
		    }    	    
		    return timeString;
		  }

	    public static long  isPositive(long i){
	    	return i & 0x8000000000000000L;        
	    }
	    
	 
/**
 * @method-To convert the date to user Time Zone -[o/p Ex:2011-04-07 22:50:48] 
 * @param givenDate
 * @param givenTimeZone
 * @return String
 */
public  String convertDateToGivenTimeZone(String givenDate,String givenTimeZone){
	TimeZone givenTimeZ = null;
    DateFormat formatter = null; 
    DateFormat dateFormatter = null; 
    Date date =null;     
    String dateString = null;			
         try {
        	givenTimeZ = TimeZone.getTimeZone(givenTimeZone);
        	formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        	
			date = (Date)formatter.parse(givenDate);
			formatter.setTimeZone(givenTimeZ);			
			dateString=formatter.format(date );					 
		//	System.out.println("dateString " +dateString);
		} catch (ParseException e) {		
			e.printStackTrace();
		}    
          return dateString;
}

/**
 * @method-To convert the date to user Time Zone -[o/p Ex: 6.00A.M/7.00P.M]
 * @param givenDate
 * @param givenTimeZone
 * @return String
 */
public  String convertDateToGivenTime(String givenDate,String givenTimeZone){
	TimeZone givenTimeZ = null;
    DateFormat formatter = null; 
    DateFormat dateFormatter = null; 
    Date date =null;     
    String dateString = null;		
         try {
        	givenTimeZ = TimeZone.getTimeZone(givenTimeZone);
        	formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	dateFormatter = DateFormat.getTimeInstance(DateFormat.SHORT);	
			Timestamp timestamp = null;			
			date = (Date)formatter.parse(givenDate);
			formatter.setTimeZone(givenTimeZ);					
			dateString=formatter.format(date );	
			dateFormatter.setTimeZone(givenTimeZ);	
			timestamp = new Timestamp(date.getTime());		
			dateString= dateFormatter.format(timestamp);
			//System.out.println("A.M/P.M >> " +dateString);
			
		} catch (ParseException e) {	
			e.printStackTrace();
		}    
          return dateString;
}
public  String getTodaysDateInUserTimeZone(String userTimeZone){
	String todayDateTz = null;
	TimeZone tz=TimeZone.getTimeZone(userTimeZone);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	format.setTimeZone(tz);
	todayDateTz = format.format(new java.util.Date());
	return todayDateTz;
}

public  String getTodaysDateInServerTimeZone(){
	String todayDateTz = null;
	//TimeZone tz=TimeZone.getTimeZone(userTimeZone);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//format.setTimeZone(tz);
	todayDateTz = format.format(new java.util.Date());
	return todayDateTz;
}

}