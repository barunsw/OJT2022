package com.barunsw.ojt.jmlee.day16;

public enum CmdType {
	SELECT,
	SELECTONE,
	INSERT,
	UPDATE,
	DELETE;

public static CmdType getCmdType(String cmd) {
		switch (cmd) {
		case "SELECT":
			return SELECT;
		case "SELECTONE":
			return SELECTONE;
		case "INSERT":
			return INSERT;
		case "UPDATE":
			return UPDATE;
		case "DELETE":
			return DELETE;
		default:
			return null;			
		}		
	}
}
