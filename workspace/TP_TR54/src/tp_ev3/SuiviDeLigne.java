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
public class SuiviDeLigne {
	private CapteurCouleur colorSensor ;
	private DifferentialPilot pilot ;
	final private int LEFT = 1;
	final private int RIGHT = -1;
	final private double WHEEL_CIRCUMFERENCE = (2*Math.PI*5)/3;
	private int ROTATION_ANGLE;
	private int actualColor;
	private int orientation;
	
	public SuiviDeLigne() {
		pilot = new DifferentialPilot(5, 12, Motor.C, Motor.B);
		colorSensor = new CapteurCouleur();
		orientation = 0;
		actualColor =0;
		ROTATION_ANGLE=10;
	}
	
	public int run() {
		int coefRot = 0;
		actualColor = colorSensor.getColor();
		double speed=WHEEL_CIRCUMFERENCE; 
		Delay.msDelay(10);
		LCD.drawString(String.valueOf(actualColor), 2, 2); 
		while(true) {
			actualColor = colorSensor.getColor();
			LCD.drawInt(actualColor, 2, 1); 
			switch (actualColor) {
			case Color.BLACK :	if (speed <= 20)
									speed = 1.1*speed;
								isBlack(speed);
								coefRot = 0;
								break;
			case Color.WHITE :	coefRot++;
								speed=WHEEL_CIRCUMFERENCE;
								isWhite(coefRot);
								break;
			
			}		
		}
	}
	
	public void isBlack (double speed){
			ROTATION_ANGLE=10;
			pilot.setTravelSpeed(speed);
			pilot.forward();
			LCD.drawString(String.valueOf(pilot.getTravelSpeed()), 2, 2); 
	}
	
	public void isWhite (int coefRot){
		pilot.setTravelSpeed(WHEEL_CIRCUMFERENCE);
		pilot.setRotateSpeed(15);
		if(orientation == 0){
			pilot.rotate(15);// left
			if ((colorSensor.getColor()==Color.BLACK))
				orientation = LEFT;
			if ((colorSensor.getColor()==Color.WHITE)) {
				pilot.rotate(-30);
				if (colorSensor.getColor()==Color.BLACK)
					orientation= RIGHT;
				else pilot.travel(2);
			}
		} else {
			//ROTATION_ANGLE=10;
			pilot.rotate(orientation*ROTATION_ANGLE);
			if ((colorSensor.getColor()==Color.WHITE)){
				ROTATION_ANGLE+=(2*ROTATION_ANGLE/3);
				orientation= -1*orientation;
				pilot.rotate(orientation*ROTATION_ANGLE,true);
			}
			LCD.drawString("RotAngle=", 2, 4);
			LCD.drawString(String.valueOf(ROTATION_ANGLE), 14, 4);
		}
				
	}
	
	public void setRotationAngle(int angle){
		ROTATION_ANGLE= angle;
	}
	
	
}
