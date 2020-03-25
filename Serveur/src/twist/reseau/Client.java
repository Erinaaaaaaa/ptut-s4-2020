package twist.reseau;
import twistlock.ControleurGui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {

	private ControleurGui ctrl;
	private String adresse;
	private int port;

	public Client(ControleurGui ctrl, String adresse, int port) {
		this.ctrl = ctrl;
		this.adresse = adresse;
		this.port = port;

		try {
			Socket serveur = new Socket(adresse, port);
		} catch(Exception e) {e.printStackTrace();}
	}

	public void lireMessage(String message) {
		String debutMessage = message.substring(2);

		switch (debutMessage) {
			case "01":
				break;
			case "10":
				break;
			case "20":
				break;
			case "50":
				break;
			case "88":
				break;
			default :
				System.out.println("Erreur message reçu");
			 	break;
		}
	}
}
