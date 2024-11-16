package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (css = "input.text-validated:nth-child(1)")
	WebElement Country;
	
	@FindBy (xpath = "//span[text()=' India']")
	WebElement SelectCountry;
	
	@FindBy (xpath = "//a[text()='Place Order ']")
	WebElement PlaceOrder;
	
	public void selectCountry(String CountryName) {
		waitforWebElementToAppear(Country);
		Country.sendKeys(CountryName);
		SelectCountry.click();
	}
	
	public ConfirmationPage placeOrder() {
		PlaceOrder.click();
		return new ConfirmationPage(driver);
	}
}

