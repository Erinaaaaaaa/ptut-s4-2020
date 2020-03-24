package twist.reseau;
import twistlock.ControleurGui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Serveur extends Thread {
	private ControleurGui controleur;
	private String adresseIP;
	private ArrayList<ClientServeur> clients;
	private DatagramSocket datagramSocket;

	public Serveur(ControleurGui controleur, String adresseIP) {
		this.controleur = controleur;
		this.adresseIP = adresseIP;
		this.clients = new ArrayList<>();

		try {
			this.datagramSocket = new DatagramSocket(this.portConnexion);
			this.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

	}
}
