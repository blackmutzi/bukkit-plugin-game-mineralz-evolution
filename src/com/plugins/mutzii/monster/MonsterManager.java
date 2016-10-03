package com.plugins.mutzii.monster;


import java.util.ConcurrentModificationException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import org.bukkit.World;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import com.plugins.mutzii.api.Monster;
import com.plugins.mutzii.api.WorldInterface;
import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.storage.MonsterStore;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMob;
import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobs;

/**
 * Monster Manager (spawn,kill..etc)
 * @author Mutzii
 *
 */
public abstract class MonsterManager implements Monster,WorldInterface{

	protected World world;
	protected Location location;
	protected EntityType type;
	protected Entity entity;
	protected int level;
	protected double damage;
	
	private int live;
	private int maxlive;
	
	
	private ControllableMob<?> controller = null;	
	private Location movelocation;
		
	public final int    DEFAULT_MONSTER_LEVEL   = 1;
	public final float  DEFAULT_MONSTER_DAMAGE  = 10;
	
	private boolean ispawned = false;

	
	public MonsterManager(EntityType type,Location location,int livepoints){
			
		setType(type);
		setLocation(location);
		setWorld(location.getWorld());
		setLive(livepoints);
	}
	
	
	public MonsterEquipmentManager getEquipmentManager(){
		if(getLivingEntity() != null){
			 return new MonsterEquipmentManager(getLivingEntity());
		}
		
	  return null;
	}
	
	
	public MonsterPotionManager getPotionManager(){
		
		if(getLivingEntity() != null){
			return new MonsterPotionManager(getLivingEntity());
		}
	
	  return null;
	}
		
	@Override
	public void free(){
		ControllableMobs.unassign(getMonsterController());
	}
	
	@Override
	public void unassign() throws ConcurrentModificationException{
		die();
		free();
	}	
	
	@Override
	public void moveTo(Location location){
		getMonsterController().getActions().moveTo(location);
	}
		
	@Override
	public void move(Direction direction,int steps) throws NullPointerException{
		
		if(getEntity() != null){
													
			if(direction == Direction.NORTH){
				
			  getMoveLocation().setZ(getMoveLocation().getZ() - steps);
			  moveTo(getMoveLocation());
				
			}
			
			if(direction == Direction.SOUTH){
				
				getMoveLocation().setZ(getMoveLocation().getZ() + steps);
				moveTo(getMoveLocation());
			}
			
			if(direction == Direction.EAST){

				getMoveLocation().setX(getMoveLocation().getX() + steps);
				moveTo(getMoveLocation());
			}
			
			if(direction == Direction.WEST){
				
				getMoveLocation().setX(getMoveLocation().getX() - steps);
				moveTo(getMoveLocation());	
			}
		}	
	}
	
	@Override
	public void kill()
	{
		
		getMonsterController().getActions().clearActions();
		getMonsterController().getActions().clearActionQueue();
		getMonsterController().getActions().die();
		die();
		free();
		
		Bukkit.getServer().getLogger().log(Level.INFO, "Monster Dead");
	}
	
	
	@Override
	public void die() throws ConcurrentModificationException {
		
		 MonsterStore.getInstance().unregister(getLivingEntity());	
	}
	
	
	@Override
	public boolean spawn() throws ConcurrentModificationException{
					
			//Entity entity   = spawnNewEntity(getLocation());
			Entity entity = (Entity)location.getWorld().spawnEntity(getLocation(),getType());

			if(entity != null){
				
				Location location = new Location(getWorld(),getLocation().getX(),getLocation().getY(),getLocation().getZ());
				setMoveLocation(location);
				setEntity(entity);				
				setController();
				
				this.ispawned = true;
				return true;	
		   }
		

	  return false;
	}
	
	@Override
	public boolean spawn(int level,double damage)throws ConcurrentModificationException{
		
		 setLevel(level);
		 setDamage(damage);	
		 
		 return spawn();
	}
	
		
	public void setController(){
		
		 if(getEntity().getType() == getType()){
			  ControllableMob<Zombie> monster = ControllableMobs.assign((Zombie)getEntity(),false);
			  this.controller = monster;
			  
		 }
	}
	
	@Override
	public int getMaxLive(){
		return this.maxlive;
	}
	
	@Override
	public void setMaxLive(int livepoints){
		this.maxlive = livepoints;
	}
	
	
	@Override 
	public int getLive(){
		return this.live;
	}
	
	@Override 
	public void setLive(int livepoints){
		this.live = livepoints;
	}
	
	@Override
	public boolean isMonsterSpawned(){
		return this.ispawned;
	}
	

	@Override
	public ControllableMob<?> getMonsterController(){
		return this.controller;
	}
	
	@Override
	public Location getMoveLocation(){
		return this.movelocation;
	}
	
	@Override
	public double getDamage() {
		return damage;
	}

	
	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public Location getLocation() {
		return location;
	}


	@Override
	public World getWorld() {
		return world;
	}
	
	
	@Override
	public LivingEntity getLivingEntity(){
		return (LivingEntity) this.entity;
	}
	
	@Override
	public Entity getEntity() {
		return this.entity;
	}
	
	@Override
	public EntityType getType(){
		return this.type;
	}
	
	
	@Override 
	public void setDamage(double damage){
		this.damage = damage;
	}
	
	@Override
	public void setLevel(int level){
		this.level = level;
	}
	
	@Override 
	public void setEntity(Entity entity){
		this.entity = entity;
	}
	
	@Override 
	public void setType(EntityType type){
		this.type = type;
	}
	
	@Override
	public void setWorld(World world){
		this.world = world;
	}
	
	@Override
	public void setLocation(Location location){
		this.location = location;
	}
	
	@Override
	public void setMoveLocation(Location location){
		this.movelocation = location;
	}
	
}
