Feature: ShopCluesAutomation [TET - 005]

Scenario: [TC005] Positive flow for ShopClues Automation
Given User launches the Browser for ShopClues 
And User mouse hovers on women and clicks Casual Shoes 
And User selects colour as Black 
And User checks whether the product name contains the word Black and If so user adds the product name and price to Map
And User checks display the count of shoes which are display more than 500Rupees 
And User clicks the highest price of the Shoe 
And User gets the current page Url and checks with the product ID 
And User copies the offer code 
And User selects the size, colour and clicks Add to Cart 
And User mouse hovers on Shopping Cart and clicks View Cart 
When User types Pincode as 600016 clicks Submit and Place Order 
Then User closes the Browser 
