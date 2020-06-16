Feature: SheinAutomation [TET - 006]

Scenario: [TC006] Positive flow for Shein Automation
Given User launches browser for Shein 
And User mouse hovers on Clothing and click Jeans
And User chooses Black under Jeans product count 
And User checks size as medium 
And User checks whether the color is black 
And User clicks first item to Add to Bag 
And User clicks the size as M abd click Submit 
When User clicks view Bag 
Then User checks the size is Medium or not and closes the browser
