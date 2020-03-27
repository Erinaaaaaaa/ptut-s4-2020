package twist.net;

import java.net.InetSocketAddress;

/*
 * Classe Message.java
 * Classe servant de base aux messages envoyés et reçus par le serveur
 * On y définit le message ainsi que l'adresse
 */

public class Message
{
    private final String message;
    private final InetSocketAddress adresse;

    public Message(String message, InetSocketAddress adresse)
    {
        this.message = message;
        this.adresse = adresse;
    }

    public String getMessage() { return this.message; }
    public InetSocketAddress getAdresse() { return this.adresse; }
}
