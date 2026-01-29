package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import pageObjects.LoginScreen;
import testBase.BaseTestClass;
import utilities.AppiumUtilities;

public class TC02_ShopSelectingCountry extends BaseTestClass {

	@Test
	public void shopSelectingCountryTest() throws InterruptedException {
		LoginScreen lp = new LoginScreen(getDriver());
		String country = "Cuba";
		lp.openCountrySelector(country);
		AppiumUtilities.scrollAndroidElementIntoView(driver, country);
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + country + "\")")).click();

		Assert.assertEquals(
				getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]")).getText(),
				"Cuba");
	}
}
