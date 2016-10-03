package com.plugins.mutzii.api;

import java.util.HashMap;
import java.util.List;

import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.other.MineralzPosition;

public interface PowerDetector {

	public HashMap<Building,MineralzPosition> buildDetector();
	List<MineralzPosition> detectDispenser();
}
