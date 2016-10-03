package com.plugins.mutzii.threads;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import com.plugins.mutzii.build.MineralzTower;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.monster.MonsterSpawner;
import com.plugins.mutzii.storage.ThreadStore;

public abstract class ThreadManager {

	public final int  TICKS_300 = 300;
	public final int  TICKS_200 = 200;
	public final int  TICKS_100 = 100;
	
	public final int MONSTER_START_LIVE = 3500;
	public final int MONSTER_SPAWN_TICK = 200;
	public final int ADD_MONSTER_DAMAGE = 10;
	public final int MAX_MONSTER  = 10;
	public final int BOSS_MONSTER = 1;
	
	
	public void sleeper(Thread waiter,int duration) throws InterruptedException{
		Thread sleeper = new Thread(new Sleeper(waiter,duration));
		sleeper.start();
	}
	
	public boolean isBuildingDestroyed(Location location){
		
		Block block = location.getBlock();
		if(block.getType() == Material.AIR){
			return true;
		}
		
	  return false;
	}
	
	
	public static void registerTask(String name,Thread thread){
		ThreadStore.getInstance().registerThread(name, thread);
	}
	
	public static void registerTask(String name,BukkitTask task){
		ThreadStore.getInstance().registerBukkitTask(name, task);
	}
		
	//Sync
	public static void runTaskTowerShoot(Plugin plugin,MineralzTower tower, Dispenser dispenser){
		Bukkit.getScheduler().runTaskLater(plugin,new TowerShoot(tower,dispenser), 10L);
	}
	

//	//Sync
//	public static BukkitTask runTaskPower(Plugin plugin,BuildingManager manager){
//		return Bukkit.getScheduler().runTaskLater(plugin,new SendPower(manager),100);
//	}
	
	//Sync
//	public static void runTaskChangePlayerFood(Plugin plugin, Player player){
//		Bukkit.getScheduler().runTaskLater(plugin, new ChangePlayerFood(player), 100);
//	}
	
	//Sync
//	public static void runTaskMonsterSpawn(Plugin plugin,MonsterSpawner spawner, int lastMonsterAmount,int livepoints,Thread th){
//		Bukkit.getScheduler().runTaskLater(plugin,new MonsterSpawnSync(spawner,lastMonsterAmount,livepoints,th),10L);
//	}
	
	//Sync
    public static void runTaskRemoveBuilding(Plugin plugin,Building manager){
    	Bukkit.getScheduler().runTaskLater(plugin,new RemoveBuilding(manager), 10);
    }
       
    //Sync
    public static void runTaskClearTower(Plugin plugin,Building manager){
    	Bukkit.getScheduler().runTaskLater(plugin,new ClearTower(manager),10);
    }
    
}
