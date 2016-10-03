package com.plugins.mutzii.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class VirtualInventory implements Inventorys
{
	private Inventory inventory;
	private String    inventoryName;
	
	public VirtualInventory() {}
	
	@Override
	public void createVirtualInventory( int slots , String inventory_name )
	{
		this.setInventory( Bukkit.createInventory(null, slots , inventory_name ) );
		this.setInventoryName( inventory_name );
	}
		
	@Override
	public void closeVirtualInventory(Player player) {
		player.closeInventory();
	}
	
	@Override
	public void openVirtualInventory(Player player){
		player.openInventory( this.getInventory() );
	}
	
	@Override
	public Inventory getInventory() {
		return inventory;
	}
	
	@Override
	public String getInventoryName()
	{
		return this.inventoryName;
	}
	
	@Override
	public void setInventoryName(String name) {
	   	this.inventoryName = name;
	}
	
	@Override
	public void setInventory(Inventory inv) {
		this.inventory = inv;	
	}

}
