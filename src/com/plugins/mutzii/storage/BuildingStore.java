package com.plugins.mutzii.storage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;


import com.plugins.mutzii.api.MineralzDatabase;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.editor.Coordinaten;

import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.other.MineralzPosition;

public class BuildingStore implements MineralzDatabase{

	private static BuildingStore instance = null;
	private List<Building> bStore = null;
	
	protected BuildingStore(){}

	public static BuildingStore getInstance(){
		
		if(instance == null){
			instance = new BuildingStore();
			instance.clear();
		}
		
	   return instance;
	}
	
	
	public List<Building> getList(){
		return this.bStore;
	}
	
	
	public Building getGameArea(){
		
		for(Building build : bStore){
			if(build.getType() == BuildType.AREA){
				return build;
			}
		}
		
		return null;
	}
	
	
	public List<Building> getBuildings(Player player){
		
		List<Building> pBuildingList = new ArrayList<Building>();
		
		for(Building build : bStore){
			
			 if(build.getType() != BuildType.AREA){
				 
				 if(build.getPlayer().equals(player)){
					 pBuildingList.add(build);
				 }
				 
			 }
			
		}
		
	  return pBuildingList;
	}
	
	
	public  Building getBuildingBase(Player player) throws NullPointerException{
		

			for(Building build : bStore){
				
				if(build.getPlayer().equals(player)
				     && build.getType() == BuildType.BASE){
					
					return build;
					
				}
				
			}

	  return null;
	}
	
	
	public void register(Building building){
		bStore.add(building);
	}
	
	/**
	 * Performance: 
	 *  -> Diese Methode sollte man am besten in einem Thread laufen lassen.
	 * @param player
	 * @param pos
	 */
	public void unregister(Player player, MineralzPosition pos) throws NullPointerException{
		
		int counter = 0;
		for(Building build : bStore){
	   		
	   		if(build.getPlayer().equals(player)){ //for more Performance
	   			  if(isPositionInField(build,pos)){
	   				  bStore.remove(counter);
	   				  return;
	   			  }
	   		}
	   		
	   	  counter++;
	   	}	
	}
	
	
	public  Building getBuilding(String uuid){
		
		for(Building build : bStore){
			
			if(build.getUUID().toString().equalsIgnoreCase(uuid)){
				return build;
			}
			
		}
		
	  return null;
	}
	
	
	public  Building getBuilding(MineralzPosition pos) throws NullPointerException{
		
	     for(Building build : bStore){
	   		
	    	 if(build.getType() != BuildType.AREA){
	    	 
	   			 if(isPositionInField(build,pos)){
	   				  return build;
	   			  }
	    	 }
	   	 } 
	   	
	   return null;	
	}
	
	
	/**
	 * Performance:
	 * @param player
	 * @param pos
	 * @return
	 */
	public Building getBuilding(Player player, MineralzPosition pos){
	

	   	for(Building build : bStore){
	   		
	   		if(build.getPlayer().equals(player)){ 
	   			
	   			  if(build.getType() != BuildType.AREA){
	   			
	   				  if(isPositionInField(build,pos)){
	   					  return build;
	   				  }
	   			  }
	   		}
	   	}
	   
	 return null;
	}
	
	
	
	private  boolean isPositionInField(Building manager,MineralzPosition pos){
		  
	  List<Coordinaten> coordList = manager.getBlocks();
	
	  for(Coordinaten c : coordList){
		   if(c.getX() == pos.getX()
				   && c.getY() == pos.getY()
				   && c.getZ() == pos.getZ()){
			   return true;
		   } 
	  }
	  
	  
	  return false;
	}

	
	
	
	@Override
	public void clear() {
		bStore = new ArrayList<Building>();
	}
	
	
}
