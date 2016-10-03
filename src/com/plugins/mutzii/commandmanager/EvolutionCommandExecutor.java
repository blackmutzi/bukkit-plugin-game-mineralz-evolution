package com.plugins.mutzii.commandmanager;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

import com.plugins.mutzii.exceptions.InvalidCommandException;
import com.plugins.mutzii.storage.CommandStore;

public class EvolutionCommandExecutor implements CommandExecutor 
{
	protected CommandSender sender;
	protected String[] args;
	protected String label;
	protected String permission;
	
	public EvolutionCommandExecutor() {}
		
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) 
	{
        boolean sender_is_player = false;
;         
        if( sender instanceof Player )
        		   sender_is_player = true;
        
		
        //Description Content 
		if( args.length == 0 )
		{
			 // Show Description 
			 for(EvolutionCommand command : CommandStore.getInstance().getList() )
			 { 
				 command.getInstance().showDescription();
			 }	
		}
        
       
		try
		{	

			if(args.length != 0)
			{
				String first_argument = args[0];
		  
				for(EvolutionCommand command : CommandStore.getInstance().getList() )
				{
					/* it known the first argument then call command */
					if( command.getFirstArgument().equalsIgnoreCase( first_argument ) )
					{
						
					  //set Attribute 
					  command.getInstance().setSender    (  sender );
					  command.getInstance().setArguments (  args   );
					  command.getInstance().setLabel     (  label  );
					  command.getInstance().setPermission( command.getPermission() );
				  
					  //run it 
					  command.getInstance().run();
				 
					  return false;
					}
				}
		  
		
			  		if( sender_is_player ){
			  			throw new InvalidCommandException("What are you doing? Command "+args[0]+" not exists. ;-)",sender);
			  		}
			 }
	  
	     }catch(InvalidCommandException e)
	     {
		  
		      if(e.getSender() != null)
		      {
			  
			      if( e.getSender() instanceof Player)
			      {  
				       Player sender_player = (Player)  e.getSender();
				       sender_player.sendMessage(e.getException());    
			      }
			      
			      if( e.getSender() instanceof ConsoleCommandSender || e.getSender() instanceof RemoteConsoleCommandSender)
				  {
					   Bukkit.getServer().getLogger().log(Level.WARNING, e.getException());
				  }   
			  }
	
	     }
	  
	  
		return false;
	}
	
	
	public static void register(String first_argument , String permission , CommandBehavior instance )
	{	
		  CommandStore.getInstance().register( first_argument , permission , instance );
	}
}
