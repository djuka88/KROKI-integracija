package com.panelcomposer.enumerations;

   /** 
   File generated using Kroki EnumGenerator 
   @Author MiloradFilipovic 
   Creation date: 22.05.2013  10:56:57h
   **/

public enum PanelType {
	STANDARDPANEL("StandardPanel"),
	PARENTCHILDPANEL("ParentChildPanel"),
	MANYTOMANYPANEL("ManyToManyPanel");
	
	String label;
	
	PanelType() {
	}
	
	PanelType(String label) {
		this.label = label;
	}
	
}
