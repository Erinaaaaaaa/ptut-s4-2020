package twist.net;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;

import twist.metier.*;

public class ClientUDP
{
    private final int PACKET_MAX_LENGTH = 350;

    private byte[] inputBytes  = new byte[PACKET_MAX_LENGTH];
    private byte[] outputBytes = new byte[0];

    private DatagramPacket outputPacket;
    private DatagramPacket inputPacket;

    private DatagramSocket socket;

    public ClientUDP(String ipAddr, int port) throws UnknownHostException, SocketException
    {
        this.socket = new DatagramSocket();
        this.socket.connect(InetAddress.getByName(ipAddr), port);

        this.outputPacket = new DatagramPacket(outputBytes, outputBytes.length, InetAddress.getByName(ipAddr), port);
        this.inputPacket  = new DatagramPacket(inputBytes , PACKET_MAX_LENGTH);
    }

    public void envoyer(String text) throws IOException
    {
        System.out.println("SENDING: " + text);

        this.outputBytes = text.getBytes();

        this.outputPacket.setData  (this.outputBytes);
        this.outputPacket.setLength(this.outputBytes.length);

        this.socket.send(this.outputPacket);
    }

    public Conteneur[][] recupererConteneurs(String message)
    {

        int nbLignes   = message.split("\\|").length;
        int nbColonnes = message.split("\\|")[0].split(":").length;

        Conteneur[][] tabConteneurs = new Conteneur[nbLignes][nbColonnes];

        String ligneActuelle = "";
        for(int i = 0; i < nbLignes; i++)
        {
            ligneActuelle = message.split("\\|")[i];
            for(int j = 0; j < nbColonnes; j++)
            {
              int valeur = Integer.parseInt(ligneActuelle.split(":")[j]);
              Conteneur cont = new Conteneur(valeur);
              tabConteneurs[i][j] = cont;
            }
        }

        return tabConteneurs;
    }

    public String lire() throws IOException
    {
        this.inputBytes = new byte[PACKET_MAX_LENGTH];
        this.inputPacket.setData(this.inputBytes);

        this.socket.receive(this.inputPacket);

        return new String(this.inputBytes).trim();
    }


}
