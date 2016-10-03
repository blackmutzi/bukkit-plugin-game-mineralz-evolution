package com.plugins.mutzii.upgrades;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.plugins.mutzii.api.TowerUpgrades;
import com.plugins.mutzii.api.UpgradeManager;
import com.plugins.mutzii.build.MineralzTower;
import com.plugins.mutzii.buildingmanager.Building;
import com.plugins.mutzii.config.Identification;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.mineralz.ShareMineralz;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.other.PriceChecker;
import com.plugins.mutzii.storage.BuildingStore;

public class UpgradeBuildTower extends PriceChecker implements TowerUpgrades,UpgradeManager
{

	private MineralzTower tower;
	
	public UpgradeBuildTower(MineralzTower tower){
		this.tower = tower;
	}
	
	
	@Override
    public void upgrade(Upgrades upgrade,Player player){
				
		switch(upgrade){
		
		 case ENABLE_TOWER:
			 tower.setActiv(true);
		 break;
		 
		 case DISABLE_TOWER:
			 tower.setActiv(false);
		 break;
		
		 case ENABLE_TOWER_DISPENSER:
			 
			 if( tower.isActiv() ){
			 
				 Bukkit.getServer().getLogger().log(Level.INFO, "Enable Dispenser (Upgrade)");
				 tower.enableDispenser();
				 
			 }else{
				 player.sendMessage("Tower is deactived");
			 }
			 
			 
		 break;
		 
		 case DISABLE_TOWER_DISPENSER:
			 
			 if( tower.isActiv() ){
			 
				 Bukkit.getServer().getLogger().log(Level.INFO, "Disable Dispenser (Upgrade)");
				 tower.disableDispenser();
				
			 }else{
				 player.sendMessage("Tower is deactived");
			 }
		 
		 break;
		 
		 case UPGRADE_TOWER:
			  
			  Building manager = BuildingStore.getInstance().getBuildingBase(player);
			  if(manager != null && manager.getLevel() >= tower.getLevel()){
			     upgradeTower(player);
			  }
			  
		 break;
		 
		 case DESTROY:
			   tower.setPower(0);
			   tower.setLevel(0);
			   tower.setLive(0);
			   tower.virtual_scoreboard.updateScoreboard();
			   tower.remove();
			   
			   MineralzPosition position = tower.getPosition();
			   
			   Location location = new Location( tower.getPlayer().getWorld(),position.getX(), position.getY(), position.getZ());
			   tower.getPlayer().getWorld().playEffect(location,Effect.GHAST_SHRIEK,1);
			   
		 break;
		default:
			break;
		 	 
		}
		
	}
	
	
	@Override
	public void upgradeTower(Player player) 
	{
				
		 UpgradePrice price =  getConfigStore().getUpgrade(Upgrades.UPGRADE_TOWER);	
			
		 List< MineralzType > list_of_mineralz_type = new Identification().getMineralzTypeList();
		 
		 boolean has_enough_mineralz = false;
		 
		 for( MineralzType type : list_of_mineralz_type )
		 {
			 has_enough_mineralz  = ShareMineralz.hastPlayerEnoughMineralz( player, type , price.get( type , tower.getLevel() ) );
		 }
		 
		 /* if the player has enough mineralz then remove the mineralz from player */
		 if( has_enough_mineralz )
		 {
			 for( MineralzType type : list_of_mineralz_type )
			 {
				 ShareMineralz.changeMineralAmountNegative( player , type, price.get( type, tower.getLevel() ) );
			 }
			 
			 tower.setLevel( tower.getLevel() + ADD_LEVEL);	
		 	 tower.setLive(tower.getLive() + ADD_LIVE_POINTS_PRO_LEVEL);
		 	 tower.setMaxLive(tower.getLive());
		 		
		 		//updateTabScoreboard(player);
		 	 tower.virtual_scoreboard.updateScoreboard();  
		 }
	}
	
	
}
