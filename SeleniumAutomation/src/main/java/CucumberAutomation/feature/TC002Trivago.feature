Feature: TrivagoAutomation [TET - 002]

Scenario: [TC002] Positive flow for Trivago Automation
Given open the trivago website
And Type Agra in Destination and select Agra, Uttar Pradesh
And Choose June25 as check in and June27 as check out
And Select Room as Family Room
And Choose Number of Adults2, Childern1 and set Child's Age4
And Click Confirm button 
And click Search
And Select Accommodation type as Hotels only and choose 4stars
And Select Guest rating as Very Good
And Set Hotel Location as Agra Fort 
And click Done
And In more Filters, select Air conditioning, Restaurant and WiFi and click Done
And Sort the result as Rating & Recommended
And Print the Hotel name, Rating, Number of Reviews 
And Click View Deal
And Print the URL of the Page
When Print the Price of the Room and click Choose Your Room
Then Click Reserve and I'll Reserve