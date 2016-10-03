package com.plugins.mutzii.commandmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.plugins.mutzii.editor.BlockFieldList;
import com.plugins.mutzii.editor.Coordinaten;
import com.plugins.mutzii.editor.DirectionCoord;
import com.plugins.mutzii.editor.Editor;
import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.exceptions.InvalidCommandException;
import com.plugins.mutzii.other.AdminTool;
import com.plugins.mutzii.other.Converter;
import com.plugins.mutzii.other.MineralzField;
import com.plugins.mutzii.other.WriteConfigFile;
import com.plugins.mutzii.storage.GameStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class CommandSave extends CommandBehavior {

	private final int SECOND_ARGUMENT = 1;
	
	private final String SPAWN     = "spawn";
	
	public CommandSave(Plugin instance) 
	{
		super(instance);
	}

	@Override
	public void run() throws InvalidCommandException {

		if(getPlayer() != null){
			
			if(getArguments().length > 1){
			
				if(hasPermission(getPlayer(), this.permission )){

					String fileName = getArguments()[SECOND_ARGUMENT];
					if(fileName.equalsIgnoreCase(SPAWN)){
						
						createSpawnConfigFile();
						
					}else{
						createFiles(getPlayer(),fileName);
					}
				
				}
			}
		}
	}
	
	
	public void createSpawnConfigFile() throws InvalidCommandException{
				
		   try {
				
			    Location location = GameStore.getInstance().getMonsterSpawnLocation();
			    String direction = GameStore.getInstance().getMonsterDirectionPath().toString();
			    
				WriteConfigFile buildFile = new WriteConfigFile( this.plugin ,"spawn");
								
				buildFile.createObject(direction,"direction",false);
				buildFile.createObject(getPlayer().getWorld().getName(),"world", false);
				buildFile.createObject(location.getBlockX(),"location_x", false);
				buildFile.createObject(location.getBlockY(),"location_y", false);
				buildFile.createObject(location.getBlockZ(),"location_z", true);
				
				
			}catch(IOException e) {
			}catch(NullPointerException e){
				throw new InvalidCommandException(ChatColor.RED+"First time set Buildings or Spawn and then usage Command save.",getPlayer());
			}	
	}
	
	

	public void createFiles(Player player,String fileName) throws InvalidCommandException{
		
		try{
		
		 Editor editor = new Editor(player.getWorld());
		 editor.setField(getField(fileName));
		 
		 List<Coordinaten> coordList = editor.createCoordinatenList(getField(fileName));
		 List<BlockFieldList> newBlockList  = new ArrayList<BlockFieldList>();
		 
		 Direction currentDirection  = PlayerMineralzStore.getInstance().getPlayerMineralzz( getPlayer() ).getLastPlayerDirection();
		 
		 int startX = editor.getField().getStartX();
		 int startY = editor.getField().getStartY();
		 int startZ = editor.getField().getStartZ();
		 
		 int diffX  = editor.getDifferentX();
		 int diffY  = editor.getDifferentY();
		 int diffZ  = editor.getDifferentZ();
		 
		 /**
		  * Create New BlockList
		  */for(Coordinaten blocklist : coordList){
			 
			  MineralzField newField = new MineralzField(startX,startY,startZ,0,0,0);
;
				if(Direction.SOUTH == currentDirection || Direction.NORTH == currentDirection){
	
						newField.setEndX(blocklist.getX());
						newField.setEndY(blocklist.getY());
						newField.setEndZ(blocklist.getZ());
  
						editor.setField(newField);
						DirectionCoord  south = new DirectionCoord(Direction.SOUTH,editor.getDifferentX(),editor.getDifferentY(),editor.getDifferentZ());
						DirectionCoord  north = new DirectionCoord(Direction.NORTH,editor.getDifferentX(),editor.getDifferentY(),editor.getDifferentZ());
    
						DirectionCoord  east  = new DirectionCoord(Direction.EAST,editor.getDifferentZ(),editor.getDifferentY(),editor.getDifferentX());
						DirectionCoord  west  = new DirectionCoord(Direction.WEST,editor.getDifferentZ(),editor.getDifferentY(),editor.getDifferentX()); 
    
						newBlockList.add(new BlockFieldList(blocklist.getBlock(),north,east,south,west));
    
				} 
				
				
				if(Direction.EAST == currentDirection || Direction.WEST == currentDirection){
					
					newField.setEndX(blocklist.getX());
					newField.setEndY(blocklist.getY());
					newField.setEndZ(blocklist.getZ());
				  
					editor.setField(newField);
				    DirectionCoord  east = new DirectionCoord(Direction.EAST,editor.getDifferentX(),editor.getDifferentY(),editor.getDifferentZ());
				    DirectionCoord  west = new DirectionCoord(Direction.WEST,editor.getDifferentX(),editor.getDifferentY(),editor.getDifferentZ());
				    
				    DirectionCoord  north  = new DirectionCoord(Direction.NORTH,editor.getDifferentZ(),editor.getDifferentY(),editor.getDifferentX());
				    DirectionCoord  south  = new DirectionCoord(Direction.SOUTH,editor.getDifferentZ(),editor.getDifferentY(),editor.getDifferentX()); 
				    
				    newBlockList.add(new BlockFieldList(blocklist.getBlock(),north,east,south,west));
				    
				} 
		 }//create new block list end
		  
		  //Write File
		    try {
				
				WriteConfigFile buildFile = new WriteConfigFile( this.plugin ,fileName);
				buildFile.createObject(newBlockList,fileName,false);
				buildFile.createObject(diffX,"endx", false);
				buildFile.createObject(diffY,"endy", false);
				buildFile.createObject(diffZ,"endz", true);
				
				
			} catch (IOException e) {}
		  
		 
		}catch(NullPointerException e){
			throw new InvalidCommandException(ChatColor.RED+"First time set Buildings or Spawn and then usage Command save.",getPlayer());
		}catch(InvalidCommandException e){
			throw new InvalidCommandException(ChatColor.RED+"What are you doing? Error: "+e.getException(),getPlayer());
		}
	}
	
	
	public MineralzField getField(String name) throws InvalidCommandException{
		
			
		if( AdminTool.getInstance().isFirstLocationSet() &&  AdminTool.getInstance().isSecondLocationSet()) 
		{
			Location first_location   = AdminTool.getInstance().getFirstLocation();
			Location second_location  = AdminTool.getInstance().getSecondLocation();
		
			return Converter.getMineralzField( first_location , second_location );
			
		}
		
		throw new InvalidCommandException("No Coordinates! (AdminTool) ");
	}

	@Override
	public void showDescription() 
	{
		if(hasPermission(getPlayer(), this.permission ))
		{
			getPlayer().sendMessage("Description: first select a field with /game select ");
			getPlayer().sendMessage(" - save all blocks and write it into a file ");
	    	getPlayer().sendMessage("/game save spawn | area | tower | generator | base | healer");
		}
		
	}
}
