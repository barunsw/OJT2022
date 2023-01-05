package com.barunsw.ojt.day18_2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalcServer {
	private static Logger LOGGER = LogManager.getLogger(CalcServer.class);
	
	public static void main(String[] args) {
		LOGGER.debug("+++ CalcServer");
		
		try {
			// RMI registry를 local에 생성
			Registry registry = LocateRegistry.createRegistry(50000);
			
			CalcInterface calc = new CalcImpl();
			
			// RMI registry에 등록
			registry.rebind("CALC", calc);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		LOGGER.debug("--- CalcServer");
	}
}
