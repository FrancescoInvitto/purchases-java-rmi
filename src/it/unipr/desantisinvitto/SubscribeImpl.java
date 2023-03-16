package it.unipr.desantisinvitto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

/**
 * The {@code SubscribeImpl} class defines the remote object created by the server
 * in order to know how many clients can make an offer for the product it sells.
 * 
 * @author De Santis Fabrizio, Invitto Francesco
 *
 */
public class SubscribeImpl extends UnicastRemoteObject implements Subscribe {

	private static final long serialVersionUID = 1L;
	
	private Set<ProductBuyer> buyers;
		
	/**
	 * Class constructor.
	 * 
	 * @param s	the set of clients' remote objects
	 * @throws RemoteException
	 */
	public SubscribeImpl(final Set<ProductBuyer> s) throws RemoteException{
		this.buyers = s;
	}

	@Override
	public void subscribe(ProductBuyer b) throws RemoteException {
		this.buyers.add(b);
	}
	
	@Override
	public void unsubcribe(ProductBuyer b) throws RemoteException{
		this.buyers.remove(b);
	}
}
