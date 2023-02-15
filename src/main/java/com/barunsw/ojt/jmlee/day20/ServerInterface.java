package com.barunsw.ojt.jmlee.day20;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
   public void register(User user, ClientInterface clientInterface) throws RemoteException;
   public void deregister(User user) throws RemoteException;
   public void send(User user, String msg) throws RemoteException;
   public int getSeqNum() throws RemoteException;
}