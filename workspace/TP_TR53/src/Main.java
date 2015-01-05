import java.io.IOException;
import java.net.InetAddress;

/**
 * 
 */

/**
 * @author lekaizer
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	/*	byte[] buffer = new byte[1024];
		@SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket(8532);
		DatagramPacket packet = null;
		while(true){
			packet = new DatagramPacket(buffer, buffer.length,InetAddress.getByName("192.168.43.255"), 8532);
			socket.receive(packet);
			System.out.println("<<< "+ (new String(packet.getData(),"UTF-8")));
		}*/
		TrafficLights traffic = new TrafficLights(InetAddress.getByName("192.168.43.255"), 8532);
		traffic.start();
		
	}

}
