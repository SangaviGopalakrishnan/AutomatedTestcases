Feature: BigBasket2Automation [TET - 004]

Scenario: [TC004] Positive flow for BigBasket2 Automation
Given User launches the browser for Big Basket
And User mouse hovers on  Shop by Category 
And User goes to FOODGRAINS, OIL & MASALA and RICE & RICE PRODUCTS 
And User clicks on BOILED & STEAM RICE 
And User gets the URL of the page and check it with site navigation link HOME > FOODGRAINS, OIL & MASALA> RICE & RICE PRODUCTS> BOILED & STEAM RICE 
And User chooses the Brand as bb Royal 
And User goes to Ponni Boiled Rice and select 10kg bag from Dropdown and User clicks Add button 
And User goes to search box and type Dal
And User adds Toor/Arhar Dal 2kg and set Qty2 from the list 
And User clicks Address 
And User selects Chennai as City, Alandur-600016,Chennai as Area  and click Continue 
And User mouse hovers on My Basket take a screen shot 
When clicks View Basket and Checkout
Then clicks the close button and close the browser