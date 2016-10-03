package com.plugins.mutzii.mineralz;

import java.util.List;
import org.bukkit.entity.Player;

import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.players.PlayerBehavior;

public class MineralzPlayer extends PlayerBehavior {

	private final int  DEFAULT_MINERAL_AMOUNT = 100;
	//private Scoreboard player_scoreboard;
	private List< Mineralz > mineralz_list;

	public MineralzPlayer(Player player)
	{
		super( player );
				
		mineralz_list.add( new Mineralz( DEFAULT_MINERAL_AMOUNT , MineralzType.MINERAL_BLUE  ) );
		mineralz_list.add( new Mineralz( DEFAULT_MINERAL_AMOUNT , MineralzType.MINERAL_GREEN ) );
		mineralz_list.add( new Mineralz( DEFAULT_MINERAL_AMOUNT , MineralzType.MINERAL_LILA  ) );
		mineralz_list.add( new Mineralz( DEFAULT_MINERAL_AMOUNT , MineralzType.MINERAL_RED   ) );
		
		//createScoreboard();
	}
	public MineralzPlayer( Player player , List< Mineralz > list_of_mineralz )
	{
		 super(player );
		 
		 this.mineralz_list  = list_of_mineralz;
		 
		 //createScoreboard();
	}

	public Mineralz getMineralzByType( MineralzType color )
	{
		 Mineralz new_mineralz = new Mineralz();
		
		 if( mineralz_list.isEmpty() )
			     return new_mineralz;
		
		 for( Mineralz mineralz : mineralz_list )
		 {
			 if( mineralz.getMineralzColor() == color )
			 {
				 new_mineralz = mineralz;
				 break;
			 }
		 }
		 
		 return new_mineralz;
	}
	
	public void changeMineralAmount( int amount , MineralzType color )
	{
		getMineralzByType( color ).setMineralzAmount( amount );
	}
	
	public int getMineralAmount( MineralzType color )
	{
		return getMineralzByType( color ).getMineralzAmount();
	}
			
//	@Override
//	public Scoreboard getScoreboard() {
//		return this.player_scoreboard;
//	}
//
//
//	@Override
//	public void setScoreboard(Scoreboard board) {
//		this.player_scoreboard = board;
//	}
//	
//	
//	@Override
//	public void createScoreboard() {
//		
//		ScoreboardManager manager = Bukkit.getScoreboardManager();
//		Scoreboard score          = manager.getMainScoreboard();
//		
//		//Objective 
//		Objective playerScore     = score.getObjective(getPlayer().getName());
//		
//		if(playerScore  == null){
//			playerScore = score.registerNewObjective(getPlayer().getName(), "dummy");
//			playerScore.setDisplaySlot(DisplaySlot.SIDEBAR);
//			playerScore.setDisplayName("Block Stats");
//		}
//		
//		setScoreboard(score);			
//	}
//	
//	
//	public void clearScoreBoard(){
//		Scoreboard board = getScoreboard();
//		Objective  obj   = board.getObjective(getPlayer().getName());
//		
//		if(obj != null){
//			obj.unregister();
//		}
//	}
//	
//	public void scoreBoardReload(){
//		
//		Scoreboard board = getScoreboard();
//		Objective  obj   = board.getObjective(getPlayer().getName());
//		
//		if(obj != null){
//			obj.unregister();
//		}
//		
//		createScoreboard();
//	}
//	
//	
//	public ChatColor getScoreBoardColor( MineralzType type){
//		
//		 switch(type){
//		 
//		 case MINERAL_BLUE:
//			 	return ChatColor.BLUE;
//		 case MINERAL_RED:
//			    return ChatColor.RED;
//		 case MINERAL_GREEN:
//			    return ChatColor.GREEN;
//		 case MINERAL_LILA:
//			    return ChatColor.LIGHT_PURPLE;
//		default:
//			break;
//			 		 
//		 }
//	
//	  return ChatColor.WHITE;
//	}
//	
//	@Override
//	public void updateScoreboard(){
//
//		scoreBoardReload();
//		Scoreboard board = getScoreboard();
//		Objective  obj   = board.getObjective(getPlayer().getName());
//		
//	 /**
//	  * Block Scoreboard..	
//	  */if(getMineralz() != null){
//		   
//		    MineralzType type = getMineralz().getMineralzColor();
//		    int amount          = getMineralz().getMineralz();
//			obj.getScore(Bukkit.getOfflinePlayer(getScoreBoardColor(type)+"Mineral")).setScore(amount);
//		}
//		
//	  
//	    PlayerStore.getInstance().getPlayer(getPlayer()).updateScoreBoard(null);			
//	}	
}
