Feature: Fares and payments

  As a transport for london customer
  I want to see the fares for my journey
  So that I know the journey travel cost

  Scenario: User can find single fare
    Given A customer is at the single fare finder page
    When I check fares between Twickenham Rail Station and Feltham Rail Station
    Then the fares are displayed
