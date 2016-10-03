package com.plugins.mutzii.api;

import org.bukkit.Location;

import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.other.MineralzField;

public interface MineralzGame {

	public final int FIRST_MONSTER_DAMAGE = 2;
	public final int START_STAGE_LEVEL    = 1;
	
	public boolean isGameRunning();
	
	public  int getStage();
	public  int getMonsterLevel();
	
	public  Location getMonsterSpawnLocation();
	public  Location getPlayerSpawnLocation();
	
	public  Direction getMonsterDirectionPath();
	
	public  double getMonsterDamage();
	
	public MineralzField  getGameArea();
	public MineralzField  getTowerField();
	public MineralzField  getHealerField();
	public MineralzField  getGeneratorField();
	public MineralzField  getBaseField();
	
	public void setRunStatus(boolean status);
	
	public void setStage(int stage);
	
	public void setMonsterSpawnLocation(Location location);
	public void setPlayerSpawnLocation(Location location);
	
	public void setMonsterDirection(Direction direction);

	public void setMonsterDamage(double monsterdamage);
	
	public void setGameArea(Location start, Location ende);
	public void setTowerField(Location start,Location ende);
	public void setHealerField(Location start, Location ende);
	public void setGeneratorField(Location start, Location ende);
	public void setBaseField(Location start, Location ende);
	
	public void setGameArea(MineralzField field);
	public void setTowerField(MineralzField field);
	public void setHealerField(MineralzField field);
	public void setGeneratorField(MineralzField field);
	public void setBaseField(MineralzField field);
	
}
