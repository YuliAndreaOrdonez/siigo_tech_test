Feature: Req Res API

  Scenario: Search for a User and update it
    Given User get all users
    And User get details of the first user in the list
    When User change the user name for "Test"
    Then User deletes the modified user