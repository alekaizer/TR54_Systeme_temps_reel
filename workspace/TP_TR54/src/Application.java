import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import tp_ev3.Communication_V2;
import tp_ev3.Navigation;

/**
 * 
 */

/**
 * @author lekaizer
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Navigation pilot = new Navigation();
		final Communication_V2 comModule = new Communication_V2(10000);
		
		
		Thread comMgr = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
					while(true){
					synchronized (pilot) {
						// envoi de donnée
						try {
							comModule.sendData(new byte[1024], InetAddress.getByName("192.168.4.255"), 10000);
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					try {
						comModule.receiveData(InetAddress.getByName("192.168.4.255"), 10000);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread.yield();
				}
			}
		});
		
		pilot.start();
		comMgr.start();
	}

}
