package eCommerce;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicOrderItem {

	public static void main(String[] args) {

		String ItemName = "ADIDAS ORIGINAL";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys("chandrahas@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Micky@021");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> Items = driver.findElements(By.cssSelector(".mb-3"));
		WebElement Product = Items.stream()
				.filter(prod -> prod.findElement(By.cssSelector("h5 b")).getText().equals(ItemName)).findFirst()
				.orElse(null);
		//System.out.println(Product.getText());
		Product.findElement(By.cssSelector("button.w-10")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

		List<WebElement> CartItems = driver.findElements(By.cssSelector("div.cartSection h3"));
		boolean match = CartItems.stream().anyMatch(Item -> Item.getText().equalsIgnoreCase(ItemName));
		Assert.assertTrue(match);

		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.cssSelector("input.text-validated:nth-child(1)"))));
		driver.findElement(By.cssSelector("input.text-validated:nth-child(1)")).sendKeys("India");
		driver.findElement(By.xpath("//span[text()=' India']")).click();

		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();

		String ConfirmMessage = driver.findElement(By.xpath("//h1[text()=' Thankyou for the order. ']")).getText();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));

		System.out.println(ConfirmMessage);
		driver.close();
	}
}
