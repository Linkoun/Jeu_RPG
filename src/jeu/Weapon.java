package jeu;

import java.util.*;

public class Weapon extends Ability {
	private int IMP;
	private int MAN;
	private double PBA_D;
	private double EFF_D;
	private boolean ModeDefense;
	private boolean isWaiting;
	private boolean SortParé=false;
	private static Weapon[] WeaponList={new Weapon("Sword",50,50),new Weapon("Dagger",20,80),new Weapon("Great Sword",70,30),new Weapon("Battle Axe",80,20),new Weapon("Rapier",30,70)};
	
	
	public Weapon(String Name,int IMP,int MAN){
		super(Name);
		if(IMP+MAN==100 && IMP>=20 && MAN>=20){
			this.IMP=IMP;
			this.MAN=MAN;
		}
		
	}
	
	public Weapon(Weapon W){
		super(W);
		this.IMP=W.getIMP();
		this.MAN=W.getMAN();
	}
	
	public void setWeapon(Character Char){
		this.setPBA((float)Char.getDEX()*this.getMAN()/10000);
		this.PBA_D=(float)Char.getDEX()*this.getMAN()/5000;
		this.setEFF(Char.getFOR()*this.getIMP()/100);
		this.EFF_D=Char.getFOR()*this.getIMP()/50;
		
	}
	
	
	public static String WeaponList(){
		String s="";
		int j=0;
	
		for(int i=0; i<WeaponList.length;i++){
			s+=j+". "+Weapon.WeaponList[i]+"\n";
			j++;
		}
		return s;
	}
	
	public static Weapon getWeapon(int i){
		return Weapon.WeaponList[i];
	}
	
	public static int getWeaponlength(){
		return Weapon.WeaponList.length;
	}
	
	
	public double getPBA(){
		if(this.isWaiting==true)
			return (double)(super.getPBA()*0.75);
		else return (double)(super.getPBA());
	}
	
	public double getPBA_D() {
		if(this.isWaiting==true)
			return (PBA_D*0.75);
		else
			return PBA_D;
	}



	public double getEFF_D() {
		if(this.SortParé==true){
			if(this.isWaiting==true)
				return (EFF_D/3)*0.75;
			else			
				return EFF_D/3;
		}
		else{
			if(this.isWaiting==true)
				return EFF_D*0.75;
			else
				return EFF_D;
		}
	}

	public double getEFF(){
		if(this.SortParé==true){
			
			if(this.isWaiting==true){
				return (super.getEFF()/3)*0.75;
			}
			else
				return super.getEFF()/3;
		}
		else
			if(this.isWaiting==true)
				return super.getEFF()*0.75;
			else
				return super.getEFF();
	}
	
	public int getIMP() {
		return IMP;
	}


	public int getMAN() {
		return MAN;
	}
	
	public boolean getSortParé() {
		return SortParé;
	}

	public void setSortParé(boolean sortParé) {
		this.SortParé = sortParé;
	}
	
	public boolean isModeDefense() {
		return ModeDefense;
	}

	public void setModeDefense(boolean modeDefense) {
		this.ModeDefense = modeDefense;
	}

	public String toString(){
		
		return this.getName()+"\nImpact :"+this.getIMP()+"\nManiabilité :"+this.getMAN();
	}

	public boolean isWaiting() {
		return isWaiting;
	}

	public void setWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}
	
	
	

}