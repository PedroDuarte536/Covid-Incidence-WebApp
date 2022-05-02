Feature: Reserve a ticket

  Scenario: Choose destination
    When I navigate to "https://blazedemo.com/"
    And I choose travelling from "<from>" to "<to>"
    Then I should see the title "Flights from <from> to <to>:"

    Examples:
        | from | to |
        | Portland | Cairo |