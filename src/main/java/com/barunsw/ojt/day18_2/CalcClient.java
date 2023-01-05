package com.barunsw.ojt.day18_2;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalcClient {
	private static Logger LOGGER = LogManager.getLogger(CalcClient.class);
	
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 50000);
			
			Remote remote = registry.lookup("CALC");
			
			if (remote instanceof CalcInterface) {
				CalcInterface calc = (CalcInterface)remote;
				
				LOGGER.debug(String.format("1 + 2 = %s", calc.add(1, 2)));
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}
