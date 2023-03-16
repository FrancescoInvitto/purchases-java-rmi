package it.unipr.desantisinvitto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The {@code ProductImpl} class defines the remote object representing the 
 * product.
 * 
 * @author De Santis Fabrizio, Invitto Francesco
 *
 */
public class ProductImpl extends UnicastRemoteObject implements Product{
	private static final long serialVersionUID = 1L;
	
	private int price;
	
	/**
	 * Class constructor.
	 * 
	 * @throws RemoteException
	 */
	public ProductImpl() throws RemoteException{
		this.price = 0;
	}

	@Override
	public int getPrice() throws RemoteException {
		return this.price;
	}
	
	/**
	 * This method is used by the server to update the current price of the product.
	 * 
	 * @param p	the new product price
	 */
	public void setPrice(final int p) {
		this.price = p;
	}

}
