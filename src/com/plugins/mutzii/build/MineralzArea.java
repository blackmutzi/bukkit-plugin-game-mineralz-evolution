package com.plugins.mutzii.build;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.plugins.mutzii.api.MaterialValues;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.buildingmanager.InventorySlotManager;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.InventoryType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.upgrades.UpgradeBuildArea;

public class MineralzArea extends Building implements MaterialValues 
{

	public MineralzArea(Player player, MineralzPosition start, MineralzPosition ende)
	{
		 super(player, BuildType.AREA , start, ende);

		 virtual_scoreboard.clearScoreBoard();
		 		
		 this.SetupInventory();
		
		 super.setUpgradeManager( new UpgradeBuildArea(this) );
		
		 BuildingStore.getInstance().register(this);	
	}
	
	
	private void SetupInventory()
	{
		//Inventory Name: MineralzArea 
	    //Inventory Size: InventoryType.MIDDLE
		virtual_inventory.createVirtualInventory( InventoryType.MIDDLE.getNumber() ,BuildType.AREA.toString());
		
		InventorySlotManager slot_manager = new InventorySlotManager();
		slot_manager.setInventory( virtual_inventory.getInventory() );
		
		super.setUpgradeSlot( 0  , Upgrades.GENERATE_MINERALZ_FIELD );
		super.setUpgradeSlot( 2  , Upgrades.GAME_RESTART            );
		super.setUpgradeSlot( 9  , Upgrades.GAME_LEVEL_LOW          );
		super.setUpgradeSlot( 10 , Upgrades.GAME_LEVEL_MIDDLE       );
		super.setUpgradeSlot( 11 , Upgrades.GAME_LEVEL_HIGH         );
		super.setUpgradeSlot( 17 , Upgrades.DESTROY                 );
		
		slot_manager.addItemToInventory( 0 , new ItemStack(Material.APPLE) , "Generate Mineralz Field (comming soon)");
		slot_manager.addItemToInventory( 2 , new ItemStack(Material.APPLE) , "Game Restart" );
		slot_manager.addItemToInventory( 9,  new ItemStack(Material.APPLE) , "Game Start - difficult: LOW ");
		slot_manager.addItemToInventory( 10, new ItemStack(Material.APPLE) , "Game Start - difficult: MID ");
		slot_manager.addItemToInventory( 11, new ItemStack(Material.APPLE) , "Game Start - difficult: HIGH");
		slot_manager.addItemToInventory( 17, new ItemStack(Material.APPLE) , "Destroy Area ");	
		
	}
}
