package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utilities.AppiumUtilities;

public class LoginScreen extends BasePage {

	private static final Logger log = LogManager.getLogger(LoginScreen.class);

	// constructor
	public LoginScreen(WebDriver driver) {
		super(driver);
		log.debug("LoginScreen initialized");
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

	@FindBy(xpath = "(//android.widget.Toast)[1]")
	WebElement toastMessage;

	// ===== Actions with Logging =====

	public String getPageTitle() {
		String title = pageTitle.getText();
		log.debug("Fetched page title: {}", title);
		return title;
	}

	public String getNameLabel() {
		String label = yourNameLabel.getText();
		log.debug("Fetched name label text: {}", label);
		return label;
	}

	public void enterName(String name) {
		log.debug("Entering name into text box: {}", name);
		nameTextBox.clear();
		nameTextBox.sendKeys(name);
	}

	public void selectRadioButton(String gender) throws Exception {
		log.debug("Selecting gender radio button: {}", gender);

		switch (gender.toLowerCase()) {
		case "male":
			maleRadioButton.click();
			log.debug("Male radio button selected");
			break;

		case "female":
			femaleRadioButton.click();
			log.debug("Female radio button selected");
			break;

		default:
			log.error("Invalid gender provided: {}", gender);
			throw new Exception("Only male or female allowed for this selection.");
		}
	}

	public void clickLetsShopButton() {
		log.debug("Clicking 'Let's Shop' button");
		letsShopButton.click();
	}

	public void selectCountry(WebDriver driver, String country) throws Exception {
		log.debug("Opening country dropdown");
		countryDropdown.click();

		log.debug("Scrolling to country: {}", country);
		AppiumUtilities.scrollAndroidElementIntoView(driver, country);

		log.debug("Selecting country from list: {}", country);
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + country + "\")")).click();
	}

	public String getToastMessage() {
		String toast = toastMessage.getText();
		log.debug("Captured toast message: {}", toast);
		return toast;
	}
}
