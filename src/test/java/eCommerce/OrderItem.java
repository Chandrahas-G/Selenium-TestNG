package eCommerce;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrdersPage;
import pageObjects.ProductCatelogue;
import testComponents.BaseTest;

public class OrderItem extends BaseTest {

	// String ItemName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getJsonData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatelogue productCatelogue = loginPage.login(input.get("Email"), input.get("Password"));
		productCatelogue.addProductToCart(input.get("ItemName"));
		CartPage cartPage = productCatelogue.goToCart();
		boolean match = cartPage.verifyProducts(input.get("ItemName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		String ConfirmMessage = confirmationPage.getMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(ConfirmMessage);
	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData")
	public void verifyOrder(String Email, String Password, String ItemName) {

		ProductCatelogue productCatelogue = loginPage.login(Email, Password);
		OrdersPage ordersPage = productCatelogue.goToOrders();
		Assert.assertTrue(ordersPage.VerifyOrders(ItemName));
	}
	
	@DataProvider
	public Object[][] getJsonData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//jsonData//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };

	}

	@DataProvider
	public Object[][] hashMapData() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Email", "chandrahas@gmail.com");
		map.put("Password", "Micky@021");
		map.put("ItemName", "ADIDAS ORIGINAL");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("Email", "micky@gmail.com");
		map1.put("Password", "Micky@021");
		map1.put("ItemName", "IPHONE 13 PRO");

		return new Object[][] { { map }, { map1 } };
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "chandrahas@gmail.com", "Micky@021", "ADIDAS ORIGINAL" },
				{ "micky@gmail.com", "Micky@021", "IPHONE 13 PRO" } };
	}
}
