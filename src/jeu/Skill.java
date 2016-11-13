package jeu;

public class Skill extends Ability {


	private String Type;
	private int PUI;
	private int FAC;

	
	public Skill(String Name,String Type,int PUI,int FAC){
		super(Name);
		this.Type=Type;
		if(PUI+FAC==100 && PUI>=20 && FAC>=20){
			this.PUI=PUI;
			this.FAC=FAC;
		}
	}
	
	public Skill(Skill S){
		super(S);
		this.Type=new String(S.getType());
		this.PUI=S.getPUI();
		this.FAC=S.getFAC();
	}
	
	public void setSkill(Character Char){
		this.setPBA((float)Char.getINT()*this.getFAC()/10000);
		this.setEFF(Char.getCON()*this.getPUI()/100);
	}

	public String toString(){
		return this.getName()+":\n  Puissance :"+this.getPUI()+"\n  Facilité :"+this.getFAC()+"\n";
	}
	
	public int getPUI() {
		return this.PUI;
	}
	
	public String getName(){
		return super.getName();
	}

	public void setPUI(int power) {
		this.PUI = power;
	}

	public String getType() {
		return this.Type;
	}

	public void setType(String type) {
		this.Type = type;
	}
	
	public int getFAC() {
		return this.FAC;
	}

	public void setFAC(int FAC) {
		this.FAC = FAC;
	}


	

}
