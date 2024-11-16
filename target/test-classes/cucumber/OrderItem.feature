@tag
Feature: Purchase the order from eCommerce Website
  I want to use this template for my feature file

  Background: 
    Given I landed on eCommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <Email> and password <Password>
    When I add product <ItemName> to Cart
    And Checkout <ItemName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | Email                | Password  | ItemName        |
      | chandrahas@gmail.com | Micky@021 | ADIDAS ORIGINAL |