package it.unipr.desantisinvitto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The {@code ProductBuyer} interface defines the remote methods called by the
 * server in order to retrieve the offer of the clients and to communicate the
 * outcome of such offers.
 * 
 * @author De Santis Fabrizio, Invitto Francesco
 *
 */
public interface ProductBuyer extends Remote {
	/**
	 * This method is called by the server to retrieve the offer for the product.
	 * 
	 * @return	the client's offer
	 * @throws RemoteException
	 */
	int getOffer() throws RemoteException;
	
	/**
	 * This method is called by the server to communicate the outcome of the
	 * client's offer (true if accepted, false if rejected).
	 * 
	 * @param c	the outcome of the offer
	 * @throws RemoteException
	 */
	void setConfirmed(final Boolean c) throws RemoteException;
}
