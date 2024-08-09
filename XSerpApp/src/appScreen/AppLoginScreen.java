package appScreen;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import appUtility.Constant;
import appUtility.ReadPropertyFile;

public class AppLoginScreen {
	ReadPropertyFile property = PageFactory.initElements(Constant.driver, ReadPropertyFile.class);
	
	
	public WebDriver driver;
	public AppLoginScreen(WebDriver driver){
		this.driver = driver;
	}
	
	
}
