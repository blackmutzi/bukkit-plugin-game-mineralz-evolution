package com.plugins.mutzii.threads;
import org.bukkit.Location;
import org.bukkit.block.Dispenser;

import com.plugins.mutzii.build.MineralzTower;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.other.Converter;
import com.plugins.mutzii.other.MineralzPosition;

public class ClearTower extends ThreadManager implements Runnable{

	private Building manager;
	
	public ClearTower(Building manager){
		this.manager      = manager;
	}
	
	public Building getBuildManager(){
		return this.manager;
	}
	
	public boolean isTower(){
		
		if(getBuildManager().getType() == BuildType.TOWER)
				return true;
		
	  return false;
	}
	
	public MineralzTower getTower(){
		return (MineralzTower) getBuildManager();
	}
	
	
	@Override
	public void run() {
	
		if(isTower()){
			
			for(MineralzPosition position : getTower().detectDispenser()){
				
				Location loc = Converter.getLocation(getBuildManager().getPlayer().getWorld(), position);
				
				if(loc.getBlock().getState() instanceof Dispenser){
					
					    Dispenser dispenser = (Dispenser) loc.getBlock().getState();
						getTower().clearDispenser(dispenser);
				}
			}	
		}
	}

	
	
}
