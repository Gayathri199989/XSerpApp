package appTestCases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import appScreen.AppLoginScreen;
import appUtility.Constant;
import appUtility.Log;
import appUtility.ReadPropertyFile;

public class LoginTestCases {
	private static AndroidDriver driver;
	static ReadPropertyFile property = PageFactory.initElements(driver, ReadPropertyFile.class);
	 static AppiumDriverLocalService appiumService;

	Log log = new Log();
	
	static String extentReportFile;
	static ExtentReports extent;
	static String extentReportImage;
	static ExtentTest extentTest;
	static String date = Constant.DateTimeFormat();
	 //define an Excel Work Book
	  HSSFWorkbook workbook;
	  //define an Excel Work sheet
	  HSSFSheet sheet;
	  //define a test result data object
	Map<String, Object[]> testresultdata;
	
	@BeforeClass
	public static void BeforeClass() throws Exception{
		extentReportFile = property.getExtentReportFile()+"extentReportFile"+date+"."+"html";
		extentReportImage = property.getExtentReportImg()+"extentReportImage"+date+"."+"png";
		// Create object of extent report and specify the report file path.
		extent = new ExtentReports(extentReportFile, false);
		Constant.logFile("XSerp App");
//		appiumService = AppiumDriverLocalService.buildDefaultService();
//		appiumService.start();
		
		 //Browser Initialization
		 Constant.AppSetting();
		 driver = Constant.getDriver();
		
	}
	 
	@BeforeTest
	public void Before() throws Exception{
//		DOMConfigurator.configure("log4j.xml");
		//create a new work book
		workbook = new HSSFWorkbook();
		//create a new work sheet
		sheet = workbook.createSheet("Catch Up");
		testresultdata = new LinkedHashMap<String, Object[]>();
		//add test result excel file column header
		// write the header in the first row
		testresultdata.put("1", new Object[] {"S.No", "Scenario", "Expected Result","Pass/Fail"});
	}
	
	@Test
	public void test001_LoginScreenVerification(){
		Log.startTestCase("Login with registered user");
		// Start the test using the ExtentTest class object.
		extentTest = extent.startTest("Login with registered user",
				"Verify Login with registered user");
		 extentTest.log(LogStatus.INFO, "Browser Launched");
		try{
//			ChatScreen chatScreen = new ChatScreen(driver);
//			boolean flag = chatScreen.login(0);
//			if(flag == true){
//				Constant.log.info("test001_LoginWithRegisteredUser: Login  with registered user test case is successful");
//				extentTest.log(LogStatus.PASS, "Login with registered user Successful");
//				testresultdata.put("2", new Object[] {1d, "Login", "Login with registered user is successful","Pass"});
//			}else{
//				System.err.println("test001_LoginWithRegisteredUser: Login with registered user test case failed ");
//				Constant.captureScreen_Negative("Login with registered user");
//				extentTest.log(LogStatus.FAIL, "Login with registered user failed");
//				testresultdata.put("2", new Object[] {1d, "Login with registered user", "Login with registered user failed","Fail"});
//			}
//			Assert.assertTrue(flag == true);
		}catch(Exception e){
			System.err.println(e);
		}
		Log.endTestCase("Login with registered user");
	}
	
	@AfterTest
	public void After(){
//	 	nurseScreen.logout();
	 	 //write excel file and file name is TestResult.xls 
        Set<String> keyset = testresultdata.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = testresultdata.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof Date) 
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }
        try {
            FileOutputStream out =new FileOutputStream(new File("CatchUp.xls"));
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
	}
	
	@AfterClass
	public static void AfterClass() throws Exception{
		driver.quit();
		extentTest.log(LogStatus.INFO, "Browser closed");
		// close report.  
		extent.endTest(extentTest);
		// writing everything to document.
    	extent.flush();
//    	appiumService.stop();
	}
}
