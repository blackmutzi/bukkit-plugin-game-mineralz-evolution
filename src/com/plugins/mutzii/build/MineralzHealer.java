package com.plugins.mutzii.build;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.api.MaterialValues;
import com.plugins.mutzii.api.Healer;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.buildingmanager.InventorySlotManager;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.InventoryType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.other.Converter;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.threads.SendPowerAsync;
import com.plugins.mutzii.upgrades.UpgradeBuildHealer;

public class MineralzHealer extends Building implements MaterialValues,Healer{
	
	private Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
	
	public MineralzHealer(Player player,MineralzPosition start, MineralzPosition ende) 
	{
		super(player, BuildType.HEALER , start, ende);
	
	    this.SetupInventory();
		
	    super.setPower( FULL_POWER );
	    
		super.setUpgradeManager(new UpgradeBuildHealer(this));
		
		BuildingStore.getInstance().register(this);
			
		Bukkit.getScheduler().runTaskAsynchronously( plugin , new SendPowerAsync(plugin,this) );
	}
	
	private void SetupInventory()
	{
		super.virtual_inventory.createVirtualInventory( InventoryType.SMALL.getNumber() , BuildType.HEALER.toString() );	
		
		InventorySlotManager slot_manager = new InventorySlotManager();
		slot_manager.setInventory( super.virtual_inventory.getInventory() );
		
		super.setUpgradeSlot( 0  , Upgrades.ENABLE_HEAL     );
		super.setUpgradeSlot( 1  , Upgrades.DISABLE_HEAL    );
		super.setUpgradeSlot( 6  , Upgrades.UPGRADE_HEALER  );
		super.setUpgradeSlot( 8  , Upgrades.DESTROY         );
		
		slot_manager.addItemToInventory( 0 , new ItemStack(Material.APPLE)  , ChatColor.GREEN+"Enable"+ChatColor.WHITE+" Heal ");
		slot_manager.addItemToInventory( 1 , new ItemStack(Material.APPLE)  , ChatColor.RED+"Disable"+ChatColor.WHITE+" Heal " );
		slot_manager.addItemToInventory( 6 , new ItemStack(Material.APPLE)  , "Upgrade Healer");
		slot_manager.addItemToInventory( 8 , new ItemStack(Material.APPLE)  , "Destroy Healer");		
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
	
	
	public void startHealProcess() throws NullPointerException
	{
		try
		{
			  HashMap<Building, MineralzPosition> powerFieldCollision = getDetectorManager().buildDetector();
			
			  for(Entry<Building,MineralzPosition> map : powerFieldCollision.entrySet())
			  {
					
					/* Healer is Tower healing  1:20 */
					if(map.getKey().getType() == BuildType.TOWER)
					{
						/* allowed to heal */
						MineralzTower tower = (MineralzTower) map.getKey();
						
						int power_available =      super.getPower();
						int power_need      = 1  * super.getLevel();
						int heal_points     = 20 * super.getLevel();
						
						if( power_available >= power_need )
						{
							super.last_healing = true;
							super.setPower( power_available - power_need );
							tower.setLive( heal_points );
							
							Location location = Converter.getLocation( super.getPlayer().getWorld() , super.getPosition() );
							super.getPlayer().getWorld().playEffect(location, Effect.POTION_BREAK, 1); 
							
							super.virtual_scoreboard.updateScoreboard();
							tower.virtual_scoreboard.updateScoreboard();
							
						}
							
					}
					
					
					
		      }
			
			
		}catch(NullPointerException e){
	  		throw new NullPointerException();
	  	}	
	}
	
}
