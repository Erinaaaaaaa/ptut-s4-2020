package twist.net;

import twist.util.Logger;

import java.io.IOException;
import java.net.*;

/*
 * Classe ServeurUdp.java
 * Classe de base pour le serveur en protocole UDP
 * Cette classe permettra les intéractions avec le ClientUdp
 */

public class ServeurUdp
{
    private final static int MAX_PACKET_SIZE = 2048;
    private final int port;

    DatagramSocket socket;

    public ServeurUdp(int port) throws SocketException
    {
        this.port = port;
        this.socket = new DatagramSocket(port);
    }

    public int getPort()
    {
        return this.port;
    }

    public Message lireMessage() throws IOException
    {
        byte[] inputBuffer = new byte[MAX_PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(inputBuffer, inputBuffer.length);
        socket.receive(packet);
        String texte = new String(packet.getData()).trim();
        Logger.verbose(String.format("[From %s] %s", packet.getSocketAddress().toString(), texte));
        return new Message(texte, (InetSocketAddress) packet.getSocketAddress());
    }

    public void envoyerMessage(Message message) throws IOException
    {
        envoyerMessage(message.getMessage(), message.getAdresse());
    }

    public void envoyerMessage(String texte, InetSocketAddress destination) throws IOException
    {
        Logger.verbose(String.format("[To   %s] %s", destination.toString(), texte));

        byte[] textBytes = texte.getBytes();

        DatagramPacket packet = new DatagramPacket(textBytes, textBytes.length, destination);

        socket.send(packet);
    }
}
