package com.plugins.mutzii.commandmanager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.exceptions.InvalidCommandException;
import com.plugins.mutzii.other.AdminTool;

public class CommandSelect extends CommandBehavior
{
	private List< String > list_of_arguments = new ArrayList<String>();
		
	public CommandSelect(Plugin instance)
	{
		super(instance);

		list_of_arguments.add("spawn");
		list_of_arguments.add("area ");
		list_of_arguments.add("tower");
		list_of_arguments.add("generator");
		list_of_arguments.add("healer");
		list_of_arguments.add("base");
		
	}

	@Override
	public void run() throws InvalidCommandException
	{

		if(getPlayer() != null){
			
				if(getArguments().length > 1){
				
					if( hasPermission(getPlayer(), this.permission ))
					{
						
						String argument = getArguments()[1];
						
						if( isValid( argument ) )
						{
							
							AdminTool.getInstance().removeAdminItem    ( getPlayer() );
							AdminTool.getInstance().givePlayerAdminItem( getPlayer() );					
							AdminTool.getInstance().setFileName( argument );
						
						}else
						{
							throw new InvalidCommandException(ChatColor.RED+"What is "+ChatColor.WHITE+ argument +" ? ",getPlayer());
						}
						
							
					}
				}
		}
	}
	
	private boolean isValid( String argument )
	{
		return list_of_arguments.contains( argument );
	}

	@Override
	public void showDescription() 
	{
		if( hasPermission(getPlayer(), this.permission ))
		{
			getPlayer().sendMessage("Description: select a field ");
			getPlayer().sendMessage("/game select spawn | area | tower | generator | base | healer ");
		}
	}

}
