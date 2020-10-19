Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add place payload with <name>, <language>, and <address>
	When usercalls "addPlaceAPI" with "post" http request
	Then the API call returns success with status code 200
	And "status" in response body is "OK"
	And verify "place_id" created maps to <name> using "getPlaceAPI"
	
Examples: 
	|	name	|	language	|	address		  |
	|Hebron		|Tswana			|Lethlabile Centre|
#	|Oskraal	|English		|Brits Centre	  |

@DeletePlace
Scenario: Verify if delete place functionality is working
	Given Delete payload with place_id
	When usercalls "deletePlaceAPI" with "post" http request
	Then the API call returns success with status code 200
	And "status" in response body is "OK"