package com.plugins.mutzii.buildingmanager;

import org.bukkit.ChatColor;

import com.plugins.mutzii.enums.BuildType;

public class ColorManager {

	protected BuildType type;
	
	public ColorManager(BuildType type){
		this.type = type;
	}
	
	public BuildType getType(){
		return this.type;
	}
	
	public ChatColor getColorRed(){
		return ChatColor.RED;
	}
	
	public ChatColor getColorGreen(){
		return ChatColor.GREEN;
	}
	
	public ChatColor getScoreBoardColor(){
		
		 switch(getType()){
		 
		 case BASE:
			 	return ChatColor.WHITE;
		 case GENERATOR:
			    return ChatColor.RED;
		 case TOWER:
			    return ChatColor.BLUE;
		 case HEALER:
			    return ChatColor.LIGHT_PURPLE;
		default:
			break;
			 		 
		 }
	
	  return ChatColor.WHITE;
	}
	
}
