package com.plugins.mutzii.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.buildingmanager.ColorManager;

public class VirtualScoreboard implements Scoreboards{

	private Building building;
	private Scoreboard   scoreboard;
	private Player 	     player;
	private ColorManager color;
	
	public VirtualScoreboard( Building building )
	{
		this.building = building;
		this.player   = building.getPlayer(); 
		this.color    = building.colormanager;
	}
	
	private Player getPlayer()
	{
		return this.player;
	}
	
	
	@Override
	public void clearScoreBoard(){
		Scoreboard board = getScoreboard();
		Objective  obj   = board.getObjective(getPlayer().getName());
		
		if(obj != null){
			obj.unregister();
		}
	}
	
	@Override
	public void scoreBoardReload(){
					
		Scoreboard board = getScoreboard();
		Objective  obj   = board.getObjective(getPlayer().getName());
		
		if(obj != null){
			obj.unregister();
		}
		
		createScoreboard();
		
	}
	
	@Override
	public void createScoreboard() {
	      
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard score          = manager.getNewScoreboard();
		Team      team            = score.registerNewTeam(getPlayer().getName());
		
		//Objective 
		Objective buildScore     = score.getObjective(getPlayer().getName());
		team.addPlayer(getPlayer());
		getPlayer().setScoreboard(score);
		
		if( buildScore  == null){
			
			buildScore = score.registerNewObjective(getPlayer().getName(), "dummy");
			buildScore.setDisplaySlot(DisplaySlot.SIDEBAR);
			buildScore.setDisplayName(color.getScoreBoardColor()+ building.getName() );
			
		 if( building.getLive() <= 0){}else{
			 buildScore.getScore(Bukkit.getOfflinePlayer(color.getColorGreen()+"Live:")).setScore( building.getLive());
		 }
		 
		 if( building.getLevel() <= 0){}else{
			 buildScore.getScore(Bukkit.getOfflinePlayer(color.getColorGreen()+"Level:")).setScore( building.getLevel());
		 }
		 
		 if( building.getPower() <= 0){}else{
			 buildScore.getScore(Bukkit.getOfflinePlayer(color.getColorGreen()+"Power:")).setScore( building.getPower());
		 }
		 
		}
		
		
		setScoreboard(score);
		
	}
	
	@Override
	public void updateScoreboard() {
		
		try{
		
		scoreBoardReload();
		Scoreboard board = getScoreboard();
		Objective  obj   = board.getObjective(getPlayer().getName());
		
		if(obj != null){
			
			obj.setDisplayName(color.getScoreBoardColor()+ building.getName() );
			
			 if( building.getLive() <= 0){}else{
				 obj.getScore(Bukkit.getOfflinePlayer(color.getColorGreen()+"Live:")).setScore( building.getLive());
			 }
			 
			 if( building.getLevel() <= 0){}else{
				 obj.getScore(Bukkit.getOfflinePlayer(color.getColorGreen()+"Level:")).setScore( building.getLevel());
			 }
			 
			 if( building.getPower() <= 0){}else{
				 obj.getScore(Bukkit.getOfflinePlayer(color.getColorGreen()+"Power:")).setScore( building.getPower());
			 }
			
		}
		
		getPlayer().setScoreboard(getScoreboard());
		
		}catch(IllegalStateException e){}
		
	}

	@Override
	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	@Override
	public void setScoreboard(Scoreboard board) {
		this.scoreboard = board;
	}

}
