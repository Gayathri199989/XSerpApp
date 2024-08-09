package appUtility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertyFile {
	protected static Properties prop = null;
	
	 public ReadPropertyFile() throws Exception{
		 File file = new File("C:/config.PROPERTIES");
		 FileInputStream objfile = new FileInputStream(file);
		 prop = new Properties();
		 prop.load(objfile);
	
	    }

	 public String getAppLocation(){
		 return prop.getProperty("XSerp_AppLocation");
		  
	 }
	 
	 public String getAppPackage(){
		 return prop.getProperty("XSerp_AppPackage");
		  
	 }
	 
	 public  String getAppActivity(){
		 return prop.getProperty("XSerp_AppActivity");
	 }
	 
	 public  String getTestDataPath_CatchUp(){
		 return prop.getProperty("Path_TestData_CatchUp");
	 }
	 
	 public String getScreenshotpath(){
		 return prop.getProperty("ScreenshotPath");
	 }
	 
	 public String getTestResultpath(){
		 return prop.getProperty("Path_TestResult");
	 }
	 
	 public String getLogFilePath(){
		 return prop.getProperty("Path_LogFile");
	 }
	 
	 public String getExtentReportFile(){
		 return prop.getProperty("ExtentReportFile");
	 }
	 
	 public String getExtentReportImg(){
		 return prop.getProperty("ExtentReportImage");
	 }
	 
	 public String getJunitReport(){
		 return prop.getProperty("JunitReport");
	 }
	 
	 public String getDeviceName(){
		 return prop.getProperty("XSerp_DeviceName");
	 }
}
