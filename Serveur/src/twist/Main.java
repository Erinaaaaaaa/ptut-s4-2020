package twist;

import twist.net.Message;
import twist.net.ServeurJeu;
import twist.net.ServeurUdp;
import twist.util.Logger;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        new ServeurJeu(57300, 2).run();
    }
}
