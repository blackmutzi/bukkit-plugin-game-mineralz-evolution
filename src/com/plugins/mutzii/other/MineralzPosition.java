package com.plugins.mutzii.other;

public class MineralzPosition {

	private int px;
	private int py;
	private int pz;
	
	public MineralzPosition(int x, int y, int z){
		this.px = x;
		this.py = y;
		this.pz = z;
	}
	
	public int getX(){
		return this.px;
	}
	
	public int getY(){
		return this.py;
	}
	
	public int getZ(){
		return this.pz;
	}
	
	
}
