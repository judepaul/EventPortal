package com.eventattend.portal.web.util;

public class ApplicationConfig {
	public static String baseUrlPath = "";
	public static String mailPath = "";
	
	public static String appConfigFilePath = "";

	public static String getBaseUrlPath() {
		return baseUrlPath;
	}
	public static void setBaseUrlPath(String baseUrlPath) {
		ApplicationConfig.baseUrlPath = baseUrlPath;
	}
	public static String getMailPath() {
		return mailPath;
	}
	public static void setMailPath(String mailPath) {
		ApplicationConfig.mailPath = mailPath;
	}
	public static String getAppConfigFilePath() {
		return appConfigFilePath;
	}
	public static void setAppConfigFilePath(String appConfigFilePath) {
		ApplicationConfig.appConfigFilePath = appConfigFilePath;
	}

	
}
