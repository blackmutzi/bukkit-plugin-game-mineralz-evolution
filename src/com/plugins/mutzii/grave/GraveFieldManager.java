package com.plugins.mutzii.grave;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.plugins.mutzii.other.Converter;
import com.plugins.mutzii.other.MineralzField;

/**
 * GraveField Manager:
 * 
 * @author Mutzii
 *
 */
public abstract class GraveFieldManager {

	private MineralzField field;
	private World world;
	
	public GraveFieldManager(MineralzField field,World world){
		setField(field);
		setWorld(world);
		
	}
	
	public GraveFieldManager(Location start, Location end,World world){
		setField(Converter.getMineralzField(start, end));
		setWorld(world);
	}
	
	
	public void EditBlock(Material type,Location location){
		Block block = getWorld().getBlockAt(location);
		block.setType(type);
	}
	
	
	
	public Location getLocation(int x,int y,int z){
		return Converter.getLocation(getWorld(), x, y, z);
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public MineralzField getField(){
		return this.field;
	}
	
	public void setField(MineralzField field){
		this.field = field;
	}
	
	public void setWorld(World world){
		this.world = world;
	}
}
