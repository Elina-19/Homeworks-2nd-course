package main.java.ru.kpfu.itis.client;

import main.java.ru.kpfu.itis.protocol.Message;

public interface ClientExample {
  public void connect() throws ClientException;
  public Message sendMessage(Message message) throws ClientException;
}
