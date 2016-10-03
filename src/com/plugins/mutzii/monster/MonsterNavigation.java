package com.plugins.mutzii.monster;

import org.bukkit.World;

import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.storage.GameStore;

public class MonsterNavigation extends MonsterHitBox{

	protected MonsterManager manager;
	
	private final int MONSTER_STEPS = 2;
	
	public MonsterNavigation(MonsterManager manager,World world){
		super(world,manager.getDamage());
		this.manager = manager;
	}
	
	/**
	 * Monster in eine richtung Bewegen. 
	 * 
	 * Die Richtung wird vom Amdin festgelegt und kommt 
	 * vom StageStore.
	 * @param direction
	 */
	public void moveTo(){
		Direction monsterDirection = GameStore.getInstance().getMonsterDirectionPath();
		manager.move(monsterDirection,MONSTER_STEPS);
	}
	
	public MonsterManager getManager(){
		return this.manager;
	}

	
}
