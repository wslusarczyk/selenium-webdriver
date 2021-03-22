Feature: Portal registration

  Scenario: Verify redirecting to register page
    Given User is on authentication page
    When User clicks on Create an account button
    Then User is redirected to register page

  Scenario: Verify data used for registering
    Given User is on registering page
    When User enters empty first name, 4 characters password and invalid mobile phone number
    Then An error message containing invalid fields is displayed

  Scenario: Verify registering with valid data
    Given User is on registering page 2
    When User enters valid data
    Then An account is created, user is redirected to My account page

  Scenario: Verify logging in with account created previously
    Given User is on authentication page 2
    When User enters correct email and password
    Then User is redirected to My account page