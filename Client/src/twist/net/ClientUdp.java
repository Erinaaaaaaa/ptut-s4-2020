package twist.net;

import twist.util.Logger;

import java.io.IOException;
import java.net.*;

/*
 * Classe ClientUdp.java
 * Classe de base pour se connecter au serveur
 * Classe servant aux intéractions avec le ServeurUdp
 */

public class ClientUdp
{
    private final int PACKET_MAX_LENGTH = 350;

    private byte[] inputBytes = new byte[PACKET_MAX_LENGTH];
    private byte[] outputBytes = new byte[0];

    private final DatagramPacket outputPacket;
    private final DatagramPacket inputPacket;

    private final DatagramSocket socket;

    private final String fullHost;

    public ClientUdp(String ipAddr, int port) throws UnknownHostException, SocketException
    {
        this.fullHost = ipAddr + ":" + port;

        this.socket = new DatagramSocket();
        this.socket.connect(InetAddress.getByName(ipAddr), port);

        this.outputPacket = new DatagramPacket(outputBytes, outputBytes.length, InetAddress.getByName(ipAddr), port);
        this.inputPacket = new DatagramPacket(inputBytes, PACKET_MAX_LENGTH);
    }

    public void envoyer(String text) throws IOException
    {
        Logger.verbose("[To   " + fullHost + "] " + text);

        this.outputBytes = text.getBytes();

        this.outputPacket.setData(this.outputBytes);
        this.outputPacket.setLength(this.outputBytes.length);

        this.socket.send(this.outputPacket);
    }

    public String lireMessage() throws IOException
    {
        this.inputBytes = new byte[PACKET_MAX_LENGTH];
        this.inputPacket.setData(this.inputBytes);

        this.socket.receive(this.inputPacket);

        String s = new String(this.inputBytes).trim();

        Logger.verbose("[From " + fullHost + "] " + s);

        return s;
    }


    public boolean connecte()
    {
        return !this.socket.isClosed();
    }
}
