package it.unipr.sowide.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class TemperatureReaderImpl extends UnicastRemoteObject implements TemperatureReader {
  private static final long serialVersionUID = 1L;

  private static final int MAX = 100;
  private static final int MIN = -100;

  private Random random;

  public TemperatureReaderImpl() throws RemoteException {
    this.random = new Random();
  }

  @Override
  public int getTemperature() throws RemoteException {
    return this.random.nextInt(MAX - MIN) + MIN;
  }
}
