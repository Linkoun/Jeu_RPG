package jeu;
import java.util.Scanner;

public class Warrior extends Character{
	private Skill[] WARRIOR_SKILLS={new Skill("Rest","Heal",20,80),new Skill("Charge","Attack",30,70)	};
	
	
	public Warrior(){
		super();
	}
	
	public Warrior(String name, int FOR,int DEX,int INT, int CON) throws ExceptionChar{
		super(name,FOR,DEX,INT,CON);
		if(this.getFOR()<this.getDEX()+10 || this.getDEX()+10<this.getINT()+10 || this.getINT()+10<this.getCON())
			throw new ExceptionChar("Conditions aren't respected.\nTip: FOR >= (DEX + 10) >= (INT + 10) >= CON");
		this.setVIT();
		this.setNbCap();
		for(int i=0;i<WARRIOR_SKILLS.length;i++){
			this.addSkills(WARRIOR_SKILLS[i]);
		}
	}
	
	public void init(){
		Scanner input= new Scanner(System.in);
		System.out.println("Choose a name");
		this.setName(input.next());
		do{
			System.out.println("Attribute your warrior's statistics\nTip :(FOR + DEX + INT + CON) <= (100 + EXP)\nTip :FOR >= (DEX + 10) >= (INT + 10) >= CON");
			super.init();
			if(this.getFOR()<this.getDEX()+10 || this.getDEX()+10<this.getINT()+10 || this.getINT()+10<this.getCON())
				System.out.println("Conditions aren't respected.");
		}while(this.getFOR()<this.getDEX()+10 || this.getDEX()+10<this.getINT()+10 || this.getINT()+10<this.getCON());

		this.setVIT();
		this.setNbCap();
		for(int i=0;i<WARRIOR_SKILLS.length;i++){
			this.addSkills(WARRIOR_SKILLS[i]);
		}
	}
	
	public String toString(){

		return "Warrior :\n" +super.toString();
	}
	
}
