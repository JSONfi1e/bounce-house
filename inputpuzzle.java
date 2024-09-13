import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.Random;
import java.util.*;

public class inputpuzzle {
	public static void main(String... args) {
		Scanner in = new Scanner(System.in);
		//System.out.println("enter size");
		//int size = in.nextInt();
		String a = JOptionPane.showInputDialog("enter size");
		int size = Integer.parseInt(a);
		JFrame j = new JFrame(); // JFrame is the window; window is a depricated class
		MyPanelb m = new MyPanelb(size);
		j.setSize(m.getSize());
		j.add(m); // adds the panel to the frame so that the picture will be drawn
					// use setContentPane() sometimes works better then just add b/c of greater
					// efficiency.
		j.addMouseListener(m);
		j.setVisible(true); // allows the frame to be shown.

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the dialog box exit when you click the "x" button.
		System.out.print(m);
	}

}

class MyPanelb extends JPanel implements MouseListener {
	Rectangle[][] r;
	boolean scramble[];
	char matrix[][];
	Random rnd;
	int blankR;
	int blankC;
	char blankletter;
	int playLevel;
	int width, height;
	int xpos, ypos;
	int size;

	MyPanelb(int s) {

		setSize(2000, 1500);
		size = s;
		r = new Rectangle[size][size];
		xpos = 25;
		ypos = 25;
		width = 800 / size;
		height = 800 / size;
		blankletter = (char) (64 + size*size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				r[i][j] = new Rectangle(xpos + (width * j), ypos + (height * i), width, height);
			}
		}
		scramble = new boolean[size*size + 1];
		for (int k = 1; k <= scramble.length - 1; k++)
			scramble[k] = false;
		rnd = new Random();
		matrix = new char[size + 2][size + 2];
		for (int r = 0; r < size + 2; r++)
			for (int c = 0; c < size + 2; c++)
				matrix[r][c] = '#';

		for (int r = 1; r <= size; r++)
			for (int c = 1; c <= size; c++) {
				matrix[r][c] = getLetter();
				if (matrix[r][c] == blankletter) {
					blankR = r;
					blankC = c;
				}
			}
		setVisible(true); // it's like calling the repaint method.

	}

	public char getLetter() {
		char letter = ' ';
		boolean Done = false;
		while (!Done) {
			int rndNum = rnd.nextInt(size*size) + 1;
			if (scramble[rndNum] == false) {
				letter = ((char) (rndNum + 64));
				scramble[rndNum] = true;
				Done = true;
			}
		}
		return letter;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(0, 0, 2000, 1500);
		g.setColor(Color.BLACK);
		drawGrid(g);
		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				drawLetter(g, matrix[i][j], xpos + (width * (j - 1)), (height * (i - 1)));
			}
		}
	}

	public void drawGrid(Graphics g) {
		g.drawRect(xpos, ypos, (size * height), (size * height));
		for (int i = 1; i < size; i++) {
			g.drawLine(xpos + (width * i), ypos, xpos + (width * i), ypos + (height * size));
		}
		for (int j = 1; j < size; j++) {
			g.drawLine(xpos, ypos + (height * j), xpos + (size * width), ypos + (height * j));
		}
	}

	public void drawLetter(Graphics g, char letter, int x, int y) {
		int offSetX = x;
		int offSetY = y + height;
		g.setFont(new Font("Arial", Font.BOLD, (height)));
		if (letter == blankletter) {
			g.setColor(Color.white);
			g.fillRect(x, y + ypos, width, height);
		} else {
			g.setColor(Color.black);
			g.drawString(letter + "", offSetX, offSetY);
		}
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (r[i][j].contains(x, y) && okSquare(i + 1, j + 1)) {
					swap(i + 1, j + 1);
				}
			}
		}
	}

	public boolean okSquare(int r, int c) {
		boolean check = false;
		if (matrix[r - 1][c] == blankletter)
			check = true;
		else if (matrix[r + 1][c] == blankletter)
			check = true;
		else if (matrix[r][c - 1] == blankletter)
			check = true;
		else if (matrix[r][c + 1] == blankletter)
			check = true;
		return check;
	}

	public void swap(int r, int c) {
		matrix[blankR][blankC] = matrix[r][c];
		matrix[r][c] = blankletter;
		blankR = r;
		blankC = c;
		repaint();
	}

	public void update(Graphics g) {
		paint(g);
	}

	public String toString() {
		String output = "";
		output += "Scramble:\n";
		for (int i = 0; i < scramble.length; i++) {
			output += (scramble[i] + " ");
		}
		output += "\nmatrix\n";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				output += (matrix[i][j] + " ");
			}
			output += "\n";
		}
		output += "size";
		output += (size + "\n");
		output += "width";
		output += (width + "\n");
		output += "height";
		output += (height + "\n");
		output += "blankr";
		output += (blankR + "\n");
		output += "blankc";
		output += (blankC + "\n");
		output += "r\n";
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r.length; j++) {
				output += (r[i][j] + " ");
			}
			output += "\n";
		}
		return output;
	}
}