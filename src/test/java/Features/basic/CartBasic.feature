Feature: Cart feature

  Scenario: Verify adding items to cart
    Given User is on product page
    When User adds product to cart
    Then Added products are available in cart page