package com.plugins.mutzii.threads;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;
import com.plugins.mutzii.build.MineralzTower;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.events.TowerShootArrowEvent;


/**
 * TowerShootArrowsThread create a Event
 * 
 * CallEvent: TowerShootArrowEvent
 * Sync: ASync 
 * @author Mutzii
 *
 */
public class TowerShootAsync extends ThreadManager implements Runnable{

	private Building manager;
	private World world;
	
	public TowerShootAsync(Building manager,World world){
		this.manager = manager;	
		this.world   = world;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public Building getManager(){
		return this.manager;
	}
	
	public void setManager(Building manager){
		this.manager = manager;
	}
	
	@Override
	public void run() {
			
		Bukkit.getServer().getLogger().log(Level.INFO, "Starte TowerShootAsync Thread.");
		
		while(true){
			
		   try{	
			 
			   synchronized(Thread.currentThread()){
		    		 sleeper(Thread.currentThread(),200);
		    		 Thread.currentThread().wait();
		    	 }
			   
		     MineralzTower tower = (MineralzTower) getManager();
			 
		     if(tower.isDispenserEnabled()){
		    	 
		    	 TowerShootArrowEvent event  = new TowerShootArrowEvent(getManager(),getWorld());
		    	 event.setTicks(1000);
		    	 Bukkit.getPluginManager().callEvent(event);
		    	 
		    	 synchronized(Thread.currentThread()){
		    		 sleeper(Thread.currentThread(),event.getTicks());
		    		 Thread.currentThread().wait();
		    	 }
		     }
		     
		     if(tower.isRemoved()){
		    	 break;
		     }
				
		     }catch(NullPointerException e){
		    	 Bukkit.getServer().getLogger().log(Level.INFO, "NullPointerException beim Tower Thread");
		    	 e.printStackTrace();
		     } catch (InterruptedException e) {}
		}	
	}

}
