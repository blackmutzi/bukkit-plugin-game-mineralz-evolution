package com.plugins.mutzii.threads;

import org.bukkit.block.Dispenser;

import com.plugins.mutzii.build.MineralzTower;

/**
 * TowerShoot
 * Sync: Bukkit RunTaskLater (yes)
 * @author Mutzii
 */
public class TowerShoot implements Runnable{
	
	private MineralzTower tower;
	private Dispenser dispenser;
	
	public TowerShoot(MineralzTower tower, Dispenser dispenser){
		this.tower = tower;
		this.dispenser = dispenser;
	}

	public MineralzTower getTower(){
		return this.tower;
	}
	

	@Override
	public void run() {
	
		getTower().fillDispenser(this.dispenser);
		getTower().shoot(this.dispenser);
	
	}

}
