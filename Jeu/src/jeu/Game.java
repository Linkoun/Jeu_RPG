package jeu;
import java.util.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
public class Game {
	
	
	public static void main(String [] args){
		Toolkit TK= Toolkit.getDefaultToolkit();
		Dimension dim= TK.getScreenSize();
		new FenJeu("Jeu",0,0,dim.width,dim.height);
	}
}
	

