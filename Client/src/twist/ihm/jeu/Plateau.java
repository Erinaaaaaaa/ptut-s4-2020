package twist.ihm.jeu;

import twist.Controleur;
import twist.ControleurGui;
import twist.metier.*;
import twist.ihm.Apparence;
import twist.util.Logger;

import java.awt.geom.Rectangle2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

/**
 * Représente la grille de jeu dans l'IHM.
 */
public class Plateau extends JPanel implements MouseListener
{

    private final int SIZE = 20;
    private IhmPlateau ihm;

    private Controleur controleur;
    private double contWidth, contHeight;

    Plateau(IhmPlateau ihm, Controleur controleur)
    {
        super(true);

        this.ihm = ihm;
        this.controleur = controleur;
        Apparence.setBackgroundPlateau(this);
        addMouseListener(this);
    }

    void miseAJour()
    {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        Dimension dim = this.getSize();
        Conteneur conteneur;
        Lock lock;
        double baseX, baseY;

        double x, y;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        contWidth = dim.getWidth() / this.controleur.getHauteurPont() - 1;
        contHeight = dim.getHeight() / this.controleur.getLargeurPont() - 1;

        for (int lig = 0; lig < this.controleur.getConteneurs().length; lig++)
            for (int col = 0; col < this.controleur.getConteneurs()[0].length; col++)
            {

                conteneur = this.controleur.getConteneurs()[lig][col];
                baseX = col * contWidth;
                baseY = lig * contHeight;

                int coordX[] = {(int) (col * contWidth) - SIZE + 5, (int) ((col + 1) * contWidth) - SIZE + 5,
                        (int) ((col + 1) * contWidth) - SIZE + 5, (int) ((col) * contWidth) - SIZE + 5};

                int coordY[] = {(int) (lig * contHeight) - SIZE + 5, (int) ((lig) * contHeight) - SIZE + 5,
                        (int) ((lig + 1) * contHeight) - SIZE + 5, (int) ((lig + 1) * contHeight) - SIZE + 5};

                g2.drawArc(coordX[0], coordY[0], SIZE * 2 - 5, SIZE * 2 - 5, -10, -70);
                g2.drawLine((int) baseX + SIZE, (int) baseY + 5, coordX[1], (int) baseY + 5);

                g2.drawArc(coordX[1], coordY[1], SIZE * 2 - 5, SIZE * 2 - 5, -90 - 10, -70);
                g2.drawLine(coordX[1] + (SIZE - 5), (int) baseY + SIZE, coordX[1] + (SIZE - 5), coordY[2]);

                g2.drawArc(coordX[2], coordY[2], SIZE * 2 - 5, SIZE * 2 - 5, -180 - 10, -70);
                g2.drawLine((int) baseX + SIZE, coordY[2] + (SIZE - 5), coordX[1], coordY[2] + (SIZE - 5));

                g2.drawArc(coordX[3], coordY[3], SIZE * 2 - 5, SIZE * 2 - 5, -270 - 10, -70);
                g2.drawLine((int) baseX + 5, (int) baseY + SIZE, (int) baseX + 5, coordY[2]);

                for (int i = 0; i < conteneur.getLocks().length; i++)
                {
                    lock = conteneur.getLocks()[i];
                    if (lock == null || lock.getJoueur() == null) continue;
                    switch (i)
                    {
                        default:
                        case 0: x = 0; y = 0; break;
                        case 1: x = 0; y = 1; break;
                        case 2: x = 1; y = 1; break;
                        case 3: x = 1; y = 0; break;
                    }
                    g2.setColor(Apparence.getJoueurCouleur(this.controleur.getNumeroJoueur(lock.getJoueur())));
                    Ellipse2D.Double c = new Ellipse2D.Double(((col + x) * contWidth) - (SIZE - 3) / 2, ((lig + y) * contHeight) - (SIZE - 3) / 2, SIZE, SIZE);
                    g2.fill(c);
                    if (conteneur.joueurMajoritaire() != null)
                    {
                        g2.setColor(Apparence.getJoueurCouleur(this.controleur.getNumeroJoueur(conteneur.joueurMajoritaire())));
                        Ellipse2D.Double c1 = new Ellipse2D.Double(baseX + contWidth / 2 - (SIZE), baseY + contHeight / 2 - (SIZE), SIZE * 2, SIZE * 2);
                        g2.fill(c1);
                    }
                }

                g2.setColor(Color.black);
                String valConteneur = "" + conteneur.getValeur();
                FontMetrics fm = g2.getFontMetrics();
                Rectangle2D r = fm.getStringBounds(valConteneur, g2);
                int x1 = (int) (((baseX + (col + 1) * contWidth) - (int) r.getWidth()) / 2);
                int y1 = (int) (((baseY + (lig + 1) * contHeight) - (int) r.getHeight()) / 2 + fm.getAscent());
                g2.drawString(valConteneur, x1, y1);
            }
    }

    public void mouseClicked(MouseEvent e)
    {
        boolean locker = false;
        int lig = 0;
        int col = 0;
        int lMax = this.controleur.getConteneurs().length + 1;
        int cMax = this.controleur.getConteneurs()[0].length + 1;
        for (lig = 0; lig < lMax && !locker; lig++)
            for (col = 0; col < cMax && !locker; col++)
            {
                double baseX = col * contWidth;
                double baseY = lig * contHeight;
                if ((e.getX() < baseX + SIZE && e.getX() > baseX - SIZE) &&
                        (e.getY() < baseY + SIZE && e.getY() > baseY - SIZE))
                {
                    locker = true;
                }
            }
        if (locker)
        {
            Logger.information("[GUI] Cliqué sur: " + lig + " : " + col);
            if (lig == lMax && col == cMax)
            {
                this.controleur.jouer(lMax - 2, cMax - 2, 2);
            } else if (lig == lMax && col < cMax)
            {
                this.controleur.jouer(lMax - 2, col - 1, 1);
            } else if (lig < lMax && col == cMax)
            {
                this.controleur.jouer(lig - 1, cMax - 2, 3);
            }
				/*else if (lig ==  1 && col ==  cMax) {this.controleur.jouer(lig-1, cMax-2, 0);}
				else if (lig ==  1 && col == 11) {this.controleur.jouer(lig-1, col-1, 1);}*/
            else
            {
                this.controleur.jouer(lig - 1, col - 1, 0);
            }
        } else
        {
            Logger.information("[GUI] Pas cliqué sur un lock");
            this.controleur.jouer(-1, -1, 0);
        }
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}
