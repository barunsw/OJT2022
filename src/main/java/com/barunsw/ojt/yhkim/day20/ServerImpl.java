package com.barunsw.ojt.yhkim.day20;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
   private static final Logger LOGGER = LogManager.getLogger(ServerImpl.class);
   
   private Map<User, ClientInterface> clientRepo = new ConcurrentHashMap<>();
   
   private int seqNum = 1;
   
   public ServerImpl() throws RemoteException {
      super();
   }
   
   @Override
   public void register(User user, ClientInterface clientInterface) throws RemoteException {
      LOGGER.debug("register");
      
      synchronized (clientRepo) {
         clientRepo.put(user, clientInterface);
      }
   }
   
   @Override
   public void deregister(User user) throws RemoteException {
      LOGGER.debug("deregister");
      
      synchronized (clientRepo) {
         clientRepo.remove(user);
      }
   }

   @Override
   public void send(User user, String msg) throws RemoteException {
      LOGGER.debug(String.format("Server send  id : [%d] / name : [%s]", user.getSeqNum(), user.getName()));
      
      synchronized (clientRepo) {
         for (ClientInterface oneClient : clientRepo.values()) {
            oneClient.push(String.format("[%s] %s", user.getName(), msg));
         }
      }
   }
   
   @Override
   public int getSeqNum() {
      return seqNum++;
   }
}