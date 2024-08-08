package stepDefinitions;

import PageObjects.*;
import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class stepDefinitionImplementation extends BaseTest {

    public LandingPage  landingpage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on the Ecommerce website")
    public void I_landed_on_the_Ecommerce_website() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with a username (.+) and password (.+)$")
    public void Logged_in_with_a_username_and_password(String username, String password){
        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }
    @And("^Checkout (.+) and submit the order$")
    public void Checkout_product_and_submit_the_order(String productName){
        cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("poland");
        confirmationPage = checkoutPage.submitOrder();
    }
    @Then("Confirmation message appears {string}")
    public void Confirmation_message_appears(String string){
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }
    @Then("^\"([^\"]*)\" message is displayed$")
    public void message_is_displayed(String strArg1){
        Assert.assertEquals(strArg1, landingPage.getErrorMessage());
        driver.close();
    }

}
