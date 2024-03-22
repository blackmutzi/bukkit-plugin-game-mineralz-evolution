package com.plugins.mutzii.storage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.plugins.mutzii.api.MineralzDatabase;
import com.plugins.mutzii.mineralz.MineralzPlayer;

public class PlayerMineralzStore implements MineralzDatabase{

	private static PlayerMineralzStore instance = null;
	private List<MineralzPlayer> pMStore = new ArrayList<MineralzPlayer>();
	
	protected PlayerMineralzStore(){}
	
	public static PlayerMineralzStore getInstance()
	{
		
		if(instance == null){
			 instance = new PlayerMineralzStore();
			 instance.clear();
		}
		
	   return instance;
	}

	
	public MineralzPlayer getPlayerMineralz(String playerName){
		
		for(MineralzPlayer pm : pMStore){
			if(pm.getPlayer().getName().equalsIgnoreCase(playerName)){
				return pm;
			}
		}
		
		return null;
	}
	
	
	public  boolean isPlayerAlready(String playerName){
		
		for(MineralzPlayer pm : pMStore){
			if(pm.getPlayer().getName().equalsIgnoreCase(playerName)){
				return true;
			}
		}
		return false;
	}
	
	/*!
	 * Is the Player a Mineralz Player 
	 */
	public  boolean isPlayerAlready(Player player) throws NullPointerException{
				
		for(MineralzPlayer pm : pMStore){
			if(pm.getPlayer().equals(player)){
				return true;
			}
		}
		
	  return false;
	}
	
	
	public  MineralzPlayer getPlayerMineralzz(Player player) throws NullPointerException{
		
		for(MineralzPlayer pm : pMStore){
			
			if(pm.getPlayer().equals(player)){
				return pm;
			}
			
		}
		
	  return null;
	}
	
	
	
	
	public void register(MineralzPlayer pm){
		pMStore.add(pm);
	}
	
	public void unregister(Player player) throws NullPointerException{
		
		int counter = 0;
		for(MineralzPlayer pm : pMStore){
			
			  if(pm.getPlayer().equals(player)){
				  pMStore.remove(counter);
			  }
			
		  counter++;
		}
	}
	
	public List<MineralzPlayer> getList()
	{
		return pMStore;
	}
	
	
	@Override
	public  void clear() {
		pMStore.clear();
	}
	
	
	
	
}
