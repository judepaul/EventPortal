
/*
 * Copyright 2006-2007 Vision Tech Solutions,(pvt) Ltd. All Rights Reserved.
 * This software is the confidential and proprietary information of
 * Vision Tech Solutions. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into 
 * with Vision Tech Solutions.
 */
package com.eventattend.portal.common.util;
/**
 * Action class for HibernateUtil 
 * @version 1.0
 * @author gsundarishree
 * @Date 25-04-2009
 */
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

public class Conversion {
	
/****************************************************************************************************************
 *  					Converting Object to other data types
 *****************************************************************************************************************/
	
	/**
	 * Convert Object to long
	 */
	public long convertObjectToLong(Object object){
		long longValue=0;
		String tempString=null;
		if(object!=null){
			tempString=object.toString();
			longValue=Long.parseLong(tempString);
		}
		return longValue;
		}
	public String decimalFormat(Double d){
		DecimalFormat dFormat = new DecimalFormat("#.###");
		String result = dFormat.format(d);
		return result;
	}
	/**
	 * Convert Object to int
	 * @param object
	 * @return
	 */
	public int convertObjectToInt(Object object){
		int intValue=0;
		String tempString=null;
		if(object!=null){
			tempString=object.toString();			
			intValue=Integer.parseInt(tempString);
		}
		return intValue;
		}
	
	
	public Integer convertObjectToInteger(Object object){
		Integer IntegerValue=null;		
		IntegerValue=(Integer)object;
		return IntegerValue;
		}
	
	
	/**
	 * Convert Object to SQL Date
	 * @param object
	 * @return
	 */
	public Date convertObjectToSqlDate(Object object){
		Date dateValue=null;
		String tempString=null;
		tempString=object.toString();
		dateValue=convertStringToSqlDateFormat(tempString,"MM/dd/yyyy","yyyy-MM-dd");
		return dateValue;
		}
	/**
	 * Convert Object to Boolean
	 * @param object
	 * @return
	 */
	public boolean convertObjectToBoolean(Object object){
		boolean boolValue;
		String str=object.toString();
		Boolean bool=new Boolean(str);
		boolValue=bool.booleanValue();
		return boolValue;
		}
	
	
	/**
	 * Convert Object to float
	 * @param object
	 * @return
	 */
	public float convertObjectToFloat(Object object){
		float floatValue=0;
		String tempString=null;
		tempString=object.toString();
		floatValue=Float.valueOf(tempString).floatValue();
		return floatValue;
		}
	
	/**
	 * Convert Object to Double
	 * @param object
	 * @return
	 */
	public double convertStringToDouble(String string){
		double doubleValue=0;	
		doubleValue=Double.valueOf(string).doubleValue();
		return doubleValue;
		}
	
	/**
	 * Convert Object to float
	 * @param object
	 * @return
	 */
	public float convertStringToFloat(String string){
		float floatValue=0;	
		floatValue=Float.valueOf(string).floatValue();
		return floatValue;
		}
	
	/**	
	 * Convert Object to String 
	 * @param object
	 * @return
	 */	
	public	String convertObjectToString(Object object){
		String strValue=null;
		if(object!=null){
			strValue=String.valueOf(object);
			}
		return strValue;
		}
	

	/**
	 * Convert Object to String Array
	 * @param objArray
	 * @return
	 */
	public String[] convertObjectToStringArray(Object[] objArray){
		String[] strArray=new String[objArray.length];
		for(int i=0;i < objArray.length;i++){
	 		strArray[i]=objArray[i].toString();
			}
		return strArray;
	}
	


	/**************************************************************************************************************
	 * 							Converting String to other types
	 **************************************************************************************************************/
	
	/**
	 * Converting String to int
	 * @param strValue
	 * @return
	 */
	public int convertStringToInt(String strValue){
		StringUtility stringUtility=new StringUtility();
		int intValue=0;
		
		if(stringUtility.isValidString(strValue)){			
			intValue= Integer.parseInt(strValue);
			}
		return intValue;
		}
	

	/**
	 * Converting String to Integer
	 * @param strValue
	 * @return
	 */
	public Integer convertStringToInteger(String strValue){
		StringUtility stringUtility=new StringUtility();
		Integer integerValue=null;
		
		if(stringUtility.isValidString(strValue)){
		    integerValue= Integer.valueOf(strValue);
			}
		return integerValue;
		}
	
	/**
	 * Converting String to long
	 * @param strValue
	 * @return
	 */
	public long convertStringToLong(String strValue){		
		StringUtility stringUtility=new StringUtility();
		long longValue=0;
		
		if(stringUtility.isValidString(strValue)){			
			longValue= Long.parseLong(strValue);
			}
		return longValue;
		}
	
	/**
	 * Converting String to UtilDate 
	 * @param strDate
	 * @return
	 */	
	@SuppressWarnings("unused")
	public java.util.Date convertStringToUtilDate(String strDate){
		/** Return Date value */
		java.util.Date retUtilDate = null;

		/** Input format */
		SimpleDateFormat sdfInput = null;

		/** Output format */
		SimpleDateFormat sdfOutput = null;

		/**Util Date value */
		java.util.Date utilDate = null;

		/** Sql Date value */
		String tempSqlDate = null;

		/** Defining the date formats for input and output*/
		sdfInput = new SimpleDateFormat("MM/dd/yyyy");
		sdfOutput = new SimpleDateFormat("MM/dd/yyyy");

		try {
			if (strDate != null) {
				/** Converting the date type*/
				utilDate = sdfInput.parse(strDate);			
			}
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			//e.printStackTrace();
		}
		return utilDate;
	}
	
	
	/**
	 * Converting String to SqlDate
	 * @param strDate
	 * @param inputFormat
	 * @param outputFormat
	 * @return
	 */
	public java.sql.Date convertStringToSqlDateFormat(String strDate, String inputFormat,String outputFormat){
		
		/** Return Date value */
		java.sql.Date retSqlDate=null;
		
		/** Input format */
		SimpleDateFormat sdfInput=null;
		
		/** Output format */
		SimpleDateFormat sdfOutput=null;
		
		/**Util Date value */
		java.util.Date utilDate=null;
		
		/** Sql Date value */
		String tempSqlDate = null;
		
		/** Defining the date formats for input and output*/
		sdfInput= new SimpleDateFormat(inputFormat);
		sdfOutput= new SimpleDateFormat(outputFormat);
		SimpleDateFormat sdf = new SimpleDateFormat();
	    try {
	    	if(strDate!=null){	 
	    		/** Converting the date type*/
	    		utilDate = sdfInput.parse(strDate);
	    		tempSqlDate = sdfOutput.format(utilDate);	
	    		retSqlDate = java.sql.Date.valueOf(tempSqlDate);
	    		
//	    		utilDate = sdfInput.parse(strDate); 
//	    		tempSqlDate  = sdfOutput.format(utilDate);	
//	    		Date returnDate = sdf.parse(tempSqlDate);
	    		
	    	}
	     }
	    catch (Exception e) {
	     System.out.println(e.getLocalizedMessage());	
	     // e.printStackTrace();
	    }
	    return retSqlDate;
	  }
	
	
	/* added by sabeer */
public java.sql.Date convertStringToSqlDateFormat1(String strDate){
		
		/** Return Date value */
		java.sql.Date retSqlDate=null;
		
		/** Input format */
		SimpleDateFormat sdfInput=null;
		
		/** Output format */
		SimpleDateFormat sdfOutput=null;
		
		/**Util Date value */
		java.util.Date utilDate=null;
		
		/** Sql Date value */
		String tempSqlDate = null;
		
		/** Input Format for Date*/		
		String inputFormat="MM/dd/yyyy";
		
		
		/** Output Format for Date*/
		String outputFormat="MM/dd/yy";
		
		/** Defining the date formats for input and output*/
		sdfInput= new SimpleDateFormat(inputFormat);
		sdfOutput= new SimpleDateFormat(outputFormat);
	    try {
	    	if(strDate!=null){	 
	    		/** Converting the date type*/
	    		utilDate = sdfInput.parse(strDate);
	    		tempSqlDate = sdfOutput.format(utilDate);	
	    		retSqlDate = java.sql.Date.valueOf(tempSqlDate);
	    	}
	     }
	    catch (Exception e) {
	     System.out.println(e.getLocalizedMessage());	
	      e.printStackTrace();
	    }
	    return retSqlDate;
	  }
	
	
	/**
	 * Converting String to SqlDate
	 * @param strDate
	 * @param inputFormat
	 * @param outputFormat
	 * @return
	 */
	public java.sql.Date convertStringToSqlDateFormat(String strDate){
		
		/** Return Date value */
		java.sql.Date retSqlDate=null;
		
		/** Input format */
		SimpleDateFormat sdfInput=null;
		
		/** Output format */
		SimpleDateFormat sdfOutput=null;
		
		/**Util Date value */
		java.util.Date utilDate=null;
		
		/** Sql Date value */
		String tempSqlDate = null;
		
		/** Input Format for Date*/		
		String inputFormat="MM/dd/yyyy";
		
		
		/** Output Format for Date*/
		String outputFormat="yyyy-MM-dd";
		
		/** Defining the date formats for input and output*/
		sdfInput= new SimpleDateFormat(inputFormat);
		sdfOutput= new SimpleDateFormat(outputFormat);
	    try {
	    	if(strDate!=null){	 
	    		/** Converting the date type*/
	    		utilDate = sdfInput.parse(strDate);
	    		tempSqlDate = sdfOutput.format(utilDate);	
	    		retSqlDate = java.sql.Date.valueOf(tempSqlDate);
	    	}
	     }
	    catch (Exception e) {
	     System.out.println(e.getLocalizedMessage());	
	      e.printStackTrace();
	    }
	    return retSqlDate;
	  }


	/**
	 * Converting String to TimeStamp 
	 * @param fieldData
	 * @return
	 */	
	public Timestamp convertStringToTimeStamp(String strValue)	{
		long value = 0;
		StringUtility stringUtility=new StringUtility(); 
		if(stringUtility.isValidString(strValue)){
		try{
			strValue = strValue.toUpperCase();
			int index = strValue.indexOf("DAY");
			if (index == -1){
				index = strValue.indexOf("YEAR");
				if (index == -1){
					index = strValue.indexOf("MONTH");
					if(index==-1){				
						index = strValue.indexOf("WEEK");		
						value = Integer.parseInt(strValue.substring(0,index));
						value = (value * 7 * 86400000);				
					}else{
					value = Integer.parseInt(strValue.substring(0,index));
					value = (value * 30 * 86400000);
					}
				}else{
					value = Integer.parseInt(strValue.substring(0,index));
					value = (value * 365 * 86400000);
				}
			}else{
				value = Integer.parseInt(strValue.substring(0,index));
				value = (value * 86400000);
			}
		}catch(Exception ex){
			//ex.printStackTrace();
		}
	}// End of If
	
	/** Converting value to Timestamp*/
	Timestamp ts=new Timestamp(value);
	return ts;
	}
	
	public int convertStingToInt(String strValue){
		StringUtility stringUtility=new StringUtility();
		int intValue=0;
		if(stringUtility.isValidString(strValue)){
			intValue=Integer.parseInt(strValue);
		}
		return intValue;
		}
	
	
	/**
	 * Formatting field value for String 
	 * @param value
	 * @return
	 */
	public String formatFieldValue(String value){
		StringUtility stringUtility=new StringUtility();
		if(stringUtility.isValidString(value)){
			value="'" + value + "'";
			}
		return value;
	}
	/**
	 * Formatting field value for String 
	 * @param value
	 * @return
	 */
	public String formatFieldValue(Date dateValue){
		String value=null;
		if(dateValue!=null){
			value =dateValue.toString();
			StringUtility stringUtility=new StringUtility();
			if(stringUtility.isValidString(value)){
				value="'" + value + "'";
				}
		}
		return value;
		
	}
	
	/**
	 * Formatting field value for String 
	 * @param value
	 * @return
	 */
	public String formatLikeFieldValue(String value){
		StringUtility stringUtility=new StringUtility();
		if(stringUtility.isValidString(value)){
			value="'%" + value + "%'";
			}
		return value;
	}

	public String formatFieldValue(long longValue){
		String strValue=null;
		if(longValue==0){
			strValue=null;
			}else{
			strValue=String.valueOf(longValue);	
				}
				
		return strValue;
	}


	/*****************************************************************************************************
	 * 								Convert Collection to other data types
	****************************************************************************************************/

	/**
	 * Converting Collection to List
	 */	
	@SuppressWarnings("unchecked")
	public List convertCollectionToList(Collection collection){
		List list=new ArrayList();
		Iterator iterator;
		Object object;
		if(collection!=null){
			iterator=collection.iterator();
			while(iterator.hasNext()){
				object=(Object)iterator.next();
				list.add(object);
				}
			}
		return list;
		}
	
	/**
	 * Convert Collection to Array List
	 * @param collection
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList collectionToArrayList(Collection collection) {
		/**Iterator for Employee Details*/
		Iterator iterator;
	
		/**ArrayList of Employee*/
		ArrayList list = new ArrayList();
	
		if (collection != null) {
			iterator = collection.iterator();
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
		}
		return list;
	}
	
	/*****************************************************************************************************
	 * 								Convert Long to other data types
	****************************************************************************************************/

	/**
	 * Converting Long to String
	 * @param newParam TODO
	 */
	public String convertLongToString(long lngValue){
		String str=null;
		if(lngValue!= 0)
		{
	        str =String.valueOf(lngValue);
	   	}
		return str;
	}
	
	/**
	 * Converting Long to int 
	 * @param longValue
	 * @return
	 */
	public int convertLongToInt(long longValue){
		int intVal=(int)longValue;
		return intVal;
		}
	
	
	/**
	 * Converting Long to int 
	 * @param longValue
	 * @return
	 */
	public Integer convertLongToInteger(long longValue){
		Integer integerVal=null;
		if(longValue!=0){
			String strValue=convertLongToString(longValue);
			
			if(strValue!=null){
					integerVal=Integer.valueOf(strValue);
			}else{
				integerVal=null;
				}
		}else{
			integerVal=new Integer(0);
			}
		return integerVal;
		}
	
	
	
	/*****************************************************************************************************
	 * 								Convert boolean to other data types
	****************************************************************************************************/
	
	/**
	 * To Convert boolean value to Object using String
	 * @param booleanValue
	 * @return
	 */
	
	public Object convertBooleanToObject(boolean booleanValue){
		Object object;
		object=String.valueOf(booleanValue);
		return object;
		}

	/*****************************************************************************************************
	 * 								Convert Array to other data types
	****************************************************************************************************/
	/**
	 * To Convert Array to Map
	 * @param stringArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map convertArrayToMap(String[] stringArray){
		Map map=new HashMap();
		for(int i=0; i <= stringArray.length-1 ; i++ ){
			if ((stringArray[i]!=null) && !(stringArray[i].equals(""))){
				map.put(stringArray[i],stringArray[i]);
			}
		}
		return map;
		}
	
	
	/*****************************************************************************************************
	 * 								Convert Int to other data types
	****************************************************************************************************/
	
	public String convertIntToString(int intValue){
		String strValue=null;
		strValue=String.valueOf(intValue);
		return strValue;
		}
	/**
	 * To convert Int To Integer
	 * @param intValue
	 * @return
	 */
	public Integer convertIntToInteger(int intValue){
		Integer integerValue=new Integer(intValue);
		return integerValue;
		}
	/**
	 * To convert Integer to Int
	 * @param integerValue
	 * @return
	 */
	public int convertIntegerToInt(Integer integerValue){	    
	    int intValue=0;
	    if(integerValue!=null){
	        intValue=integerValue.intValue();
	    }
		return intValue;
		}
	/**
	 * To convert Integer to String
	 * @param integerValue
	 * @return
	 */
	public String convertIntegerToString(Integer integerValue){	    
	    String strValue=null;
	    if(integerValue!=null){
	        strValue=integerValue.toString();
	    }
		return strValue;
		}
	/**
	 * To convert Sql Date to String
	 * @param inputDate
	 * @return
	 */
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
	/**
	 * To convert to Date Format
	 * @param strDate
	 * @return
	 */
	public String convertDateFormat(String strDate) {
		String day = null;
		String month = null;
		String year = null;
		String convertedDate = null;

		StringTokenizer st = new StringTokenizer(strDate, "/");
		if(st!=null){	
			while (st.hasMoreTokens()) {
				month = st.nextToken();
				day = st.nextToken();
				year = st.nextToken();
	
				convertedDate = year + "/" + month + "/" + day;
				//System.out.println("CONVERTED DATE==" + convertedDate);
			}
		}
		return convertedDate;
	}
	
	
	
    /**
     * To parse SQL date format
     * @param strDate
     * @return
     */
	public java.sql.Date parseSqlDateFormat(String strDate) 
	{
		java.sql.Date retSqlDate=null;
		
		SimpleDateFormat sdfInput=null;
		
		SimpleDateFormat sdfOutput=null;
		
		
		java.util.Date utilDate=null;
		
		String tempSqlDate = null;
		
		sdfInput= new SimpleDateFormat("yyyy-mm-dd");
	    sdfOutput= new SimpleDateFormat("mm/dd/yyyy");
	    
	    try {
	    	if(strDate!=null){	 
	    	    		utilDate = sdfInput.parse(strDate.toString());
	    		tempSqlDate = sdfOutput.format(utilDate);
	    		retSqlDate = java.sql.Date.valueOf(tempSqlDate);
	    	}
	     }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retSqlDate;
	  }
}


