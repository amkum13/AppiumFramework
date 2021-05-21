package practice.AppiumFramework;
import java.io.IOException; 
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.Preferences;

public class ApiDemoTest extends Base {

	@Test(dataProvider="InputData", dataProviderClass=TestData.class)
	public void apiDemo(String input) throws IOException, InterruptedException {
		
		service=startServer();
		
		AndroidDriver<AndroidElement> driver = Capabilities("apiDemos");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		HomePage h = new HomePage(driver);	
		h.Preferences.click();
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		
		Preferences p = new Preferences(driver);
		p.Dependencies.click();
		//driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys(input);
		
		//p.buttons.get(1).click();
		driver.findElementsByClassName("android.widget.Button").get(1).click();
		
		
		service.stop();	
 
	}

}
