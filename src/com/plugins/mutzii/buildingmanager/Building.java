package com.plugins.mutzii.buildingmanager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.plugins.mutzii.api.BuildDirection;
import com.plugins.mutzii.api.BuildingInterface;

import com.plugins.mutzii.api.Upgrade;
import com.plugins.mutzii.api.UpgradeManager;
import com.plugins.mutzii.api.UpgradeManagerInterface;
import com.plugins.mutzii.api.VirtualInventory;
import com.plugins.mutzii.api.VirtualScoreboard;
import com.plugins.mutzii.api.WorldInterface;
import com.plugins.mutzii.editor.Coordinaten;
import com.plugins.mutzii.editor.PlayerDirection;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.threads.ThreadManager;


public abstract class Building extends FieldManager implements BuildingInterface ,BuildDirection,Upgrade,UpgradeManagerInterface,WorldInterface{

	public final VirtualInventory   virtual_inventory; 
	public final VirtualScoreboard  virtual_scoreboard;
	
	public UpgradeManager  upgrademanager;
	public ColorManager colormanager;
	
	private String buildingName;
	private int power;
	private int live;
	private int level;
	private int maxlive;
	private int powerInterval;
	private MineralzPosition buildPosition;
	private boolean active;
	public boolean last_healing;

	private BuildType type;
	private Direction direction;
	private boolean remove;
	private World world;


	private List<Coordinaten> blocks = new ArrayList<Coordinaten>();
	
	protected final int FIRST_LEVEL   = 1;
	protected final int DEFAULT_POWER = 0;
	protected final int DEFAULT_LIVE  = 0;
	protected final int FULL_POWER    = 100;
   
	
	public HashMap<MineralzType,Integer>   upgradeLevel = new HashMap<MineralzType,Integer>();
	public HashMap<Integer,Upgrades>       upgradeSlot  = new HashMap<Integer,Upgrades>();
	
	public Building(Player player,BuildType type,MineralzPosition start, MineralzPosition ende){
		
		super(new PlayerDirection(player),start,ende);
		
		setUUID(UUID.randomUUID());
		setBuildType(type);
		setBuildName(getType().toString());
		
		colormanager = new ColorManager(getType());
		
		virtual_inventory  = new VirtualInventory();
		virtual_scoreboard = new VirtualScoreboard( this );
		
		virtual_scoreboard.createScoreboard();	
		
		setDirection(new PlayerDirection(player).getDirection());
		setPosition(start);

		setLevel(FIRST_LEVEL);
		setPower(DEFAULT_POWER);
		setLive(DEFAULT_LIVE);
		setMaxLive(DEFAULT_LIVE);
		setActiv(true);
	}
		
	
	@Override
	public void sendPower() throws NullPointerException{
	    try
	    {
	    	
	  	}catch(NullPointerException e){
	  		throw new NullPointerException();
	  	}	
	  	
	 }		
	
	
	@Override 
   /**
    * Destroy Building
    * and Remove from Store
    */public void remove()
	  {
		 Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
		 ThreadManager.runTaskRemoveBuilding(plugin,this);
	  }
	
	
	
	
	@Override
	public List<Coordinaten> getBlocks(){
		return this.blocks;
	}
	
	@Override
	public void setBlocks(List<Coordinaten> blocks){
		this.blocks = blocks;
	}
	
	@Override 
	public boolean isRemoved(){
	      if(this.remove)
	    	  	return true;
	      
	   return false;
	}
	
	
	public void setPowerInterval( int interval )
	{
		this.powerInterval = interval;
	}
	
	public int getPowerInterval()
	{
		return this.powerInterval;
	}
	
	@Override
	public void setWorld(World world){
		this.world = world;
	}
	
	@Override
	public World getWorld(){
		return this.world;
	}
	
	@Override 
	public void setRemove(boolean status){
		this.remove = status;
	}
	
	@Override 
	public int getMaxPower(){
		return FULL_POWER;
	}
	
	@Override
	public boolean isActiv(){
		return this.active;
	}
	
	@Override
	public void setMaxLive(int livepoints){
		this.maxlive = livepoints;
	}
	
	@Override
	public void setBuildName(String name){
		this.buildingName = name;
	}
	
	@Override
	public String getName()
	{
		return this.buildingName;
	}
	
	@Override
	public void setUpgradeSlot(int slot, Upgrades up){
		upgradeSlot.put(slot,up);
	}
	
	@Override
	public  int getLevel() {
		return level;
	}

	@Override
	public  int getLive() {
		return live;
	}

	@Override
	public int getPower()
	{
		return this.power;
	}
	
	@Override
	public void setPower(int power) {
		this.power = power;
	}
			
	@Override
	public void setLive(int livepoints) {
		
		if( livepoints >= this.getMaxLive() )
		{
			
			this.live = this.getMaxLive();
			
		}else
		{
			this.live = livepoints;
		}
	}
	
	@Override
	public void setPosition(MineralzPosition buildPosition) {
		this.buildPosition = buildPosition;
	}
	
	
	@Override
	public MineralzPosition getPosition() {
		return buildPosition;
	}


	@Override
	public  BuildType getType() {
		return this.type;
	}

	
	@Override
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public void setBuildType(BuildType type) {
		this.type = type;
	}
	
	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public void setUpgradeManager(UpgradeManager upgradeManager){
	   this.upgrademanager = upgradeManager;	
	}
	
	@Override
	public UpgradeManager getUpgradeManager(){
		return this.upgrademanager;
	}
	
	@Override
	public Upgrades getUpgrade(int slot) {
		return upgradeSlot.get(slot);
	}
	
	@Override
	public  Direction getDirection() {
		return direction;
	}
	
	@Override
	public int getMaxLive() {
		return maxlive;
	}
	
	@Override
	public void setActiv(boolean status) {
		this.active = status;
	}
	
	@Override
	public void triggerUpgrade(int slot) {
			getUpgradeManager().upgrade(getUpgrade(slot), getPlayer());
	}
		
}
