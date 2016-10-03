package com.plugins.mutzii.other;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;


public class WriteConfigFile {
	
	  private Plugin plugin;
	  private String fileName;
	  private FileConfiguration fileConfig = null;
	  private File  currentFile;
	
	  public WriteConfigFile(final Plugin instance,String fileName){
		  this.plugin   = instance;
		  this.fileName = fileName;
		  fileConfig    = plugin.getConfig();
		  
		  createFile();
	  }
	  
	  private void createFile(){
		  currentFile = new File(plugin.getDataFolder(), this.fileName);
		  fileConfig = YamlConfiguration.loadConfiguration(currentFile);
	  }
	  
	  
	  public void createObject(Object object,String Name,boolean autosave) throws IOException{
		  fileConfig.set(Name, object);
		   
		  if(autosave){
			  save();
		  }
	  }
	  
	  public void saveHashMap(String path,HashMap<?,?> map) throws IOException{
		  
		  fileConfig.createSection(path,map);
		  save();
	  }
	  
	  
	  public void save() throws IOException{

		fileConfig.save(this.currentFile);

	  }
	  
	  
}
