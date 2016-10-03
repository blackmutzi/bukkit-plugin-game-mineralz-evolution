package com.plugins.mutzii.other;

import org.bukkit.Location;
import org.bukkit.World;

public abstract class Converter extends FieldExperiment{
	

	public Converter(World world) {
		super(world);
	}


	public static MineralzPosition getMineralzPosition(Location location){
		return new MineralzPosition(location.getBlockX(),location.getBlockY(),location.getBlockZ());
	}
	
	
	public static Location getLocation(World world, int x, int y, int z){
		return new Location(world,x,y,z);		
	}
	
	public static Location getLocation(World world,MineralzPosition position){
		
		Location location = new Location(world,position.getX(),position.getY(),position.getZ());
		return location;
		
	}
	
	
}
