package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utilities.AppiumUtilities;

public class LoginScreen extends BasePage {

	WebDriver driver;

	// constructor
	public LoginScreen(WebDriver driver) {
		super(driver);

	}

	// locators
	@FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\"]")
	WebElement pageTitle;

	@FindBy(xpath = "//android.widget.Spinner[@resource-id=\"com.androidsample.generalstore:id/spinnerCountry\"]")
	WebElement countryDropdown;

	@FindBy(xpath = "//android.widget.TextView[@text=\\\"Your Name\\\"]")
	WebElement yourNameLabel;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	WebElement nameTextBox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	WebElement maleRadioButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	WebElement femaleRadioButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	WebElement letsShopButton;

	// actions
	public String getPageTitle() {
		return pageTitle.getText();
	}

	public String getNameLabel() {
		return yourNameLabel.getText();
	}

	public void enterName(String name) {
		nameTextBox.sendKeys(name);
	}

	public void selectRadioButton(String gender) throws Exception {
		switch (gender.toLowerCase()) {
		case "male":
			maleRadioButton.click();
			break;
		case "female":
			femaleRadioButton.click();
			break;
		default:
			throw new Exception("Only male or female allowed for this selection.");
		}
	}

	public void clickLetsShopButton() {
		letsShopButton.click();
	}

	public void selectCountry(WebDriver driver, String country) throws Exception {
		countryDropdown.click();
		AppiumUtilities.scrollAndroidElementIntoView(driver, country);
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + country + "\")")).click();
	}

}
