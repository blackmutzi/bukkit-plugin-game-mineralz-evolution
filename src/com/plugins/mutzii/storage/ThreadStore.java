package com.plugins.mutzii.storage;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import org.bukkit.scheduler.BukkitTask;

import com.plugins.mutzii.api.MineralzDatabase;



public class ThreadStore implements MineralzDatabase{

	private HashMap<String,BukkitTask> bukkitTaskMap = null;
	private List<Thread>  threadList = null;
	
	private static ThreadStore instance = null;
	
	protected ThreadStore(){}
	
	public static  ThreadStore getInstance(){
		
		if(instance == null){
			 instance = new ThreadStore();
			 instance.clear();
		}
		
		return instance;
	}
	
	public boolean isThreadActiv(Thread thread){
		if(thread != null)
				return true;
		
		return false;
	}
	
	
	public void closeAllThreads(){
	  try{
		for(Thread th : threadList){
			
			if(isThreadActiv(th)){
				 th.interrupt();
			}
			
			unregisterThread(th);
		}
	  }catch(ConcurrentModificationException e){}
	}
	
	
	public void registerBukkitTask(String Name,BukkitTask task){
		bukkitTaskMap.put(Name, task);
	}
	
	public void registerThread(String Name,Thread th){
		threadList.add(th);
	}
	
	public void unregisterThread(Thread th){
		
		int counter = 0;
		for(Thread thSave : threadList){
			 if(thSave.equals(th)){
				 threadList.remove(counter);
				 break;
			 }
			 
		   counter++;
		}
		
	}
	

	@Override
	public void clear() {
		this.bukkitTaskMap = new HashMap<String,BukkitTask>();
		this.threadList    = new ArrayList<Thread>();
	}
	
	
}
