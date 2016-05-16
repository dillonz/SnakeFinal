package graphics;

public class Box {

	private int timeAlive;
	private int[] position;
	
	public Box(int x, int y, int snakeLength) {
		this.position = new int[2];
		this.position[0] = x;
		this.position[1] = y;
		this.timeAlive = (snakeLength - 1) * 10;
	}
	
	public void increment() {
		this.timeAlive--;
	}
	
	public int timeLeft() {
		return this.timeAlive;
	}
	
	public int x() {
		return this.position[0];
	}
	
	public int y() {
		return this.position[1];
	}

}
