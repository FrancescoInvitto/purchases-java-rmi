package it.unipr.desantisinvitto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProductImpl extends UnicastRemoteObject implements Product{
	private static final long serialVersionUID = 1L;
	
	private int price;
	
	public ProductImpl() throws RemoteException{
		this.price = 0;
	}

	@Override
	public int getPrice() throws RemoteException {
		return this.price;
	}
	
	public void setPrice(final int p) {
		this.price = p;
	}

}
