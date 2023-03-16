package it.unipr.desantisinvitto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The {@code Subscribe} interface defines the remote methods called by the clients
 * to enter/exit the list of clients receiving the updates of the product.
 * 
 * @author De Santis Fabrizio, Invitto Francesco
 *
 */
public interface Subscribe extends Remote {
	/**
	 * This method is called by the clients to enter the list.
	 * 
	 * @param b	the remote object of the client
	 * @throws RemoteException
	 */
	void subscribe(final ProductBuyer b) throws RemoteException;
	
	/**
	 * This method is called by the clients to exit the list.
	 * 
	 * @param b	the remote object of the client
	 * @throws RemoteException
	 */
	void unsubcribe(final ProductBuyer b) throws RemoteException;
}
