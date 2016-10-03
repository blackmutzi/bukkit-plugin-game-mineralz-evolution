package com.plugins.mutzii.commandmanager;

import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.exceptions.InvalidCommandException;
import com.plugins.mutzii.players.GameConnection;

public class CommandLeaveGame extends CommandBehavior
{

	public CommandLeaveGame(Plugin instance) {
		super(instance);
	}

	@Override
	public void run() throws InvalidCommandException {

		if(getPlayer() != null)
		{
			if(hasPermission(getPlayer(), this.permission ))
			{
			     GameConnection.LeaveGame( getPlayer() );						
			}
		}	
	}

	@Override
	public void showDescription() 
	{
		 if(hasPermission( getPlayer() , this.permission ))
		  {
			  getPlayer().sendMessage(" Description: Leave Game ");
	    	  getPlayer().sendMessage("/game leave ");
	      }	
	}
	
	
}
