package com.plugins.mutzii.monster;

public class MonsterStoreManager {

	private MonsterManager manager;
	
	public MonsterStoreManager(MonsterManager manager){
		this.manager = manager;
	}
	
	public MonsterManager getMonsterManager(){
		return this.manager;
	}
	

}
