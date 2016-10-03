package com.plugins.mutzii.config;

import java.util.List;

import com.plugins.mutzii.editor.BlockFieldList;

public class FileInput {

	protected List<BlockFieldList> fieldList;
	protected int x;
	protected int y;
	protected int z;
	
	public FileInput(List<BlockFieldList> fieldList,int diffX,int diffY, int diffZ){
		this.fieldList = fieldList;
		this.x = diffX;
		this.y = diffY;
		this.z = diffZ;
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
	
	public List<BlockFieldList> getFieldList(){
		return this.fieldList;
	}
	
}
