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
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.SupportsContextSwitching;

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


	public static void switchContexts(WebDriver driver, Set<String> contexts, String switchToContext) {
		if (!(driver instanceof SupportsContextSwitching)) {
			throw new IllegalArgumentException("Driver does not support context switching: " + driver.getClass());
		}
		SupportsContextSwitching ctxDriver = (SupportsContextSwitching) driver;
		for (String context : contexts) {
			if (context.contains(switchToContext)) {
				ctxDriver.context(context);
				return;
			}
		}
		throw new IllegalStateException("No matching context found containing: " + switchToContext);
	}

	public static void waitForContext(WebDriver driver, String contextToWaitFor) {
		if (!(driver instanceof SupportsContextSwitching)) {
			throw new IllegalArgumentException("Driver does not support context switching: " + driver.getClass());
		}
		SupportsContextSwitching ctxDriver = (SupportsContextSwitching) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				Set<String> contexts = ctxDriver.getContextHandles();
				return contexts.contains(contextToWaitFor);
			}

			@Override
			public String toString() {
				return "Context '" + contextToWaitFor + "' to become available.";
			}
		});
	}
	
	public static void pressBackButton(WebDriver driver) {
		AndroidDriver adr = (AndroidDriver) driver;
		adr.pressKey(new KeyEvent(AndroidKey.BACK));
	}

}