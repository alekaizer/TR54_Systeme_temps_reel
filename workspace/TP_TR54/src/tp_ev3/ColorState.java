/**
 * 
 */
package tp_ev3;

/**
 * @author carouko
 *
 */
public class ColorState {

	private int black ;
	private int white ;
	private int actualColor;
	
	

	public ColorState() {
	}

	public synchronized void setActualColor(int val){
		actualColor=val;
	}
	
	public synchronized int getActualColor() throws InterruptedException{
		this.wait();
		return actualColor;
	}
	
	public int getBlack() {
		return black;
	}

	public void setBlack(int black) {
		this.black = black;
	}

	public int getWhite() {
		return white;
	}

	public void setWhite(int white) {
		this.white = white;
	}

}
