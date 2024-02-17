#Author: Aldi Rahman - aldirhmn@gmail.com
@AddingCartProduct
Feature: Adding to Cart
  User Adding some Product to Cart

  @AddingCartFromSearch
  Scenario: User successfully adding Product to Cart
    Given User already have List Product to Add
    When User tap plus button to Add to Cart
    Then User successfully adding Product to Cart

  @AddingProductFromDetail
  Scenario: User successfully adding Product from Detail Product
    Given User already have List Product to Add
    And User tap the Product to Detail Product
    When User tap button Keranjang in Detail Product
    Then User successfully adding Product to Cart

  @AddingProductFromHome
  Scenario: User successfully adding Product from Home Page
    Given User already in Home Page
    And User tap some Kategori in Home Page
    When User tap plus button to Add to Cart
    Then User successfully adding Product to Cart
