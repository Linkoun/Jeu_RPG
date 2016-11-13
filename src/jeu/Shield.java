package jeu;

import java.util.*;

public class Shield extends Ability{
	private int PRO;
	private int MAN;
	private float PBA_D;
	private double EFF_D;
	private boolean ModeDefense;
	private boolean isWaiting;
	private boolean SortParé=false;
	private static Shield[] ShieldList={new Shield("Buckler",30,70),new Shield("Pavise",80,20),new Shield("Heater Shield",50,50)};
	
	public Shield(String Name,int PRO,int MAN){
		super(Name);
		if(PRO+MAN ==100 && PRO>=20 && MAN>=20){
			this.PRO=PRO;
			this.MAN=MAN;
		}
	}
	
	public Shield(Shield S){
		super(S);
		this.PRO=S.getPRO();
		this.MAN=S.getMAN();
	}
	
	public void setShield(Character Char){
		this.setPBA((float)Char.getDEX()*this.getMAN()/5000);
		this.PBA_D=((float)Char.getDEX()*this.getMAN()/10000);
			
		this.setEFF(Char.getFOR()*this.getPRO()/50);
		this.EFF_D=Char.getFOR()*this.getPRO()/100;	
	}

	
	public static int getShieldlength(){
		return Shield.ShieldList.length;
	}
	
	public static String ShieldList(){
		String s="";
		int j=0;
		for(int i=0;i<Shield.ShieldList.length;i++){
			s+=j+". "+Shield.ShieldList[i]+"\n";
			j++;
		}
		return s;
	}
	
	public static Shield getShieldList(int i){
		return Shield.ShieldList[i];
	}
	
	public String toString(){
		return this.getName()+"\nProtection :"+this.getPRO()+"\nManiabilité :"+this.getMAN();
	}
	
	public double getPBA(){
		if(this.isWaiting==true)
			return (super.getPBA())*0.75;
		else return super.getPBA();
	}
	public double getPBA_D() {
		if(this.isWaiting==true)
			return PBA_D*0.75;
		else return PBA_D;
	}

	public double getEFF(){
		if(this.SortParé==true){
			if(this.isWaiting==true)
				return (super.getEFF()/3)*0.75;
			else return super.getEFF()/3;
		}
		else{
			if(this.isWaiting==true)
				return super.getEFF()*0.75;
			else return super.getEFF();
			
		}
		
	}
	
	public double getEFF_D() {
		if(this.SortParé==true){
			if(this.isWaiting==true)
				return (EFF_D/3)*0.75;
			else return EFF_D/3;
		}
			
		else{
			if(this.isWaiting==true)
				return EFF_D*0.75;
			else return EFF_D;
		}
	}


	public int getPRO() {
		return PRO;
	}

	public int getMAN() {
		return MAN;
	}

	public boolean getSortParé() {
		return SortParé;
	}

	
	public boolean isModeDefense() {
		return ModeDefense;
	}

	public void setModeDefense(boolean modeDefense) {
		this.ModeDefense = modeDefense;
	}

	public void setSortParé(boolean sortParé) {
		this.SortParé = sortParé;
	}

	public boolean isWaiting() {
		return isWaiting;
	}

	public void setWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}
	
	
}
