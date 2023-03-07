package it.unipr.sowide.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * The {@code TemperatureClient} class provides an implementation of a RMI
 * client that requires a service to a RMI server.
 *
**/

public class TemperatureClient {
  public static void main(final String [] args) throws Exception {
    Registry registry = LocateRegistry.getRegistry();

    TemperatureReader service =
        (TemperatureReader) registry.lookup("temperature");

    System.out.println("Temperature is " + service.getTemperature());
  }
}
