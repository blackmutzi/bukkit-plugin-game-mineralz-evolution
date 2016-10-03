package com.plugins.mutzii.threads;

import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.mineralz.MineralzPlayer;
import com.plugins.mutzii.monster.MonsterSpawner;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class EvolutionSyncThread implements Runnable
{
    private Plugin plugin;
    
    public EvolutionSyncThread( Plugin plugin )
    {
    	this.plugin = plugin;
    }
	
	public void changePlayerFood()
	{
		for( MineralzPlayer player : PlayerMineralzStore.getInstance().getList() )
		{
			player.getPlayer().setFoodLevel( 20 );
		}
	}
	
	public void processSpawnEntitys()
	{
		if( ThreadInteract.getInstance().spawn_entity_process )
		{
			MonsterSpawner spawner = new MonsterSpawner();
			
			for( int count=0; count < ThreadInteract.getInstance().spawn_entitys; count++)
			{
			    spawner.createMonster( ThreadInteract.getInstance().entity_live_points );
			}
			
			ThreadInteract.getInstance().spawn_entity_process = false;
		}
	}
    
	

	/**
	 *  Sync Tasks 
	 */
	@Override
	public void run()
	{
	    changePlayerFood();
		
	    processSpawnEntitys();
	    
	}

}
