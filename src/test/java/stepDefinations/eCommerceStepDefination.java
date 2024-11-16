package stepDefinations;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LoginPage;
import pageObjects.ProductCatelogue;
import testComponents.BaseTest;

public class eCommerceStepDefination extends BaseTest {

	public LoginPage loginPage;
	public ProductCatelogue productCatelogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on eCommerce Page")
	public void I_landed_on_eCommerce_Page() throws IOException {
		loginPage = launchApplication();
	}

	// (.+) to represent any type of variable like, int, char etc.
	// (.+) only works when data is driven from examples
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String Email, String Password) {
		productCatelogue = loginPage.login(Email, Password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String ItemName) throws InterruptedException {
		productCatelogue.addProductToCart(ItemName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String ItemName) {
		CartPage cartPage = productCatelogue.goToCart();
		boolean match = cartPage.verifyProducts(ItemName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.placeOrder();
	}

	// {string} are used to tell that Message is a String
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String ConfirmMessage = confirmationPage.getMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
		System.out.println(ConfirmMessage);
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg) throws Throwable {
    	Assert.assertEquals(strArg, loginPage.getErrorMessage());
    	System.out.println(loginPage.getErrorMessage());
    	driver.close();
    }

}
