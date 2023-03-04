package it.unipr.desantisinvitto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Subscribe extends Remote {
	void subscribe(final ProductBuyer b) throws RemoteException;
	void unsubcribe(final ProductBuyer b) throws RemoteException;
}
