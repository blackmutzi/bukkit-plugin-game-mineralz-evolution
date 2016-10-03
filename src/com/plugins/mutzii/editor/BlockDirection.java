package com.plugins.mutzii.editor;

import org.bukkit.block.BlockFace;
import org.bukkit.material.Dispenser;
import org.bukkit.material.Ladder;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Sign;

public class BlockDirection {

	private byte newdata = 0;
	private MaterialData data   = null;
	
	
	public BlockDirection(MaterialData data){
		this.data = data;
	}
	
	public byte getData(){
		return this.newdata;
	}
	
	public boolean setBlockFace(BlockFace face){
				
		  
		if(data instanceof Dispenser){
			Dispenser d = (Dispenser) data;
			d.setFacingDirection(face);
			newdata = d.getData();
			return true;
		}
		
		
		if(data instanceof Ladder){
			Ladder ladder = (Ladder)data;
			ladder.setFacingDirection(face);
			newdata = ladder.getData();
			return true;
		}
		
		
		if(data instanceof Sign){
			Sign sign = (Sign)data;
			sign.setFacingDirection(face);
			newdata = sign.getData();
			return true;
		}
		
		
		return false;
	}
	
	
}
