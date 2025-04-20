Feature: Create Customer

  Background:
    Given User opens Siigo application
    And User logins into the application

  Scenario: Create new customer
    When User clicks the create customer option
    And User select the following customer types:
    |Clientes|
    |Proveedores|
    |Otros|
    And User select "Persona" as type
    And User select "Cedula de Ciudadania" as identification type and enter the value 123456789
    And User enter the value 1 as branch code
    And User enter the value "Yuli" as name
    And User enter the value "Ordoñez" as last name
    And User enter the value "Test Company" as company name
    And User enter the value "Cali" as city
    And User enter the value "Calle 123" as addres
    And User enter the value 57 1234567890 123 as phone number
    And User enter the value "Test" as contact name
    And User clicks on Save Button
    Then The Customer Identification is present in the Customer View
