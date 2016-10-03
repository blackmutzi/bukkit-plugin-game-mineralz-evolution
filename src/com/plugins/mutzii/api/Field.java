package com.plugins.mutzii.api;

import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.other.MineralzField;
import com.plugins.mutzii.other.MineralzPosition;

public interface Field {
 
	public void setRadius(int powerfieldRadius);
	public int  getRadius();
	
	public MineralzField getPowerField();
	public MineralzField getStructField();
	
	public void createPowerField();
	public void extendedPowerField(Direction direction);
	public void create3DField(MineralzPosition start, MineralzPosition ende);
	
	public void setStructField(MineralzField field);
	public void setPowerField(MineralzField field);
}
