package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends BasePage {

	WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/toolbar_title\")")
	WebElement productPageTitle;
		
	@FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	WebElement cartButton;
	
	
	public String getProductPageTitle() {
		return productPageTitle.getText();
	}
	
	public void openCart() {
		cartButton.click();
	}
	
	public void addProductToCart(WebDriver driver, String productToSelect) {
		int productOnScreenCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for (int i = 0; i < productOnScreenCount; i++) {
			String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i)
					.getText();
			if (productName.equalsIgnoreCase(productToSelect)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
	}
	
}
