package com.plugins.mutzii.buildingmanager;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventorySlotManager {

	private Inventory inventory;
	
	public InventorySlotManager(){}
	
//	public InventorySlotManager(BuildManager manager,Inventory inv,int slot,ItemStack stack, Upgrades upgrade, String name){
//		this.inventory = inv;
//		this.slot      = slot;
//		this.stack     = stack;
//		this.upgrade   = upgrade;
//		this.name      = name;
//		this.manager   = manager;
//		
//		getManager().setUpgradeSlot(getSlot(),getUpgrade());
//		getInventory().setItem(getSlot(), getItemStack());
//	}
//	
	
	public void addItemToInventory( int inventory_slot , ItemStack item , String display_name )
	{
		this.changeDisplayName(item, display_name);
		this.getInventory().setItem( inventory_slot , item );		
	}
	
	
	private void changeDisplayName(ItemStack item, String display_name )
	{
		 ItemMeta meta_data  = item.getItemMeta();
		 
		 meta_data.setDisplayName( display_name );
		 
		 item.setItemMeta(meta_data);
	}
	
	
	public void setInventory( Inventory inventory )
	{
		this.inventory = inventory;
	}
	
	public Inventory getInventory(){
		return this.inventory;
	}
	

//	public void setItemMeta(Inventory inventory,int slot ,String header,ArrayList<String> itemdesc){
//		
//		 ItemStack stack = inventory.getItem(slot);
//		 ItemMeta meta   = stack.getItemMeta();
//		 
//		 meta.setDisplayName(header);
//		 meta.setLore(itemdesc);
//		 
//		 stack.setItemMeta(meta);
//		 inventory.setItem(slot, stack);
//		 this.inventory = inventory;
//    }
//	
//	public Inventory fill(){
//		ArrayList<String> itemdesc = new ArrayList<String>();	
//	    setItemMeta(getInventory(),getSlot(),this.name,itemdesc);
//		return getInventory();
//	}
}
