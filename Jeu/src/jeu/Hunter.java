package jeu;
import java.util.*;
public class Hunter extends Character{
	
	public Skill[] HUNTER_SKILLS = {new Skill("Fire Arrow","Attack",60,40), new Skill("Rapid Fire","Attack",30,70)  };
	
	public Hunter(){
		super();
	}
	
	public Hunter(String name, int FOR,int DEX,int INT, int CON) throws ExceptionChar{
		super(name,FOR,DEX,INT,CON);
		if(this.getFOR()<20 || this.getDEX()<20 || this.getINT()<20 || this.getCON()<20)
			throw new ExceptionChar("Conditions aren't respected.\nTip: Each statistics above 20");
		this.setVIT();
		this.setNbCap();
		for(int i=0;i<HUNTER_SKILLS.length;i++){
			this.addSkills(HUNTER_SKILLS[i]);
		}
	}
	
	public void init(){
		Scanner input= new Scanner(System.in);
		System.out.println("Choose a name");
		this.setName(input.next());
		do{
			System.out.println("Attribute your hunter's statistics\nTip : (FOR + DEX + INT + CON) <= (100 + EXP)\nTip : All Statistics >= 20");
			super.init();
			if(this.getFOR()<20 || this.getDEX()<20 || this.getINT()<20 || this.getCON()<20)
				System.out.println("Conditions aren't respected.");
		}while(this.getFOR()<20 || this.getDEX()<20 || this.getINT()<20 || this.getCON()<20);
		
		this.setVIT();
		this.setNbCap();
		for(int i=0;i<HUNTER_SKILLS.length;i++){
			this.addSkills(HUNTER_SKILLS[i]);
		}
	}
	
	public String toString(){
		return "Hunter :\n" +super.toString();
	}
	

}