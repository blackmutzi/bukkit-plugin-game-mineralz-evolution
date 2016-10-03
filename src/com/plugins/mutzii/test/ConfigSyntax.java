package com.plugins.mutzii.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConfigSyntax {
	
	private Config c = null;
	
	public ConfigSyntax(){}
	List<Config> config = new ArrayList<Config>();
	
	
	public void readFile(String filename){
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String zeile = null;
			boolean next = false;
			while ((zeile = in.readLine()) != null) {
				
				if(createConfig(zeile)){
						next = true;
				}
				
				if(next == true){
					
					  if(!zeile.equalsIgnoreCase("}")){
						   setInput(zeile);
					   }else{
						   config.add(getConfig());
						   this.c = null;
						   next   = false;
					  }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Config getConfig(){
		return this.c;
	}
	
	public boolean createConfig(String currentZeile){
		
		Pattern pat = Pattern.compile("[^{]+");
		Matcher mat = pat.matcher(currentZeile);
		
		Config newConfig = new Config();
		
		while(mat.find())
		{
			newConfig.setCategorie(mat.group(0));
			this.c = newConfig;
			return true;
		}
		
	  return false;
	}
	
	
	public void setInput(String input){
		
		Config config = this.getConfig();
		
		Pattern pat = Pattern.compile("[^=]+");
		Matcher mat = pat.matcher(input);
		while(mat.find())
		{
			config.setContent(mat.group(1), mat.group(2));
			break;
		}
		
	}
	

	
	
	
	
	
	
	
	
}
