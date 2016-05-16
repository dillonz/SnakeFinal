package graphics;

public class Pixel {

	private int red;
	private int green;
	private int blue;
	
	public Pixel(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public int getRed() {
		return this.red;
	}
	
	public int getBlue() {
		return this.blue;
	}
	
	public int getGreen() {
		return this.green;
	}
}
