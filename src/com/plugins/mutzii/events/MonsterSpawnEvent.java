package com.plugins.mutzii.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MonsterSpawnEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	private LivingEntity entity;
	
	
	public MonsterSpawnEvent(LivingEntity entity){
		this.entity = entity;
	}
	

	public LivingEntity getEntity(){
		return this.entity;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
