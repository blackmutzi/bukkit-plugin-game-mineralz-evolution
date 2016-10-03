package com.plugins.mutzii.commandmanager;

import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.exceptions.InvalidCommandException;
import com.plugins.mutzii.mineralz.ShareMineralz;


public class CommandDebug extends CommandBehavior {

	public CommandDebug(Plugin instance) {
		super(instance);
	}

	@Override
	public void run() throws InvalidCommandException {
		
		if(getPlayer() != null){
			
			if(hasPermission(getPlayer(), super.permission )){
			
				//is a Cheat for Developer add 1000 Mineralz 
				
				ShareMineralz.changeMineralAmountPositive( getPlayer() , MineralzType.MINERAL_BLUE  , 1000 );
				ShareMineralz.changeMineralAmountPositive( getPlayer() , MineralzType.MINERAL_RED   , 1000 );
				ShareMineralz.changeMineralAmountPositive( getPlayer() , MineralzType.MINERAL_GREEN , 1000 );
				ShareMineralz.changeMineralAmountPositive( getPlayer() , MineralzType.MINERAL_LILA  , 1000 );
				
				
				throw new InvalidCommandException("You Cheater :D",getPlayer());
			}
		}
		
		
	}

	@Override
	public void showDescription() 
	{
		 if(hasPermission( getPlayer() , super.permission ) )
		  {
  	    	 getPlayer().sendMessage("Description: Mineralz Cheat");
  	    	 getPlayer().sendMessage("/game debug");
  	      }
		
	}

}
