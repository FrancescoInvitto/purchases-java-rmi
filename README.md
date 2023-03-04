# AcquistiRMI
A simple Distributed System project using Java RMI.

The goal is to create a client - server application for the purchase of products at an advantageous cost. This application involves a server and a certain number of clients (at least 3).
The application will have to be developed with RMI.
The products have a price that varies within a range between 10 and 200 €.
The server periodically randomly generates sales prices and informs clients of their value.
Each customer receives the prices of the products from time to time, randomly generates the maximum purchase price (always in the range between 10 and 200 €) and sends a purchase request to the server if the sale price is lower than the price maximum purchase.
Once the purchase request has been received, the server sends a sale confirmation if the purchase price is greater than or equal to the current sale price; otherwise send a rejection message.
Each client terminates its activity after the same number of purchases (at least 10).
When all clients have completed their purchases, the server terminates the application.
