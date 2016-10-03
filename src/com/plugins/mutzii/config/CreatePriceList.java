package com.plugins.mutzii.config;

import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.storage.ConfigStore;
import com.plugins.mutzii.upgrades.UpgradePrice;

public class CreatePriceList {

	public CreatePriceList(){}
	
	public void create(){
		
		   ConfigStore configStore = ConfigStore.getInstance();
			
		   /**
		    * Building Upgrades ...
		    */
		    
		    //Upgrade Base
		    UpgradePrice base = new UpgradePrice();
		    base.put(MineralzType.MINERAL_BLUE, "30 + %LEVEL% * 5");
		    base.put(MineralzType.MINERAL_GREEN,"30 + %LEVEL% * 4");
		    base.put(MineralzType.MINERAL_LILA, "30 + %LEVEL% * 4");
		    base.put(MineralzType.MINERAL_RED,  "30 + %LEVEL% * 4");
			
			configStore.registerUpgrade(Upgrades.UPGRADE_BASE,base);
			
			//Upgrade Tower
			UpgradePrice tower = new UpgradePrice();
			tower.put(MineralzType.MINERAL_BLUE, "30 + %LEVEL% * 5");
			tower.put(MineralzType.MINERAL_GREEN,"30 + %LEVEL% * 4");
			tower.put(MineralzType.MINERAL_LILA, "30 + %LEVEL% * 4");
			tower.put(MineralzType.MINERAL_RED,  "30 + %LEVEL% * 4");
			
			configStore.registerUpgrade(Upgrades.UPGRADE_TOWER,tower);
			
			
			//Upgrade Healer
			UpgradePrice healer = new UpgradePrice();
			healer.put(MineralzType.MINERAL_BLUE, "30 + %LEVEL% * 5");
			healer.put(MineralzType.MINERAL_GREEN,"30 + %LEVEL% * 4");
			healer.put(MineralzType.MINERAL_LILA, "30 + %LEVEL% * 4");
			healer.put(MineralzType.MINERAL_RED,  "30 + %LEVEL% * 4");
			
			configStore.registerUpgrade(Upgrades.UPGRADE_HEALER,healer);
			
			//Upgrade Generator
			UpgradePrice gen = new UpgradePrice();
			gen.put(MineralzType.MINERAL_BLUE, "30 + %LEVEL% * 5 * %LEVEL%");
			gen.put(MineralzType.MINERAL_GREEN,"30 + %LEVEL% * 4 * %LEVEL%");
			gen.put(MineralzType.MINERAL_LILA, "30 + %LEVEL% * 4 * %LEVEL%");
			gen.put(MineralzType.MINERAL_RED,  "30 + %LEVEL% * 4 * %LEVEL%");
			
			configStore.registerUpgrade(Upgrades.UPGRADE_GENERATOR,gen);
			
			
			
			/**
			 * Build Price 
			 */
			
			//Tower
			UpgradePrice buildtower = new UpgradePrice();
			buildtower.put(MineralzType.MINERAL_BLUE, "20");
			buildtower.put(MineralzType.MINERAL_GREEN,"20");
			buildtower.put(MineralzType.MINERAL_LILA, "20");
			buildtower.put(MineralzType.MINERAL_RED,  "20");
			
			configStore.registerUpgrade(Upgrades.BUILD_TOWER,buildtower);
			
			//Healer
			UpgradePrice buildhealer = new UpgradePrice();
			buildhealer.put(MineralzType.MINERAL_BLUE, "20");
			buildhealer.put(MineralzType.MINERAL_GREEN,"20");
			buildhealer.put(MineralzType.MINERAL_LILA, "20");
			buildhealer.put(MineralzType.MINERAL_RED,  "20");
			
			configStore.registerUpgrade(Upgrades.BUILD_HEALER,buildhealer);
			
			
			//Generator
			UpgradePrice buildgen = new UpgradePrice();
			buildgen.put(MineralzType.MINERAL_BLUE, "20");
			buildgen.put(MineralzType.MINERAL_GREEN,"20");
			buildgen.put(MineralzType.MINERAL_LILA, "20");
			buildgen.put(MineralzType.MINERAL_RED,  "20");
			
			configStore.registerUpgrade(Upgrades.BUILD_GENERATOR,buildgen);
			
			
			
			/**
			 * Mineralz Upgrades..
			 */
			
			UpgradePrice green = new UpgradePrice();
			green.put(MineralzType.MINERAL_GREEN,"30 + %MINELEVEL% * 5");
			configStore.registerUpgrade(Upgrades.UPGRADE_MINERAL_GREEN,green);
			
			UpgradePrice blue = new UpgradePrice();
			blue.put(MineralzType.MINERAL_GREEN,"30 + %MINELEVEL% * 2");
			configStore.registerUpgrade(Upgrades.UPGRADE_MINERAL_BLUE,blue);
			
			UpgradePrice red = new UpgradePrice();
			red.put(MineralzType.MINERAL_GREEN,"30 + %MINELEVEL% * 2");
			configStore.registerUpgrade(Upgrades.UPGRADE_MINERAL_RED,red);
			
			UpgradePrice lila = new UpgradePrice();
			lila.put(MineralzType.MINERAL_GREEN,"30 + %MINELEVEL% * 2");
			configStore.registerUpgrade(Upgrades.UPGRADE_MINERAL_LILA,lila);
		
		
	}
	
}
