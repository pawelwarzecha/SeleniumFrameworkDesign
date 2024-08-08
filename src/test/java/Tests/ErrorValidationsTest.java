package Tests;

import PageObjects.*;
import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException {

        String productName = "ZARA COAT 3";
        landingPage.loginApplication("testaccount@test.com", "Password");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }
    @Test
    public void productErrorValidation() throws IOException, InterruptedException {

        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("testaccount@test.com", "Password1!");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }

}
