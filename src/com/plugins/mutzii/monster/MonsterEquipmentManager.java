package com.plugins.mutzii.monster;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

/**
 * Monster Equipment Manager
 * @author Mutzii
 *
 */
public class MonsterEquipmentManager 
{
	private LivingEntity entity;
	
	public MonsterEquipmentManager(LivingEntity entity){
		this.entity = entity;
	}
	
	public LivingEntity getEntity(){
		return this.entity;
	}
	
	public void getDiamondSword(){
		getEntity().getEquipment().setItemInMainHand(new ItemStack( Material.DIAMOND_SWORD ));
	}
	
	public void getDiamondBoots(){
		getEntity().getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
	}
	
	public void getDiamondChestplate(){
		getEntity().getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
	}
	
	public void getDiamondHelmet(){
		getEntity().getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
	}
	
	public void getDiamondLeggings(){
		getEntity().getEquipment().setLeggings(new ItemStack( Material.DIAMOND_LEGGINGS ));
	}
	
	public void getGoldSword(){
		getEntity().getEquipment().setItemInMainHand(new ItemStack( Material.GOLD_SWORD ));
	}
	
	public void removeEquipment(){
		getEntity().getEquipment().clear();
	}
}
