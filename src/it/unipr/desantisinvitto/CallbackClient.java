package it.unipr.desantisinvitto;

import java.rmi.registry.LocateRegistry;
import java.util.Random;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * The {@code CallbackClient} class defines the behavior of the clients.
 * Each client creates a remote object that will be used by the server
 * to retrieve possible offers for the products.
 * 
 * A client keeps running until it completes a certain number of purchases
 * (10 is default).
 * 
 * The client periodically retrieves the current price of the product and it
 * randomly generates a maximum purchase price; if the product price is less
 * than this value, the client makes an offer to the server. If the client
 * has made an offer it waits until it receives the outcome (either accepted or
 * rejected).
 * 
 * @author De Santis Fabrizio, Invitto Francesco
 *
 */
public class CallbackClient {
	private static final int MIN_PRICE = 10;
	private static final int MAX_PRICE = 200;
	private static final int PURCHASES = 10;

	public static void main(String[] args) throws Exception{
		Random random = new Random();
		Registry registry = LocateRegistry.getRegistry();
		
		ProductBuyer b = new ProductBuyerImpl();	//creates a remote object
		
		//looks up a reference to a remote object with external name "subscribe"
		Subscribe service = (Subscribe) registry.lookup("subscribe");
		service.subscribe(b);	//invokes the remote method on the server
		
		//looks up a reference to a remote object with external name "product"
		Product product = (Product) registry.lookup("product");
		
		int purchCount = 0;
		int max_offer, sell_price;
		
		while(purchCount < PURCHASES) {
			sell_price = product.getPrice();	//retrieves the price
			
			if(sell_price != 0) {
				max_offer = random.nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE;	//random generation of the maximum purchase price
				
				System.out.println("-------------------------");
				
				System.out.println("Maximum price: " + max_offer);
				System.out.println("Current price: " + sell_price);

				
				if(sell_price < max_offer) {
					System.out.println("Product at good price, I make an offer!");
					((ProductBuyerImpl)b).setOffer(max_offer);	//casting is necessary to use local method
					
					Boolean confirmed;
					
					do {
						Thread.sleep(1000);
						
						confirmed = ((ProductBuyerImpl)b).getConfirmed(); //casting is necessary to use local method
						
					}while(confirmed == null);	//the client waits until it receives the outcome of its offer
					
					if(confirmed) {
						purchCount++;
						System.out.println("Offer accepted! Product purchased at price: " + max_offer);
					}
					else {
						System.out.println("Offer rejected!");
					}
				
				}
				else {
					System.out.println("Product too expensive for me!");
				}
				
				System.out.println("-------------------------");
				
				b.setConfirmed(null);
				((ProductBuyerImpl) b).setOffer(0); //casting is necessary to use local method
						
			}
			
			Thread.sleep(2000);
		}
		
		System.out.println("I have finished my purchases.");
		service.unsubcribe(b);	
		UnicastRemoteObject.unexportObject(b, false);
	}

}
