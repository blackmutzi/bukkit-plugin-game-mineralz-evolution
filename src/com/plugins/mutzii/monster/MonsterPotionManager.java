package com.plugins.mutzii.monster;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MonsterPotionManager {
	
	private LivingEntity entity;
	
	public MonsterPotionManager(LivingEntity entity){
		this.entity = entity;
	}
	
	public LivingEntity getEntity(){
		return this.entity;
	}
	
	//Bug
	public void MonsterHeal(int duration,int healtype){
		getEntity().addPotionEffect(new PotionEffect(PotionEffectType.HEAL,duration,healtype));
	}

	public void MonsterDamageResistance(int duration){
		getEntity().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,duration,1));
	}
	
	public void MonsterFireResistance(int duration){
		getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,duration,1));
	}
	
	
}
