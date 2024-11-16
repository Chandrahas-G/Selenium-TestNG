package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.OrdersPage;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.wait = w;
		PageFactory.initElements(driver, this);
	}

	@FindBy (css = "button[routerlink='/dashboard/cart']")
	WebElement Cart;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement Orders;
	
	public void waitforWebElementToAppear(WebElement findBy) {
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitforWebElementToDisappear(WebElement findBy) throws InterruptedException {
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.invisibilityOf(findBy));
	}
	
	public CartPage goToCart() {
		Cart.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToOrders() {
		Orders.click();
		return new OrdersPage(driver);
	}
}
