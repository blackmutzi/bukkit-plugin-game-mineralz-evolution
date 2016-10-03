package com.plugins.mutzii.editor;

import org.bukkit.block.Block;

public class Coordinaten {
	
	private MyBlock block;
	private int x;
	private int y;
	private int z;
	
	public Coordinaten(Block block,int x,int y,int z){
		this.block = new MyBlock(block.getTypeId(),block.getData());
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Coordinaten(MyBlock block,int x,int y, int z){
		this.block = block;
		this.x     = x;
		this.y     = y;
		this.z     = z;
	}
	
	public  int getX(){
		return this.x;
	}
	
	public  int getY(){
		return this.y;
	}
	
	public int getZ(){
		return this.z;
	}
	
	public MyBlock getBlock(){
		return this.block;
	}
	
}
