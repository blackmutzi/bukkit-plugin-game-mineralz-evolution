package com.plugins.mutzii.api;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.monster.MonsterPotionManager;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMob;

public interface Monster {
	
	public void moveTo(Location location);
	public void move(Direction direction,int ticks);
	
	public boolean spawn();
	public boolean spawn(int level,double damage);
	
	public void  setDamage(double damage);
	public double getDamage();
	
	public void setLevel(int level);
	public int  getLevel();
	
	public void setLive(int livepoints);
	public int  getLive();
	
	public void setMaxLive(int livepoints);
	public int getMaxLive();
	
	public void kill();
	public void die();
	
	public LivingEntity getLivingEntity();
	public Entity getEntity();
	public void setEntity(Entity monster);
	
	public void setType(EntityType type);
	public EntityType getType();
	
	public void setLocation(Location location);
	public Location getLocation();
	
	public ControllableMob<?> getMonsterController();
	
	public boolean isMonsterSpawned();
	
	public void free();
	public void unassign();
	
	public void setMoveLocation(Location location);
	public Location getMoveLocation();
	
	public MonsterPotionManager getPotionManager();
	
}
