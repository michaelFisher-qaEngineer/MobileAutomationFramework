package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import pageObjects.ProductsPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseTestClass;

public class TC06_TermsAndConditions extends BaseTestClass {
	private static final Logger log = LogManager.getLogger(TC06_TermsAndConditions.class);
	String productName = "Jordan 6 Rings";

	@Test
	public void termsAndConditionsTest() throws Exception {
		log.info("========== TEST START: Verify terms and conditions ==========");
		try {
			// ===== Login Step =====
			log.info("Step 1: Entering user details on Login screen");
			LoginScreen lp = new LoginScreen(getDriver());

			lp.selectRadioButton("male");
			log.debug("Gender selected: male");

			lp.enterName("michael");
			log.debug("User name entered: michael");

			lp.clickLetsShopButton();
			log.info("Login details submitted");

			// ===== Product Selection =====
			log.info("Step 2: Adding product to cart");
			ProductsPage prod = new ProductsPage(getDriver());

			log.debug("Target product: {}", productName);
			prod.addProductToCart(getDriver(), productName);
			log.info("Product added to cart");

			log.info("Opening shopping cart");
			prod.openCart();

			// ===== Verify Terms and conditions alert =====
			ShoppingCartPage cart = new ShoppingCartPage(getDriver());
			cart.longPressTermsButton(getDriver());
			log.info("Long press terms and conditions button");
			
			String actualAlertTitle = cart.getAlertTitle(getDriver());
			String expectedAlertTitle = "Terms Of Conditions";
			log.info("Verifying alert title");
			log.debug("Expected alert title: {}", expectedAlertTitle);
			log.debug("Actual alert title: {}", actualAlertTitle);
			
			Assert.assertEquals(actualAlertTitle, expectedAlertTitle);
			log.info("✅ Validation passed — alert has expected title {}", actualAlertTitle);
		} catch (Exception e) {
            log.error("❌ Test failed: Verify terms and conditions failed. Reason: {}", e.getMessage(), e);
            throw e;
        }

        log.info("========== TEST END: termsAndConditionsTest ==========");
	}
}
