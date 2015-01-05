/**
 * 
 */
package tp_ev3;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

/**
 * @author carouko<br>
 * Student in Computers Science at UTBM<br>
 * Twitter <b>@alekaizer</b><br>
 * Github <b>lekaizer</b>
 *
 */
public class CapteurCouleur {
	private EV3ColorSensor colorSensor;
	
	/**
	 * The default constructor, it instantiates the IRsensor object.
	 * On the robot's port 1 
	 */
	public CapteurCouleur(){
		colorSensor = new EV3ColorSensor(SensorPort.S2);
		
	}
	
	/**
	 * This method is used to specify for which color the robot has to stop.<br>
	 * The parameter should be a standard Color int (eg: Color.Red, Color.BLACK)
	 * @param color The color parameter
	 */
	public void trafficLights(Moteur moteur, int color){
		if(colorSensor.getColorID() == color){
			moteur.stop();
		}
	}
	
	public int getColor (){
		return colorSensor.getColorID();
	}
}
