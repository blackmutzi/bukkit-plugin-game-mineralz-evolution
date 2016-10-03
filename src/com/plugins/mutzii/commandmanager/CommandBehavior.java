package com.plugins.mutzii.commandmanager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.api.EvolutionCommandInterface;
import com.plugins.mutzii.plugin.VaultPermission;

public abstract class CommandBehavior extends VaultPermission implements  EvolutionCommandInterface 
{
    protected Plugin plugin;
    protected CommandSender sender;
    protected String[] args;
    protected String label;
    protected String permission;
	
    public CommandBehavior( Plugin plugin )
    {
    	super( plugin );
    	this.plugin = plugin;
    }
    
	public Player getPlayer()
	{
	  	return (Player) this.sender;
	}
	
	public String[] getArguments() 
	{
		return this.args;
	}
	
	public void setSender(CommandSender sender){
		this.sender = sender;
	}
	
	public void setArguments(String[] args){
		this.args = args;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public void setPermission( String permission )
	{
		this.permission = permission;
	}
	
}
