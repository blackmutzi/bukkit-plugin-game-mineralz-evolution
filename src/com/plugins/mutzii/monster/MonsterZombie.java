package com.plugins.mutzii.monster;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class MonsterZombie extends MonsterManager{


	public MonsterZombie(Location location,int livepoints) {
		super(EntityType.ZOMBIE,location,livepoints);
	}
	
}
