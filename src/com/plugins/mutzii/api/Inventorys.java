package com.plugins.mutzii.api;


import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface Inventorys {

	public void setInventoryName(String name);
	public String getInventoryName();
	
	public Inventory getInventory();
	public void setInventory(Inventory inv);
	
	public void openVirtualInventory( Player player );
	public void createVirtualInventory(int slots, String inventory_name );
	public void closeVirtualInventory(Player player);
	
}
