package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import pageObjects.ProductsPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseTestClass;

public class TC04_AddToCart extends BaseTestClass {
	private static final Logger log = LogManager.getLogger(TC04_AddToCart.class);
	String productName = "Jordan 6 Rings";

	@Test
	public void addToCartTest() throws Exception {
		log.info("========== TEST START: add item to cart ==========");
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
			
			// ===== Verify Cart =====
			log.info("Step 3. Validate product in cart");
			ShoppingCartPage cart = new ShoppingCartPage(getDriver());
			String nameInCart = cart.getProductNameInCart();
			log.debug("Product name in cart: {}", nameInCart);
			
			Assert.assertEquals(nameInCart, productName);
			log.info("✅ Validation passed — {} present in cart", nameInCart);
		} catch (Exception e) {
            log.error("❌ Test failed: Add product to cart failed. Reason: {}", e.getMessage(), e);
            throw e;
        }
		
		log.info("========== TEST END: addToCartTest ==========");
	}
}
