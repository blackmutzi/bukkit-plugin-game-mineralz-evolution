package com.plugins.mutzii.events;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.plugins.mutzii.build.MineralzTower;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.enums.BuildType;

public class TowerShootArrowEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	private Building manager;
	private World world;
	private int ticks;
	
	
	public TowerShootArrowEvent(Building manager, World world){
		this.manager = manager;
		this.world   = world;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public Building getBuildManager(){
		return this.manager;
	}
	
	public boolean isTower(){
		if(getBuildManager().getType() == BuildType.TOWER){
			return true;
		}
		
	  return false;
	}
	
	public MineralzTower getTower(){
		
		if(isTower()){
			return (MineralzTower) getBuildManager();
		}
	
	  return null;
	}
	
	public void setTicks(int ticks){
		this.ticks = ticks;
	}
	
	public int getTicks(){
		return this.ticks;
	}
	
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}


}
