
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.Random;

public class FiveByFive
{
	public static void main(String...args)
	{
		JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
		MyPanelb m = new MyPanelb();
		j.setSize(m.getSize());
		j.add(m); //adds the panel to the frame so that the picture will be drawn
			      //use setContentPane() sometimes works better then just add b/c of greater efficiency.
        j.addMouseListener(m);
		j.setVisible(true); //allows the frame to be shown.

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the dialog box exit when you click the "x" button.
	}

}

class MyPanelb extends JPanel implements MouseListener
{
	Rectangle r11,r12,r13,r14,r15,r21,r22,r23,r24,r25,r31,r32,r33,r34,r35,r41,r42,r43,r44,r45,r51,r52,r53,r54,r55;
	boolean scramble[];
	String matrix[][];
	Random rnd;
	int blankR;
	int blankC;
	int playLevel;
	double scale = 0.5;
	
	MyPanelb()
	{

		setSize(2000, 1500);
	
		r11 = new Rectangle(100/2,100/2,200/2,200/2);
		r12 = new Rectangle(300/2,100/2,200/2,200/2);
		r13 = new Rectangle(500/2,100/2,200/2,200/2);
		r14 = new Rectangle(700/2,100/2,200/2,200/2);
		r15 = new Rectangle(900/2,100/2,200/2,200/2);
		r21 = new Rectangle(100/2,300/2,200/2,200/2);
		r22 = new Rectangle(300/2,300/2,200/2,200/2);
		r23 = new Rectangle(500/2,300/2,200/2,200/2);
		r24 = new Rectangle(700/2,300/2,200/2,200/2);
		r25 = new Rectangle(900/2,300/2,200/2,200/2);
		r31 = new Rectangle(100/2,500/2,200/2,200/2);
		r32 = new Rectangle(300/2,500/2,200/2,200/2);
		r33 = new Rectangle(500/2,500/2,200/2,200/2);
		r34 = new Rectangle(700/2,500/2,200/2,200/2);
		r35 = new Rectangle(900/2,500/2,200/2,200/2);
		r41 = new Rectangle(100/2,700/2,200/2,200/2);
		r42 = new Rectangle(300/2,700/2,200/2,200/2);
		r43 = new Rectangle(500/2,700/2,200/2,200/2);
		r44 = new Rectangle(700/2,700/2,200/2,200/2);
		r45 = new Rectangle(900/2,700/2,200/2,200/2);
		r51 = new Rectangle(100/2,900/2,200/2,200/2);
		r52 = new Rectangle(300/2,900/2,200/2,200/2);
		r53 = new Rectangle(500/2,900/2,200/2,200/2);
		r54 = new Rectangle(700/2,900/2,200/2,200/2);
		r55 = new Rectangle(900/2,900/2,200/2,200/2);
		
		matrix = new String[7][7];
		scramble = new boolean[26];
		for (int k = 1; k <=25; k++) // changed from 9
			scramble[k] = false;
		rnd = new Random();
		
		for (int r = 0; r <= 6; r++)// from 4
			for (int c = 0; c <= 6; c++)
				matrix[r][c] = "#";	
		
		for (int r = 1; r <= 5; r++)//from 3
			for (int c = 1; c <= 5; c++)
			{
				matrix[r][c] = getLetter();
				if (matrix[r][c].equals("Y"))
				{
					blankR = r;
					blankC = c;
				}
			}
		setVisible(true); //it's like calling the repaint method.
	
	
	}
	
	public String getLetter()
	{
		String letter = "";
		boolean Done = false;
		while(!Done)
		{
			int rndNum = rnd.nextInt(25) + 1; //from 9
			if (scramble[rndNum] == false)
			{
				letter = String.valueOf((char) (rndNum+64));
				scramble[rndNum] = true;
				Done = true;
			}
		}
		return letter;		
	}
	
	public void paintComponent(Graphics g)
	{
	    drawGrid(g);
		drawLetter(g,matrix[1][1],100/2,100/2);
		drawLetter(g,matrix[1][2],300/2,100/2);
		drawLetter(g,matrix[1][3],500/2,100/2);
		drawLetter(g,matrix[1][4],700/2,100/2);
		drawLetter(g,matrix[1][5],900/2,100/2);
		drawLetter(g,matrix[2][1],100/2,300/2);		
		drawLetter(g,matrix[2][2],300/2,300/2);
		drawLetter(g,matrix[2][3],500/2,300/2);
		drawLetter(g,matrix[2][4],700/2,300/2);
		drawLetter(g,matrix[2][5],900/2,300/2);
		drawLetter(g,matrix[3][1],100/2,500/2);
		drawLetter(g,matrix[3][2],300/2,500/2);
		drawLetter(g,matrix[3][3],500/2,500/2);
		drawLetter(g,matrix[3][4],700/2,500/2);
		drawLetter(g,matrix[3][5],900/2,500/2);
		drawLetter(g,matrix[4][1],100/2,700/2);
		drawLetter(g,matrix[4][2],300/2,700/2);
		drawLetter(g,matrix[4][3],500/2,700/2);
		drawLetter(g,matrix[4][4],700/2,700/2);
		drawLetter(g,matrix[4][5],900/2,700/2);
		drawLetter(g,matrix[5][1],100/2,900/2);
		drawLetter(g,matrix[5][2],300/2,900/2);
		drawLetter(g,matrix[5][3],500/2,900/2);
		drawLetter(g,matrix[5][4],700/2,900/2);
		drawLetter(g,matrix[5][5],900/2,900/2);
		
	}
	
	public void drawGrid(Graphics g)
	{
		g.drawRect(100/2,100/2,1000/2,1000/2);
		g.drawLine(300/2,100/2,300/2,1100/2);
		g.drawLine(500/2,100/2,500/2,1100/2);
		g.drawLine(700/2,100/2,700/2,1100/2);
		g.drawLine(900/2,100/2,900/2,1100/2);
		g.drawLine(100/2,300/2,1100/2,300/2);
		g.drawLine(100/2,500/2,1100/2,500/2);
		g.drawLine(100/2,700/2,1100/2,700/2);
		g.drawLine(100/2,900/2,1100/2,900/2);
	}
	
	
	public void drawLetter(Graphics g, String letter, int x, int y)
	{
		int offSetX = x + 30/2;
		int offSetY = y + 175/2;
		g.setFont(new Font("Arial",Font.BOLD,100));
		if(letter.equals("W"))
			g.setFont(new Font("Arial",Font.BOLD,90));
		if (letter.equals("Y"))
		{
			g.setColor(Color.white);
			g.fillRect(x+1,y+1,198/2,198/2);
		}
		else
		{
			g.setColor(Color.black);
			g.drawString(letter,offSetX,offSetY);			
		}
	}
	

	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	
	public void mouseClicked(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		if(r11.inside(x,y) && okSquare(1,1))
			swap(1,1);
		else if(r12.inside(x,y) && okSquare(1,2))
			swap(1,2);
		else if(r13.inside(x,y) && okSquare(1,3))
			swap(1,3);
		else if(r14.inside(x,y) && okSquare(1,4))
			swap(1,4);
		else if(r15.inside(x,y) && okSquare(1,5))
			swap(1,5);
		else if(r21.inside(x,y) && okSquare(2,1))
			swap(2,1);
		else if(r22.inside(x,y) && okSquare(2,2))
			swap(2,2);
		else if(r23.inside(x,y) && okSquare(2,3))
			swap(2,3);
		else if(r24.inside(x,y) && okSquare(2,4))
			swap(2,4);
		else if(r25.inside(x,y) && okSquare(2,5))
			swap(2,5);
		else if(r31.inside(x,y) && okSquare(3,1))
			swap(3,1);
		else if(r32.inside(x,y) && okSquare(3,2))
			swap(3,2);
		else if(r33.inside(x,y) && okSquare(3,3))
			swap(3,3);
		else if(r34.inside(x,y) && okSquare(3,4))
			swap(3,4);
		else if(r35.inside(x,y) && okSquare(3,5))
			swap(3,5);
		else if(r41.inside(x,y) && okSquare(4,1))
			swap(4,1);
		else if(r42.inside(x,y) && okSquare(4,2))
			swap(4,2);
		else if(r43.inside(x,y) && okSquare(4,3))
			swap(4,3);
		else if(r44.inside(x,y) && okSquare(4,4))
			swap(4,4);
		else if(r45.inside(x,y) && okSquare(4,5))
			swap(4,5);
		else if(r51.inside(x,y) && okSquare(5,1))
			swap(5,1);
		else if(r52.inside(x,y) && okSquare(5,2))
			swap(5,2);
		else if(r53.inside(x,y) && okSquare(5,3))
			swap(5,3);
		else if(r54.inside(x,y) && okSquare(5,4))
			swap(5,4);
		else if(r55.inside(x,y) && okSquare(5,5))
			swap(5,5);
		//return true;
	}
	
	
	public boolean okSquare(int r, int c)
	{
		boolean temp = false;
		if (matrix[r-1][c].equals("Y"))
			temp = true;
		else if (matrix[r+1][c].equals("Y"))
			temp = true;
		else if (matrix[r][c-1].equals("Y"))
			temp = true;
		else if (matrix[r][c+1].equals("Y"))
			temp = true;
		return temp;	
	}
	
	
	public void swap(int r, int c)
	{
		matrix[blankR][blankC] = matrix[r][c];
		matrix[r][c] = "Y";
		blankR = r;
		blankC = c;
		repaint();
	}
	
			
	public void update(Graphics g)
	{
		paint(g);
	}
}