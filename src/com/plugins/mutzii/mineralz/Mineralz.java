package com.plugins.mutzii.mineralz;

import com.plugins.mutzii.api.MineralzInterface;
import com.plugins.mutzii.enums.MineralzType;
import com.plugins.mutzii.other.MineralzPosition;

public class Mineralz implements MineralzInterface {

	private int mineral_amount                = 0;
	private String mineral_name               = "Unknown";
	private MineralzType mineral_color        = MineralzType.MINERAL_CUSTOM;
	private MineralzPosition mineral_position = new MineralzPosition(0,0,0);
	
	public Mineralz(){}
	public Mineralz( int mineral_amount , String mineral_name )
	{
		this.mineral_amount = mineral_amount;
		this.mineral_name   = mineral_name;
	}
	
	public Mineralz( int mineral_amount , MineralzType mineral_color )
	{
		this.mineral_amount = mineral_amount;
		this.mineral_color  = mineral_color;
	}
	
	@Override
	public void setMineralzName(String mineral_name) {
		this.mineral_name = mineral_name;
	}
	@Override
	public String getMineralzName() {
		return this.mineral_name;
	}
	@Override
	public void setMineralzPosition(MineralzPosition position) {
		this.mineral_position = position;
	}
	@Override
	public MineralzPosition getMineralPosition() {
		return this.mineral_position;
	}
	@Override
	public void setMineralzColor(MineralzType color) {
		this.mineral_color = color;
	}
	@Override
	public MineralzType getMineralzColor() {
		return this.mineral_color;
	}
	@Override
	public void setMineralzAmount(int mineral_amount) {
		this.mineral_amount = mineral_amount;
	}
	@Override
	public int getMineralzAmount() {
		return this.mineral_amount;
	}

}
