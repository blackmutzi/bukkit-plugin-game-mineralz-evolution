package com.plugins.mutzii.editor;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class MyBlock implements ConfigurationSerializable{

	private int itemId;
	private byte data;
		
	public MyBlock(int itemid,int data){
		this.itemId = itemid;
		this.data   = (byte)data;
	
	}
	
	public MyBlock(int itemid, String data){
		this.itemId = itemid;
		this.data   = Byte.valueOf(data);
	}
	
	public MyBlock(int itemId,byte data){
		this.itemId = itemId;
		this.data   = data;
	}
	
	public int getType(){
		return this.itemId;
	}
	
	public byte getData(){
		return this.data;
	}

	@Override
	public Map<String, Object> serialize() {
		
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		 result.put("type", getType());
		 result.put("data", getData());
	 
		 return result;
		
	}
	
	public static MyBlock deserialize(Map<String, Object> args) {
		
		int itemid  = (Integer)  args.get("type");
		int data    = (Integer)  args.get("data");

		return new MyBlock(itemid,data);
	}
}
