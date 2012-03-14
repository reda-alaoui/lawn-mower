package com.mowitnow.lawnmower.mower;

import com.mowitnow.lawnmower.lawn.Square;

/**
 * Describes commands that a mower can execute.
 * 
 * @author Reda
 * 
 */
public interface Mower {

	/**
	 * 
	 * @param commands
	 */
	void executeCommands(String commands);

	/**
	 * 
	 */
	void turnRight();

	/**
	 * 
	 */
	void turnLeft();

	/**
	 * 
	 */
	void moveForward();

	/**
	 * 
	 * @return
	 */
	Orientation getOrientation();

	/**
	 * 
	 * @return
	 */
	Square getSquare();
}
