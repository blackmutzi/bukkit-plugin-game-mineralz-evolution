package com.plugins.mutzii.commandmanager;

public class EvolutionCommand 
{
	private CommandBehavior command_instance ;
	private String first_argument;
	private String permission;
	
	public EvolutionCommand(String first_argument, String permission , CommandBehavior command_instance )
	{
		this.first_argument   = first_argument;
		this.permission       = permission;
		this.command_instance = command_instance;		
	}
	
	public CommandBehavior getInstance() {
		return this.command_instance;
	}
	
	public String getFirstArgument(){
		return this.first_argument;
	}
	
	public String getPermission()
	{
		return this.permission;
	}

}
