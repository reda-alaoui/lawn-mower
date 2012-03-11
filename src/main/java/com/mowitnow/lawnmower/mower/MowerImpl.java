package com.mowitnow.lawnmower.mower;

import com.mowitnow.lawnmower.lawn.Square;

/**
 * Mower implementation.
 * 
 * @author Reda
 * 
 */
public class MowerImpl implements Mower {

	private Orientation orientation;
	private Square square;

	public MowerImpl(Square square, Orientation orientation) {
		this.orientation = orientation;
		this.square = square;
	}

	public void turnRight() {
		switch (orientation) {
		case NORTH:
			orientation = Orientation.EAST;
			break;
		case EAST:
			orientation = Orientation.SOUTH;
			break;
		case WEST:
			orientation = Orientation.NORTH;
			break;
		case SOUTH:
			orientation = Orientation.WEST;
			break;
		default:
			break;
		}
		// System.out.println(orientation);
	}

	public void turnLeft() {
		switch (orientation) {
		case NORTH:
			orientation = Orientation.WEST;
			break;
		case EAST:
			orientation = Orientation.NORTH;
			break;
		case WEST:
			orientation = Orientation.SOUTH;
			break;
		case SOUTH:
			orientation = Orientation.EAST;
			break;
		default:
			break;
		}
		// System.out.println(orientation);
	}

	public void moveForward() {
		int newSquareAbscissa = square.getX();
		int newSquareOrdinate = square.getY();

		switch (orientation) {
		case NORTH:
			newSquareOrdinate++;
			break;
		case EAST:
			newSquareAbscissa++;
			break;
		case WEST:
			newSquareAbscissa--;
			break;
		case SOUTH:
			newSquareOrdinate--;
			break;
		default:
			return;
		}

		Square targetedSquare = this.square.getLawn().getSquare(
				newSquareAbscissa, newSquareOrdinate);

		if (targetedSquare != null && targetedSquare.isFree()) {
			this.square.setMower(null);
			targetedSquare.setMower(this);
			this.square = targetedSquare;
		}
		// System.out.println(square.getX() + " " + square.getY() + " "
		// + orientation);

	}

	/**
	 * @return the square
	 */
	public Square getSquare() {
		return square;
	}

	public void executeCommands(String orders) {

		int length = orders.length();

		for (int i = 0; i < length; i++) {
			char order = orders.charAt(i);
			// System.out.println(order);

			switch (order) {
			case 'D':
				turnRight();
				break;
			case 'G':
				turnLeft();
				break;
			case 'A':
				moveForward();
				break;
			default:
				break;
			}
		}

		System.out.println(square.getX() + " " + square.getY() + " "
				+ orientation.getStringCode());
	}
}
