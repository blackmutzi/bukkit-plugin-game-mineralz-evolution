package com.plugins.mutzii.api;

import org.bukkit.scoreboard.Scoreboard;

public interface Scoreboards {

	public Scoreboard getScoreboard();
	public void setScoreboard(Scoreboard board);
	
	public void createScoreboard();
	public void updateScoreboard();
	public void clearScoreBoard();
	public void scoreBoardReload();
		
}
