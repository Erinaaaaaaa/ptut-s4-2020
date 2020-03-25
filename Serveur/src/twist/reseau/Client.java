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
	}
}
