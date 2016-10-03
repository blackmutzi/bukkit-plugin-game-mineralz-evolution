package com.plugins.mutzii.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.api.Base;
import com.plugins.mutzii.build.MineralzBase;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.editor.BlockFieldList;
import com.plugins.mutzii.editor.Coordinaten;
import com.plugins.mutzii.editor.Editor;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.other.Converter;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.other.ReadConfigFile;
import com.plugins.mutzii.players.PlayerBehavior;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.storage.ConfigStore;
import com.plugins.mutzii.storage.MineralzStore;
import com.plugins.mutzii.storage.MonsterStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;
import com.plugins.mutzii.storage.GameStore;
import com.plugins.mutzii.upgrades.UpgradePrice;

public class ConfigManager {
	
    private Plugin plugin;
    
	public ConfigManager(final Plugin instance){
		this.plugin = instance;
	}
	

	public void storeInit(){
		
		ConfigStore.getInstance();
		MonsterStore.getInstance();
		BuildingStore.getInstance();
		MineralzStore.getInstance();
		PlayerMineralzStore.getInstance();
		GameStore.getInstance();
		
		plugin  = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
	}
		
	public void priceInit(){
		new CreatePriceList().create();
	}
	
	
	public void createBuilding(Player player ,MineralzPosition masterPosition,Block block){
		
		Identification i = new Identification();
		Editor    editor = new Editor(player.getWorld());
		
		switch( i.getBuildTypeByBlock( block ) ) 
		{
			
			case AREA:
				//Load File..
				FileInput areafile = readFile("area");
				//Create End Postion
				MineralzPosition areaende = new MineralzPosition(areafile.getX(),areafile.getY(),areafile.getZ());
				//Register
				Building area = new CreateBuildings(player,masterPosition,areaende).createGameArea();
				//Build
				/**
				 * Generate new BlockFieldList
				 */List<BlockFieldList> buildarea = editor.newBlockFieldList(player, masterPosition,areafile.getFieldList());
				   		   
				/**
				 * Building Area
				 */List<Coordinaten> areaList = editor.build(player,buildarea);
				 
				   areaList.add(new Coordinaten(Converter.getLocation(player.getWorld(), masterPosition).getBlock(),masterPosition.getX(),masterPosition.getY(),masterPosition.getZ()));
				   area.setBlocks(areaList);
			
			break;
			case BASE:
				
				//Check Player
				PlayerBehavior player_behavior  = (PlayerBehavior) PlayerMineralzStore.getInstance().getPlayerMineralzz( player );
				
				if(! player_behavior.hasMineralzBase() ){ //if false then Build
				
					//Load File..
					FileInput basefile = readFile("base");
					//Create End Postion
					MineralzPosition baseende = new MineralzPosition(basefile.getX(),basefile.getY(),basefile.getZ());
					//Register
					Building base = new CreateBuildings(player,masterPosition,baseende).createBase();
					//Build
					/**
					 * Generate new BlockFieldList
					 */List<BlockFieldList> basebuild = editor.newBlockFieldList(player, masterPosition,basefile.getFieldList());   
					 /**
					  * Building Base
					  */List<Coordinaten> baseList = editor.build(player,basebuild);
					  	baseList.add(new Coordinaten(Converter.getLocation(player.getWorld(), masterPosition).getBlock(),masterPosition.getX(),masterPosition.getY(),masterPosition.getZ()));
					  	base.setBlocks(baseList);
				 
				   player_behavior.setMineralzBase( (MineralzBase) base ); 
				  
				}else{
					
					/**
					 * Wurde versucht eine second Base zu Bauen werd der getzte Block entfernt.
					 */Location location = new Location(player.getWorld(),masterPosition.getX(),masterPosition.getY(),masterPosition.getZ());
					   Block masterBlock = location.getBlock();
					   masterBlock.setType(Material.AIR);
				}	  
				
				
		    break;
		    
			case GENERATOR:
				//Load File..
				FileInput genfile = readFile("generator");
				//Create End Postion
				MineralzPosition genende = new MineralzPosition(genfile.getX(),genfile.getY(),genfile.getZ());
				//Register
				Building generator = new CreateBuildings(player,masterPosition,genende).createGenerator();
				//Build
				/**
				 * Generate new BlockFieldList
				 */List<BlockFieldList> buildgen = editor.newBlockFieldList(player, masterPosition,genfile.getFieldList());
				   		   
				/**
				 * Building Generator..
				 */List<Coordinaten> genList = editor.build(player,buildgen);
				   genList.add(new Coordinaten(Converter.getLocation(player.getWorld(), masterPosition).getBlock(),masterPosition.getX(),masterPosition.getY(),masterPosition.getZ()));
				   generator.setBlocks(genList);
				  
			break;
			
			case HEALER:
				//Load File..
				FileInput healfile = readFile("healer");
				//Create End Postion
				MineralzPosition healerende = new MineralzPosition(healfile.getX(),healfile.getY(),healfile.getZ());
				//Register
				Building healer = new CreateBuildings(player,masterPosition,healerende).createHealer();
				//Build
				/**
				 * Generate new BlockFieldList
				 */List<BlockFieldList> buildheal = editor.newBlockFieldList(player, masterPosition,healfile.getFieldList());
				   		   
				/**
				 * Building Healer
				 */List<Coordinaten> healList = editor.build(player,buildheal);
				   healList.add(new Coordinaten(Converter.getLocation(player.getWorld(), masterPosition).getBlock(),masterPosition.getX(),masterPosition.getY(),masterPosition.getZ()));
				   healer.setBlocks(healList);
				
			break;
			
			case TOWER:
				//Load File..
				FileInput towerfile = readFile("tower");
				//Create End Postion
				MineralzPosition towerende = new MineralzPosition(towerfile.getX(),towerfile.getY(),towerfile.getZ());
				//Register
				Building tower = new CreateBuildings(player,masterPosition,towerende).createTower();
				//Build
				/**
				 * Generate new BlockFieldList
				 */List<BlockFieldList> buildtower = editor.newBlockFieldList(player, masterPosition,towerfile.getFieldList());
				   		   
				/**
				 * Building Tower
				 */List<Coordinaten> towerList = editor.build(player,buildtower);
				   towerList.add(new Coordinaten(Converter.getLocation(player.getWorld(), masterPosition).getBlock(),masterPosition.getX(),masterPosition.getY(),masterPosition.getZ()));
				   tower.setBlocks(towerList);
			break;
		default:
			break;
		}
		
		
	}
	
	
	public FileInput readFile(String name){
		
		int diffX = 0;//Breite
		int diffY = 0;//Hoehe
		int diffZ = 0;//Laenger
		
		List<BlockFieldList> fieldList = new ArrayList<BlockFieldList>();
		
		/**
		 * Read File 
		 */File currentFile = new File(this.plugin.getDataFolder(),name);
		 
		   if(currentFile.exists()){
			   
			   ReadConfigFile file = new ReadConfigFile(this.plugin,name);
			   
			   diffX = (Integer) file.getConfig().getInt("endx");
			   diffY = (Integer) file.getConfig().getInt("endy");
			   diffZ = (Integer) file.getConfig().getInt("endz");
			   
			   List<?> list = file.getConfig().getList(name);
			   
			  	 for(Object object : list){
			  	
			  		 	if(object instanceof BlockFieldList){
			  		 		
			  		 		Iterator<?> itr = list.iterator();
			  		 		while(itr.hasNext()) {
			  		 			fieldList.add((BlockFieldList) itr.next()); 
						    }
			  		 	}
			  	
			  	} 
		   }
		
		
		FileInput fileInput = new FileInput(fieldList,diffX,diffY,diffZ);
		return fileInput;
	}
	
	
	public String createGreenString(int greenPrice){
	
		String newString  = ChatColor.GREEN+String.valueOf(greenPrice);
		return newString;
	
	}
	
	public String createPriceString(int bluePrice, int greenPrice, int redPrice, int lilaPrice){
				
		String newString  = ChatColor.BLUE+String.valueOf(bluePrice)+" "+ChatColor.RED+String.valueOf(redPrice)+" "+ChatColor.GREEN+String.valueOf(greenPrice)+" "+ChatColor.LIGHT_PURPLE+String.valueOf(lilaPrice); 
		return newString;
	}
	
	/**
	 * Create Item Meta Informationen..
	 * @param build
	 * @param slot
	 * @return
	 */
	public ArrayList<String> getArrayList(Building build,int slot){
		
		  Upgrades currentUpgrade = build.getUpgrade(slot);
		  ArrayList<String>  list = new ArrayList<String>();
		  UpgradePrice price = ConfigStore.getInstance().getUpgrade(currentUpgrade);
		  
		  int bluePrice;
  		  int greenPrice;
  		  int redPrice;
  		  int lilaPrice;
		  
  		  int nextbluePrice;
  		  int nextgreenPrice;
  		  int nextredPrice;
  		  int nextlilaPrice;
  		  
		  
  		  if(currentUpgrade == Upgrades.BUILD_GENERATOR 
  				  || currentUpgrade == Upgrades.BUILD_HEALER 
  				  || currentUpgrade == Upgrades.BUILD_TOWER){
  			  
  			bluePrice      = price.get(MineralzType.MINERAL_BLUE, build.getLevel());
    		greenPrice     = price.get(MineralzType.MINERAL_GREEN, build.getLevel());
    		redPrice       = price.get(MineralzType.MINERAL_RED, build.getLevel());
    		lilaPrice      = price.get(MineralzType.MINERAL_LILA, build.getLevel());
    		
    		list.add(ChatColor.YELLOW+"Price:");
    		list.add(createPriceString(bluePrice,greenPrice,redPrice,lilaPrice));
  			  
  		  }
  		  
  		  
  		  if(currentUpgrade == Upgrades.UPGRADE_BASE 
  				  || currentUpgrade == Upgrades.UPGRADE_HEALER 
  				  || currentUpgrade == Upgrades.UPGRADE_GENERATOR 
  				  || currentUpgrade == Upgrades.UPGRADE_TOWER){
  			  
  			  
  			bluePrice      = price.get(MineralzType.MINERAL_BLUE, build.getLevel());
    		greenPrice     = price.get(MineralzType.MINERAL_GREEN, build.getLevel());
    		redPrice       = price.get(MineralzType.MINERAL_RED, build.getLevel());
    		lilaPrice      = price.get(MineralzType.MINERAL_LILA, build.getLevel());
    		
    		
    		nextbluePrice      = price.get(MineralzType.MINERAL_BLUE, build.getLevel()+1);
    		nextgreenPrice     = price.get(MineralzType.MINERAL_GREEN, build.getLevel()+1);
    		nextredPrice       = price.get(MineralzType.MINERAL_RED, build.getLevel()+1);
    		nextlilaPrice      = price.get(MineralzType.MINERAL_LILA, build.getLevel()+1);
    		
    	    list.add(ChatColor.GOLD+"Current Level: "+ChatColor.WHITE+String.valueOf(build.getLevel()));
    		list.add(ChatColor.YELLOW+"Price:");
    		list.add(createPriceString(bluePrice,greenPrice,redPrice,lilaPrice));
	    	
    		list.add(ChatColor.YELLOW+"Next Level Price:");
    		list.add(createPriceString(nextbluePrice,nextgreenPrice,nextredPrice ,nextlilaPrice ));
  			  
  			   
  		  }
  		  
  		  
  		  if(currentUpgrade == Upgrades.UPGRADE_MINERAL_BLUE 
  				  || currentUpgrade == Upgrades.UPGRADE_MINERAL_GREEN 
  				  || currentUpgrade == Upgrades.UPGRADE_MINERAL_LILA 
  				  || currentUpgrade == Upgrades.UPGRADE_MINERAL_RED
  				  && build.getType() == BuildType.BASE){
  			  
  			  Base base = (Base) build;
  			  
  			  greenPrice     = 0;
  			  nextgreenPrice = 0;
  			  
  			  if(currentUpgrade == Upgrades.UPGRADE_MINERAL_BLUE){
  			      greenPrice         = price.getMine(MineralzType.MINERAL_GREEN, base.getMineralLevel(MineralzType.MINERAL_BLUE));
  			      nextgreenPrice     = price.getMine(MineralzType.MINERAL_GREEN, base.getMineralLevel(MineralzType.MINERAL_BLUE)+1);
  			      
  			      list.add(ChatColor.GOLD+"Current Level: "+ChatColor.WHITE+String.valueOf(base.getMineralLevel(MineralzType.MINERAL_BLUE)));
  			      list.add(ChatColor.YELLOW+"Price:");
  			      
  			  }
  			  
  			  if(currentUpgrade == Upgrades.UPGRADE_MINERAL_RED){
 			      greenPrice         = price.getMine(MineralzType.MINERAL_GREEN, base.getMineralLevel(MineralzType.MINERAL_RED));
 			      nextgreenPrice     = price.getMine(MineralzType.MINERAL_GREEN, base.getMineralLevel(MineralzType.MINERAL_RED)+1);
 			      
 			      list.add(ChatColor.GOLD+"Current Level: "+ChatColor.WHITE+String.valueOf(base.getMineralLevel(MineralzType.MINERAL_RED)));
 			      list.add(ChatColor.YELLOW+"Price:");
 			      
 			  }
  			  
  			  if(currentUpgrade == Upgrades.UPGRADE_MINERAL_GREEN){
			      greenPrice         = price.getMine(MineralzType.MINERAL_GREEN, base.getMineralLevel(MineralzType.MINERAL_GREEN));
			      nextgreenPrice     = price.getMine(MineralzType.MINERAL_GREEN, base.getMineralLevel(MineralzType.MINERAL_GREEN)+1);
			      
			      list.add(ChatColor.GOLD+"Current Level: "+ChatColor.WHITE+String.valueOf(base.getMineralLevel(MineralzType.MINERAL_GREEN)));
			      list.add(ChatColor.YELLOW+"Price:");
			  }
  			  
  			  if(currentUpgrade == Upgrades.UPGRADE_MINERAL_LILA){
			      greenPrice         = price.getMine(MineralzType.MINERAL_GREEN, base.getMineralLevel(MineralzType.MINERAL_LILA));
			      nextgreenPrice     = price.getMine(MineralzType.MINERAL_GREEN, base.getMineralLevel(MineralzType.MINERAL_LILA)+1);
			      
			      list.add(ChatColor.GOLD+"Current Level: "+ChatColor.WHITE+String.valueOf(base.getMineralLevel(MineralzType.MINERAL_LILA)));
			      list.add(ChatColor.YELLOW+"Price:");
			  }
  			  
  			    
  	    		 list.add(createGreenString(greenPrice));
  	    		 
  	    		 list.add(ChatColor.YELLOW+"Next Level Price:");
  	    		 list.add(createGreenString(nextgreenPrice));
  			  
  		  }
  		  
  		  

	  return list;
	}
	
		
	
	
}
