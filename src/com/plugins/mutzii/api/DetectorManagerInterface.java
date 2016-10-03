package com.plugins.mutzii.api;

import com.plugins.mutzii.buildingmanager.DetectorBehavior;
import com.plugins.mutzii.other.MineralzField;

public interface DetectorManagerInterface {
	
	public DetectorBehavior getDetectorManager();
	public void setDetectorManager(DetectorBehavior manager);
	
	public void setPowerField(MineralzField field);
	public void setStructField(MineralzField field);
	
}
