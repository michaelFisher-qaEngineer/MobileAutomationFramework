package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AppiumUtilities;

public class ShoppingCartPage extends BasePage {
	private static final Logger log = LogManager.getLogger(ShoppingCartPage.class);
	
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
        log.debug("ShoppingCartPage initialized");
	}

	// locators
	@FindBy(className = "android.widget.CheckBox")
	WebElement sendEmailsCheckbox;

	@FindBy(id = "com.androidsample.generalstore:id/btnProceed")
	WebElement completePurchaseButton;

	@FindBy(id = "com.androidsample.generalstore:id/productName")
	WebElement productNameInCart;
	
	@FindBy(id = "com.androidsample.generalstore:id/termsButton")
	WebElement termsButton;
	
	@FindBy(id = "com.androidsample.generalstore:id/alertTitle")
	WebElement termsAlertTitle;

	
	public String getProductNameInCart() {
		String name = productNameInCart.getText();
		log.debug("Retrieved product name from cart: {}", name);
		return name;
	}

	public void checkSendEmailsCheckbox() {
		if (sendEmailsCheckbox.getAttribute("checked").equalsIgnoreCase("checked")) {
			log.debug("Send emails checkbox already checked");
		} else {
			log.info("Clicking send emails checkbox");
			sendEmailsCheckbox.click();
		}
	}
	
	public void clickCompletePurchaseButton() {
		log.info("Clicking complete purchase button");
		completePurchaseButton.click();
	}
	
	public void longPressTermsButton(WebDriver driver) {
		log.info("Long pressing terms and conditions button");
		AppiumUtilities.androidLongPress(driver, termsButton);
	}
	
	public String getAlertTitle(WebDriver driver) {
		String title = termsAlertTitle.getText();
		log.debug("Retrieved alert title: {}", title);
		return termsAlertTitle.getText();
	}

}
