package com.plugins.mutzii.upgrades;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.api.AreaUpgrades;
import com.plugins.mutzii.api.UpgradeManager;
import com.plugins.mutzii.build.MineralzArea;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.storage.GameStore;

public class UpgradeBuildArea implements AreaUpgrades,UpgradeManager{

	private MineralzArea area;
	private Plugin plugin  = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
	
	public UpgradeBuildArea( MineralzArea area){
		this.area = area;
	}
	
	
	@Override
	public void upgrade(Upgrades upgrade, Player player) {

		switch(upgrade){
				
		    case GAME_RESTART:
		    	 
		    	GameStore.getInstance().GameRestart( this.plugin );
		    	 
		    break;
		
			case DESTROY:
				 area.remove();
			break;
		 
	   default:
			break;
		
		}
	}

}
