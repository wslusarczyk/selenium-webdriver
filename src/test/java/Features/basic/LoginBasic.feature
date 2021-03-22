Feature: Portal login

  Scenario: Verify invalid data
    Given User is on login page
    When User tries to login on the page with incorrect mail and corret password
    Then An error message is displayed

  Scenario: Verify valid email and empty password
    Given User is on login page 1
    When User tries to login on the page with correct email and empty password
    Then An error message is displayed 1

  Scenario: Verify valid email and password
    Given User is on login page 2
    When User tries to login on the page with incorrect email and password
    Then Then An error message is displayed 2
