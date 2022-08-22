package com.company;

import java.awt.Polygon;
import java.awt.GradientPaint;
import java.awt.Color;
public class Wall
{
	int[] rows;
	int[] cols;
	int r;
	int g;
	int b;
	String type;
	int sides;
	//GradientPaint gradientPaint;
	//Polygon poly;

	public Wall(int[] rows, int[] cols, int r, int g, int b, String type, int sides)
	{
		this.rows = rows;
		this.cols = cols;
		this.r = r;
		this.g = g;
		this.b = b;
		this.type = type;
		this.sides = sides;
	}

	public int[] getRows()
	{
		return rows;
	}


	public int[] getCols()
	{
		return cols;
	}


	public int getR()
	{
		return r;
	}

	public int getG()
	{
		return g;
	}

	public int getB()
	{
		return b;
	}

	public String getType()
	{
		return type;
	}

	public int getSides()
	{
		return sides;
	}

	public Polygon getShape()
	{
		return new Polygon(cols, rows, sides);
	}

	public GradientPaint getPaint()
	{
		return new GradientPaint(rows[0], cols[0], new Color(r, g, b), rows[1], cols[1], new Color(r, g, b));
	}





}