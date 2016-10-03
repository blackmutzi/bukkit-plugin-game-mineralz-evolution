package com.plugins.mutzii.other;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import com.plugins.mutzii.editor.Coordinaten;

public abstract class FieldExperiment {

	private World world;
	
	public enum POS{
		X_ACHSE,
		Y_ACHSE,
		Z_ACHSE
	}
	
	public FieldExperiment(World world){
		this.world = world;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	
	public List<Coordinaten> VectorCoordinatenList(Vector start, Vector ende){
		
		List<Coordinaten> list = new ArrayList<Coordinaten>();
		scan(start,ende,null,POS.X_ACHSE,new Vector(),list);
		return list;
		
	}
	
	public List<Coordinaten> FieldCoordinatenList(MineralzField field){
		
		List<Coordinaten> list = new ArrayList<Coordinaten>();
		Vector start = getStartVector(field);
		Vector ende  = getEndVector(field);
		scan(start,ende,null,POS.X_ACHSE,new Vector(),list);
		return list;
	}
	
	
	
	public Vector scan(Vector start, Vector end,POS lastposition,POS nextposition,Vector result,List<Coordinaten> list){
		
		int first_position   = 0;
		int second_position  = 0;
		POS nextnextposition = null;
		
		if(isLittleAs(getPosition(start,nextposition),getPosition(end,nextposition))){
				first_position   = getPosition(start,nextposition);
				second_position  = getPosition(end,nextposition);
		}
		
		if(isBigAs(getPosition(start,nextposition),getPosition(end,nextposition))){
				 first_position   = getPosition(end,nextposition);
				 second_position  = getPosition(start,nextposition);
		}
		
		if(isEqual(getPosition(start,nextposition),getPosition(end,nextposition))){
			    first_position   = getPosition(start,nextposition);
			    second_position  = getPosition(start,nextposition);
		}
		

		if(lastposition == POS.X_ACHSE){
			nextnextposition = POS.Y_ACHSE;
		}
		
		if(lastposition == POS.Y_ACHSE){
			nextnextposition = POS.Z_ACHSE;
		}
		
		if(lastposition == POS.Z_ACHSE){
			nextnextposition = null;
		}
		
				
		for(int n=first_position; n <= second_position; n++){
			
			if(nextnextposition == null){
				
				 result = setPosition(result,n,nextposition);
				
				 Location location = result.toLocation(getWorld());
				 Block block  = location.getBlock();
				 
				 if(block != null && block.getType() != Material.AIR){
					 list.add(new Coordinaten(block,location.getBlockX(),location.getBlockY(),location.getBlockZ()));
				 }
				
				
			}else{
				result = scan(start,end,nextposition,nextnextposition,setPosition(result,n,nextposition),list);
			}
		}
		
		return result;
	}
	
	
	public Vector setPosition(Vector vector,int amount, POS pos){
		
		if(pos == POS.X_ACHSE){
			 vector.setX(amount);
		}
		
		if(pos == POS.Y_ACHSE){
			 vector.setY(amount);
		}
		
		if(pos == POS.Z_ACHSE){
			  vector.setY(amount);
		}

		return vector;
	}
	
	public int getPosition(Vector vector,POS pos){
		
		if(pos == POS.X_ACHSE)
				return vector.getBlockX();
		
		if(pos == POS.Y_ACHSE)
				return vector.getBlockY();
		
		if(pos == POS.Z_ACHSE)
				return vector.getBlockZ();
		
		
	   return 0;
	}
	
	

	
	public static boolean isEqual(int x,int y){
		return x == y;
	}
	
	public static boolean isLittleAs(int x,int y){
		return x < y;
	}
	
	public static boolean isBigAs(int x,int y){
		return x > y;
	}
		
	
	
	public static Vector getEndVector(MineralzField field){
		Vector vector = new Vector();
		vector.setX(field.getEndX());
		vector.setY(field.getEndY());
		vector.setZ(field.getEndZ());
		return vector;
	}
	
	public static Vector getStartVector(MineralzField field){
		Vector vector = new Vector();
		vector.setX(field.getStartX());
		vector.setY(field.getStartY());
		vector.setZ(field.getStartZ());
		return vector;
	}
	
	
	public static MineralzField getMineralzField(Location start, Location end){
		
		MineralzField field = new MineralzField();
		
		field.setStartX(start.getBlockX());
		field.setStartY(start.getBlockY());
		field.setStartZ(start.getBlockZ());
		
		field.setEndX(end.getBlockX());
		field.setEndY(end.getBlockY());
		field.setEndZ(end.getBlockZ());
	
		return field;
	}
	
	
	public static MineralzField getMineralzField(Vector start, Vector end){
		
		MineralzField field = new MineralzField();
		
		field.setStartX(start.getBlockX());
		field.setStartY(start.getBlockY());
		field.setStartZ(start.getBlockZ());
		
		field.setEndX(end.getBlockX());
		field.setEndY(end.getBlockY());
		field.setEndZ(end.getBlockZ());
	
		return field;
	}
	
	
	
	public static boolean isVectorInVectorField(Vector start,Vector ende,Vector suche){
		return suche.isInAABB(start, ende);
	}
		
	public static boolean isVectorInField(MineralzField field,Vector vector){
		return vector.isInAABB(new Vector(field.getStartX(),field.getStartY(),field.getStartZ()), new Vector(field.getStartX(),field.getStartY(),field.getStartZ()));
	}
	
	public static boolean isLocationInField(MineralzField field,Location location){	
		Vector vec = new Vector(location.getX(),location.getY(),location.getZ());
		return vec.isInAABB(new Vector(field.getStartX(),field.getStartY(),field.getStartZ()), new Vector(field.getStartX(),field.getStartY(),field.getStartZ()));	
	}
	
	

}
