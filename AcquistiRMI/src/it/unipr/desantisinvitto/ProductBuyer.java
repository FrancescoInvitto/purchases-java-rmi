package it.unipr.desantisinvitto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProductBuyer extends Remote {
	int getOffer() throws RemoteException;
	void setConfirmed(final Boolean c) throws RemoteException;
}
