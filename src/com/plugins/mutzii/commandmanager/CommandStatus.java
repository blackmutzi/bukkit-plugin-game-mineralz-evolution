package com.plugins.mutzii.commandmanager;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.build.MineralzTower;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.exceptions.InvalidCommandException;
import com.plugins.mutzii.mineralz.MineralzPlayer;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class CommandStatus extends CommandBehavior  {

	public CommandStatus(Plugin instance) 
	{
		super(instance);
	}

	@Override
	public void run() throws InvalidCommandException 
	{
		
		if(getPlayer() != null){
			if(hasPermission( getPlayer(), this.permission ))
			{
			
				if( !PlayerMineralzStore.getInstance().isPlayerAlready( getPlayer() ) )
				    return;
				
				
					MineralzPlayer m_player = PlayerMineralzStore.getInstance().getPlayerMineralzz(getPlayer());
					
					int blue    = m_player.getMineralAmount( MineralzType.MINERAL_BLUE  );
					int red     = m_player.getMineralAmount( MineralzType.MINERAL_RED   );
					int green   = m_player.getMineralAmount( MineralzType.MINERAL_GREEN );
					int magenta = m_player.getMineralAmount( MineralzType.MINERAL_LILA  );
					
					
					getPlayer().sendMessage(ChatColor.GOLD+"~  +++++ You Mineralz +++++  ~");
					getPlayer().sendMessage(" Mineral: "+ChatColor.BLUE+String.valueOf(blue));
					getPlayer().sendMessage(" Mineral: "+ChatColor.RED+String.valueOf(red));
					getPlayer().sendMessage(" Mineral: "+ChatColor.GREEN+String.valueOf(green));
					getPlayer().sendMessage(" Mineral: "+ChatColor.LIGHT_PURPLE+String.valueOf(magenta));
	
				
				getPlayer().sendMessage(ChatColor.GOLD+"~  +++++ You Buildings +++++  ~");
				for(Building build : BuildingStore.getInstance().getBuildings(getPlayer()))
				{

						getPlayer().sendMessage(ChatColor.GREEN+"Name: "+ChatColor.WHITE+build.getName());
						getPlayer().sendMessage(ChatColor.GREEN+"Level: "+ChatColor.GOLD+build.getLevel());
					
						if(build.isActiv()){
							getPlayer().sendMessage("Status: "+ChatColor.GREEN+" Enabled ");
						}else{
							getPlayer().sendMessage(ChatColor.GREEN+"Status: "+ChatColor.RED+" Disabled ");
						}
						
						if(build.getType() == BuildType.TOWER){
							
							MineralzTower tower = (MineralzTower)build;
							if(tower.isDispenserEnabled()){
								getPlayer().sendMessage("Dispenser: "+ChatColor.GREEN+" Enabled ");
							}else{
								getPlayer().sendMessage("Dispenser: "+ChatColor.RED+" Disabled ");
							}
							
							getPlayer().sendMessage("Damage: "+ChatColor.GOLD+String.valueOf(tower.getTowerDamage()));	
						}
	
				}

			}
		}
		
		
		
	}

	@Override
	public void showDescription() 
	{
		if(hasPermission( getPlayer(), this.permission ))
		{
			 getPlayer().sendMessage("Description: Show Player Stats ");
			 getPlayer().sendMessage("/game status");
		}
	}

}
