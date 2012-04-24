package com.eventattend.portal.db4o.uniquekeygenerator;

public class UniqueKeyGenerator {
	private static PlatformIndependentGuidGen platformIndependentGuidGen = PlatformIndependentGuidGen.getInstance();
	public static String generateUniqueKey(){

	return platformIndependentGuidGen.genNewGuid(); 

	}

}
