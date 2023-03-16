package it.unipr.desantisinvitto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The {@code ProductBuyerImpl} class defines the remote objects created by the clients.
 * 
 * @author De Santis Fabrizio, Invitto Francesco
 *
 */
public class ProductBuyerImpl extends UnicastRemoteObject implements ProductBuyer{
	private static final long serialVersionUID = 1L;
	private int offer;
	private Boolean confirmed;
	
	/**
	 * Class constructor.
	 * 
	 * @throws RemoteException
	 */
	public ProductBuyerImpl() throws RemoteException{
		this.offer = 0;
		this.confirmed = null;
	}
	
	/**
	 * This method is called by the client to set the offer for the product.
	 * 
	 * @param o	the offer
	 */
	public void setOffer(final int o) {
		this.offer = o;
	}
	
	@Override
	public int getOffer() throws RemoteException{
		return this.offer;
	}
	
	@Override
	public void setConfirmed(final Boolean c) throws RemoteException{
		this.confirmed = c;
	}
	
	/**
	 * This method is called by the client to retrieve the outcome of the offer.
	 * 
	 * @return	the outcome of the offer
	 */
	public Boolean getConfirmed() {
		return this.confirmed;
	}
}
