package com.plugins.mutzii.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.mcsg.double0negative.tabapi.TabAPI;

import com.plugins.mutzii.api.MineralzColor;
import com.plugins.mutzii.mineralz.MineralzPlayer;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class TabScoreboard implements MineralzColor {

	private String stage_clock = "00:00";
	private Player player;
	private Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
	
	public TabScoreboard(){}
	public TabScoreboard( Player player )
	{
		this.player = player;
	}
	
	public TabScoreboard( Player player , String stage_clock )
	{
		this.player      = player;
		this.stage_clock = stage_clock; 
	}
	
	public void setStageClock( String time )
	{
		stage_clock = time;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
		
	public void updateScoreBoard()
	{
	    showScoreboard();
	 	
	    updatePlayer();
	}
	
	private void showScoreboard()
	{
		//TODO: The Plugin TabAPI is outdated. 
		
		createTab(ChatColor.WHITE+" Welcome to ",0,0);
		createTab(ChatColor.GREEN+" Mineralz ",0,1);
		createTab(" Evolutions",0,2);
		
		createTab(ChatColor.GOLD+" Game Timer: ",2,0);
		createTab( stage_clock , 2 , 1 );
		
        createTab(ChatColor.WHITE+" Player Online",4,0);
	    createTab(ChatColor.RED+" Mine"+ChatColor.BLUE+"ralz ",4,1);
	    createTab(ChatColor.LIGHT_PURPLE+" Sta"+ChatColor.GREEN+"ts ",4,2);  
      
	  
	  int firstPlayerPosition = 6;
	  for(Player currentPlayer : Bukkit.getOnlinePlayers())
	  {
		  
		    //PlayerName
		    createTab(ChatColor.GREEN+" "+currentPlayer.getName(),firstPlayerPosition,0);
		  
		  	MineralzPlayer playermine	= PlayerMineralzStore.getInstance().getPlayerMineralzz(currentPlayer);
		    int bluecontent  = playermine.getMineralAmount(BLUE);
		    int redcontent   = playermine.getMineralAmount(RED);
		    int lilacontent  = playermine.getMineralAmount(LILA);
		    int greencontent = playermine.getMineralAmount(GREEN);
		  
		  createTab(ChatColor.BLUE+String.valueOf(bluecontent)+"   "+ChatColor.RED+String.valueOf(redcontent),firstPlayerPosition,1);
		  createTab(ChatColor.GREEN+String.valueOf(greencontent)+"   "+ChatColor.LIGHT_PURPLE+String.valueOf(lilacontent),firstPlayerPosition,2);
		  
		  firstPlayerPosition++;
	  }
	  
	  
	  
	  /**
	   * Informationen
	   * 
	   */ createTab(ChatColor.GOLD+"~Information~",16,0);
	   	  createTab("Break  any  Mine",17,0);
	   	  createTab("ralz  Block and",17,1);
	   	  createTab("the Game Started",17,2);
	   	  
	   	  createTab("And  build first",18,0);
	   	  createTab("Time  a   Base,",18,1);
	   	  createTab("Tower,Healer and",18,2);
	      createTab("a Generator.",19,0); 
	      
	      createTab(ChatColor.GREEN+" Have Fun and ",19,1);
	      createTab(ChatColor.GOLD+"   Good Luck!! ",19,2);
		
	}
	
	private void updatePlayer(){
		TabAPI.updatePlayer( this.player );
	}
	
	private void createTab(String name,int row,int column){
		TabAPI.setTabString(plugin, this.player , row, column , name );
	}
	
}
