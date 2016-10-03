package com.plugins.mutzii.api;

import com.plugins.mutzii.exceptions.InvalidCommandException;

public interface EvolutionCommandInterface {

	public void run() throws InvalidCommandException;
	
	public void showDescription();
}
