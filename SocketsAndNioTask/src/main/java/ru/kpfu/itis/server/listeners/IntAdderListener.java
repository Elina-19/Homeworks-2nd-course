package main.java.ru.kpfu.itis.server.listeners;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import main.java.ru.kpfu.itis.protocol.Message;
import main.java.ru.kpfu.itis.server.AbstractServerEventListener;
import main.java.ru.kpfu.itis.server.ServerEventListenerException;
import main.java.ru.kpfu.itis.server.ServerException;

public class IntAdderListener extends AbstractServerEventListener{

  
  @Override
  public void handle(int connectionId, Message message) throws ServerEventListenerException {
    if(!this.init){
      throw new ServerEventListenerException("Listener has not been initiated yet.");
    }
    // Could be more optimized and safer
    IntBuffer buffer = ByteBuffer.wrap(message.getData()).asIntBuffer();
    int summ = buffer.get(0) + buffer.get(1);
    int param = buffer.get(2);

    Boolean result;
    Message answer;
    if (param == summ){
      result = true;
      answer = Message.createMessage(Message.TYPE3, ByteBuffer.allocate(4).put(result.toString().getBytes()).array());
    }else {
      result = false;
      answer = Message.createMessage(Message.TYPE3, ByteBuffer.allocate(5).put(result.toString().getBytes()).array());
    }
    try{
      this.server.sendMessage(connectionId, answer);
    } catch (ServerException ex) {
      //Add some catch implementation
    }
  }

  @Override
  public int getType() {
    return Message.TYPE1;
  }

}
