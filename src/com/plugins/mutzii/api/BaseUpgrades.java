package com.plugins.mutzii.api;


import org.bukkit.entity.Player;

import com.plugins.mutzii.enums.MineralzType;


public interface BaseUpgrades {

	public void upgradeMinerals(Player player);
	public void upgradeMineral(MineralzType mineral, Player player);
	
	public void upgradeBase(Player player);	
}
