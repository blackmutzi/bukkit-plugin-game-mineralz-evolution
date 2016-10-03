package com.plugins.mutzii.listener;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.api.MineralzInterface;
import com.plugins.mutzii.build.MineralzBase;
import com.plugins.mutzii.config.ConfigManager;
import com.plugins.mutzii.config.Identification;
import com.plugins.mutzii.config.MineralzConfig;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.mineralz.Mineralz;
import com.plugins.mutzii.mineralz.MineralzPlayer;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.storage.MineralzStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;


public class MineralzBlockListener implements Listener{

	public final Plugin plugin;
	
	public MineralzBlockListener(final Plugin instance){
		this.plugin = instance;
	}
	
	public MineralzStore getMineralStore(){
		return MineralzStore.getInstance();
	}
	
	public PlayerMineralzStore getPlayerStore(){
		return PlayerMineralzStore.getInstance();
	}
	
	public BuildingStore getBuildStore(){
		return BuildingStore.getInstance();
	}
	
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
	    createBuildingByBlock( event );
	}
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		registerMineralz( event );
		
		transferMineralz( event );
	}
	
	public void createBuildingByBlock( BlockPlaceEvent event )
	{  
		 MineralzPosition position = new MineralzPosition(event.getBlock().getX(),event.getBlock().getY(),event.getBlock().getZ());
		
		 if( new Identification().getBuildTypeByBlock( event.getBlock() ) != null )
		 {
              new ConfigManager( this.plugin ).createBuilding(event.getPlayer(), position, event.getBlock() );
		 }
	}
	
	public void registerMineralz( BlockBreakEvent event )
	{
		MineralzPosition position = new MineralzPosition(event.getBlock().getX(),event.getBlock().getY(),event.getBlock().getZ());
		MineralzConfig config     = new MineralzConfig();
		
		/* is event.getBlock a Mineral (WOOL) : true */
		if( config.identification( event.getBlock() )  != null )
		{
				/* is the mineral not register : true  */
				if( !getMineralStore().isMineralBlockRecorded ( position ) )
				{
					  /* register the mineral now */
						
					   Mineralz new_mineralz = new Mineralz();
					   
					   new_mineralz.setMineralzAmount  ( 5000 ); /* Amount Mineralz into a Mineralz Block: default 5000  */
					   new_mineralz.setMineralzColor   ( config.identification( event.getBlock() )        );
					   new_mineralz.setMineralzName    ( config.identification( event.getBlock() ).name() );
					   new_mineralz.setMineralzPosition( position );
					
					   /* register it */
					   getMineralStore().register( new_mineralz );
					   Bukkit.getServer().getLogger().log(Level.INFO,"register a new mineralz ");
					 		
				}
		}	
	}
	
	public void transferMineralz( BlockBreakEvent event )
	{
		MineralzPosition position = new MineralzPosition(event.getBlock().getX(),event.getBlock().getY(),event.getBlock().getZ());
		MineralzConfig config     = new MineralzConfig();
		
		/* is event.getBlock() a Mineral : true */
		if( config.identification( event.getBlock() ) != null )
		{
			   /* if the mineral already register : true */
			if( getMineralStore().isMineralBlockRecorded( position ) )
			{
				 /* check the player - if the player a MineralzPlayer then : true */
				 if( PlayerMineralzStore.getInstance().isPlayerAlready(event.getPlayer() ) )
				 {
					  /* Get Type / Color  Information from actually Mineral */
					  MineralzInterface mineral = getMineralStore().getMineralz(position);
				      MineralzType         type = mineral.getMineralzColor();
					 
					  /* MineralzBase from Player X */
					  MineralzBase mineralzbase = (MineralzBase) getBuildStore().getBuildingBase( event.getPlayer() );
					    
					  /* Set / Update mineral_mining_amount */
					  int mineral_mining_amount = 0; 
					  int mineral_level         = 0;
					 
					  /* Get Mineral Level from MineralzBase */
					  if( mineralzbase != null)
						  	mineral_level = mineralzbase.getMineralLevel(type);
					  
					  /* Mineralz Mining Algo : result = mineral_mining_amount */
					  mineral_mining_amount = mineral_level * 2 + mineralzbase.getLevel(); 
					  
					  /* transfer mineral_mining_amount */
					  /* remove mineral_mining_amount from mineralz */
					  mineral.setMineralzAmount( mineral.getMineralzAmount() - mineral_mining_amount );
					  
					  /* add mineral_mining_amount to Player */
					  MineralzPlayer m_player = getPlayerStore().getPlayerMineralzz( event.getPlayer() );
					  int last_amount         = m_player.getMineralAmount( type );
					  m_player.changeMineralAmount( last_amount + mineral_mining_amount , type );
					 
					  /* at the last one - cancel event for not destroying the mineralz block */
					  event.setCancelled( true ); 
					  
					  /* has the mineralz block no more mineralz as MineralzAmount == 0 then destroy it */
					  if( mineral.getMineralzAmount() <= 0 )
					  {
						  /* break the block : yes */
						  event.setCancelled( false );
						  
						  /* unregister from database */
						  getMineralStore().unregister(position);
					  }
						       
					  
					  //TODO: Show /Update Mineralz Blocks Scoreboard (SIDEBAR)
					  //TODO: Show /Update TAB Scroreboard from Player 
				 }
					
			}
		}	
	}

}
