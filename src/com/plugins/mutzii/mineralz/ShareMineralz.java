package com.plugins.mutzii.mineralz;

import org.bukkit.entity.Player;

import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.storage.PlayerMineralzStore;

public class ShareMineralz {

	private Player sender;

	public ShareMineralz(){}
	public ShareMineralz(Player player)
	{
		this.sender = player;
	}
		
	/**
	 * Send Mineralz by Type to new Player 
	 */
	public boolean sendMineralTo( Player player ,MineralzType type ,int amount )
	{
		/* has the Sender so much amount : yes then true */
		if( getMineralAmount( this.sender , type ) >= amount )
		{
			/* remove the mineralz from sender */
			changeMineralAmountNegative( this.sender , type , amount );
			
			/* add amount to the new player */
			changeMineralAmountPositive( player , type , amount );
			
			//TODO: Send Message to Success 
			//TODO: Show / Update TAB Scoreboard 
			
			return true; //success return true 
		}
		
		return false;
	}
	
	public static int getMineralAmount( Player player , MineralzType type )
	{
		MineralzPlayer m_player = PlayerMineralzStore.getInstance().getPlayerMineralzz( player );
		return m_player.getMineralAmount( type );
	}
	
	public static void changeMineralAmountNegative( Player player , MineralzType type , int amount )
	{
		int last_mineral_amount = getMineralAmount( player , type );
		MineralzPlayer m_player  = PlayerMineralzStore.getInstance().getPlayerMineralzz( player );
		
		m_player.changeMineralAmount( last_mineral_amount - amount ,  type );	
	}
	
	public static void changeMineralAmountPositive( Player player , MineralzType type , int amount )
	{
		int last_mineral_amount  = getMineralAmount( player ,type );
		MineralzPlayer m_player  = PlayerMineralzStore.getInstance().getPlayerMineralzz( player );
		
		m_player.changeMineralAmount( last_mineral_amount + amount ,  type );	
	}
	
	public static boolean hastPlayerEnoughMineralz( Player player , MineralzType type , int amount )
	{
		if( getMineralAmount( player , type) >= amount )
			     return true;
		
		return false;
	}
		
}
