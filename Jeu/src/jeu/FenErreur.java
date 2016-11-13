package jeu;
import javax.swing.*;
import java.awt.*;

public class FenErreur extends JFrame{
	public FenErreur(String titre,String err){
		JOptionPane.showMessageDialog(this, err,titre,JOptionPane.ERROR_MESSAGE);
	}
	
	

}
