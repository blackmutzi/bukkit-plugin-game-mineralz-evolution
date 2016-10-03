package com.plugins.mutzii.storage;

import java.util.ArrayList;
import java.util.List;

import com.plugins.mutzii.api.MineralzDatabase;
import com.plugins.mutzii.commandmanager.CommandBehavior;
import com.plugins.mutzii.commandmanager.EvolutionCommand;


public class CommandStore implements MineralzDatabase{

	private static CommandStore instance = null;
	
	protected CommandStore(){}
	
	protected List<EvolutionCommand> cmdList = null;
	
	
	public static CommandStore getInstance(){
		
		if(instance == null){
			instance = new CommandStore();
			instance.clear();
		}
		
	  return instance;
	}
	
	public  List<EvolutionCommand> getList(){
		return this.cmdList;
	}
	
	public void register(String first_argument , String permission , CommandBehavior instance )
	{
		 cmdList.add( new EvolutionCommand( first_argument , permission , instance ));
	}
	
	
	@Override
	public  void clear() {
		this.cmdList = new ArrayList<EvolutionCommand>();
	}

}
