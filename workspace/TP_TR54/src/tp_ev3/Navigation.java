/**
 * 
 */
package tp_ev3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

/**
 * @author carouko
 *
 */
public class Navigation extends Thread {
	private CapteurCouleur colorSensor ;
	private CapteurDist distanceSensor;
	private DifferentialPilot pilot ;
	final private double WHEEL_CIRCUMFERENCE = (2*Math.PI*5)/3;
	private int voie;
	private static float distance ;

	
	public Navigation() {
		pilot = new DifferentialPilot(5, 12, Motor.C, Motor.B);
		colorSensor = new CapteurCouleur();
		distanceSensor = new CapteurDist();
		
		distance = 0;
		voie = 1;
	}
	
	
	
	public void run() {
		int coefVit = 1;
		while(true) {
			Delay.msDelay(10);
			switch (colorSensor.getColor()) {
			
			case Color.BLACK :								
								isBlack();
								break;
			case Color.BLUE :	
							coefVit+=0.1*coefVit;
								isBlue(coefVit);
								break;
			case Color.WHITE :
								coefVit=0;
								isWhite();
								break;
			case Color.RED : // 0 ---> Orange
						distance = 0;
						voie++;
						if(voie>2)
							voie=1;
						isBlue(coefVit);
						notify();
			
			}
			if(distanceSensor.distance() < 15)
				pilot.stop();
			
		}
	}
	
	// when black rotate on left
	public void isBlack (){
			pilot.setTravelSpeed(WHEEL_CIRCUMFERENCE);
			pilot.setRotateSpeed(-10);	
			pilot.travelArc(15,2, true);				
			LCD.drawString(String.valueOf(pilot.getTravelSpeed()), 2, 2); 
	}
	
	//when blue move forward
	public void isBlue (int coefVit){
		
		pilot.setTravelSpeed(WHEEL_CIRCUMFERENCE+(WHEEL_CIRCUMFERENCE*coefVit/190));
		pilot.forward();
		LCD.drawString(String.valueOf(pilot.getTravelSpeed()), 2, 2);
}
	
	// when white rotate on right
	public void isWhite (){
		pilot.setTravelSpeed(WHEEL_CIRCUMFERENCE);
		pilot.setRotateSpeed(10);
		pilot.travelArc(-15, 2, true);	
		LCD.drawString(String.valueOf(pilot.getTravelSpeed()), 2, 2); 		
	}
	
	// find lines on the circuit
	public void findLine(int color){
		while (colorSensor.getColor() != color) {
			pilot.setTravelSpeed(WHEEL_CIRCUMFERENCE);
			pilot.setRotateSpeed(10);
			pilot.rotate(15, true);
			if (colorSensor.getColor()!= color){
				pilot.rotate(-30, true);
				if (colorSensor.getColor() != color)
					pilot.travel(3);
				 else break;
			} else break;
		}
	}
	
	//calculate the traveled distance
	synchronized public float distanceTraveled(){
			return  (distance+=pilot.getMovement().getDistanceTraveled());			
	}
	
	public int getNumeroVoie(){
		return voie;
	}
	
}
