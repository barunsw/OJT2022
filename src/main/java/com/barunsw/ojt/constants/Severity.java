package com.barunsw.ojt.constants;

public class Severity {
	public static int CRITICAL 	= 0;
	public static int MAJOR 	= 1;
	public static int MINOR 	= 2;
	public static int NORMAL 	= 3;
	
	public static Severity CRI = new Severity(CRITICAL);
	public static Severity MAJ = new Severity(MAJOR);
	public static Severity MIN = new Severity(MINOR);
	public static Severity NOM = new Severity(NORMAL);
	
	private int value;
	private Severity(int value) {
		this.value = value;
	}
	
	public static Severity[] items = {CRI, MAJ, MIN, NOM};
	
	@Override
	public String toString() {
		if (this.value == CRITICAL)
			return "CRITICAL";
		else if (this.value == MAJOR)
			return "MAJOR";
		else if (this.value == MINOR)
			return "MINOR";
		else if (this.value == NORMAL)
			return "NORMAL";
		
		return null;
	}
}
