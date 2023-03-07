package it.unipr.sowide.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.Random;

public class UnrefTemperatureReaderImpl
  extends UnicastRemoteObject implements TemperatureReader, Unreferenced {
  private static final long serialVersionUID = 1L;

  private static final int MAX = 100;
  private static final int MIN = -100;

  private Random random;

  public UnrefTemperatureReaderImpl() throws RemoteException {
    this.random = new Random();

    // Opens logging file ...
  }

  public int getTemperature() throws RemoteException {
    int t = this.random.nextInt(MAX - MIN) + MIN;

    // Saves temperature ...

    return t; 
  }

  public void unreferenced() {
    // Closes file ...
  }
}
