#Author: Aldi Rahman - aldirhmn@gmail.com
@SearchProductFeature
Feature: Feature Search Product
  This Feature to Search some Product in Application

  @SearchProduct
  Scenario: User successfully Search Product
    Given User already in Home Page
    And User tap Search Product
    When User search some Product in Field Search Product with "Le Minerale"
    Then User successfully show Product

  @SearchPopulerProduct
  Scenario: User successfully Search Product by Choosing Populer Product
    Given User already in Home Page
    And User tap Search Product
    When User tap Recommandation Populer Product
    Then User successfully show Product
