package pageObjects;

import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.remote.SupportsContextSwitching;
import utilities.AppiumUtilities;

public class WebviewPage extends BasePage {

	
	public WebviewPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(name = "q")
	WebElement searchBox;
	
	
	public void switchToWebviewContext(WebDriver driver) {
		AppiumUtilities.waitForContext(driver, "WEBVIEW_com.androidsample.generalstore");
		SupportsContextSwitching ctxDriver = (SupportsContextSwitching) driver;
		Set<String> contexts = ctxDriver.getContextHandles();
		AppiumUtilities.switchContexts(driver, contexts, "WEBVIEW");
	}
	
	public void switchToNativeContext(WebDriver driver) {
		SupportsContextSwitching ctxDriver = (SupportsContextSwitching) driver;
		Set<String> contexts = ctxDriver.getContextHandles();
		AppiumUtilities.waitForContext(driver, "NATIVE_APP");
		AppiumUtilities.switchContexts(driver, contexts, "NATIVE_APP");
	}
	
	public void searchForTerm(WebDriver driver, String searchTerm) throws InterruptedException {
		switchToWebviewContext(driver);
		searchBox.click();
		searchBox.sendKeys(searchTerm);
		searchBox.sendKeys(Keys.ENTER);
	}
	

	public void goBack(WebDriver driver) {
		AppiumUtilities.pressBackButton(driver);
	}
}
