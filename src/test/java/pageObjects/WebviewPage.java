package pageObjects;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.remote.SupportsContextSwitching;
import utilities.AppiumUtilities;

public class WebviewPage extends BasePage {
	private static final Logger log = LogManager.getLogger(WebviewPage.class);
//	WebDriver driver;
	
	public WebviewPage(WebDriver driver) {
		super(driver);
        log.debug("WebviewPage initialized");
	}
	
	//locators
	@FindBy(name = "q")
	WebElement searchBox;
	
	
	public void switchToWebviewContext(WebDriver driver) {
        log.debug("Waiting for WEBVIEW context to be available...");
		AppiumUtilities.waitForContext(driver, "WEBVIEW_com.androidsample.generalstore");
		SupportsContextSwitching ctxDriver = (SupportsContextSwitching) driver;
		Set<String> contexts = ctxDriver.getContextHandles();
        log.debug("Available contexts: {}", contexts);
        
		AppiumUtilities.switchContexts(driver, contexts, "WEBVIEW");
        log.debug("Switched to WEBVIEW context successfully");
	}
	
	public void switchToNativeContext(WebDriver driver) {
        log.debug("Switching back to NATIVE_APP context...");
		SupportsContextSwitching ctxDriver = (SupportsContextSwitching) driver;
		Set<String> contexts = ctxDriver.getContextHandles();
        log.debug("Available contexts before switching: {}", contexts);
        
		AppiumUtilities.waitForContext(driver, "NATIVE_APP");
		AppiumUtilities.switchContexts(driver, contexts, "NATIVE_APP");

        log.debug("Switched to NATIVE_APP context successfully");
	}
	
	public void searchForTerm(WebDriver driver, String searchTerm) throws InterruptedException {
		log.info("Performing search in webview");
		switchToWebviewContext(driver);
		

        log.debug("Clicking search box");
		searchBox.click();
		
        log.debug("Entering search term: {}", searchTerm);
		searchBox.sendKeys(searchTerm);
		
        log.debug("Submitting search by pressing enter key");
		searchBox.sendKeys(Keys.ENTER);
	}
	

	public void goBack(WebDriver driver) {
        log.debug("Pressing device back button to exit webview");
		AppiumUtilities.pressBackButton(driver);
	}
}
