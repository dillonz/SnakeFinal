package graphics;

import java.util.*;

public class Block {
	private int x;  //X position of box
	private int y;  //Y position of box
	private int width;  //Width of box
	private int height;  //Height of box
	private int[] direction;
	private List<Integer> movement;
	private List<Integer> waitTime;
	
	private final int[][] dirs= {
			{0, -1},
			{1, 0},
			{0, 1},
			{-1, 0}
	};
	
	public Block(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		width = 10;
		height = 10;
		direction = new int[2];
		direction[0] = dx;
		direction[1] = dy;
		movement = new ArrayList<Integer>();
		waitTime = new ArrayList<Integer>();
	}
	
	private void setDir(int dir) {
		if (Math.abs((double)direction[0]) != Math.abs((double)dirs[dir][0]))
				direction = dirs[dir];
	}
	
	/**
	 * @return X coordinate of box
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * @return Y coordinate of box
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * @return Width of box
	 */
	public int getWidth() {
		return this.width;
	}
	
	
	/**
	 * @return Height of box
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * @return Direction box is going in terms of change in X and change in Y
	 */
	public int[] getDirection() {
		return this.direction;
	}
	
	public List<Integer> getMoves() {
		return movement;
	}
	
	public List<Integer> getWaitTime() {
		return waitTime;
	}
	
	/**
	 * Adds a direction the block will move in the future
	 * @param i Holds the direction it is adding
	 * @param position Holds position of block in the snake
	 */
	public void addMove(int i, int position) {
		movement.add(new Integer(i));
		waitTime.add(new Integer(position));
	}
	
	/**
	 * Moves the block in right direction
	 */
	public void move() {
		this.x += direction[0];
		this.y += direction[1];
	}
	
	public void changeDir() {
		if (movement.size() > 0 && waitTime.get(0).intValue() == 0) {
			int i = movement.remove(0).intValue();
			waitTime.remove(0);
			setDir(i);
		}
		for (int j = 0; j < waitTime.size(); j++) {
			int temp = waitTime.get(j).intValue();
			waitTime.set(j, temp - 1);
		}
	}
	
	public void newBlock() {
		this.x += (-1) * direction[0] * 10;
		this.y += (-1) * direction[1] * 10;
		for (int i = 0; i < waitTime.size(); i++)
			waitTime.set(i, new Integer(waitTime.get(i).intValue() + 1));
	}
	
	public void copyBlock(Block other) {
		this.x = other.getX();
		this.y = other.getY();
		this.direction[0] = other.getDirection()[0];
		this.direction[1] = other.getDirection()[1];
		List<Integer> movements = other.getMoves();
		for (int i = 0; i < movements.size(); i++)
			this.movement.add(movements.get(i));
		List<Integer> waits = other.getWaitTime();
		for (int i = 0; i < waits.size(); i++)
			this.waitTime.add(waits.get(i));
	}
}
