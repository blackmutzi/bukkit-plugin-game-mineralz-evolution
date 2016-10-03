package com.plugins.mutzii.build;

import java.util.ConcurrentModificationException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


import com.plugins.mutzii.api.MaterialValues;
import com.plugins.mutzii.api.Tower;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.buildingmanager.InventorySlotManager;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.InventoryType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.threads.ThreadManager;
import com.plugins.mutzii.threads.TowerShootAsync;
import com.plugins.mutzii.upgrades.UpgradeBuildTower;

public class MineralzTower extends Building implements MaterialValues,Tower{

	private boolean dispenser = false;
	private Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
	
	public MineralzTower(Player player,MineralzPosition start, MineralzPosition ende) 
	{
		super(player,BuildType.TOWER , start, ende);
				
		this.SetupInventory();
	
		super.setUpgradeManager(new UpgradeBuildTower(this));
	    
		BuildingStore.getInstance().register(this);	
		
		Bukkit.getScheduler().runTaskAsynchronously( plugin , new TowerShootAsync( this , getPlayer().getWorld() ));
	}
	
	
	private void SetupInventory()
	{
		super.virtual_inventory.createVirtualInventory( InventoryType.SMALL.getNumber() , BuildType.TOWER.toString() );	
		
		InventorySlotManager slot_manager = new InventorySlotManager();
		slot_manager.setInventory( super.virtual_inventory.getInventory() );
		
		super.setUpgradeSlot( 0  , Upgrades.ENABLE_TOWER_DISPENSER  );
		super.setUpgradeSlot( 1  , Upgrades.DISABLE_TOWER_DISPENSER );
		super.setUpgradeSlot( 8  , Upgrades.UPGRADE_TOWER           );
		super.setUpgradeSlot( 12 , Upgrades.ENABLE_TOWER            );
		super.setUpgradeSlot( 13 , Upgrades.DISABLE_TOWER           );
		super.setUpgradeSlot( 17 , Upgrades.DESTROY                 );
		
		slot_manager.addItemToInventory( 0 , new ItemStack(Material.APPLE) , ChatColor.GREEN+"Enable"+ChatColor.WHITE+" Dispenser");
		slot_manager.addItemToInventory( 1 , new ItemStack(Material.APPLE) , ChatColor.RED+"Disable"+ChatColor.WHITE+" Dispenser" );
		slot_manager.addItemToInventory( 8 , new ItemStack(Material.APPLE) , "Upgrade Tower");
		slot_manager.addItemToInventory( 12, new ItemStack(Material.APPLE) , ChatColor.GREEN+"Enable"+ChatColor.WHITE+" Tower ");
		slot_manager.addItemToInventory( 13, new ItemStack(Material.APPLE) , ChatColor.RED+"Disable"+ChatColor.WHITE+" Tower ");
		slot_manager.addItemToInventory( 17, new ItemStack(Material.APPLE) , "Destroy Tower" );	
		
	}
	
	@Override
	public void remove()
	{
		 getUpgradeManager().upgrade(Upgrades.DISABLE_TOWER_DISPENSER,this.getPlayer());
	  	 getUpgradeManager().upgrade(Upgrades.DISABLE_TOWER, this.getPlayer());
	  
	     ThreadManager.runTaskClearTower(plugin,this);
	     
	     super.remove();
	}
	

	    @Override
		public  double getTowerDamage(){
			 return getLevel() * 3 + getLive()-100;
		}
	
		@Override
		public  void shoot(Dispenser dispenser){
			
		  try{
			
		   dispenser.dispense();
		   dispenser.update();
		   
		  }catch(ConcurrentModificationException e){}
		
		}

		@Override
		public  void fillDispenser(Dispenser dispenser){
			
	    	try{
	    		
	    		dispenser.getInventory().clear();
	    	
	    		ItemStack stack   = new ItemStack(Material.ARROW,64);		
	    		dispenser.getInventory().addItem(stack);
			
			
	    	}catch(ConcurrentModificationException e){
	    		Bukkit.getServer().getLogger().log(Level.INFO, "Dispenser fill Bug");
	    	}
		}
	
		@Override
	    public void clearDispenser(Dispenser dispenser){
	    	dispenser.getInventory().clear();
	    }
	    
	    @Override
	    public void disableDispenser(){
	    	setDispenserStatus(false);
	    }
	
	    @Override
		public void enableDispenser(){
			setDispenserStatus(true);
		}
	
		@Override
		public  boolean isDispenserEnabled(){
			return this.dispenser;
		}
	
		@Override
		public  void setDispenserStatus(boolean status){
			this.dispenser = status;
		}
}
