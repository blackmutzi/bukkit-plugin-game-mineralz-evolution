package com.plugins.mutzii.storage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.api.MineralzGame;
import com.plugins.mutzii.api.MineralzDatabase;
import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.other.Converter;
import com.plugins.mutzii.other.MineralzField;
import com.plugins.mutzii.plugin.MineralzEvolution;
import com.plugins.mutzii.threads.EvolutionSyncThread;
import com.plugins.mutzii.threads.GameOverProcess;
import com.plugins.mutzii.threads.MonsterInteract;
import com.plugins.mutzii.threads.StageProcess;

public class GameStore implements MineralzGame,MineralzDatabase
{

	private int currentStage;
	private boolean isGameRunning =  false;
	private static GameStore instance;
	private Direction monsterdirection;
	private Location  monsterspawnlocation;
	private Location  playerspawnlocation;
	private double monsterdamage = FIRST_MONSTER_DAMAGE;
	private MineralzField areafield;
	private MineralzField towerfield;
	private MineralzField generatorfield;
	private MineralzField basefield;
	private MineralzField healerfield;
	
	private boolean isHealSound;
	
	

	protected GameStore(int stage){
		setStage(stage);
	}
	
	public static GameStore getInstance()
	{
			
		if(instance == null){
			  instance = new GameStore(START_STAGE_LEVEL);
			  instance.clear();
		}
		
	  return instance;
	}
	
	public void GameStart( Plugin plugin )
	{
	   /* Start Sync Thread */
   	   Bukkit.getScheduler().runTask( plugin , new EvolutionSyncThread( plugin ) );
   	   
   	   /* Stage Process */
   	   int start_level = 1;
 	   Bukkit.getScheduler().runTaskAsynchronously( plugin , new StageProcess( start_level ) ); 
 	   
 	  /* Game Over Process */
 	   Bukkit.getScheduler().runTaskAsynchronously( plugin , new GameOverProcess() );
 	     
 	   /* Entity / Monster Interacts */
 	   Bukkit.getScheduler().runTaskAsynchronously( plugin , new MonsterInteract( Bukkit.getWorlds().get(0) ));
 	   		    
   	   /* Show Message: Game is starting */
 	   Bukkit.broadcastMessage("Game is starting ...");
	}
	
	public void GameRestart( Plugin plugin )
	{
		MineralzEvolution.clearPlayerInventorys();
		MineralzEvolution.KillActivMonsters();
		MineralzEvolution.removeAllBuildingsSyncSafe();
		MineralzEvolution.StoreClear();
  	  
		GameStart( plugin );
	}
	
	
	public boolean hasHealSound(){
		return this.isHealSound;
	}
	
	public void setHealSound(boolean status){
		this.isHealSound = status;
	}
	
	@Override
	public  boolean isGameRunning(){
		return this.isGameRunning;
	}
	 
	@Override
	public  Location getMonsterSpawnLocation(){
		return this.monsterspawnlocation;
	}
	
	@Override
	public  Direction getMonsterDirectionPath(){
		return this.monsterdirection;
	}
	
	@Override
	public Location getPlayerSpawnLocation(){
		return this.playerspawnlocation;
	}
	
	@Override
	public MineralzField getGameArea(){
		return this.areafield;
	}
	
	@Override
	public  double getMonsterDamage(){
		return this.monsterdamage;
	}
	
	@Override
	public  int getMonsterLevel(){
		return getStage();
	}
	
	
	@Override
	public  int getStage() {
		return this.currentStage;
	}
	
	@Override
	public MineralzField getTowerField(){
		return this.towerfield;
	}
	
	@Override
	public MineralzField  getHealerField(){
		return this.healerfield;
	}
	
	@Override
	public MineralzField  getGeneratorField(){
		return this.generatorfield;
	}
	
	@Override
	public MineralzField getBaseField(){
		return this.basefield;
	}
	
	@Override
	public void setTowerField(Location start,Location ende){
		setTowerField(Converter.getMineralzField(start, ende));
	}
	
	@Override
	public void setHealerField(Location start, Location ende){
		setHealerField(Converter.getMineralzField(start, ende));
	}
	
	@Override
	public void setGeneratorField(Location start, Location ende){
		setGeneratorField(Converter.getMineralzField(start, ende));
	}
	
	@Override
	public void setBaseField(Location start, Location ende){
		setBaseField(Converter.getMineralzField(start, ende));
	}
	
	@Override
	public void setTowerField(MineralzField field){
		this.towerfield = field;
	}

	@Override
	public void setHealerField(MineralzField field){
		this.healerfield = field;
	}
	
	@Override
	public void setGeneratorField(MineralzField field){
		this.generatorfield = field;
	}
	
	@Override
	public void setBaseField(MineralzField field){
		this.basefield = field;
	}
		
	@Override
	public void setMonsterSpawnLocation(Location location){
		this.monsterspawnlocation = location;
	}
	
	@Override
	public void setMonsterDirection(Direction direction){
		this.monsterdirection = direction;
	}
	
	@Override
	public void setPlayerSpawnLocation(Location location){
		this.playerspawnlocation = location;
	}
	
	@Override
	public void setGameArea(MineralzField field){
		this.areafield = field;
	}
	
	@Override
	public void setGameArea(Location start, Location ende){
		setGameArea(Converter.getMineralzField(start, ende));
	}
	
	@Override
	public void setMonsterDamage(double monsterdamage){
		this.monsterdamage = monsterdamage;
	}
	
	@Override
	public void setRunStatus(boolean status)
	{
		this.isGameRunning = status;
	}
	
	
	@Override
	public  void setStage(int stage) {
		this.currentStage = stage;
	}

	@Override
	public  void clear() 
	{
		setStage(START_STAGE_LEVEL);
		isGameRunning = false;
		monsterdamage = FIRST_MONSTER_DAMAGE;
	}

}
