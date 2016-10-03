package com.plugins.mutzii.listener;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.config.ConfigManager;

import com.plugins.mutzii.editor.PlayerDirection;
import com.plugins.mutzii.mineralz.MineralzPlayer;
import com.plugins.mutzii.other.AdminTool;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;
import com.plugins.mutzii.storage.GameStore;

public class MineralzPlayerListener implements Listener{

	public final Plugin plugin;
	
	public MineralzPlayerListener(final Plugin instance){
		this.plugin = instance;
	}
	
	public PlayerMineralzStore getPlayerStore(){
		return PlayerMineralzStore.getInstance();
	}
		
	public BuildingStore getBuildStore(){
		return BuildingStore.getInstance();
	}
	
	@EventHandler
	public void playerRespawn(PlayerRespawnEvent event)
	{
		Location location = GameStore.getInstance().getPlayerSpawnLocation();
		event.setRespawnLocation( location );		
	}
	
	
	@EventHandler 
	public void isPlayerDeath(PlayerDeathEvent event){
		
	     if( !PlayerMineralzStore.getInstance().isPlayerAlready( (Player) event.getEntity() ) )
		    return;
		
	   if(event.getEntity() instanceof Player)
	   {
		   MineralzPlayer  m_player = PlayerMineralzStore.getInstance().getPlayerMineralzz( (Player) event.getEntity() );
		   
		   // Set Player Behavior Live status 
		   m_player.setPlayerAliveStatus( false ); /* player is dead */
	   }	
	   
	}
	
	@EventHandler
	public void triggerUpgrades(InventoryClickEvent event)
	{	
		
		if( !PlayerMineralzStore.getInstance().isPlayerAlready( (Player) event.getWhoClicked() ) )
			    return;
		
		//Get Mineralz Player 
		MineralzPlayer  m_player = PlayerMineralzStore.getInstance().getPlayerMineralzz( (Player) event.getWhoClicked()  );

		//Get Inventory Building 
		Building building = m_player.getInventoryBuilding();
		
		if( building != null && m_player.hasVirtualInventoryOpen() )
	    {
				 // Trigger Action by Inventory Slot 
				 building.triggerUpgrade( event.getRawSlot() );
				 
				 // close this inventory 
				 building.virtual_inventory.closeVirtualInventory( (Player) event.getWhoClicked() );
				 m_player.setVirtualInventoryStatus( false );
				 
				 // remove building
				 m_player.setInventoryBuilding( null );
	    }
	}
	
	
	public void selectAnyField( Block block , Player player )
	{
		AdminTool tool = AdminTool.getInstance();
		
		if(! tool.isFirstLocationSet() || !tool.isSecondLocationSet())
		{
			tool.setLocation( block.getLocation() );	
			 
			MineralzPlayer  m_player = PlayerMineralzStore.getInstance().getPlayerMineralzz( player );
			m_player.setLastPlayerDirection( new PlayerDirection(player).getDirection() );		
		}
		
		if( tool.isFirstLocationSet() && tool.isSecondLocationSet() )
		{
	    	 tool.removeAdminItem( player );
	    	 player.sendMessage("You can now the /game save command : Selected is finish.");
	    }
	}
	
	public void setMonsterSpawnLocation( Block block , Player player )
	{
		AdminTool tool = AdminTool.getInstance();
		
	    //Monster Direction
		GameStore.getInstance().setMonsterDirection( new PlayerDirection(player).getDirection() );
		player.sendMessage("Monster Move Direction is now set.");
			
		//Monster Location
		GameStore.getInstance().setMonsterSpawnLocation( block.getLocation() );
		player.sendMessage("Monster Spawn Location is now set.");
		
		tool.removeAdminItem( player );
					
	}
	
	public void setPlayerSpawnLocation( Block block , Player player )
	{
		AdminTool tool = AdminTool.getInstance();
		
		GameStore.getInstance().setPlayerSpawnLocation( block.getLocation());
		player.sendMessage("Player Location is now set.");
		
		tool.removeAdminItem( player );
	}
	
	public void onBlockClickedEvent( Block block , Player player  )
	{
		AdminTool tool = AdminTool.getInstance();
		
		if( tool.getName().equalsIgnoreCase("spawn")) 
		{
			setMonsterSpawnLocation( block , player );
			
		}else if( tool.getName().equalsIgnoreCase("player") )
		{
			setPlayerSpawnLocation( block , player );
	
		}else{
			
			selectAnyField( block , player );	
		}		
	}
	
	public void openVirtualInventory( PlayerInteractEvent event )
	{
		 Block block = event.getClickedBlock();
		 MineralzPosition blockPosition = new MineralzPosition(block.getX(),block.getY(),block.getZ());
		 Building build = getBuildStore().getBuilding(event.getPlayer(), blockPosition);
		 
		 if( build != null )
		 {
			   //save building 
			   MineralzPlayer  m_player = PlayerMineralzStore.getInstance().getPlayerMineralzz( event.getPlayer() );
			   m_player.setInventoryBuilding( build );
			 
			   //update / set price values 
			   ConfigManager config = new ConfigManager(this.plugin);
    		   int slot = 0 ;
    		   for(ItemStack stack : build.virtual_inventory.getInventory().getContents()){
    			   
    			   if(stack != null){
    				   			    				   
    				   ItemMeta meta = stack.getItemMeta();
    				   meta.setLore(config.getArrayList(build,slot));
    				   
    				   stack.setItemMeta(meta);
    				   build.virtual_inventory.getInventory().setItem(slot, stack);
    			   }
    			   
    			  slot++;
    		   }
			 
    		   //open inventory 
    		   build.virtual_inventory.openVirtualInventory( event.getPlayer() );
    		   m_player.setVirtualInventoryStatus( true );
		 } 
	}
	
			
	@EventHandler
	public void onPlayerInteractEvent( PlayerInteractEvent event )
	{
		if( !PlayerMineralzStore.getInstance().isPlayerAlready( event.getPlayer()  ) )
		    return;
		
		
		AdminTool tool = AdminTool.getInstance();
		
		if( event.getPlayer().equals( tool.getPlayerAdmin() ) )
		{
			if( event.getClickedBlock().getType() != Material.AIR )
			{
				if( event.getPlayer().getInventory().getItemInMainHand().equals( tool.getAdminToolItem() ) )
				{
					 onBlockClickedEvent( event.getClickedBlock() , event.getPlayer() );
				}
			}
		}else
		{
			/*Admin Modus is not active */
			
			if( event.getClickedBlock().getType() != Material.AIR )
			{
				openVirtualInventory( event );	
			}			
		}
	}
		
			
//	@EventHandler
//	public void playerRegister(PlayerJoinEvent event){
//		
//		/**
//		 * Reconnect Exception
//		 */boolean reconnect = false;
//		
//		 
//		//Restore Assign Mineralz to Player
//		if(PlayerMineralzStore.getInstance().isPlayerAlready(event.getPlayer().getName())){ //true
//			
//				MineralzPlayer pM = PlayerMineralzStore.getInstance().getPlayerMineralz(event.getPlayer().getName());
//				pM.setPlayer(event.getPlayer());
//				reconnect = true;
//				
//				GameConnection register = new GameConnection(plugin,event.getPlayer());
//				register.reconnect();
//				PlayerStore.getInstance().register(event.getPlayer(),register);
//				
//				
//		}else{
//			MineralzEvolution.playerRegister(this.plugin, event.getPlayer());
//		}
//		
//		
//		if(reconnect){
//			
//			//Restore Assign Buildings to Player
//			for(BuildingManager manager : BuildingStore.getInstance().getList()){
//				
//				if(manager.getPlayer().getName().equalsIgnoreCase(event.getPlayer().getName())){
//					manager.setPlayer(event.getPlayer());
//				}
//			}
//			
//			MineralzEvolution.clearPlayerInventory(event.getPlayer());
//		}
//		
//		
//	}
	
	
//	@EventHandler
//	public void closePlayerThread(PlayerQuitEvent event){
//		
//		if(PlayerStore.getInstance().isPlayerAlready(event.getPlayer())){
//		
//			PlayerBehavior pStore = PlayerStore.getInstance().getPlayer(event.getPlayer());
//			pStore.getPlayerRegister().closeThread();
//			MineralzEvolution.removeSideBarFromPlayer(event.getPlayer());
//			PlayerStore.getInstance().unregister(event.getPlayer());
//		
//		}
//		
//	}
	
	

	
}
