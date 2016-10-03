package com.plugins.mutzii.build;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.api.MaterialValues;
import com.plugins.mutzii.api.Generator;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.buildingmanager.InventorySlotManager;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.InventoryType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.threads.SendPowerAsync;
import com.plugins.mutzii.upgrades.UpgradeBuildGenerator;

public class MineralzGenerator extends Building implements MaterialValues,Generator
{
	
	private Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
	
	public MineralzGenerator(Player player,MineralzPosition start, MineralzPosition ende) {
		
		super(player,BuildType.GENERATOR , start, ende);
		
		this.SetupInventory();
		
		super.setPower( FULL_POWER );
		super.setPowerInterval(1000); //1sec
		super.setUpgradeManager(new UpgradeBuildGenerator(this));
		
		BuildingStore.getInstance().register(this);	
		
		
		/* Start Send Power Thread Loop */
		Bukkit.getScheduler().runTaskAsynchronously( plugin , new SendPowerAsync(plugin,this) );		
	}

	private void SetupInventory()
	{
		super.virtual_inventory.createVirtualInventory( InventoryType.SMALL.getNumber() , BuildType.GENERATOR.toString() );
		
		InventorySlotManager slot_manager = new InventorySlotManager();
		slot_manager.setInventory( super.virtual_inventory.getInventory() );
		
		super.setUpgradeSlot( 0  , Upgrades.ENABLE_POWER         );
		super.setUpgradeSlot( 1  , Upgrades.DISABLE_POWER        );
		super.setUpgradeSlot( 6  , Upgrades.UPGRADE_GENERATOR    );
		super.setUpgradeSlot( 8  , Upgrades.DESTROY              );
		
		slot_manager.addItemToInventory( 0 , new ItemStack(Material.APPLE) , ChatColor.GREEN+"Enable"+ChatColor.WHITE+" Power ");
		slot_manager.addItemToInventory( 1 , new ItemStack(Material.APPLE) , ChatColor.RED+"Disable"+ChatColor.WHITE+" Power " );
		slot_manager.addItemToInventory( 6 , new ItemStack(Material.APPLE) , "Upgrade Generator");
		slot_manager.addItemToInventory( 8 , new ItemStack(Material.APPLE) , "Destroy Generator");
		
	}
	
	@Override
	public void sendPower() throws NullPointerException
	{
		try
		{
			  HashMap<Building, MineralzPosition> powerFieldCollision = getDetectorManager().buildDetector();
			
			  for(Entry<Building,MineralzPosition> map : powerFieldCollision.entrySet())
			  {
				   
				    if(map.getKey().getType() == BuildType.HEALER)
					{
					    /* send power allowed */
						/* Power: 3 * Generator Level */
						
						MineralzHealer healer = (MineralzHealer) map.getKey();
						healer.setPower( 3 * super.getLevel() );
						
						healer.virtual_scoreboard.updateScoreboard();				
					}
			  }
			
			
		}catch(NullPointerException e){
	  		throw new NullPointerException();
	  	}	
	}
	
	@Override
	public void remove()
	{
		  setRemove(true);
		   try {
			Thread.sleep(300);
		   } catch (InterruptedException e) {}
	    
		super.remove();
	}
	

}
