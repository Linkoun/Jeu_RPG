package jeu;

public class Potion extends Ability{
	private int PUI;
	private int FAC;
	
	public Potion(String Name,int PUI,int FAC){
		super(Name);
		if(PUI+FAC==100 && PUI>=20 && FAC>=20){
			this.PUI=PUI;
			this.FAC=FAC;
		}

	}
	
	public Potion(Potion P){
		super(P);
		this.PUI=P.getPUI();
		this.FAC=P.getFAC();
	}
	
	public void setPotion(Character Char){
		this.setPBA((float)Char.getDEX()*this.getFAC()*3/10000);
		this.setEFF(Char.getFOR()*this.getPUI()/100);
	
		
	}
	
	public String toString(){
		return this.getName()+"\nPuissance :"+this.getPUI()+"\nFacilité :"+this.getFAC()+"\nProbabilité de réussite :"+this.getPBA()+"\nEfficacité :"+this.getEFF();
	}
	

	
	public int getPUI() {
		return PUI;
	}

	public int getFAC() {
		return FAC;
	}



}
