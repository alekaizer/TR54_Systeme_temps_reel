/**
 * 
 */
package tp_ev3;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * @author carouko
 *
 */
public class Moteur {
	private RegulatedMotor moteur1;
	private RegulatedMotor moteur2;
	
	public Moteur(){
		moteur1 = new EV3LargeRegulatedMotor(MotorPort.B);
		moteur2 = new EV3LargeRegulatedMotor(MotorPort.C); 
	}
	public void forward(){
		moteur1.forward();
		moteur2.forward();		
	}
	
	public void forward(int speed){
		moteur1.setSpeed(speed);
		moteur2.setSpeed(speed);
		forward();
	}
	
	public void stop(){
		moteur1.stop();
		moteur2.stop();
	}
	
	public void rotate(int angle){
		moteur1.rotate(angle);
		//moteur2.rotate(angle);
	}
}
