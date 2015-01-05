/**
 * 
 */
package tp_ev3;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import lejos.hardware.lcd.LCD;
/**
 * @author carouko
 *
 */
public class Communication {
	private DatagramSocket socketSent;
	private DatagramSocket socketReceived;
	private DatagramPacket packetSent;
	private DatagramPacket packetReceived;
	final private int SIZE = 1024;
	private byte[] buffer= new byte[SIZE];
	private byte[] buffer2= new byte[SIZE];
	
	public Communication(InetAddress ip,int sPort, int dPort) throws SocketException {
	// TODO Auto-generated constructor stub
		socketSent = new DatagramSocket();
		socketReceived = new DatagramSocket(sPort);
	}
	
	public void runServer(InetAddress ip,int sPort) throws IOException, InterruptedException{
		int i = 0;
		while(true){
			buffer = String.valueOf(i).getBytes();
			packetSent = new DatagramPacket(buffer, buffer.length, ip, sPort);
			socketSent.send(packetSent);
			LCD.drawString(">>>"+ (new String(packetSent.getData(),"UTF-8")), 2, 4);
			Thread.sleep(100);
			Thread.yield();
			i++;
		}
	}
	
	public void runClient(InetAddress ip,int sPort) throws IOException, InterruptedException {
		while (true){
			packetReceived = new DatagramPacket(buffer2, buffer2.length, ip, sPort);
			socketReceived.receive(packetReceived);
			LCD.drawString("<<<"+ (new String(packetReceived.getData(),"UTF-8")), 2, 5);
			Thread.sleep(100);
			Thread.yield();
		}
	}
	}

