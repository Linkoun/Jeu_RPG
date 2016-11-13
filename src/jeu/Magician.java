package jeu;
import java.util.*;

public class Magician extends Character {
	private Skill[] MAGICIAN_SKILLS={new Skill("Firebolt","Attack",70,30),new Skill("Ice Armor","Defense",35,65)		};
	
	
	
	public Magician(){
		super();
		}
		
	public Magician(String name, int FOR,int DEX,int INT, int CON) throws ExceptionChar{
		super(name,FOR,DEX,INT,CON);
		if(this.getINT()<max( this.getFOR(),this.getDEX() )+15 || this.getCON()< max( this.getFOR(),this.getDEX())+15)
			throw new ExceptionChar("Conditions aren't respected.\nTip: INT >= max(FOR, DEX) + 15\nCON >= max(FOR, DEX) + 15");
		this.setVIT();
		this.setNbCap();
		for(int i=0;i<MAGICIAN_SKILLS.length;i++){
			this.addSkills(MAGICIAN_SKILLS[i]);
		}
	}
	
	
	public void init(){
			Scanner input= new Scanner(System.in);
			System.out.println("Choose a name");
			this.setName(input.next());
			do{
				System.out.println("Attribute your magician's statistics\nTip : (FOR + DEX + INT + CON) <= (100 + EXP)\nTip :INT >= max(FOR, DEX) + 15\nTip :CON >= max(FOR, DEX) + 15		");
				super.init();
				if(this.getINT()<max( this.getFOR(),this.getDEX() )+15 || this.getCON()< max( this.getFOR(),this.getDEX())+15)
					System.out.println("Conditions aren't respected.");
			}while(this.getINT()<max( this.getFOR(),this.getDEX() )+15 || this.getCON()< max( this.getFOR(),this.getDEX())+15);
			
			this.setVIT();
			this.setNbCap();
			for(int i=0;i<MAGICIAN_SKILLS.length;i++){
				this.addSkills(MAGICIAN_SKILLS[i]);
			}
	}
	
	public String toString(){

		return "Magician :\n" +super.toString();
	}
	
}
