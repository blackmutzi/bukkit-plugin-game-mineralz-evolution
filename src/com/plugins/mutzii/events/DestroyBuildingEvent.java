package com.plugins.mutzii.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.plugins.mutzii.buildingmanager.Building;
/**
 * This Class is inactive..
 * @author Mutzii
 */
public class DestroyBuildingEvent extends Event implements Cancellable{

	private static final HandlerList handlers = new HandlerList();
	private boolean cancel = false;
	private Building manager;
	
	public DestroyBuildingEvent(Building manager){
		this.manager = manager;
	}
	
	public Building getBuildManager(){
		return this.manager;
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancel;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this.cancel = arg0;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
