Feature: BigBasket2Automation [TET - 004]

Scenario: [TC007] Positive flow for BigBasket2 Automation
Given Go to link https://www.bigbasket.com
And mouse over on to Shop by Category 
And Go to Beverages and Fruit juices & Drinks
And Click on JUICES
And click Tropicana and Real under Brand and Check count of the products from each Brands and total count
And Check whether the products is availabe with Add button.
And Add the First listed available product
And click on Address
And Select Chennai as City, Alandur as Area and click Continue 
And Mouse over on My Basket print the product name. count and price.
When Click View Basket & Checkout
Then Click the close button