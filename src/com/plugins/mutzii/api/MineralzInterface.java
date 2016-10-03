package com.plugins.mutzii.api;

import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.other.MineralzPosition;

public interface MineralzInterface 
{

	public void setMineralzName(String mineralz_name );
	public String getMineralzName();
	
	public void setMineralzPosition(MineralzPosition position);
	public MineralzPosition getMineralPosition();
	
	public void setMineralzColor(MineralzType color);
	public MineralzType getMineralzColor();
	
	public void setMineralzAmount( int mineral_amount );
	public int  getMineralzAmount();	
}
