@tag
  Feature: Place an order on a Ecommerce website

    Background:
      Given I landed on the Ecommerce website

      @Regression
      Scenario Outline: Positive test of placing an order

        Given Logged in with a username <name> and password <password>
        When I add product <productName> to cart
        And Checkout <productName> and submit the order
        Then Confirmation message appears "THANKYOU FOR THE ORDER."

        Examples:
          | name                 | password   | productName |
          | testaccount@test.com | Password1! | ZARA COAT 3 |