package com.plugins.mutzii.threads;

public class ThreadInteract 
{
	private static ThreadInteract instance = null;

	public ThreadInteract(){}
	
	public static ThreadInteract getInstance()
	{
		if( instance == null)
			    instance = new ThreadInteract();
		
		return instance;
	}

	public int spawn_entitys            = 0;
	public int entity_live_points       = 0;	
	public int entity_damage            = 0;
	public boolean entity_boss          = false;
	public boolean spawn_entity_process = false;
		
}
