package Tests;

import PageObjects.*;
import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("poland");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
        public void OrderHistoryTest(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("testaccount@test.com", "Password1!");
        OrderPage ordersPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\HP Compat\\Documents\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}

//HashMap<String, String> map = new HashMap<String, String>();
//map.put("email", "testaccount@test.com");
//map.put("password", "Password1!");
//map.put("product", "ZARA COAT 3");

//HashMap<String, String> map1 = new HashMap<String, String>();
//map1.put("email", "testaccount1@test.com");
//map1.put("password", "Password2!");
//map1.put("product", "ADIDAS ORIGINAL");