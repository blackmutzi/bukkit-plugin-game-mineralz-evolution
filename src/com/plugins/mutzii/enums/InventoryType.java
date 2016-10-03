package com.plugins.mutzii.enums;

public enum InventoryType {

	SMALL(9),
	MIDDLE(18),
	BIG(27),
	DOUBLEBIG(54);
	
	private final int size;
	
	InventoryType(int size){
		this.size = size;
	}
	
	public int getNumber(){
		return this.size;
	}
	
	
}
