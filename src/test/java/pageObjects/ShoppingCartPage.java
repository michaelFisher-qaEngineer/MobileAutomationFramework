package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AppiumUtilities;

public class ShoppingCartPage extends BasePage {
	WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
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
		return productNameInCart.getText();
	}

	public void checkSendEmailsCheckbox() throws Exception {
		if (sendEmailsCheckbox.getAttribute("checked").equalsIgnoreCase("checked")) {
			return;
		} else {
			sendEmailsCheckbox.click();
		}
	}
	
	public void clickCompletePurchaseButton() {
		completePurchaseButton.click();
	}
	
	public void longPressTermsButton(WebDriver driver) {
		AppiumUtilities.androidLongPress(driver, termsButton);
	}
	
	public String getAlertTitle(WebDriver driver) {
		return termsAlertTitle.getText();
	}

}
