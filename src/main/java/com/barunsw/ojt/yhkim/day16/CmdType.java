package com.barunsw.ojt.yhkim.day16;

public enum CmdType {
	SELECT,
	INSERT,
	UPDATE,
	DELETE;
	
	public static CmdType getCmdType(String value) {
		switch(value) {
		case "SELECT":
			return SELECT;
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
