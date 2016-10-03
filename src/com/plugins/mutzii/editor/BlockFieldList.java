package com.plugins.mutzii.editor;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;


import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.other.MineralzPosition;


public class BlockFieldList implements ConfigurationSerializable{

	private MyBlock block;
	
	private DirectionCoord north;
	private DirectionCoord south;
	private DirectionCoord east;
	private DirectionCoord west;
	

	public BlockFieldList(MyBlock block,DirectionCoord north,DirectionCoord east,DirectionCoord south,DirectionCoord west){
		this.block = block;
		this.north = north;
		this.south = south;
		this.east  = east;
		this.west  = west;
	}
	
	
	public BlockFieldList(Block block,DirectionCoord north,DirectionCoord east,DirectionCoord south,DirectionCoord west){
		this.block = new MyBlock(block.getTypeId(),block.getData());
		this.north = north;
		this.south = south;
		this.east  = east;
		this.west  = west;
	}
	
	public ItemStack getItemStack(){
		
		ItemStack stack = new ItemStack(this.block.getType());
		stack.getData().setData(this.block.getData());
		return stack;
	}
	
	
	public MineralzPosition getPosition(DirectionCoord dCoord){
		return new MineralzPosition(dCoord.getX(),dCoord.getY(),dCoord.getZ());
	}
	
	public DirectionCoord getNorth(){
		return this.north;
	}
	
	public DirectionCoord getSouth(){
		return this.south;
	}
	
	public DirectionCoord getEast(){
		return this.east;
	}
	
	public DirectionCoord getWest(){
		return this.west;
	}
	
	
	public int getZ(DirectionCoord dCoord){
		return dCoord.getZ();
	}
	
	public int getX(DirectionCoord dCoord){
		return dCoord.getX();
	}
	
	public int getY(DirectionCoord dCoord){
		return dCoord.getY();
	}
	
	public MyBlock getBlock(){
		return this.block;		
	}
	

	@Override
	public Map<String, Object> serialize() {
		 Map<String, Object> result = new LinkedHashMap<String, Object>();
		 
		 result.put("Block", getBlock());
		 
		 result.put("nX", getX(getNorth()));
		 result.put("nY", getY(getNorth()));
		 result.put("nZ", getZ(getNorth()));
		 
		 result.put("sX", getX(getSouth()));
		 result.put("sY", getY(getSouth()));
		 result.put("sZ", getZ(getSouth()));
		 
		 result.put("eX", getX(getEast()));
		 result.put("eY", getY(getEast()));
		 result.put("eZ", getZ(getEast()));
		 
		 result.put("wX", getX(getWest()));
		 result.put("wY", getY(getWest()));
		 result.put("wZ", getZ(getWest()));
		 	 
		 return result;
	}
	

	public static BlockFieldList deserialize(Map<String, Object> args) {
		
		MyBlock currentBlock = (MyBlock) args.get("Block");
		
		int nX = (Integer) args.get("nX");
		int nY = (Integer) args.get("nY");
		int nZ = (Integer) args.get("nZ");
		
		int sX = (Integer) args.get("sX");
		int sY = (Integer) args.get("sY");
		int sZ = (Integer) args.get("sZ");
		
		int eX = (Integer) args.get("eX");
		int eY = (Integer) args.get("eY");
		int eZ = (Integer) args.get("eZ");
		
		int wX = (Integer) args.get("wX");
		int wY = (Integer) args.get("wY");
		int wZ = (Integer) args.get("wZ");
		
		DirectionCoord  north = new DirectionCoord(Direction.NORTH,nX,nY,nZ);
		DirectionCoord  south = new DirectionCoord(Direction.SOUTH,sX,sY,sZ);
		DirectionCoord  east  = new DirectionCoord(Direction.EAST,eX,eY,eZ);
		DirectionCoord  west  = new DirectionCoord(Direction.WEST,wX,wY,wZ);
		
		/**
		 * Sieht komisch aus wenn man den Constructer sich genauer anguckt.
		 * Aber es funktioniert so.. total Strange. Alles total vertauscht :D
		 *///return new BlockFieldList(currentBlock,west,south,east,north);
		 return new BlockFieldList(currentBlock,north,east,south,west);
	}
	
	
}
