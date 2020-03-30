package twist.ihm;

import java.awt.*;
import java.awt.font.*;
import java.util.Map;

import javax.swing.*;
import twist.ihm.jeu.PanelJoueur;

public class Apparence
{
    private static final Color[] TABCOULJOUEUR = {Color.RED, Color.GREEN, Color.BLUE, new Color(255, 215, 0)};
    private static final Color[] TABCOULGENERAL = {new Color(7, 16, 19), new Color(35, 181, 211), new Color(117, 171, 188), new Color(152, 164, 177), new Color(223, 224, 226)};

    public static void setStyleBtnPrincipale(JButton btn)
    {
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        btn.setBackground(TABCOULGENERAL[1]);
        btn.setForeground(TABCOULGENERAL[4]);
        btn.setFont(new Font("Helvetica", Font.BOLD, 26));
    }

    public static void setStyleBtnAction(JButton btn)
    {
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setBackground(TABCOULGENERAL[2]);
        btn.setForeground(TABCOULGENERAL[4]);
        btn.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                btn.setBackground(TABCOULGENERAL[1]);
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                btn.setBackground(TABCOULGENERAL[2]);
            }
        });
        btn.setFont(new Font("Helvetica", Font.BOLD, 15));
    }

    public static void setStyleLbl(JLabel lbl, Color coulJ)
    {
        lbl.setFont(new Font("Helvetica", Font.BOLD, 15));
        lbl.setForeground(coulJ);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void setStyleTitle(JLabel lbl)
    {
        lbl.setForeground(TABCOULGENERAL[0]);
        lbl.setFont(new Font("Helvetica", Font.BOLD, 50));
        Font font = lbl.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        lbl.setFont(font.deriveFont(attributes));
    }

    public static Color getJoueurCouleur(int id)
    {
        return TABCOULJOUEUR[id];
    }

    public static void setBackgroundPlateau(JPanel pan)
    {
        pan.setBackground(TABCOULGENERAL[4]);
    }

    public static void setBackgroundPanelJoueur(PanelJoueur panJ, int idJ)
    {
        if (panJ.getNbJoueur() == idJ)
        {
            panJ.setBackground(TABCOULGENERAL[1]);
        } else
        {
            panJ.setBackground(TABCOULGENERAL[2]);
        }
    }
}
