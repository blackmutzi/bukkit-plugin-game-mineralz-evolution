package com.plugins.mutzii.buildingmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.plugins.mutzii.api.DetectorManagerInterface;
import com.plugins.mutzii.api.PlayerInterface;
import com.plugins.mutzii.api.PowerDetector;
import com.plugins.mutzii.api.UUIDInterface;
import com.plugins.mutzii.editor.Coordinaten;
import com.plugins.mutzii.editor.Editor;
import com.plugins.mutzii.other.MineralzField;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.storage.BuildingStore;

public abstract class DetectorBehavior implements PowerDetector,DetectorManagerInterface,UUIDInterface,PlayerInterface{

	private Player player;
	private MineralzField structField;
	private MineralzField powerField;
	private DetectorBehavior instance;
	private UUID identification;
	
	public DetectorBehavior(Player player)
	{
		setPlayer( player );
		setDetectorManager(this);
	}
	
	@Override 
	public void setUUID(UUID id){
		this.identification = id;
	}
	
	@Override
	public synchronized UUID getUUID(){
		return this.identification;
	}
	
	@Override 
	public void setDetectorManager(DetectorBehavior manager){
		this.instance = manager;
	}
	
	@Override
	public  DetectorBehavior getDetectorManager(){
		return this.instance;
	}
	
	@Override
	public void setPowerField(MineralzField field){
		this.powerField = field;
	}
	
	@Override
	public void setStructField(MineralzField field){
		this.structField = field;
	}
	
	@Override
	public void setPlayer( Player player )
	{
		this.player = player;
	}
	
	@Override
	public Player getPlayer(){
		return this.player;
	}
	
	public MineralzField getStructField(){
		return this.structField;
	}
	
	public MineralzField getPowerField(){
		return this.powerField;
	}
	
		
	@Override
	public List<MineralzPosition> detectDispenser(){
		
		List<MineralzPosition> dispenserList = new ArrayList<MineralzPosition>();
		Editor editor = new Editor(getPlayer().getWorld());
		List<Coordinaten> coordDispenser = editor.createCoordinatenList(getStructField());
		
	    for(Coordinaten coord : coordDispenser){
	    
	    	if(coord.getBlock().getType() == Material.DISPENSER.getId()){
	    		
	    		MineralzPosition pos = new MineralzPosition(coord.getX(),coord.getY(),coord.getZ());
	    		dispenserList.add(pos);
	    	}
	    }		
		
	  return dispenserList;
	}
	
		
	@Override
	public  HashMap<Building, MineralzPosition> buildDetector() throws NullPointerException{
		
		HashMap<Building,MineralzPosition> map = new HashMap<Building,MineralzPosition>();
		
		try{
		
		BuildingStore store = BuildingStore.getInstance();
		
		Editor editor = new Editor(getPlayer().getWorld());
		List<Coordinaten> coordList = new ArrayList<Coordinaten>();
		coordList = editor.createCoordinatenList(getPowerField());
		
		if(coordList == null){
			Bukkit.getServer().getLogger().log(Level.INFO, "CoordList is null");
		}
		
		for(Coordinaten coord : coordList){
						
		    for(Building buildList : store.getList()){
		    	
		    	  List<Coordinaten> buildCoordList = editor.createCoordinatenList(buildList.getPowerField());
		    	  
		    	  for(Coordinaten bcl : buildCoordList){
		    		  
		    		  if(coord.getX() == bcl.getX()
		    				  && coord.getY() == bcl.getY()
		    				  && coord.getZ() == bcl.getZ()){
		    			
		    			  if(getUUID() != buildList.getUUID()){
		    				  /**
		    				   * Ist er es nicht selber dann hat er ein anderes Building gefunden.
		    				   * Deswegen die UUID.
		    				   */
		    				  MineralzPosition powerFieldCollision = new MineralzPosition(coord.getX(),coord.getY(),coord.getZ());
			    			  map.put(buildList,powerFieldCollision);
		    			  }
		    			  
		    		  }
		    	  }
		    	  
		    }
		}
		
		}catch(NullPointerException e){
			throw new NullPointerException();
		}
		
	   return map;
	}

}
