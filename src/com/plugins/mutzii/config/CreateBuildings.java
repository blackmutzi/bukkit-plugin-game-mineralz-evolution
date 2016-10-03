package com.plugins.mutzii.config;

import org.bukkit.entity.Player;

import com.plugins.mutzii.build.MineralzArea;
import com.plugins.mutzii.build.MineralzBase;
import com.plugins.mutzii.build.MineralzGenerator;
import com.plugins.mutzii.build.MineralzHealer;
import com.plugins.mutzii.build.MineralzTower;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.other.MineralzPosition;

public class CreateBuildings {

	protected Player player;
	protected MineralzPosition start;
	protected MineralzPosition ende;
	
	public final int BASE_LIVE   = 1000;
	public final int TOWER_LIVE  = 200;
	public final int HEALER_LIVE = 500;
	public final int GEN_LIVE    = 500;

	public final int BASE_POWER     = 100;
	public final int GEN_POWER      = 100;
	
	private final int GEN_FIELD_SIZE   = 6;
	private final int BASE_FIELD_SIZE  = 0; 
	private final int HEAL_FIELD_SIZE  = 6; 
	private final int TOW_FIELD_SIZE   = 4; 
	

	public CreateBuildings(Player player,MineralzPosition start, MineralzPosition ende){
		this.player     = player;
		this.start      = start;
		this.ende       = ende;
	}
	
	
	public Building createGameArea(){
		
		MineralzArea area = new MineralzArea(this.player,this.start,this.ende);
		area.setMaxLive(0);
		area.setLive(0);
		area.setActiv(false);
	
		return area;
	}
	
	
	public Building createBase(){
		
				MineralzBase base = new MineralzBase(this.player,this.start,this.ende);
				base.setMaxLive(BASE_LIVE);
				base.setLive(BASE_LIVE);
				base.setRadius(BASE_FIELD_SIZE);
				base.extendedPowerField(base.getDirection());
				base.virtual_scoreboard.updateScoreboard();
				
				return base;
	}
	
	public  Building  createGenerator(){
			MineralzGenerator gen = new MineralzGenerator(this.player,this.start,this.ende);
			gen.setMaxLive(GEN_LIVE);
			gen.setLive(GEN_LIVE);
			gen.setRadius(GEN_FIELD_SIZE);
			gen.extendedPowerField(gen.getDirection());
			gen.setPower(100);
			gen.virtual_scoreboard.updateScoreboard();
			return gen;
	}
	
	public  Building createHealer(){
		    MineralzHealer healer = new MineralzHealer(this.player,this.start,this.ende);
		    healer.setMaxLive(HEALER_LIVE);
		    healer.setLive(HEALER_LIVE);
		    healer.setRadius(HEAL_FIELD_SIZE);
		    healer.extendedPowerField(healer.getDirection());
		    healer.setPower(0);
		    healer.virtual_scoreboard.updateScoreboard();
		    return healer;
	}
	
	public  Building  createTower(){
			MineralzTower tower = new MineralzTower(this.player,this.start,this.ende);
			tower.setMaxLive(TOWER_LIVE);
			tower.setLive(TOWER_LIVE);
			tower.setRadius(TOW_FIELD_SIZE);
			tower.extendedPowerField(tower.getDirection());
			tower.virtual_scoreboard.updateScoreboard();
			return tower;
	}
	
	
	
}
