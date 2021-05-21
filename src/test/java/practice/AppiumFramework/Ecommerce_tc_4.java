package practice.AppiumFramework;
import java.net.MalformedURLException;    

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import static java.time.Duration.ofSeconds;

import java.io.IOException;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Ecommerce_tc_4 extends Base {
	
	

	//public static void main(String[] args) throws MalformedURLException, InterruptedException, IOException {
		
	@Test
	public void totalValidation() throws IOException, InterruptedException {
		
		service=startServer();
		
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
		
		Utilities util = new Utilities(driver);
		util.scrollToText("Argentina");
		
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		FormPage formPage = new FormPage(driver);
		formPage.nameField.sendKeys("Hello");
		//formPage.getNameField().sendKeys("Hello");
		
		
		driver.hideKeyboard();
		//driver.findElement(By.xpath("//*[@text='Female']")).click();
		formPage.femaleOption.click();
		
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//TC_04 Steps:
		driver.findElements(By.xpath("//*[@text= 'ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text= 'ADD TO CART']")).get(0).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		
		Thread.sleep(4000);
		
		//MORE CODE OPTIMIZAION
		
	
	int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
	double sumOfProducts =0;
	
	CheckoutPage checkOutPage = new CheckoutPage(driver);
	for(int i=0;i<count;i++) {
		
		//String amount= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
		String amount= checkOutPage.productList.get(i).getText();
		double amountValue = getAmount(amount);
		sumOfProducts += amountValue;
	
	}
			
		//String total = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	    String total =checkOutPage.getTotalAmount().getText();
		
		total = total.substring(1);
		double totalValue = Double.parseDouble(total);
		System.out.println("Expected Value "+sumOfProducts);
		System.out.println("Actual Value "+totalValue);
		
		
		Assert.assertEquals(totalValue, sumOfProducts);
		System.out.println("Assertion Passed! Products price are calculated correctly");
		
		
		//TC_05 Steps: Mobile Gestures:
		WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		
		
		WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		service.stop();
		
		
			
		}
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		//taskkill /F ?IM node.exe
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
		
	}


	
	public static double getAmount(String value) {
	
	value = value.substring(1);
	double doubleValue = Double.parseDouble(value);
	return doubleValue;
	
	}
	
	

}
