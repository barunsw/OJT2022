package com.barunsw.ojt.jmlee.day18;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalcClient {
	private static Logger LOGGER = LogManager.getLogger(CalcClient.class);
	
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 50000); // 같은서버여야 함
			
			Remote remote = registry.lookup("CALC"); // 클라이언트는 서버가 등록한 registry에 붙기위해 
													// lookup으로 찾아 연결
			if (remote instanceof CalcInterface) { // 서버에 해당 인터페이스가 존재하는지 확인
				CalcInterface calc = (CalcInterface)remote; // 객체생성 없이 캐스팅하여 메서드 호출
				
				LOGGER.debug(String.format("1 + 2 = %s", calc.add(1, 2)));
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}
