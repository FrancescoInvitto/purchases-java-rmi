package it.unipr.desantisinvitto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Product extends Remote{
	int getPrice() throws RemoteException;
}
