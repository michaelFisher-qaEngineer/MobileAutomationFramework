package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import testBase.BaseTestClass;

public class TC02_ShopSelectingCountry extends BaseTestClass {
	private static final Logger log = LogManager.getLogger(TC02_ShopSelectingCountry.class);
	@Test
	public void shopSelectingCountryTest() throws Exception {
		log.info("========== TEST START: Verify selecting country from login page ==========");

		try {
			log.info("Step 1: Initializing Login Screen");
			LoginScreen lp = new LoginScreen(getDriver());
			String country = "Cuba";
			log.info("Step 2: Select country from dropdown");
			lp.selectCountry(driver, country);
			log.debug("Country name: {}", country);

			Assert.assertEquals(
					getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]")).getText(),
					country);

			log.info("✅ Validation passed — Country successfully selected.");
		} catch (Exception e) {
			log.error("❌ Test failed due to exception: {}", e.getMessage(), e);
			throw e; // rethrow so TestNG still marks test as FAILED
		}
		log.info("========== TEST END: shopSelectingCountryTest ==========");
	}
}
