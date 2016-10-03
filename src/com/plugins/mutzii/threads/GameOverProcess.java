package com.plugins.mutzii.threads;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.plugins.mutzii.players.PlayerBehavior;
import com.plugins.mutzii.plugin.MineralzEvolution;
import com.plugins.mutzii.storage.GameStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class GameOverProcess extends ThreadManager implements Runnable
{
	public GameOverProcess(){}
		
	private void getCredits()
	{	
		//Credits Message 
		String e = new String(ChatColor.WHITE+"E");
		String v = new String(ChatColor.GOLD+"v");
		String o = new String(ChatColor.RED+"o");
		String l = new String(ChatColor.BLUE+"l");
		String u = new String(ChatColor.DARK_GREEN+"u");
		String t = new String(ChatColor.LIGHT_PURPLE+"t");
		String i = new String(ChatColor.DARK_AQUA+"i");
		String n = new String(ChatColor.DARK_GREEN+"n");
		String s = new String(ChatColor.AQUA+"s");
	   
		Bukkit.getServer().broadcastMessage(" ---------- Gameover ---------- ");
		Bukkit.getServer().broadcastMessage(ChatColor.GOLD+"Thanks for playing "+ChatColor.GREEN+" Mineralz "+e+v+o+l+u+t+i+o+n+s);
		Bukkit.getServer().broadcastMessage("    ");
		Bukkit.getServer().broadcastMessage(ChatColor.GOLD+" "+ChatColor.UNDERLINE+" Developed by Mutzii :) ");
	
	}
	
	private void GameOver() throws InterruptedException
	{		
			getCredits();
			
			//MineralzEvolution.removeSideBar();
			MineralzEvolution.clearPlayerInventorys();
			MineralzEvolution.removeAllBuildingsSyncSafe();
			MineralzEvolution.KillActivMonsters();
			 
			MineralzEvolution.cancelAllTasks();
			MineralzEvolution.cancelThreadTasks();
			MineralzEvolution.StoreClear();				
	}
	
	
	private boolean isAllPlayerDeath() throws InterruptedException
	{
		int max_players          = PlayerMineralzStore.getInstance().getList().size();
		int player_death_counter = 0;
		
		for( PlayerBehavior player : PlayerMineralzStore.getInstance().getList() )
		{
			if( !player.isPlayerAlive() )
				     player_death_counter++;
			
			if( player_death_counter == max_players )
					 return true;
		}
		
		return false;
	}
	
	@Override
	public  void run() 
	{
		try
		{
		
			while( GameStore.getInstance().isGameRunning() )
			{
				if( isAllPlayerDeath() )
				{
					GameStore.getInstance().setRunStatus( false );
					
					GameOver();
				}
			}	
			
		}catch( InterruptedException execption){}
	}	
}
