package com.plugins.mutzii.threads;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;

import com.plugins.mutzii.storage.GameStore;
import com.plugins.mutzii.storage.MonsterStore;

public class StageProcess implements Runnable
{
	private int     stage_level    = 0;
	private String  current_time   = "00:00";
	
	private final int ADD_STAGE_LEVEL      = 1;
	private final int MONSTER_START_LIVE   = 3500;
	private final int MONSTER_START_DAMAGE = 200;
    private final int MAX_MONSTER          = 10;	
	
	public StageProcess( int start_level )
	{
		this.stage_level = start_level;
	}
	
	private String getString( int minutes , int seconds )
	{
		String _minutes = new String( String.valueOf( minutes ) );
		String _seconds = new String( String.valueOf( seconds ) );
		
		if( minutes <= 9 )
			    _minutes = new String("0"+String.valueOf(minutes));
		
		if( seconds <= 9 )
			    _seconds = new String("0"+String.valueOf(seconds));
		
		return new String(" "+_minutes+":"+_seconds+" ");
	}
	
	private void Waiting( int minutes , int seconds )  throws InterruptedException
	{
		if( minutes <= 0 && seconds <= 0 )
			   return;
		
		while(true)
		{
			this.current_time = getString( minutes , seconds );
			//TODO: show in TAB Scoreboard
			
			if( seconds == 0 )
			{
				minutes--;
				seconds = 59;
			}
			
			TimeUnit.SECONDS.sleep(1);
			seconds--;
			
			if( minutes <= 0 && seconds <= 0 )
				     break;
		}
	}
	

	@Override
	public void run() 
	{
		try
		{
			while( GameStore.getInstance().isGameRunning() )
			{
				if( MonsterStore.getInstance().getList().isEmpty() )
				{
					/* wait a minute */
					Waiting(1,30);
									
					/* set entity live points by Stage Level */
					if( this.stage_level == 1 )
					{
						ThreadInteract.getInstance().spawn_entitys      = MAX_MONSTER;
						ThreadInteract.getInstance().entity_boss        = false;
						ThreadInteract.getInstance().entity_live_points = MONSTER_START_LIVE;
						ThreadInteract.getInstance().entity_damage      = MONSTER_START_DAMAGE;
						
					}else
					{
						ThreadInteract.getInstance().spawn_entitys      = MAX_MONSTER;
						ThreadInteract.getInstance().entity_boss        = false;
						ThreadInteract.getInstance().entity_live_points = MONSTER_START_LIVE    + 10 * this.stage_level;
						ThreadInteract.getInstance().entity_damage      = MONSTER_START_DAMAGE  + 10 * this.stage_level;
					}
					
					/* Show Stage Level */
					Bukkit.broadcastMessage("Current Stage Level "+ this.stage_level );
					
					/* spawn entitys */
					ThreadInteract.getInstance().spawn_entity_process = true;
					
					/* wait unit all entitys spawned */
					while( ThreadInteract.getInstance().spawn_entity_process ){}	
					
					/* Add Stage Level ... */
					this.stage_level += ADD_STAGE_LEVEL;
				}	
			}
			
		}catch( InterruptedException exception ){}		
	}
}
