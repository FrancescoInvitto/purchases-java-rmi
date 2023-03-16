package it.unipr.desantisinvitto;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The {@code CallbackServer} class defines the behavior of the server.
 * The server starts selling its product only when at least 3 clients have subscribed.
 * 
 * It keeps running until all the clients have completed their purchases.
 * 
 * The server periodically generated a new random price for the product and it
 * updates it using the reference of its own object (accessible remotely by all the clients).
 * Then it checks if some client has made an offer; in that case it performs a further check,
 * to see if the offer is greater than the current price of the product. If this condition is
 * satisfied the offer is accepted, otherwise it is rejected.
 * 
 * @author De Santis Fabrizio, Invitto Francesco
 *
 */
public class CallbackServer {
	private static final int PORT = 1099;
	private static final int MIN_PRICE = 10;
	private static final int MAX_PRICE = 200;
	private static final int MIN_CLIENTS = 3;

	public CallbackServer() {
	}

	public static void main(String[] args) throws Exception{
		Random random = new Random();
		Registry registry = LocateRegistry.createRegistry(PORT);
		
		Set<ProductBuyer> buyers = new CopyOnWriteArraySet<>();	
		
		Subscribe service = new SubscribeImpl(buyers);	//creates a remote object
		registry.rebind("subscribe", service);	//publishes a remote reference to that object with external name "subscribe"
		
		Product product = new ProductImpl(); //creates remote object
		registry.rebind("product", product);	//publishes a remote reference to that object with external name "product"
		
		while(buyers.size() < MIN_CLIENTS) {	//the server starts when at least 3 clients are subscribed
			System.out.println("Not enough clients to open the shop: " + buyers.size() + "/" + MIN_CLIENTS);
			Thread.sleep(2000);
		}
		
		int price;
		
		while(buyers.size() > 0) {	//the server continues until all the clients have completed their purchases
			price = random.nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE;
			((ProductImpl) product).setPrice(price);	//updates the price, casting is necessary to use local method
			
			System.out.println("--------------------");

			System.out.println("Current price: " + price);
			
			try {	
				Thread.sleep(1000);
				
				for(ProductBuyer b : buyers) {
					int offer = b.getOffer();	//retrieves client offer
					
					if(offer != 0) { // if offer == 0, the client has not made an offer
						System.out.println("Client offer: " + offer);
						
						if(offer >= price) {
							//accepted
							b.setConfirmed(true);
							System.out.println("Offer accepted!");
						}
						else {
							//rejected
							b.setConfirmed(false);
							System.out.println("Offer rejected!");
						}
					}
						
				}
				
				System.out.println("--------------------");
				Thread.sleep(500);
			}
			catch(Exception e) {
				continue;
			}		
		}
		
		System.out.println("All clients have disconnected. I close the shop.");
		UnicastRemoteObject.unexportObject(service, false);
		UnicastRemoteObject.unexportObject(product, false);
	}

}
