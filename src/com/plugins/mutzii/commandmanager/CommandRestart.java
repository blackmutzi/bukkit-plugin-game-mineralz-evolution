package com.plugins.mutzii.commandmanager;

import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.exceptions.InvalidCommandException;
import com.plugins.mutzii.storage.GameStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class CommandRestart extends CommandBehavior
{
	private Plugin plugin;
	
	public CommandRestart(Plugin instance) {
		super(instance);
		
		this.plugin = instance;
	}

	@Override
	public void run() throws InvalidCommandException {

		if(getPlayer() != null)
		{
			if(hasPermission(getPlayer(), this.permission ))
			{
				if( !PlayerMineralzStore.getInstance().isPlayerAlready( getPlayer() ) )
				    return;
				
				
			     GameStore.getInstance().GameRestart( this.plugin );				
			}
		}
		
		
		
	}

	@Override
	public void showDescription() 
	{
		if(hasPermission( getPlayer(), this.permission ))
		{
		     getPlayer().sendMessage("Description: Game Restarting ");
		     getPlayer().sendMessage("/game restart ");
		}
	}

}
