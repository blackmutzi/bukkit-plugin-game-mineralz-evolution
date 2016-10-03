package com.plugins.mutzii.exceptions;

import org.bukkit.command.CommandSender;

public class InvalidCommandException extends Exception{

	private static final long serialVersionUID = 1539075336761473879L;
	
	private String exception;
	private CommandSender sender;
	
	public InvalidCommandException(String exception){
		setException(exception);
	}
	
	public InvalidCommandException(String exception,CommandSender sender){
		setException(exception);
		setCommandSender(sender);
	}
	
	
	public CommandSender getSender(){
		return this.sender;
	}
	
	public String getException(){
		return this.exception;
	}
	
	public void setCommandSender(CommandSender sender){
		this.sender = sender;
	}
	
	public void setException(String exception){
		this.exception = exception;
	}
	
	

}
