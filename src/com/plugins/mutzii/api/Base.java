package com.plugins.mutzii.api;

import com.plugins.mutzii.enums.MineralzType;

public interface Base {
	
	public final int BRICKS      = 45; //Generator
	public final int MOSS_STONE  = 48; //Healer
	public final int SANDSTONE   = 24; //Tower
	public final int COBBLESTONE =  4; //Base
	
	public void setMineralLevel(MineralzType type, int level);
	public int getMineralLevel(MineralzType type);
}
