package com.barunsw.ojt.constants;

public enum Gender {
	MAN,
	WOMAN;
	
	public static Gender getGender(String value) {
		switch(value) {
		case "남":
		case "남자":
		case "MAN":
		case "M":
			return MAN;
		case "여":
		case "여자":
		case "WOMAN":
		case "W":
			return WOMAN;
		default:
			return null;
		}
	}
}
