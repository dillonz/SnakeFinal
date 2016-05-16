package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Snake extends JPanel{

	private List<Block> snake;
	private boolean[][] isTaken;
	private Food food;
	private List<Box> turns;
	final int UP = 0;
	final int RIGHT = 1;
	final int DOWN = 2;
	final int LEFT = 3;
	
	public Snake() {
		super();
		turns = new ArrayList<Box>();
		snake = new ArrayList<Block>();
		snake.add(new Block(50, 0, 1, 0));
		snake.add(new Block(40, 0, 1, 0));
		snake.add(new Block(30, 0, 1, 0));
		snake.add(new Block(20, 0, 1, 0));
		snake.add(new Block(10, 0, 1, 0));
		snake.add(new Block(0, 0, 1, 0));
		isTaken = new boolean[50][50];
		adjustOpen();
		food = new Food(isTaken);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		this.setBackground(Color.WHITE);
		
		g.setColor(Color.LIGHT_GRAY);
		for (int r = 0; r <= 500; r += 10)
			for (int c = 0; c <= 500; c += 10)
				g.drawRect(r, c, 10, 10);
	
		g.setColor(Color.BLUE);
		for (int i = 0; i < turns.size(); i++) {
			Box temp = turns.get(i);
			temp.increment();
			turns.set(i, temp);
			if (turns.get(i).timeLeft() > 0)
				g.fillRect(turns.get(i).x(), turns.get(i).y(), 10, 10);
			else
				turns.remove(i);
		}
		for (int i = 0; i < snake.size(); i++) {
			Block temp = snake.get(i);
			g.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
			if (temp.getX() <= 500 && temp.getY() <= 500 && temp.getX() >= 0 && temp.getY() >= 0) {
				temp.move();
				if (temp.getX() % 10 == 0 && temp.getY() % 10 == 0) {
					temp.changeDir();
					adjustOpen();
				}
			} else
				System.exit(0); //TODO Add game over thingymabob
			snake.set(i, temp);
		}
		if (snake.get(0).getX() == (food.getPos()[0] * 10) && snake.get(0).getY() == (food.getPos()[1] * 10)) {
			food = new Food(isTaken);
			int index = snake.size() - 1;
			Block temp = new Block(0, 0, 0, 0);
			temp.copyBlock(snake.get(index));
			temp.newBlock();
			snake.add(temp);
			Block temp2 = new Block(0, 0, 0, 0);
			temp2.copyBlock(snake.get(index + 1));
			temp2.newBlock();
			snake.add(temp2);
			
		}
		
		g.setColor(Color.RED);
		g.fillOval(food.getPos()[0] * 10, food.getPos()[1] * 10, 10, 10);
		
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}
	
	public void adjustOpen() {
		makeFalse();
		int x = 0, y = 0;
		for (int i = 0; i < snake.size(); i++) {
			if (snake.get(i).getX() / 10 == 50) {
				x = 49;
			} else {
				x = snake.get(i).getX() / 10;
			}
			if (snake.get(i).getY() / 10 == 50) {
				y = 49;
			} else {
				y = snake.get(i).getY() / 10;
			}

			isTaken[x][y] = true;
		}
	}
	
	private void makeFalse() {
		for (int r = 0; r < isTaken.length; r++)
			for (int c = 0; c < isTaken[0].length; c++)
				isTaken[r][c] = false;
	}
	
	public void changeDir(int dir) {
		if (dir != -1) {
			for (int i = 0; i < snake.size(); i++) {
				Block temp = snake.get(i);
				temp.addMove(dir, i);
				snake.set(i, temp);
			}
		}
		Block temp = snake.get(0);
		int x = temp.getX();
		int y = temp.getY();
		if (temp.getDirection()[0] != 0)
			x = (x + 9) / 10 * 10;
		if (temp.getDirection()[1] != 0)
			y = (y + 9) / 10 * 10;
		turns.add(new Box(x, y, snake.size()));
	}
	
	private static int indexOf(int[] array, int n) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == n)
				return i;
		return -1;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Snake snake = new Snake();
		frame.addKeyListener(new KeyListener() {

			final int[] dirs = {KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT};

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {
				snake.changeDir(indexOf(dirs, e.getKeyCode()));
			}

			public void keyReleased(KeyEvent e) {
				
			}
						
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(snake);
		frame.setSize(500, 536);
		frame.setVisible(true);

	}
}
