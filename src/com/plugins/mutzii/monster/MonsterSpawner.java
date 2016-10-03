package com.plugins.mutzii.monster;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.plugins.mutzii.events.MonsterSpawnEvent;
import com.plugins.mutzii.storage.MonsterStore;
import com.plugins.mutzii.storage.GameStore;

public class MonsterSpawner
{

	public MonsterSpawner(){}

	public MonsterManager createMonster(int livepoints){
		
        Location monsterSpawnLocation = GameStore.getInstance().getMonsterSpawnLocation();
		MonsterZombie monster = new MonsterZombie(monsterSpawnLocation,livepoints);
		
		monster.setDamage(GameStore.getInstance().getMonsterDamage());
		monster.setLevel(GameStore.getInstance().getMonsterLevel());
	
			if(monster.spawn(monster.getLevel(),monster.getDamage())){
			
				if(monster.isMonsterSpawned()){
					
					if(monster.getLivingEntity().isValid()){
						
						MonsterStore.getInstance().register(monster);
						Bukkit.getServer().getPluginManager().callEvent(new MonsterSpawnEvent(monster.getLivingEntity()));
						return monster;
					}
					
				}
			}
			
	
	  return null;
	}

	
}
