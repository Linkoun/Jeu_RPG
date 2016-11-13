package jeu;
import java.util.*;


public class Fight {
	private Character C1;
	private Character C2;
	private boolean T1attacking=false;
	private Character Surrender=null;
	private String s="";
	private boolean CombatFini=false;
	private Ability Action1=null;
	private Ability Action2=null;

/*	
	private int EFFT1=0;
	private int EFFT2=0;
	private Character J1=null;
	private Character J2=null;
	*/
	
	public Fight(Character C1,Character C2){
		this.C1=C1;
		this.C2=C2;
	}
	
	public Ability AttackWeapon(Character c){
		double EF;
		c.getWea().setModeDefense(false);
		EF=c.attackWeapon();
		if(EF != 0){
			s+=c.getName()+" : Successful Attack\n";
			return c.getWea();
		}
		else {
			s+=c.getName()+" : Attack missed\n";
			return null;
		}
	}
	
	public Ability DefendWeapon(Character c){
		double EF;
		EF=c.defendWeapon();
		if(EF != 0){
			c.getWea().setModeDefense(true);
			s+=c.getName()+" : Successful Parade\n";
			return c.getWea();
		}
		else{
			s+=c.getName()+" : Parade missed\n";
			return null;
		}
	}
	
	public Ability AttackShield(Character c){
		double EF;
		c.getShi().setModeDefense(false);
		EF=c.attackShield();
		if(EF != 0){
			s+=c.getName()+" : Successful Attack\n";
			return c.getShi();
		}
		else {
			s+=c.getName()+" : Attack missed\n";
			return null;
		}
	}
	
	public Ability DefendShield(Character c){
		double EF;
		
		EF=c.defendShield();
		if(EF != 0){
			c.getShi().setModeDefense(true);
			s+=c.getName()+" : Successful Parade\n";
			return c.getShi();
		}
		else {
			s+=c.getName()+" : Parade missed\n";
			return null;
		}
	}
	
	public Ability UseSkill(Character c,int i){
		double EF;
		EF=c.useSkill(i);
		if(EF !=0){
			s+=c.getName()+" : Skill used.\n";
			return c.getSkills().get(i);
		}
		else{
			s+=c.getName()+" : Skill failed.\n";
			return null;
		}
	}
	
	public Ability UsePot(Character c){
		double EF;
		EF=c.usePotion();
		if(EF!=0){
			s+=c.getName()+" : Potion used.\n";
			return c.getPot();
		}
		else{
			s+=c.getName()+" : Potion failed.\n";
			return null;
		}
	}
	// Controle Automatique du personnage
	public Ability ActionAuto(Character c){
		double EF;
		double rand;
		
		if(!this.T1attacking){
			do{
				rand=Math.random();
				
			}while(rand>0.66);
		}
		else rand=Math.random();
		if(rand<=0.50){
			if(Math.random()<=0.50){
				c.getWea().setModeDefense(false);
				EF=c.attackWeapon();
				if(EF != 0){
					s+=c.getName()+" : Successful Attack\n";
					return c.getWea();
				}
				else {
					s+=c.getName()+" : Attack missed\n";
					return null;
				}
			}
			else{
				c.getShi().setModeDefense(false);
				EF=c.attackShield();
				if(EF != 0){
					s+=c.getName()+" : Successful Attack\n";
					return c.getShi();
				}
				else {
					s+=c.getName()+" : Attack missed\n";
					return null;
				}
			}
		}
		if(rand>0.50 && rand<=0.60){
			EF=c.usePotion();
			if(EF!=0){
				s+=c.getName()+" : Potion used.\n";
				return c.getPot();
			}
			else{
				s+=c.getName()+" : Potion failed.\n";
				return null;
			}
		}
		if(rand>0.60 && rand<=0.66){
			this.Escape(c);
		}
		if(rand>0.66){
			if(Math.random()<=0.50){
				c.getWea().setModeDefense(true);
				EF=c.defendWeapon();
				if(EF != 0){
					s+=c.getName()+" : Successful Parade\n";
					return c.getWea();
				}
				else{
					s+=c.getName()+" : Parade missed\n";
					return null;
				}
			}
			else{
				c.getShi().setModeDefense(true);
				EF=c.defendShield();
				if(EF != 0){
					s+=c.getName()+" : Successful Parade\n";
					return c.getShi();
				}
				else {
					s+=c.getName()+" : Parade missed\n";
					return null;
				}
			}
		}
		return null;
	}
	
	public String Escape(Character c){
		this.CombatFini=true;
		this.Surrender=c;
		return c.getName()+" escaped.\n";
	}
	// Permet de savoir qui va commencer
	public int setFight(){
		C1.setCharacter();
		C2.setCharacter();
		C1.setHP(C1.getVIT());
		C2.setHP(C2.getVIT());
		if(C1.getEXP()>C2.getEXP())
			return 0;
		else if(C1.getEXP()<C2.getEXP())
			return 1;
		else if(C1.getEXP()==C2.getEXP()){
			double i;
			double j;
			System.out.println("Tirage au sort");
			do{
				i=Math.random();
				j=Math.random();
			}while(i==j);
			if(i>j)
				return 0;
			else
				return 1;
		}
		return 0;
	}
	//Réinitialise un tour de jeu
	public void resetTurn(){
		T1attacking=false; 
		C1.getShi().setWaiting(false);
		C2.getShi().setWaiting(false);
		C1.getWea().setWaiting(false);
		C2.getWea().setWaiting(false);
		this.Action1=null;
		this.Action2=null;
	}
	// Effectue les conséquences du combat
	public String FightProgress(int ite){
		if(ite==0){
			if(((Action1 instanceof Weapon && !(((Weapon) Action1).isModeDefense())) || (Action1 instanceof Shield && !(((Shield)Action1).isModeDefense())) ||( Action1 instanceof Skill && ((Skill)Action1).getType().equals("Attack")))){
				if(Action2 instanceof Weapon && ((Weapon)Action2).isModeDefense() || (Action2 instanceof Shield && ((Shield)Action2).isModeDefense() || (Action2 instanceof Skill && ((Skill)Action2).getType().equals("Defense")))){
					if(Action2.getEFF()>Action1.getEFF())
						Action2.setEFF(Action1.getEFF());
					C2.setHP(C2.getHP()-(Action1.getEFF()-Action2.getEFF()));
					s+=C1.getName()+" dealt "+ Action1.getEFF()+" damage.\n";
					s+=C2.getName()+" reduced "+ Action2.getEFF()+" damage\n";
					if(Action1 instanceof Skill && Action2 instanceof Weapon && C2.getWea().getSortParé()==false){
						((Weapon)Action2).setSortParé(true);
						s+=C2.getName()+"'s weapon is broken.\n";
					}
					else if(Action1 instanceof Skill && Action2 instanceof Shield && C2.getShi().getSortParé()==false){
						((Shield)Action2).setSortParé(true);
						s+=C2.getName()+"'s shield is broken.\n";
					}
				}
				else{
					C2.setHP(C2.getHP()-(Action1.getEFF()));
					s+=C1.getName()+" dealt "+ Action1.getEFF()+" damage.\n";
				}		 	
			}		
			else if(( Action1 instanceof Potion || (Action1 instanceof Skill && ((Skill)Action1).getType().equals("Heal")))){
				if(C1.getHP()>0){
					C1.setHP(C1.getHP()+Action1.getEFF());
					if(C1.getHP()>C1.getVIT()){			
						s+=C1.getName()+" recovered "+(Action1.getEFF()-(C1.getHP()-C1.getVIT()))+" HP.\n";
						C1.setHP(C1.getVIT());
					}
					else{
						s+=C1.getName()+" recovered "+Action1.getEFF()+" HP.\n";
					}
				}
			}
			if(((Action2 instanceof Weapon && !(((Weapon) Action2).isModeDefense())) || (Action2 instanceof Shield && !(((Shield)Action2).isModeDefense())) ||(Action2 instanceof Skill && ((Skill)Action2).getType().equals("Attack")))){
				C1.setHP(C1.getHP()-Action2.getEFF());
				s+=C2.getName()+" dealt "+ Action2.getEFF()+" damage. \n";
			}
			else if(( Action2 instanceof Potion || (Action2 instanceof Skill && ((Skill)Action2).getType().equals("Heal")))){
				if(C2.getHP()>0){
					C2.setHP(C2.getHP()+Action2.getEFF());
					if(C2.getHP()>C2.getVIT()){	
						s+=C2.getName()+" recovered "+(Action2.getEFF()-(C2.getHP()-C2.getVIT()))+" HP.\n";
						C2.setHP(C2.getVIT());
					}
					else{
						s+=C2.getName()+" recovered "+Action2.getEFF()+" HP.\n";
					}
				}
			}	
		}		
		else if(ite==1){
			if(((Action2 instanceof Weapon && !(((Weapon) Action2).isModeDefense())) || (Action2 instanceof Shield && !(((Shield)Action2).isModeDefense())) ||(Action2 instanceof Skill && ((Skill)Action2).getType().equals("Attack")))){
				if(Action1 instanceof Weapon && ((Weapon)Action1).isModeDefense() || (Action1 instanceof Shield && ((Shield)Action1).isModeDefense() || (Action1 instanceof Skill && ((Skill)Action1).getType().equals("Defense")))){
					if(Action1.getEFF()>Action2.getEFF())
						Action1.setEFF(Action2.getEFF());
					C1.setHP(C1.getHP()-(Action2.getEFF()-Action1.getEFF()));
					s+=C2.getName()+" dealt "+ Action2.getEFF()+" damage.\n";
					s+=C1.getName()+" reduced "+ Action1.getEFF()+" damage.\n";
					if(Action2 instanceof Skill && Action1 instanceof Weapon && C1.getWea().getSortParé()==false ){
						((Weapon)Action1).setSortParé(true);
						s+=C2.getName()+"'s weapon is broken.\n";
					}
					else if(Action2 instanceof Skill && Action1 instanceof Shield  && C1.getShi().getSortParé()==false){		
						((Shield)Action1).setSortParé(true);
						s+=C2.getName()+"'s shield is broken.\n";
					}
				}
				else{
					C1.setHP(C1.getHP()-(Action2.getEFF()));
					s+=C2.getName()+" dealt "+ Action2.getEFF()+" damage.\n";
				}
			}
			else if(( Action2 instanceof Potion || (Action2 instanceof Skill && ((Skill)Action2).getType().equals("Heal")))){
				if(C2.getHP()>0){
					C2.setHP(C2.getHP()+Action2.getEFF());
					if(C2.getHP()>C2.getVIT()){				
						s+=C2.getName()+" recovered "+(Action2.getEFF()-(C2.getHP()-C2.getVIT()))+" HP.\n";
						C2.setHP(C2.getVIT());
					}
					else 
						s+=C2.getName()+" recovered "+Action2.getEFF()+" HP.\n";
				}
			}
			
			if(((Action1 instanceof Weapon && !(((Weapon) Action1).isModeDefense())) || (Action1 instanceof Shield && !(((Shield)Action1).isModeDefense())) ||( Action1 instanceof Skill && ((Skill)Action1).getType().equals("Attack")))){
				C2.setHP(C2.getHP()-Action1.getEFF());
				s+=C1.getName()+" dealt "+ Action1.getEFF()+" damage.\n";
			}
			else if(( Action1 instanceof Potion || (Action1 instanceof Skill && ((Skill)Action1).getType().equals("Heal")))){
				if(C1.getHP()>0){
					C1.setHP(C1.getHP()+Action1.getEFF());
					if(C1.getHP()>C1.getVIT()){
					s+=C1.getName()+" recovered "+(Action1.getEFF()-(C1.getHP()-C1.getVIT()))+" HP.\n";
					C1.setHP(C1.getVIT());
				}
				else 
					s+=C1.getName()+" recovered "+Action1.getEFF()+" HP.\n";
				}
			}
		}
		if(C1.getHP()<=0 || C2.getHP()<=0){
			CombatFini=true;
		}
		return s;
	}
	// Effectue des changement selon l'issue du combat
	public Character Winner(){
		if(C1.getHP()<0 || Surrender==C1){
			C2.LevelUp();
			C1.LevelDown();
			C1.setNbCap();
			C2.setNbCap();
			return C2;
		}
		else if(C2.getHP()<0 || Surrender==C2){
			C1.LevelUp();
			C2.LevelDown();
			C1.setNbCap();
			C2.setNbCap();
			return C1;
		}
		return null;
	}
	
/*	
	public void StartFight(){
		C1.setCharacter();
		C2.setCharacter();
		boolean CombatFini=false;
		Character J1=null;
		Character J2=null;
		if(C1.getEXP()>C2.getEXP()){
			J1=this.C1;
			J2=this.C2;
		}
		
		else if(C1.getEXP()<C2.getEXP()){
			J1=this.C2;
			J2=this.C1;
		}
		
		else if(C1.getEXP()==C2.getEXP()){
			double i;
			double j;
			System.out.println("Tirage au sort");
			do{
				i=Math.random();
				j=Math.random();
			}while(i==j);
			
			if(i>j){
				J1=this.C1;
				J2=this.C2;

			}
			else{
				J1=this.C2;
				J2=this.C1;

			}
		}
		int HP1=J1.getVIT();
		int HP2=J2.getVIT();
		

		Ability Action1=null;
		Ability Action2=null;

		int ite=0;
	

		do{
			System.out.println("Joueur 1 :"+J1.getName()+" HP:"+HP1+"/"+J1.getVIT());
			System.out.println("Joueur 2 :"+J2.getName()+" HP:"+HP2+"/"+J2.getVIT());
			System.out.println("\nBeginning of the round\n.");
			this.EFFT1=0;
			this.EFFT2=0;
			T1attacking=false; 
			J1.getShi().setWaiting(false);
			J2.getShi().setWaiting(false);
			if(ite==0){
				if(this.Surrender==null){
					System.out.println(J1.getName()+"'s turn");
					Action1=this.Action(J1,1);
				}
				if(this.Surrender==null){	
					System.out.println(J2.getName()+"'s turn");
					Action2=this.Action(J2,2);
				}

				if(this.Surrender==null){
					if(this.EFFT1!=0 && ((Action1 instanceof Weapon && !(((Weapon) Action1).isModeDefense())) || (Action1 instanceof Shield && !(((Shield)Action1).isModeDefense())) ||( Action1 instanceof Skill && ((Skill)Action1).getType().equals("Attack")))){
						if(Action2 instanceof Weapon && ((Weapon)Action2).isModeDefense() || (Action2 instanceof Shield && ((Shield)Action2).isModeDefense() || (Action2 instanceof Skill && ((Skill)Action2).getType().equals("Defense")))){
							if(Action2.getEFF()>Action1.getEFF())
								Action2.setEFF(Action1.getEFF());
							HP2=HP2-(Action1.getEFF()-Action2.getEFF());
							System.out.println(J1.getName()+" dealt "+ Action1.getEFF()+" damage.");
							System.out.println(J2.getName()+" reduced "+ Action2.getEFF()+" damage");
							if(Action1 instanceof Skill && Action2 instanceof Weapon && J2.getWea().getSortParé()==false){
								((Weapon)Action2).setSortParé(true);
								System.out.println(J2.getName()+"'s weapon is broken.");
							}
							else if(Action1 instanceof Skill && Action2 instanceof Shield && J2.getShi().getSortParé()==false){
								((Shield)Action2).setSortParé(true);
								System.out.println(J2.getName()+"'s shield is broken.");
							}
						}
						else{
							HP2=HP2-(Action1.getEFF());
							System.out.println(J1.getName()+" dealt "+ Action1.getEFF()+" damage.");
						}
						 	
					}
					
					else if(this.EFFT1!=0 &&( Action1 instanceof Potion || (Action1 instanceof Skill && ((Skill)Action1).getType().equals("Heal")))){
						if(HP1>0){
							HP1+=Action1.getEFF();
							if(HP1>J1.getVIT()){
								
								System.out.println(J1.getName()+" recovered "+(Action1.getEFF()-(HP1-J1.getVIT()))+" HP.");
								HP1=J1.getVIT();
							}
							else{
						
							System.out.println(J1.getName()+" recovered "+Action1.getEFF()+" HP.");
						
							}
						}
					}
					if(this.EFFT2!=0 && ((Action2 instanceof Weapon && !(((Weapon) Action2).isModeDefense())) || (Action2 instanceof Shield && !(((Shield)Action2).isModeDefense())) ||(Action2 instanceof Skill && ((Skill)Action2).getType().equals("Attack")))){
						HP1=HP1-Action2.getEFF();
						System.out.println(J2.getName()+" dealt "+ Action2.getEFF()+" damage. ");
					}
					else if(this.EFFT2!=0 &&( Action2 instanceof Potion || (Action2 instanceof Skill && ((Skill)Action2).getType().equals("Heal")))){
						if(HP2>0){
							HP2+=Action2.getEFF();
							if(HP2>J2.getVIT()){
								
								System.out.println(J2.getName()+" recovered "+(Action2.getEFF()-(HP2-J2.getVIT()))+" HP.");
								HP2=J2.getVIT();
							}
							else{
						
							System.out.println(J2.getName()+" recovered "+Action2.getEFF()+" HP.");
							}
						}
					}	
				}
				else
					CombatFini=true;
			}
			
			
			else if(ite==1){
				if(this.Surrender==null){
					System.out.println(J2.getName()+"'s turn");
					Action2=this.Action(J2,1);
					}
				if(this.Surrender==null){
					System.out.println(J1.getName()+"'s turn");
					Action1=this.Action(J1,2);
				}	
				if(this.Surrender==null){
					if(this.EFFT1!=0 && ((Action2 instanceof Weapon && !(((Weapon) Action2).isModeDefense())) || (Action2 instanceof Shield && !(((Shield)Action2).isModeDefense())) ||(Action2 instanceof Skill && ((Skill)Action2).getType().equals("Attack")))){
						if(Action1 instanceof Weapon && ((Weapon)Action1).isModeDefense() || (Action1 instanceof Shield && ((Shield)Action1).isModeDefense() || (Action1 instanceof Skill && ((Skill)Action1).getType().equals("Defense")))){
							if(Action1.getEFF()>Action2.getEFF())
								Action1.setEFF(Action2.getEFF());
							HP1=HP1-(Action2.getEFF()-Action1.getEFF());
							System.out.println(J2.getName()+" dealt "+ Action2.getEFF()+" damage.");
							System.out.println(J1.getName()+" reduced "+ Action1.getEFF()+" damage");
							if(Action2 instanceof Skill && Action1 instanceof Weapon && J1.getWea().getSortParé()==false ){
								((Weapon)Action1).setSortParé(true);
								System.out.println(J2.getName()+"'s weapon is broken.");
							}
							else if(Action2 instanceof Skill && Action1 instanceof Shield  && J1.getShi().getSortParé()==false){		
								((Shield)Action1).setSortParé(true);
								System.out.println(J2.getName()+"'s shield is broken.");
							}
						}
						else{
							HP1=HP1-(Action2.getEFF());
							System.out.println(J2.getName()+" dealt "+ Action2.getEFF()+" damage.");
						}
					}
					else if(this.EFFT1!=0 &&( Action2 instanceof Potion || (Action2 instanceof Skill && ((Skill)Action2).getType().equals("Heal")))){
						if(HP2>0){
							HP2+=Action2.getEFF();
							if(HP2>J2.getVIT()){				
								System.out.println(J2.getName()+" recovered "+(Action2.getEFF()-(HP2-J2.getVIT()))+" HP.");
								HP2=J2.getVIT();
							}
							else System.out.println(J2.getName()+" recovered "+Action2.getEFF()+" HP.");
						}
					}
					if(this.EFFT2!=0 && ((Action1 instanceof Weapon && !(((Weapon) Action1).isModeDefense())) || (Action1 instanceof Shield && !(((Shield)Action1).isModeDefense())) ||( Action1 instanceof Skill && ((Skill)Action1).getType().equals("Attack")))){
						HP2=HP2-Action1.getEFF();
						System.out.println(J1.getName()+" dealt "+ Action1.getEFF()+" damage. ");
					}
					else if(this.EFFT2!=0 &&( Action1 instanceof Potion || (Action1 instanceof Skill && ((Skill)Action1).getType().equals("Heal")))){
						if(HP1>0){
							HP1+=Action1.getEFF();
							if(HP1>J1.getVIT()){
								System.out.println(J1.getName()+" recovered "+(Action1.getEFF()-(HP1-J1.getVIT()))+" HP.");
								HP1=J1.getVIT();
							}
							else System.out.println(J1.getName()+" recovered "+Action1.getEFF()+" HP.");
						}
					}
				}
				else CombatFini=true;
			}
			ite++;
			if(ite==2)
				ite=0;
			System.out.println("\nEnd of the round\n");
			if(HP1<0 || HP2<0){
				System.out.println("\nEnd of the fight\n");
				CombatFini=true;
			}
		}while(CombatFini==false);
		
		Scanner input=new Scanner(System.in);
		String s;
		boolean OK=false;
		int i=-1;
		if(HP1<0 || this.Surrender.equals(J1)){
			System.out.println(J1.getName()+" lost.");
			System.out.println(J2.getName()+" is the winner.");
			J2.LevelUp();
			J1.LevelDown();
			J1.setNbCap();
			J2.setNbCap();
			if(!(J1.getSkills().size()<=J1.getNbCap())){
				do{
					System.out.println(J1.getName()+" : You have to remove one of your's skill");
					System.out.println(J1.SkillList());
					i=input.nextInt();
				}while(i<0 || J1.SkillList().length()<i);
				J1.getSkills().remove(i);
			}
			if(this.Surrender.equals(J1)){
				do{	
					if(!(J2.getSkills().size()<J2.getNbCap())){
						System.out.println("Do you want to replace one of your skill ? (Y/N)");
					}
					else
						System.out.println("Do you want to copy one of your opponent's skill ?(Y/N)");
					s=input.next();
				}while(!(s.equals("Y") || s.equals("N")));
				if(s.equals("Y")){
					if((J2.getSkills().size()<J2.getNbCap())){
						do{
							try{
								System.out.println(J2.SkillList());
								System.out.println("Which one do you want to remove ?");
								i=input.nextInt();
							}
							catch(InputMismatchException e){}
						}while(i<0 || J1.SkillList().length()<i);
						J1.getSkills().remove(i);
					}
					do{
						try{
							System.out.println(J1.SkillList());	
							System.out.println("Which one do you want to take ?");
							i=input.nextInt();
							OK=true;
						}
						catch(InputMismatchException e){}
						if(J2.getSkills().contains((Skill)J1.getSkills().get(i)) ){
							System.out.println("You already got it");
							OK=false;
						}
					}while(i<0 || J1.SkillList().length()<i || !(OK));
					J2.addSkills(new Skill(J1.getSkills().get(i)));
				}
			}
		}
		else if(HP2<0 || this.Surrender.equals(J2)){
			System.out.println(J2.getName()+" lost.");
			System.out.println(J1.getName()+" is the winner.");
			J1.LevelUp();
			J2.LevelDown();
			J1.setNbCap();
			J2.setNbCap();	
			if(this.Surrender.equals(J2)){
				do{	
					if(!(J1.getSkills().size()<J1.getNbCap())){
						System.out.println(J1.getName()+" : Do you want to replace one of your skill ? (Y/N)");
					}
					else
						System.out.println(J1.getName()+" : Do you want to copy one of your opponent's skill ?(Y/N)");
					s=input.next();
				}while(!(s.equals("Y") || s.equals("N")));
				if(s.equals("Y")){
					if(!(J1.getSkills().size()<J1.getNbCap())){
						do{
							try{
								System.out.println(J1.SkillList());
								System.out.println("Which one do you want to remove ?");
								i=input.nextInt();
							}
							catch(InputMismatchException e){}
						}while(i<0 || J1.SkillList().length()<i);
						J1.getSkills().remove(i);
					}
					do{
						try{
							System.out.println(J2.SkillList());	
							System.out.println("Which one do you want to take ?");
							i=input.nextInt();
							OK=true;
						}
						catch(InputMismatchException e){
						}
						if(J1.getSkills().contains((Skill)J2.getSkills().get(i))){
							System.out.println("You already got it");
							OK=false;
						}
					}while(i<0 || J2.SkillList().length()<i || !(OK) );
					J1.addSkills(new Skill(J2.getSkills().get(i)));
				}
			}
			if(!(J2.getSkills().size()<=J2.getNbCap())){
				do{
					System.out.println(J2.getName()+" : You have to remove one of your's skill");
					System.out.println(J2.SkillList());
					i=input.nextInt();
				}while(i<0 || J2.SkillList().length()<i);
				J2.getSkills().remove(i);
			}
		}

		J2.getWea().setSortParé(false);
		J2.setVIT();
		J1.setVIT();
		J1.getShi().setWaiting(false);
		J2.getShi().setWaiting(false);
	}
	
	public Ability Action(Character J,int nj) throws InputMismatchException{
		Scanner input=new Scanner(System.in);
		String s=null;
		if(nj==2 && EFFT1!=0){
			do{
				System.out.println(J.getName()+",what do you want to do ?( Attack(A),Defend(D),use a potion(P), use a skill(S) or escape(E) )");
				s=input.next();
			}while(!(s.equals("A")|| s.equals("D") || s.equals("P") || s.equals("S") || s.equals("E")));
		}
		else{
			do{
				System.out.println(J.getName()+",what do you want to do ?( Attack(A),use a potion(P), use a skill(S) or escape(E) )");
				s=input.next();
			}while(!(s.equals("A") ||  s.equals("P") || s.equals("S") || s.equals("E")));
		}
		if(s.equals("A")){
			do{
				System.out.println("With your Weapon(W) or your Shield(S) ?");
				s=input.next();
			}while(!(s.equals("W") || s.equals("S")));
			if(s.equals("W")){
				if(nj==1){
					EFFT1=J.attackWeapon();
					if(EFFT1==0)
						return null;
				}
				else if(nj==2){
					EFFT2=J.attackWeapon();
					if(EFFT2==0)
						return null;
				}
				if(nj==1 && EFFT1!=0)
					T1attacking=true;
				J.getWea().setModeDefense(false);
				return J.getWea();
			}
			else if(s.equals("S")){
				if(nj==1){
					EFFT1=J.attackShield();
					if(EFFT1==0)
						return null;
				}
				else if(nj==2){
					EFFT2=J.attackShield();
					if(EFFT2==0)
						return null;
				}
				if(nj==1 && EFFT1!=0)
					T1attacking=true;
				J.getShi().setModeDefense(false);
				return J.getShi();
			}	
		}
		
		else if(s.equals("D")){
			do{
				System.out.println("Defend now(N) or wait and see the attack's damage first(W) ?");
				s=input.next();
				
			}while(!(s.equals("N") || s.equals("W")));
			if(s.equals("W")){
				System.out.println("The opponent will deal "+EFFT1+" damage.");
				J.getShi().setWaiting(true);
			}
			do{
				System.out.println("Defend with your weapon(W) or your shield(S) ? Or Cancel(C)");
				s=input.next();
			}while(!(s.equals("W") || s.equals("S") || s.equals("C")));
			if(s.equals("C"))
				this.Action(J, nj);
			if(s.equals("W")){
				J.getWea().setModeDefense(true);
				if(nj==1){
					EFFT1=J.defendWeapon();
					if(EFFT1==0)
						return null;
				}
				else if(nj==2){
					EFFT2=J.defendWeapon();
					if(EFFT2==0)
						return null;
				}
				return J.getWea();
					
			}
			
			else if(s.equals("S")){
				J.getShi().setModeDefense(true);
				if(nj==1){
					EFFT1=J.defendShield();
					if(EFFT1==0)
						return null;
				}
				else if(nj==2){
					EFFT2=J.defendShield();
					if(EFFT2==0)
						return null;
				}
				return J.getShi();
					
			}	
		}
		
		else if(s.equals("P")){
			if(nj==1){
				EFFT1=J.usePotion();
				if(EFFT1==0)
					return null;
			}
			else if(nj==2){
				EFFT2=J.usePotion();
				if(EFFT2==0)
					return null;
			}
			return J.getPot();
		}
		
		else if(s.equals("S")){
			int i;
			boolean ok=false;
			do{
				i=0;
				try{
					System.out.println("Which skill do you want to use ?");
					System.out.println(J.SkillList());
					i=input.nextInt();
					ok=true;
				}
				catch(InputMismatchException e){
					System.out.println("Erreur à la saisie.");
				}
				if(J.getSkills().get(i).getType().equals("Defense") &&( nj==1 || T1attacking==false)){
					System.out.println("You can't use a Defense Skill when you're starting the round or when the opponent isn't attacking");
				}
			}while(i<0 || i>J.getSkills().size() || (J.getSkills().get(i).getType().equals("Defense") && nj!=2) || ok==false);
			
			if(nj==1){
				EFFT1=J.useSkill(i);
				if(EFFT1==0)
					return null;
			}
			else if(nj==2){
				EFFT2=J.useSkill(i);
				if(EFFT2==0)
					return null;
			}
			if(J.getSkills().get(i).getType().equals("Attack"))
					if(nj==1 && EFFT1!=0)
						T1attacking=true;
				
			return J.getSkills().get(i);
		
			
		}
		else if(s.equals("E")){
			this.Surrender=J;
			return null;
		}

		return null;
		
	}	
	*/

	public Character getC1(){
		return C1;
	}

	public Character getC2(){
		return C2;
	}
	public void setAction1(Ability a){
		Action1=a;
	}
	public void setAction2(Ability a){
		Action2=a;
	}
	public void setCombatFini(boolean b){
		CombatFini=b;
	}
	public void resetS(){
		s="";
	}
	public Ability getAction1(){
		return Action1;
	}
	public Ability getAction2(){
		return Action2;
	}
	public boolean getT1attacking(){
		return this.T1attacking;
	}
	public void setT1attacking(boolean b){
		T1attacking=b;
	}
	public void setSurrender(Character c){
		Surrender=c;
	}
	
	public boolean getCombatFini(){
		return this.CombatFini;
	}
	
	public Character getSurrender(){
		return this.Surrender;
	}
}

