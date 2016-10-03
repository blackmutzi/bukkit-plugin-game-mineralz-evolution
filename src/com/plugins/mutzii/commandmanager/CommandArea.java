package com.plugins.mutzii.commandmanager;

import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.build.MineralzArea;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.exceptions.InvalidCommandException;
import com.plugins.mutzii.storage.BuildingStore;

public class CommandArea extends CommandBehavior {

	public CommandArea(Plugin plugin) 
	{
		super(plugin);
	}

	@Override
	public void run() throws InvalidCommandException {
		
		if(getPlayer() != null){
			if(hasPermission(getPlayer(), this.permission )){
				
				try{
				 //Open Area Inventory
				 Building manager = BuildingStore.getInstance().getGameArea();
				 
				 if(manager != null){
					 MineralzArea area = (MineralzArea) manager;
					 area.virtual_inventory.openVirtualInventory( getPlayer() );
				 }
				 
				}catch(NullPointerException e){
					throw new InvalidCommandException("Area Inventory settings comming soon...",getPlayer());
				}
				
			}
		}
		
	}

	@Override
	public void showDescription() 
	{
		 if(hasPermission( getPlayer() , this.permission ))
		  {
			  getPlayer().sendMessage(" Description: Open area inventory ");
 	    	  getPlayer().sendMessage("/game area");
	      }
		
	}

}
