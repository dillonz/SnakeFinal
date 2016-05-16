package graphics;

public class Food {

	private int[] position;
	
	public Food(boolean[][] taken) {
		this.position = new int[2];
		position[0] = 50;
		position[1] = 50;
		int[] tempPos = new int[2];
		tempPos[0] = rand();
		tempPos[1] = rand();
		while (position[0] == 50 && position[1] == 50) {
			if (!taken[tempPos[0]][tempPos[1]]) {
				this.position[0] = tempPos[0];
				this.position[1] = tempPos[1];
			}
		}
	}
	
	private int rand() {
		return (int)(Math.random() * 50);
	}
	
	public int[] getPos() {
		return position;
	}
}
