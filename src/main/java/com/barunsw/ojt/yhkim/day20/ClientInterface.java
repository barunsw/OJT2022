package com.barunsw.ojt.yhkim.day20;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
	public void push(String msg) throws RemoteException;
}
