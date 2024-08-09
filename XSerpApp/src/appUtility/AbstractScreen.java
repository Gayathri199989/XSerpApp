package appUtility;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public abstract class AbstractScreen {
	
	protected AndroidDriver driver;
	public AbstractScreen(AndroidDriver driver) {
		this.driver = driver;
		}
	
	public void loadPage(){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);		
	}
	
	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void rotateScreen() {
		driver.rotate(ScreenOrientation.LANDSCAPE);
		// TODO Auto-generated method stub
	}

	
}
