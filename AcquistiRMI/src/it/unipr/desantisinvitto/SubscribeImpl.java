package it.unipr.desantisinvitto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public class SubscribeImpl extends UnicastRemoteObject implements Subscribe {

	private static final long serialVersionUID = 1L;
	
	private Set<ProductBuyer> buyers;
		
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
