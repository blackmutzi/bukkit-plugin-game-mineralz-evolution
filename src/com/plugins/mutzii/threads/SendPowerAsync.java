package com.plugins.mutzii.threads;

import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import com.plugins.mutzii.build.MineralzGenerator;
import com.plugins.mutzii.build.MineralzHealer;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.storage.BuildingStore;

public class SendPowerAsync extends ThreadManager implements Runnable{
	
	private Building building;
	private Plugin plugin;
	private BukkitTask task;
	
	public SendPowerAsync(Plugin plugin, Building building )
	{
		this.building = building;
		this.plugin   = plugin;	
	}
	
	@Override
	public void run() {
		
		while(true)
		{
			try
			{
				/* send power*/
				if( building.getType() == BuildType.GENERATOR )
				{
					MineralzGenerator generator = (MineralzGenerator) building;
					generator.sendPower();
					
					/* wait interval  */
					Thread.sleep( building.getPowerInterval() );
					continue;
				}
				
				if( building.getType() == BuildType.HEALER )
				{
					MineralzHealer  healer = (MineralzHealer) building;
					healer.startHealProcess();
					
					/* wait interval - if last healing was true */
					if (healer.last_healing)
					{
						Thread.sleep( building.getPowerInterval() );
						healer.last_healing = false;
					}
		
					continue;
				}
			
				
			}catch(IllegalPluginAccessException e){
			}catch(InterruptedException e){				
			}catch(NullPointerException e){
				break;
			}
		}
	}
	
	
	

}
