package com.plugins.mutzii.config;

import org.bukkit.block.Block;

import com.plugins.mutzii.enums.MineralzType;

public class MineralzConfig {

	public final int MATERIAL_WOOL  = 35;
	public final byte WOOL_BLUE     = 0x3;
	public final byte WOOL_RED      = 0xE;
	public final byte WOOL_MAGENTA  = 0x2; //Lila
	public final byte WOOL_GREEN    = 0xD;
	
	
	public MineralzConfig(){}
	
	
	public MineralzType identification(Block block){
		
		if(block.getTypeId() == MATERIAL_WOOL){
			
				switch(block.getData()){
				
				case WOOL_BLUE:
					 return MineralzType.MINERAL_BLUE;

				case WOOL_RED:
					  return MineralzType.MINERAL_RED;
				
				case WOOL_MAGENTA:
					  return MineralzType.MINERAL_LILA;
							
				case WOOL_GREEN:
					  return MineralzType.MINERAL_GREEN;
				}
			
		}
			
		return null;
	}
	
	
	
	
}
