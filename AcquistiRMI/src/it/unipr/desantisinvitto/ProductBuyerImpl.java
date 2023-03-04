package it.unipr.desantisinvitto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;

public class ProductBuyerImpl extends UnicastRemoteObject implements ProductBuyer, Unreferenced {
	private static final long serialVersionUID = 1L;
	private int offer;
	private Boolean confirmed;
	
	public ProductBuyerImpl() throws RemoteException{
		this.offer = 0;
		this.confirmed = null;
	}
	
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
	
	public Boolean getConfirmed() {
		return this.confirmed;
	}
	
	public void unreferenced() {
		System.out.println("Finish.");
	}

}
