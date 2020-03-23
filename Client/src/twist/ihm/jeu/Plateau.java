package twist.ihm.jeu;
import twist.ControleurGui;
import twist.metier.*;
import twist.ihm.Apparence;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Représente la grille de jeu dans l'IHM.
 */
public class Plateau extends JPanel {

	private IhmPlateau ihm;

	private ControleurGui controleur;

	Plateau(IhmPlateau ihm, ControleurGui controleur) {
		super(true);

		this.ihm = ihm;
		this.controleur = controleur;
	}

	void preparer(boolean focus) {
		this.setOpaque(true);
		this.setLayout(new GridLayout(controleur.getHauteurPont(), controleur.getLargeurPont()));
		this.repaint();
	}

	void miseAJour() {
		this.repaint();
	}

	@Override
public void paintComponent(Graphics g) {
	super.paintComponent(g);

	Graphics2D g2 = (Graphics2D) g;
	Dimension dim = this.getSize();
	Conteneur conteneur;
	Lock lock;
	double baseX, baseY;
	double contWidth, contHeight;
	double x, y;

	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	int SIZE = 20;

	contWidth = dim.getWidth() / this.controleur.getHauteurPont()-1;
	contHeight = dim.getHeight() / this.controleur.getLargeurPont()-1;
	System.out.println(contWidth+" : "+contHeight);

	for (int lig = 0; lig < this.controleur.getLargeurPont(); lig++)
		for (int col = 0; col < this.controleur.getHauteurPont(); col++) {
			conteneur = this.controleur.getConteneurs()[lig][col];

			baseX = col * contWidth;
			baseY = lig * contHeight;
				g2.setColor(Color.black);
			g2.drawRect((int)baseX, (int)baseY, (int)contWidth, (int)contHeight);
			g2.drawArc((int)(col * contWidth)-SIZE,(int) (lig * contHeight)-SIZE,SIZE*2,SIZE*2, 0, -90);
			g2.drawArc((int)((col+1) * contWidth)-SIZE,(int) ((lig) * contHeight)-SIZE,SIZE*2,SIZE*2, -90, -90);
			g2.drawArc((int)((col+1) * contWidth)-SIZE,(int) ((lig+1) * contHeight)-SIZE,SIZE*2,SIZE*2, -180, -90);
			g2.drawArc((int)((col) * contWidth)-SIZE,(int) ((lig+1) * contHeight)-SIZE,SIZE*2,SIZE*2, -270, -90);
			g2.setColor(Color.lightGray);
			Ellipse2D.Double c1 = new Ellipse2D.Double((int)(col * contWidth)-(SIZE-1),(int) (lig * contHeight)-(SIZE-1),(SIZE-1)*2,(SIZE-1)*2);
			g2.fill(c1);
			for (int i = 0; i < conteneur.getLocks().length; i++) {
				lock = conteneur.getLocks()[i];
				if (lock == null || lock.getJoueur() == null)
					continue;

				switch (i) {
					default:
					case 0:x = 0;y = 0;break;
					case 1: x = contWidth;y = 0; break;
					case 2:x = contWidth;y = contHeight;break;
					case 3:x = 0; y = contHeight; break;
				}

				g2.setColor(Apparence.getJoueurCouleur(this.ihm.getJoueurActif()));

				Ellipse2D.Double c = new Ellipse2D.Double(baseX + x - SIZE / 2D, baseY + y - SIZE / 2D, SIZE, SIZE);
				g2.fill(c);
			}
		}
}

}
