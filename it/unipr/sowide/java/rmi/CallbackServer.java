package it.unipr.sowide.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class CallbackServer
{
  private static final int PORT = 1099;
  private static final int MAX = 100;
  private static final int MIN = -100;

  public static void main(final String[] args) throws Exception
  {
    Random random = new Random();
    Registry registry = LocateRegistry.createRegistry(PORT);

    Set<TemperatureWriter> writers = new CopyOnWriteArraySet<>();

    Subscribe service = new SubscribeImpl(writers);

    registry.rebind("subscribe", service);

    while (true)
    {
      int t = random.nextInt(MAX - MIN) + MIN;

      try
      {
        for (TemperatureWriter w : writers)
        {
          w.putTemperature(t);
        }

        Thread.sleep(1000);
      }
      catch (Exception e)
      {
        continue;
      }
    }
  }
}
