package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css = "div.cartSection h3")
	List<WebElement> CartItems;
	
	@FindBy (xpath = "//button[contains(text(),'Checkout')]")
	WebElement Checkout;
	
	public boolean verifyProducts(String ItemName) {
		boolean match = CartItems.stream().anyMatch(Item -> Item.getText().equalsIgnoreCase(ItemName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		Checkout.click();
		return new CheckoutPage(driver);
	}
	
	
}
