package pageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.pagefactory.AndroidFindBy;
import utilities.AppiumUtilities;

public class ProductsPage extends BasePage {
	private static final Logger log = LogManager.getLogger(ProductsPage.class);
//	WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		super(driver);
		log.debug("ProductsPage initialized");
	}

	// locators

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/toolbar_title\")")
	WebElement productPageTitle;

	@FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	WebElement cartButton;

	public String getProductPageTitle() {
		String title = productPageTitle.getText();
		log.debug("Retrieved product page title: {}", title);
		return productPageTitle.getText();
	}

	public void openCart() {
		log.debug("Clicking cart button");
		cartButton.click();
		log.info("Cart button clicked");
	}

	public void addProductToCart(WebDriver driver, String productToSelect) throws InterruptedException {
	    log.info("Attempting to add product '{}' to cart", productToSelect);

		AppiumUtilities.scrollAndroidElementIntoView(driver, productToSelect);
	    log.debug("Scrolled into view: {}", productToSelect);
	    
	    List<WebElement> productNames = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));

		int productOnScreenCount = productNames.size();
	    log.debug("Number of products visible on screen: {}", productOnScreenCount);

	    boolean productFound = false;

		for (int i = 0; i < productOnScreenCount; i++) {
	        String productName = productNames.get(i).getText();
	        log.trace("Checking product at index {}: {}", i, productName);
			if (productName.equalsIgnoreCase(productToSelect)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
	            log.info("Product '{}' added to cart (index {})", productToSelect, i);
	            productFound = true;
	            break;
			}
		}
		
		if (!productFound) {
	        log.warn("Product '{}' not found on screen", productToSelect);
	    }
	}

}
