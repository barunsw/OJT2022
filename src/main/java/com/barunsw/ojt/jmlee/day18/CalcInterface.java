package com.barunsw.ojt.jmlee.day18;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalcInterface extends Remote {
	public int add(int a, int b) throws RemoteException;
	public int substract(int a, int b) throws RemoteException;
	public int multiply(int a, int b) throws RemoteException;
	public int devide(int a, int b) throws RemoteException;
}
