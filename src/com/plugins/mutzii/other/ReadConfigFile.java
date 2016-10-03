package com.plugins.mutzii.other;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ReadConfigFile {

	private Plugin plugin;
	private String fileName;
	private FileConfiguration fileConfig = null;
	private File  currentFile;
	
	public ReadConfigFile(final Plugin instance,String fileName){
	  this.plugin    = instance;
	  this.fileName  = fileName;
	  fileConfig     = plugin.getConfig();
	  
	  createFile();
	  
	}
	
	public void createFile(){
		currentFile = new File(plugin.getDataFolder(), this.fileName);
		fileConfig = YamlConfiguration.loadConfiguration(currentFile);
	}
	
	public FileConfiguration getConfig(){
		return this.fileConfig;
	}
	
	
}
