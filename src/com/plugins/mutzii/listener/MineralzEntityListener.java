package com.plugins.mutzii.listener;

import java.util.ConcurrentModificationException;
import java.util.List;


import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.World;
import org.bukkit.block.Block;


import org.bukkit.entity.Arrow;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.build.MineralzTower;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.editor.Coordinaten;

import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.events.MonsterRemoveBlockEvent;
import com.plugins.mutzii.events.MonsterSpawnEvent;
import com.plugins.mutzii.monster.MonsterManager;
import com.plugins.mutzii.monster.MonsterNavigation;

import com.plugins.mutzii.players.PlayerBehavior;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.storage.MonsterStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class MineralzEntityListener implements Listener
{

	protected final Plugin plugin;
	
	public MineralzEntityListener(final Plugin instance){
		this.plugin = instance;
	}
	
		
	public MonsterNavigation getMonsterNavigation(MonsterManager manager,World world){
		
		MonsterNavigation navigation = new MonsterNavigation(manager,world);
		return navigation;	
	}
	
	
	@EventHandler
	public void ArrowCleaner(ProjectileHitEvent event){
		
		if(event.getEntity() instanceof Arrow){
			 Entity entity = (Entity) event.getEntity();	
			 entity.remove();
		}
	}
	
	
	@EventHandler 
	public void entityDeath(EntityDeathEvent event){
		event.getDrops().clear();
	}
	

	@EventHandler
	public void monsterSpawnEvent(MonsterSpawnEvent event){
								
		MonsterManager manager = MonsterStore.getInstance().getMonsterManager(event.getEntity());
		MonsterNavigation navigation = null;
		boolean monsterIsActiv = false;
	
		if(manager != null){
			navigation = getMonsterNavigation(manager,event.getEntity().getWorld());
			navigation.moveTo();
			monsterIsActiv = true;
		}
	
		if(monsterIsActiv){
			/**
			 * Monster Spawn Settings....
			 */
		}
	}
	
	
	
	@EventHandler
	public void makeBuildDamage(MonsterRemoveBlockEvent event){
			
		try{
		
		Block currentBlock = event.getBlock();
		boolean buildingFound = false;
		
			for(Building building : BuildingStore.getInstance().getList()){
				
				List<Coordinaten> cList   = building.getBlocks();
				
				for(Coordinaten c : cList){
					
					if(c.getX() == currentBlock.getX()
							&& c.getY() == currentBlock.getY()
							&& c.getZ() == currentBlock.getZ()){
						
						//Remove Live
						
						if(building.getLive() < event.getDamage()){
							building.setLive(0);
						}else{
							building.setLive(building.getLive() - (int)event.getDamage());
						}
												
						//Make Sound Damage
						event.getBlock().getWorld().playEffect(event.getBlock().getLocation(),Effect.ZOMBIE_DESTROY_DOOR,1);
						
						//Update Scoreboard
						building.virtual_scoreboard.updateScoreboard();
						
						//Dont destroy the Block.
						event.setCancelled(true);
						buildingFound = true;

						if(building.getLive() <= 0){
							//Destroy Building..
							building.getUpgradeManager().upgrade(Upgrades.DESTROY, building.getPlayer());							
							event.setCancelled(false); //Break the block
						}
					
						break;
					}
					
				}
				
				if(buildingFound){
					break;
				}
			}
			
			
		}catch(ConcurrentModificationException e){}
	}
	
	
	@EventHandler
	public void playerToPlayerDamage(EntityDamageByEntityEvent event){
		
		 if(event.getDamager() instanceof Player){
			 Entity opfer =  event.getEntity();
			 
			 if(opfer instanceof Player){
				 event.setCancelled(true); 
			 }
		 }
	
	}
	
	
	/**
	 * Bukkit Bug: BUKKIT-1038 
	 * arrow.getShooter() is null if dispenser shoot arrows..
	 * @param event
	 */
	@EventHandler
	public void makeMonsterDamage(EntityDamageByEntityEvent event){
		
		//Silverfish
		if(event.getDamager() instanceof Silverfish){
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
				event.getDamager().remove();
			}
		}
		
		//Giant
		if(event.getDamager() instanceof Giant){
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
				event.getDamager().remove();
			}
		}
		
		
		//Wolf
		if(event.getDamager() instanceof Wolf){
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
				event.getDamager().remove();
			}
		}
		
		//Enderman
		if(event.getDamager() instanceof Enderman){
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
				event.getDamager().remove();
			}
		}
		
		
		//Blaze
		if(event.getDamager() instanceof Blaze){
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
				event.getDamager().remove();
			}
		}
		
		//Ghast
		if(event.getDamager() instanceof Ghast){
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
				event.getDamager().remove();
			}
		}
		
		//Spider
		if(event.getDamager() instanceof Spider){
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
				event.getDamager().remove();
			}
		}
		
		
		//Slime
		if(event.getDamager() instanceof Slime){
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
				event.getDamager().remove();
			}
		}
		
		
		//Creeper Damage to Player
		if(event.getDamager() instanceof Creeper){
			if(event.getEntity() instanceof Player){
				event.setCancelled(true);
				event.getDamager().remove();
			}
		}
		
		//Skeleton Damage to Player
		if(event.getDamager() instanceof Arrow){
				Arrow arrow = (Arrow)event.getDamager();
				if(arrow.getShooter() instanceof Skeleton){
					if(event.getEntity() instanceof Player){
						event.setCancelled(true);
						
						//arrow.getShooter().remove();
					}
				}
		}
		
		
		//Monster make Damage to Player (Onehit kill)
		if(event.getDamager() instanceof Zombie){
			if(event.getEntity() instanceof Player){
			
					Player player = (Player) event.getEntity();
					player.setHealth(0);
			}
		}
		
		//Arrow make Damage to Entity(Zombie)
		if(event.getDamager() instanceof Arrow ){
			if(event.getEntity() instanceof LivingEntity){
						
					Arrow arrow = (Arrow) event.getDamager();
					
					try{
						
						if(arrow.getShooter() == null){ //<< BUKKIT BUG
							    //Bukkit.getServer().getLogger().log(Level.INFO, "get Shooter is null");
							/**
							 * Tower identification failed if getShooter is null
							 */ //Dispenser dis         = (Dispenser) arrow.getShooter();
								//Location loc          = dis.getLocation();
								//MineralzPosition pos  = new MineralzPosition(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
								//BuildManager building = BuildingStore.getInstance().getBuilding(pos);
						}
						
						//Tower Make Damage...
						  if(arrow.getShooter() == null){
						
							Building building = null;
							
							//Alternative wegen dem Bukkit Bug.
							//Es sollte eh nur ein Tower existieren in Game.
							for(Building m : BuildingStore.getInstance().getList()){
								if(m.getType() == BuildType.TOWER){
									building = m;
									break;
								}
							}
							
							
							if(building != null && event.getEntity() instanceof Zombie){
							
							    	event.setCancelled(true);
									if(building.getType() == BuildType.TOWER){
								
										MineralzTower tower = (MineralzTower) building;
										if(MonsterStore.getInstance().isRegister((LivingEntity) event.getEntity())){
											
												MonsterManager manager = MonsterStore.getInstance().getMonsterManager((LivingEntity) event.getEntity());
												
												if(manager.getLive() < tower.getTowerDamage()){
													
													manager.setLive(0);
													event.getEntity().playEffect(EntityEffect.HURT);
													
												}else{
													manager.setLive(manager.getLive() - (int) tower.getTowerDamage());												
													event.getEntity().playEffect(EntityEffect.HURT);
												}
												
													
										}
									
									}
							}						
						}
					
					
						if(arrow.getShooter() instanceof Player)
						{
						
							if( PlayerMineralzStore.getInstance().isPlayerAlready( (Player) arrow.getShooter() ) )
							{
								PlayerBehavior m_player = (PlayerBehavior) PlayerMineralzStore.getInstance().getPlayerMineralzz( (Player) arrow.getShooter() );
								
								
								if(MonsterStore.getInstance().isRegister((LivingEntity) event.getEntity()))
								{
									
									MonsterManager manager = MonsterStore.getInstance().getMonsterManager((LivingEntity) event.getEntity());
									
									if(manager.getLive() < m_player.getDamage() ){
										
										manager.setLive(0);
										event.getEntity().playEffect(EntityEffect.HURT);
										
									}else{
										
										manager.setLive(manager.getLive() - (int) m_player.getDamage() );
										event.getEntity().playEffect(EntityEffect.HURT);
										
									}
									
							   }
								
							}
						
						}	
					
					
				  }catch(NullPointerException e){}
			}
		}
		
	}
	
}
