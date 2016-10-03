package com.plugins.mutzii.api;
import org.bukkit.block.Dispenser;


public interface Tower {
	public final int SANDSTONE   = 24; //Tower
	
	public  boolean isDispenserEnabled();
	
	public  double getTowerDamage();
	public  void shoot(Dispenser dispenser);
	
	public  void fillDispenser(Dispenser dispenser);
    public  void clearDispenser(Dispenser dispenser);
    
    public  void disableDispenser();
	public  void enableDispenser();
	public  void setDispenserStatus(boolean status);
	
	
	
}
