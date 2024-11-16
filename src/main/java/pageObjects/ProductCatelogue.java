package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class ProductCatelogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatelogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page factory is only for WebElements not for Locators 
	@FindBy (css = ".mb-3")
	List<WebElement> Items;
	
	@FindBy (css = ".mb-3")
	WebElement Item;
	
	@FindBy (css = ".ng-animating")
	WebElement Spinner;
	
	@FindBy (css = "#toast-container")
	WebElement ToastMessage;
	
	// By is for Locators(i.e., By Locator)
	By addToCart = By.cssSelector("button.w-10");
	
	public List<WebElement> getProductList() {
		waitforWebElementToAppear(Item);
		return Items;	
	}
	
	public WebElement getProduct(String ItemName) {
		WebElement Product = getProductList().stream()
				.filter(prod -> prod.findElement(By.cssSelector("h5 b")).getText().equals(ItemName)).findFirst()
				.orElse(null);
		return Product;
	}
	
	public void addProductToCart(String ItemName) throws InterruptedException {
		// cannot create create page factory for custom driver(i.e., WebElement.findElement)
		WebElement Product = getProduct(ItemName);
		Product.findElement(addToCart).click();
		waitforWebElementToDisappear(ToastMessage);
		waitforWebElementToDisappear(Spinner);
	}
}
