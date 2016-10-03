package com.plugins.mutzii.monster;


import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.editor.Coordinaten;
import com.plugins.mutzii.events.MonsterRemoveBlockEvent;
import com.plugins.mutzii.storage.BuildingStore;

/**
 * Monster Attacks
 * 
 * @author Mutzii
 */
public class MonsterHitBox {

	protected World world;
	protected double monsterdamage;
	
	public MonsterHitBox(World world,double damage){
	   this.world = world;
	   this.monsterdamage = damage;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	
	/**
	 * make Zombie Destroy Door Sound :D
	 * @param location
	 */
	public void makeAttackSound(Location location){
		getWorld().playEffect(location,Effect.ZOMBIE_DESTROY_DOOR,1);
	}
	
	
	/**
	 * get complete Building Instance from Entity if selected any building Block
	 * @param entity
	 * @return Building (Tower,Base,Healer,Generator)
	 */
	public Building buildDetector(LivingEntity entity){
		
		BuildingStore store   = BuildingStore.getInstance();
		Set<Material> transparent = null;
		List<Block> blockList = entity.getLineOfSight( transparent , 4);
		
		for(Block currentBlock : blockList){
			
			for(Building building : store.getList()){
				
				List<Coordinaten> cList   = building.getBlocks();
				
				for(Coordinaten c : cList){
					
					if(c.getX() == currentBlock.getX()
							&& c.getY() == currentBlock.getY()
							&& c.getZ() == currentBlock.getZ()){
						
						return building;
					}
					
				}
				
			}
		}
		
		
	  return null;
	}
	
	
	/**
	 * Monster has selected any Block then get Building Location
	 * @param entity
	 * @return Building Location
	 * @throws NullPointerException
	 */
	public Location buildLocation(LivingEntity entity)throws NullPointerException{
		
	 try{
		 

		BuildingStore store       = BuildingStore.getInstance();
		Set<Material> transparent = null;
		List<Block> blockList = entity.getLineOfSight(transparent, 4);

		
		for(Block currentBlock : blockList){
			
			for(Building building : store.getList()){
				
				List<Coordinaten> cList   = building.getBlocks();
				
				for(Coordinaten c : cList){
					
					if(c.getX() == currentBlock.getX()
							&& c.getY() == currentBlock.getY()
							&& c.getZ() == currentBlock.getZ()){
						
						return currentBlock.getLocation();
					}
					
				}
				
			}
		}
		
	  }catch(NullPointerException e){
		  throw new NullPointerException();
	  }
		
	  return null;
	}
	
	
	/**
	 * Zoombie (Entity) try removed any Block
	 * @param entity
	 * @param location
	 * @return
	 */
	public boolean tryRemoveBlock(LivingEntity entity, Location location){
		
		Set<Material> transparent = null;
		List<Block> blockList = entity.getLineOfSight( transparent , 2);
		
		for(Block currentBlock : blockList){
			
			if(currentBlock.getX() == location.getBlockX()
					&& currentBlock.getY() == location.getBlockY()
					&& currentBlock.getZ() == location.getBlockZ()){
				
				MonsterRemoveBlockEvent event = new MonsterRemoveBlockEvent(entity,currentBlock,this.monsterdamage);
				Bukkit.getServer().getPluginManager().callEvent(event);
								
				return true;
			}
		}
		
		return false;
	}
	
	
//	/**
//	 * is Any Block Removed?
//	 * @param blockList
//	 * @return
//	 */
//	public boolean isAnyBlockRemoved(List<Coordinaten> blockList){
//		
//		for(Coordinaten c : blockList){
//			
//			 Location location = new Location(getWorld(),c.getX(),c.getY(),c.getZ());
//			 Block      bState = location.getBlock();
//			 
//			 if( bState.getType() != c.getBlock().getType()){
//				 return true;
//			 }
//		}
//		
//	  return false;
//	}
//	
	

}
