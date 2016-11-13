package jeu;
import java.util.*;

import jeu.Character;

import java.io.*;

public class Character implements Serializable{
	private String Name;
	private int EXP=1;
	private double HP;
	private int VIT;
	private int FOR;
	private int DEX;
	private int INT;
	private int CON;
	private int nbCap;
	private Weapon Wea;
	private Shield Shi;
	private Potion Pot= new Potion("Potion",50,50);
	private ArrayList<Skill> Skills;
	
	
	
	//Constructeur par defaut
	public Character(){

		this.EXP=1;
		this.Skills= new ArrayList<Skill>();
	}
	//Constructeur champ a champ
	Character(String name, int FOR,int DEX,int INT, int CON) throws ExceptionChar{
		this.Name=name;
		this.FOR=FOR;
		this.DEX=DEX;
		this.INT=INT;
		this.CON=CON;
		if(this.FOR+this.DEX+this.INT+this.CON> 100+this.EXP)
			throw new ExceptionChar("Too much points have heen given.Restart");
		if(this.FOR<1 || this.DEX<1 || this.INT<1 || this.CON<1)
			throw new ExceptionChar("Each statistic must be above 0.");
		this.Skills= new ArrayList<Skill>();
	}
	// Constructeur par copie
	public Character(Character C){
		this.Name=new String(C.Name);
		this.EXP=C.EXP;
		this.VIT=C.VIT;
		this.FOR=C.FOR;
		this.DEX=C.DEX;
		this.INT=C.INT;
		this.CON=C.CON;
		this.nbCap=this.nbCap;
		if(C.Wea!=null)
			this.Wea=new Weapon(C.Wea);
		if(C.Shi!=null)
			this.Shi=new Shield(C.Shi);
		if(C.Pot!=null)
			this.Pot=new Potion(C.Pot);
		this.Skills=C.Skills;
	}
	
	public void init(){
		Scanner input=new Scanner(System.in);
		do{
			System.out.println("FOR:");
			this.setFOR(input.nextInt());
			System.out.println("DEX:");
			this.setDEX(input.nextInt());
			System.out.println("INT:");
			this.setINT(input.nextInt());
			System.out.println("CON:");
			this.setCON(input.nextInt());
			if(this.FOR+this.DEX+this.INT+this.CON> 100+this.EXP)
				System.out.println("Too much points have heen given.Restart");
			if(this.FOR<1 || this.DEX<1 || this.INT<1 || this.CON<1)
				System.out.println("Each statistic must be above 0;");
		}while(this.FOR+this.DEX+this.INT+this.CON> 100+this.EXP || this.FOR<1 || this.DEX<1 || this.INT<1 || this.CON<1);

	}
	//Permet de sauvegarder un personnage
	public static void sauvegarder(String s,Character C) {
		
		try{
			FileOutputStream fos=new FileOutputStream(s);
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(C);
			oos.close();
			System.out.println("Character saved");
		}
		catch(IOException e){
			System.out.println("Save failed.");
		}
		
		
	
	}
	//Permet de charger un personnage
	public static Character charger(String s,Character C){
		
		try{
			FileInputStream fis=new FileInputStream(s);
			ObjectInputStream ois= new ObjectInputStream(fis);
			Object O=ois.readObject();
			C=(Character)O;
			ois.close();
			System.out.println("Character loaded");
		}
		catch(IOException e){
			System.out.println("Load failed");
		}
		catch(ClassNotFoundException e){
			System.out.println("Class not found");
		}
		return C;
		
	}
	
	//Prépare le personnage(vie,efficacité des armes ...)
	public void setCharacter(){
		this.setVIT();
		if(this.Wea!=null)Wea.setWeapon(this);
		if(this.Shi!=null)Shi.setShield(this);
		if(this.Pot!=null)Pot.setPotion(this);
		for(int i=0; i<Skills.size();i++){
			this.Skills.get(i).setSkill(this);
		
		}
		this.setNbCap();
	}
	//Augmenter un level
	public void LevelUp(){
		if(this.EXP<=20){
			this.EXP+=1;
			System.out.println(this.getName()+" : Your level is now "+this.getEXP()+", Congratulations!");
		}
		else
			System.out.println(this.getName()+" : Level max has been reached");
	}
	//Perdre un level
	public void LevelDown(){
		if(this.EXP>1){
			this.EXP-=1;
			System.out.println(this.getName()+" : Your level is now "+this.getEXP()+", Too bad!");
			if(this.getFOR()+this.getDEX()+this.getINT()+this.getCON()>100+this.getEXP())
				this.LoseStat();
		}
		else
			System.out.println(this.getName()+" : Your Level is already too low");
	}
	//Permet d'augmenter une statistique (Visu)
	public void UpgradeStat(Character Char,int i) throws Exception{
		int F;int D;int I;int C;
		F=Char.FOR;D=Char.DEX;I=Char.INT;C=Char.CON;
			if(i==1)
				F+=1;
			else if(i==2)
				D+=1;
			else if(i==3)
				I+=1;
			else if(i==4)
				C+=1;
			if(Char instanceof Magician){
				if((I<max( F,D )+15 || C<max( F,D)+15) || F<0 || D<0 || I<0 || C<0 || F+D+I+C>100+this.EXP)
					throw new Exception("Conditions aren't respected\nTip: INT >= max(FOR, DEX) + 15\nCON >= max(FOR, DEX) + 15\n points<=100+LVL");
			}
			else if(Char instanceof Warrior){
				if(F<0 || D<0 || I<0 || C<0 || F<D+10 || D+10<I+10 || I+10<C || F+D+I+C>100+this.EXP)
					throw new Exception("Conditions aren't respected\nTip: FOR >= (DEX + 10) >= (INT + 10) >= CON\n points<=100+LVL");
			}
			
			else if(Char instanceof Hunter){
				if(F<20 || D<20 || I<20 || C<20 || F+D+I+C>100+this.EXP)
					throw new Exception("Conditions aren't respected\nTip: Each statistics above 20\n points<=100+LVL");
			}
		Char.FOR=F;Char.DEX=D;Char.INT=I;Char.CON=C;
	}
	/*
	// Permet d'augmenter une statistique (Console)
	public void UpgradeStat(Character Char){
		String s=null;
		Scanner input=new Scanner(System.in);
		boolean OK=false;
		int F;
		int D;
		int I;
		int C;
		do{
			F=Char.FOR;
			D=Char.DEX;
			I=Char.INT;
			C=Char.CON;
			do{
				System.out.println("Which statistic do you want to upgrade ? Or Leave(L)");
				s=input.next();
				
			}while(!(s.equals("FOR") || s.equals("DEX") || s.equals("INT") || s.equals("CON") || s.equals("L")));
			if(s.equals("FOR"))
				F+=1;
			else if(s.equals("DEX"))
				D+=1;
			else if(s.equals("INT"))
				I+=1;
			else if(s.equals("CON"))
				C+=1;
			if(s.equals("L"))
				OK=true;
			else if(Char instanceof Magician){
				if((!(I<max( F,D )+15 || C< max( F,D)+15) && F>0 && D>0 && I>0 && C>0) && F+D+I+C<=100+this.EXP)
					OK=true;
			}
			
			else if(Char instanceof Warrior){
				if(F>0 && D>0 && I>0 && C>0 && !(F<D+10 || D+10<I+10 || I+10<C) && F+D+I+C<100+this.EXP)
					OK=true;
			}
			
			else if(Char instanceof Hunter){
				if(!(F<20 && D<20 && I<20 && C<20) && F+D+I+C<100+this.EXP)
					OK=true;
			}
			
			if(OK==false){
				System.out.println("Conditions aren't respected");
			}
		}while(OK==false);
		Char.FOR=F;Char.DEX=D;Char.INT=I;Char.CON=C;
	}*/
	// Effectue la perte des statistiques quand le nombre de points attribués passe sous la limite
	public void LoseStat(){
		boolean OK=false;
		int F;int D;int I;int C;
		do{
			F=this.FOR;D=this.DEX;I=this.INT;C=this.CON;
			double j=Math.random();
			System.out.println(j);
			if(j<=0.25)
				F-=1;
			else if(j>0.25 && j<=0.50)
				D-=1;
			else if(j>0.50 && j<=0.75)
				I-=1;
			else if(j>0.75)
				C-=1;
			if(this instanceof Magician){
				if((!(I<max( F,D )+15 || C< max( F,D)+15) && F>0 && D>0 && I>0 && C>0))
					OK=true;
			}
			
			if(this instanceof Warrior){
				if(!(F<=0 || D<=0 || I<=0 || C<=0 || F<D+10 || D+10<I+10 || I+10<C))
					OK=true;
			}
			
			if(this instanceof Hunter){
				if(!(F<20 || D<20 || I<20 || C<20))
					OK=true;
			}
		}while(OK==false);
		this.FOR=F;this.DEX=D;this.INT=I;this.CON=C;
	}
	// Donne le nombre le plus grand
	public int max(int st1, int st2){
		if(st1>st2)
			return st1;
		else return st2;
	}
	//Attaque avec L'arme
	public double attackWeapon(){
		if(Wea.getPBA()<Math.random()){
			System.out.println("Attack missed\n");
			return 0;
		}
		
		else{
			System.out.println("Successful Attack\n");
			return Wea.getEFF();
		}
	}
	//Attaque avec le bouclier
	public double attackShield(){
		if(Shi.getPBA()<Math.random()){
			System.out.println("Attack missed\n");
			return 0;
		}
		else{
			System.out.println("Successful Attack\n");
			return Shi.getEFF();
		}
		
	}
	// Défense avec l'arme
	public double defendWeapon(){
		if(Wea.getPBA_D()<Math.random()){
			System.out.println("Parade missed\n");
			return 0;
		}
		else{
			System.out.println("Successful Parade\n");
			return Wea.getEFF_D();
		}
	}
	
	//Défense au bouclier
	public double defendShield(){
		if(Shi.getPBA_D()<Math.random()){
			System.out.println("Parade missed\n");
			return 0;
		}
		else{
			System.out.println("Successful Parade\n");
			return Shi.getEFF_D();
		}
	}
	// Utilisation d'une potion
	public double usePotion(){
		if(Pot.getPBA()<Math.random()){
			System.out.println("Using potion(Fail)\n");
			return 0;
		}
		else{
			System.out.println("Potion used\n");
			return Pot.getEFF();
		}
	}
	
	// Utilisation d'une capacité
	public double useSkill(int i){
		if(this.Skills.get(i).getPBA()<Math.random()){
			System.out.println("Skill failed");
			return 0;
		}
		else{
			System.out.println("Skill("+this.Skills.get(i).getType() +") used");
			return this.Skills.get(i).getEFF();
		}
	}
	// Création du personnage (Console)
	public static Character createCharacter(){
		String s=null;
		Character Char=null;
		Scanner input= new Scanner(System.in);
		do{
			System.out.println("Saisir une classe.(Hunter,Magician,Warrior)");
			s=input.next();
			
		}while(!(s.equals("Hunter") || s.equals("Warrior") || s.equals("Magician")));
		do{
			Char=null;
			try{
				if(s.equals("Hunter"))
					Char= new Hunter();
				
				if(s.equals("Warrior"))
					Char=new Warrior();
			
				if(s.equals("Magician"))
					Char=new Magician();
				
				Char.init();
				System.out.println(Char);
			}
		
			catch(InputMismatchException e){
				System.out.println("Erreur à la saisie");
				return null;
			}
			
		}while(Char==null);
		
		return Char;
	}
		
	
	
	public String toString(){
		String s = "Name :"+this.Name+"\n EXP :"+this.EXP+"\n VIT :"+ this.VIT+"\n FOR :" +this.FOR + "\n DEX :" + this.DEX + "\n INT :"+this.INT+"\n CON :"+this.CON+"\nSkills :";
		ListIterator i= Skills.listIterator();
		while(i.hasNext())
			s+=i.next();

		return s;
	}
	// Classe qui donne les capacités des personnages
	public String SkillList(){
		String s="";
		int j=0;
		ListIterator i= Skills.listIterator();
		while(i.hasNext()){
			s+=j+". "+i.next();
			j++;
		}	
		return s;
	}
	// Classe qui permet de choisir une arme
	public void ChoseWeapon(){
		Scanner input=new Scanner(System.in);
		int i=0;
		do{
			try{
				System.out.println(Weapon.WeaponList());
				System.out.println("Which weapon do you want to use ?");
				i=input.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Erreur à la saisie.");
			}
		}while(i<0 || i>Weapon.getWeaponlength()-1);
		
	
		this.setWea(new Weapon(Weapon.getWeapon(i)));
	}
	// Classe qui permet de choisir un bouclier
	public void ChoseShield(){
		Scanner input=new Scanner(System.in);
		int i=0;
		do{
			try{
				System.out.println(Shield.ShieldList());
				System.out.println("Which shield do you want to use ?");
				i=input.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Erreur à la saisie.");
			}
		}while(i<0 || i>Shield.getShieldlength()-1);
		
	
		this.setShi(new Shield(Shield.getShieldList(i)));
	
	}
	
	
	// Mutateurs et Accesseurs

	public String getName() {
		return Name;
	}


	public void setName(String name) {
		this.Name = name;
	}


	public int getEXP() {
		return EXP;
	}

	public void setEXP(int eXP) {
		this.EXP = eXP;
	}

	public int getVIT() {
		return VIT;
	}

	public void setVIT() {
		this.VIT = 200-(this.getFOR()+this.getDEX()+this.getCON()+this.getINT())+this.getEXP()*3;
	}

	public int getFOR() {
		return FOR;
	}

	public void setFOR(int fOR) {
		this.FOR = fOR;
	}

	public int getDEX() {
		return DEX;
	}

	public void setDEX(int dEX) {
		this.DEX = dEX;
	}

	public int getINT() {
		return INT;
	}

	public void setINT(int iNT) {
		this.INT = iNT;
	}

	public int getCON() {
		return CON;
	}


	public void setCON(int cON) {
		this.CON = cON;
	}

	public int getNbCap() {
		return nbCap;
	}

	public void setNbCap() {
		this.nbCap = this.EXP/2;
		if(this.nbCap<2)
			this.nbCap=2;
	}

	
	public void addSkills(Skill S){
		this.Skills.add(S);
	}
	
	public ArrayList<Skill> getSkills(){
		return this.Skills;
	}

	public Weapon getWea() {
		return Wea;
	}

	public void setWea(Weapon wea) {
		Wea = wea;
	}

	public Shield getShi() {
		return Shi;
	}

	public void setShi(Shield shi) {
		this.Shi = shi;
	}

	public Potion getPot() {
		return Pot;
	}

	public void setPot(Potion pot) {
		this.Pot = pot;
	}
	
	public void setHP(double d){
		this.HP=d;
	}
	
	public double getHP(){
		return this.HP;
	}
	
	
	

}