package appUtility;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.offset.PointOption;

public class Constant {
//	private static final String FileUtils = null;
	public static AndroidDriver driver;
	static DesiredCapabilities capabilities = new DesiredCapabilities();
	  static AppiumDriverLocalService appiumService;
	    static String appiumServiceUrl;
	public static long Min_Sleep = 5000;
	
	public static long Medium_Sleep = 12000;
	
	public static long Max_Sleep = 15000;
	
	 static Dimension size;
	//Log initialization
	public static Logger log = Logger.getLogger("Constant");
	
	public static void AppSetting() throws Exception{
		ReadPropertyFile property = PageFactory.initElements(driver, ReadPropertyFile.class);
//		 appiumServiceUrl = appiumService.getUrl().toString();
//		 System.out.println("Appium Service Address : - "+ appiumServiceUrl);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", property.getAppLocation());
		capabilities.setCapability("appPackage", property.getAppPackage());
		capabilities.setCapability("appActivity", property.getAppActivity());
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability(MobileCapabilityType.NO_RESET,"false");
//		capabilities.setCapability(MobileCapabilityType.FULL_RESET,"true");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
//		capabilities.setCapability("avdArgs", "-no-window");
//		capabilities.setCapability("isHeadless", true); 
		capabilities.setCapability("adbExecTimeout", "5000000");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//		driver = new AndroidDriver<>(new URL(appiumServiceUrl), capabilities);
		
	}
	
	public static AndroidDriver getDriver() throws MalformedURLException, Exception{
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(6000);
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		Thread.sleep(2000);
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_TAB);
		Thread.sleep(2000);
		// Click the current item
		driver.pressKeyCode(AndroidKeyCode.ENTER);
		return driver;
	}
	
	public static void uninstallSettings_7(){
		String cmd = "adb shell getprop ro.build.version.release";

		String osVersion=executeCommand(cmd);

		    if(osVersion.contains("7"))
		    {
		        //uninstall io.appium.settings
		        cmd="adb uninstall  io.appium.settings";
		        executeCommand(cmd);

		        //uninstall io.appium.unlock
		        cmd="adb uninstall  io.appium.unlock";
		        executeCommand(cmd);
		        
		        cmd = "adb uninstall io.appium.android.ime";
		        executeCommand(cmd);
		    }
	}
	
	 public static String NormalDateFormat(){
    	 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		 //get current date time with Date()
		  Date date = new Date();
		    
		 // Now format the date
		 String date1= dateFormat.format(date);
		 return date1;
    }
	 
	 public static String CurrentDate(){
    	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    
		 //get current date time with Date()
		  Date date = new Date();
		    
		 // Now format the date
		 String date1= dateFormat.format(date);
		 return date1;
    }
	
	public static String executeCommand(String cmd)
	{
	    String commandresponse="";
	    try
	    {
	        Runtime run = Runtime.getRuntime();
	        Process proc=run.exec(cmd);
	        BufferedReader stdInput = new BufferedReader(new 
	                InputStreamReader(proc.getInputStream()));

	        BufferedReader stdError = new BufferedReader(new 
	                InputStreamReader(proc.getErrorStream()));

	        String response=null;
	        while ((response = stdInput.readLine()) != null) 
	        {
	            if(response.length()>0)
	            {
	                commandresponse=commandresponse+response;
	            }
	        }

	        while ((response = stdError.readLine()) != null) 
	        {
	            commandresponse=commandresponse+response;

	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    //System.out.println(commandresponse);
	    return commandresponse;
	}
	
	  public static String DateTimeFormat(){
	    	DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
			    
			 //get current date time with Date()
			  Date date = new Date();
			    
			 // Now format the date
			 String date1= dateFormat.format(date);
			 return date1;
	   }
	  
	  public static String CurrentTimeFormat(){
	    	DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
			    
			 //get current date time with Date()
			  Date date = new Date();
			    
			 // Now format the date
			 String date1= dateFormat.format(date);
			 return date1;
	   }
	    
	  public static void logFile(String logfile) throws Exception{
	    	ReadPropertyFile property = new ReadPropertyFile();
	    	// This block configure the logger with handler and formatter  
	    	String date = DateTimeFormat();
	    	FileHandler fh = new FileHandler(property.getLogFilePath()+logfile+date+".log");  
	        log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
	        log.info("Log file created");
	    }
	  
	  public static String captureScreen_Negative(String ScriptName) throws Exception {
	    	ReadPropertyFile property = new ReadPropertyFile();
	        String path;
	        try {
	        	File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        	String date = DateTimeFormat();
	            path = property.getScreenshotpath()+"Screenshots_Negative\\"+ScriptName+"."+date+"\\" + source.getName();
	            FileUtils.copyFile(source, new File(path)); 
	            log.info("Screenshot captured for failure");
	        }
	        catch(IOException e) {
	            path = "Failed to capture screenshot: " + e.getMessage();
	        }
	        return path;
	    }
	  
	  public static void swipingVertical_Down() throws InterruptedException {
		  //Get the size of screen.
		  size = driver.manage().window().getSize();
		  int anchor = (int) (size.height * 0.5);
		  int startPoint = (int) (size.width * 0.85);
		  int endPoint = (int) (size.width * 0.01);
		  new TouchAction(driver).press(anchor,startPoint).waitAction().moveTo(PointOption.point(anchor,endPoint)).release().perform();
		  //Swipe from Bottom to Top.
		  Thread.sleep(2000);
		 }
	  
	  public static void swipingVertical_Up() throws InterruptedException {
		  //Get the size of screen.
		  size = driver.manage().window().getSize();
		  int anchor = (int) (size.height * 0.5);
		  int startPoint = (int) (size.width * 0.85);
		  int endPoint = (int) (size.width * 0.01);
		  new TouchAction(driver).press(startPoint,anchor).waitAction().moveTo(PointOption.point(endPoint,anchor)).release().perform();
		  //Swipe from Bottom to Top.
		  Thread.sleep(2000);
		 }
	  
	  public static void swipingHorizontal_RL() throws InterruptedException {
		  	size = driver.manage().window().getSize();
		    int anchor = (int) (size.height * 0.5);
		    int startPoint = (int) (size.width * 0.9);
		    int endPoint = (int) (size.width * 0.01);
		    new TouchAction(driver).press(startPoint, anchor).waitAction().moveTo(endPoint, anchor).release().perform();
		 }
	  
	  public static void swipingHorizontal_LR() throws InterruptedException {
		  	size = driver.manage().window().getSize();
		    int anchor = (int) (size.height * 0.5);
		    int startPoint = (int) (size.width * 0.9);
		    int endPoint = (int) (size.width * 0.01);
		    new TouchAction(driver).press(endPoint, anchor).waitAction().moveTo(startPoint, anchor).release().perform();
		 }
	  
	  public static void ScrollToTab() throws Exception{
		  //Get the size of screen.
		  size = driver.manage().window().getSize();
		   
		  //Find swipe start and end point from screen's with and height.
		  //Find starty point which is at bottom side of screen.
		  int startx = (int) (size.width * 0.70);
		  //Find endy point which is at top side of screen.
		  int endx = (int) (size.width * 0.30);
		  //Find horizontal point where you wants to swipe. It is in middle of screen width.
		  int yCoordinates = 626;
		  //Swipe from right to left.   
		  new TouchAction(driver).press(startx, yCoordinates).waitAction().moveTo(endx,yCoordinates).release().perform();
//		  driver.swipe(startx, yCoordinates, endx, yCoordinates, 3000);
		  Thread.sleep(2000);
		  
	  }
	  
	  public static boolean isElementPresent(By locatorKey) {
	        try {
	            driver.findElement(locatorKey);
	            return true;
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            return false;
	        }
	    }
	  
	  public static String NextDate(String sValue){
	    	DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
	    	//get previous day date
			Date date = DateUtils.addDays(new Date(), +Integer.parseInt(sValue));
			 // Now format the date
			String date1 = dateFormat.format(date);
			return date1;
	    }
	    
	  public static String lastDateOfMonth(){
	    	 Date today = new Date();  

	         Calendar calendar = Calendar.getInstance();  
	         calendar.setTime(today);  

	         calendar.add(Calendar.MONTH, 1);  
	         calendar.set(Calendar.DAY_OF_MONTH, 1);  
	         calendar.add(Calendar.DATE, -1);  

	         Date lastDayOfMonth = calendar.getTime();  
	         DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	         String date = sdf.format(lastDayOfMonth).substring(8, 10); 
	         return date;
	    }
}
