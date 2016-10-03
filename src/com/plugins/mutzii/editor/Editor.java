package com.plugins.mutzii.editor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.other.Converter;
import com.plugins.mutzii.other.MineralzField;
import com.plugins.mutzii.other.MineralzPosition;

public class Editor extends Converter{

	private MineralzField field;
	private MineralzField structField;
	
	private World world = null;
	
	public Editor(World world){
		super(world);
		setWorld(world);
	}
	
	public void setField(MineralzField field){
		this.field = field;
	}
	
	public MineralzField getField(){
		return this.field;
	}
	
	public MineralzField getNewStructField(){
		return this.structField;
	}
	
	public void setWorld(World world){
		this.world = world;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	
	public void createStructField(PlayerDirection playerDirection, MineralzPosition masterBlock, int diffX, int diffY , int diffZ){
		
		MineralzField newField = new MineralzField();
		
		newField.setStartX(masterBlock.getX());
		newField.setStartY(masterBlock.getY());
		newField.setStartZ(masterBlock.getZ());
		
		newField.setEndY(masterBlock.getY() + diffY);
		
		
		if(Direction.NORTH == playerDirection.getDirection()){ // North - Last East
			
			newField.setEndX(masterBlock.getX() + diffX);
			newField.setEndZ(masterBlock.getZ() - diffZ);
		}
		
		
		if(Direction.SOUTH  == playerDirection.getDirection()){ //South - Last West
			
			newField.setEndX(masterBlock.getX() - diffX);
			newField.setEndZ(masterBlock.getZ() + diffZ);
			
		}
		
		if(Direction.EAST == playerDirection.getDirection()){ //East - Last South
			
			newField.setEndX(masterBlock.getX() + diffX);
			newField.setEndZ(masterBlock.getZ() + diffZ);
			
		}
		
		if(Direction.WEST == playerDirection.getDirection()){ //West - Last NOrth
			
			newField.setEndX(masterBlock.getX() - diffX);
			newField.setEndZ(masterBlock.getZ() - diffZ);
			
		}
		
		this.structField = newField;
	}
	
	
	
	public int getDifferentX(){
		
			if(getField().getStartX() > getField().getEndX()){
				return getField().getStartX() - getField().getEndX();
			}
		
			if(getField().getEndX() > getField().getStartX()){
				return getField().getEndX() - getField().getStartX();
			}
			
	  return 0;
	}
	
	
	public int getDifferentY(){
		
		if(getField().getStartY() > getField().getEndY()){
			return getField().getStartY() - getField().getEndY();
		}
	
		if(getField().getEndY() > getField().getStartY()){
			return getField().getEndY() - getField().getStartY();
		}
		
	  return 0;
	}
	
	
	public int getDifferentZ(){
		
		if(getField().getStartZ() > getField().getEndZ()){
			return getField().getStartZ() - getField().getEndZ();
		}
	
		if(getField().getEndZ() > getField().getStartZ()){
			return getField().getEndZ() - getField().getStartZ();
		}
		
	  return 0;
	}
	
	
	/**
	 * Build File wurde gelesen und die Informationen in die fieldList geschrieben
	 * jetzt muss man nur noch die neuen Koordinaten berechnen. Das ist norwendig damit 
	 * die Methode Build ausgefuehrt werden kann.
	 * @param player
	 * @param field
	 * @param fieldList
	 * @return
	 */
	public List<BlockFieldList> newBlockFieldList(Player player,MineralzPosition field,List<BlockFieldList> fieldList){
		
		List<BlockFieldList> list = new ArrayList<BlockFieldList>();
				
				/**
				 * Bau Direction ist korrekt...
				 */ 
						 for(BlockFieldList blockList : fieldList){
							 
							     int nY = blockList.getY(blockList.getNorth()) + field.getY();
							     int sY = blockList.getY(blockList.getSouth()) + field.getY();
							     int eY = blockList.getY(blockList.getEast())  + field.getY();
							     int wY = blockList.getY(blockList.getWest())  + field.getY();
							     
							     int nZ,sZ,eZ,wZ;
							     int nX,sX,eX,wX;
							     
							     DirectionCoord north,south,east,west;
							 

								
								nX = field.getX() + blockList.getX(blockList.getNorth());
								nZ = field.getZ() - blockList.getZ(blockList.getNorth());
								
								north = new DirectionCoord(Direction.NORTH,nX,nY,nZ);
								
						

					
								sX = field.getX() - blockList.getX(blockList.getSouth());
								sZ = field.getZ() + blockList.getZ(blockList.getSouth());
								
								south = new DirectionCoord(Direction.SOUTH,sX,sY,sZ);
							
						
							
				
								eX = field.getX() + blockList.getX(blockList.getEast());
								eZ = field.getZ() + blockList.getZ(blockList.getEast());
								
								east = new DirectionCoord(Direction.EAST,eX,eY,eZ);
							
							
					
								
								wX = field.getX() - blockList.getX(blockList.getWest());
								wZ = field.getZ() - blockList.getZ(blockList.getWest());
								
								west = new DirectionCoord(Direction.WEST,wX,wY,wZ);
							
							 								 	
						   list.add(new BlockFieldList(blockList.getBlock(),north,east,south,west));
						 }
						 

	 return list;		
	}
	
	
	/**
	 * Baut das Gebaude ...
	 * @param player
	 * @param field
	 * @param buildList
	 */
	public List<Coordinaten> build(Player player,List<BlockFieldList> buildList){
		
		List<Coordinaten> list = new ArrayList<Coordinaten>();	
		PlayerDirection pDir = new PlayerDirection(player);
		Direction direction  = pDir.getDirection();
		
	
		int x = 0;
		int y = 0;
		int z = 0;
		
		for(BlockFieldList bauList : buildList){

			if(Direction.NORTH == direction){
				
				x = bauList.getX(bauList.getNorth());
				y = bauList.getY(bauList.getNorth());
				z = bauList.getZ(bauList.getNorth());
					
				Block block = createBlock(player,x,y,z,bauList.getBlock().getType(),bauList.getBlock().getData(),BlockFace.NORTH);
				list.add(new Coordinaten(block,x,y,z));
			}
			
			if(Direction.EAST == direction){
				
				x = bauList.getX(bauList.getEast());
				y = bauList.getY(bauList.getEast());
				z = bauList.getZ(bauList.getEast());
				
				Block block = createBlock(player,x,y,z,bauList.getBlock().getType(),bauList.getBlock().getData(),BlockFace.EAST);
				list.add(new Coordinaten(block,x,y,z));
								
			}
			
			if(Direction.SOUTH == direction){
				
				x = bauList.getX(bauList.getSouth());
				y = bauList.getY(bauList.getSouth());
				z = bauList.getZ(bauList.getSouth());
				

				Block block = createBlock(player,x,y,z,bauList.getBlock().getType(),bauList.getBlock().getData(),BlockFace.SOUTH);
				list.add(new Coordinaten(block,x,y,z));
								
			}
			
			if(Direction.WEST == direction){
				
				x = bauList.getX(bauList.getWest());
				y = bauList.getY(bauList.getWest());
				z = bauList.getZ(bauList.getWest());
							
				Block block = createBlock(player,x,y,z,bauList.getBlock().getType(),bauList.getBlock().getData(),BlockFace.WEST);
				list.add(new Coordinaten(block,x,y,z));
			}
			
		}
		
		
	  return list;
	}
	
	
	public Block createBlock(Player player, int x,int y,int z,int type,byte data, BlockFace face){
		
		//Create Block
		Location loc = new Location(player.getWorld(),x,y,z);
		Block block  = player.getWorld().getBlockAt(loc);
		block.setTypeId(type);
		block.setData(data);
		
		//Set Block Face ...
		
		if(face != null){
		
			MaterialData bState = block.getState().getData();
			BlockDirection bDir = new BlockDirection(bState);
			if(bDir.setBlockFace(face)){
				block.setData(bDir.getData());
			}
						
		}
		
		return block;		
	}
	
	
	
	/**
	 * Diese Methode wird vom AdminTool benoetigt. Und sie sollte nur ausgefuehrt werden
	 * wenn das Gebaude existiert, sonst sind die Files leer.
	 * @param field
	 * @return
	 */
	public List<Coordinaten> createCoordinatenList(MineralzField field) throws NullPointerException{
		
		List<Coordinaten> list = new ArrayList<Coordinaten>();
		World currentWorld     = getWorld();
		
		if(field.getStartX() < field.getEndX()){
			for(int sectionx = field.getStartX() ; sectionx <= field.getEndX(); sectionx++){
				
				if(field.getStartY() > field.getEndY()){
					for(int sectiony = field.getEndY() ; sectiony <= field.getStartY(); sectiony++){
						
						if(field.getStartZ() > field.getEndZ()){
							for(int sectionz = field.getEndZ(); sectionz <= field.getStartZ(); sectionz++){
								
								 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
								 Block block  = currentWorld.getBlockAt(loc);
								 
								 if(block != null && block.getType() != Material.AIR){
									 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
								 }
								 
								
							}
						}
						
						if(field.getStartZ() < field.getEndZ()){
							for(int sectionz = field.getStartZ(); sectionz <= field.getEndZ(); sectionz++){
								
								 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
								 Block block  = currentWorld.getBlockAt(loc);
								 
								 if(block != null && block.getType() != Material.AIR){
									 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
								 }
								 
							
							}
						}
						
						
					}
				}
				
				
				if(field.getStartY() == field.getEndY()){
					
					int sectiony = field.getStartY();
					
					if(field.getStartZ() > field.getEndZ()){
						for(int sectionz = field.getEndZ(); sectionz <= field.getStartZ(); sectionz++){
							
							 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
							 Block block  = currentWorld.getBlockAt(loc);
							 
							 if(block != null && block.getType() != Material.AIR){
								 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
							 }
						
							
						}
					}
					
					if(field.getStartZ() < field.getEndZ()){
						for(int sectionz = field.getStartZ(); sectionz <= field.getEndZ(); sectionz++){
							
							 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
							 Block block  = currentWorld.getBlockAt(loc);
							 
							 if(block != null && block.getType() != Material.AIR){
								 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
							 }
						}
					}
						
				}
				
				
				if(field.getStartY() < field.getEndY()){
					for(int sectiony = field.getStartY() ; sectiony <= field.getEndY(); sectiony++){
						
						if(field.getStartZ() > field.getEndZ()){
							for(int sectionz = field.getEndZ(); sectionz <= field.getStartZ(); sectionz++){
								
								 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
								 Block block  = currentWorld.getBlockAt(loc);
								 
								 if(block != null && block.getType() != Material.AIR){
									 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
								 }
								 
							
								
							}
						}
						
						if(field.getStartZ() < field.getEndZ()){
							for(int sectionz = field.getStartZ(); sectionz <= field.getEndZ(); sectionz++){
								
								 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
								 Block block  = currentWorld.getBlockAt(loc);
								 
								 if(block != null && block.getType() != Material.AIR){
									 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
								 }
								 
								
								
							}
						}
						
						
					}
				}
				
				
				
				
			}
		}
		
		
		if(field.getStartX() > field.getEndX()){
			for(int sectionx = field.getEndX() ; sectionx <= field.getStartX(); sectionx++){
			
				if(field.getStartY() > field.getEndY()){
					for(int sectiony = field.getEndY() ; sectiony <= field.getStartY(); sectiony++){
						
						if(field.getStartZ() > field.getEndZ()){
							for(int sectionz = field.getEndZ(); sectionz <= field.getStartZ(); sectionz++){
								
								 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
								 Block block  = currentWorld.getBlockAt(loc);
								 
								 if(block != null && block.getType() != Material.AIR){
									 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
								 }
								 
								
								
							}
						}
						
						if(field.getStartZ() < field.getEndZ()){
							for(int sectionz = field.getStartZ(); sectionz <= field.getEndZ(); sectionz++){
								
								 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
								 Block block  = currentWorld.getBlockAt(loc);
								 
								 if(block != null && block.getType() != Material.AIR){
									 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
								 }
								
								
							}
						}
						
						
					}
				}
				
				
				if(field.getStartY() == field.getEndY()){
					
					int sectiony = field.getStartY();
					
					if(field.getStartZ() > field.getEndZ()){
						for(int sectionz = field.getEndZ(); sectionz <= field.getStartZ(); sectionz++){
							
							 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
							 Block block  = currentWorld.getBlockAt(loc);
							 
							 if(block != null && block.getType() != Material.AIR){
								 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
							 }
							
						}
					}
					
					if(field.getStartZ() < field.getEndZ()){
						for(int sectionz = field.getStartZ(); sectionz <= field.getEndZ(); sectionz++){
							
							 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
							 Block block  = currentWorld.getBlockAt(loc);
							 
							 if(block != null && block.getType() != Material.AIR){
								 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
							 }
							
						}
					}
						
				}
				
				
				if(field.getStartY() < field.getEndY()){
					for(int sectiony = field.getStartY() ; sectiony <= field.getEndY(); sectiony++){
						
						if(field.getStartZ() > field.getEndZ()){
							for(int sectionz = field.getEndZ(); sectionz <= field.getStartZ(); sectionz++){
								
								 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
								 Block block  = currentWorld.getBlockAt(loc);
								 
								 if(block != null && block.getType() != Material.AIR){
									 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
								 }
								 
								
								
							}
						}
						
						if(field.getStartZ() < field.getEndZ()){
							for(int sectionz = field.getStartZ(); sectionz <= field.getEndZ(); sectionz++){
								
								 Location loc = new Location(currentWorld,sectionx,sectiony,sectionz);
								 Block block  = currentWorld.getBlockAt(loc);
								 
								 if(block != null && block.getType() != Material.AIR){
									 list.add(new Coordinaten(block,sectionx,sectiony,sectionz));
								 }
								 
								
								
							}
						}
						
						
					}
				}
				
				
			}
		}
		
		 return list;	
	}
	
	
	
	
	
}
