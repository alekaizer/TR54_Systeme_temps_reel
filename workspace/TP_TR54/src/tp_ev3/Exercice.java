/**
 * 
 */
package tp_ev3;

import java.io.IOException;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.Color;
import lejos.robotics.DirectionFinderAdaptor;
import lejos.robotics.localization.CompassPoseProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;


/**
 * @author carouko
 * 
 */
public class Exercice {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		Navigation lineFollower = new Navigation();
		lineFollower.run();
		//lineFollower.com();
	}
	
	public static void arretCouleur(){
		@SuppressWarnings("resource")
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
		Moteur moteur = new Moteur();
		while(colorSensor.getColorID()!= Color.RED) {
				moteur.forward();
		}
		moteur.stop();
	}

	public static void DiffentialPiloting(){
		DifferentialPilot diffPilot = new DifferentialPilot(5, 12, Motor.C, Motor.B);
		@SuppressWarnings("resource")
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
		while(colorSensor.getColorID()!= Color.RED) {
			diffPilot.rotate(30);
		}
		diffPilot.stop();
	}
	
	public static void NavigatorPilot(){
		Navigator gouvernail = new Navigator(new DifferentialPilot(5, 12, Motor.C, Motor.B));
		Path trajet = new Path();
		trajet.add(new Waypoint(0,0));
		trajet.add(new Waypoint(0,20));
		trajet.add(new Waypoint(20,20));
		trajet.add(new Waypoint(20,0));
		trajet.add(new Waypoint(0,0));		
		gouvernail.setPath(trajet);
		gouvernail.followPath();
		gouvernail.waitForStop();		
	}
	
	public static void odometryPilot(){
		DifferentialPilot pilote=new DifferentialPilot(5, 12, Motor.C, Motor.B);
		Navigator gouvernail = new Navigator(pilote, new OdometryPoseProvider(pilote));
		Path trajet = new Path();
		trajet.add(new Waypoint(0,0));
		trajet.add(new Waypoint(0,20));
		trajet.add(new Waypoint(20,20));
		trajet.add(new Waypoint(20,0));
		trajet.add(new Waypoint(0,0));		
		gouvernail.setPath(trajet);
		gouvernail.followPath();
		gouvernail.waitForStop();		
	}
	
	public static void compassPilot(){
		DifferentialPilot pilote=new DifferentialPilot(5, 12, Motor.C, Motor.B);
		@SuppressWarnings("resource")
		Navigator gouvernail = new Navigator(pilote, new CompassPoseProvider(pilote, new DirectionFinderAdaptor(new EV3GyroSensor(SensorPort.S2).getAngleMode())));
		Path trajet = new Path();
		trajet.add(new Waypoint(0,0));
		trajet.add(new Waypoint(0,20));
		trajet.add(new Waypoint(20,20));
		trajet.add(new Waypoint(20,0));
		trajet.add(new Waypoint(0,0));		
		gouvernail.setPath(trajet);
		gouvernail.followPath();
		gouvernail.waitForStop();		
	}
}
