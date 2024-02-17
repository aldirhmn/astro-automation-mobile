#Author: Aldi Rahman - aldirhmn@gmail.com
@LoginPositiveCase
Feature: Menu Login
  User login into application use valid phone number that already registred

  @Login
  Scenario: User succescfully Login into Astro Application and Direct into Homepage
    Given User open the Astro Application
    When User input their valid phone number into Login Page with data "081291833109"
    And User input OTP that sended
    Then User successfully Login into Astro Application and Direct to HomePage