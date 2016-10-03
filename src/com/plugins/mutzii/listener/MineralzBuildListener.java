package com.plugins.mutzii.listener;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.events.TowerShootArrowEvent;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.threads.ThreadManager;

public class MineralzBuildListener implements Listener{

	private Plugin plugin;
	
	public MineralzBuildListener(final Plugin plugin){
		this.plugin = plugin;
	}
	
		
	@EventHandler
	public void towerShootArrow(TowerShootArrowEvent event) throws InterruptedException{
		
		List<MineralzPosition> disPositions = event.getBuildManager().getDetectorManager().detectDispenser();
		
		    for(MineralzPosition pos : disPositions){
			
			   Location location = new Location(event.getBuildManager().getPlayer().getWorld(),pos.getX(),pos.getY(),pos.getZ());
			   Block    block    = location.getBlock();
			   
			   if(block.getState() instanceof Dispenser){
				   
				    Dispenser dis = (Dispenser) block.getState();
				   	ThreadManager.runTaskTowerShoot(plugin,event.getTower(),dis);
			   }
			  
			}

	}
	
}
