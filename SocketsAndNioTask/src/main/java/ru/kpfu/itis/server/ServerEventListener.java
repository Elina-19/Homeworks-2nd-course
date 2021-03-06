package main.java.ru.kpfu.itis.server;

import java.net.Socket;
import main.java.ru.kpfu.itis.protocol.Message;

public interface ServerEventListener {
  public void init(ServerExample server);
  public void handle(int connectionId, Message message) throws ServerEventListenerException;
  public int getType();
}
