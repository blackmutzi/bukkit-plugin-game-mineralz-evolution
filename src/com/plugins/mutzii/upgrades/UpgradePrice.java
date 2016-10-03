package com.plugins.mutzii.upgrades;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.plugins.mutzii.enums.MineralzType;

public class UpgradePrice implements ConfigurationSerializable{

	private HashMap<MineralzType,String> mineralPrice = new HashMap<MineralzType,String>();

	public UpgradePrice(){}
	
	public void put(MineralzType mineralType, String content){
		this.mineralPrice.put(mineralType, content);
	}
	
	
	public int get(MineralzType type,int Level){
		String string =  mineralPrice.get(type);
		       string =  replaceLevel(string,Level);
		
		return (int) mathExpresion(string," ");
	}
	
	
	public int getMine(MineralzType type,int mineLevel){
		String string =  mineralPrice.get(type);
		       string =  replaceMineLevel(string,mineLevel);
		return (int) mathExpresion(string," ");
	}
	
	
	public HashMap<MineralzType,String> getPriceList(){
		return this.mineralPrice;
	}
	
	public String replaceLevel(String content,int level){
		return content.replace("%LEVEL%",String.valueOf(level));
	}
	
	public String replaceMineLevel(String content,int level){
		return content.replace("%MINELEVEL%",String.valueOf(level));
	}
	
	
	
	
    public double mathExpresion(String input,String regex){
    	
      String exp[] = input.split(regex);
      int expcounter;
      
      Stack<String> operator = new Stack<String>();
      int operatorCounter    = 0;
      
      Stack<Double> numbers  = new Stack<Double>();
      int numbersCounter     = 0;
      
      
      for(expcounter = 0; expcounter < exp.length; expcounter++){
    	  
    	  String currentExp = exp[expcounter];
    	  
    	  	if(isOperator(currentExp)){
    	  		operator.push(currentExp);
    	  		operatorCounter++;
    	  	}else{
    	  		numbers.push(Double.valueOf(currentExp));
    	  		numbersCounter++;
    	  	}  
      }
      
      		int defaultcounter;
      		for(defaultcounter = 0; defaultcounter < operatorCounter; defaultcounter++){
      			 
      			   String op = operator.pop();
      			   
      			   if(op.equals("+")){
      				   numbers.push(numbers.pop() + numbers.pop());
      			   }
      			   
      			   if(op.equals("*")){
      				   numbers.push(numbers.pop() * numbers.pop());
      			   }
      			
      		}
      
      
      	if(numbersCounter == 0){
      		numbers.push(Double.valueOf(input));
      	}
      		
       
     return numbers.pop();
    }
    
    
    public boolean isOperator(String exp){
    	
    	if(exp.equals("+") || exp.equals("*")){
    		return true;
    	}
    	
      return false;
    }
    
	

	@Override
	public Map<String, Object> serialize() {
		
		 Map<String, Object> result = new LinkedHashMap<String, Object>();
		 
		 for( Map.Entry<MineralzType,String> input : mineralPrice.entrySet()){
			 
			  result.put(input.getKey().toString(), input.getValue());
		 }
		 
		return result;
	}
	
}
