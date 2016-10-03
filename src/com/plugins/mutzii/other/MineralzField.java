package com.plugins.mutzii.other;

public class MineralzField {

	private int start_vx;
	private int start_vy;
	private int start_vz;
	
	private int end_vx;
	private int end_vy;
	private int end_vz;
	
	public MineralzField(){}
	
	public MineralzField(int start_x, int start_y, int start_z,int end_x,int end_y, int end_z){
	   
		this.start_vx = start_x;
		this.start_vy = start_y;
		this.start_vz = start_z;
		
		this.end_vx  = end_x;
		this.end_vy  = end_y;
		this.end_vz  = end_z;
	}
	
	
	public int getStartX(){
		return this.start_vx;
	}
	
	public void setStartX(int x){
		this.start_vx = x;
	}
	
	public int getStartY(){
		return this.start_vy;
	}
	
	public void setStartY(int y){
		this.start_vy = y;
	}
	
	
	public int getStartZ(){
		return this.start_vz;
	}
	
	public void setStartZ(int z){
		this.start_vz = z;
	}
	
	
	public int getEndX(){
		return this.end_vx;
	}
	
	public void setEndX(int x){
		this.end_vx = x;
	}
	
	
	public int getEndY(){
		return this.end_vy;
	}
	
	public void setEndY(int y){
		this.end_vy = y;
	}
	
	
	public int getEndZ(){
		return this.end_vz;
	}
	
	public void setEndZ(int z){
		this.end_vz = z;
	}
	
	
}
