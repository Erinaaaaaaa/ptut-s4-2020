package twist;

import twist.net.ServeurJeu;
import twist.util.Logger;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        new ServeurJeu(57300, 2).run();
    }
}