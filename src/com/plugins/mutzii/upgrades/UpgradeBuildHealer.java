package com.plugins.mutzii.upgrades;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.plugins.mutzii.api.HealerUpgrades;
import com.plugins.mutzii.api.UpgradeManager;
import com.plugins.mutzii.build.MineralzHealer;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.config.Identification;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.mineralz.ShareMineralz;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.other.PriceChecker;
import com.plugins.mutzii.storage.BuildingStore;

public class UpgradeBuildHealer extends PriceChecker implements HealerUpgrades,UpgradeManager{

	 private MineralzHealer healer;
	
	 public UpgradeBuildHealer(MineralzHealer healer){
		 this.healer = healer;
	 }
		 
	 public int getLevel(){
		 return healer.getLevel();
	 }
	 	
	 @Override
	 public void upgrade(Upgrades upgrade,Player player){
			
		 if(upgrade != null){
			switch(upgrade){
			
			 case ENABLE_HEAL:
				healer.setActiv(true);
			 break;
			
			 case DISABLE_HEAL:
				healer.setActiv(false);
			 break;
			
			 case UPGRADE_HEALER:
				  Building manager = BuildingStore.getInstance().getBuildingBase(player);
				  if(manager != null && manager.getLevel() >= healer.getLevel()){
					  upgradeHealer(player);
				  }
			 break;
			 
			 case DESTROY:
				   healer.setPower(0);
				   healer.setLevel(0);
				   healer.setLive(0);
				   healer.virtual_scoreboard.updateScoreboard();
				   healer.remove();
				   
				   MineralzPosition position = healer.getPosition();
				   
				   Location location = new Location(healer.getPlayer().getWorld(),position.getX(),position.getY(),position.getZ());
				   healer.getPlayer().getWorld().playEffect(location,Effect.GHAST_SHOOT,1);
				   
			 break;
			default:
				break;
			 	 
			}
		 }
			
	}
	 
	 
	    @Override
		public void upgradeHealer(Player player) 
	    {
			
			 UpgradePrice price =  getConfigStore().getUpgrade(Upgrades.UPGRADE_HEALER);	
				
			 List< MineralzType > list_of_mineralz_type = new Identification().getMineralzTypeList();
			 
			 boolean has_enough_mineralz = false;
			 
			 for( MineralzType type : list_of_mineralz_type )
			 {
				 has_enough_mineralz  = ShareMineralz.hastPlayerEnoughMineralz( player, type , price.get( type , getLevel() ) );
			 }
			 
			 /* if the player has enough mineralz then remove the mineralz from player */
			 if( has_enough_mineralz )
			 {
				 for( MineralzType type : list_of_mineralz_type )
				 {
					 ShareMineralz.changeMineralAmountNegative( player , type, price.get( type, getLevel() ) );
				 }
				 
					healer.setLevel(healer.getLevel()+ADD_LEVEL);
			 		
			 		healer.setLive(healer.getLive() + ADD_LIVE_POINTS_PRO_LEVEL);
			 		healer.setMaxLive(healer.getLive());
			 		
			 		healer.setPower(healer.getMaxPower());
			 		
			 		//updateTabScoreboard(player);
			 		healer.virtual_scoreboard.updateScoreboard();  
			 }	
		} 
		
	
	
}
