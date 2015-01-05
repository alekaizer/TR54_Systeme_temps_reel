/**
 * 
 */
package tp_ev3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.filter.MeanFilter;

/**
 * @author carouko
 *
 */
public class CapteurDist {
	
	private EV3UltrasonicSensor captDist;
	
	public CapteurDist(){
		captDist=new EV3UltrasonicSensor(SensorPort.S3);
	}
	
	public float distance(){
		float[] distance=new float[1];
		captDist.getDistanceMode().fetchSample(distance, 0);
		return distance[0]; 
	}
	
	public void displayDistance(){
		LCD.drawString(String.valueOf((distance()*100)), 2, 2);
	}
	
	public float distanceMoyenne(int n){
		MeanFilter distmoy=new MeanFilter(captDist.getDistanceMode(),n);
		float[] sample = new float[n];
		distmoy.fetchSample(sample, 0);
		return sample[0];
	}
}
