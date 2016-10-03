package com.plugins.mutzii.other;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.plugins.mutzii.api.MineralzDatabase;

public class AdminTool implements MineralzDatabase{

	private static AdminTool instance  = null;
	private Material admin_tool_item   = Material.BLAZE_ROD;
	private Player player;
	private Location firstLocation     = null;
	private Location secondLocation    = null;
	private boolean isFirstLocationSet = false;
	private boolean isSecondLocationSet = false;
	private String name;
	
	protected AdminTool(){}
	
	
	public static AdminTool getInstance(){
		if(instance == null){
			instance = new AdminTool();
			instance.clear();
		}
		
	  return instance;
	}
	
	public void setFileName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
    public Material getAdminToolItem(){
    	return this.admin_tool_item;
    }
	
    public boolean isFirstLocationSet(){
    	return this.isFirstLocationSet;
    }
    
    public boolean isSecondLocationSet(){
    	return this.isSecondLocationSet;
    }
    
    public void setLocation(Location location){
    	
    	if(!isFirstLocationSet){
    		
    		this.firstLocation = location;
    		this.isFirstLocationSet = true;
    		
    	}else{
    		
    		this.secondLocation = location;
    		isSecondLocationSet = true;
    	}
    }
    
    public Location getFirstLocation(){
    	
       if(this.firstLocation != null){
    	   return this.firstLocation;
       }
       
       return null;
    }
    
    public Location getSecondLocation(){
    	
    	if(this.secondLocation != null){
    		return this.secondLocation;
    	}
    	
      return null;
    }
    
    
	public Player getPlayerAdmin()
	{
		return this.player;
	}
	
	public void givePlayerAdminItem( Player player )
	{
		this.player = player;
		player.getInventory().setItemInMainHand( new ItemStack( this.admin_tool_item ) );
	}
	
	public void removeAdminItem( Player player )
	{
		player.getInventory().clear();
	    clear();	
	}
		
		
	@Override
	public void clear() 
	{	
		this.player = null;
		this.firstLocation  = null;
		this.secondLocation = null;
		this.isFirstLocationSet = false;
		this.isSecondLocationSet = false;
	}

}
