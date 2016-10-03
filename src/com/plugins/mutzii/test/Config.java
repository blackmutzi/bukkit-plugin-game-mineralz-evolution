package com.plugins.mutzii.test;

import java.util.HashMap;

public class Config {
	
	
	private HashMap<String,String> map = new HashMap<String,String>();
	private String cat = null;
	
	public Config(){}
	
	public void setContent(String key,String value){
		map.put(key, value);
	}
	
	public String getValue(String key){
		return map.get(key);
	}
	
	public boolean isKey(String key){
		return map.containsKey(key);
	}
	
	
	public void setCategorie(String cat){
		this.cat = cat;
	}
	
	public String getCategorie(){
		return this.cat;
	}

	
	public boolean isNull(Object object){
		  if(object == null){
			   return true;
		  }
	  return false;
	}
	
	
	
	
}
