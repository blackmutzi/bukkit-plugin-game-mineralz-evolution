package com.plugins.mutzii.upgrades;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.plugins.mutzii.api.Base;
import com.plugins.mutzii.api.BaseUpgrades;
import com.plugins.mutzii.api.UpgradeManager;
import com.plugins.mutzii.build.MineralzBase;
import com.plugins.mutzii.config.Identification;
import com.plugins.mutzii.enums.BuildType;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.enums.Upgrades;
import com.plugins.mutzii.mineralz.ShareMineralz;
import com.plugins.mutzii.other.MineralzPosition;
import com.plugins.mutzii.other.PriceChecker;

public class UpgradeBuildBase extends PriceChecker implements Base,BaseUpgrades,UpgradeManager{

	private MineralzBase base;
	
	public UpgradeBuildBase(MineralzBase base){
		this.base = base;
	}
			
	@Override
	public void upgrade(Upgrades upgrade,Player player){
	
	if(upgrade != null){
		
		switch(upgrade){
		
		 case UPGRADE_ALL_MINERAL: 
			 upgradeMinerals(player);
		 break;
		 
		 case UPGRADE_MINERAL_RED:
			 upgradeMineral(MineralzType.MINERAL_RED,player);
		 break;
		 
		 case UPGRADE_MINERAL_BLUE:
			 upgradeMineral(MineralzType.MINERAL_BLUE,player);
		 break;
		 
		 case UPGRADE_MINERAL_LILA:
			 upgradeMineral(MineralzType.MINERAL_LILA,player);
		 break;
		 
		 case UPGRADE_MINERAL_GREEN:
			 upgradeMineral(MineralzType.MINERAL_GREEN,player);
		 break;
		 
		 case UPGRADE_BASE:
			 upgradeBase(player);
		 break;	
		 
		 case BUILD_TOWER:
			     givePlayerBuildItemInHand( player , Upgrades.BUILD_TOWER     , BuildType.TOWER ,    "Build Tower");
		 break;
		 
		 case BUILD_HEALER:
			     givePlayerBuildItemInHand( player , Upgrades.BUILD_HEALER    , BuildType.HEALER ,   "Build Healer");
		 break;
		 
		 case BUILD_GENERATOR:
			     givePlayerBuildItemInHand( player , Upgrades.BUILD_GENERATOR , BuildType.GENERATOR , "Build Generator");
		 break;
		 
		 case DESTROY:
			   base.setPower(0);
			   base.setLevel(0);
			   base.setLive(0);
			   base.virtual_scoreboard.updateScoreboard();
			   base.remove();
			   
			   MineralzPosition position = base.getPosition();
			   
			   Location location = new Location(base.getPlayer().getWorld(),position.getX(),position.getY(),position.getZ());
			   
			   base.getPlayer().getWorld().playEffect(location,Effect.POTION_BREAK,1);
			   
		 break;
		default:
			break;
		 
		}
	 }
	}
	

	public int getLevel(){
		return base.getLevel();
	}

	@Override
	public int getMineralLevel(MineralzType mineral) {
		return base.getMineralLevel(mineral);
	}
	
	@Override
	public void setMineralLevel(MineralzType type, int level) {
		base.setMineralLevel(type, level);
	}
	
	@Override
	public void upgradeBase(Player player) 
	{
		
		 UpgradePrice price =  getConfigStore().getUpgrade(Upgrades.UPGRADE_BASE);
		
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
			 
			 //change Base Attribute 
			 base.setLevel(base.getLevel()+ ADD_LEVEL);	
		 	 base.setLive(base.getLive() + ADD_LIVE_POINTS_PRO_LEVEL);
		 	 base.setMaxLive(base.getLive());		 		
		     base.setPower(base.getMaxPower());
		     
		     //TODO: updateTabScoreboard(player);
		     base.virtual_scoreboard.updateScoreboard();    
		 }
	}

	@Override
	public void upgradeMineral(MineralzType mineral,Player player){
		
		UpgradePrice  pGreen =  getConfigStore().getUpgrade(Upgrades.UPGRADE_MINERAL_GREEN);
		UpgradePrice  pRED   =  getConfigStore().getUpgrade(Upgrades.UPGRADE_MINERAL_RED);
		UpgradePrice  pBlue  =  getConfigStore().getUpgrade(Upgrades.UPGRADE_MINERAL_BLUE);
		UpgradePrice  pLila  =  getConfigStore().getUpgrade(Upgrades.UPGRADE_MINERAL_LILA);
		/**
		 * Hier werden die Preise fuer die Upgrades festgelegt...
		 */int price = 0;
		 		if(mineral == GREEN){  price  = pGreen.getMine(GREEN,getMineralLevel(GREEN));}
		 			if(mineral == RED){    price  = pRED.getMine(GREEN,getMineralLevel(RED));}
		 				if(mineral == BLUE){   price  = pBlue.getMine(GREEN,getMineralLevel(BLUE));}
		 					if(mineral == LILA){   price  = pLila.getMine(GREEN,getMineralLevel(LILA));}
		
		    // Mineral Upgrades Cost: GREEN Mineralz
            // has Player enough green mineralz then true 
		 	if( ShareMineralz.hastPlayerEnoughMineralz( player, GREEN , price ) )
		 	{
		 		// Remove only Green Mineralz from Player 
		 		ShareMineralz.changeMineralAmountNegative( player , GREEN , price );
		 		
		 		//Set Upgrade Attribute 
		 		base.setMineralLevel( mineral , base.getMineralLevel(mineral) + ADD_LEVEL );
		 		
		 		//Show Stats / Scoreboard 
		 		//TODO: Show / Upadte TAB Scoreboard 
		 		
		 		base.virtual_scoreboard.updateScoreboard();	
		 	}
	}
	

    @Override
	public void upgradeMinerals(Player player) 
	{
		
		upgradeMineral(MineralzType.MINERAL_BLUE  ,player );
		upgradeMineral(MineralzType.MINERAL_RED   ,player );
		upgradeMineral(MineralzType.MINERAL_GREEN ,player );
		upgradeMineral(MineralzType.MINERAL_LILA  ,player );
		
	}
	
	private void givePlayerBuildItemInHand( Player player , Upgrades upgrade , BuildType buildtype , String display_name )
	{
		UpgradePrice price =  getConfigStore().getUpgrade( upgrade );
		
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
			 
			 //give player Build Item in the Hand 
			 
			 ItemStack item = new ItemStack( new Identification().getMaterialByBuildType( buildtype ) );
			 ItemMeta  meta = item.getItemMeta();
			 meta.setDisplayName( display_name );
			 item.setItemMeta( meta );
			 
			 player.getInventory().setItemInMainHand( item );
			 
			 //TODO: Show TAB Scoreboard 
			 base.virtual_scoreboard.updateScoreboard();
			 
		 }
	}	
}
	
	
