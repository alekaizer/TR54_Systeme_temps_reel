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
public class Communication_V2 {
		private DatagramSocket sendingSocket;
		private DatagramPacket packetSent;
		
		private DatagramSocket receivingSocket;
		private DatagramPacket packetReceived;

		final private int SIZE = 1;
		private byte[] buffer = new byte[SIZE];
		
		
		public Communication_V2(int sPort) {
			try {
				sendingSocket = new DatagramSocket();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				receivingSocket =  new DatagramSocket(sPort);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void sendData(byte[] buffer,InetAddress ipAdress, int dPort) throws IOException{			
			packetSent = new DatagramPacket(buffer, buffer.length, ipAdress, dPort);
			sendingSocket.send(packetSent);
			LCD.drawString(">>>"+(new String(packetSent.getData(),"UTF-8")),2,2);
		}
		
		public String receiveData(InetAddress ipAddress, int sPort) throws IOException{
			packetReceived = new DatagramPacket(buffer, buffer.length, ipAddress, sPort);
			receivingSocket.setSoTimeout(5); 
			receivingSocket.receive(packetReceived);
			String s =new String(packetReceived.getData(),"UTF-8");
			return s;
		}
		
		

}
