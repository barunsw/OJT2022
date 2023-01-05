package com.barunsw.ojt.day18_2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalcImpl extends UnicastRemoteObject implements CalcInterface {
	private static Logger LOGGER = LogManager.getLogger(CalcImpl.class);
	
	public CalcImpl() throws RemoteException {
	}
	
	@Override
	public int add(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub		
		return a + b;
	}

	@Override
	public int substract(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a - b;
	}

	@Override
	public int multiply(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a * b;
	}

	@Override
	public int devide(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a / b;
	}
}
