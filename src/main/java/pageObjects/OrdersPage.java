package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath = "//button[contains(text(),'Checkout')]")
	WebElement Checkout;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> OrderedItems;

	public Boolean VerifyOrders(String ItemName) {
		Boolean match = OrderedItems.stream().anyMatch(product -> product.getText().equalsIgnoreCase(ItemName));
		return match;
	}
}
