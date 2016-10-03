package com.plugins.mutzii.players;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.plugins.mutzii.build.MineralzBase;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.enums.Direction;
import com.plugins.mutzii.other.MineralzPosition;;

public abstract class PlayerBehavior 
{

	private final int PLAYER_DEFAULT_DAMAGE  = 100;
	private Player player                    = null;
	private MineralzPosition position        = new MineralzPosition(0,0,0); 
	private boolean playerIsAlive            = true;
	private ItemStack[] inventory_content    = null;
	private MineralzBase base_building       = null;
	private double  player_make_damage       = PLAYER_DEFAULT_DAMAGE;
	private Direction lastdirection;
	private boolean  open_virtual_inventory  = false;
	private Building	 inventory_building  = null;
	
	
	public PlayerBehavior(){}
	public PlayerBehavior( Player player )
	{
		 this.player = player; 
	}

	public boolean hasVirtualInventoryOpen()
	{
		return open_virtual_inventory;
	}
	
    public void setVirtualInventoryStatus( boolean status )
    {
    	this.open_virtual_inventory = status;
    }
	
	public void setInventoryBuilding( Building building )
	{
		this.inventory_building = building;
	}
	
	public Building getInventoryBuilding()
	{
		return this.inventory_building;
	}
	
	public void setDamage( double player_make_damage_amount )
	{
		this.player_make_damage = player_make_damage_amount;
	}
	
	public double getDamage(){
		return this.player_make_damage;
	}
	
	public void setMineralzBase( MineralzBase base_building )
	{
		this.base_building = base_building;
	}
	
	public MineralzBase getMineralzBase()
	{
		return this.base_building;
	}
	
	public boolean hasMineralzBase()
	{
		if( this.base_building != null ) 
			   return true;
		
		return false;
	}
	
	public void saveInventoryContent()
	{
		Inventory inventory = this.player.getInventory();
		inventory_content   = inventory.getContents();
	}
	
	public void restoreInventoryContent()
	{
		this.player.getInventory().setContents( inventory_content );
	}
	
	public ItemStack[] getInventoryContent() 
	{
		if( inventory_content != null){
			return this.inventory_content;
		}
		
	  return null;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public boolean isPlayerAlive()
	{
		return playerIsAlive;
	}
	
	public Direction getLastPlayerDirection(){
		return this.lastdirection;
	}
	
	public void setLastPlayerDirection(Direction direction){
		this.lastdirection = direction;
	}
	
	public void setPlayerAliveStatus(boolean status){
		this.playerIsAlive = status;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	@Deprecated
	public void setLastBlockClicked(MineralzPosition position){
		this.position = position;
	}
	
	@Deprecated
	public MineralzPosition getLastBlockPositionClicked(){
		return this.position;
	}
	
	
//	public void updateScoreBoard(String timer)
//	{
//		
//			 TabScoreboard playerListScoreboard = new TabScoreboard(getPlayerRegister().plugin);
//			 playerListScoreboard.setPlayer(getPlayer());
//			
//			 TabAPI.setPriority(getPlayerRegister().plugin,getPlayer(), 0);
//			 playerListScoreboard.updateScoreBoard(timer);
//	}
//		
}
