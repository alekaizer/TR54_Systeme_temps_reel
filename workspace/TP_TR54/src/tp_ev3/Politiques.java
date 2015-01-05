package tp_ev3;

import lejos.robotics.navigation.DifferentialPilot;

public class Politiques {
	private DifferentialPilot moteur;
	private CapteurDist capteurdist;
	
	public Politiques(DifferentialPilot pilot){
		moteur= pilot;
		capteurdist=new CapteurDist();
	}
	
	/**
	 * This method implements the Tout ou rien policy, it makes the robots <br>
	 * move forward with 40% of his default speed, and stops when the distance<br>
	 * between the robot and the obstacle in front of its is less than distance<br>
	 * specified
	 * @param speed the forward speed
	 * @param distance the minimum distance allowed between robot and obstacle,<br> Units used is <b>centimeter</b>
	 */
	public void toutOuRien(int speed, float distance){
		while (true){
			float measuredDistance = (capteurdist.distance()*100);
			if(measuredDistance<distance){
				moteur.stop();
			}
			else{
				moteur.setTravelSpeed(speed);
				moteur.forward();
			}
		}
	}
	
	/**
	 * This method implements the A un Point policy, it makes the robot move forward <br>
	 * with the speed calculated based on the distance between the robot and the obstacle<br>
	 * in front of it.
	 * @param D The minimum distance allowed between robot and obstacle<br> Units used is <b>centimeter</b>
	 * @param alpha The multiplying factor
	 */
	public void aUnPoint(float D, int alpha){
		int speed;
		float measuredDistance=0;
		while(true){
			speed= (int) Math.max(Math.min(50, (alpha*(measuredDistance-D))), 0);
			measuredDistance = capteurdist.distance()*100;
			if(measuredDistance<D) {
				moteur.stop();
			} else {
				moteur.setTravelSpeed(speed);
				moteur.forward();
			}
			
		}
	}
	

	/**
	 * This method implements the A Deux Point policy, it makes the robot move forward <br>
	 * with the speed calculated based on the distance between the robot and the obstacle<br>
	 * in front of it. This policy is different from the A un point policy, because this <br>
	 * policy is also based on the previous speed of the robot.
	 * @param D The minimum distance allowed between robot and obstacle
	 * @param alpha The multiplying factor
	 * @see aUnPoint
	 */
	public void aDeuxPoints(float D, int alpha){
		int speed = 0;
		float measuredDistance = 0;
		
		while(true){
			speed= (int)Math.min(Math.max((2.5*(capteurdist.distance()-20)),
						Math.min(Math.max(alpha*(capteurdist.distance()-D),0), speed)), 50);
			measuredDistance = capteurdist.distance()*100;
			if(measuredDistance<D) {
				moteur.stop();
			} else {
				moteur.setTravelSpeed(speed);
				moteur.forward();
			}
			
		}
	}
}
