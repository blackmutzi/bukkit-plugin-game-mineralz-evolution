package com.plugins.mutzii.build;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.plugins.mutzii.api.Base;
import com.plugins.mutzii.api.MaterialValues;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.buildingmanager.InventorySlotManager;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.InventoryType;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.players.PlayerBehavior;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;
import com.plugins.mutzii.upgrades.UpgradeBuildBase;

public class MineralzBase extends Building implements MaterialValues,Base{
 
	private final int UPGRADE_DEFAULT_LEVEL = 1;
    
	public MineralzBase(Player player, MineralzPosition start, MineralzPosition ende) {
		
		super(player, BuildType.BASE , start, ende);
		
		//Inventory Name: BuildType.Base 
		//Inventory Size: InventoryType.MIDDLE
		
		this.SetupInventory();
		
		this.setMineralLevel(MineralzType.MINERAL_BLUE  ,UPGRADE_DEFAULT_LEVEL );
		this.setMineralLevel(MineralzType.MINERAL_RED   ,UPGRADE_DEFAULT_LEVEL );
		this.setMineralLevel(MineralzType.MINERAL_GREEN ,UPGRADE_DEFAULT_LEVEL );
		this.setMineralLevel(MineralzType.MINERAL_LILA  ,UPGRADE_DEFAULT_LEVEL );
			
		super.setUpgradeManager( new UpgradeBuildBase(this) );
		
		BuildingStore.getInstance().register(this);	
	}
	
	private void SetupInventory()
	{
		//Inventory Name: Base
	    //Inventory Size: InventoryType.MIDDLE
		virtual_inventory.createVirtualInventory( InventoryType.MIDDLE.getNumber() , BuildType.BASE.toString() );
		
		InventorySlotManager slot_manager = new InventorySlotManager();
		slot_manager.setInventory( virtual_inventory.getInventory() );
		
		super.setUpgradeSlot( 0  , Upgrades.BUILD_TOWER );
		super.setUpgradeSlot( 1  , Upgrades.BUILD_HEALER            );
		super.setUpgradeSlot( 2  , Upgrades.BUILD_GENERATOR         );
		super.setUpgradeSlot( 8  , Upgrades.UPGRADE_BASE            );
		super.setUpgradeSlot( 9  , Upgrades.UPGRADE_ALL_MINERAL     );
		super.setUpgradeSlot( 10 , Upgrades.UPGRADE_MINERAL_BLUE    );
		super.setUpgradeSlot( 11 , Upgrades.UPGRADE_MINERAL_RED     );
		super.setUpgradeSlot( 12 , Upgrades.UPGRADE_MINERAL_LILA    );
		super.setUpgradeSlot( 13 , Upgrades.UPGRADE_MINERAL_GREEN   );
		super.setUpgradeSlot( 17 , Upgrades.DESTROY                 );
		
		slot_manager.addItemToInventory( 0 , new ItemStack(Material.SANDSTONE)           , "Build Tower");
		slot_manager.addItemToInventory( 1 , new ItemStack(Material.STONE)               , "Build Healer" );
		slot_manager.addItemToInventory( 2 , new ItemStack(Material.BRICK)               , "Build Generator ");
		slot_manager.addItemToInventory( 8 , new ItemStack(Material.COBBLESTONE)         , "Upgrade Base  ");
		slot_manager.addItemToInventory( 9 , new ItemStack(Material.WOOL,1,WOOL_WHITE  ) , "Upgrade all Mineralz");
		slot_manager.addItemToInventory( 10, new ItemStack(Material.WOOL,1,WOOL_BLUE   ) , "Upgrade"+ChatColor.BLUE+" Blue "+ChatColor.WHITE+"Mineral");
		slot_manager.addItemToInventory( 11, new ItemStack(Material.WOOL,1,WOOL_RED    ) , "Upgrade"+ChatColor.RED+" Red "+ChatColor.WHITE+"Mineral");	
		slot_manager.addItemToInventory( 12, new ItemStack(Material.WOOL,1,WOOL_MAGENTA) , "Upgrade"+ChatColor.LIGHT_PURPLE+" Purple "+ChatColor.WHITE+"Mineral");	
		slot_manager.addItemToInventory( 13, new ItemStack(Material.WOOL,1,WOOL_GREEN  ) , "Upgrade"+ChatColor.GREEN+" Green "+ChatColor.WHITE+"Mineral");	
		slot_manager.addItemToInventory( 17, new ItemStack(Material.APPLE) 				 , "Destroy Base ");	
		
	}
	
	@Override
	public void remove()
	{
		/* Remove Mineralz Base Instance from Player Store */	
		PlayerBehavior player = ( PlayerBehavior ) PlayerMineralzStore.getInstance().getPlayerMineralzz( getPlayer() );
		player.setMineralzBase( null );
				
		super.remove();
	}
	

	@Override
	public  void triggerUpgrade(int slot) {
		getUpgradeManager().upgrade(getUpgrade(slot), getPlayer());
	}

	
	@Override
	public int getMineralLevel(MineralzType type) {
		return upgradeLevel.get(type);
	}
	
	@Override
	public void setMineralLevel(MineralzType type, int level){
		upgradeLevel.put(type,level);
	}

}
