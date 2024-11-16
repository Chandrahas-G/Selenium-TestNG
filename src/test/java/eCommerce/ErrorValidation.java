package eCommerce;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.ProductCatelogue;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups = { "errors" }, retryAnalyzer = Retry.class)
	public void loginError() {

		loginPage.login("abc@gmail.com", "Abc@021");
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());
	}

	@Test(groups = { "errors" }, retryAnalyzer = Retry.class)
	public void productError() throws InterruptedException {

		ProductCatelogue productCatelogue = loginPage.login("micky@gmail.com", "Micky@021");
		productCatelogue.addProductToCart("ADIDAS ORIGINAL");
		CartPage cartPage = productCatelogue.goToCart();
		boolean match = cartPage.verifyProducts("ADIDAS ORIGINALss");
		Assert.assertFalse(match);
	}
}
