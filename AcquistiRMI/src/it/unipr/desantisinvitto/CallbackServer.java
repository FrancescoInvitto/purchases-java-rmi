package it.unipr.desantisinvitto;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
		
		Subscribe service = new SubscribeImpl(buyers);
		registry.rebind("subscribe", service);
		
		Product product = new ProductImpl();
		registry.rebind("product", product);
		
		while(buyers.size() < MIN_CLIENTS) {
			System.out.println("Not enough clients to open the shop: " + buyers.size() + "/" + MIN_CLIENTS);
			Thread.sleep(2000);
		}
		
		int price;
		
		while(buyers.size() > 0) {
			price = random.nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE;
			((ProductImpl) product).setPrice(price);
			
			System.out.println("--------------------");

			System.out.println("Current price: " + price);
			
			try {	
				Thread.sleep(1000);
				
				for(ProductBuyer b : buyers) {
					int offer = b.getOffer();
					
					if(offer != 0) {
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
