package com.eventattend.portal.db4o.uniquekeygenerator;

import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

public class PlatformIndependentGuidGen {
	
	  private static PlatformIndependentGuidGen _TheInstance = null;
	  private Random _RandomForSpace = null;
	  private Random _RandomForTime = null;
	 
	  /**
	   * Note: the first time you call this function it is very expensive
	   * @return PlatformIndependentGuidGen the instace of the Guid generator
	   */
	  public static synchronized PlatformIndependentGuidGen getInstance()
	  {
	    if (_TheInstance == null)
	      _TheInstance = new PlatformIndependentGuidGen();
	 
	    return _TheInstance;
	  }
	 
	  /**
	   *
	   * @return String a new Guid
	   */
	  public String genNewGuid()
	  {
	    long uniqueInSpace = _RandomForSpace.nextLong();
	    long uniqueInTime = _RandomForTime.nextLong();
	 
	    return Long.toHexString(uniqueInSpace) +
	        "-" + Long.toHexString(uniqueInTime);
	  }
	 
	 
	  private PlatformIndependentGuidGen()
	  {
	    long uniqueSpaceSeed = createUniqueSpaceSeed();
	    long uniqueTimeSeed = System.currentTimeMillis();
	 
	    _RandomForSpace = new Random(uniqueSpaceSeed);
	    _RandomForTime = new Random(uniqueTimeSeed);
	  }
	 
	  /**
	   * Does the best job it can to produce a number that is dependent on the machine
	   * on which this code is running
	   * @return long
	   */
	  private long createUniqueSpaceSeed()
	  {
	    String hashingString = ""; //Contains the conglomeration of as much machine or
	                               //or config dependant stuff as I could come up with
	 
	    Properties systemProps = System.getProperties();
	    Enumeration systemPropKeys = systemProps.keys();
	    String systemPropKey = null;
	    Locale[] locales = Locale.getAvailableLocales();
	    int iCounter = 0;
	    Runtime runtime = Runtime.getRuntime();
	    InetAddress myAddress = null;
	 
	    //Loop through all the system properties and add them to the hashingString
	    while(systemPropKeys.hasMoreElements())
	    {
	      systemPropKey = (String) systemPropKeys.nextElement();
	 
	      //Append the system prop to the hashing string
	      hashingString = hashingString + " " + systemPropKey + " = " +
	          systemProps.getProperty(systemPropKey) + "\n";
	      	
	 
	    }
	 
	    //Do a hardware dependent test and add the result to the hashingString
	    hashingString = hashingString + "Cycle Test = " +
	        cpuCycleTest() + "\n";
	 
	    //Loop through all the locales that are installed and add them to the hashingString
	    for (iCounter = 0; iCounter < locales.length; iCounter++)
	    {
	      hashingString = hashingString +
	          locales[iCounter].getCountry() + " " +
	          locales[iCounter].getDisplayCountry() + " " +
	          locales[iCounter].getDisplayName() + "\n";
	      
	    }
	 
	    //Get as much machine dependent stuff out of the runtime as
	    //you can and add them to the hashingString
	    hashingString = hashingString + "Available proc = " +
	        runtime.availableProcessors() + "\n";
	   
	    hashingString = hashingString + "free memory = " +
	        runtime.freeMemory() + "\n";
	    
	    hashingString = hashingString + "max memory = " +
	        runtime.maxMemory() + "\n";
	   
	    hashingString = hashingString + "total memory = " +
	        runtime.totalMemory() + "\n";
	  
	    //try to get my IP address and add to the hashingString
	    try
	    {
	      myAddress = InetAddress.getLocalHost();
	      hashingString = hashingString + "my IP address = " +
	          myAddress.toString();
	   
	    }
	    catch(Exception e)
	    {
	      //Do nothing
	    }
	 
	    //System.out.println(hashingString);
	 
	    return hashingString.hashCode();
	  }
	 
	  /**
	   * how high can we count in 10 msec
	   * @return long
	   */
	  private long cpuCycleTest()
	  {
	    long returnValue = 0;
	    long startTime = System.currentTimeMillis();
	    long rightNow = System.currentTimeMillis();
	 
	    //Do this for thirty millisec
	    while( (rightNow - startTime) < 10 &&
	           returnValue < (Long.MAX_VALUE - 10) )
	    {
	      returnValue++;
	      rightNow = System.currentTimeMillis();
	    }
	 
	    return returnValue;
	  }
}
