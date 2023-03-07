package it.unipr.sowide.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TemperatureServer {
  private static final int PORT = 1099;

  public static void main(final String [] args) throws Exception {
    Registry registry         = LocateRegistry.createRegistry(PORT);
    TemperatureReader service = new TemperatureReaderImpl();

    registry.rebind("temperature", service);
  }
}
