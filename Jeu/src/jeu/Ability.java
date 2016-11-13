package jeu;

import java.io.Serializable;

public class Ability implements Serializable {
	private String Name;
	private double PBA;
	private double EFF;
	
	public Ability(String Name){
		this.Name=Name;
	}
	
	public Ability(Ability A){
		this.Name=new String(A.getName());
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	
	public double getPBA() {
		return PBA;
	}
	public void setPBA(float pBA) {
		this.PBA = pBA;
	}
	public double getEFF() {
		return EFF;
	}
	public void setEFF(double eFF) {
		this.EFF = eFF;
	}

}
