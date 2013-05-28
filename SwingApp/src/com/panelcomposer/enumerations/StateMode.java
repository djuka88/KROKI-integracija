package com.panelcomposer.enumerations;

   /** 
   File generated using Kroki EnumGenerator 
   @Author MiloradFilipovic 
   Creation date: 22.05.2013  10:56:57h
   **/

public enum StateMode {
	UPDATE("UPDATE"),
	ADD("ADD"),
	SEARCH("SEARCH");
	
	String label;
	
	StateMode() {
	}
	
	StateMode(String label) {
		this.label = label;
	}
	
}
