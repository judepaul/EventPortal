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
 * Action class for StringUtility
 * @author mmanimaran
 * @version 1.0
 * @Date 30-04-2009
 */
public class StringUtility {
	
	
	public long convertStringToLong(String strValue){
		long longValue=0;
		if(strValue!=null){
			
			if(strValue != ""){
			longValue= Long.valueOf(strValue).longValue();
			}
			}
		return longValue;
		}
	
	public void sop(Object o){
		
		System.out.println("To check---->"+o);
		
	}
	
	public boolean isValidString(String strValue){
		boolean result=false;
		if(strValue!=null){
			if(strValue.length()>0){
				if(!strValue.equalsIgnoreCase("null")){
					result=true;
					}
				}
			}
		return result;
		}
	
	
	public boolean isValidObject(Object obj){
		boolean result=false;
		if(obj!=null){
		    	result=true;
			}
		return result;
		}
	
	
	
	
	public String formatSearchString(String strValue){
		String formattedString=null;
		if (isValidString(strValue)){
			formattedString=strValue.toUpperCase();
		}
		return formattedString;
		}

	public String toSentenceCase(String strValue){
		if(isValidString(strValue)){
			/** Convert the given String in to Lower case */
			String lowerCaseValue=strValue.substring(1).toLowerCase();
			
			/** Change the first Letter as upper case*/
			char replChar[]=strValue.substring(0,1).toUpperCase().toCharArray();
			
			strValue=replChar[0]+lowerCaseValue;
		}	
		return strValue;
			
	}
	
	
	/**
	 * Method that returns the formatted employee Name along with employee Id 
	 * for display purpose ex: Diamler Chrys..(123234)
	 * @param empId - employee id
	 * @param empName - employee name
	 * @param maxChar - maximum characters to display
	 * @return String - the formatted output string
	 */
	public String formatEmpIdName(String empId, String empName, int maxChar) {

		String empIdName = null;
		String tempStr = null;
		
		int noOfChars = 0;
		int empIdChars = 0;
		int reqChars = 0;

		empIdName = empName + "(" + empId + ")";

		noOfChars = empIdName.length();

		if (noOfChars > maxChar) {

			empIdChars = String.valueOf(empId).length();

			reqChars = maxChar - empIdChars - 4;

			tempStr = empIdName.substring(0, reqChars);

			empIdName = tempStr + ".." + "(" + empId + ")";

		}

		return empIdName;
	}
	
	
	public String messageExtraction(String msg){
		
		String s1[] = msg.split(" ");
		String extractedMsg = null;
		String s="error";
		for(int i=0;i<s1.length;i++)
		{	
			if(s1[i].length()>5){
				if(s.equals(s1[i].substring(0,5))){
					extractedMsg = s1[i];
					System.out.println(s1[i]);
				}
			}
		}

		return extractedMsg;
	}

	public String fileExtension(String inputFileName) {

		int dotIndex = 0;
		String fileExtension = "";

		dotIndex = inputFileName.lastIndexOf('.');

		if (dotIndex > 0 && dotIndex < inputFileName.length() - 1) {
			fileExtension = inputFileName.substring(dotIndex + 1).toLowerCase();
		}
		return fileExtension;
	}
	
	
	public String escapesquence(String escape){
		
	
		for (int i = 0; i < escape.length(); i++){
			
		char c = escape.charAt(i);
		sop("char"+c);
		if (c == '\"') {
			sop("in esc seq"+escape);
			escape = escape.replace(c,'\"');
			escape = escape.trim();
			System.out.println("\""+escape+"");
			
		}
		else{
			System.out.println(escape);
			
		}
			}				
		return escape;
		}
	
	public String appendEscapeCharacter(String inputStr){
		
		StringBuffer strBuf = new StringBuffer();
		String finalStr = null;
		
		int strIndex = inputStr.indexOf("\"");
		
		if(strIndex != -1){
			
			strBuf = strBuf.append(inputStr);
			//strBuf.insert(strIndex,"\\");
			finalStr = strBuf.toString();
			sop("finalStr"+finalStr);
			
		}
		return finalStr;
	
		}
	//added by suresh for converting string to firstletter caps on sep 28 2008
	public static String toTitleCase(String inputStr) {
		char[] chars = inputStr.trim().toLowerCase().toCharArray();
		boolean found = false;
	 
		for (int i=0; i<chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i])) {
				found = false;
			}

		}
	 
		return String.valueOf(chars);
		}
	/**
	 * To find missing fields in load data
	 * @method findingMissingfields
	 * @param String[],String[]
	 * @return StringBuffer
	 */
	public static StringBuffer findingMissingfields(String[] loadDataFields,String[] loadDatas){
		StringBuffer errorMessage=new StringBuffer();
		for(int iteratorValue=0;iteratorValue<loadDataFields.length;iteratorValue++){
			if(loadDataFields[iteratorValue]==null||loadDataFields[iteratorValue].equals("")||loadDataFields[iteratorValue].equals(" ")){
				int missingField=iteratorValue+1;
				errorMessage.append(loadDatas[missingField-1]+" has no value~");
			}
		}
		return errorMessage;
	}
	/**
	 * To check loading status
	 * @method loadStatus
	 * @param StringBuffer
	 * @return boolean
	 */
	public static boolean loadStatus(StringBuffer errorMessage){
		if(errorMessage.length()>0){
			return false;
		}else{
		return true;
		}
	}
	/**
	 * To find indefinite length of a record
	 * @method findIndefiniteLength
	 * @param String,String,String,integer
	 * @return StringBuffer
	 */
	public static StringBuffer findIndefiniteLength(String originalLength,String expectedLength,String indefiniteLegth,int lineCount){
		StringBuffer errorMessage=new StringBuffer();
		if(Integer.parseInt(originalLength)>Integer.parseInt(expectedLength)){
			errorMessage.append("Record No."+new Integer(lineCount)+" has Indefinite Length("+indefiniteLegth+")~");
		}
		return errorMessage;
	}
	/**
	 * To find indefinite length of a record
	 * @method findIndefiniteLength
	 * @param String,String,String,integer
	 * @return StringBuffer
	 */
	public static boolean checkingSpecialchar(String pfsNodeId){
		char special[]={'~','!','@','#','%','^','&','*','(',')','|','{','}','[',']','<','>','?','/',',','.',';',':','_'};
		boolean invalidStatus=true;
		for(int i=0;i<pfsNodeId.length();i++){
			for(int j=0;j<special.length;j++){
				if(pfsNodeId.charAt(i)==special[j]){
					invalidStatus = false;		
				}
			}
		}
		return invalidStatus;
	}
	
	public String addComma(String InputString) {
		String emptyString = "";
		String outputString = "";
		StringBuffer buffer = null;
		int l = InputString.length();
		int j = 0;
		for (int k = l; k <= l; k--) {
			if (k != 0) {
				j++;
				if (j == 3) {
					j = 0;
					emptyString = emptyString + InputString.charAt(k - 1);
					if (k != 1) {
						emptyString = emptyString + ",";
					}
				} else {
					emptyString = emptyString + InputString.charAt(k - 1);
				}
			} else {
				break;
			}
		}
		buffer = new StringBuffer(emptyString);
		buffer = buffer.reverse();
		outputString = buffer.toString();
		return outputString;
	}
	
	//Event Attend Used methods
	
	/**
	 * Method that returns the short description of a given string
	 * it identifies the first space character and returns the
	 * first part of string as a short description
	 * @param inputString - the given input string	
	 * @return String - the short description of the given input
	 */
	public String retShortDescFirstIndex(String inputString) {

		String resultStr = null;
		int firstIndex = 0;

		if (inputString != null) {
			firstIndex = inputString.indexOf(" ");

			if (firstIndex != -1) {
				resultStr = inputString.substring(0, firstIndex);
			}else{
				resultStr = inputString;
			}
		} else {
			resultStr = inputString;
		}
		return resultStr;
	}
	
	/**
	 * Method that returns the short description of a given string
	 * it identifies the last space character and returns the
	 * first part of string as a short description
	 * @param inputString - the given input string	
	 * @return String - the short description of the given input
	 */
	public String retShortDescLastIndex(String inputString) {

		String resultStr = null;
		int lastIndex = 0;

		if (inputString != null) {
			lastIndex = inputString.lastIndexOf(" ");

			if (lastIndex != -1) {
				resultStr = inputString.substring(0, lastIndex);
			}else{
				resultStr = inputString;
			}
		} else {
			resultStr = inputString;
		}
		return resultStr;
	}
	
	
	public String retShortDescription(String inputString){
		
		String resultStr = null;
		
		int index = 150;

		if (inputString != null) {
				
				if(inputString.length() > index){
					resultStr = inputString.substring(0, index);
				}else{
					resultStr = inputString;
				}
				
				
				resultStr = retShortDescLastIndex(resultStr);
			
		} else {
			
			resultStr = inputString;
		
		}
		
		System.out.println("Short Desc==>"+resultStr);
		
		return resultStr;
	}
	
	
	public static String shrinkName(String str){
		String shrinkStr = null;
		if(str.length()>=15){
			shrinkStr = str.substring(0, 15);
			shrinkStr=shrinkStr+"...";
		}else{
			shrinkStr = str;
		}
		System.out.println("<Event name shrink>>>  "+shrinkStr);
		return shrinkStr;
	}
	
}
