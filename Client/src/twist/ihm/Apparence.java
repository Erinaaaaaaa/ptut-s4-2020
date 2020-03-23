package twist.ihm;

import java.awt.*;
import java.awt.font.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;


public class Apparence
{
	private static Color[] TABCOUL ={Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};

	public static void setStyleBtnPrincipale(JButton btn){
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setBorderPainted(false);
		btn.setFocusable(false);
		btn.setBackground(new Color(88, 196, 237));
		btn.setForeground(new Color(255, 255, 255));
		btn.setFont(new Font("Helvetica", Font.BOLD, 26));
	}

	public static void setStyleBtnAction(JButton btn)
	{
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setBorderPainted(false);
		btn.setFocusable(false);
		btn.setBackground(new Color(127, 105, 76));
		btn.setForeground(new Color(235, 235, 235));
		btn.setFont(new Font("Helvetica", Font.BOLD, 15));
	}

	public static void setStyleLbl(JLabel lbl)
	{
		lbl.setFont(new Font("Helvetica", Font.BOLD, 15));
		lbl.setForeground(new Color(235, 235, 235));
	}

	public static Color  getJoueurCouleur(int id) {return TABCOUL[id];}

	public static JLabel getJoueurImage  (int id) {
		JLabel jLRetour = new JLabel();
		switch (id) {
			case 0 : jLRetour = new JLabel(new ImageIcon("ihm/img/joueurR.png"));break;
			case 1 : jLRetour = new JLabel(new ImageIcon("ihm/img/joueurG.png"));break;
			case 2 : jLRetour = new JLabel(new ImageIcon("ihm/img/joueurB.png"));break;
			case 3 : jLRetour = new JLabel(new ImageIcon("ihm/img/joueurY.png"));break;
		}
		return jLRetour;
	}
}
