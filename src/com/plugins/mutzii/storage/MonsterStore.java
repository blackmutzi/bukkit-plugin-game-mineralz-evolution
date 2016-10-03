package com.plugins.mutzii.storage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import com.plugins.mutzii.api.MineralzDatabase;
import com.plugins.mutzii.monster.MonsterStoreManager;
import com.plugins.mutzii.monster.MonsterManager;

public class MonsterStore implements MineralzDatabase{

	private static MonsterStore instance = null;

	protected MonsterStore(){}
	
	private CopyOnWriteArrayList<MonsterStoreManager> monsterList = null;
	
	
	public static MonsterStore getInstance(){
		
		if(instance == null){
			instance = new MonsterStore();
			instance.clear();
		}
		
	  return instance;
	}
	
	
	public boolean isRegister(LivingEntity entity){
		
		for(MonsterStoreManager monster : monsterList){
			
			if(monster.getMonsterManager().getEntity().getEntityId() == entity.getEntityId()){
				return true;
			}
			
		}
		
	  return false;
	}
	
	
	public  List<MonsterStoreManager> getList(){
		return this.monsterList;
	}
	
	public void register(MonsterManager manager){
		if(manager.isMonsterSpawned()){
			monsterList.add(new MonsterStoreManager(manager));
			Bukkit.getServer().getLogger().log(Level.INFO, "Monster wird registriert.");
		}
	}
	
	public void unregister(LivingEntity entity){
		
		int counter = 0;
		for(MonsterStoreManager monster : monsterList){
			
			if(monster.getMonsterManager().getEntity().getEntityId() == entity.getEntityId()){
				 monsterList.remove(counter);
				 break;
			 }
		  counter++;	
		}
	}


	public  MonsterManager getMonsterManager(LivingEntity entity) throws NullPointerException{
	
		for(MonsterStoreManager monster : monsterList){
			
			if(monster.getMonsterManager().getEntity().getEntityId() == entity.getEntityId()){
				return monster.getMonsterManager();
			}
			
		}
		
	  return null;
	}
	
	
	
	@Override
	public  void clear() {
		monsterList = new CopyOnWriteArrayList<MonsterStoreManager>();
	}

}
