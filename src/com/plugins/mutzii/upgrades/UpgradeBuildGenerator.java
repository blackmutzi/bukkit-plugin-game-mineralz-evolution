package com.plugins.mutzii.upgrades;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.plugins.mutzii.api.GeneratorUpgrades;
import com.plugins.mutzii.api.UpgradeManager;
import com.plugins.mutzii.build.MineralzGenerator;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.config.Identification;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.mineralz.ShareMineralz;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.other.PriceChecker;
import com.plugins.mutzii.storage.BuildingStore;

public class UpgradeBuildGenerator extends PriceChecker implements GeneratorUpgrades,UpgradeManager{

	  private MineralzGenerator generator;
	
	  public UpgradeBuildGenerator(MineralzGenerator generator){
	      this.generator = generator;	  
	  }
	
	  
	  public int getLevel(){
		  return generator.getLevel();
	  }
	  	  
	  @Override
	  public void upgrade(Upgrades upgrade,Player player){
		
		  if(upgrade != null){
			switch(upgrade){
			
			 case ENABLE_POWER:
				  generator.setActiv(true);
		     break;	
		     
			 case DISABLE_POWER:
				  generator.setActiv(false);
			 break;
			 
			 case UPGRADE_GENERATOR: 
				  
				  Building manager = BuildingStore.getInstance().getBuildingBase(player);
				  
				  if(manager != null && manager.getLevel() >= getLevel() ){
					  upgradeGenerator(player);
				  }
				  				  
			 break;
			 
			 case DESTROY:
				   generator.setPower(0);
				   generator.setLevel(0);
				   generator.setLive(0);
				   generator.virtual_scoreboard.updateScoreboard();
				   generator.remove();
				   
				   MineralzPosition position = generator.getPosition();
				   
				   Location location = new Location(generator.getPlayer().getWorld(),position.getX(),position.getY(),position.getZ());
				   generator.getPlayer().getWorld().playEffect(location,Effect.DOOR_TOGGLE,1);
			 break;
			default:
				break;
			 	 
			}
		  }
			
	}
	  
	  
	    @Override
		public void upgradeGenerator(Player player) 
	    {	
			 UpgradePrice price =  getConfigStore().getUpgrade(Upgrades.UPGRADE_GENERATOR);	
			
			 List< MineralzType > list_of_mineralz_type = new Identification().getMineralzTypeList();
			 
			 boolean has_enough_mineralz = false;
			 
			 for( MineralzType type : list_of_mineralz_type )
			 {
				 has_enough_mineralz  = ShareMineralz.hastPlayerEnoughMineralz( player, type , price.get( type , getLevel() ) );
			 }
			 
			 if( has_enough_mineralz )
			 {
				 
				 for( MineralzType type : list_of_mineralz_type )
				 {
					 ShareMineralz.changeMineralAmountNegative( player , type, price.get( type, getLevel() ) );
				 }
				 
				 generator.setLevel( generator.getLevel()+ADD_LEVEL);
			 	 generator.setLive( generator.getLive() + ADD_LIVE_POINTS_PRO_LEVEL);
			 	 generator.setMaxLive( generator.getLive());	
			 	 generator.setPower(generator.getMaxPower());
			 	 
			 	 //TODO: updateTabScoreboard(player);
				 
			 }
		 } 
		
	
}
