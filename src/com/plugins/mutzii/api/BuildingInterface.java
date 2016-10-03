package com.plugins.mutzii.api;

import java.util.List;

import org.bukkit.entity.Player;

import com.plugins.mutzii.editor.Coordinaten;
import com.plugins.mutzii.enums.BuildType;

import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.other.MineralzPosition;


public interface BuildingInterface {

	//General 
	
	public void setBlocks(List<Coordinaten> blocks);
	public List<Coordinaten> getBlocks();
	
	public boolean isActiv();
	public void setActiv(boolean status);
	
	
	public void setMaxLive(int livepoints);
	public int getMaxLive();
	
	public void remove();

	/**
	 * is Remove Mark 
	 */public boolean isRemoved();
	   public void setRemove(boolean status);
	
	/**
	 * Building Name
	 */
	public void setBuildName(String name);
    public String getName();
	
    /**
     * Building Type (Tower,Generator,Healer,Base)
     */
	public void setBuildType(BuildType type);
	public BuildType getType();
	
	/**
	 * Masterblock Position
	 */
	public void setPosition(MineralzPosition buildPosition);
	public MineralzPosition getPosition();
	
	/**
	 * Building Level
	 * Upgrade Building + ADD_LEVEL
	 */
	public int getLevel();
	public void setLevel(int level);
	
	/**
	 * Building Live (Lebenspunkte)
	 */
	public int getLive();
	public void setLive(int livepoints);
	
	/**
	 * Building Power (Strom)
	 */
	public int getPower();
	public void setPower(int power);
	
	/**
	 * Building Owner
	 */
	public Player getPlayer();
	public void setPlayer(Player player);
	
	
	public void setUpgradeSlot(int slot, Upgrades up);
	public Upgrades getUpgrade(int slot);


	public void sendPower();	
	public int getMaxPower();
	
}
