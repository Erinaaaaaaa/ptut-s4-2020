package twist.ihm;

import java.awt.*;
import java.awt.font.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

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
		String imageJoueur ="/twist/ihm/img/joueur";
		switch (id) {
			case 0 : imageJoueur += "R.png";break;
			case 1 : imageJoueur += "G.png";break;
			case 2 : imageJoueur += "B.png";break;
			case 3 : imageJoueur += "Y.png";break;
		}
		JLabel jLRetour = new JLabel(new ImageIcon(loadImage(imageJoueur)));
		return jLRetour;
	}

	private static Image loadImage(final String string) {
		try {
				final Image bi = ImageIO.read(MethodHandles.lookup().lookupClass().getResourceAsStream(string));
				return bi;
		}
		catch (IOException e) {
				e.printStackTrace();
				return null;
		}
}
}
