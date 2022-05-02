Feature: Checking cases

  Scenario: Get cases worldwide
    When I navigate to "http://localhost:4200/"
    And I select no filters
    Then Result country should be "World"

  Scenario: Get invalid country
    When I navigate to "http://localhost:4200/"
    And I select country "Aveiro"
    Then Result value should be "" and result country should be ""

  Scenario: Get invalid date range
    When I navigate to "http://localhost:4200/"
    And I select date range starting in "02-05-2022" and ending in "01-05-2022"
    Then Result value should be "" and result country should be "World"