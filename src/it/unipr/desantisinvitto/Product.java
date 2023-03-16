package it.unipr.desantisinvitto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The {@code Product} interface defines the remote methods that are
 * called by the clients to obtain information about the product.
 * 
 * @author De Santis Fabrizio, Invitto Francesco
 *
 */
public interface Product extends Remote{
	/**
	 * This method is used by the clients to retrieve the current price of the product.
	 * 
	 * @return	the current price of the product
	 * @throws RemoteException
	 */
	int getPrice() throws RemoteException;
}
