package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	
	//Concatenate Driver -- i.e. through constructor
	public FormPage(AndroidDriver <AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/nameField"))
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public WebElement nameField;
	
	//driver.findElement(By.xpath("//*[@text='Female']"))
	@AndroidFindBy(xpath="//*[@text='Female']")
	public WebElement femaleOption;
	
	public WebElement getNameField() {
		System.out.println("Some more code can be written here wrt finding web elements");
		return nameField;
	}

}
