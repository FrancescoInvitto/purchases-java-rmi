package it.unipr.desantisinvitto;

import java.rmi.registry.LocateRegistry;
import java.util.Random;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CallbackClient {
	private static final int MIN_PRICE = 10;
	private static final int MAX_PRICE = 200;
	private static final int PURCHASES = 3;

	public static void main(String[] args) throws Exception{
		Random random = new Random();
		Registry registry = LocateRegistry.getRegistry();
		
		ProductBuyer b = new ProductBuyerImpl();
		
		Subscribe service = (Subscribe) registry.lookup("subscribe");
		service.subscribe(b);
		
		Product product = (Product) registry.lookup("product");
		
		int purchCount = 0;
		int max_price, sell_price;
		
		while(purchCount < PURCHASES) {
			sell_price = product.getPrice();
			
			if(sell_price != 0) {
				max_price = random.nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE;
				
				System.out.println("-------------------------");
				
				System.out.println("Maximum price: " + max_price);
				System.out.println("Current price: " + sell_price);

				
				if(sell_price < max_price) {
					System.out.println("Product at good price, I make an offer!");
					((ProductBuyerImpl)b).setOffer(max_price);
					
					Boolean confirmed;
					
					do {
						Thread.sleep(1000);
						
						confirmed = ((ProductBuyerImpl)b).getConfirmed();
						
					}while(confirmed == null);
					
					if(confirmed) {
						purchCount++;
						System.out.println("Offer accepted! Product purchased at price: " + max_price);
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
				((ProductBuyerImpl) b).setOffer(0);
						
			}
			
			Thread.sleep(2000);
		}
		
		System.out.println("I have finished my purchases.");
		service.unsubcribe(b);	
		UnicastRemoteObject.unexportObject(b, false);
	}

}
