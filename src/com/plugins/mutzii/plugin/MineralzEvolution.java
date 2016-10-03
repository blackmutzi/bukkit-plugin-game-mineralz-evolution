package com.plugins.mutzii.plugin;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.commandmanager.CommandDebug;
import com.plugins.mutzii.commandmanager.CommandJoinGame;
import com.plugins.mutzii.commandmanager.CommandLeaveGame;
import com.plugins.mutzii.commandmanager.CommandArea;
import com.plugins.mutzii.commandmanager.EvolutionCommandExecutor;
import com.plugins.mutzii.commandmanager.CommandRestart;
import com.plugins.mutzii.commandmanager.CommandSave;
import com.plugins.mutzii.commandmanager.CommandSend;
import com.plugins.mutzii.commandmanager.CommandSelect;
import com.plugins.mutzii.commandmanager.CommandSpawn;
import com.plugins.mutzii.commandmanager.CommandStatus;
import com.plugins.mutzii.config.ConfigManager;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.exceptions.FileNotFoundExcepetion;
import com.plugins.mutzii.listener.MineralzBuildListener;
import com.plugins.mutzii.listener.MineralzBlockListener;
import com.plugins.mutzii.listener.MineralzEntityListener;
import com.plugins.mutzii.listener.MineralzPlayerListener;
import com.plugins.mutzii.monster.MonsterStoreManager;
import com.plugins.mutzii.other.AdminTool;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.other.ReadConfigFile;
import com.plugins.mutzii.storage.BuildingStore;
import com.plugins.mutzii.storage.ConfigStore;
import com.plugins.mutzii.storage.MineralzStore;
import com.plugins.mutzii.storage.MonsterStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;
import com.plugins.mutzii.storage.GameStore;
import com.plugins.mutzii.storage.ThreadStore;
import com.plugins.mutzii.threads.ThreadManager;


public class MineralzEvolution extends JavaPlugin{

	 static 
	 {
		 ConfigurationSerialization.registerClass(com.plugins.mutzii.editor.BlockFieldList.class, "BlockFieldList");
	     ConfigurationSerialization.registerClass(com.plugins.mutzii.editor.MyBlock.class, "MyBlock");   
	 }
		 
	@Override
	public void onEnable()
	{
		this.saveConfig();
				
	 /**
	  * Register Listener	
	  */PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new MineralzPlayerListener(this), this);
		manager.registerEvents(new MineralzBlockListener(this), this);
		manager.registerEvents(new MineralzEntityListener(this),this);
		manager.registerEvents(new MineralzBuildListener(this), this);
			
	  /**
	   * Init. Storages
	   */ConfigManager config = new ConfigManager(this);	
	     config.storeInit();
	     config.priceInit();
	   
	     AdminTool.getInstance();
	    
	  /**
	   * CommandManager   
	   */
	     getCommand("Game").setExecutor( new EvolutionCommandExecutor() );
	   
	     EvolutionCommandExecutor.register("select" , "evolution.admin.select"   , new CommandSelect(this)    );
	     EvolutionCommandExecutor.register("save"   , "evolution.admin.save"     , new CommandSave( this )    );
	     EvolutionCommandExecutor.register("debug"  , "evolution.admin.debug"    , new CommandDebug(this)     );
	     EvolutionCommandExecutor.register("send"   , "evolution.player.send"    , new CommandSend(this)      );
	     EvolutionCommandExecutor.register("status" , "evolution.player.status"  , new CommandStatus(this)    );
	     EvolutionCommandExecutor.register("restart", "evolution.player.restart" , new CommandRestart(this)   );
	     EvolutionCommandExecutor.register("spawn"  , "evolution.admin.spawn"    , new CommandSpawn(this)     );
	     EvolutionCommandExecutor.register("area"   , "evolution.player.area"    , new CommandArea(this)      );
	     EvolutionCommandExecutor.register("join"   , "evolution.player.join"    , new CommandJoinGame(this)  );
	     EvolutionCommandExecutor.register("leave"  , "evolution.player.leave"   , new CommandLeaveGame(this) );
	    
	    	     
	    /**
	     * Its last Time Server crashed, remove Sidebar again.
	     *///removeSideBar();
	     
	     
	     /**
	      * Try Read Spawn Config file
	      */if(!isSpawnSet()){ //false
	    	  
	    	  try {
				
	    		  LoadSpawnConfig("spawn");
				
			  } catch (FileNotFoundExcepetion e) {
				  Bukkit.getServer().getLogger().log(Level.INFO, "Spawn Config not found.");
			  }
	      } 
	      
	      
	      /**
	       * Load Settings
	       */try{
	    	   
	    	   LoadSoundConfig("sound");
	    	   
	    	   
	        }catch(FileNotFoundExcepetion e){
	    	   Bukkit.getServer().getLogger().log(Level.INFO, "Sound Config not found");
	        }
	      
	      
	}
	
	@Override 
	public void onDisable(){
		
			//removeSideBar();
			clearPlayerInventorys();
		    
		    removeBuildingsByDisablePluginSyncSafe(this);
		    
		    cancelAllTasks();
		    cancelThreadTasks();
		    
		    StoreClear();
	}
	

   /**
    * Cancel all Tasks
    */public static void cancelAllTasks(){
		Bukkit.getScheduler().cancelAllTasks();
	  }
    
      public static void cancelThreadTasks(){
    	  ThreadStore.getInstance().closeAllThreads();
    	  ThreadStore.getInstance().clear();
      }
    
    
    
//      public static void removeSideBarFromPlayer(Player player){
//    	  
//    	  if(getPlayerMineralzStore().isPlayerAlready(player)){
//    	     MineralzPlayer pM = getPlayerMineralzStore().getPlayerMineralzz(player);
//    	     pM.clearScoreBoard();
//    	  }
//    	  
//      }
      
      

//    public static void removeSideBar(){
//		
//		for(Player player : Bukkit.getServer().getOnlinePlayers()){
//			 
//			   if(getPlayerMineralzStore().isPlayerAlready(player)){
//				   
//				      MineralzPlayer pM = getPlayerMineralzStore().getPlayerMineralzz(player);
//				      pM.clearScoreBoard();
//			   }
//		 }
//	 }
	
//	public void writeConfigFile(){
//		
//		try {
//			
//	         ConfigStore configStore = ConfigStore.getInstance();
//		     WriteConfigFile config = new WriteConfigFile(this,"config.yml");		     
//		     config.saveHashMap("Upgrades", configStore.getHashMap());
//
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	      	 
	  public static boolean isSpawnSet(){
		  return GameStore.getInstance().getMonsterSpawnLocation() != null;
	  }
	
	
	  public void LoadSoundConfig(String file)throws FileNotFoundExcepetion{
		  
		  Plugin plugin = Bukkit.getPluginManager().getPlugin("MineralzEvolution");
		  File currentFile = new File(plugin.getDataFolder(),file);
		  
		  if(currentFile.exists()){
			  
			  ReadConfigFile read = new ReadConfigFile(plugin,file);
			  boolean healsound   = (boolean) read.getConfig().getBoolean("healsound");
			  GameStore.getInstance().setHealSound(healsound);
			  
		  }else{
			   throw new FileNotFoundExcepetion("File not found:",file);
		   }
		  
	  }
	  
	  public void LoadSpawnConfig(String file) throws FileNotFoundExcepetion{
		  
		  Plugin plugin = Bukkit.getPluginManager().getPlugin("MineralzEvolution");
		  File currentFile = new File(plugin.getDataFolder(),file);
			 
		   if(currentFile.exists()){
			   ReadConfigFile read = new ReadConfigFile(plugin,file);
			   
			   String direction = (String) read.getConfig().getString("direction");
			   String world = (String) read.getConfig().getString("world");
			   
			   int loc_x = (Integer) read.getConfig().getInt("location_x");
			   int loc_y = (Integer) read.getConfig().getInt("location_y");
			   int loc_z = (Integer) read.getConfig().getInt("location_z");
			   
			   Location spawnLocation = new Location(plugin.getServer().getWorld(world),loc_x,loc_y,loc_z);
			   GameStore.getInstance().setPlayerSpawnLocation(spawnLocation);
			   GameStore.getInstance().setMonsterSpawnLocation(spawnLocation);
			   
			   if(direction.equalsIgnoreCase("EAST")){
				   GameStore.getInstance().setMonsterDirection(Direction.EAST);
			   }
			   
			   if(direction.equalsIgnoreCase("NORTH")){
				   GameStore.getInstance().setMonsterDirection(Direction.NORTH);
			   }
			   
			   if(direction.equalsIgnoreCase("SOUTH")){
				   GameStore.getInstance().setMonsterDirection(Direction.SOUTH);
			   }
			   
			   if(direction.equalsIgnoreCase("WEST")){
				   GameStore.getInstance().setMonsterDirection(Direction.WEST);
			   }
			   
		 
		   }else{
			   throw new FileNotFoundExcepetion("File not found:",file);
		   }
	  }

	public static void KillActivMonsters(){
		for(MonsterStoreManager manager : MonsterStore.getInstance().getList()){
			manager.getMonsterManager().kill();
		}
	}
	   
	public static void teleportPlayer(Player player, Location location){
		player.teleport(location);
	}
	 
	public static void teleportPlayer(Player player,int x, int y, int z){
		Location loc = new Location(player.getWorld(),x,y,z);
		player.teleport(loc);
	}
	
	public static void clearPlayerInventory(Player player){
		player.getInventory().clear();
	}
	
	public static void clearPlayerInventorys(){
		for(Player player : Bukkit.getServer().getOnlinePlayers()){
			 player.getInventory().clear();
		}
	}
	
	public static PlayerMineralzStore getPlayerMineralzStore(){
		return PlayerMineralzStore.getInstance();
	}
	
	public static BuildingStore getBuildingStore(){
		return BuildingStore.getInstance();
	}
	
	public static ConfigStore getConfigStore(){
		return ConfigStore.getInstance();
	}
	
	public static MineralzStore getMineralzStore(){
		return MineralzStore.getInstance();
	}
		
	public static GameStore getStageStore(){
		return GameStore.getInstance();
	}
	
	
   /**
    * Create Default MineralzEvolution Buildings 
    * 
    */public static void createBuilding(Player player ,MineralzPosition masterPosition,Block block){
    	Plugin plugin = Bukkit.getPluginManager().getPlugin("MineralzEvolution");
		new ConfigManager(plugin).createBuilding(player, masterPosition, block);
	  }
	

	public static void removeBuildingSyncSafe(Building manager){
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
		ThreadManager.runTaskRemoveBuilding(plugin,manager);  
	}
	
	public static void removeBuilding(Building manager){
		manager.remove();	
	}
	
	public static void removeAllBuildingsSyncSafe(){
		
		try{
			  Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
			  for(Building m : BuildingStore.getInstance().getList()){
				  	//Entfernt alle Buildings and destroys all Threads.
				  
				  /**
				   * is now Thread Safe.
				   */
				  
				  if(m.getType() != BuildType.AREA){
					  ThreadManager.runTaskRemoveBuilding(plugin,m);  
				  }
				  
				  
			  }
	   }catch(NullPointerException e){}
	}
	
	
	public static void StoreClear(){
				 
		   BuildingStore.getInstance().clear();
		   MineralzStore.getInstance().clear();
		   MonsterStore.getInstance().clear();
		   PlayerMineralzStore.getInstance().clear();
		   ThreadStore.getInstance().clear();
		   GameStore.getInstance().clear();
	  }
	
	  
	/**
	 * Remove Spezial Listener 
	 */public static void removeListener(Event event,Listener listener){
		 event.getHandlers().unregister(listener); 
	  }
	 

    /**
     * Remove MineralzEvoltions Listener
     */public static void removeListener(){
    	 
    	 Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MineralzEvolution");
    	 
    	 BlockBreakEvent.getHandlerList().unregister(new MineralzBlockListener(plugin));
    	 BlockPlaceEvent.getHandlerList().unregister(new MineralzBlockListener(plugin));
 
    	 EntityDamageByEntityEvent.getHandlerList().unregister(new MineralzEntityListener(plugin));
    	 
    	 PlayerInteractEvent.getHandlerList().unregister(new MineralzPlayerListener(plugin));
    	 PlayerDeathEvent.getHandlerList().unregister(new MineralzPlayerListener(plugin));
    	 PlayerJoinEvent.getHandlerList().unregister(new MineralzPlayerListener(plugin));
    	 PlayerRespawnEvent.getHandlerList().unregister(new MineralzPlayerListener(plugin));
    	 InventoryClickEvent.getHandlerList().unregister(new MineralzPlayerListener(plugin));
  
      }
     
     
      private void removeBuildingsByDisablePluginSyncSafe(Plugin plugin){
    	  try{
			  for(Building m : BuildingStore.getInstance().getList()){
				  	//Entfernt alle Buildings and destroys all Threads.
				  
				  /**
				   * is now Thread Safe.
				   */ThreadManager.runTaskRemoveBuilding(plugin,m);  
			  }
	   }catch(NullPointerException e){}
      }
     
     
	
}
