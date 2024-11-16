@tag
Feature: Purchase the order from eCommerce Website
  I want to use this template for my feature file

  Background: 
    Given I landed on eCommerce Page

  @ErrorValidation
  Scenario Outline: Error validation with Invalid credentials
    Given Logged in with username <Email> and password <Password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | Email         | Password  |
      | cucumber@gmail.com | Micky@021 |
