package com.plugins.mutzii.config;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.storage.BuildingStore;

public class Identification {
	
	private HashMap< Material  , BuildType > assign_material_type = new HashMap< Material  , BuildType >();
	private HashMap< BuildType , Material  > assign_type_material = new HashMap< BuildType , Material  >();
	private List< MineralzType > list_of_mineralz_type;
	
	public Identification()
	{
		assign_material_type.put( Material.COBBLESTONE       , BuildType.BASE      );
		assign_material_type.put( Material.BEDROCK           , BuildType.AREA      );
		assign_material_type.put( Material.SANDSTONE         , BuildType.TOWER     );
		assign_material_type.put( Material.MOSSY_COBBLESTONE , BuildType.HEALER    );
		assign_material_type.put( Material.BRICK             , BuildType.GENERATOR );	
		
		assign_type_material.put( BuildType.BASE      , Material.COBBLESTONE       );
		assign_type_material.put( BuildType.AREA      , Material.BEDROCK           );
		assign_type_material.put( BuildType.TOWER     , Material.SANDSTONE         );
		assign_type_material.put( BuildType.HEALER    , Material.MOSSY_COBBLESTONE );
		assign_type_material.put( BuildType.GENERATOR , Material.BRICK             );
		
		list_of_mineralz_type.add( MineralzType.MINERAL_BLUE  );
		list_of_mineralz_type.add( MineralzType.MINERAL_GREEN );
		list_of_mineralz_type.add( MineralzType.MINERAL_LILA  );
		list_of_mineralz_type.add( MineralzType.MINERAL_RED   );
		
	}
	
	public Building inventoryIdentification(Player player,Inventory inventory,MineralzPosition position) throws NullPointerException {
		
		 String inventoryTitle = inventory.getTitle();
		 
		 	if(inventoryTitle.equalsIgnoreCase(BuildType.BASE.toString()))
		 	{
		 		Bukkit.getLogger().log(Level.INFO, "Identification::Return Base ");
		 		return BuildingStore.getInstance().getBuilding(player, position);
		 	}
		 	
		 	if(inventoryTitle.equalsIgnoreCase(BuildType.GENERATOR.toString()))
		 	{
		 		Bukkit.getLogger().log(Level.INFO, "Identification::Return Generator ");
		 		return BuildingStore.getInstance().getBuilding(player, position);
		 	}
		 	
		 	if(inventoryTitle.equalsIgnoreCase(BuildType.HEALER.toString()))
		 	{
		 		Bukkit.getLogger().log(Level.INFO, "Identification::Return Healer ");
		 		return BuildingStore.getInstance().getBuilding(player, position);
		 	}
		 	
		 	if(inventoryTitle.equalsIgnoreCase(BuildType.TOWER.toString()))
		 	{
		 		Bukkit.getLogger().log(Level.INFO, "Identification::Return Tower ");
		 		return BuildingStore.getInstance().getBuilding(player, position);
		 	}
		 	
		 	if(inventoryTitle.equalsIgnoreCase(BuildType.AREA.toString()))
		 	{
		 		Bukkit.getLogger().log(Level.INFO, "Identification::Return Area ");
		 		return BuildingStore.getInstance().getGameArea();
		 	}
		 	
		Bukkit.getLogger().log(Level.INFO, "Identification failed!");
		return null;
	}
	
	
	public List<MineralzType> getMineralzTypeList()
	{
		return list_of_mineralz_type;
	}
	
	public BuildType getBuildTypeByBlock( Block block )
	{
		return getBuildTypeByMaterial( block.getType() );
	}
	
	public Material  getMaterialByBuildType( BuildType type )
	{
		return (Material) assign_type_material.get( type );
	}
	
	public BuildType getBuildTypeByMaterial( Material material )
	{
		return (BuildType) assign_material_type.get( material );
	}
}
