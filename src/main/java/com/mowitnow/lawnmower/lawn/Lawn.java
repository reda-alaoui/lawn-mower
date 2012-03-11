package com.mowitnow.lawnmower.lawn;

/**
 * Describes the lawn to mow.
 * 
 * @author Reda
 * 
 */
public class Lawn {
	private Square[][] grid;
	private int extremeOrdinate;
	private int extremeAbscissa;

	public Lawn(int extremeOrdinate, int extremeAbscissa) {
		this.extremeOrdinate = extremeOrdinate;
		this.extremeAbscissa = extremeAbscissa;
		this.grid = new Square[extremeAbscissa + 1][extremeOrdinate + 1];

		for (int i = 0; i < extremeAbscissa + 1; i++) {
			for (int j = 0; j < extremeOrdinate + 1; j++) {
				grid[i][j] = new Square(this, i, j);
			}
		}
	}

	public Square getSquare(int x, int y) {
		if (x <= extremeAbscissa && y <= extremeOrdinate) {
			return grid[x][y];
		}
		return null;
	}

	@Override
	public String toString() {
		String gridRepresentation = "";

		for (int j = extremeOrdinate; j >= 0; j--) {
			for (int i = 0; i < extremeAbscissa + 1; i++) {
				gridRepresentation += "["
						+ (grid[i][j].isFree() ? " " : grid[i][j].getMower()
								.getOrientation().getStringCode()) + "]";
			}
			gridRepresentation += "\n";
		}
		return gridRepresentation;
	}

	public void render() {
		System.out.println(this);
	}
}
