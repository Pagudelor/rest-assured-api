Feature: Validate place Api

 @AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceApi
    Given Add place payload with <name> <languaje> <address>
    When  user calls "AddPlaceAPI" with "post" http request
    Then  "status" in response body should be "OK"
    And   verify "place_id" created maps to <name> using "getPlaceAPI"

    Examples:
    |name  |languaje |address             |
    |"name"|"english"|"world cross center"|

  @DeletePlace
Scenario: Verify if delete place functionality is working
  Given Delete place payload
  When user calls "deletePlaceAPI" with "post" http request
  Then "status" in response body should be "OK"