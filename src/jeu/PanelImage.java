package jeu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;

public class PanelImage extends JPanel{
	private String nom;
	public PanelImage(String nom){
		this.nom=nom;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image fond=Toolkit.getDefaultToolkit().getImage(nom);
		g.drawImage(fond,0,0,this.getWidth(),this.getHeight(),this);
	}
}
