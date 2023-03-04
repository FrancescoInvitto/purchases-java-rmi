# AcquistiRMI
A simple Distributed System project using Java RMI.
In this application a server offers a product at a certain price, that changes over time. A client each time a new price is generated, selects a random maximum price; if the price of the product is less than the maximum generated value, the client sends an offer to the server. The server checks if the offer is greater than the current price and, in that case, the offer is accepted.
