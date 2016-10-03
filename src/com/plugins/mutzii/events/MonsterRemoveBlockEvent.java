package com.plugins.mutzii.events;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MonsterRemoveBlockEvent extends Event implements Cancellable{

	private static final HandlerList handlers = new HandlerList();
	
	protected LivingEntity entity;
	protected Block        block;
	private   boolean      cancel;
	protected double        monsterdamage;
	
	
	public MonsterRemoveBlockEvent(LivingEntity entity, Block block,double damage){
		this.entity = entity;
		this.block  = block;
		this.monsterdamage = damage;
	}
	
	public Block getBlock(){
		return block;
	}
	
	public LivingEntity getEntity(){
		return entity;
	}
	public double getDamage(){
		return this.monsterdamage;
	}


	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return this.cancel;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this.cancel = arg0;
	}
	
}
