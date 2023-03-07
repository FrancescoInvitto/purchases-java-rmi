package it.unipr.sowide.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CallbackClient {
  public static void main(final String [] args) throws Exception {
    Registry registry   = LocateRegistry.getRegistry();
    TemperatureWriter w = new TemperatureWriterImpl();
    Subscribe service   = (Subscribe) registry.lookup("subscribe");

    service.subscribe(w);
  }
}
