package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import pageObjects.ProductsPage;
import testBase.BaseTestClass;

public class TC03_LetsShop extends BaseTestClass {

	private static final Logger log = LogManager.getLogger(TC03_LetsShop.class);

	@Test
	public void letsShopTest() throws Exception {

		log.info("========== TEST START: Verify user can enter details and reach Products page ==========");

		try {
			log.info("Step 1: Initializing Login Screen");
			LoginScreen lp = new LoginScreen(getDriver());

			log.info("Step 2: Selecting gender radio button");
			lp.selectRadioButton("male");
			log.debug("Gender selected: male");

			log.info("Step 3: Entering user name");
			lp.enterName("michael");
			log.debug("User name entered: michael");

			log.info("Step 4: Clicking 'Let's Shop' button");
			lp.clickLetsShopButton();

			log.info("Step 5: Verifying Products page is displayed");
			ProductsPage prod = new ProductsPage(getDriver());

			String actualTitle = prod.getProductPageTitle();
			log.debug("Actual Products page title: {}", actualTitle);

			Assert.assertEquals(actualTitle, "Products");
			log.info("✅ Validation passed — Products page loaded successfully");

		} catch (Exception e) {
			log.error("❌ Test failed due to exception: {}", e.getMessage(), e);
			throw e; // rethrow so TestNG still marks test as FAILED
		}

		log.info("========== TEST END: letsShopTest ==========");
	}
}
