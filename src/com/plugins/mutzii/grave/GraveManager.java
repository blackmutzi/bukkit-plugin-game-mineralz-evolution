package com.plugins.mutzii.grave;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.plugins.mutzii.api.MaterialValues;
import com.plugins.mutzii.api.UUIDInterface;
import com.plugins.mutzii.api.WorldInterface;
import com.plugins.mutzii.enums.MineralzType;

public abstract class GraveManager extends GraveFieldManager implements WorldInterface,UUIDInterface,MaterialValues{

	private UUID uuid;
	
	private HashMap<MineralzType,Material> fieldItems = new HashMap<MineralzType,Material>();
	
	public GraveManager(Location start, Location end, World world) {
		super(start, end, world);
		
		setFieldItems(MineralzType.MINERAL_CUSTOM,Material.COBBLESTONE);
		
		setFieldItems(MineralzType.MINERAL_BLUE,Material.WOOL);
		setFieldItems(MineralzType.MINERAL_RED,Material.WOOL);
		setFieldItems(MineralzType.MINERAL_GREEN,Material.WOOL);
		setFieldItems(MineralzType.MINERAL_LILA,Material.WOOL);
		
	}
	
	
	public void spawnRandomBlocks(){
	
			Random random    = new Random(this.fieldItems.size());
			int randomNumber = random.nextInt();
			/**
			 * TODO:
			 * ....
			 */
	}
		
	@Override 
	public World getWorld(){
		return super.getWorld();
	}
	
	@Override
	public UUID getUUID(){
		return this.uuid;
	}
	
	@Override
	public void setUUID(UUID id){
		this.uuid = id;
	}
	
	@Override
	public void setWorld(World world){
		super.setWorld(world);
	}
	
	public void setFieldItems(MineralzType type ,Material material){
		this.fieldItems.put(type, material);
	}
	
}
