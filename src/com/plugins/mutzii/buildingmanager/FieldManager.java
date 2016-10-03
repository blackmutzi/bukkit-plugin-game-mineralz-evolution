package com.plugins.mutzii.buildingmanager;


import org.bukkit.World;

import com.plugins.mutzii.api.Field;
import com.plugins.mutzii.editor.Editor;
import com.plugins.mutzii.editor.PlayerDirection;
import com.plugins.mutzii.enums.Direction;

import com.plugins.mutzii.other.MineralzField;
import com.plugins.mutzii.other.MineralzPosition;

public abstract class FieldManager extends DetectorBehavior implements Field{

	private int fieldRadius = 0;
	private MineralzField powerField;
	private MineralzField structField;
	private PlayerDirection playerDirection;
	private World world;
	
	
	public FieldManager(PlayerDirection playerDirection , MineralzPosition start, MineralzPosition ende){
		
		super(playerDirection.getPlayer());
		this.playerDirection = playerDirection;
		
		//Create Fields
		
		setWorld(playerDirection.getPlayer().getWorld());
		
		create3DField(start,ende);
		createPowerField();
		
	}
	
	
	@Override
	public int getRadius(){
		return this.fieldRadius;
	}
	
	@Override
	public MineralzField getPowerField(){
		return powerField;
	}
	
	@Override
	public MineralzField getStructField(){
		return structField;
	}
	

	public void setWorld(World world){
		this.world = world;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	@Override
	public void setRadius(int powerfieldRadius){
	     fieldRadius = powerfieldRadius;
	}
	
	@Override
	public void setStructField(MineralzField field){
		this.structField = field;
		super.setStructField(this.structField);
	}
	
	@Override
	public void setPowerField(MineralzField field){
		this.powerField = field;
		super.setPowerField(this.powerField);
	}
	

	@Override
	public void createPowerField(){

		int sx = getStructField().getStartX();
		int sy = getStructField().getStartY();
		int sz = getStructField().getStartZ();
		
		int ex = getStructField().getEndX();
		int ey = getStructField().getEndY();
		int ez = getStructField().getEndZ();
		
		MineralzField powerFieldGround = new MineralzField();
		powerFieldGround.setStartX(sx);
		powerFieldGround.setStartY(sy);
		powerFieldGround.setStartZ(sz);
		
		powerFieldGround.setEndX(ex);
		powerFieldGround.setEndY(ey);
		powerFieldGround.setEndZ(ez);
		
		
		/**
		 * Power Field Hoehe..
		 */int powerachse = sy-1;
		
		/**
		 * Create new 2D Field
		 * 
		 */powerFieldGround.setStartY(powerachse);
		   powerFieldGround.setEndY(powerachse);	
	
		   
	 setPowerField(powerFieldGround);
	}
	

	@Override
	public void extendedPowerField(Direction direction){
		
		if(direction == Direction.SOUTH){
			
			powerField.setEndZ(powerField.getEndZ() + fieldRadius);
			powerField.setEndX(powerField.getEndX() - fieldRadius);
			
			powerField.setStartZ(powerField.getStartZ() - fieldRadius);
			powerField.setStartX(powerField.getStartX() + fieldRadius);
		
		}
		
		
		if(direction == Direction.NORTH){
			
			powerField.setEndZ(powerField.getEndZ() - fieldRadius);
			powerField.setEndX(powerField.getEndX() + fieldRadius);
			
			powerField.setStartZ(powerField.getStartZ() + fieldRadius);
			powerField.setStartX(powerField.getStartX() - fieldRadius);
		
		}
		
		
		if(direction == Direction.EAST){
			
			powerField.setEndZ(powerField.getEndZ() + fieldRadius);
			powerField.setEndX(powerField.getEndX() + fieldRadius);
			
			powerField.setStartZ(powerField.getStartZ() - fieldRadius);
			powerField.setStartX(powerField.getStartX() - fieldRadius);
			
		}
		
		if(direction == Direction.WEST){
			
			powerField.setEndZ(powerField.getEndZ() - fieldRadius);
			powerField.setEndX(powerField.getEndX() - fieldRadius);
			
			powerField.setStartZ(powerField.getStartZ() + fieldRadius);
			powerField.setStartX(powerField.getStartX() + fieldRadius);
			
		}
		
		setPowerField(powerField);
	}
	
	@Override
	public void create3DField(MineralzPosition start, MineralzPosition ende){
	 	    
		     Editor editor = new Editor(getWorld());
		     editor.createStructField(this.playerDirection,start, ende.getX(), ende.getY(), ende.getZ());
			 setStructField(editor.getNewStructField());
	}
	
	
	
	
	
}
