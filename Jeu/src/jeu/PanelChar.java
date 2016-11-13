package jeu;
import java.awt.*;
import javax.swing.*;

public class PanelChar extends JPanel{
	private Image img=Toolkit.getDefaultToolkit().getImage("Question.png");
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	//	Image fond=Toolkit.getDefaultToolkit().getImage("Fond.jpg");
	//	g.drawImage(fond,0,0,this.getWidth(),this.getHeight(),this);
		g.drawImage(img,this.getWidth()/4,this.getHeight()/4,this.getWidth()/2,this.getHeight()/2, this);
	}
	
	public void setImg(String nom){
		this.img=Toolkit.getDefaultToolkit().getImage(nom);
	}
}
