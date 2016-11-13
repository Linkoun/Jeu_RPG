package jeu;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class FenJeu extends JFrame {
	public static final int LOAD=0,SAVE=1;
	private JButton DW,DS,Wait;
	private PanelChar panRight;
	private JRadioButton butFOR,butDEX,butINT,butCON;
	private JTextField Name,FOR,DEX,INT,CON;
	private Character character=null;
	private JTextArea SkillT=new JTextArea("");
	private JTextArea Cons=new JTextArea("");
	private JTextArea Weap=new JTextArea("");
	private JTextArea Shi=new JTextArea("");
	private JTextArea p1=new JTextArea("");
	private JTextArea p2=new JTextArea("");
	private Fight fight;
	private int ite;
	private JScrollPane ScrollP =new JScrollPane(Cons);
	
	//Crée la fenetre
	public FenJeu(String s, int x , int  y , int w, int h ){
		super(s);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(x,y,w,h);
		this.initialise();
		this.setJMenuBar(this.Menu());
		this.setVisible(true);
		this.Cons.setBorder(BorderFactory.createTitledBorder("Statistics"));
	}
	//Crée la barre de menu
	public JMenuBar Menu(){
		JMenuBar Menu = new JMenuBar();
		JMenu M1=new JMenu("File");
		JMenuItem m1= new JMenuItem("Quitter");
		M1.add(m1);
		Menu.add(M1);
		ButtonListener list = new ButtonListener();
		m1.addActionListener(list);
		return Menu;
	}
	
	public void initialise(){
		ScrollP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ScrollP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setContentPane(this.getPan());
		this.revalidate();
		this.Cons.setEditable(false);
		this.Shi.setBorder(BorderFactory.createTitledBorder("Shield"));
		this.Weap.setBorder(BorderFactory.createTitledBorder("Weapon"));
	}
	
	// La page de chargement et de sauvegarde de personnage
	public JPanel LoadP(int i){
		PanelImage pan= new PanelImage("FondStart.jpg");
		pan.setLayout(new BorderLayout());
		JPanel panLeft=new JPanel(new GridLayout(2,2,40,40));
		JPanel panRight=new JPanel(new BorderLayout());
		JButton load1=new JButton("Slot 1");
		JButton load2=new JButton("Slot 2");
		JButton load3=new JButton("Slot 3");
		JButton load4=new JButton("Slot 4");
		
		panLeft.add(load1);
		panLeft.add(load2);
		panLeft.add(load3);
		panLeft.add(load4);
	
		JPanel panel1=new JPanel(new BorderLayout());
		panel1.setPreferredSize(new Dimension(400,200));
		panel1.add(this.Cons, BorderLayout.CENTER);
		JPanel panel2=new JPanel(new BorderLayout());
		JButton conf=new JButton("Confirm");
		JButton back=new JButton("Back");
		if(i==LOAD){
			LoadListener list=new LoadListener();
			load1.addActionListener(list);
			load2.addActionListener(list);
			load3.addActionListener(list);
			load4.addActionListener(list);
			conf.addActionListener(list);
			back.addActionListener(list);
		}
		else if(i==SAVE){
			SaveListener liste=new SaveListener();
			load1.addActionListener(liste);
			load2.addActionListener(liste);
			load3.addActionListener(liste);
			load4.addActionListener(liste);
			conf.addActionListener(liste);
			back.addActionListener(liste);
	
		}
		conf.setPreferredSize(new Dimension(200,100));
		back.setPreferredSize(new Dimension(200,100));
		panel2.add(conf,BorderLayout.NORTH);
		panel2.add(back,BorderLayout.SOUTH);
		panRight.add(panel1, BorderLayout.CENTER);
		panRight.add(panel2,BorderLayout.SOUTH);
		panLeft.setOpaque(false);
		panel2.setOpaque(false);
		pan.add(panLeft,BorderLayout.CENTER);	
		pan.add(panRight, BorderLayout.EAST);
		return pan;
	}
	
	//La page principal 
	public JPanel MainP(){
		JPanel panMain=new JPanel(new BorderLayout());
		
		JPanel panEast=new JPanel(new BorderLayout());
		JPanel panEastCenter=new JPanel();
		JPanel panEastTop=new JPanel();
		JPanel panEastBot=new JPanel(new BorderLayout());
		JLabel CharacterIcon=null;
		if(character instanceof Hunter){
			CharacterIcon=new JLabel(new ImageIcon("ArcherIcon.png"));
		}
		if(character instanceof Warrior){
			CharacterIcon=new JLabel(new ImageIcon("WarriorIcon.png"));
		}
		if(character instanceof Magician){
			CharacterIcon=new JLabel(new ImageIcon("MagicianIcon.png"));
		}
		panEastTop.add(CharacterIcon);
		panEastCenter.setLayout(new BoxLayout(panEastCenter,BoxLayout.PAGE_AXIS));
		butFOR=new JRadioButton();
		butDEX=new JRadioButton();
		butINT=new JRadioButton();
		butCON=new JRadioButton();
		ButtonGroup stat=new ButtonGroup();
		stat.add(butFOR);stat.add(butDEX);stat.add(butINT);stat.add(butCON);
		JPanel panelName=new JPanel(new GridLayout(1,2,0,30));
		panelName.add(new JLabel("Name :"));panelName.add(new JLabel(character.getName()));
		JPanel panelEXP=new JPanel(new GridLayout(1,2,0,30));
		panelEXP.add(new JLabel("Level :"));panelEXP.add(new JLabel(Integer.toString(character.getEXP())));
		JPanel panelFOR=new JPanel(new GridLayout(1,3,0,10));
		panelFOR.add(new JLabel("FOR :"));panelFOR.add(new JLabel(Integer.toString(character.getFOR())));panelFOR.add(butFOR);
		JPanel panelDEX=new JPanel(new GridLayout(1,3,0,10));
		panelDEX.add(new JLabel("DEX :"));panelDEX.add(new JLabel(Integer.toString(character.getDEX())));panelDEX.add(butDEX);
		JPanel panelINT=new JPanel(new GridLayout(1,3,0,10));
		panelINT.add(new JLabel("INT :"));panelINT.add(new JLabel(Integer.toString(character.getINT())));panelINT.add(butINT);
		JPanel panelCON=new JPanel(new GridLayout(1,3,0,10));
		panelCON.add(new JLabel("CON :"));panelCON.add(new JLabel(Integer.toString(character.getCON())));panelCON.add(butCON);
		JButton butUpgrade=new JButton("Upgrade");
		
		panEastCenter.add(panelName);
		panEastCenter.add(panelEXP);
		panEastCenter.add(panelFOR);
		panEastCenter.add(panelDEX);
		panEastCenter.add(panelINT);
		panEastCenter.add(panelCON);
		butUpgrade.setPreferredSize(new Dimension(200,100));
		panEastBot.add(butUpgrade,BorderLayout.CENTER);
		panEast.add(panEastTop,BorderLayout.NORTH);
		panEast.add(panEastCenter,BorderLayout.CENTER);
		panEast.add(panEastBot,BorderLayout.SOUTH);
		PanelImage panCenter=new PanelImage("FondMain.jpg");
		JPanel panBot=new JPanel(new GridLayout(1,6,5,0));
		ButtonListener list=new ButtonListener();
		JButton butNew=new JButton("New Character"); butNew.setPreferredSize(new Dimension(100,100)); butNew.addActionListener(list);
		JButton butLoad=new JButton("Load Character");butLoad.addActionListener(list);
		JButton butSave=new JButton("Save Character");butSave.addActionListener(list);
		JButton butFight=new JButton("Fight");butFight.addActionListener(list);
		JButton butInv=new JButton("Inventory");butInv.addActionListener(list);
		JButton butSkill=new JButton("Show Skills");butSkill.addActionListener(list);
		butUpgrade.addActionListener(list);
		panBot.add(butNew);
		panBot.add(butLoad);
		panBot.add(butSave);
		panBot.add(butFight);
		panBot.add(butInv);
		panBot.add(butSkill);
		panBot.setBorder(BorderFactory.createTitledBorder("Actions"));
		panEast.setBorder(BorderFactory.createTitledBorder("Character"));
		
		panMain.add(panEast,BorderLayout.EAST);
		panMain.add(panCenter,BorderLayout.CENTER);
		panMain.add(panBot,BorderLayout.SOUTH);
		
		return panMain;
		
	}
	
	// La page de création de personnage
	public JPanel NewP(){
		PanelImage panel=new PanelImage("FondStart.jpg");
		panel.setLayout(new BorderLayout());
		this.Name=new JTextField("");
		this.FOR=new JTextField("");
		this.DEX=new JTextField("");
		this.INT=new JTextField("");
		this.CON=new JTextField("");
		JPanel panLeft=new JPanel();
		panLeft.setBorder(BorderFactory.createTitledBorder("Create Character"));
		panLeft.setLayout(new BoxLayout(panLeft,BoxLayout.PAGE_AXIS));
		JRadioButton Hunter= new JRadioButton("Hunter");
		JRadioButton Magician=new JRadioButton("Magician");
		JRadioButton Warrior= new JRadioButton("Warrior");
		ButtonGroup butg=new ButtonGroup();
		JButton conf=new JButton("Create");
		JButton back=new JButton("Back");
		JPanel panel1=new JPanel();
		JPanel panelName=new JPanel(new GridLayout(1,2,0,30));
		panelName.add(new JLabel("Name :"));panelName.add(this.Name);
		JPanel panelFOR=new JPanel(new GridLayout(1,2,0,30));
		panelFOR.add(new JLabel("FOR :"));panelFOR.add(this.FOR);
		JPanel panelDEX=new JPanel(new GridLayout(1,2,0,30));
		panelDEX.add(new JLabel("DEX :"));panelDEX.add(this.DEX);
		JPanel panelINT=new JPanel(new GridLayout(1,2,0,30));
		panelINT.add(new JLabel("INT :"));panelINT.add(this.INT);
		JPanel panelCON=new JPanel(new GridLayout(1,2,0,30));
		panelCON.add(new JLabel("CON :"));panelCON.add(this.CON);
		JPanel panelBut=new JPanel(new BorderLayout());
		JPanel panelEnt=new JPanel(new BorderLayout());
		butg.add(Hunter);
		butg.add(Magician);
		butg.add(Warrior);
		panel1.add(Hunter);
		panel1.add(Magician);
		panel1.add(Warrior);
		panLeft.add(panel1);
		panLeft.add(panelName);
		panLeft.add(panelFOR);
		panLeft.add(panelDEX);
		panLeft.add(panelINT);
		panLeft.add(panelCON);
		panelBut.add(conf,BorderLayout.NORTH);
		panelBut.add(back,BorderLayout.SOUTH);
		panelEnt.add(panelBut,BorderLayout.SOUTH);
		panLeft.add(panelEnt);
		panel.add(panLeft,BorderLayout.WEST);
		NewListener list= new NewListener();
		Hunter.addActionListener(list);
		Magician.addActionListener(list);
		Warrior.addActionListener(list);
		back.addActionListener(list);
		conf.addActionListener(list);
		back.setPreferredSize(new Dimension(400,100));
		conf.setPreferredSize(new Dimension(400,100));
		this.panRight=new PanelChar();
		panel.add(panRight, BorderLayout.CENTER);
		panRight.setOpaque(false);
		return panel;
	}
	// La page de départ ou l'on peut créer ou charger un personnage
	public JPanel getPan(){
		PanelImage pan=new PanelImage("FondStart.jpg");
		pan.setLayout(new BorderLayout());
		
	/*	JPanel panelTop=new JPanel();
		Icon sword=new ImageIcon("icon.png");
		JLabel Label=new JLabel(sword,JLabel.CENTER);

		panelTop.add(Label);*/
		PanelImage panelTop=new PanelImage("icon.png");
		panelTop.setPreferredSize(new Dimension(1000,400));

	
		JPanel panelBot= new JPanel(new GridLayout(2,1,0,20));
		JButton but1=new JButton("New Character");
		JButton but2=new JButton("Load Character");
		but1.setPreferredSize(new Dimension(300,100));
		panelBot.add(but1);
		panelBot.add(but2);
		JPanel panel=new JPanel();
		panel.add(panelBot);
		
		ButtonListener list=new ButtonListener();
		but1.addActionListener(list);
		but2.addActionListener(list);
		panel.setOpaque(false);
		panelBot.setOpaque(false);
		panelTop.setOpaque(false);
		
		pan.add(panel,BorderLayout.CENTER);
		pan.add(panelTop,BorderLayout.NORTH);
		return pan;
	}
	

	//La page d'inventaire ou l'on peut voir l'arme et le bouclier
	public JPanel InvP(){
		InventoryListener list=new InventoryListener();
		JPanel panel=new JPanel(new BorderLayout());
		PanelImage panCenter = new PanelImage("FondInv.png");
		panCenter.setLayout(new BorderLayout());
		JPanel panCenterBot=new JPanel();
		JButton back=new JButton("Back");
		back.setPreferredSize(new Dimension(200,100));
		panCenterBot.add(back);
		panCenterBot.setOpaque(false);
		panCenter.add(panCenterBot,BorderLayout.SOUTH);
		back.addActionListener(list);
		
		JPanel panRight=new JPanel(new BorderLayout());
		JPanel panel1=new JPanel(new BorderLayout());
		panel1.setPreferredSize(new Dimension(200,200));
		panel1.add(this.Shi, BorderLayout.CENTER);
		if(character.getShi()!=null)
			this.Shi.setText(character.getShi().toString());
		JPanel panel2=new JPanel(new BorderLayout());
		JButton changeShi=new JButton("Change Shield");
		changeShi.setPreferredSize(new Dimension(200,100));
		panel2.add(changeShi,BorderLayout.CENTER);
		panRight.add(panel1, BorderLayout.CENTER);
		panRight.add(panel2,BorderLayout.SOUTH);
		changeShi.addActionListener(list);
		
		JPanel panLeft=new JPanel(new BorderLayout());
		JPanel panel3=new JPanel(new BorderLayout());
		panel3.setPreferredSize(new Dimension(200,200));
		panel3.add(this.Weap, BorderLayout.CENTER);
		if(character.getWea()!=null)
			this.Weap.setText(character.getWea().toString());
		JPanel panel4=new JPanel(new BorderLayout());
		JButton changeWea=new JButton("Change Weapon");
		changeWea.setPreferredSize(new Dimension(200,100));
		panel4.add(changeWea,BorderLayout.CENTER);
		panLeft.add(panel3, BorderLayout.CENTER);
		panLeft.add(panel4,BorderLayout.SOUTH);
		changeWea.addActionListener(list);
		
		panel.add(panCenter,BorderLayout.CENTER);
		panel.add(panRight,BorderLayout.EAST);
		panel.add(panLeft,BorderLayout.WEST);
		return panel;
	}
	
	// La page du choix de bouclier
	public JPanel ShieldP(){
		PanelImage pan= new PanelImage("FondStart.jpg");
		pan.setLayout(new BorderLayout());
		JPanel panCenter=new JPanel();
		JPanel panRight=new JPanel(new BorderLayout());
		ShieldListener list=new ShieldListener();
		for(int i=0; i<Shield.getShieldlength();i++){
			JButton but=new JButton(Shield.getShieldList(i).getName());
			but.setPreferredSize(new Dimension(200,100));
			panCenter.add(but);
			but.addActionListener(list);
		}
		JPanel panel1=new JPanel(new BorderLayout());
		panel1.setPreferredSize(new Dimension(400,200));
		panel1.add(this.Cons, BorderLayout.CENTER);
		JPanel panel2=new JPanel(new BorderLayout());
		JButton back=new JButton("Back");
		JButton change=new JButton("Change");
		change.addActionListener(list);
		change.setPreferredSize(new Dimension(200,100));
		back.addActionListener(list);
		back.setPreferredSize(new Dimension(200,100));
		panel2.add(back,BorderLayout.SOUTH);
		panel2.add(change,BorderLayout.NORTH);
		panRight.add(panel1, BorderLayout.CENTER);
		panRight.add(panel2,BorderLayout.SOUTH);
		panCenter.setOpaque(false);
		panel2.setOpaque(false);
		pan.add(panCenter,BorderLayout.CENTER);	
		pan.add(panRight, BorderLayout.EAST);
		return pan;
	}
	
	// La page du choix de l'arme
	public JPanel WeaponP(){
		PanelImage pan= new PanelImage("FondStart.jpg");
		pan.setLayout(new BorderLayout());
		JPanel panCenter=new JPanel();
		JPanel panRight=new JPanel(new BorderLayout());
		WeaponListener list=new WeaponListener();
		for(int i=0; i<Weapon.getWeaponlength();i++){
			JButton but=new JButton(Weapon.getWeapon(i).getName());
			but.setPreferredSize(new Dimension(200,100));
			panCenter.add(but);
			but.addActionListener(list);
		}
		JPanel panel1=new JPanel(new BorderLayout());
		panel1.setPreferredSize(new Dimension(400,200));
		panel1.add(this.Cons, BorderLayout.CENTER);
		JPanel panel2=new JPanel(new BorderLayout());
		JButton change=new JButton("Change");
		change.addActionListener(list);
		change.setPreferredSize(new Dimension(200,100));
		JButton back=new JButton("Back");
		back.addActionListener(list);
		back.setPreferredSize(new Dimension(200,100));
		panel2.add(back,BorderLayout.SOUTH);
		panel2.add(change , BorderLayout.NORTH);
		panRight.add(panel1, BorderLayout.CENTER);
		panRight.add(panel2,BorderLayout.SOUTH);
		panCenter.setOpaque(false);
		panel2.setOpaque(false);
		pan.add(panCenter,BorderLayout.CENTER);	
		pan.add(panRight, BorderLayout.EAST);
		return pan;
	}
	
	// La page du combat
	public JPanel FightP(){

		p1.setPreferredSize(new Dimension(150,100));
		p2.setPreferredSize(new Dimension(150,100));
		p1.setBorder(BorderFactory.createTitledBorder("Character 1"));
		p2.setBorder(BorderFactory.createTitledBorder("Character 2"));
		this.Cons.setBorder(BorderFactory.createTitledBorder("Console"));

		p1.setText("HP = "+fight.getC1().getHP()+"/"+fight.getC1().getVIT()+"\n"+fight.getC1());
		p2.setText("HP = "+fight.getC2().getHP()+"/"+fight.getC2().getVIT()+"\n"+fight.getC2());
		JPanel pan= new JPanel(new BorderLayout());
		JPanel panCenter=new JPanel(new BorderLayout());
		JPanel panRight=new JPanel(new BorderLayout());
		JPanel panLeft=new JPanel(new BorderLayout());
		PanelImage panCenterTop=new PanelImage("Versus.png");
		panRight.add(p2,BorderLayout.CENTER);
		panLeft.add(p1,BorderLayout.CENTER);
		panCenter.add(panCenterTop,BorderLayout.CENTER);
		Cons.setPreferredSize(new Dimension(300,200));
		panCenter.add(Cons,BorderLayout.SOUTH);
		FightListener list=new FightListener();
		JButton AW=new JButton("Attack with Weapon");AW.addActionListener(list);
		JButton AS=new JButton("Attack with Shield");AS.addActionListener(list);
		DW=new JButton("Defend with Weapon");DW.addActionListener(list);
		DS=new JButton("Defend with Shield");DS.addActionListener(list);
		Wait=new JButton("Wait before defending");Wait.addActionListener(list);
		JButton Potion=new JButton("Use Potion");Potion.addActionListener(list);
		JButton Skill= new JButton("Use Skill");Skill.addActionListener(list);
		JButton Escape=new JButton("Escape");Escape.addActionListener(list);
		JPanel panBot=new JPanel(new GridLayout(1,5));
		JPanel panAttack=new JPanel(new GridLayout(2,1,0,0));
		JPanel panDefend=new JPanel(new GridLayout(3,1,0,0));
		panAttack.add(AW);
		panAttack.add(AS);
		panDefend.add(DW);
		panDefend.add(DS);
		panDefend.add(Wait);
		panBot.add(panAttack);
		panBot.add(panDefend);
		panBot.add(Potion);
		panBot.add(Skill);
		panBot.add(Escape);
		pan.add(panCenter,BorderLayout.CENTER);
		pan.add(panRight, BorderLayout.EAST);
		pan.add(panLeft,BorderLayout.WEST);
		pan.add(panBot,BorderLayout.SOUTH);
		return pan;
	
	}
	
	// La page qui permet d'intéragir avec les sorts
	public JPanel SkillP(int p){
		PanelImage pan= new PanelImage("FondStart.jpg");
		SkillT.setBorder(BorderFactory.createTitledBorder("Skill"));
		pan.setLayout(new BorderLayout());
		JPanel panLeft=new JPanel();
		JPanel panRight=new JPanel(new BorderLayout());		
		JPanel panel1=new JPanel(new BorderLayout());
		JPanel panel2=new JPanel(new BorderLayout());
		if(p==0){
			useSkillListener list=new useSkillListener();
			panel1.add(this.SkillT, BorderLayout.CENTER);
			JButton use= new JButton("Use Skill");
			JButton back=new JButton("Back");
			use.addActionListener(list);
			use.setPreferredSize(new Dimension(200,100));
			back.addActionListener(list);
			back.setPreferredSize(new Dimension(200,100));
			panel2.add(back,BorderLayout.SOUTH);
			panel2.add(use,BorderLayout.NORTH);
			for(int i=0; i<this.character.getSkills().size();i++){
				JButton but=new JButton(character.getSkills().get(i).getName());
				but.setPreferredSize(new Dimension(200,100));
				panLeft.add(but);
				but.addActionListener(list);
				if(fight.getT1attacking()==false && this.character.getSkills().get(i).getType().equals("Defense"))
					but.setEnabled(false);
			}
		}
		if(p==1){
			SkillListener list=new SkillListener();
			panel1.add(this.SkillT, BorderLayout.CENTER);
			JButton back=new JButton("Back");
			back.addActionListener(list);
			back.setPreferredSize(new Dimension(200,100));
			panel2.add(back,BorderLayout.SOUTH);
			for(int i=0; i<this.character.getSkills().size();i++){
				
				JButton but=new JButton(this.character.getSkills().get(i).getName());
				but.setPreferredSize(new Dimension(200,100));
				panLeft.add(but);
				but.addActionListener(list);
			}
		}
		if(p==2){
			remSkillListener list=new remSkillListener();
			panel1.add(this.SkillT, BorderLayout.CENTER);
			JButton use= new JButton("Remove Skill");
			JButton back=new JButton("Leave");
			use.addActionListener(list);
			use.setPreferredSize(new Dimension(200,100));
			back.addActionListener(list);
			back.setPreferredSize(new Dimension(200,100));
			panel2.add(back,BorderLayout.SOUTH);
			panel2.add(use,BorderLayout.NORTH);
			for(int i=0; i<this.character.getSkills().size();i++){
			
				JButton but=new JButton(character.getSkills().get(i).getName());
				but.setPreferredSize(new Dimension(200,100));
				panLeft.add(but);
				but.addActionListener(list);
			}
		}
		if(p==3){
			changeSkillListener list=new changeSkillListener();
			panel1.add(this.SkillT, BorderLayout.CENTER);
			JButton back=new JButton("Change Skill");
			back.addActionListener(list);
			back.setPreferredSize(new Dimension(200,100));
			panel2.add(back,BorderLayout.SOUTH);
			if(character.equals(fight.getC1())){
				for(int i=0; i<fight.getC2().getSkills().size();i++){
					JButton but=new JButton(fight.getC2().getSkills().get(i).getName());
					but.setPreferredSize(new Dimension(200,100));
					panLeft.add(but);
					but.addActionListener(list);
				}
			}
			else{
				for(int i=0; i<fight.getC1().getSkills().size();i++){
					JButton but=new JButton(fight.getC1().getSkills().get(i).getName());
					but.setPreferredSize(new Dimension(200,100));
					panLeft.add(but);
					but.addActionListener(list);
				}
			}
		}
		if(p==4){
			remSkillListener1 list=new remSkillListener1();
			panel1.add(this.SkillT, BorderLayout.CENTER);
			JButton use= new JButton("Remove Skill");
			use.addActionListener(list);
			use.setPreferredSize(new Dimension(200,100));
			panel2.add(use,BorderLayout.NORTH);
			for(int i=0; i<this.character.getSkills().size();i++){
				JButton but=new JButton(character.getSkills().get(i).getName());
				but.setPreferredSize(new Dimension(200,100));
				panLeft.add(but);
				but.addActionListener(list);
			}
		}
		panRight.add(panel1, BorderLayout.CENTER);
		panRight.add(panel2,BorderLayout.SOUTH);
		panel2.setOpaque(false);
		panLeft.setOpaque(false);
		pan.add(panLeft,BorderLayout.CENTER);	
		pan.add(panRight, BorderLayout.EAST);
		return pan;
	}
	
	// Permet de faire progresser le combat
	public void refreshTurn(){
		if(ite==0 && fight.getCombatFini()==false){
			if(((fight.getAction1() instanceof Weapon && !(((Weapon) fight.getAction1()).isModeDefense())) || (fight.getAction1() instanceof Shield && !(((Shield)fight.getAction1()).isModeDefense())) ||(fight.getAction1() instanceof Skill && ((Skill)fight.getAction1()).getType().equals("Attack")))){
				fight.setT1attacking(true);
			}
			fight.setAction2(fight.ActionAuto(fight.getC2()));
			Cons.setText(fight.FightProgress(ite));
			ite=1;
			fight.resetTurn();
			fight.resetS();
			if(fight.getCombatFini()==false)
				Cons.append(fight.getC2().getName()+" begin the next turn.\n");
			fight.setAction2(fight.ActionAuto(fight.getC2()));
			if(((fight.getAction2() instanceof Weapon && !(((Weapon) fight.getAction2()).isModeDefense())) || (fight.getAction2() instanceof Shield && !(((Shield)fight.getAction2()).isModeDefense())) ||(fight.getAction2() instanceof Skill && ((Skill)fight.getAction2()).getType().equals("Attack")))){
				fight.setT1attacking(true);
				Cons.append(fight.getC2().getName()+" will attack\n");
			}
			else
				fight.setT1attacking(false);
			
			if(fight.getT1attacking()){
				DW.setEnabled(true);
				DS.setEnabled(true);
				Wait.setEnabled(true);
			}
			else{
				DW.setEnabled(false);
				DS.setEnabled(false);
				Wait.setEnabled(false);
			}
		}
		else if(ite==1 && fight.getCombatFini()==false){
			
			Cons.setText(fight.FightProgress(ite));
			if(fight.getCombatFini()==false)
				Cons.append(fight.getC1().getName()+ " begin the next turn.\n");
			ite=0;
			fight.resetTurn();	
			fight.resetS();
			if(fight.getT1attacking()){
				DW.setEnabled(true);
				DS.setEnabled(true);
				Wait.setEnabled(true);
			}
			else{
				DW.setEnabled(false);
				DS.setEnabled(false);
				Wait.setEnabled(false);
			}
		}
		if(fight.getCombatFini()){
			if(fight.getSurrender()!=null){
				Cons.setText(fight.Escape(fight.getSurrender()));
			}
			Character Winner=fight.Winner();
			
			if(Winner==FenJeu.this.character){
				if(fight.getSurrender()!=null){
					if(!(fight.getSurrender().equals(FenJeu.this.character))){
						if(this.character.getSkills().size()<this.character.getNbCap()){
							int n=JOptionPane.showConfirmDialog(FenJeu.this,"You win\nDo you want to steal one of your opponent's skill?","Victory !",JOptionPane.YES_NO_OPTION);
							if(n==JOptionPane.YES_OPTION){
								FenJeu.this.setContentPane(FenJeu.this.SkillP(3));
							}
							else if(n==JOptionPane.NO_OPTION)
								FenJeu.this.setContentPane(FenJeu.this.MainP());
						}
						else{
							int n=JOptionPane.showConfirmDialog(FenJeu.this,"You win\nDo you want to replace your skill with one of your opponent's skill?","Victory !",JOptionPane.YES_NO_OPTION);
							if(n==JOptionPane.YES_OPTION){
								FenJeu.this.setContentPane(FenJeu.this.SkillP(2));
							}
							else if(n==JOptionPane.NO_OPTION)
								FenJeu.this.setContentPane(FenJeu.this.MainP());
						}
					}
					else FenJeu.this.setContentPane(FenJeu.this.MainP());
				}
				else{
					JOptionPane.showMessageDialog(FenJeu.this,"You win","Victory !",JOptionPane.INFORMATION_MESSAGE);
					FenJeu.this.setContentPane(FenJeu.this.MainP());
					
				}
			}
			else{
				JOptionPane.showMessageDialog(FenJeu.this,"You lose","Defeat !",JOptionPane.INFORMATION_MESSAGE);
				if(this.character.getSkills().size()>this.character.getNbCap()){
					JOptionPane.showMessageDialog(FenJeu.this,"You have to remove one of your skills","Defeat !",JOptionPane.INFORMATION_MESSAGE);
					FenJeu.this.setContentPane(FenJeu.this.SkillP(4));
				}
				else FenJeu.this.setContentPane(FenJeu.this.MainP());
			}
			
			Cons.setText("");
		}
		
	}
	// Les écouteurs 
	public class changeSkillListener implements ActionListener{
		Skill ski;
		public void  actionPerformed(ActionEvent event){
			String s=event.getActionCommand();
			if(character.equals(fight.getC1()))
				for(int i=0; i< fight.getC2().getSkills().size();i++){
					if(s.equals(fight.getC2().getSkills().get(i).getName())){
						ski=fight.getC2().getSkills().get(i);
						SkillT.setText(character.getSkills().get(i).toString());
					}
				}
			else{
				for(int i=0; i< fight.getC1().getSkills().size();i++){
					if(s.equals(fight.getC1().getSkills().get(i).getName())){
						ski=fight.getC1().getSkills().get(i);
						SkillT.setText(character.getSkills().get(i).toString());
					}
				}
			}
			if(s.equals("Change Skill")){
				character.getSkills().add(ski);
				FenJeu.this.setContentPane(FenJeu.this.MainP());
				SkillT.setText("");
			}
			FenJeu.this.revalidate();
		}
	}
	public class remSkillListener implements ActionListener{
		int j;
		public void actionPerformed(ActionEvent event){
			String s= event.getActionCommand();
			for(int i=0; i< character.getSkills().size();i++){
				if(s.equals(character.getSkills().get(i).getName())){
					j=i;
					SkillT.setText(character.getSkills().get(i).toString());
				}
			}
			if(s.equals("Remove Skill")){
				character.getSkills().remove(j);
				SkillT.setText("");
				FenJeu.this.setContentPane(FenJeu.this.SkillP(3));
			}
			if(s.equals("Leave")){
				FenJeu.this.setContentPane(FenJeu.this.MainP());
				SkillT.setText("");
			}
			FenJeu.this.revalidate();
		}
	}
	public class remSkillListener1 implements ActionListener{
		int j;
		public void actionPerformed(ActionEvent event){
			String s= event.getActionCommand();
			for(int i=0; i< character.getSkills().size();i++){
				if(s.equals(character.getSkills().get(i).getName())){
					j=i;
					SkillT.setText(character.getSkills().get(i).toString());
				}
			}
			if(s.equals("Remove Skill")){
				character.getSkills().remove(j);
				SkillT.setText("");
				FenJeu.this.setContentPane(FenJeu.this.MainP());
			}
			FenJeu.this.revalidate();
		}
	}
	public class useSkillListener implements ActionListener{
		int j;
		public void actionPerformed(ActionEvent event){
			String s =event.getActionCommand();
			for(int i=0; i< character.getSkills().size();i++){
				if(s.equals(character.getSkills().get(i).getName())){
					j=i;
					SkillT.setText(character.getSkills().get(i).toString());
				}
			}
			if(s.equals("Use Skill")){
				FenJeu.this.setContentPane(FenJeu.this.FightP());
				fight.setAction1(fight.UseSkill(FenJeu.this.character, j));
				FenJeu.this.refreshTurn();
				SkillT.setText("");
			}
			if(s.equals("Back")){
				FenJeu.this.setContentPane(FenJeu.this.FightP());	
				SkillT.setText("");
			}
			if(fight.getT1attacking()){
				DW.setEnabled(true);
				DS.setEnabled(true);
				Wait.setEnabled(true);
			}
			else{
				DW.setEnabled(false);
				DS.setEnabled(false);
				Wait.setEnabled(false);
			}
			FenJeu.this.revalidate();
		}
	}
	
	public class FightListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String s= event.getActionCommand();
			if(s.equals("Attack with Weapon"))
				fight.setAction1(fight.AttackWeapon(FenJeu.this.character));
			else if(s.equals("Attack with Shield"))
				fight.setAction1(fight.AttackShield(FenJeu.this.character));
			else if(s.equals("Defend with Weapon"))
				fight.setAction1(fight.DefendWeapon(FenJeu.this.character));
			else if(s.equals("Defend with Shield"))
				fight.setAction1(fight.DefendShield(FenJeu.this.character));
			else if(s.equals("Use Potion"))
				fight.setAction1(fight.UsePot(FenJeu.this.character));
			else if(s.equals("Escape")){
				fight.Escape(character);
			}
			if(s.equals("Use Skill"))
				FenJeu.this.setContentPane(FenJeu.this.SkillP(0));
			else if(s.equals("Wait before defending")){
				fight.getC1().getShi().setWaiting(true);
				fight.getC1().getWea().setWaiting(true);
				if(character.equals(fight.getC1()))	
					Cons.append(fight.getC2().getName() +" will deal "+fight.getAction2().getEFF()+" damage.\n");
				else
					Cons.append(fight.getC1().getName() +" will deal "+fight.getAction1().getEFF()+" damage.\n");
			}
			else 
				FenJeu.this.refreshTurn();			
			p1.setText("HP = "+fight.getC1().getHP()+"/"+fight.getC1().getVIT()+"\n"+fight.getC1());
			p2.setText("HP = "+fight.getC2().getHP()+"/"+fight.getC2().getVIT()+"\n"+fight.getC2());
			FenJeu.this.revalidate();
			}
	}
	
	public class InventoryListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String s=event.getActionCommand();
			if(s.equals("Change Weapon"))
				FenJeu.this.setContentPane(FenJeu.this.WeaponP());
			if(s.equals("Change Shield"))
				FenJeu.this.setContentPane(FenJeu.this.ShieldP());
			if(s.equals("Back")){
				FenJeu.this.Shi.setText("");
				FenJeu.this.Weap.setText("");
				if(character==null){
					FenJeu.this.setContentPane(FenJeu.this.getPan());
				}
				else
					FenJeu.this.setContentPane(FenJeu.this.MainP());
			}
			FenJeu.this.revalidate();
		}
	}

	
	public class WeaponListener implements ActionListener{
		Weapon weapon=null;
		public void actionPerformed(ActionEvent event){
			String s=event.getActionCommand();
			for(int i=0; i<Weapon.getWeaponlength();i++){
				if(s.equals(Weapon.getWeapon(i).getName())){
					weapon=Weapon.getWeapon(i);
					Cons.setText(weapon.toString());
				}
			}
			if(s.equals("Change")){
				character.setWea(new Weapon(weapon));
				FenJeu.this.setContentPane(FenJeu.this.InvP());
				FenJeu.this.Cons.setText("");
			}
			if(s.equals("Back")){
				FenJeu.this.setContentPane(FenJeu.this.InvP());
				FenJeu.this.Cons.setText("");
			}
			FenJeu.this.revalidate();
		}
	}
	
	public class ShieldListener implements ActionListener{
		Shield shield=null;
		public void actionPerformed(ActionEvent event){
			String s=event.getActionCommand();
			for(int i=0; i<Shield.getShieldlength();i++){
				if(s.equals(Shield.getShieldList(i).getName())){
					shield=Shield.getShieldList(i);
					Cons.setText(shield.toString());
				}
			}
			if(s.equals("Change")){
				character.setShi(new Shield(shield));
				FenJeu.this.setContentPane(FenJeu.this.InvP());
				FenJeu.this.Cons.setText("");
			}
			if(s.equals("Back")){
				FenJeu.this.setContentPane(FenJeu.this.InvP());
				FenJeu.this.Cons.setText("");
			}
			FenJeu.this.revalidate();
		}
	}
	
	public class NewListener implements ActionListener{
		int OK=0;
		public void actionPerformed(ActionEvent event){
			String s=event.getActionCommand();
			Character New;
			if(s.equals("Magician")){
				panRight.setImg("Magician.png");
				panRight.repaint();
				OK=1;
			}
			if(s.equals("Warrior")){
				panRight.setImg("Warrior.png");
				panRight.repaint();
				OK=2;
			}
			if(s.equals("Hunter")){
				panRight.setImg("Hunter.png");
				panRight.repaint();
				OK=3;
			}
			if(s.equals("Create") && OK!=0){
				try{
					if(OK==1){	
						New=new Magician(Name.getText(),Integer.parseInt(FOR.getText()),Integer.parseInt(DEX.getText()),Integer.parseInt(INT.getText()),Integer.parseInt(CON.getText()));
						character=(Magician)New;
					}
					else if(OK==2){
						New=new Warrior(Name.getText(),Integer.parseInt(FOR.getText()),Integer.parseInt(DEX.getText()),Integer.parseInt(INT.getText()),Integer.parseInt(CON.getText()));
						character=(Warrior)New;
					}
					else{
						New=new Hunter(Name.getText(),Integer.parseInt(FOR.getText()),Integer.parseInt(DEX.getText()),Integer.parseInt(INT.getText()),Integer.parseInt(CON.getText()));
						character=(Hunter)New;
					}
					FenJeu.this.setContentPane(FenJeu.this.MainP());
				}
				catch(ExceptionChar ex){
					new FenErreur("Erreur personnage",ex.getMessage());
				}
				catch(NumberFormatException ex){
					new FenErreur("Erreur personnage","Erreur à la saisie");
				}
			}
			if(s.equals("Back")){
				if(character==null){
					FenJeu.this.setContentPane(FenJeu.this.getPan());
				}
				else
					FenJeu.this.setContentPane(FenJeu.this.MainP());
			}
			FenJeu.this.revalidate();	
		}
	}
	
	public class LoadListener implements ActionListener{
		int w=0;
		Character load=null;
		public void actionPerformed(ActionEvent event){
			String s= event.getActionCommand();
			
			try{
				if(s.equals("Slot 1")){
					load=null;
					load=Character.charger("Sauvegarde1",load);
				}
				else if(s.equals("Slot 2")){
					load=null;
					load=Character.charger("Sauvegarde2",load);
				}
				else if(s.equals("Slot 3")){
					load=null;
					load=Character.charger("Sauvegarde3",load);
				}
				else if(s.equals("Slot 4")){
					load=null;
					load=Character.charger("Sauvegarde4",load);
				}
				FenJeu.this.Cons.setText(load.toString());
			}
			catch(NullPointerException ex){
				load=null;
				FenJeu.this.Cons.setText("Slot vide");
			}
			if(s.equals("Confirm")){
				if(load!=null){
					character=load;
					if(character==null)
						FenJeu.this.setContentPane(FenJeu.this.getPan());
					else
						FenJeu.this.setContentPane(FenJeu.this.MainP(	));
					FenJeu.this.Cons.setText("");
				}
			}
			if(s.equals("Back")){
				if(character==null)
					FenJeu.this.setContentPane(FenJeu.this.getPan());
				else
					FenJeu.this.setContentPane(FenJeu.this.MainP());
				FenJeu.this.Cons.setText("");
			}
			FenJeu.this.revalidate();	
		}
	}
	
	public class SaveListener implements ActionListener{
		int w=0;Character load=null;
		public void actionPerformed(ActionEvent event){
			String s= event.getActionCommand();
			
			try{
				if(s.equals("Slot 1")){
					load=null;w=1;
					load=new Character(Character.charger("Sauvegarde1",load));
				}
				else if(s.equals("Slot 2")){
					w=2;load=null;
					load=new Character(Character.charger("Sauvegarde2",load));
				}
				else if(s.equals("Slot 3")){
					w=3;load=null;
					load=new Character(Character.charger("Sauvegarde3",load));
				}
				else if(s.equals("Slot 4")){
					w=4;load=null;
					load=new Character(Character.charger("Sauvegarde4",load));
				}
				FenJeu.this.Cons.setText(load.toString());
			}
			catch(NullPointerException ex){
				load=null;
				FenJeu.this.Cons.setText("Slot vide");
			}
			if(s.equals("Confirm")){
				Character.sauvegarder("Sauvegarde"+w, character);
				FenJeu.this.setContentPane(FenJeu.this.MainP());
				FenJeu.this.Cons.setText("");
			}
			if(s.equals("Back")){
				if(character==null)
					FenJeu.this.setContentPane(FenJeu.this.getPan());
				else
					FenJeu.this.setContentPane(FenJeu.this.MainP());
				FenJeu.this.Cons.setText("");
			}
			FenJeu.this.revalidate();	
		}
	}
	
	public class SkillListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String s=event.getActionCommand();
			for(int i=0; i< character.getSkills().size();i++){
				if(s.equals(character.getSkills().get(i).getName())){
					SkillT.setText(character.getSkills().get(i).toString());
				}
			}
			if(s.equals("Back")){
				if(character==null)
					FenJeu.this.setContentPane(FenJeu.this.getPan());
				else
					FenJeu.this.setContentPane(FenJeu.this.MainP());
				FenJeu.this.SkillT.setText("");
			}
			FenJeu.this.revalidate();
		}
	}
	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String s=event.getActionCommand();
			if(s.equals("New Character"))
				FenJeu.this.setContentPane(FenJeu.this.NewP());
			if(s.equals("Load Character"))
				FenJeu.this.setContentPane(FenJeu.this.LoadP(LOAD));
			if(s.equals("Save Character"))
				FenJeu.this.setContentPane(FenJeu.this.LoadP(SAVE));
			if(s.equals("Show Skills"))
				FenJeu.this.setContentPane(FenJeu.this.SkillP(1));
			if(s.equals("Upgrade")){
				try{
					if(butFOR.isSelected())
						character.UpgradeStat(character,1);
					if(butDEX.isSelected())
						character.UpgradeStat(character,2);
					if(butINT.isSelected())
						character.UpgradeStat(character,3);
					if(butCON.isSelected())
						character.UpgradeStat(character,4);
					character.setVIT();
				}
				catch(Exception ex){
					new FenErreur("Upgrade failed",ex.getMessage());
				}
				FenJeu.this.setContentPane(FenJeu.this.MainP());
			}
			if(s.equals("Inventory")){
				FenJeu.this.setContentPane(FenJeu.this.InvP());
			}
			if(s.equals("Fight")){
				Character Ene=null;
				fight=new Fight(FenJeu.this.character,Character.charger("Sauvegarde5",Ene));
				FenJeu.this.setContentPane(FenJeu.this.FightP());
				ite=fight.setFight();
				p1.setText("HP = "+fight.getC1().getHP()+"/"+fight.getC1().getVIT()+"\n"+fight.getC1());
				p2.setText("HP = "+fight.getC2().getHP()+"/"+fight.getC2().getVIT()+"\n"+fight.getC2());
				if(ite==1){
					Cons.append(fight.getC2().getName()+" begin the next turn.\n");
					fight.setAction2(fight.ActionAuto(fight.getC2()));
				}
				else
					Cons.append(fight.getC1().getName()+" begin the next turn.\n");
				if(((fight.getAction2() instanceof Weapon && !(((Weapon) fight.getAction2()).isModeDefense())) || (fight.getAction2() instanceof Shield && !(((Shield)fight.getAction2()).isModeDefense())) ||(fight.getAction2() instanceof Skill && ((Skill)fight.getAction2()).getType().equals("Attack")))){
					fight.setT1attacking(true);
					Cons.append(fight.getC2().getName()+" will attack\n");
				}
				else
					fight.setT1attacking(false);
				if(fight.getT1attacking()){
					DW.setEnabled(true);
					DS.setEnabled(true);
					Wait.setEnabled(true);
				}
				else{
					DW.setEnabled(false);
					DS.setEnabled(false);
					Wait.setEnabled(false);
				}
			}
			if(s.equals("Quitter"))
				FenJeu.this.dispose();
			
			FenJeu.this.revalidate();
		}	
	}
}
