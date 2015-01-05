/**
 * 
 */
package tp_ev3;


import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * @author carouko
 *
 */
public class SuiveurDeLigneThreads {

	private CapteurCouleur colorSensor ;
	private DifferentialPilot pilot ;
	private int lastColor ;
	private int color ;
	
	public SuiveurDeLigneThreads() {
		pilot = new DifferentialPilot(5, 12, Motor.C, Motor.B);
		colorSensor = new CapteurCouleur();
		lastColor = 1;
	}
	
	public void run() {
		Thread motorsController = new Thread(new Runnable() {
			
			@Override
			public void run() {
			while (true){
				switch (color) {
				case lejos.robotics.Color.BLACK :
							isBlack();
							break;
				case lejos.robotics.Color.WHITE :
							isWhite();
							break;
							}
				}
			}
		});
		
		Thread sensorController = new Thread(new Runnable(){
			@Override
			public void run(){
				color = colorSensor.getColor();
			}
		});
		
		
		motorsController.start();
		sensorController.start();
		
	}
	

	private void isWhite() {
		// TODO Auto-generated method stub
		if (lastColor == 1)
			pilot.rotate(-20, true);
		if (lastColor == 2)
			pilot.rotate(20,true);
		lastColor = 2;
	}

	private void isBlack() {
		// TODO Auto-generated method stub
		pilot.forward();
		lastColor = 1;
	}
}
