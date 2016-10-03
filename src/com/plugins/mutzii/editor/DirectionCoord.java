package com.plugins.mutzii.editor;

import com.plugins.mutzii.enums.Direction;

public class DirectionCoord {

	private Direction direction;
	private int x;
	private int y;
	private int z;
	
	public DirectionCoord(Direction direction,int x,int y, int z){
		
		this.direction = direction;
		this.x         = x;
		this.y         = y;
		this.z         = z;
	}
	
	public Direction getDirection(){
		return this.direction;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getZ(){
		return this.z;
	}
	
	
}
