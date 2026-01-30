package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import pageObjects.ProductsPage;
import pageObjects.ShoppingCartPage;
import pageObjects.WebviewPage;
import testBase.BaseTestClass;

public class TC05_CompletePurchase extends BaseTestClass {

    private static final Logger log = LogManager.getLogger(TC05_CompletePurchase.class);
    String productName = "Jordan 6 Rings";

    @Test
    public void completePurchaseTest() throws Exception {

        log.info("========== TEST START: Complete purchase flow for product '{}' ==========", productName);

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

            // ===== Cart Actions =====
            log.info("Step 3: Completing purchase from cart");
            ShoppingCartPage cart = new ShoppingCartPage(getDriver());

            cart.checkSendEmailsCheckbox();
            log.debug("Send emails checkbox selected");

            cart.clickCompletePurchaseButton();
            log.info("Purchase process initiated");

            // ===== Webview Interaction =====
            log.info("Step 4: Performing search in webview");
            WebviewPage webview = new WebviewPage(getDriver());

            String searchTerm = "Bootcamp";
            log.debug("Searching for term: {}", searchTerm);

            webview.searchForTerm(getDriver(), searchTerm);
            log.info("Search submitted in webview");

            log.info("Navigating back from webview to native app");
            webview.goBack(driver);
            webview.switchToNativeContext(driver);

            // ===== Final Validation =====
            log.info("Step 5: Validating return to General Store home screen");
            String title = lp.getPageTitle();
            log.debug("Home screen title displayed: {}", title);

            Assert.assertEquals(title, "General Store");
            log.info("✅ Validation passed — user returned to home screen successfully");

        } catch (Exception e) {
            log.error("❌ Test failed: Complete purchase flow broke. Reason: {}", e.getMessage(), e);
            throw e;
        }

        log.info("========== TEST END: completePurchaseTest ==========");
    }
}
