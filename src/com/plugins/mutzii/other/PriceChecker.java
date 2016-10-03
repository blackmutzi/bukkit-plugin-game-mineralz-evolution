package com.plugins.mutzii.other;

import com.plugins.mutzii.api.MineralzColor;
import com.plugins.mutzii.storage.ConfigStore;


public abstract class PriceChecker implements MineralzColor{
		
	public final int ADD_LEVEL                  = 1;
	public final int DEFAULT_LEVEL              = 1;
	public final int ADD_LIVE_POINTS_PRO_LEVEL  = 300;
	public final int ADD_POWER_POINTS_PRO_LEVEL = 100;
	
	public ConfigStore getConfigStore(){
		return ConfigStore.getInstance();
	}
	

//	public static void removeMineralz(Player player, int amount, MineralzType color){
//		
//		PlayerMineralzStore store  = PlayerMineralzStore.getInstance();
//		MineralzPlayer    pStore  = store.getPlayerMineralzz(player);
//		
//		int currentMineralContent = pStore.getMineral(color);
//		int newMineralContent     = currentMineralContent - amount;
//		
//		pStore.setMineral(color,newMineralContent);
//	}
//	
//	public static boolean hasMineralz(Player player,int price,MineralzType color){
//		
//		PlayerMineralzStore store  = PlayerMineralzStore.getInstance();
//		MineralzPlayer    pStore  = store.getPlayerMineralzz(player);
//		
//		if(pStore.getMineral(color) >= price){
//			return true;
//		}
//		
//	  return false;
//	}
//	
//	
//	public void updateTabScoreboard(Player player){
//		PlayerStore.getInstance().getPlayer(player).updateScoreBoard(null);
//	}
	
	
	
}
