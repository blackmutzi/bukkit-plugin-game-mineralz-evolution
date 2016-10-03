package com.plugins.mutzii.commandmanager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.exceptions.InvalidCommandException;
import com.plugins.mutzii.mineralz.ShareMineralz;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class CommandSend extends CommandBehavior {

	public CommandSend(Plugin instance) {
		super(instance);
	}

	@Override
	public void run() throws InvalidCommandException 
	{

		if( hasPermission(getPlayer(), this.permission ) ){
			
				if(getArguments().length == 4)
				{
					
					try
					{

						if( !PlayerMineralzStore.getInstance().isPlayerAlready( getPlayer()  ) )
						    return;
						
					String option = getArguments()[1];
					Player player = getCustomPlayer(getArguments()[2]);
					int    amount = getAmount(getArguments()[3]);

					ShareMineralz send = new ShareMineralz( getPlayer() );
					send.sendMineralTo(player,getType(option), amount);
					
				
					}catch(InvalidCommandException e){
						throw new InvalidCommandException(e.getException(),e.getSender());
					}
			  
				}

		}
		
	}
	
	
	
	public MineralzType getType(String option) throws InvalidCommandException{
		
			if(option.equalsIgnoreCase("-r"))
					return MineralzType.MINERAL_RED;
			
			if(option.equalsIgnoreCase("-g"))
					return MineralzType.MINERAL_GREEN;
			
			if(option.equalsIgnoreCase("-b"))
					return MineralzType.MINERAL_BLUE;
			
			if(option.equalsIgnoreCase("-m"))
					return MineralzType.MINERAL_LILA;
			
			throw new InvalidCommandException(ChatColor.RED+"Bad MineralzType. Usage -r,-g,-b,-m",getPlayer());
	}
	
	public int getAmount(String amount) throws InvalidCommandException{
		
		try{
			
			return Integer.valueOf(amount);
			
		}catch(NumberFormatException e){
			throw new InvalidCommandException(ChatColor.RED+"What happend? " +amount+ " is no Number.",getPlayer());
		}
	}
	
	public Player getCustomPlayer(String playerName) throws InvalidCommandException
	{
		return PlayerMineralzStore.getInstance().getPlayerMineralz( playerName ).getPlayer();
	}

	@Override
	public void showDescription() 
	{
	   if( hasPermission(getPlayer(), this.permission ) )
	   {
		   getPlayer().sendMessage("Description: send another Player Mineralz");
	       getPlayer().sendMessage("/game send [options: -r| -g | -b | -m] [player] [amount]");
	   }
		
	}
	

}
