package twist.ihm.jeu;

import twist.Controleur;
import twist.metier.*;
import twist.ihm.Apparence;
import twist.util.Logger;

import java.awt.geom.Rectangle2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Représente la grille de jeu dans l'IHM.
 */
public class Plateau extends JPanel implements MouseListener
{

    private final int SIZE = 0;

    private final int ARC_RADIUS = 12;
    private final int GAP_WIDTH  = 4;
    private IhmPlateau ihm;

    private Controleur controleur;
    private int largeurConteneur, hauteurConteneur;

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
        Conteneur conteneur;
        Lock lock;
        double baseX, baseY;

        double x, y;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        largeurConteneur = this.getWidth() / this.controleur.getLargeurPont();
        hauteurConteneur = this.getHeight() / this.controleur.getHauteurPont();

        for (int lig = 0; lig < this.controleur.getHauteurPont(); lig++)
            for (int col = 0; col < this.controleur.getLargeurPont(); col++)
            {

                conteneur = this.controleur.getConteneurs()[col][lig];
                baseX = col * largeurConteneur;
                baseY = lig * hauteurConteneur;

                drawConteneur(g2, conteneur, baseX, baseY, largeurConteneur, hauteurConteneur);
            }
    }

    private void drawConteneur(Graphics2D g2d, Conteneur conteneur, double x, double y, double largeur, double hauteur)
    {
        int startX = (int) x            + GAP_WIDTH;
        int endX   = (int)(x + largeur) - GAP_WIDTH;
        int startY = (int) y            + GAP_WIDTH;
        int endY   = (int)(y + hauteur) - GAP_WIDTH;
        int midX   = (int)(x + largeur / 2);
        int midY   = (int)(y + hauteur / 2);

        largeur = endX - startX;
        hauteur = endY - startY;

        g2d.drawArc(startX - ARC_RADIUS, startY - ARC_RADIUS, 2*ARC_RADIUS, 2*ARC_RADIUS,    0, -90);
        g2d.drawArc(endX   - ARC_RADIUS, startY - ARC_RADIUS, 2*ARC_RADIUS, 2*ARC_RADIUS, - 90, -90);
        g2d.drawArc(endX   - ARC_RADIUS, endY   - ARC_RADIUS, 2*ARC_RADIUS, 2*ARC_RADIUS, -180, -90);
        g2d.drawArc(startX - ARC_RADIUS, endY   - ARC_RADIUS, 2*ARC_RADIUS, 2*ARC_RADIUS, -270, -90);

        g2d.drawLine(startX + ARC_RADIUS, startY, endX - ARC_RADIUS, startY);
        g2d.drawLine(startX, startY + ARC_RADIUS, startX, endY - ARC_RADIUS);
        g2d.drawLine(startX + ARC_RADIUS, endY  , endX - ARC_RADIUS, endY  );
        g2d.drawLine(endX, startY + ARC_RADIUS, endX, endY - ARC_RADIUS);

        for (int i = 0; i < 4; i++)
        {
            if (conteneur.getLock(i) != null)
            {
                int lockX = 0, lockY = 0;

                switch (i)
                {
                    case 0:
                        lockX = startX - GAP_WIDTH; lockY = startY - GAP_WIDTH; break;
                    case 1:
                        lockX = endX + GAP_WIDTH; lockY = startY - GAP_WIDTH; break;
                    case 2:
                        lockX = endX + GAP_WIDTH; lockY = endY + GAP_WIDTH; break;
                    case 3:
                        lockX = startX - GAP_WIDTH; lockY = endY + GAP_WIDTH; break;
                }

                int joueur = controleur.getNumeroJoueur(conteneur.getLock(i).getJoueur());

                g2d.setColor(Apparence.getJoueurCouleur(joueur));

                g2d.fillOval(lockX - ARC_RADIUS, lockY - ARC_RADIUS, 2*ARC_RADIUS, 2*ARC_RADIUS);
            }
        }

        Joueur joueurMajoritaire = conteneur.joueurMajoritaire();

        if (joueurMajoritaire != null)
        {
            int joueur = controleur.getNumeroJoueur(joueurMajoritaire);

            g2d.setColor(Apparence.getJoueurCouleur(joueur));

            g2d.fillOval(midX - ARC_RADIUS*2, midY - ARC_RADIUS*2, 4 * ARC_RADIUS, 4 * ARC_RADIUS);
        }

        g2d.setColor(Color.black);

        FontMetrics metrics = g2d.getFontMetrics();

        String valeurConteneur = String.valueOf(conteneur.getValeur());

        Rectangle2D bounds = metrics.getStringBounds(String.valueOf(conteneur.getValeur()), g2d);

        g2d.drawString(valeurConteneur, (int)(midX - (bounds.getWidth() / 2)), (int)(midY + (bounds.getHeight() / 2)));
    }

    public void mouseClicked(MouseEvent e)
    {
        int col = e.getX() / largeurConteneur;
        int lig = e.getY() / hauteurConteneur;

        int x = e.getX() - col * largeurConteneur;
        int y = e.getY() - lig * hauteurConteneur;

        // Gestion des situations de bord
        if (col >= this.controleur.getLargeurPont())
        {
            col--;
            x += largeurConteneur;
        }

        if (lig >= this.controleur.getHauteurPont())
        {
            lig--;
            y += hauteurConteneur;
        }

        boolean surCoin = false;
        int coin = -1;

        // Détermination du coin cliqué

        // Coin supérieur gauche
        if (x <= ARC_RADIUS && y <= ARC_RADIUS)
        {
            coin = 0;
        }
        // Coin supérieur droit
        else if (x >= (largeurConteneur - ARC_RADIUS) && y <= ARC_RADIUS)
        {
            coin = 1;
        }
        // Coin inférieur droit
        else if (x >= (largeurConteneur - ARC_RADIUS) && y >= (hauteurConteneur - ARC_RADIUS))
        {
            coin = 2;
        }
        // Coin inférieur gauche
        else if (x <= ARC_RADIUS && y >= (hauteurConteneur - ARC_RADIUS))
        {
            coin = 3;
        }

        Logger.information("Jeu sur Case (" + col + ":" + lig + ") coin " + coin);
        controleur.jouer(col, lig, coin);
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}
