package com.plugins.mutzii.threads;

public class Sleeper implements Runnable{

	private Thread lastThread;
	private int timer;
	
	public Sleeper(Thread lastThread, int timer){
		this.lastThread = lastThread;
		this.timer = timer;
	}
	
	@Override
	public void run() {
		
		try {
			
			Thread.sleep(this.timer);
			
			
        } catch (InterruptedException e) {
		} finally { // Sicherstellen, dass Parent geweckt wird
        
            synchronized (lastThread) {
            	lastThread.notify();
            }
            
        }
	}

}
