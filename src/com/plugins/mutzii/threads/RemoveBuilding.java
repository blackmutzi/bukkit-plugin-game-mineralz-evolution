package com.plugins.mutzii.threads;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.material.Ladder;



import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.editor.Coordinaten;
import com.plugins.mutzii.editor.Editor;
import com.plugins.mutzii.other.Converter;
import com.plugins.mutzii.storage.BuildingStore;

/**
 * Sync Thread
 * @author Mutzii
 *
 */
public class RemoveBuilding extends ThreadManager implements Runnable{

	private Building manager;
	
	public RemoveBuilding(Building manager){
		this.manager = manager;
	}
	
	public Building getBuildManager(){
		return this.manager;
	}
	
	public List<Coordinaten> removeFirstList(List<Coordinaten> coord){
		
		for(Coordinaten c : coord){
			
			isSpezialBlock(c);

		}
		
	   return coord;
	}
	
	
	public boolean isSpezialBlock(Coordinaten c){
		
		Location location = Converter.getLocation(getBuildManager().getWorld(),c.getX(),c.getY(),c.getZ());
		Editor   editor   = new Editor(getBuildManager().getWorld());
		
		
		if(location.getBlock().getState().getData() instanceof Ladder){
			 editor.createBlock(getBuildManager().getPlayer(),c.getX(),c.getY(),c.getZ(),Material.AIR.getId(),(byte)0x0,null);
			 return true;
		}
		
		if(location.getBlock().getState().getData() instanceof Sign){
			 editor.createBlock(getBuildManager().getPlayer(),c.getX(),c.getY(),c.getZ(),Material.AIR.getId(),(byte)0x0,null);
			 return true;
		}
		
		
		
	  return false;
	}
	
	
	@Override
	public void run() {
		
	    Editor editor = new Editor(getBuildManager().getWorld());
		List<Coordinaten> coord = removeFirstList(getBuildManager().getBlocks());
		for(Coordinaten cList : coord){
			editor.createBlock(getBuildManager().getPlayer(),cList.getX(),cList.getY(),cList.getZ(),Material.AIR.getId(),(byte)0x0,null);
		}
		
		BuildingStore.getInstance().unregister(getBuildManager().getPlayer(), getBuildManager().getPosition());
	
	}

}
