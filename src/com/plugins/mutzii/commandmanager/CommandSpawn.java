package com.plugins.mutzii.commandmanager;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.config.Identification;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.exceptions.InvalidCommandException;

public class CommandSpawn extends CommandBehavior
{

	public CommandSpawn(Plugin instance) 
	{
		super(instance);
	}

	@Override
	public void run() throws InvalidCommandException 
	{
		 if(getPlayer() != null)
		 {
			 	if(hasPermission(getPlayer(), this.permission ) )
			 	{
			 		    if( getArguments().length == 2 )
			 		    {
			 		    	String command = getArguments()[1];
			 		    	
			 		    	//command: /game spawn area 
			 		    	if( command.equalsIgnoreCase("area") )
			 		    	{
			 		    		Identification i = new Identification();
			 		    		
			 		    		//give player itemInMainHand 
			 		    		getPlayer().getInventory().setItemInMainHand( new ItemStack( i.getMaterialByBuildType( BuildType.AREA ) ) );
			 		    		
			 		    	}	
			 		    }
			 	}
		 }
	}

	@Override
	public void showDescription() 
	{
		if(hasPermission(getPlayer(), this.permission ) )
	 	{
			 getPlayer().sendMessage("Description: give player the area ItemInMainHand ");
			 getPlayer().sendMessage("/game spawn area");
	 	}
	}

}
