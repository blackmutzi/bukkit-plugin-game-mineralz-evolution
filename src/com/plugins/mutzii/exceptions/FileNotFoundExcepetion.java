package com.plugins.mutzii.exceptions;

public class FileNotFoundExcepetion extends Exception{

	private static final long serialVersionUID = -4597227564957177376L;
	
	private String FileName;
	private String Message;
	
	public FileNotFoundExcepetion(String Message,String fileName){
		
		this.Message  = Message;
		this.FileName = fileName;
	}
	
	public String getMessage(){
		return this.Message;
	}
	
	public String getFileName(){
		return this.FileName;
	}


}
