package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

}
