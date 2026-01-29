package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import testBase.BaseTestClass;

public class TC02_ShopSelectingCountry extends BaseTestClass {

	@Test
	public void shopSelectingCountryTest() throws Exception {
		LoginScreen lp = new LoginScreen(getDriver());
		String country = "Cuba";
		lp.selectCountry(driver, country);

		Assert.assertEquals(
				getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]")).getText(),
				country);
	}
}
