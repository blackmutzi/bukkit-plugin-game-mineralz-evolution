package com.plugins.mutzii.storage;

import java.util.HashMap;

import com.plugins.mutzii.api.MineralzDatabase;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.upgrades.UpgradePrice;

public class ConfigStore implements MineralzDatabase{

    private static ConfigStore instance = null;
    
    private HashMap<Upgrades,UpgradePrice> upPrice = null;
    
	protected ConfigStore(){}
	
	
	public static ConfigStore getInstance(){
	
		 if(instance == null){
			  instance = new ConfigStore();
			  instance.clear();
		 }
		
	  return instance;
	}
	
	
	public void registerUpgrade(Upgrades upgrade ,UpgradePrice up){
		upPrice.put(upgrade,up);
	}
	
	public UpgradePrice getUpgrade(Upgrades upgrade){
		return upPrice.get(upgrade);
	}
	
	
	public HashMap<Upgrades,UpgradePrice> getHashMap(){
		return this.upPrice;
	}
	
	@Override
	public void clear() {
		upPrice = new HashMap<Upgrades,UpgradePrice>();
	}


}
