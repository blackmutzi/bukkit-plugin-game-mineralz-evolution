package com.plugins.mutzii.editor;

import org.bukkit.entity.Player;
import com.plugins.mutzii.enums.Direction;

public class PlayerDirection {

	private Player currentplayer;
	
	public PlayerDirection(Player player){
	   setPlayer(player);
	}
	
	public Player getPlayer(){
		return this.currentplayer;
	}
	
	public PlayerDirection setPlayer(Player player){
		currentplayer = player;
		return this;
	}
	
	
	public float getYaw(Direction direction){
		
		if(direction == Direction.SOUTH){
			return 0;
		}
		
		if(direction == Direction.NORTH){
			return 2;
		}
		
		if(direction == Direction.EAST){
			return 3;
		}
		
		if(direction == Direction.WEST){
			return 1;
		}
		
	  return 0;
	}
	
	public Direction getYawDirection(float yaw){
		
		if (yaw == -4 || yaw == 0 || yaw == 4) {return Direction.SOUTH;}
	    if (yaw == -1 || yaw == 3) {return Direction.EAST;}
	    if (yaw == -2 || yaw == 2) {return Direction.NORTH;}
	    if (yaw == -3 || yaw == 1) {return Direction.WEST;}
	    
	    return null;
	}
	
	public float getYaw(){
		
		float yaw = getPlayer().getLocation().getYaw();
		yaw = yaw / 90;
	    yaw = (float)Math.round(yaw);
	 
	    return yaw;   
	}
	
	public Direction getDirection(){
		
		    float yaw = getPlayer().getLocation().getYaw();
		 
		    yaw = yaw / 90;
		    yaw = (float)Math.round(yaw);
		 
		    if (yaw == -4 || yaw == 0 || yaw == 4) {return Direction.SOUTH;}
		    if (yaw == -1 || yaw == 3) {return Direction.EAST;}
		    if (yaw == -2 || yaw == 2) {return Direction.NORTH;}
		    if (yaw == -3 || yaw == 1) {return Direction.WEST;}
		 
		    return null;
	}
	
}
