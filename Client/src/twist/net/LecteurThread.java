package twist.net;

import twist.metier.Conteneur;
import twist.util.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LecteurThread implements Runnable
{
    private ControleurReseau ctrl;

    public LecteurThread(ControleurReseau ctrl)
    {
        this.ctrl = ctrl;
    }

    Pattern p = Pattern.compile("([1-9][A-Z][1-4])");

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                String[] s = ctrl.lireMessage().split("-", 2);

                if (s[0].length() == 1)
                {
                    ctrl.setJoueurLocal(Integer.parseInt(s[0]) - 1);
                }
                else
                {
                    switch (s[0])
                    {
                        case "01":
                        {
                            Conteneur[][] conteneurs = ctrl.interpreterMap(s[1].split("\\n")[1].substring(4));
                            ctrl.creerPont(conteneurs);
                        }
                        break;
                        case "10":
                        {
                            // Pas nécessairement utile?
                        }
                        break;
                        case "20":
                        {
                            Matcher m = p.matcher(s[1]);
                            if (m.find())
                            {
                                String jeu = m.group(1);

                                int lig =  jeu.charAt(0) - '1';
                                int col =  jeu.charAt(1) - 'A';
                                int coin = jeu.charAt(2) - '1';

                                ctrl.jouerLocal(col, lig, coin);
                            }
                        }
                        break;
                        case "22":
                        {
                            // Coup adversaire illégal
                            ctrl.jouerLocal(-1, -1, -1);
                        }
                        break;
                        case "88":
                        {
                            ctrl.finPartie();
                        }
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                Logger.error("Impossible de lire un message! " + e.getMessage());
            }

        }
    }


}
