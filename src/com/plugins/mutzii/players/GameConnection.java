package com.plugins.mutzii.players;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.plugins.mutzii.mineralz.MineralzPlayer;
import com.plugins.mutzii.plugin.MineralzEvolution;
import com.plugins.mutzii.storage.GameStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class GameConnection 
{	
	public GameConnection(){}
			
	public static void JoinGame( Player player )
	{
		 //TODO: Thread are Removed. Create a Global Thread for all Players to Call ChangeFood every Time 
		 //threadinstance = new CallTask(new ChangePlayerFoodAsync(plugin,this));
	     //threadinstance.StartMyThread();
	     //ThreadManager.registerTask("ChangePlayerFoodAsync",threadinstance);
		
		 if(GameStore.getInstance().isGameRunning()) 
		 {	  
			 //TODO: Show Log - Player Instance is actually Running - anywhere can not join 
			 return;
		 }
		
		 /* check player - is false : then next part */
		 if( !PlayerMineralzStore.getInstance().isPlayerAlready( player.getName() ) )
	     {
			 //clear player inventory 
			 MineralzEvolution.clearPlayerInventory( player );
			 
			 //register in player store 
			 PlayerMineralzStore.getInstance().register( new MineralzPlayer( player ) );
			  
			 //give player a Item (COBBLESTONE) to Build the Base 
			 player.getInventory().setItemInMainHand( new ItemStack( Material.COBBLESTONE ) );	
			 
			 //TODO: Show Log: Mineralz Player created 
			 //TODO: Show / Update TAB Scoreboard 
	     }
	}
	
	public static void LeaveGame( Player player )
	{
	    /* exists player already then true */
		if( PlayerMineralzStore.getInstance().isPlayerAlready( player.getName() ) )
		{
			//clear player inventory 
			MineralzEvolution.clearPlayerInventory( player );
			
			//remove player from player store 
			PlayerMineralzStore.getInstance().unregister( player );
		}	
	}
	
	public static void ReJoin( Player player )
	{
		/* player rejoin can only if the game already register it */
		if( PlayerMineralzStore.getInstance().isPlayerAlready( player.getName() ) )
		{
			// Teleport Player back to Game Area Spawn Location 
			 MineralzEvolution.teleportPlayer( player , GameStore.getInstance().getPlayerSpawnLocation() );
			 
			//TODO: Show / Update TAB Scoreboard 
		}
	}		
}
