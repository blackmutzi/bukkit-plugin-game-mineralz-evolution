package com.plugins.mutzii.storage;

import java.util.ArrayList;
import java.util.List;


import com.plugins.mutzii.api.MineralzInterface;
import com.plugins.mutzii.api.MineralzDatabase;
import com.plugins.mutzii.other.MineralzPosition;

public class MineralzStore implements MineralzDatabase{

	private static MineralzStore instance = null;
	private List<MineralzInterface> mStore  = null;
	
	protected MineralzStore(){}
	
	public static MineralzStore getInstance(){
		
		if(instance == null){
			  instance = new MineralzStore();
			  instance.clear();
		}
		
	  return instance;
	}
	
	
	public List<MineralzInterface> getList(){
		return this.mStore;
	}
	
	
	
	public  boolean isMineralBlockRecorded(MineralzPosition minepos) throws NullPointerException{
		
		for(MineralzInterface m : mStore){
			
			if(equals(m,minepos)){
				return true;
			}
		}
		
	  return false;
	}
	
	
	public  MineralzInterface getMineralz(MineralzPosition minepos) throws NullPointerException{
		
		for(MineralzInterface m : mStore){
			
			if(equals(m,minepos)){
				return m;
			}
			
		}

	  return null;
	}
	
	
	public void register(MineralzInterface mine){
		mStore.add(mine);
	}
	
	public void unregister(MineralzPosition minepos) throws NullPointerException{
		
		int counter = 0;
		for(MineralzInterface m : mStore){
			
		    if(equals(m,minepos)){
		    	mStore.remove(counter);
		    	return;
		    }
			
			counter++;
		}
		
	}
	
		
	private  boolean equals(MineralzInterface m , MineralzPosition pos){
		
		
		MineralzPosition mPos = m.getMineralPosition();
		
		if(mPos.getX() == pos.getX()
				&& mPos.getY() == pos.getY()
		        && mPos.getZ() == pos.getZ()){
			return true;
		}
		
	  return false;
	}

	@Override
	public  void clear() {
		mStore   = new ArrayList<MineralzInterface>();
	}
	
	
	
}
