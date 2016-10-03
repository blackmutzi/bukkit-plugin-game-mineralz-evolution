package com.plugins.mutzii.threads;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import com.plugins.mutzii.monster.MonsterNavigation;
import com.plugins.mutzii.monster.MonsterStoreManager;
import com.plugins.mutzii.players.PlayerBehavior;
import com.plugins.mutzii.storage.GameStore;
import com.plugins.mutzii.storage.MonsterStore;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class MonsterInteract implements Runnable 
{
	private World world;
	
	public MonsterInteract( World world )
	{
		this.world = world;
	}
	
	private boolean isBuildingDestroyed( Location location )
	{
		Block block = location.getBlock();
		
		if(block.getType() == Material.AIR)
			return true;
		
	    return false;
	}
	
//	public void isBossMonster(MonsterManager monsterManager){
//		   
//		   //If Boss Monster
//			if(getLastMonster() == BOSS_MONSTER){
//				
//				monsterManager.getLivingEntity().setCustomName(ChatColor.GOLD+" BOSS MONSTER ");
//			    monsterManager.getLivingEntity().setCustomNameVisible(true);
//				
//			    monsterManager.getEquipmentManager().getGoldSword();
//			    
//				monsterManager.setLive(monsterManager.getLive() + 10000);
//				monsterManager.setDamage(monsterManager.getDamage() * 5);
//				Bukkit.broadcastMessage(ChatColor.GOLD+"BOSS Zombie has spawned");
//			}
//		   
//		   
//	   }

	@Override
	public void run() 
	{
		
		while( GameStore.getInstance().isGameRunning() )
		{
			if( !MonsterStore.getInstance().getList().isEmpty() )
			{
		
			   for(MonsterStoreManager manager : MonsterStore.getInstance().getList() )
			   {
				   MonsterNavigation navigation      = new MonsterNavigation( manager.getMonsterManager() , this.world ); 
				   LivingEntity      living_entity   = navigation.getManager().getLivingEntity();
				   Location          build_location  = navigation.buildLocation( living_entity );
				   
				   if(navigation.getManager().getEntity().isDead() || navigation.getManager().getLive() <= 0)
				   {
					  navigation.getManager().getEntity().playEffect(EntityEffect.DEATH);
					  navigation.getManager().getEntity().playEffect(EntityEffect.HURT);
					  navigation.getManager().kill();  
					  continue;
				   }
										   
				   if(build_location != null)
				   {
					    //Entity is Attack Building 
					   
					    // is building not destroyed : false
					   if( !isBuildingDestroyed( build_location ) )
					   {
						   navigation.tryRemoveBlock( living_entity , build_location );
						   navigation.getManager().getMonsterController().getActions().clearActionQueue();
						   navigation.getManager().moveTo( build_location );
						   navigation.getManager().getMonsterController().getActions().wait(10);   
					   }
					   
				   }else
				   {
					   //Entity is Attack Player  
					   
					   for(PlayerBehavior player: PlayerMineralzStore.getInstance().getList())
					   {
	    	    		 	
							if(player.isPlayerAlive() && player.getPlayer().isOnline() )
							{
								if( living_entity.hasLineOfSight(  player.getPlayer() ) )
								{
									
									 navigation.getManager().getMonsterController().getActions().follow( player.getPlayer() );
									 navigation.getManager().getMonsterController().getActions().wait(4);
									 navigation.getManager().getMonsterController().getActions().target( player.getPlayer() );
									 navigation.getManager().getMonsterController().getActions().wait(4);
									
								}
							}			
	    	    	   } //#for loop 
					  
				   }//#if-else
				   
			   } //#for loop
			    	  	     
			}//#if
			
		}//#while
		
	}//#methode:run
}
