package com.mowitnow.lawnmower.lawn;

import com.mowitnow.lawnmower.controller.Controller;

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
	private Controller controller;

	public Lawn(Controller controller, int extremeOrdinate, int extremeAbscissa) {
		this.controller = controller;
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

	public void renderRotation(Square square) {
		System.out.println(this);
		if (controller.isGuiActivated()) {
			controller.renderRotation(square);
		}
	}

	public void renderMove(Square oldSquare, Square newSquare) {
		System.out.println(this);
		if (controller.isGuiActivated()) {
			controller.renderMove(oldSquare, newSquare);
		}
	}

	public void renderAll() {
		System.out.println(this);
		if (controller.isGuiActivated()) {
			controller.renderAll();
		}
	}

	/**
	 * @return the extremeOrdinate
	 */
	public int getExtremeOrdinate() {
		return extremeOrdinate;
	}

	/**
	 * @return the extremeAbscissa
	 */
	public int getExtremeAbscissa() {
		return extremeAbscissa;
	}

	/**
	 * @return the controller
	 */
	public Controller getController() {
		return controller;
	}

}
