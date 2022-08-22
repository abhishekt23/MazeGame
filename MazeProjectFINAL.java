package com.company;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Polygon;

public class MazeProjectFINAL extends JPanel implements KeyListener
{
	JFrame frame;
	HeroMaze hero;
	int movements = 0;
	int dir = 1;
	int size = 15;
	int r = 1;
	int c = 0;
	int numRows = 20;
	int numCols = 84;
	char[][] maze = new char[numRows][numCols];
	ArrayList<Wall> walls;
	int factor = 50;
	boolean draw3D = false;

	//Winning Game variables
	Font fontend = new Font("MazeFinish2D", Font.BOLD, 30);
	boolean end = false;

	//Checkpoints variables
	Font fontcheck = new Font("Checkpoints", Font.BOLD, 20);
	boolean check1 = false;
	boolean check2 = false;
	boolean check3 = false;
	boolean check4 = false;
	boolean check5 = false;

	//Traps variables
	Font fonttrap = new Font("Traps", Font.ITALIC, 25);
	int traphealth = 7;
	boolean initial = false;
	boolean trap1 = false;
	boolean trap2 = false;
	boolean trap3 = false;
	boolean trap4 = false;
	boolean trap5 = false;
	boolean trap6 = false;
	boolean trap7 = false;

	//checkpoints are at: (10,21), (10,43), (17,55), (6,64), (5,73)
	//traps are at: (3,7), (13,20), (18,15), (2,37), (12,55), (1,53), (3,78)








	public MazeProjectFINAL()
	{
		frame = new JFrame("A-Mazing Program");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setSize(1500, 600);
		hero = new HeroMaze(new Location(r,c), dir, size, Color.BLUE);




		frame.addKeyListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		File fileName = new File("/Users/abhishekthakare/Desktop/forAbhi/Data Structures Projects/src/com/company/MazeProjectText.txt");

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			int r = 0;
			while( (text = input.readLine()) != null )
			{
				for(int c = 0; c < text.length(); c++)
				{
					maze[r][c] = text.charAt(c);
				}
				r++;
			}


		}catch(IOException e)
		{
			System.out.print("File not found");
		}

		if(draw3D)
			createWalls();


	} //app
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); ///giant eraser (starts over/wipes out screen)
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,1500,1200);


		if(!draw3D)
		{
			g2.setColor(Color.GRAY);
			for(int c = 0; c < maze[0].length; c++)
			{
				for(int r = 0; r < maze.length; r++)
				{
					if(maze[r][c] == ' ')
						g2.fillRect(c*size + size, r*size + size, size, size);
					else
						g2.drawRect(c*size + size, r*size + size, size, size);

				}
			}
			g2.setColor(hero.getColor());
			g2.fill(hero.getRect());

			//Track movement
			g2.setFont(fontend);
			g2.setColor(new Color(255, 255, 0));
			g2.drawString("Movements: ", 1290, 100);
			g2.drawString("" + hero.getMovements(), 1350, 150);




			int rloc = hero.getLoc().getR();
			int cloc = hero.getLoc().getC();

			//Maze Checkpoints(2D Version)
			if(rloc == 10 && cloc == 21)
			{
				check1 = true;
			}
			if(check1 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 1 Reached", 50, 350);
			}

			if(rloc == 10 && cloc == 43)
			{
				check2 = true;
			}
			if(check2 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 2 Reached", 50, 400);
			}

			if(rloc == 17 && cloc == 55)
			{
				check3 = true;
			}
			if(check3 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 3 Reached", 50, 450);
			}

			if(rloc == 6 && cloc == 64)
			{
				check4 = true;
			}
			if(check4 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 4 Reached", 350, 350);
			}

			if(rloc == 5 && cloc == 73)
			{
				check5 = true;
			}
			if(check5 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 5 Reached", 350, 400);
			}


			//Traps(2D Version)
			if(rloc == 1 && cloc == 0)
			{
				initial = true;

			}
			if(rloc == 3 && cloc == 7)
			{
				if(traphealth == 0)
					traphealth = 0;
				else if(trap1 == true)
					trap1 = false;
				else
				{
					traphealth--;
					trap1 = true;
				}
			}
			if(rloc == 13 && cloc == 20)
			{
				if(traphealth == 0)
					traphealth = 0;
				else if(trap2 == true)
					trap2 = false;
				else
				{
					traphealth--;
					trap2 = true;
				}
			}
			if(rloc == 18 && cloc == 15)
			{
				if(traphealth == 0)
					traphealth = 0;
				else if(trap3 == true)
					trap3 = false;
				else
				{
					traphealth--;
					trap3 = true;
				}
			}
			if(rloc == 2 && cloc == 37)
			{
				if(traphealth == 0)
					traphealth = 0;
				else if(trap4 == true)
					trap4 = false;
				else
				{
					traphealth--;
					trap4 = true;
				}
			}
			if(rloc == 12 && cloc == 55)
			{
				if(traphealth == 0)
					traphealth = 0;
				else if(trap5 == true)
					trap5 = false;
				else
				{
					traphealth--;
					trap5 = true;
				}
			}
			if(rloc == 1 && cloc == 53)
			{
				if(traphealth == 0)
					traphealth = 0;
				else if(trap6 == true)
					trap6 = false;
				else
				{
					traphealth--;
					trap6 = true;
				}
			}
			if(rloc == 3 && cloc == 78)
			{
				if(traphealth == 0)
					traphealth = 0;
				else if(trap7 == true)
					trap7 = false;
				else
				{
					traphealth--;
					trap7 = true;
				}
			}


			if(initial == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 900, 350);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Health: " + traphealth + " hearts", 900, 380);
			}
			if(trap1 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 900, 350);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 1 set off", 900, 410);
			}
			if(trap2 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 900, 350);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 2 set off", 900, 440);
			}
			if(trap3 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 900, 350);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 3 set off", 900, 470);
			}
			if(trap4 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 900, 350);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 4 set off", 900, 500);
			}
			if(trap5 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 900, 350);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 5 set off", 900, 530);
			}
			if(trap6 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 900, 350);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 6 set off", 900, 560);
			}
			if(trap7 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 900, 350);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 7 set off", 900, 590);
			}







			//Maze End(2D Version)
			if(rloc == 10 && cloc == 82)
			{
				end = true;
			}
			if(end == true)
			{
				if(traphealth != 0)
				{
					g2.setFont(fontend);
					g2.setColor(new Color(0, 128, 0));
					g2.drawString("Congratulations!", 350, 450);
					g2.drawString("Feel free to explore,", 350, 500);
					g2.drawString("but watch out for traps!", 350, 550);

				}
				else
				{
					g2.setFont(fontend);
					g2.setColor(new Color(0, 128, 0));
					g2.drawString("End!", 350, 450);
				}
			}



		}
		else
		{
			for(Wall test:walls)
			{
				if(test.getSides() == 4)
				{
					g2.setColor(Color.WHITE);
					g2.setPaint(test.getPaint());
					g2.fillPolygon(test.getShape());
					g2.setColor(Color.BLACK);
					g2.drawPolygon(test.getShape());
				}
				if(test.getSides() == 3)
				{
					g2.setColor(Color.WHITE);
					g2.setPaint(test.getPaint());
					g2.fillPolygon(test.getShape());
					g2.setColor(Color.BLACK);
					g2.drawPolygon(test.getShape());
				}
			}

			//Track movement
			g2.setFont(fontend);
			g2.setColor(new Color(255, 255, 0));;
			g2.drawString("Movements: " + hero.getMovements(), 1050, 750);

			//Maze Checkpoints(3D Version)
			if(check1 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 1 Reached", 50, 750);
			}
			if(check2 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 2 Reached", 50, 800);
			}
			if(check3 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 3 Reached", 50, 850);
			}
			if(check4 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 4 Reached", 50, 900);
			}
			if(check5 == true)
			{
				g2.setFont(fontcheck);
				g2.setColor(new Color(0, 0, 255));
				g2.drawString("Checkpoint 5 Reached", 300, 750);
			}


			//Traps(3D Version)

			if(initial == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 1050, 100);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Health: " + traphealth + " hearts", 1050, 150);
			}
			if(trap1 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 1050, 100);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 1 set off", 1050, 200);
			}
			if(trap2 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 1050, 100);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 2 set off", 1050, 250);
			}
			if(trap3 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 1050, 100);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 3 set off", 1050, 300);
			}
			if(trap4 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 1050, 100);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 4 set off", 1050, 350);
			}
			if(trap5 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 1050, 100);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 5 set off", 1050, 400);
			}
			if(trap6 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 1050, 100);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 6 set off", 1050, 450);
			}
			if(trap7 == true)
			{
				if(traphealth == 0)
				{
					g2.setFont(fonttrap);
					g2.setColor(new Color(255, 0, 0));
					g2.drawString("Game Over! You are now a ghost.", 1050, 100);
				}
				g2.setFont(fonttrap);
				g2.setColor(new Color(255, 0, 0));
				g2.drawString("Trap 7 set off", 1050, 500);
			}



			//Maze End(3D Version)
			if(end == true)
			{
				if(traphealth != 0)
				{
					end = false;
					g2.setFont(fontend);
					g2.setColor(new Color(0, 128, 0));
					g2.drawString("Congratulations!", 500, 400);
					g2.drawString("Feel free to explore,", 500, 500);
					g2.drawString("but watch out for traps!", 500, 600);
				}
				else
				{
					g2.setFont(fontend);
					g2.setColor(new Color(0, 128, 0));
					g2.drawString("End!", 500, 400);
				}
			}

		}

	} //end paintcomponent

	public void keyPressed(KeyEvent e)
	{
		hero.move(e.getKeyCode(), maze);

		if(e.getKeyCode() == 32)
		{
			draw3D = !draw3D;
		}
		if(draw3D)
			createWalls();
		repaint();

	}
	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}

	public void createWalls()
	{
		walls = new ArrayList<Wall>();


		//ceiling
		for(int n = 0; n < 5; n++)
		{
			walls.add(getCeiling(n));
		}

		//floor
		for(int n = 0; n < 5; n++)
		{
			walls.add(getFloor(n));
		}

		int rr = hero.getLoc().getR();
		int cc = hero.getLoc().getC();
		int dir = hero.getDir();

		//Checkpoints
		if(rr == 10 && cc == 21)
			check1 = true;
		if(rr == 10 && cc == 43)
			check2 = true;
		if(rr == 17 && cc == 55)
			check3 = true;
		if(rr == 6 && cc == 64)
			check4 = true;
		if(rr == 5 && cc == 73)
			check5 = true;

		//Traps
		if(rr == 1 && cc == 0)
			initial = true;
		if(rr == 3 && cc == 7)
		{
			if(traphealth == 0)
				traphealth = 0;
			else if(trap1 == true)
				trap1 = false;
			else
			{
				traphealth--;
				trap1 = true;
			}
		}
		if(rr == 13 && cc == 20)
		{
			if(traphealth == 0)
				traphealth = 0;
			else if(trap2 == true)
				trap2 = false;
			else
			{
				traphealth--;
				trap2 = true;
			}
		}
		if(rr == 18 && cc == 15)
		{
			if(traphealth == 0)
				traphealth = 0;
			else if(trap3 == true)
				trap3 = false;
			else
			{
				traphealth--;
				trap3 = true;
			}
		}
		if(rr == 2 && cc == 37)
		{
			if(traphealth == 0)
				traphealth = 0;
			else if(trap4 == true)
				trap4 = false;
			else
			{
				traphealth--;
				trap4 = true;
			}
		}
		if(rr == 12 && cc == 55)
		{
			if(traphealth == 0)
				traphealth = 0;
			else if(trap5 == true)
				trap5 = false;
			else
			{
				traphealth--;
				trap5 = true;
			}
		}
		if(rr == 1 && cc == 53)
		{
			if(traphealth == 0)
				traphealth = 0;
			else if(trap6 == true)
				trap6 = false;
			else
			{
				traphealth--;
				trap6 = true;
			}
		}
		if(rr == 3 && cc == 78)
		{
			if(traphealth == 0)
				traphealth = 0;
			else if(trap7 == true)
				trap7 = false;
			else
			{
				traphealth--;
				trap7 = true;
			}
		}





		switch(dir)
		{
			case 0: //facing up
					for(int n = 0; n < 5; n++)
					{
						try
						{
							//looking for left wall
							if(maze[rr-n][cc-1] == '#')
								walls.add(getLeftWall(n));
							else
							{
								walls.add(getLeftPath(n+1));
								walls.add(getLeftCeiling(n));
								walls.add(getLeftFloor(n));



							}
							//looking for right wall
							if(maze[rr-n][cc+1] == '#')
								walls.add(getRightWall(n));
							else
							{
								walls.add(getRightPath(n+1));
								walls.add(getRightCeiling(n));
								walls.add(getRightFloor(n));


							}

						}catch(ArrayIndexOutOfBoundsException e)
						{

						}
					}
					//front wall facing up
					for(int n = 5; n > 0; n--)
					{
						try
						{
							if(maze[rr-n][cc] == '#')
								walls.add(getFrontWall(n));

						}catch(ArrayIndexOutOfBoundsException e)
						{

						}
					}

			break;



			case 1: //facing right
					for(int n = 0; n < 5; n++)
					{
						try
						{
							//looking for left wall
							if(maze[rr-1][cc+n] == '#')
								walls.add(getLeftWall(n));
							else
							{
								walls.add(getLeftPath(n+1));
								walls.add(getLeftCeiling(n));
								walls.add(getLeftFloor(n));



							}
							//looking for right wall
							if(maze[rr+1][cc+n] == '#')
								walls.add(getRightWall(n));
							else
							{
								walls.add(getRightPath(n+1));
								walls.add(getRightCeiling(n));
								walls.add(getRightFloor(n));



							}
						}catch(ArrayIndexOutOfBoundsException e)
						{

						}


					}
					//front wall facing right
					for(int n = 5; n > 0; n--)
					{
						try
						{
							if(maze[rr][cc+n] == '#')
								walls.add(getFrontWall(n));
							if(maze[rr][cc+n] == 'e')
							{
								walls.add(getFrontWall(n));
								if(n == 1)
								{
									end = true;
								}
							}


						}catch(ArrayIndexOutOfBoundsException e)
						{

						}
					}

			break;


			case 2: //facing down
					for(int n = 0; n < 5; n++)
					{
						try
						{
							//looking for left wall
							if(maze[rr+n][cc+1] == '#')
								walls.add(getLeftWall(n));
							else
							{
								walls.add(getLeftPath(n+1));
								walls.add(getLeftCeiling(n));
								walls.add(getLeftFloor(n));



							}
							//looking for right wall
							if(maze[rr+n][cc-1] == '#')
								walls.add(getRightWall(n));
							else
							{
								walls.add(getRightPath(n+1));
								walls.add(getRightCeiling(n));
								walls.add(getRightFloor(n));


							}
						}catch(ArrayIndexOutOfBoundsException e)
						{

						}
					}
					//front wall facing down
					for(int n = 5; n > 0; n--)
					{
						try
						{
							if(maze[rr+n][cc] == '#')
								walls.add(getFrontWall(n));


						}catch(ArrayIndexOutOfBoundsException e)
						{

						}
					}

			break;


			case 3: //facing left
					for(int n = 0; n < 5; n++)
					{
						try
						{
							//looking for left wall
							if(maze[rr+1][cc-n] == '#')
								walls.add(getLeftWall(n));
							else
							{
								walls.add(getLeftPath(n+1));
								walls.add(getLeftCeiling(n));
								walls.add(getLeftFloor(n));


							}
							//looking for right wall
							if(maze[rr-1][cc-n] == '#')
								walls.add(getRightWall(n));
							else
							{
								walls.add(getRightPath(n+1));
								walls.add(getRightCeiling(n));
								walls.add(getRightFloor(n));

							}
						}catch(ArrayIndexOutOfBoundsException e)
						{

						}
					}
					//front wall facing left
					for(int n = 5; n > 0; n--)
					{
						try
						{
							if(maze[rr][cc-n] == '#')
								walls.add(getFrontWall(n));


						}catch(ArrayIndexOutOfBoundsException e)
						{

						}
					}

			break;










		}

	}

	public Wall getLeftPath(int n) //rectangle
	{
								//A             //B           //C          //D
		int[] rLocs = new int[] {100+factor*n, 100+factor*n, 700-factor*n, 700-factor*n};
		int[] cLocs = new int[] {250+factor*n, 300+factor*n, 300+factor*n, 250+factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "LeftPath", 4);
	}

	public Wall getLeftWall(int n) //trapezoid
	{
								//A             //B           //C           //D
		int[] rLocs = new int[] {100+factor*n, 150+factor*n, 650-factor*n, 700-factor*n};
		int[] cLocs = new int[] {300+factor*n, 350+factor*n, 350+factor*n, 300+factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "LeftWall", 4);
	}










	public Wall getRightPath(int n) //rectangle
	{
								//A             //B           //C           //D
		int[] rLocs = new int[] {100+factor*n, 100+factor*n, 700-factor*n, 700-factor*n};
		int[] cLocs = new int[] {950-factor*n, 1000-factor*n, 1000-factor*n, 950-factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "RightPath", 4);
	}

	public Wall getRightWall(int n) //trapezoid
	{
								//A             //B           //C           //D
		int[] rLocs = new int[] {150+factor*n, 100+factor*n, 700-factor*n, 650-factor*n};
		int[] cLocs = new int[] {900-factor*n, 950-factor*n, 950-factor*n, 900-factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "RightWall", 4);
	}










	public Wall getCeiling(int n) //rectangle
	{
								//A             //B           //C           //D
		int[] rLocs = new int[] {100+factor*n, 100+factor*n, 150+factor*n, 150+factor*n};
		int[] cLocs = new int[] {300+factor*n, 950-factor*n, 900-factor*n, 350+factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "Ceiling", 4);
	}

	public Wall getFloor(int n) //rectangle
	{
								//A             //B           //C           //D
		int[] rLocs = new int[] {650-factor*n, 650-factor*n, 700-factor*n, 700-factor*n};
		int[] cLocs = new int[] {300+factor*n, 900-factor*n, 950-factor*n, 300+factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "Floor", 4);
	}

	public Wall getFrontWall(int n) //rectangle
	{
								//A             //B           //C           //D
		int[] rLocs = new int[] {100+factor*n, 100+factor*n, 700-factor*n, 700-factor*n};
		int[] cLocs = new int[] {300+factor*n, 950-factor*n, 950-factor*n, 300+factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "Front", 4);
	}








	public Wall getLeftCeiling(int n) //triangle
	{
								//A             //B           //C
		int[] rLocs = new int[] {100+factor*n, 150+factor*n, 150+factor*n};
		int[] cLocs = new int[] {300+factor*n, 300+factor*n, 350+factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "LeftCeiling", 3);
	}

	public Wall getRightCeiling(int n) //triangle
	{
								//A             //B           //C
		int[] rLocs = new int[] {100+factor*n, 150+factor*n, 150+factor*n};
		int[] cLocs = new int[] {950-factor*n, 950-factor*n, 900-factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "RightCeiling", 3);
	}

	public Wall getLeftFloor(int n) //triangle
	{
									//A             //B           //C
		int[] rLocs = new int[] {650-factor*n, 650-factor*n, 700-factor*n};
		int[] cLocs = new int[] {300+factor*n, 350+factor*n, 300+factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "LeftFloor", 3);
	}

	public Wall getRightFloor(int n) //triangle
	{
								//A             //B           //C
		int[] rLocs = new int[] {650-factor*n, 650-factor*n, 700-factor*n};
		int[] cLocs = new int[] {950-factor*n, 900-factor*n, 950-factor*n};
		return new Wall (rLocs, cLocs, 255-factor*n, 255-factor*n, 255-factor*n, "RightFloor", 3);
	}





	public static void main(String[] args)
	{
		MazeProjectFINAL app = new MazeProjectFINAL();
	}
} //class