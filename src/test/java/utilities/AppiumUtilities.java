package utilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AppiumUtilities {

	public static void scrollAndroidElementIntoView(WebDriver driver, String selection) throws InterruptedException {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))" + ".scrollTextIntoView(\"" + selection + "\")"));
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
				.presenceOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + selection + "\")")));
	}

	// getsure automation from appium website
	// https://appium.github.io/appium.io/docs/en/writing-running-appium/android/android-mobile-gestures/
	public static void androidLongPress(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
	}

	public static void switchContexts(AndroidDriver driver, Set<String> contexts, String switchToContext) {
		for (String context : contexts) {
			if (context.contains(switchToContext)) {
				driver.context(context);
				break;
			}
		}
	}


	public static void waitForContext(AndroidDriver driver, String contextToWaitFor) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver1) {
				if (driver1 instanceof AndroidDriver) {
					AndroidDriver androidDriver = (AndroidDriver) driver1;
					Set<String> contexts = androidDriver.getContextHandles();
					System.out.println("Available contexts: " + contexts);
					return contexts.contains(contextToWaitFor);
				} else {
					System.out.println("Driver is not an AndroidDriver.");
					return false;
				}
			}

			@Override
			public String toString() {
				return "Context '" + contextToWaitFor + "' to become avaialble.";
			}
		});
	}

}