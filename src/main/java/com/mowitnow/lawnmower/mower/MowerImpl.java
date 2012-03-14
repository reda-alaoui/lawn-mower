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
	private String id;
	/**
	 * Allows to set mowers speed.<br>
	 * Bigger value make slower mowers.
	 */
	private static final long timeout = 500;

	/**
	 * 
	 * @param id
	 * @param square
	 * @param orientation
	 */
	public MowerImpl(String id, Square square, Orientation orientation) {
		this.id = id;
		this.orientation = orientation;
		setSquare(square);
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
		square.getLawn().renderRotation(square);
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
		square.getLawn().renderRotation(square);
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
		Square oldSquare = this.square;
		Square targetedSquare = this.square.getLawn().getSquare(
				newSquareAbscissa, newSquareOrdinate);
		setSquare(targetedSquare);
		square.getLawn().renderMove(oldSquare, targetedSquare);
	}

	/**
	 * 
	 * @param square
	 */
	private void setSquare(Square square) {
		if (square != null && square.isFree()) {
			if (this.square != null) {
				this.square.setMower(null);
			}
			square.setMower(this);
			this.square = square;
		}
	}

	public void executeCommands(String orders) {
		System.out.println("-------------------------------------- Launching "
				+ id + " -----------------------------------");
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

			if (getSquare().getLawn().getController().isGuiActivated()) {
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println(id + " final location: " + square.getX() + " "
				+ square.getY() + " " + orientation.getStringCode());
	}

	@Override
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @return the square
	 */
	public Square getSquare() {
		return square;
	}
}
