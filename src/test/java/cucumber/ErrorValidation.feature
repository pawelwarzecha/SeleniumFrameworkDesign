@tag
  Feature: ErrorValidation

    @ErrorValidation
    Scenario Outline:
      Given I landed on the Ecommerce website
      When Logged in with a username <name> and password <password>
      Then "Incorrect email or password." message is displayed
      Examples:
        | name                 | password |
        | testaccount@test.com | Password |